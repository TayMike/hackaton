package com.fiap.hackaton.cache.core;

import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.HospitalRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.core.hospital.gateway.HospitalDatabaseGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitConfig(HospitalDatabaseGatewayCacheTest.TestConfig.class)
public class HospitalDatabaseGatewayCacheTest {

    private final HospitalGateway hospitalGateway;
    private final CacheManager cacheManager;

    @MockBean
    private HospitalRepository hospitalRepository;

    private Hospital hospital;

    @org.springframework.boot.test.context.TestConfiguration
    @EnableCaching
    static class TestConfig {
        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("hospital", "hospitais");
        }

        @Bean
        public HospitalGateway hospitalGateway(HospitalRepository hospitalRepository) {
            return new HospitalDatabaseGateway(hospitalRepository);
        }
    }

    @Autowired
    public HospitalDatabaseGatewayCacheTest(HospitalGateway hospitalGateway, CacheManager cacheManager) {
        this.hospitalGateway = hospitalGateway;
        this.cacheManager = cacheManager;
    }

    @BeforeEach
    public void setUp() {
        hospital = new Hospital(
                "Test Hospital",
                "12345678901234",
                Arrays.asList(UUID.randomUUID(), UUID.randomUUID()),
                "12345678",
                100,
                50,
                100
        );
        hospital.setId(UUID.randomUUID());
    }

    @Test
    public void testHospitalConstructorAndGetters() {
        assertNotNull(hospital.getId(), "ID should be set");
        assertEquals("Test Hospital", hospital.getNome(), "Nome should match constructor value");
        assertEquals("12345678901234", hospital.getCnpj(), "CNPJ should match constructor value");
        assertEquals(2, hospital.getColaboradoresIds().size(), "ColaboradoresIds should contain 2 UUIDs");
        assertEquals("12345678", hospital.getCep(), "CEP should match constructor value");
        assertEquals(100, hospital.getNumero(), "Numero should match constructor value");
        assertEquals(50, hospital.getQuantidadeLeitoAtual(), "QuantidadeLeitoAtual should match constructor value");
        assertEquals(100, hospital.getQuantidadeLeitoMaximo(), "QuantidadeLeitoMaximo should match constructor value");
    }

    @Test
    public void testHospitalSetters() {
        Hospital newHospital = new Hospital();
        newHospital.setId(UUID.randomUUID());
        newHospital.setNome("Updated Hospital");
        newHospital.setCnpj("98765432109876");
        newHospital.setColaboradoresIds(List.of(UUID.randomUUID()));
        newHospital.setCep("87654321");
        newHospital.setNumero(200);
        newHospital.setQuantidadeLeitoAtual(75);
        newHospital.setQuantidadeLeitoMaximo(150);

        assertNotNull(newHospital.getId(), "ID should be set");
        assertEquals("Updated Hospital", newHospital.getNome(), "Nome should be updated");
        assertEquals("98765432109876", newHospital.getCnpj(), "CNPJ should be updated");
        assertEquals(1, newHospital.getColaboradoresIds().size(), "ColaboradoresIds should contain 1 UUID");
        assertEquals("87654321", newHospital.getCep(), "CEP should be updated");
        assertEquals(200, newHospital.getNumero(), "Numero should be updated");
        assertEquals(75, newHospital.getQuantidadeLeitoAtual(), "QuantidadeLeitoAtual should be updated");
        assertEquals(150, newHospital.getQuantidadeLeitoMaximo(), "QuantidadeLeitoMaximo should be updated");
    }

    @Test
    public void testHospitalSerialization() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(hospital);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Hospital deserialized = (Hospital) ois.readObject();
        ois.close();

        assertEquals(hospital.getId(), deserialized.getId(), "Deserialized ID should match");
        assertEquals(hospital.getNome(), deserialized.getNome(), "Deserialized Nome should match");
        assertEquals(hospital.getCnpj(), deserialized.getCnpj(), "Deserialized CNPJ should match");
        assertEquals(hospital.getColaboradoresIds(), deserialized.getColaboradoresIds(), "Deserialized ColaboradoresIds should match");
        assertEquals(hospital.getCep(), deserialized.getCep(), "Deserialized CEP should match");
        assertEquals(hospital.getNumero(), deserialized.getNumero(), "Deserialized Numero should match");
        assertEquals(hospital.getQuantidadeLeitoAtual(), deserialized.getQuantidadeLeitoAtual(), "Deserialized QuantidadeLeitoAtual should match");
        assertEquals(hospital.getQuantidadeLeitoMaximo(), deserialized.getQuantidadeLeitoMaximo(), "Deserialized QuantidadeLeitoMaximo should match");
    }

    @Test
    public void testHospitalCacheIntegration() {
        HospitalSchema savedSchema = new HospitalSchema(hospital);
        when(hospitalRepository.save(any(HospitalSchema.class))).thenReturn(savedSchema);
        when(hospitalRepository.findById(hospital.getId())).thenReturn(Optional.of(savedSchema));
        when(hospitalRepository.findAll()).thenReturn(List.of(savedSchema));

        Hospital saved = hospitalGateway.save(hospital);
        Cache hospitalCache = cacheManager.getCache("hospital");
        Cache hospitaisCache = cacheManager.getCache("hospitais");
        assertNotNull(hospitalCache, "Cache 'hospital' should exist");
        Cache.ValueWrapper cachedHospital = hospitalCache.get(hospital.getId());
        assertNotNull(cachedHospital, "Cache should contain hospital ID");
        assertEquals(saved, cachedHospital.get(), "Cached hospital should match saved");
        assertNotNull(hospitaisCache);
        assertNull(hospitaisCache.get("hospitais"), "Hospitais cache should be evicted after save");

        List<Hospital> hospitals = hospitalGateway.findAll();
        Cache.ValueWrapper cachedHospitais = hospitaisCache.get("hospitais");
        assertNotNull(cachedHospitais, "Hospitais cache should contain 'hospitais' key");
        assertEquals(hospitals, cachedHospitais.get(), "Cached hospitais should match findAll result");

        Optional<Hospital> found = hospitalGateway.findById(hospital.getId());
        assertTrue(found.isPresent(), "Hospital should be found");
        assertEquals(saved, found.get(), "Found hospital should match saved");

        hospital.setNome("Updated Hospital");
        when(hospitalRepository.save(any(HospitalSchema.class))).thenReturn(new HospitalSchema(hospital));
        Hospital updated = hospitalGateway.update(hospital);
        cachedHospital = hospitalCache.get(hospital.getId());
        assertNotNull(cachedHospital, "Cache should contain updated hospital");
        assertEquals(updated, cachedHospital.get(), "Cache should reflect updated hospital");

        hospitalGateway.deleteById(hospital.getId());
        assertNull(hospitalCache.get(hospital.getId()), "Hospital cache should be evicted");
        assertNull(hospitaisCache.get("hospitais"), "Hospitais cache should be evicted");
        verify(hospitalRepository, times(1)).deleteById(hospital.getId());
    }
}
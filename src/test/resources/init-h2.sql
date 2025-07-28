-- Limpar tabelas existentes
DROP TABLE IF EXISTS entrega_quantidade_insumo;
DROP TABLE IF EXISTS entrega_insumo;
DROP TABLE IF EXISTS estoque_quantidade_insumo;
DROP TABLE IF EXISTS estoque_insumo;
DROP TABLE IF EXISTS colaborador;
DROP TABLE IF EXISTS hospital;
DROP TABLE IF EXISTS insumo;

-- Criar tabelas
CREATE TABLE hospital (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    cep VARCHAR(8) NOT NULL,
    numero INT NOT NULL,
    quantidade_leito_atual INT NOT NULL,
    quantidade_leito_maximo INT NOT NULL,
    colaboradores_ids VARCHAR(1000) NOT NULL DEFAULT '[]' -- Usar VARCHAR para compatibilidade
);

CREATE TABLE colaborador (
    id UUID PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    primeiro_dia_cadastro TIMESTAMP NOT NULL,
    ultimo_dia_cadastro TIMESTAMP,
    cep VARCHAR(8) NOT NULL,
    numero_casa INT NOT NULL,
    hospital_id UUID NOT NULL,
    setor VARCHAR(50) NOT NULL,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id)
);

CREATE TABLE insumo (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    custo DECIMAL(10,2) NOT NULL,
    quantidade BIGINT NOT NULL,
    peso BIGINT NOT NULL,
    validade TIMESTAMP NOT NULL,
    marca VARCHAR(50) NOT NULL,
    unidade_medida VARCHAR(50) NOT NULL
);

CREATE TABLE entrega_insumo (
    id UUID PRIMARY KEY,
    colaborador_id UUID NOT NULL,
    data_hora_recebimento TIMESTAMP NOT NULL,
    hospital_id UUID NOT NULL,
    FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
    FOREIGN KEY (hospital_id) REFERENCES hospital(id)
);

CREATE TABLE entrega_quantidade_insumo (
    entrega_id UUID NOT NULL,
    insumo_id UUID NOT NULL,
    quantidade BIGINT NOT NULL,
    FOREIGN KEY (entrega_id) REFERENCES entrega_insumo(id),
    FOREIGN KEY (insumo_id) REFERENCES insumo(id)
);

CREATE TABLE estoque_insumo (
    id UUID PRIMARY KEY,
    hospital_id UUID NOT NULL,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id)
);

CREATE TABLE estoque_quantidade_insumo (
    estoque_id UUID NOT NULL,
    insumo_id UUID NOT NULL,
    quantidade BIGINT NOT NULL,
    FOREIGN KEY (estoque_id) REFERENCES estoque_insumo(id),
    FOREIGN KEY (insumo_id) REFERENCES insumo(id)
);

-- Inserir dados
INSERT INTO hospital (id, nome, cnpj, cep, numero, quantidade_leito_atual, quantidade_leito_maximo, colaboradores_ids)
VALUES ('11111111-1111-1111-1111-111111111111', 'Hospital Central', '16365678283501', '12345678', 100, 10, 20, '["22222222-2222-2222-2222-222222222222"]');

INSERT INTO colaborador (id, cpf, nome, matricula, primeiro_dia_cadastro, ultimo_dia_cadastro, cep, numero_casa, hospital_id, setor)
VALUES ('22222222-2222-2222-2222-222222222222', '57263678900', 'Maria Souza', 'MK306', '2025-03-01 09:00:00', NULL, '12345000', 123, '11111111-1111-1111-1111-111111111111', 'Recepção');

INSERT INTO insumo (id, nome, custo, quantidade, peso, validade, marca, unidade_medida)
VALUES ('33333333-3333-3333-3333-333333333333', 'Irona', 12.50, 100, 500, '2025-03-01 09:00:00', 'FarmaciaCentral', 'MG'),
       ('44444444-4444-4444-4444-444444444444', 'Acetamol', 12.50, 100, 500, '2025-03-01 09:00:00', 'FarmaciaCentral', 'MG');

INSERT INTO estoque_insumo (id, hospital_id)
VALUES ('55555555-5555-5555-5555-555555555555', '11111111-1111-1111-1111-111111111111');

INSERT INTO estoque_quantidade_insumo (estoque_id, insumo_id, quantidade)
VALUES ('55555555-5555-5555-5555-555555555555', '33333333-3333-3333-3333-333333333333', 100),
       ('55555555-5555-5555-5555-555555555555', '44444444-4444-4444-4444-444444444444', 100);
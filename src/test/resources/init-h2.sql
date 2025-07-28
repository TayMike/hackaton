-- Criar tabelas
CREATE TABLE hospital (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    cnpj VARCHAR(14),
    cep VARCHAR(8),
    numero INT,
    quantidade_leito_atual INT,
    quantidade_leito_maximo INT
);

CREATE TABLE colaborador (
    id UUID PRIMARY KEY,
    cpf VARCHAR(11),
    nome VARCHAR(255),
    matricula VARCHAR(50),
    primeiro_dia_cadastro VARCHAR(50),
    ultimo_dia_cadastro VARCHAR(50),
    cep VARCHAR(8),
    numero_casa INT,
    hospital_id UUID,
    setor VARCHAR(50),
    FOREIGN KEY (hospital_id) REFERENCES hospital(id)
);

CREATE TABLE insumo (
    id UUID PRIMARY KEY,
    nome VARCHAR(255),
    custo DOUBLE,
    quantidade INT,
    peso DOUBLE,
    validade VARCHAR(50),
    marca VARCHAR(255),
    unidade_medida VARCHAR(10)
);

-- Inserir dados
INSERT INTO hospital (id, nome, cnpj, cep, numero, quantidade_leito_atual, quantidade_leito_maximo)
VALUES (UUID(), 'Hospital Central', '16365678283501', '12345678', 100, 10, 20);

INSERT INTO colaborador (id, cpf, nome, matricula, primeiro_dia_cadastro, ultimo_dia_cadastro, cep, numero_casa, hospital_id, setor)
VALUES (UUID(), '57263678900', 'Maria Souza', 'MK306', CURRENT_TIMESTAMP, NULL, '12345000', 123, (SELECT id FROM hospital LIMIT 1), 'Recepção');

INSERT INTO insumo (id, nome, custo, quantidade, peso, validade, marca, unidade_medida)
VALUES (UUID(), 'Irona', 12.50, 100, 500, CURRENT_TIMESTAMP, 'FarmaciaCentral', 'MG');

INSERT INTO insumo (id, nome, custo, quantidade, peso, validade, marca, unidade_medida)
VALUES (UUID(), 'Acetamol', 12.50, 100, 500, CURRENT_TIMESTAMP, 'FarmaciaCentral', 'MG');
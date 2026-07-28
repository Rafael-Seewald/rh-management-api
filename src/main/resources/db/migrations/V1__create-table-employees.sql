CREATE TABLE funcionario_db(
    id BIGSERIAL PRIMARY KEY,
    cargo VARCHAR(100),
    CPF VARCHAR(11) NOT NULL UNIQUE,
    data_admissao DATE,
    email VARCHAR(100) NOT NULL UNIQUE,
    nome VARCHAR(150),
    salario DECIMAL(10, 2)
);
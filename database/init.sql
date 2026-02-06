CREATE TABLE candidatos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    pais VARCHAR(50),
    cep VARCHAR(10),
    descricao TEXT,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    descricao TEXT,
    pais VARCHAR(50),
    cep VARCHAR(10),
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE competencias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE vagas (
    id SERIAL PRIMARY KEY,
    id_empresa INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    local VARCHAR(50),
    FOREIGN KEY (id_empresa) REFERENCES empresas(id)
);


CREATE TABLE competencias_candidatos (
    id_candidato INT NOT NULL,
    id_competencia INT NOT NULL,
    PRIMARY KEY (id_candidato, id_competencia),
    FOREIGN KEY (id_candidato) REFERENCES candidatos(id),
    FOREIGN KEY (id_competencia) REFERENCES competencias(id)
);

CREATE TABLE competencias_vagas (
    id_vaga INT NOT NULL,
    id_competencia INT NOT NULL,
    PRIMARY KEY (id_vaga, id_competencia),
    FOREIGN KEY (id_vaga) REFERENCES vagas(id),
    FOREIGN KEY (id_competencia) REFERENCES competencias(id)
);




INSERT INTO competencias (nome) VALUES 
('Java'), ('Groovy'), ('Python'), ('JavaScript'), ('TypeScript'), ('Spring Boot'), ('SQL');


INSERT INTO candidatos (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) VALUES
('Sandubinha', 'Lanches', '1995-05-20', 'sanduba@email.com', '111.111.111-11', 'Brasil', '88000-000', 'Dev Fullstack faminto por código', '123456'),
('X-Tudo', 'Completo', '1998-10-10', 'xtudo@email.com', '222.222.222-22', 'Brasil', '01000-000', 'Sei de tudo um pouco', 'senha123'),
('Hot', 'Dog', '2000-01-01', 'hotdog@email.com', '333.333.333-33', 'EUA', '90210', 'Simples e eficiente', 'vina123');


INSERT INTO empresas (nome, cnpj, email, descricao, pais, cep, senha) VALUES
('Pastelsoft', '00.111.222/0001-99', 'rh@pastelsoft.com', 'Empresa crocante de tecnologia', 'Brasil', '88000-000', 'pastel123'),
('BurguerKing Code', '99.888.777/0001-11', 'jobs@bkcode.com', 'Grelhado no fogo como código bom', 'EUA', '10001', 'whopper123');


INSERT INTO vagas (id_empresa, nome, descricao, local) VALUES
(1, 'Dev Java Junior', 'Vaga para quem gosta de pastel e Java', 'Remoto'),
(1, 'Dev Groovy Pleno', 'Manutenção de legados crocantes', 'São Paulo'),
(2, 'Tech Lead', 'Liderar a chapa quente', 'New York');


INSERT INTO competencias_candidatos (id_candidato, id_competencia) VALUES (1, 1), (1, 2);


INSERT INTO competencias_vagas (id_vaga, id_competencia) VALUES (1, 1), (1, 7);
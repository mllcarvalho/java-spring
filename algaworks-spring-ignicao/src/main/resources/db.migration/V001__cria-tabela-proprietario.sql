CREATE TABLE proprietario (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE proprietario ADD CONSTRAINT uk_proprietario_email UNIQUE (email);
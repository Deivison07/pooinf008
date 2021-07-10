CREATE DATABASE AVIII;

CREATE TABLE IF NOT EXISTS COR (
    id SERIAL,
    nome VARCHAR(150) NOT NULL,
    red INT NOT NULL DEFAULT 0,
    green INT NOT NULL DEFAULT 0,
    blue INT NOT NULL DEFAULT 0,
    ciano INT NOT NULL DEFAULT 0,
    magenta INT NOT NULL DEFAULT 0,
    amarelo INT NOT NULL DEFAULT 0,
    preto INT NOT NULL DEFAULT 0,
    simbolo INT NOT NULL,
    tipo INT NOT NULL,
    PRIMARY KEY (id)
);
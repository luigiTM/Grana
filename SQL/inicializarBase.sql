DROP DATABASE IF EXISTS grana;

CREATE DATABASE grana TABLESPACE grana;

CREATE TABLE IF NOT EXISTS Usuario(
	id_usuario serial PRIMARY KEY,
	nome varchar(50) NOT NULL,
	senha VARCHAR (50) NOT NULL,
	email VARCHAR (355) UNIQUE NOT NULL,
	criado_em TIMESTAMP NOT NULL,
	ultimo_acesso TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Grana(
	id_grana serial PRIMARY KEY,
	nome varchar(50) NOT NULL,
	id_usuario integer NOT NULL,
	criado_em TIMESTAMP NOT NULL,
	modificado_em TIMESTAMP,
	CONSTRAINT usuario_fk FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS Gasto(
	id_gasto serial PRIMARY KEY,
	id_grana integer NOT NULL,
	tipo varchar(50) NOT NULL,
	valor decimal(2) NOT NULL,
	data_gasto date NOT NULL,
	CONSTRAINT grana_fk FOREIGN KEY (id_grana) REFERENCES Grana (id_grana) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS Pessoa(
	id_pessoa serial PRIMARY KEY,
	nome varchar(50) NOT NULL,
	valor_gasto decimal(2) NOT NULL,
	percentual_gasto decimal(5) NOT NULl,
	id_usuario_criacao integer NOT NULL,
	email varchar(50)
);

CREATE TABLE IF NOT EXISTS Gasto_Pessoa(
	id_gasto_fk integer NOT NULL,
	id_pessoa_fk integer NOT NULL,
	CONSTRAINT gasto_fk FOREIGN KEY (id_gasto_fk) REFERENCES Gasto (id_gasto) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT pessoa_fk FOREIGN KEY (id_pessoa_fk) REFERENCES Pessoa (id_pessoa) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE Grana ADD COLUMN codigo_de_acesso varchar(256) UNIQUE NOT NULL;

ALTER TABLE pessoa DROP COLUMN valor_gasto;
ALTER TABLE pessoa DROP COLUMN percentual_gasto;

ALTER TABLE gasto_pessoa ADD COLUMN valor_gasto decimal(2) NOT NULL;
ALTER TABLE gasto_pessoa ADD COLUMN percentual_gasto decimal(5) NOT NULl;

ALTER TABLE pessoa RENAME COLUMN id_usuario_criacao TO usuario_criacao;

ALTER TABLE gasto ALTER COLUMN valor TYPE DECIMAL(50,2);

ALTER TABLE gasto_pessoa ALTER COLUMN valor_gasto TYPE DECIMAL(50,2);

ALTER TABLE gasto_pessoa ALTER COLUMN percentual_gasto TYPE DECIMAL(6,5);

ALTER TABLE gasto_pessoa RENAME COLUMN id_gasto_fk TO id_gasto;
ALTER TABLE gasto_pessoa RENAME COLUMN id_pessoa_fk TO id_pessoa;

ALTER TABLE gasto_pessoa ADD COLUMN id_gasto_pessoa serial PRIMARY KEY;

ALTER TABLE Usuario ALTER COLUMN senha TYPE VARCHAR(256);

CREATE TABLE IF NOT EXISTS perfil(
	id_usuario integer NOT NULL,
	id_perfil integer NOT NULL
);
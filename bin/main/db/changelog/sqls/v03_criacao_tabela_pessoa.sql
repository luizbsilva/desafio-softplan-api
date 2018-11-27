CREATE TABLE pessoa (
	codigo BIGINT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	ativo BOOLEAN NOT NULL,
	PRIMARY KEY ( codigo ),
    CONSTRAINT unique_pessoa_codigo UNIQUE( codigo ));

CREATE SEQUENCE pessoa_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE pessoa_codigo_seq OWNER TO postgres;  
ALTER TABLE pessoa ALTER COLUMN codigo SET DEFAULT nextval('pessoa_codigo_seq'::regclass);	
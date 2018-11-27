CREATE TABLE usuario (
	codigo BIGINT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,	
	PRIMARY KEY ( codigo ),
    CONSTRAINT unique_usuario_codigo UNIQUE( codigo ));

CREATE SEQUENCE usuario_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE usuario_codigo_seq OWNER TO postgres;  
ALTER TABLE usuario ALTER COLUMN codigo SET DEFAULT nextval('usuario_codigo_seq'::regclass);	


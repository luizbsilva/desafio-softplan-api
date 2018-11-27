CREATE TABLE contato (
	codigo BIGINT NOT NULL,
	codigo_pessoa BIGINT NOT NULL,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	telefone VARCHAR(20) NOT NULL,	
    CONSTRAINT unique_contato_codigo UNIQUE( codigo )) ; 
    
ALTER TABLE contato ADD CONSTRAINT fk_contato_pessoa FOREIGN KEY (codigo_pessoa)
	REFERENCES pessoa(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

CREATE SEQUENCE contato_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE contato_codigo_seq OWNER TO postgres;  
ALTER TABLE contato ALTER COLUMN codigo SET DEFAULT nextval('contato_codigo_seq'::regclass);	

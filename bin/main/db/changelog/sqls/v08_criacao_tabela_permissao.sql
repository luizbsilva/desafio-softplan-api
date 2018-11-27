CREATE TABLE permissao (
	codigo BIGINT,
	nome VARCHAR(50) NOT NULL,		
	PRIMARY KEY ( codigo ),
    CONSTRAINT unique_permissao_codigo UNIQUE( codigo ));

CREATE SEQUENCE permissao_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE permissao_codigo_seq OWNER TO postgres;  
ALTER TABLE permissao ALTER COLUMN codigo SET DEFAULT nextval('permissao_codigo_seq'::regclass);	


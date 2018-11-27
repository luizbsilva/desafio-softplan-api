CREATE TABLE categoria ( 
	codigo BIGINT NOT NULL,
	nome VARCHAR( 50 ) NOT NULL,
	PRIMARY KEY ( codigo ),
	CONSTRAINT unique_categoria_codigo UNIQUE( codigo ) );

CREATE SEQUENCE categoria_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE categoria_codigo_seq OWNER TO postgres;  
ALTER TABLE categoria ALTER COLUMN codigo SET DEFAULT nextval('categoria_codigo_seq'::regclass);	
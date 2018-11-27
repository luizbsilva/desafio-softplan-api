CREATE TABLE usuario_permissao (
	codigo BIGINT NOT NULL,
	codigo_usuario BIGINT NOT NULL,
	codigo_permissao BIGINT NOT NULL,	
    CONSTRAINT unique_usuario_permissao_codigo UNIQUE( codigo )) ; 
    
ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuario_permissao_usuario FOREIGN KEY (codigo_usuario)
	REFERENCES usuario(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;
    
ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuario_permissao_permissao FOREIGN KEY (codigo_permissao)
	REFERENCES permissao(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

CREATE SEQUENCE usuario_permissao_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE usuario_permissao_codigo_seq OWNER TO postgres;  
ALTER TABLE usuario_permissao ALTER COLUMN codigo SET DEFAULT nextval('usuario_permissao_codigo_seq'::regclass);	

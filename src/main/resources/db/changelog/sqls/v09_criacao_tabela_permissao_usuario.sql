CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT NOT NULL,
	codigo_permissao BIGINT NOT NULL,	
    CONSTRAINT unique_usuario_permissao_codigo UNIQUE( codigo_usuario, codigo_permissao )) ; 
    
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

CREATE TABLE cidade(
	codigo BIGINT NOT NULL,
	nome VARCHAR(50) NOT NULL,
  codigo_estado BIGINT NOT NULL,	
    CONSTRAINT unique_cidade_codigo UNIQUE( codigo )) ;
    
ALTER TABLE cidade ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (codigo_estado)
	REFERENCES estado(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;
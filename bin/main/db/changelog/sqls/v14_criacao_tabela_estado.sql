CREATE TABLE estado(
	codigo BIGINT NOT NULL,
	nome VARCHAR(50) NOT NULL,	
    CONSTRAINT unique_estado_codigo UNIQUE( codigo )) ;
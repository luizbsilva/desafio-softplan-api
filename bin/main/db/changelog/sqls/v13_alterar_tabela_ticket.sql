ALTER TABLE ticket ADD COLUMN anexo VARCHAR(200);
ALTER TABLE ticket ADD COLUMN codigo_usuario BIGINT;
    
ALTER TABLE ticket ADD CONSTRAINT fk_ticket_usuario FOREIGN KEY (codigo_usuario)
	REFERENCES usuario(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

CREATE TABLE ticket (
	codigo BIGINT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE,
	data_cricao DATE NOT NULL,	
	valor NUMERIC(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT NOT NULL,
	codigo_pessoa BIGINT NOT NULL,		
	PRIMARY KEY ( codigo ),
    CONSTRAINT unique_ticket_codigo UNIQUE( codigo )) ; 
    
ALTER TABLE ticket ADD CONSTRAINT fk_ticket_categoria FOREIGN KEY (codigo_categoria)
	REFERENCES categoria(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;
    
ALTER TABLE ticket ADD CONSTRAINT fk_ticket_pessoa FOREIGN KEY (codigo_pessoa)
	REFERENCES pessoa(codigo)
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;
	
CREATE SEQUENCE ticket_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE ticket_codigo_seq OWNER TO postgres;  
ALTER TABLE ticket ALTER COLUMN codigo SET DEFAULT nextval('ticket_codigo_seq'::regclass);	


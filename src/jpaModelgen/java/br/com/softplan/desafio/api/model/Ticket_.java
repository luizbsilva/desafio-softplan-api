package br.com.softplan.desafio.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ticket.class)
public abstract class Ticket_ {

	public static volatile SingularAttribute<Ticket, Long> codigo;
	public static volatile SingularAttribute<Ticket, String> observacao;
	public static volatile SingularAttribute<Ticket, TipoTicket> tipo;
	public static volatile SingularAttribute<Ticket, Pessoa> pessoa;
	public static volatile SingularAttribute<Ticket, String> anexo;
	public static volatile SingularAttribute<Ticket, LocalDate> dataVencimento;
	public static volatile SingularAttribute<Ticket, Categoria> categoria;
	public static volatile SingularAttribute<Ticket, BigDecimal> valor;
	public static volatile SingularAttribute<Ticket, Usuario> usuario;
	public static volatile SingularAttribute<Ticket, LocalDate> dataCriacao;
	public static volatile SingularAttribute<Ticket, String> descricao;

}


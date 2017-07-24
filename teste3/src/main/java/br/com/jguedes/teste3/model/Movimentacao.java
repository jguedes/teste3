package br.com.jguedes.teste3.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the movimentacao database table.
 * 
 */
@Entity
@NamedQuery(name="Movimentacao.findAll", query="SELECT m FROM Movimentacao m")
public class Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MovimentacaoPK id;

	private String comprovante;

	private String detalhamento;

	private byte fechado;

	private BigDecimal valor;

	//bi-directional many-to-one association to Conta
	@ManyToOne
	private Conta conta;

	public Movimentacao() {
	}

	public MovimentacaoPK getId() {
		return this.id;
	}

	public void setId(MovimentacaoPK id) {
		this.id = id;
	}

	public String getComprovante() {
		return this.comprovante;
	}

	public void setComprovante(String comprovante) {
		this.comprovante = comprovante;
	}

	public String getDetalhamento() {
		return this.detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}

	public byte getFechado() {
		return this.fechado;
	}

	public void setFechado(byte fechado) {
		this.fechado = fechado;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
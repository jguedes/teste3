package br.com.jguedes.teste3.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the conta database table.
 * 
 */
@Entity
@NamedQuery(name="Conta.findAll", query="SELECT c FROM Conta c")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTA_ID_GENERATOR", sequenceName="HIBERNATE_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTA_ID_GENERATOR")
	private int id;

	private int tipo;

	private String titulo;

	//bi-directional many-to-one association to Movimentacao
	@OneToMany(mappedBy="conta")
	private List<Movimentacao> movimentacoes;

	public Conta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Movimentacao> getMovimentacoes() {
		return this.movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public Movimentacao addMovimentacao(Movimentacao movimentacao) {
		getMovimentacoes().add(movimentacao);
		movimentacao.setConta(this);

		return movimentacao;
	}

	public Movimentacao removeMovimentacao(Movimentacao movimentacao) {
		getMovimentacoes().remove(movimentacao);
		movimentacao.setConta(null);

		return movimentacao;
	}

}
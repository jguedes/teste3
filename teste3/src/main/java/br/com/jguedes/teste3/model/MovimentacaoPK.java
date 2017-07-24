package br.com.jguedes.teste3.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the movimentacao database table.
 * 
 */
@Embeddable
public class MovimentacaoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String tipo;

	private int ano;

	private String mes;

	private String num;

	private String seq;

	public MovimentacaoPK() {
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getAno() {
		return this.ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getMes() {
		return this.mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getNum() {
		return this.num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSeq() {
		return this.seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MovimentacaoPK)) {
			return false;
		}
		MovimentacaoPK castOther = (MovimentacaoPK)other;
		return 
			this.tipo.equals(castOther.tipo)
			&& (this.ano == castOther.ano)
			&& this.mes.equals(castOther.mes)
			&& this.num.equals(castOther.num)
			&& this.seq.equals(castOther.seq);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipo.hashCode();
		hash = hash * prime + this.ano;
		hash = hash * prime + this.mes.hashCode();
		hash = hash * prime + this.num.hashCode();
		hash = hash * prime + this.seq.hashCode();
		
		return hash;
	}
}
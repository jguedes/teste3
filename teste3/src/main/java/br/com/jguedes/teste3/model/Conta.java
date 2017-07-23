package br.com.jguedes.teste3.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the conta database table.
 * 
 */
@Entity
@NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CONTA_ID_GENERATOR", sequenceName = "HIBERNATE_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTA_ID_GENERATOR")
	private Integer id;

	private int tipo;

	private String titulo;

	public Conta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public String imprimir() {
		return new StringBuilder().append("id=").append(id).append(";tipo=").append(tipo).append(";titulo=")
				.append(titulo).toString();
	}

}
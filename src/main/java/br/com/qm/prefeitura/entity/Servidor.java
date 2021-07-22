package br.com.qm.prefeitura.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Servidor {
	@Id
	@Column(name = "id_servidor")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idServidor;
	private String nome;
	@Column(unique = true, length = 14)
	private String cpf;
	private double salario;
	private String cargo;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "secretaria_fk", referencedColumnName = "id_secretaria")
	private Secretaria secretaria;

	public Servidor(long idServidor, String nome, String cpf, double salario, String cargo, Secretaria secretaria) {
		super();
		this.idServidor = idServidor;
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.cargo = cargo;
		this.secretaria = secretaria;
	}

	public Servidor() {
		// TODO Auto-generated constructor stub
	}

	public long getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(long idServidor) {
		this.idServidor = idServidor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}
	
	
	
}

package br.com.qm.prefeitura.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Projeto {
	@Id
	@Column(name = "id_projeto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idProjeto;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "secretaria_fk", referencedColumnName = "id_secretaria")
	private Secretaria secretaria;
	private String nome;
	private double custo;
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataPrevistaConclusao;
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataConclusao;
	private boolean concluido;

	public Projeto(long idProjeto, Secretaria secretaria, String nome, double custo, LocalDate dataPrevistaConclusao,
			LocalDate dataConclusao, boolean concluido) {
		super();
		this.idProjeto = idProjeto;
		this.secretaria = secretaria;
		this.nome = nome;
		this.custo = custo;
		this.dataPrevistaConclusao = dataPrevistaConclusao;
		this.dataConclusao = dataConclusao;
		this.concluido = concluido;
	}

	public Projeto() {
		// TODO Auto-generated constructor stub
	}

	public long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(long idProjeto) {
		this.idProjeto = idProjeto;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public LocalDate getDataPrevistaConclusao() {
		return dataPrevistaConclusao;
	}

	public void setDataPrevistaConclusao(LocalDate dataPrevistaConclusao) {
		this.dataPrevistaConclusao = dataPrevistaConclusao;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}
	
	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	
	
}

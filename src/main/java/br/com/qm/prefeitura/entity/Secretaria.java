package br.com.qm.prefeitura.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.qm.prefeitura.enums.Pasta;


@Entity
public class Secretaria {
	@Id
	@Column(name = "id_secretaria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idSecretaria;
	@Column
	private String nome;
	@JsonManagedReference
	@OneToMany(mappedBy = "secretaria", cascade = CascadeType.ALL)
	private List<Projeto> projetos;
	@JsonManagedReference
	@OneToMany(mappedBy = "secretaria", cascade = CascadeType.ALL)
	private List<Servidor> servidores;
	@Enumerated(EnumType.STRING)
	private Pasta pasta;
	@Column(name = "orcamento_folha")
	private double orcamentoFolha;
	@Column(name = "orcamento_projeto")
	private double orcamentoProjeto;

	public Secretaria(long idSecretaria, String nome, List<Projeto> projetos, List<Servidor> servidores, Pasta pasta,
			double orcamentoFolha, double orcamentoProjeto) {
		super();
		this.idSecretaria = idSecretaria;
		this.nome = nome;
		this.projetos = projetos;
		this.servidores = servidores;
		this.pasta = pasta;
		this.orcamentoFolha = orcamentoFolha;
		this.orcamentoProjeto = orcamentoProjeto;
	}

	public Secretaria(String nome, Pasta pasta, double orcamentoFolha, double orcamentoProjeto) {
		super();
		this.nome = nome;
		this.pasta = pasta;
		this.orcamentoFolha = orcamentoFolha;
		this.orcamentoProjeto = orcamentoProjeto;
	}

	public Secretaria() {
		super();
	}

	public long getIdSecretaria() {
		return idSecretaria;
	}

	public void setIdSecretaria(long idSecretaria) {
		this.idSecretaria = idSecretaria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Projeto> getProjeto() {
		return projetos;
	}

	public void setProjeto(List<Projeto> projeto) {
		this.projetos = projeto;
	}

	public List<Servidor> getServidor() {
		return servidores;
	}

	public void setServidor(List<Servidor> servidor) {
		this.servidores = servidor;
	}

	public Pasta getPasta() {
		return pasta;
	}

	public void setPasta(Pasta pasta) {
		this.pasta = pasta;
	}

	public double getOrcamentoFolha() {
		return orcamentoFolha;
	}

	public void setOrcamentoFolha(double orcamentoFolha) {
		this.orcamentoFolha = orcamentoFolha;
	}

	public double getOrcamentoProjeto() {
		return orcamentoProjeto;
	}

	public void setOrcamentoProjeto(double orcamentoProjeto) {
		this.orcamentoProjeto = orcamentoProjeto;
	}
	
	
}

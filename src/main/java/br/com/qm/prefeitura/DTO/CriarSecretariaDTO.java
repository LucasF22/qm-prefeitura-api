package br.com.qm.prefeitura.DTO;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.qm.prefeitura.entity.Secretaria;
import br.com.qm.prefeitura.enums.Pasta;

public class CriarSecretariaDTO {
	
	@NotBlank
	private String nome;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Pasta pasta;
	@NotNull
	@Min(value = 1000)
	private double orcamentoFolha;
	@NotNull
	@Min(value = 1000)
	private double orcamentoProjeto;
	
	public CriarSecretariaDTO(@NotBlank String nome, @NotBlank Pasta pasta, @NotNull double orcamentoFolha,
			@NotNull double orcamentoProjeto) {
		super();
		this.nome = nome;
		this.pasta = pasta;
		this.orcamentoFolha = orcamentoFolha;
		this.orcamentoProjeto = orcamentoProjeto;
	}
	
	public Secretaria toEntity() {
		return new Secretaria(this.getNome(), this.getPasta(), this.orcamentoFolha, this.orcamentoProjeto);
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

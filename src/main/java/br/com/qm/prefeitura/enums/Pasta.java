package br.com.qm.prefeitura.enums;

public enum Pasta {
	SAUDE("Saúde"),
	EDUCACAO("Educação"),
	INFRAESTRUTURA("Infraestrutura"),
	TRANSPORTES("Transportes"),
	ECONOMIA("Econômia");
	
	private String descricao;
	
	private Pasta(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

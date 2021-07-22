package br.com.qm.prefeitura.DTO;

public class SecretariaCriadaDTO {
	private long idSecretaria;
	private String nome;
	private String mensagem;
	
	public SecretariaCriadaDTO(long idSecretaria, String nome, String mensagem) {
		this.idSecretaria = idSecretaria;
		this.nome = nome;
		this.mensagem = mensagem;
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}

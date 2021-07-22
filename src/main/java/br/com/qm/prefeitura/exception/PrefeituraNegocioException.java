package br.com.qm.prefeitura.exception;


public class PrefeituraNegocioException extends Exception {

	private static final long serialVersionUID = 2702408417423086799L;
	
	public PrefeituraNegocioException(String mensagem) {
		super(mensagem);
	}
}

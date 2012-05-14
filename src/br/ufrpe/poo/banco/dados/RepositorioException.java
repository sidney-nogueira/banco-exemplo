package br.ufrpe.poo.banco.dados;

public class RepositorioException extends Exception {
	
	private Throwable exception;
	
	public RepositorioException(String mensagem) {
		super(mensagem);
	}

	public RepositorioException(Throwable exception) {
		super(exception);
	}
	
}

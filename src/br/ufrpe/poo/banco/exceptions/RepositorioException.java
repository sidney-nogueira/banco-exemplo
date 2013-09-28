package br.ufrpe.poo.banco.exceptions;

/**
 * Excecao que representa erros de acesso a um meio de armazenamento
 * persistente.
 */
public class RepositorioException extends Exception {

	public RepositorioException(String mensagem) {
		super(mensagem);
	}

	public RepositorioException(Throwable exception) {
		super(exception);
	}

}

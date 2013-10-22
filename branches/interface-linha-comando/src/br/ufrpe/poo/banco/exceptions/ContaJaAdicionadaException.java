package br.ufrpe.poo.banco.exceptions;

/**
 * Excessao que representa erro na associacao de um numero de conta a um
 * cliente.
 * 
 * @author
 * 
 */
public class ContaJaAdicionadaException extends Exception {

	private static final long serialVersionUID = 1L;

	public ContaJaAdicionadaException() {
		super("O cliente já adicionou conta com esse número.");
	}

}

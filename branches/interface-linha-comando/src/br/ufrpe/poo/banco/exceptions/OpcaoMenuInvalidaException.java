package br.ufrpe.poo.banco.exceptions;

/**
 * Excessao que representa a escolha invalida de uma opcao do menu textual.
 * 
 * @author
 * 
 */
public class OpcaoMenuInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public OpcaoMenuInvalidaException() {
		super("A opção de menu informada é inválida.");
	}

}

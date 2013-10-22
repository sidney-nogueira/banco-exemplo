package br.ufrpe.poo.banco.exceptions;

/**
 * Excessao que representa erros na procura de cadastros de clientes.
 * 
 * @author
 * 
 */
public class ClienteNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClienteNaoCadastradoException() {
		super("Nenhuma cliente foi cadastrado com esse cpf.");
	}

}

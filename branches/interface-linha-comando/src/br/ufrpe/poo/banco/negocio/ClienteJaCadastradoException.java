package br.ufrpe.poo.banco.negocio;

/**
 * Excessao que representa um erro ao inseir um cliente no repositorio.
 * 
 * @author
 * 
 */
public class ClienteJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClienteJaCadastradoException() {
		super("Cliente ja cadastrado com esse cpf");
	}

}

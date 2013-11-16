package br.ufrpe.poo.banco.exceptions;

public class ClienteJaPossuiContaException extends Exception {

	private static final long serialVersionUID = 1L;

	public ClienteJaPossuiContaException() {
		super("O cliente j� possui esta conta associada a ele!");
	}
}

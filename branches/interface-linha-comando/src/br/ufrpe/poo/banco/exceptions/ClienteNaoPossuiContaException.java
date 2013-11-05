package br.ufrpe.poo.banco.exceptions;

public class ClienteNaoPossuiContaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ClienteNaoPossuiContaException(){
		super("O cliente n�o possui conta com este n�mero.");
	}

}

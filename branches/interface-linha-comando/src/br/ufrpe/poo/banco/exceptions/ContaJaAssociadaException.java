package br.ufrpe.poo.banco.exceptions;

public class ContaJaAssociadaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ContaJaAssociadaException(){
		super("N�mero de conta j� associada � um cliente.");
	}

}

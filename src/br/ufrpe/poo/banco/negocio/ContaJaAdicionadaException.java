package br.ufrpe.poo.banco.negocio;

public class ContaJaAdicionadaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ContaJaAdicionadaException(){
		super("O cliente j� adicionou conta com esse n�mero.");
	}

}
package br.ufrpe.poo.banco.gui;

public class OpcaoMenuInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public OpcaoMenuInvalidaException(){
		super("A op��o de menu informada � inv�lida.");
	}

}

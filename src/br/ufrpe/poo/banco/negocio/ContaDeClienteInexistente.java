package br.ufrpe.poo.banco.negocio;

public class ContaDeClienteInexistente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ContaDeClienteInexistente(){
		super("O cliente não possui uma conta com esse número.");
	}

}

package br.ufrpe.poo.banco.negocio;

public class ContaJaCadastradaException extends Exception {
	
	public ContaJaCadastradaException() {
		super("Ja existe uma conta cadastrada com este numero");
	}
}

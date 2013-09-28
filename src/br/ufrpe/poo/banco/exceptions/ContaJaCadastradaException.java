package br.ufrpe.poo.banco.exceptions;

public class ContaJaCadastradaException extends Exception {
	
	public ContaJaCadastradaException() {
		super("Ja existe uma conta cadastrada com este numero");
	}
}

package br.ufrpe.poo.banco.negocio;

public class ContaNaoEncontradaException extends Exception {

	public ContaNaoEncontradaException() {
		super("Conta nao encontrada");
	}
}

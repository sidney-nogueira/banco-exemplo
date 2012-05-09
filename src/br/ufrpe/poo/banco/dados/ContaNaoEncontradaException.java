package br.ufrpe.poo.banco.dados;

public class ContaNaoEncontradaException extends Exception {

	public ContaNaoEncontradaException() {
		super("Conta nao encontrada");
	}
}

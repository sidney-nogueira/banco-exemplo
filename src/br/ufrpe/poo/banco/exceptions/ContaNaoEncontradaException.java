package br.ufrpe.poo.banco.exceptions;

public class ContaNaoEncontradaException extends Exception {

	public ContaNaoEncontradaException() {
		super("Conta nao encontrada");
	}
}

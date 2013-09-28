package br.ufrpe.poo.banco.exceptions;

public class RenderJurosPoupancaException extends Exception {

	public RenderJurosPoupancaException () {
		super("Erro ao render juros, o numero informado nao eh de uma Poupanca");
	}
}

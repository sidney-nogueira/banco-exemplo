package br.ufrpe.poo.banco.negocio;

public class RenderBonusContaEspecialException extends Exception {

	public RenderBonusContaEspecialException () {
		super("Erro ao render bonus, o numero informado nao eh de uma Conta Especial");
	}
}

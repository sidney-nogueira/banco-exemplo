package br.ufrpe.poo.banco.exceptions;

public class AcessoMenuClienteBloqueadoExeception extends Exception {

	private static final long serialVersionUID = 1L;

	public AcessoMenuClienteBloqueadoExeception() {
		super(
				"N�mero m�ximo de tentativas de acessar o sistema alcan�ado. Sistema encerrado!");
	}

}

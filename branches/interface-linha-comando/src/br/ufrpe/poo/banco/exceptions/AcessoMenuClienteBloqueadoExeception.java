package br.ufrpe.poo.banco.exceptions;

public class AcessoMenuClienteBloqueadoExeception extends Exception {

	private static final long serialVersionUID = 1L;

	public AcessoMenuClienteBloqueadoExeception() {
		super(
				"Número máximo de tentativas de acessar o sistema alcançado. Sistema encerrado!");
	}

}

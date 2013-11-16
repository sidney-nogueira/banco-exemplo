package br.ufrpe.poo.banco.exceptions;

public class CampoVazioException extends Exception {

	private static final long serialVersionUID = 1L;

	public CampoVazioException(String valor) {
		super("O campo " + valor + " não foi preenchido!");
	}

}

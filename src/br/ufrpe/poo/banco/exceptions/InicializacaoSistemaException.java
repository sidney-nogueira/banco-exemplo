package br.ufrpe.poo.banco.exceptions;

public class InicializacaoSistemaException extends Exception {

	public InicializacaoSistemaException() {
		super("Nao foi possivel inicializar o sistema");
	}

}

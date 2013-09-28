package br.ufrpe.poo.banco.exceptions;

public class SaldoInsuficienteException extends Exception {

	public SaldoInsuficienteException (String numero, double saldo) {
		super("Saldo insuficiente. O saldo atual da conta "+numero+" = "+saldo);
	}
}

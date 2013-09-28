package br.ufrpe.poo.banco.negocio;

import java.io.Serializable;

import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;

public abstract class ContaAbstrata implements Serializable {

	private String numero;
	private double saldo;
	
	public ContaAbstrata(String numero, double valor) {
		this.numero = numero;
		this.saldo = valor;
	}
	
	public String getNumero() {
		return this.numero;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void creditar(double valor) {
		this.saldo = this.saldo + valor;
	}
	
	public abstract void debitar(double valor) throws SaldoInsuficienteException;

	protected void setSaldo(double saldo) {
		this.saldo = saldo;
	}
		
}

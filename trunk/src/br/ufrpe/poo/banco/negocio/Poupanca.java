package br.ufrpe.poo.banco.negocio;

public class Poupanca extends Conta {
	
	public Poupanca(String numero, double valor) {
		super(numero, valor);
	}
	
	public void renderJuros(double taxa) {
		double juros = this.getSaldo() * taxa;
		this.creditar(juros);
	}
}

package br.ufrpe.poo.banco.negocio;

public class ContaEspecial extends Conta {
	
	private double bonus;
	
	public ContaEspecial(String numero, double saldo, double bonus) {
		super(numero, saldo);
		this.bonus = bonus;

	}

	@Override
	public void creditar(double valor) {
		super.creditar(valor);
		bonus = bonus + (valor * 0.01);
	}

	public void renderBonus() {
		super.creditar(this.bonus);
		bonus = 0;
	}

	public double getBonus() {
		return this.bonus;
	}
}
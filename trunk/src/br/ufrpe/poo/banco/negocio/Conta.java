package br.ufrpe.poo.banco.negocio;

public class Conta extends ContaAbstrata {

	public Conta(String numero, double valor) {
		super(numero, valor);
	}

	@Override
	public void debitar(double valor) throws SaldoInsuficienteException {
		if (this.getSaldo() < valor)
			throw new SaldoInsuficienteException(this.getNumero(),
					this.getSaldo());
		this.setSaldo(this.getSaldo() - valor);
	}

}
package br.ufrpe.poo.banco.iterator;

import br.ufrpe.poo.banco.negocio.Cliente;

public class IteratorClienteArray implements IteratorCliente {

	private Cliente[] clientes;
	private int indice;

	public IteratorClienteArray(Cliente[] clientes) {
		this.clientes = clientes;
		this.indice = 0;
	}

	@Override
	public boolean hasNext() {

		return (this.indice < clientes.length && clientes[this.indice] != null);
	}

	@Override
	public Cliente next() {
		Cliente c = clientes[this.indice];
		this.indice += 1;
		return c;
	}

}

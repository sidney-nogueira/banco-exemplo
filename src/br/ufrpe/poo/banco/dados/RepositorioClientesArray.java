package br.ufrpe.poo.banco.dados;

import br.ufrpe.poo.banco.iterator.IteratorCliente;
import br.ufrpe.poo.banco.iterator.IteratorClienteArray;
import br.ufrpe.poo.banco.negocio.Cliente;

public class RepositorioClientesArray implements IRepositorioClientes {

	private Cliente[] clientes;
	private int indice;

	public RepositorioClientesArray() {
		this.clientes = new Cliente[100];
		this.indice = 0;
	}

	private int getIndice(String cpf) {
		String n;
		boolean achou = false;
		int i = 0;
		while ((!achou) && (i < this.indice)) {
			n = clientes[i].getCpf();
			if (n.equals(cpf)) {
				achou = true;
			} else {
				i = i + 1;
			}
		}
		return i;
	}

	@Override
	public boolean inserir(Cliente cliente) {
		if (this.existe(cliente.getCpf())) {
			return false;
		}

		if (clientes.length == indice) {
			Cliente[] aux = new Cliente[clientes.length * 2];
			for (int i = 0; i < indice; i++) {
				aux[i] = clientes[i];
			}
			this.clientes = aux;
		}
		clientes[indice] = cliente;
		indice = indice + 1;
		return true;
	}

	@Override
	public Cliente procurar(String cpf) {
		Cliente cliente = null;
		int i = this.getIndice(cpf);
		if (i < this.indice) {
			cliente = this.clientes[i];
		}
		return cliente;
	}

	@Override
	public boolean remover(String cpf) {
		boolean sucesso = false;
		int i = this.getIndice(cpf);
		if (i < this.indice) {
			this.indice = this.indice - 1;
			this.clientes[i] = this.clientes[this.indice];
			this.clientes[this.indice] = null;
		}
		return sucesso;
	}

	@Override
	public boolean atualizar(Cliente cliente) {
		boolean sucesso = false;
		int i = this.getIndice(cliente.getCpf());
		if (i < this.indice) {
			this.clientes[i] = cliente;
		}
		return sucesso;
	}

	@Override
	public boolean existe(String cpf) {
		int i = this.getIndice(cpf);
		return (i != this.indice);
	}

	@Override
	public IteratorCliente getIterator() {
		return new IteratorClienteArray(this.clientes);
	}

}

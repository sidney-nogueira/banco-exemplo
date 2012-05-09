package br.ufrpe.poo.banco.dados;

import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.iterator.IteratorContaAbstrataArray;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;

public class RepositorioContasArray implements RepositorioContas {

	private ContaAbstrata[] contas;
	private int indice;

	public RepositorioContasArray() {
		contas = new ContaAbstrata[100];
		indice = 0;
	}

	public void inserir(ContaAbstrata conta) {
		if(contas.length == indice) {
			ContaAbstrata[] aux = new ContaAbstrata[contas.length*2];
			for (int i=0; i<indice; i++) {
				aux[i] = contas[i];
			}
			this.contas = aux;
		}
		contas[indice] = conta;
		indice = indice + 1;
	}

	public ContaAbstrata procurar(String numero) throws ContaNaoEncontradaException {
		ContaAbstrata resposta = null;
		int i = this.getIndice(numero);
		if (i == this.indice) {
			throw new ContaNaoEncontradaException();
		} else {
			resposta = this.contas[i];
		}
		return resposta;
	}

	public void remover(String numero) throws ContaNaoEncontradaException {
		int i = this.getIndice(numero);
		if (i == this.indice) {
			throw new ContaNaoEncontradaException();
		} else {
			this.indice = this.indice - 1;
			this.contas[i] = this.contas[this.indice];
			this.contas[this.indice] = null;
		}
	}

	public void atualizar(ContaAbstrata conta) throws ContaNaoEncontradaException {
		int i = this.getIndice(conta.getNumero());
		if (i == this.indice) {
			throw new ContaNaoEncontradaException();
		} else {
			this.contas[i] = conta;
		}
	}

	public boolean existe(String numero) {
		int i = this.getIndice(numero);
		return (i != this.indice);
	}

	private int getIndice(String numero) {
		String n;
		boolean achou = false;
		int i = 0;
		while ((!achou) && (i < this.indice)) {
			n = contas[i].getNumero();
			if (n.equals(numero)) {
				achou = true;
			} else {
				i = i + 1;
			}
		}
		return i;
	}

	public IteratorContaAbstrata getIterator() {
		return new IteratorContaAbstrataArray(this.contas);
	}
}
package br.ufrpe.poo.banco.iterator;

import br.ufrpe.poo.banco.negocio.ContaAbstrata;

public class IteratorContaAbstrataArray implements IteratorContaAbstrata {

	private ContaAbstrata[] contas;
	private int indice;

	public IteratorContaAbstrataArray (ContaAbstrata[] contas) {
		this.contas = contas;
		indice = 0;
	}

	
	public boolean hasNext() {
		return indice < contas.length &&
			   contas[indice] != null;
	}

	public ContaAbstrata next() {
		ContaAbstrata resposta = contas[indice];
		this.indice = this.indice + 1;
		return resposta;
	}

}

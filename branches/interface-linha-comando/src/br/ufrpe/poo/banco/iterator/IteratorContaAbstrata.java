package br.ufrpe.poo.banco.iterator;

import br.ufrpe.poo.banco.negocio.ContaAbstrata;

public interface IteratorContaAbstrata {
	
	boolean hasNext();
	ContaAbstrata next();

}

package br.ufrpe.poo.banco.iterator;

import br.ufrpe.poo.banco.negocio.Cliente;

public interface IteratorCliente {

	boolean hasNext();

	Cliente next();
}

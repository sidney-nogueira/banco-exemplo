package br.ufrpe.poo.banco.dados;

import br.ufrpe.poo.banco.iterator.IteratorCliente;
import br.ufrpe.poo.banco.negocio.Cliente;

public interface IRepositorioClientes {

	boolean inserir(Cliente cliente) throws RepositorioException;

	Cliente procurar(String cpf);

	boolean remover(String cpf) throws RepositorioException;

	boolean atualizar(Cliente cliente) throws RepositorioException;

	boolean existe(String cpf);

	IteratorCliente getIterator();

}

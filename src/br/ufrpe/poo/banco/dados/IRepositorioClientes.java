package br.ufrpe.poo.banco.dados;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.iterator.IteratorCliente;
import br.ufrpe.poo.banco.negocio.Cliente;

/**
 * Interface que representa um repositorio de objetos do tipo
 * <code>Cliente</code>.
 * 
 * @author
 * 
 */
public interface IRepositorioClientes {

	/**
	 * Insere um cliente no repositorio.
	 * 
	 * @param cliente
	 *            Cliente a ser inserido.
	 * @return se cliente foi inserido no respositorio. Se o cliente ja existe
	 *         no repositorio nao sera inserido.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro no repositorio.
	 * 
	 */
	boolean inserir(Cliente cliente) throws RepositorioException;
	
	/**
	 * Procurar por um cliente no repositorio a partir do seu cpf.
	 * @param cpf
	 * 			Numero do cpf a ser retornado.
	 * @return cliente encontrado. Retorna <code>null</code> se o cliente nao foi encontrado.
	 * @throws RepositorioException 
	 */
	Cliente procurar(String cpf) throws RepositorioException;

	boolean remover(String cpf) throws RepositorioException;

	boolean atualizar(Cliente cliente) throws RepositorioException;

	boolean existe(String cpf) throws RepositorioException;

	IteratorCliente getIterator();

}

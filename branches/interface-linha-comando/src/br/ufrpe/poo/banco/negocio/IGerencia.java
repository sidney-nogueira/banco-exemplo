package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaAdicionadaException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;

/**
 * Interface que representa as operacoes da parte de gerenciamento do banco.
 * 
 * @author
 * 
 */
public interface IGerencia {

	/**
	 * Cadastra um cliente no banco.
	 * 
	 * @param cliente
	 *            Cliente a ser cadastrado.
	 * @throws ClienteJaCadastradoException
	 *             Lancada caso o cliente ja tenha sido cadastrado no banco.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de clientes.
	 */
	void cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException,
			RepositorioException;

	/**
	 * Cadastra uma conta no banco.
	 * 
	 * @param conta
	 *            Conta a ser cadastrada.
	 * @throws ContaJaCadastradaException
	 *             Lancada caso a conta ja tenha sido cadastrada no banco.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de contas.
	 */
	void cadastrarConta(ContaAbstrata conta) throws ContaJaCadastradaException,
			RepositorioException;

	/**
	 * Associa uma conta bancaria ao cliente.
	 * 
	 * @param numero
	 *            Numero da conta a ser associada.
	 * @param cliente
	 *            Cliente que recebe o numero da conta.
	 * @throws ContaJaAdicionadaException
	 *             Lancada caso a conta ja tenha sido associada ao cliente.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de clientes.
	 */
	void associarContaAoCliente(String numero, Cliente cliente)
			throws ContaJaAdicionadaException, RepositorioException;

	/**
	 * Le os dados cadastrais de um cliente no repositorio.
	 * 
	 * @param cpf
	 *            Numero do cpf do cliente a ser encontrado.
	 * @return se o cliente existe. Se o cliente nao foi cadastrado eh retornado
	 *         <code>null</code>.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de clientes.
	 */
	Cliente procurarCliente(String cpf) throws RepositorioException;

	/**
	 * Le os dados de uma conta no repositorio.
	 * 
	 * @param numero
	 *            Numero da conta a ser encontrada.
	 * @return se a conta existe. Se a conta nao foi cadastrada eh retornado
	 *         <code>null</code>.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de contas.
	 */
	ContaAbstrata procurarConta(String numero) throws RepositorioException;

	/**
	 * Atualiza os dados cadastrais de um cliente.
	 * 
	 * @param cliente
	 *            Cliente com os dados atualiados.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de clientes.
	 */
	void atualizarCadastroCliente(Cliente cliente) throws RepositorioException;

	/**
	 * Remove cliente do repositorio.
	 * 
	 * @param cpf
	 *            Numero do cpf do cliente a ser removido.
	 * @throws ClienteNaoCadastradoException
	 *             Lancada caso o cliente nao exista.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de clientes.
	 */
	void removerCadastroCliente(String cpf)
			throws ClienteNaoCadastradoException, RepositorioException;

	/**
	 * Remove conta do repositorio.
	 * 
	 * @param numero
	 *            Numero da conta a ser removida.
	 * @throws ContaNaoEncontradaException
	 *             Lancada caso a conta nao exista.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro na persistencia de contas.
	 */
	void removerCadastroConta(String numero)
			throws ContaNaoEncontradaException, RepositorioException;

}

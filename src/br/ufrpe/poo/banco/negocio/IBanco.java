package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.RepositorioException;

public interface IBanco {

	/**
	 * Cadatra uma conta no banco.
	 * 
	 * @param conta
	 *            conta a ser cadastrada.
	 * @throws RepositorioException
	 *             lancada caso ocorra erro com a persistencia.
	 * @throws ContaJaCadastradaException
	 *             lancada caso a conta ja existe no banco.
	 */
	void cadastrar(ContaAbstrata conta) throws RepositorioException,
			ContaJaCadastradaException;

	/**
	 * Credita valor em uma conta do banco.
	 * 
	 * @param numero
	 *            numero da conta.
	 * @param valor
	 *            valor a ser creditado.
	 * @throws RepositorioException
	 *             lancada caso ocorra erro com a persistencia.
	 * @throws ContaNaoEncontradaException
	 *             lancada caso a conta nao exista.
	 */
	void creditar(String numero, double valor) throws RepositorioException,
			ContaNaoEncontradaException;

	/**
	 * Debita valor de uma conta do banco.
	 * 
	 * @param numero
	 *            numero da conta.
	 * @param valor
	 *            valor a ser debitado.
	 * @throws RepositorioException
	 *             lancada caso ocorra erro com a persistencia.
	 * @throws ContaNaoEncontradaException
	 *             lancada caso a conta nao exista.
	 * @throws SaldoInsuficienteException
	 *             lancada caso nao existe saldo suficiente.
	 */
	void debitar(String numero, double valor) throws RepositorioException,
			ContaNaoEncontradaException, SaldoInsuficienteException;

	/**
	 * Le o saldo de uma conta.
	 * 
	 * @param numero
	 *            numero da conta.
	 * @return valor do saldo.
	 * @throws RepositorioException
	 *             lancada caso ocorra erro com a persistencia.
	 * @throws ContaNaoEncontradaException
	 *             lancada caso a conta nao exista.
	 */
	double getSaldo(String numero) throws RepositorioException,
			ContaNaoEncontradaException;

	/**
	 * Transfere valor de uma conta origem para uma conta destino.
	 * 
	 * @param de
	 *            numero da conta de origem.
	 * @param para
	 *            numero da conta de destino.
	 * @param valor
	 *            valor a ser transferido.
	 * @throws RepositorioException
	 *             lancada caso ocorra erro com a persistencia.
	 * @throws ContaNaoEncontradaException
	 *             lancada caso a conta nao exista.
	 * @throws SaldoInsuficienteException
	 *             lancada caso nao existe saldo suficiente.
	 */
	void transferir(String de, String para, double valor)
			throws RepositorioException, ContaNaoEncontradaException,
			SaldoInsuficienteException;

	/**
	 * Rende juros na conta de poupanca indicada.
	 * 
	 * @param numero
	 *            numero da poupanca.
	 * @throws RepositorioException
	 *             lancada caso ocorra erro com a persistencia.
	 * @throws ContaNaoEncontradaException
	 *             lancada caso a conta nao exista.
	 * @throws RenderJurosPoupancaException
	 *             lancada caso o numero nao pertence a uma conta poupanca.
	 */
	void renderJuros(String numero) throws RepositorioException,
			ContaNaoEncontradaException, RenderJurosPoupancaException;

	/**
	 * Rende bonus na conta especial indicada.
	 * 
	 * @param numero
	 *            numero da conta especial.
	 * @throws RepositorioException
	 *             lancada caso ocorra erro com a persistencia.
	 * @throws ContaNaoEncontradaException
	 *             lancada caso a conta nao exista.
	 * @throws RenderBonusContaEspecialException
	 *             lancada caso o numero nao pertence a uma conta especial.
	 */
	void renderBonus(String numero) throws RepositorioException,
			ContaNaoEncontradaException, RenderBonusContaEspecialException;
}

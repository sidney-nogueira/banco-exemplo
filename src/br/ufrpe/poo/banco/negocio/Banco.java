package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioClientesArquivoBin;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoBin;
import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.ClienteJaPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ContaJaAssociadaException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.exceptions.InicializacaoSistemaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;
import br.ufrpe.poo.banco.exceptions.ValorInvalidoException;

/**
 * Implementacao do sistema bancario que faz a comunicacao com a persistencia e
 * a gui.
 * 
 * @author
 * 
 */
public class Banco implements IGerencia, ICliente {

	/**
	 * Instancia do repositorio de clientes.
	 */
	private IRepositorioClientes clientes;

	/**
	 * Instacia do repositorio de contas
	 */
	private IRepositorioContas contas;

	/**
	 * Instancia do comunicador.
	 */
	private static Banco instance;

	public Banco(IRepositorioClientes repositorioClientesArquivoBin,
			IRepositorioContas repositorioContasArquivoBin) {
		this.clientes = repositorioClientesArquivoBin;
		this.contas = repositorioContasArquivoBin;
	}

	/**
	 * Retorna a instancia unica do banco.
	 * 
	 * @return se o banco nao foi instanciado. Se o banco ja foi instanciado eh
	 *         retornado <code>null</code>.
	 * @throws RepositorioException
	 *             Lancada quando ocorre erro no repositorio.
	 * @throws InicializacaoSistemaException
	 *             Lancada quando ocorre erro no repositorio.
	 */
	public static Banco getInstance() throws RepositorioException,
			InicializacaoSistemaException {

		if (Banco.instance == null) {
			try {
				Banco.instance = new Banco(new RepositorioClientesArquivoBin(),
						new RepositorioContasArquivoBin());
			} catch (RepositorioException e) {
				throw new InicializacaoSistemaException();
			}
		}
		return Banco.instance;
	}

	@Override
	public void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException {
		if (!this.clientes.inserir(cliente))
			throw new ClienteJaCadastradoException();
	}

	@Override
	public Cliente procurarCliente(String cpf) {
		return this.clientes.procurar(cpf);
	}

	@Override
	public void cadastrarConta(ContaAbstrata conta)
			throws RepositorioException, ContaJaCadastradaException {
		if (!this.contas.inserir(conta))
			throw new ContaJaCadastradaException();

	}

	@Override
	public ContaAbstrata procurarConta(String numero) {
		return this.contas.procurar(numero);
	}

	@Override
	public void associarConta(String cpf, String numeroConta)
			throws ClienteJaPossuiContaException, ContaJaAssociadaException,
			ClienteNaoCadastradoException {
		Cliente cliente = this.procurarCliente(cpf);
		if (cliente != null) {
			ContaAbstrata conta = procurarConta(numeroConta);
			if (conta == null)
				cliente.adicionarConta(numeroConta);
			else
				throw new ContaJaAssociadaException();
		} else
			throw new ClienteNaoCadastradoException();
	}

	@Override
	public void removerCliente(String cpf) throws RepositorioException,
			ClienteNaoCadastradoException {
		if (!this.clientes.remover(cpf))
			throw new ClienteNaoCadastradoException();
	}

	@Override
	public void removerConta(String numeroConta) throws RepositorioException,
			ContaNaoEncontradaException {
		if (!this.contas.remover(numeroConta))
			throw new ContaNaoEncontradaException();
	}

	@Override
	public ContaAbstrata procurarEmContasDoCliente(Cliente cliente,
			String numero) throws ClienteNaoPossuiContaException {
		if (cliente.procurarConta(numero) == -1)
			throw new ClienteNaoPossuiContaException();
		return this.contas.procurar(numero);
	}

	@Override
	public void creditar(ContaAbstrata conta, double valor)
			throws RepositorioException, ValorInvalidoException {
		if (valor < 0)
			throw new ValorInvalidoException();
		conta.creditar(valor);
		this.contas.atualizar(conta);
	}

	@Override
	public void debitar(ContaAbstrata conta, double valor)
			throws RepositorioException, SaldoInsuficienteException,
			ValorInvalidoException {
		if (valor < 0)
			throw new ValorInvalidoException();
		conta.debitar(valor);
		this.contas.atualizar(conta);

	}

	@Override
	public void transferir(ContaAbstrata conta1, ContaAbstrata conta2,
			double valor) throws SaldoInsuficienteException,
			RepositorioException {
		conta1.debitar(valor);
		conta2.creditar(valor);
		this.contas.atualizar(conta1);
		this.contas.atualizar(conta2);
	}

}

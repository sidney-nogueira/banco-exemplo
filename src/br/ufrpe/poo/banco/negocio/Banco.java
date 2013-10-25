package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioClientesArquivoBin;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoBin;
import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.InicializacaoSistemaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;

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
	 * Instancia da interface do gerenciador do banco.
	 */
	private static IGerencia gerenciaInstance;

	/**
	 * Instancia da interface do cliente do banco.
	 */
	private static ICliente clienteInstance;

	/**
	 * Instancia do comunicador.
	 */
	private static Banco instance;

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

		if (Banco.clienteInstance == null && Banco.gerenciaInstance == null) {
			try {
				Banco.clienteInstance = new Banco(
						new RepositorioClientesArquivoBin());
				Banco.gerenciaInstance = new Banco(
						new RepositorioContasArquivoBin());
			} catch (RepositorioException e) {
				throw new InicializacaoSistemaException();
			}
		}
		return Banco.instance;
	}

	public Banco(IRepositorioClientes repClientes) {

		this.clientes = repClientes;
	}

	public Banco(IRepositorioContas repContas) {

		this.contas = repContas;
	}

	@Override
	public void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException {
		if (!this.clientes.inserir(cliente))
			throw new ClienteJaCadastradoException();
	}

	@Override
	public Cliente procurarCliente(String cpf) {
		return this.procurarCliente(cpf);
	}

}

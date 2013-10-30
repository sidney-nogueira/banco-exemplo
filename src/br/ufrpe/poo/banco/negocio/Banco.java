package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioClientesArquivoBin;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoBin;
import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
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
				Banco.instance = new Banco(new RepositorioClientesArquivoBin(), new RepositorioContasArquivoBin());
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
	public void cadastrarConta(ContaAbstrata conta) throws RepositorioException, ContaJaCadastradaException {
		if(!this.contas.inserir(conta))
			throw new ContaJaCadastradaException();
		
	}

	@Override
	public ContaAbstrata procurarConta(String numero) {
		return this.contas.procurar(numero);
	}

}

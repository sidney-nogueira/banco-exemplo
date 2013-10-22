package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.IRepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioClientesArquivoBin;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoBin;
import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaAdicionadaException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.exceptions.InicializacaoSistemaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;

public class Banco implements IGerencia, ICliente {

	private IRepositorioClientes clientes;

	private IRepositorioContas contas;

	private static IGerencia gerenciaInstance;

	private static ICliente clienteInstance;

	private static Banco instance;

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
	public void removerContaCliente(String cpf, String numero)
			throws ContaNaoEncontradaException, ClienteNaoCadastradoException,
			RepositorioException {
		Cliente cliente = this.clientes.procurar(cpf);
		if (cliente != null)
			cliente.removerConta(numero);
		else
			throw new ClienteNaoCadastradoException();

	}

	@Override
	public void adicionarContaCliente(String cpf, String numero)
			throws ContaJaAdicionadaException, ClienteNaoCadastradoException,
			RepositorioException {
		Cliente cliente = this.clientes.procurar(cpf);
		if (cliente != null)
			cliente.adicionarConta(numero);
		else
			throw new ClienteNaoCadastradoException();

	}

	@Override
	public void cadastrarConta(ContaAbstrata conta)
			throws RepositorioException, ContaJaCadastradaException {
		String numero = conta.getNumero();
		if (contas.existe(numero)) {
			throw new ContaJaCadastradaException();
		} else {
			contas.inserir(conta);
		}
	}

	@Override
	public void removerConta(ContaAbstrata conta)
			throws ContaNaoEncontradaException, RepositorioException {
		String numero = conta.getNumero();
		if (!this.contas.existe(numero))
			throw new ContaNaoEncontradaException();
		else
			this.contas.remover(numero);
	}

	@Override
	public void creditar(String numero, double valor)
			throws RepositorioException, ContaNaoEncontradaException {
		ContaAbstrata c = contas.procurar(numero);
		if (c == null) {
			throw new ContaNaoEncontradaException();
		}
		c.creditar(valor);
		contas.atualizar(c);

	}

	@Override
	public void debitar(String numero, double valor)
			throws RepositorioException, ContaNaoEncontradaException,
			SaldoInsuficienteException {
		ContaAbstrata c = contas.procurar(numero);
		if (c == null) {
			throw new ContaNaoEncontradaException();
		}
		c.debitar(valor);
		contas.atualizar(c);

	}

	@Override
	public double getSaldo(String numero) throws RepositorioException,
			ContaNaoEncontradaException {
		ContaAbstrata c = contas.procurar(numero);
		if (c == null) {
			throw new ContaNaoEncontradaException();
		}
		return c.getSaldo();
	}

	@Override
	public void transferir(String de, String para, double valor)
			throws RepositorioException, ContaNaoEncontradaException,
			SaldoInsuficienteException {
		ContaAbstrata origem = contas.procurar(de);
		ContaAbstrata destino = contas.procurar(para);
		if (origem == null || destino == null) {
			throw new ContaNaoEncontradaException();
		}
		origem.debitar(valor);
		destino.creditar(valor);
		contas.atualizar(origem);
		contas.atualizar(destino);

	}

	// @Override
	// public void renderJuros(String numero) throws RepositorioException,
	// ContaNaoEncontradaException, RenderJurosPoupancaException {
	// ContaAbstrata c = contas.procurar(numero);
	// if (c == null) {
	// throw new ContaNaoEncontradaException();
	// }
	// if (c instanceof Poupanca) {
	// ((Poupanca) c).renderJuros(TAXA_RENDIMENTO_POUPANCA);
	// contas.atualizar(c);
	// } else {
	// throw new RenderJurosPoupancaException();
	// }
	// }
	//
	// @Override
	// public void renderBonus(String numero) throws RepositorioException,
	// ContaNaoEncontradaException, RenderBonusContaEspecialException {
	// ContaAbstrata c = contas.procurar(numero);
	// if (c == null) {
	// throw new ContaNaoEncontradaException();
	// }
	// if (c instanceof ContaEspecial) {
	// ((ContaEspecial) c).renderBonus();
	// contas.atualizar(c);
	// } else {
	// throw new RenderBonusContaEspecialException();
	// }
	// }
}

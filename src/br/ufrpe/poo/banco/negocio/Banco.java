package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.RepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoBin;
import br.ufrpe.poo.banco.dados.RepositorioException;

public class Banco implements IBanco {

	private RepositorioContas contas;

	private final double TAXA_RENDIMENTO_POUPANCA = 0.008;

	private static IBanco instance;

	private Banco(RepositorioContas rep) {
		this.contas = rep;
	}
	
	public static IBanco getInstance() throws InicializacaoSistemaException {
		if (Banco.instance == null) {
			try {
				Banco.instance = new Banco(new RepositorioContasArquivoBin());
			} catch (RepositorioException e) {
				e.printStackTrace();
				throw new InicializacaoSistemaException();
			}
		}
		return Banco.instance;
	}

	public void cadastrar(ContaAbstrata conta) throws RepositorioException,
			ContaJaCadastradaException {
		String numero = conta.getNumero();
		if (contas.existe(numero)) {
			throw new ContaJaCadastradaException();
		} else {
			contas.inserir(conta);
		}
	}

	public void creditar(String numero, double valor)
			throws RepositorioException, ContaNaoEncontradaException {
		ContaAbstrata c = contas.procurar(numero);
		if (c == null) {
			throw new ContaNaoEncontradaException();
		}
		c.creditar(valor);
		contas.atualizar(c);
	}

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

	public double getSaldo(String numero) throws RepositorioException,
			ContaNaoEncontradaException {
		ContaAbstrata c = contas.procurar(numero);
		if (c == null) {
			throw new ContaNaoEncontradaException();
		}
		return c.getSaldo();
	}

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

	public void renderJuros(String numero) throws RepositorioException,
			ContaNaoEncontradaException, RenderJurosPoupancaException {
		ContaAbstrata c = contas.procurar(numero);
		if (c == null) {
			throw new ContaNaoEncontradaException();
		}
		if (c instanceof Poupanca) {
			((Poupanca) c).renderJuros(TAXA_RENDIMENTO_POUPANCA);
			contas.atualizar(c);
		} else {
			throw new RenderJurosPoupancaException();
		}
	}

	public void renderBonus(String numero) throws RepositorioException,
			ContaNaoEncontradaException, RenderBonusContaEspecialException {
		ContaAbstrata c = contas.procurar(numero);
		if (c == null) {
			throw new ContaNaoEncontradaException();
		}
		if (c instanceof ContaEspecial) {
			((ContaEspecial) c).renderBonus();
			contas.atualizar(c);
		} else {
			throw new RenderBonusContaEspecialException();
		}
	}

}
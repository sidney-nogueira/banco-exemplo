package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.RepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoTxt;
import br.ufrpe.poo.banco.dados.RepositorioException;


/**
 * Implementa�‹o para o sistema do banco.
 */
public class Banco implements IBanco {
	
	public Banco (){
		
	}
	/**
	 * Repositorio de contas.
	 */
	private RepositorioContas contas;

	/**
	 * Taxa dos juros da poupanca.
	 */
	private final double TAXA_RENDIMENTO_POUPANCA = 0.008;

	/**
	 * Instancia unica para o sistema do banco.
	 */
	private static IBanco instance;

	/**
	 * Constroi um banco a partir do repositorio fornecido.
	 * @param rep repositorio das contas.
	 */
	public Banco(RepositorioContas rep) {
		this.contas = rep;
	}
	
	/**
	 * Retorna referencia para a instancia do banco.
	 * @return instancia do banco.
	 * @throws InicializacaoSistemaException lancada caso nao seja possivel inicializar o banco.
	 */
	public static IBanco getInstance() throws InicializacaoSistemaException {
		if (Banco.instance == null) {
			try {
				Banco.instance = new Banco(new RepositorioContasArquivoTxt(new java.io.File("a.txt")));
			} catch (RepositorioException e) {
				e.printStackTrace();
				throw new InicializacaoSistemaException();
			}
		}
		return Banco.instance;
	}

	
	@Override
	public void cadastrar(ContaAbstrata conta) throws RepositorioException,
			ContaJaCadastradaException {
		String numero = conta.getNumero();
		if (contas.existe(numero)) {
			throw new ContaJaCadastradaException();
		} else {
			contas.inserir(conta);
		}
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

	@Override
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

	@Override
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
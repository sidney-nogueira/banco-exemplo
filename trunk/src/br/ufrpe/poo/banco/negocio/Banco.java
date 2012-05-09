package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.dados.RepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioException;

public class Banco {

	private RepositorioContas contas;
	
	private final double TAXA = 0.008;

	public Banco(RepositorioContas rep) {
		this.contas = rep;
	}

	public void cadastrar(ContaAbstrata conta) throws RepositorioException, ContaJaCadastradaException {
		String numero = conta.getNumero();
		if (contas.existe(numero)) {
			throw new ContaJaCadastradaException();
		} else {
			contas.inserir(conta);
		}
	}

	public void creditar(String numero, double valor) throws RepositorioException, ContaNaoEncontradaException{
		ContaAbstrata c = contas.procurar(numero);
		c.creditar(valor);
		contas.atualizar(c);
	}

	public void debitar(String numero, double valor) throws RepositorioException, ContaNaoEncontradaException, SaldoInsuficienteException {
		ContaAbstrata c = contas.procurar(numero);
		c.debitar(valor);
		contas.atualizar(c);
	}

	public double getSaldo(String numero) throws RepositorioException, ContaNaoEncontradaException{
		ContaAbstrata c = contas.procurar(numero);
		return c.getSaldo();
	}

	public void transferir(String de, String para, double valor) throws RepositorioException, ContaNaoEncontradaException, SaldoInsuficienteException {
		ContaAbstrata origem = contas.procurar(de);
		ContaAbstrata destino = contas.procurar(para);
		origem.debitar(valor);
		destino.creditar(valor);
		contas.atualizar(origem);
		contas.atualizar(destino);
	}
	
	public void renderJuros(String numero) throws RepositorioException, ContaNaoEncontradaException, RenderJurosPoupancaException{
		ContaAbstrata c = contas.procurar(numero);
		if (c instanceof Poupanca) {
			((Poupanca) c).renderJuros(TAXA);
			contas.atualizar(c);
		} 
		else {
			throw new RenderJurosPoupancaException();
		}
	}

	public void renderBonus(String numero) throws RepositorioException, ContaNaoEncontradaException, RenderBonusContaEspecialException{
		ContaAbstrata c = contas.procurar(numero);
		if (c instanceof ContaEspecial) {
			((ContaEspecial) c).renderBonus();
			contas.atualizar(c);
		} 
		else {
			throw new RenderBonusContaEspecialException();
		}
	}

}
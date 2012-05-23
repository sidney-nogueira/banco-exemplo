package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.RepositorioException;

public interface IBanco {
	
	public void cadastrar(ContaAbstrata conta) throws RepositorioException, ContaJaCadastradaException;

	public void creditar(String numero, double valor) throws RepositorioException, ContaNaoEncontradaException;

	public void debitar(String numero, double valor) throws RepositorioException, ContaNaoEncontradaException, SaldoInsuficienteException;

	public double getSaldo(String numero) throws RepositorioException, ContaNaoEncontradaException;

	public void transferir(String de, String para, double valor) throws RepositorioException, ContaNaoEncontradaException, SaldoInsuficienteException;
	
	public void renderJuros(String numero) throws RepositorioException, ContaNaoEncontradaException, RenderJurosPoupancaException;

	public void renderBonus(String numero) throws RepositorioException, ContaNaoEncontradaException, RenderBonusContaEspecialException;
}

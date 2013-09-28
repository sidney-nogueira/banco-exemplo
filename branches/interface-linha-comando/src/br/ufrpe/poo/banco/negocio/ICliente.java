package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;

public interface ICliente {

	void creditar(String numero, double valor) throws RepositorioException,
			ContaNaoEncontradaException;

	void debitar(String numero, double valor) throws RepositorioException,
			ContaNaoEncontradaException, SaldoInsuficienteException;

	double getSaldo(String numero) throws RepositorioException,
			ContaNaoEncontradaException;

	void transferir(String de, String para, double valor)
			throws RepositorioException, ContaNaoEncontradaException,
			SaldoInsuficienteException;
}

package br.ufrpe.poo.banco.dados;

import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;

public interface RepositorioContas {
	  void inserir(ContaAbstrata conta) throws RepositorioException;
	  ContaAbstrata procurar(String numero) throws RepositorioException, ContaNaoEncontradaException;
	  void remover(String numero) throws RepositorioException, ContaNaoEncontradaException;
	  void atualizar(ContaAbstrata conta) throws RepositorioException, ContaNaoEncontradaException;
	  boolean existe(String numero) throws RepositorioException;
	  IteratorContaAbstrata getIterator() throws RepositorioException;
}

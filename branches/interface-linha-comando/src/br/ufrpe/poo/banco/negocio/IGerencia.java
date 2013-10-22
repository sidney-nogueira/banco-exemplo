package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaAdicionadaException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;

public interface IGerencia {
	
	void adicionarContaCliente(String cpf, String numero)
			throws ContaJaAdicionadaException, ClienteNaoCadastradoException,
			RepositorioException;

	void removerContaCliente(String cpf, String numero)
			throws ContaNaoEncontradaException, ClienteNaoCadastradoException, RepositorioException;
	
	void cadastrarConta(ContaAbstrata conta) throws RepositorioException,
	ContaJaCadastradaException;
	
	void removerConta(ContaAbstrata conta) throws ContaNaoEncontradaException,
			RepositorioException;

}

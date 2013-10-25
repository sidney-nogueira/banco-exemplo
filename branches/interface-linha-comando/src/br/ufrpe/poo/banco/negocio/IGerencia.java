package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;


public interface IGerencia {
	
	void cadastrarCliente(Cliente cliente) throws RepositorioException, ClienteJaCadastradoException;
	
	Cliente procurarCliente(String cpf);

//	void cadastrarCliente(Cliente cliente) throws ClienteJaCadastradoException,
//			RepositorioException;
//
//
//	void cadastrarConta(ContaAbstrata conta) throws ContaJaCadastradaException,
//			RepositorioException;
//
//	void associarContaAoCliente(String numero, Cliente cliente)
//			throws ContaJaAdicionadaException, RepositorioException;
//
//
//	Cliente procurarCliente(String cpf) throws RepositorioException;
//
//	ContaAbstrata procurarConta(String numero) throws RepositorioException;
//
//
//	void atualizarCadastroCliente(Cliente cliente) throws RepositorioException;
//
//
//	void atualizarCadastroConta(ContaAbstrata conta)
//			throws RepositorioException;
//
//
//	void removerCadastroCliente(String cpf)
//			throws ClienteNaoCadastradoException, RepositorioException;
//
//
//	void removerCadastroConta(String numero)
//			throws ContaNaoEncontradaException, RepositorioException;

}

package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.ClienteJaPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaAssociadaException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;

public interface IGerencia {

	void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException;

	Cliente procurarCliente(String cpf);

	void cadastrarConta(ContaAbstrata conta) throws RepositorioException,
			ContaJaCadastradaException;

	ContaAbstrata procurarConta(String numero);

	void associarConta(String cpf, String numeroConta)
			throws ClienteJaPossuiContaException, ContaJaAssociadaException,
			ClienteNaoCadastradoException;

}

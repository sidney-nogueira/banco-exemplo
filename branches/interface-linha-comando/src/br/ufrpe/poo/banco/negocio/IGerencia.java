package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;

public interface IGerencia {

	void cadastrarCliente(Cliente cliente) throws RepositorioException,
			ClienteJaCadastradoException;

	Cliente procurarCliente(String cpf);

	void cadastrarConta(ContaAbstrata conta) throws RepositorioException,
			ContaJaCadastradaException;

	ContaAbstrata procurarConta(String numero);

}

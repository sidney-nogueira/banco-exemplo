package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.exceptions.ClienteNaoPossuiContaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;
import br.ufrpe.poo.banco.exceptions.ValorInvalidoException;

public interface ICliente {

	Cliente procurarCliente(String cpf);
	
	ContaAbstrata procurarConta(String numero);

	ContaAbstrata procurarEmContasDoCliente(Cliente cliente, String numero)
			throws ClienteNaoPossuiContaException;

	void creditar(ContaAbstrata conta, double valor)
			throws RepositorioException, ValorInvalidoException;

	void debitar(ContaAbstrata conta, double valor)
			throws RepositorioException, SaldoInsuficienteException,
			ValorInvalidoException;

	void transferir(ContaAbstrata conta1, ContaAbstrata conta2, double valor)
			throws SaldoInsuficienteException, RepositorioException;

}

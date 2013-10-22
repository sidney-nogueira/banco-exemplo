package br.ufrpe.poo.banco.negocio;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.poo.banco.exceptions.ContaJaAdicionadaException;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;

/**
 * Classe do tipo Cliente.
 * 
 * @author
 * 
 */
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Nome do cliente.
	 */
	protected String nome;

	/**
	 * Numero do cpf do cliente.
	 */
	protected String cpf;

	/**
	 * Array de numero de contas a qual o cliente possui.
	 */
	protected ArrayList<String> contas;

	public Cliente(String nome, String cpf, ArrayList<String> contas) {
		this.nome = nome;
		this.cpf = cpf;
		this.contas = contas;
	}

	/**
	 * Adiciona um numero de uma conta ao array de contas do cliente.
	 * 
	 * @param numero
	 *            Numero a ser adicionado.
	 * @throws ContaJaAdicionadaException
	 *             Lancada caso o cliente ja possua uma conta com o numero
	 *             passado.
	 */
	public void adicionarConta(String numero) throws ContaJaAdicionadaException {
		if (this.contas.contains(numero))
			throw new ContaJaAdicionadaException();
		this.contas.add(numero);
	}

	/**
	 * Remove um numero de conta do array de contas do cliente.
	 * 
	 * @param numero
	 *            Numero da conta ser removida.
	 * @throws ContaNaoEncontradaException
	 *             Lancada caso o numero passado para remocao nao existe.
	 */
	public void removerConta(String numero) throws ContaNaoEncontradaException {
		int index = this.contas.indexOf(numero);
		if (index == -1)
			throw new ContaNaoEncontradaException();
		this.contas.remove(index);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<String> getContas() {
		return contas;
	}

	public void setContas(ArrayList<String> contas) {
		this.contas = contas;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Cliente) {
			Cliente c = (Cliente) o;
			if (c.getCpf().equals(this.cpf))
				return true;
		}
		return false;
	}

}

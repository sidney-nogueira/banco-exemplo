package br.ufrpe.poo.banco.negocio;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.poo.banco.exceptions.ClienteJaPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoPossuiContaException;

/**
 * Classe que representa o tipo Cliente.
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

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.contas = new ArrayList<String>();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<String> getContas() {
		return this.contas;
	}

	public void adicionarConta(String numeroConta)
			throws ClienteJaPossuiContaException {
		if (procurarConta(numeroConta) != -1)
			throw new ClienteJaPossuiContaException();
		this.contas.add(numeroConta);

	}

	public void removerConta(String numeroConta)
			throws ClienteNaoPossuiContaException {
		int index = procurarConta(numeroConta);
		if (index == -1)
			throw new ClienteNaoPossuiContaException();
		this.contas.remove(index);
	}

	public void removerTodasAsContas() {
		this.contas = null;
	}

	public int procurarConta(String numeroConta) {
		return this.contas.indexOf(numeroConta);
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

	@Override
	public String toString() {

		return ("Nome: " + this.nome + "\nCPF: " + this.cpf + "\nContas: " + this.contas);
	}

}

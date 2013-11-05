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
	protected ArrayList<String> contas = new ArrayList<String>();

	public Cliente(String nome, String cpf, ArrayList<String> contas) {
		this.nome = nome;
		this.cpf = cpf;
		this.contas = contas;
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

	private int procurarConta(String numeroConta) {
		return this.contas.indexOf(numeroConta);
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

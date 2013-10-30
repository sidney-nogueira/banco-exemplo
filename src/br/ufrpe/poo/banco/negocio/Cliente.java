package br.ufrpe.poo.banco.negocio;

import java.io.Serializable;
import java.util.ArrayList;

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

	/**
	 * Aloca um numero de uma conta ao array de contas.
	 * 
	 * @param numero
	 *            Numero da conta a ser alocado.
	 * @return se conta foi alocada. Se ja existe o numero eh retornado
	 *         <code>false</code>.
	 */
	public boolean adicionarConta(String numero) {
		if (this.existeConta(numero) != -1)
			return false;
		return this.contas.add(numero);
	}

	/**
	 * Remove um numero de conta do array de contas.
	 * 
	 * @param numero
	 *            Numero da conta a ser removido.
	 * @return se conta foi removida. Se nao existe o numero eh retornado
	 *         <code>false</code>.
	 */
	public boolean removerConta(String numero) {
		int index = this.existeConta(numero);
		if (index == -1)
			return false;
		this.contas.remove(index);
		return true;
	}

	/**
	 * Indica a existencia de um numero de conta no array.
	 * 
	 * @param numero
	 *            Numero de conta a ser procurado.
	 * @return se existe numero da conta. Se nao existe eh retornado -1.
	 */
	public int existeConta(String numero) {
		return this.contas.indexOf(numero);
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

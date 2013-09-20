package br.ufrpe.poo.banco.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable{
	
	private String nome;
	private String cpf;
	private List<Conta> contas;
	
	public Cliente(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
		this.contas = new ArrayList<Conta>();
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

	public ArrayList<Conta> getContas() {
		return (ArrayList<Conta>) contas;
	}
	
	public void adicionarConta(Conta conta) throws ContaJaAdicionadaException{
		if(existeConta(conta.getNumero()) == -1)
			throw new ContaJaAdicionadaException();
		this.contas.add(conta);
	}
	
	public void atualizarConta(Conta conta) throws ContaDeClienteInexistente{
		int indice = this.existeConta(conta.getNumero());
		if(indice == -1)
			throw new ContaDeClienteInexistente();
		this.contas.set(indice, conta);
	}
	
	public void removerConta(String numero) throws ContaDeClienteInexistente{
		int indice = this.existeConta(numero);
		if(indice == -1)
			throw new ContaDeClienteInexistente();
		this.contas.remove(indice);
	}
	
	private int existeConta(String numero){
		Conta conta = new Conta(numero, 0);
		return (this.contas.indexOf(conta));
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Cliente){
			Cliente c = (Cliente) o;
			if(c.getCpf().equals(this.cpf))
				return true;
		}
		return false;
	}
	
}

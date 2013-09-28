package br.ufrpe.poo.banco.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.poo.banco.exceptions.ContaJaAdicionadaException;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;

public class Cliente implements Serializable{
	
	private String nome;
	private String cpf;
	private List<String> contas;
	
	public Cliente(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
		this.contas = new ArrayList<String>();
	}
	
	public void adicionarConta(String numero) throws ContaJaAdicionadaException{
		if(this.contas.contains(numero))
			throw new ContaJaAdicionadaException();
		this.contas.add(numero);
	}
	
	public void removerConta(String numero) throws ContaNaoEncontradaException{
		int index = this.contas.indexOf(numero);
		if(index == -1)
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

package br.ufrpe.poo.banco.dados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;

public class RepositorioContasArquivoBin implements RepositorioContas {

	private RepositorioContasArray contas;
	
	private final String ARQUIVO = "/Sergio/Banco.dat";
	//private final String ARQUIVO = "c:\\temp\Banco.dat";
				
	public RepositorioContasArquivoBin() throws RepositorioException {
		try {
			contas = new RepositorioContasArray();
			File fBanco = new File(this.ARQUIVO);
			fBanco.createNewFile();
			if (fBanco.length()!=0)
				this.lerArquivo();
		} catch (IOException e) {
			throw new RepositorioException(e);
		}
	}

	private void lerArquivo() throws RepositorioException {
		FileInputStream fisBanco = null;
		ObjectInputStream oisBanco = null;
		try {
			fisBanco = new FileInputStream(this.ARQUIVO);
			oisBanco = new ObjectInputStream(fisBanco);
			while(true) {
				try {
					ContaAbstrata conta = (ContaAbstrata) oisBanco.readObject();
					this.contas.inserir(conta);
				} catch (EOFException e) {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			throw new RepositorioException(e);
		} catch (IOException e) {
			throw new RepositorioException(e);
		} catch (ClassNotFoundException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				oisBanco.close();
				fisBanco.close();
			} catch (IOException e) {
				throw new RepositorioException(e);
			}
		}
	}
	
	
	public void gravarArquivo() throws RepositorioException {
		FileOutputStream fosBanco = null;
		ObjectOutputStream oosBanco = null; 
		try {
			fosBanco = new FileOutputStream(this.ARQUIVO,false); // vai apagar o arquivo
			oosBanco = new ObjectOutputStream(fosBanco); 
			IteratorContaAbstrata it = contas.getIterator();
			while(it.hasNext()) {
				ContaAbstrata c = it.next();
				oosBanco.writeObject(c);
			}
		} catch (FileNotFoundException e) {
			throw new RepositorioException(e);
		} catch (IOException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				fosBanco.close();
				oosBanco.close();
			} catch (IOException e) {
				throw new RepositorioException(e);
			}
		}
	}
	/*
	public void inserir(ContaAbstrata conta) throws IOException{
		contas[indice] = conta;
		FileOutputStream fosBanco = new FileOutputStream("Banco.dat",true); // abre para inserir no final (sem apagar conteœdo)
		ObjectOutputStream oosBanco = new ObjectOutputStream(fosBanco);		
		oosBanco.writeObject(contas[indice]);
		indice = indice + 1;
		oosBanco.close();
		fosBanco.close();
	}
	*/

	public void inserir(ContaAbstrata conta) throws RepositorioException{
		contas.inserir(conta);
		this.gravarArquivo();
	}

	public ContaAbstrata procurar(String numero) throws ContaNaoEncontradaException {
		return contas.procurar(numero);
	}

	public void remover(String numero) throws RepositorioException, ContaNaoEncontradaException{
		contas.remover(numero);
		this.gravarArquivo();
	}

	public void atualizar(ContaAbstrata conta) throws RepositorioException, ContaNaoEncontradaException{
		contas.atualizar(conta);
		this.gravarArquivo();
	}

	public boolean existe(String numero) {
		return contas.existe(numero);
	}

	public IteratorContaAbstrata getIterator() throws RepositorioException {
		return contas.getIterator();
	}
}
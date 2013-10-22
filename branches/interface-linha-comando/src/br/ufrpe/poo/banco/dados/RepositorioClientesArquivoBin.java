package br.ufrpe.poo.banco.dados;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.iterator.IteratorCliente;
import br.ufrpe.poo.banco.negocio.Cliente;

public class RepositorioClientesArquivoBin implements IRepositorioClientes {
	
	private RepositorioClientesArray clientes;

	private final String ARQUIVO = "clientes.dat";

	private File arquivoClientes;

	public RepositorioClientesArquivoBin() throws RepositorioException {
		try {
			this.clientes = new RepositorioClientesArray();
			arquivoClientes = new File(this.ARQUIVO);
			arquivoClientes.createNewFile();
			if (arquivoClientes.length() != 0)
				this.lerArquivo();
		} catch (IOException e) {
			throw new RepositorioException(e);
		}
	}

	private void lerArquivo() throws RepositorioException {
		FileInputStream fisBanco = null;
		ObjectInputStream oisBanco = null;
		try {
			fisBanco = new FileInputStream(this.arquivoClientes);
			oisBanco = new ObjectInputStream(fisBanco);
			while (true) {
				try {
					Cliente cliente = (Cliente) oisBanco.readObject();
					this.clientes.inserir(cliente);
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
			fosBanco = new FileOutputStream(this.arquivoClientes, false);
			oosBanco = new ObjectOutputStream(fosBanco);
			IteratorCliente it = this.clientes.getIterator();
			while (it.hasNext()) {
				Cliente cliente = it.next();
				oosBanco.writeObject(cliente);
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
	
	@Override
	public boolean inserir(Cliente cliente) throws RepositorioException {
		boolean sucesso = this.clientes.inserir(cliente);
		if(sucesso){
			this.gravarArquivo();			
		}
		return sucesso;
	}

	@Override
	public Cliente procurar(String cpf) throws RepositorioException {
		return this.clientes.procurar(cpf);
	}

	@Override
	public boolean remover(String cpf) throws RepositorioException {
		boolean sucesso = this.clientes.remover(cpf);
		if(sucesso){
			this.gravarArquivo();			
		}
		return sucesso;
	}

	@Override
	public boolean atualizar(Cliente cliente) throws RepositorioException {
		boolean sucesso = this.clientes.atualizar(cliente);
		if(sucesso){
			this.gravarArquivo();			
		}
		return sucesso;
	}

	@Override
	public boolean existe(String cpf) throws RepositorioException {
		return this.clientes.existe(cpf);
	}

	@Override
	public IteratorCliente getIterator() {
		return this.clientes.getIterator();
	}

}

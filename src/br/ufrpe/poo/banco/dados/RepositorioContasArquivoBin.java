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

/**
 * Implementacao de repositorio de contas que persiste os objetos das contas em
 * arquivo.
 */
public class RepositorioContasArquivoBin implements RepositorioContas {

	/** Contas do arquivo sao guardadas em memoria num repositorio de contas. */
	private RepositorioContasArray contas;

	/** Caminho para arquivo que guarda as informacoes das contas. */
	private final String ARQUIVO = "contas.dat";
	
	/** Arquivo que armazena as contas. */
	private File arquivoContas;

	/**
	 * Constroi um repositorio a partir de contas armazenadas em arquivo.
	 * 
	 * @throws RepositorioException
	 */
	public RepositorioContasArquivoBin() throws RepositorioException {
		try {
			contas = new RepositorioContasArray();
			File arquivoContas = new File(this.ARQUIVO);
			arquivoContas.createNewFile();
			if (arquivoContas.length() != 0)
				this.lerArquivo();
		} catch (IOException e) {
			throw new RepositorioException(e);
		}
	}

	/**
	 * Le as contas a partir do arquivo.
	 * 
	 * @throws RepositorioException
	 *             lancada em caso de erro na leitura do arquivo.
	 */
	private void lerArquivo() throws RepositorioException {
		FileInputStream fisBanco = null;
		ObjectInputStream oisBanco = null;
		try {
			fisBanco = new FileInputStream(this.arquivoContas);
			oisBanco = new ObjectInputStream(fisBanco);
			while (true) {
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

	/**
	 * Serializa todas as contas do array em um arquivo.
	 * 
	 * @throws RepositorioException
	 *             levantada no caso de um erro com o arquivo.
	 */
	public void gravarArquivo() throws RepositorioException {
		FileOutputStream fosBanco = null;
		ObjectOutputStream oosBanco = null;
		try {
			fosBanco = new FileOutputStream(this.arquivoContas, false);
			oosBanco = new ObjectOutputStream(fosBanco);
			IteratorContaAbstrata it = contas.getIterator();
			while (it.hasNext()) {
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

	@Override
	public boolean inserir(ContaAbstrata conta) throws RepositorioException {
		boolean sucesso = contas.inserir(conta);
		this.gravarArquivo();
		return sucesso;
	}

	@Override
	public ContaAbstrata procurar(String numero) throws RepositorioException {
		return contas.procurar(numero);
	}

	@Override
	public boolean remover(String numero) throws RepositorioException {
		boolean sucesso = contas.remover(numero);
		this.gravarArquivo();
		return sucesso;
	}

	@Override
	public boolean atualizar(ContaAbstrata conta) throws RepositorioException {
		boolean sucesso = contas.atualizar(conta);
		this.gravarArquivo();
		return sucesso;
	}

	@Override
	public boolean existe(String numero) throws RepositorioException {
		return contas.existe(numero);
	}

	public IteratorContaAbstrata getIterator() throws RepositorioException {
		return contas.getIterator();
	}
}
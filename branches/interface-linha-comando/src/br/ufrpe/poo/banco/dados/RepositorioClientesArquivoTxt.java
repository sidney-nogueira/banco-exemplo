package br.ufrpe.poo.banco.dados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.iterator.IteratorCliente;
import br.ufrpe.poo.banco.negocio.Cliente;

public class RepositorioClientesArquivoTxt implements IRepositorioClientes {

	private RepositorioClientesArray clientes;

	private File arquivo;

	public RepositorioClientesArquivoTxt(File arquivo)
			throws RepositorioException {
		clientes = new RepositorioClientesArray();
		this.arquivo = arquivo;
		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RepositorioException("Arquivo de clientes \""
						+ this.arquivo.getAbsolutePath()
						+ this.arquivo.getName() + "\" nao pode ser criado!");
			}
		}
		this.lerArquivo();
	}

	private void lerArquivo() throws RepositorioException {
		Scanner s = null;
		Cliente cliente = null;
		try {
			s = new Scanner(arquivo);
			while (s.hasNext()) {
				String nome = s.nextLine();
				String cpf = s.next();
				// ArrayList<String> contas = s.
				// cliente = new Cliente(nome, cpf, contas);
				this.clientes.inserir(cliente);
			}
		} catch (FileNotFoundException e) {
			throw new RepositorioException(e);
		} finally {
			s.close();
		}
	}

	private void concatenarEmArquivo() throws RepositorioException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(arquivo, true);
			fw.write("\n");
		} catch (IOException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				throw new RepositorioException(e);
			}
		}
	}

	private void gravarArquivo() throws RepositorioException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(arquivo);
			IteratorCliente it = clientes.getIterator();
			while (it.hasNext()) {
				Cliente c = it.next();
				fw.write(c.getNome() + " " + c.getCpf() + " " + c.getContas());
				fw.write("\n");
			}
		} catch (IOException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				throw new RepositorioException(e);
			}
		}
	}

	@Override
	public boolean inserir(Cliente cliente) throws RepositorioException {
		boolean sucesso = this.clientes.inserir(cliente);
		if (sucesso) {
			this.concatenarEmArquivo();
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
		if (sucesso) {
			this.gravarArquivo();
		}
		return sucesso;
	}

	@Override
	public boolean atualizar(Cliente cliente) throws RepositorioException {
		boolean sucesso = this.clientes.atualizar(cliente);
		if (sucesso) {
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

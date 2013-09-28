package br.ufrpe.poo.banco.dados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.iterator.IteratorCliente;
import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.negocio.Cliente;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaEspecial;
import br.ufrpe.poo.banco.negocio.ContaImposto;
import br.ufrpe.poo.banco.negocio.Poupanca;

public class RepositorioClientesArquivoTxt implements IRepositorioClientes {
	
	
	/** Contas do arquivo sao mantidas em memoria. */
	private RepositorioClientesArray clientes;

	/** Arquivo que armazena as contas. */
	private File arquivo;

	/**
	 * Constroi um repositorio que mantem contas em arquivo texto.
	 * 
	 * @param arquivo
	 *            arquivo texto com informacoes sobre as contas. Se arquivo nao
	 *            existe, sera criado um vazio.
	 * @throws RepositorioException
	 *             lancada caso o arquivo nao existe e nao pode ser criado.
	 */
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
//		this.lerArquivo();
	}


//	private void lerArquivo() throws RepositorioException {
//		Scanner s = null;
//		Cliente cliente = null;
//		try {
//			s = new Scanner(arquivo);
//			while (s.hasNext()) {
//				int tipoConta = s.nextInt();
//				String numero = s.next();
//				double saldo = Double.parseDouble(s.next());
//				switch (tipoConta) {
//				case 0:
//					cliente = new Conta(numero, saldo);
//					break;
//				case 1:
//					cliente = new Poupanca(numero, saldo);
//					break;
//				case 2:
//					cliente = new ContaImposto(numero, saldo);
//					break;
//				case 3:
//					double bonus = Double.parseDouble(s.next());
//					cliente = new ContaEspecial(numero, saldo, bonus);
//					break;
//				default:
//					throw new RepositorioException(
//							"Tipo de conta inexistente!");
//				}
//				this.clientes.inserir(cliente);
//			}
//		} catch (FileNotFoundException e) {
//			throw new RepositorioException(e);
//		} finally {
//			s.close();
//		}
//	}

	
//	private void gravarArquivo() throws RepositorioException {
//		FileWriter fw = null;
//		try {
//			fw = new FileWriter(arquivo);
//			IteratorContaAbstrata it = contas.getIterator();
//			while (it.hasNext()) {
//				ContaAbstrata c = it.next();
//				if (c instanceof Conta) {
//					fw.write("0 " + c.getNumero() + " " + c.getSaldo());
//				} else if (c instanceof Poupanca) {
//					fw.write("1 " + c.getNumero() + " " + c.getSaldo());
//				} else if (c instanceof ContaImposto) {
//					fw.write("2 " + c.getNumero() + " " + c.getSaldo());
//				} else if (c instanceof ContaEspecial) {
//					fw.write("3 " + c.getNumero() + " " + c.getSaldo() + " "
//							+ ((ContaEspecial) c).getBonus());
//				} else {
//					throw new RepositorioException(new Exception(
//							"Tipo de conta nao suportado!"));
//				}
//				fw.write("\n");
//			}
//		} catch (IOException e) {
//			throw new RepositorioException(e);
//		} finally {
//			try {
//				fw.close();
//			} catch (IOException e) {
//				throw new RepositorioException(e);
//			}
//		}
//	}
	
	@Override
	public boolean inserir(Cliente cliente) throws RepositorioException {
		boolean sucesso = this.clientes.inserir(cliente);
		if(sucesso){
//			this.concatenarEmArquivo(cliente);			
		}
		return sucesso;
	}

	@Override
	public Cliente procurar(String cpf) {
		return this.clientes.procurar(cpf);
	}

	@Override
	public boolean remover(String cpf) throws RepositorioException {
		boolean sucesso = this.clientes.remover(cpf);
		if(sucesso){
//			this.gravarArquivo();			
		}
		return sucesso;
	}

	@Override
	public boolean atualizar(Cliente cliente) throws RepositorioException {
		boolean sucesso = this.clientes.atualizar(cliente);
		if(sucesso){
//			this.gravarArquivo();			
		}
		return sucesso;
	}

	@Override
	public boolean existe(String cpf) {
		return this.clientes.existe(cpf);
	}

	@Override
	public IteratorCliente getIterator() {
		return this.clientes.getIterator();
	}

}

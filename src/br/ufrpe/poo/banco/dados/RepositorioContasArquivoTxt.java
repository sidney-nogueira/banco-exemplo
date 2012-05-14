package br.ufrpe.poo.banco.dados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.ufrpe.poo.banco.iterator.IteratorContaAbstrata;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaEspecial;
import br.ufrpe.poo.banco.negocio.ContaImposto;
import br.ufrpe.poo.banco.negocio.Poupanca;

/**
 * Implementacao de repositorio de contas que persiste contas em arquivo.
 */
public class RepositorioContasArquivoTxt implements RepositorioContas {

	/** Contas do arquivo sao guardadas em memoria num repositorio de contas. */
	private RepositorioContasArray contas;

	/** Caminho para arquivo que guarda as informacoes das contas. */
	private final String ARQUIVO = "contas.txt";

	/** Arquivo que armazena as contas. */
	private File arquivoContas;

	/**
	 * Constroi um repositorio a partir de contas armazenadas em arquivo texto.
	 * 
	 * @throws RepositorioException
	 */
	public RepositorioContasArquivoTxt() throws RepositorioException {
		contas = new RepositorioContasArray();
		arquivoContas = new File(ARQUIVO);
		if (!arquivoContas.exists()) {
			throw new RepositorioException("Arquivo de contas \""
					+ this.ARQUIVO + "\" nao foi encontrado!");
		}
		this.lerArquivo();
	}

	/**
	 * Le as contas a partir do arquivo.
	 * 
	 * @throws RepositorioException
	 *             lancada em caso de erro na leitura do arquivo.
	 */
	private void lerArquivo() throws RepositorioException {
		Scanner inBanco = null;
		ContaAbstrata conta = null;
		int tipoConta;
		String numero;
		double saldo;
		double bonus;
		try {
			inBanco = new Scanner(arquivoContas);
			while (inBanco.hasNext()) {
				tipoConta = inBanco.nextInt();
				numero = inBanco.next();
				saldo = Double.parseDouble(inBanco.next());
				switch (tipoConta) {
				case 0:
					conta = new Conta(numero, saldo); // eh uma Conta
					break;
				case 1:
					conta = new Poupanca(numero, saldo); // eh uma Poupanca
					break;
				case 2:
					conta = new ContaImposto(numero, saldo); // eh uma
																// ContaImposto
					break;
				case 3:
					bonus = Double.parseDouble(inBanco.next());
					conta = new ContaEspecial(numero, saldo, bonus); // eh uma
																		// ContaEspecial
					break;
				default:
					throw new RepositorioException(new Exception(
							"Tipo de conta inexistente!"));
				}
				this.contas.inserir(conta);
			}
		} catch (FileNotFoundException e) {
			throw new RepositorioException(e);
		} finally {
			inBanco.close();
		}
	}

	/**
	 * Concatena as informacoes da conta no fim do arquivo.
	 * 
	 * @param conta
	 *            conta a ser escrita.
	 * @throws RepositorioException
	 *             levantada no caso de erro com o arquivo.
	 */
	private void concatenarEmArquivo(ContaAbstrata conta)
			throws RepositorioException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(arquivoContas, true);
			System.out.println(conta);
			if (conta instanceof Poupanca) {
				fw.write("1 " + conta.getNumero() + " " + conta.getSaldo());
			} else if (conta instanceof ContaImposto) {
				fw.write("2 " + conta.getNumero() + " " + conta.getSaldo());
			} else if (conta instanceof ContaEspecial) {
				fw.write("3 " + conta.getNumero() + " " + conta.getSaldo()
						+ " " + ((ContaEspecial) conta).getBonus());
			} else if (conta instanceof Conta) {
				fw.write("0 " + conta.getNumero() + " " + conta.getSaldo());
			} else {
				throw new RepositorioException(new Exception("Erro interno!"));
			}
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

	/**
	 * Escreve todas as contas do array em um arquivo texto.
	 * 
	 * @throws RepositorioException
	 *             levantada no caso de um erro com o arquivo.
	 */
	private void gravarArquivo() throws RepositorioException {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(arquivoContas);
			bw = new BufferedWriter(fw);
			IteratorContaAbstrata it = contas.getIterator();
			while (it.hasNext()) {
				ContaAbstrata c = it.next();
				System.out.println(c);
				if (c instanceof Poupanca) {
					bw.write("1 " + c.getNumero() + " " + c.getSaldo());
				} else if (c instanceof ContaImposto) {
					bw.write("2 " + c.getNumero() + " " + c.getSaldo());
				} else if (c instanceof ContaEspecial) {
					bw.write("3 " + c.getNumero() + " " + c.getSaldo() + " "
							+ ((ContaEspecial) c).getBonus());
				} else if (c instanceof Conta) {
					bw.write("0 " + c.getNumero() + " " + c.getSaldo());
				} else {
					throw new RepositorioException(new Exception(
							"Erro interno!"));
				}
				bw.newLine();
			}
		} catch (IOException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				throw new RepositorioException(e);
			}
		}
	}

	@Override
	public boolean inserir(ContaAbstrata conta) throws RepositorioException {
		boolean sucesso = contas.inserir(conta);
		this.concatenarEmArquivo(conta);
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

	@Override
	public IteratorContaAbstrata getIterator() {
		return contas.getIterator();
	}
}
package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.IRepositorioClientes;
import br.ufrpe.poo.banco.dados.RepositorioClientesArquivoBin;
import br.ufrpe.poo.banco.dados.RepositorioException;

public class Gerencia implements IGerencia {

	private IRepositorioClientes clientes;

	private static IGerencia instance;

	public Gerencia(IRepositorioClientes repositorioClientesArquivoBin) {
		this.clientes = repositorioClientesArquivoBin;
	}

	public static IGerencia getInstance() throws InicializacaoSistemaException {
		if (Gerencia.instance == null) {
			try {
				Gerencia.instance = new Gerencia(
						new RepositorioClientesArquivoBin());
			} catch (RepositorioException e) {
				e.printStackTrace();
				throw new InicializacaoSistemaException();
			}
		}
		return Gerencia.instance;
	}
}

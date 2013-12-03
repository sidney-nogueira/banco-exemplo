package br.ufrpe.poo.banco.negocio;

import br.ufrpe.poo.banco.dados.RepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoTxt;
import br.ufrpe.poo.banco.dados.RepositorioException;

public class BancoGetInstanceRetornaNovaReferencia extends Banco {
	
	protected BancoGetInstanceRetornaNovaReferencia(RepositorioContas rep) {
		super(rep);
		// TODO Auto-generated constructor stub
	}

	public static IBanco getInstance() throws InicializacaoSistemaException {
		Banco.instance = null;
		return Banco.getInstance();
	}

}

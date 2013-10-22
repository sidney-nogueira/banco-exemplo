package br.ufrpe.poo.banco.gui;

import br.ufrpe.poo.banco.exceptions.InicializacaoSistemaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Banco;


public class InterfaceTextualAdministrador {
	
	private static Banco fachadaBanco; 
	
	public static void main(String[] args) throws RepositorioException {
		
		inicializarBanco();
		
	}

	private static void inicializarBanco() throws RepositorioException {
		
		try{
			fachadaBanco = Banco.getInstance();
		}catch(InicializacaoSistemaException e){
			e.printStackTrace();
		}
		
	}

}

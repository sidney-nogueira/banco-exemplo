package br.ufrpe.poo.banco.negocio;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.*;

import br.ufrpe.poo.banco.dados.RepositorioContas;
import br.ufrpe.poo.banco.dados.RepositorioContasArquivoTxt;
import br.ufrpe.poo.banco.dados.RepositorioException;

/**
 * Especializacao de Banco utilizada para testar o Banco como um unidade. Em
 * cada testes usa um repositorio mock no lugar do repositorio.
 * 
 */
public class TesteBancoUnidade extends Banco {

	/**
	 * Constroi Banco passando um repositorio nulo. Em cada teste um objeto mock
	 * sera utilizado no lugar da referencia nula.
	 */
	public TesteBancoUnidade() {
		super(null);
	}
	
	@Test
	public void testCadastraContaNaoExistente()
			throws InicializacaoSistemaException, RepositorioException {
		
		TesteBancoUnidade banco = new TesteBancoUnidade();
		ContaAbstrata c = new Conta("1", 0);
		//
		banco.contas = mock(RepositorioContas.class);
		when(banco.contas.existe("1")).thenReturn(false);
		when(banco.contas.procurar("1")).thenReturn(c);
		//
		try {
			banco.cadastrar(c);
			assertEquals(0, banco.getSaldo("1"), 0);
		} catch (RepositorioException e) {
			fail();
		} catch (ContaJaCadastradaException e) {
			fail();
		} catch (ContaNaoEncontradaException e) {
			fail();
		}
	}

	@Test(expected = ContaJaCadastradaException.class)
	public void testCadastraContaExistente()
			throws InicializacaoSistemaException, RepositorioException, 
			ContaJaCadastradaException {
		
		TesteBancoUnidade banco = new TesteBancoUnidade();
		//
		banco.contas = mock(RepositorioContas.class);
		when(banco.contas.existe("1")).thenReturn(true);
		//
		try {
			ContaAbstrata c = new Conta("1", 0);
			banco.cadastrar(c);
			fail("ContaJaCadastradaException nao foi lancada");
		} catch (RepositorioException e) {
			fail();
		} 
	}

}
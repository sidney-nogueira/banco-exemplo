package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;


/**
 * Esta classe testa Cliente utilizando um mock para ContaAbstrata
 * 
 * @author sidneynogueira
 * 
 */
public class TesteClienteUnidade {
	
	//referencias para objetos conta abstrata que seram simulados para o teste de Cliente
	private ContaAbstrata c1;
	private ContaAbstrata c2;
	
	@Before
	public void criarContasMock(){
		c1 = mock(ContaAbstrata.class);
		when(c1.getNumero()).thenReturn("1");
		c2 = mock(ContaAbstrata.class);
		when(c2.getNumero()).thenReturn("2");
	}

	/**
	 * Teste que ao ser criado um cliente retorna o rg passado como parametro,
	 * sua lista de contas nao e nula e tem tamanho 0
	 */
	@Test
	public void testeConstrutor() {
		String rg = "321312312";
		Cliente c = new Cliente(rg);
		assertEquals(rg, c.getRg());
		assertTrue(c.quantidadeContas() == 0);
	}

	/**
	 * Testa adicionar conta usando um mock para conta abstrata
	 */
	@Test
	public void testeAdicionarConta() {		
		Cliente c = new Cliente("123");
		c.adicionarConta(c1);
		c.adicionarConta(c2);
		assertEquals(2, c.quantidadeContas());
	}
		
	@Test
	public void testeRecuperarContas(){
		Cliente c = new Cliente("123");
		c.adicionarConta(c2);
		c.adicionarConta(c1);
		ContaAbstrata contas[] = c.getContas();
		assertEquals("2", contas[0].getNumero());		
		assertEquals("1", contas[1].getNumero());		
	}
	
	/**
	 * Testa que uma conta inserida depois do limite de contas e ignorada
	 */
	@Test
	public void testeAdicionarIgnoraConta() {		
		Cliente c = new Cliente("123");
		ContaAbstrata c3 = mock(ContaAbstrata.class);
		when(c3.getNumero()).thenReturn("3");
		c.adicionarConta(c1);
		c.adicionarConta(c2);
		c.adicionarConta(c3);
		assertEquals(Cliente.MAX_CONTAS, c.quantidadeContas());
	}
	
	/**
	 * Testa que um conta que uma conta que ja existe nao e adicionada novamente
	 */
	@Test
	public void testeAdicionarContaDuplicada() {		
		Cliente c = new Cliente("123");
		c.adicionarConta(c1);
		c.adicionarConta(c1);
		assertEquals(1, c.quantidadeContas());
	}
	
	@Test
	public void testeFechaContas(){
		Cliente c = new Cliente("123");
		c.adicionarConta(c2);
		c.adicionarConta(c1);
		ContaAbstrata contas[] = c.getContas();
		assertEquals("2", contas[0].getNumero());		
		assertEquals("1", contas[1].getNumero());		
	}

}

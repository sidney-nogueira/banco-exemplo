package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TesteConta {
	
	
	/**
	 * Testa se os parametros do construtor sao passados corretamente
	 */
	@Test
	public void testeConstrutor() {
		Conta c = new Conta("1",100);
		assertEquals("Numero incorreto", "1", c.getNumero());
		assertEquals("Saldo incorreto", 100,  c.getSaldo(), 0);
	}
	
	/**
	 * Testa que nao e permitido criar uma conta com saldo negativo
	 */
	@Test 
	public void testeConstrutorSaldoNegativo() {
		Conta c = new Conta("1",-786);
		assertEquals(0,  c.getSaldo(), 0);
	}
	
	/**
	 * Testa a operacao de debito quando existe saldo
	 */
	@Test
	public void testeDebitarSucesso() {
		Conta c = new Conta("123",300);
		try {
			c.debitar(200);
		} catch (SaldoInsuficienteException e) {
			fail("Excecao nao deveria ter sido levantada");
		}
		assertEquals("Saldo incorreto", 100, c.getSaldo(), 0);
	}
	
	/**
	 * Testa a operacao de debito quando nao existe saldo
	 * @throws SaldoInsuficienteException
	 */
	@Test (expected = SaldoInsuficienteException.class)
	public void testeDebitarForaLimiteLevanteExcecao() throws SaldoInsuficienteException {
		Conta c = new Conta("123",300);
		c.debitar(301);
	}

	/**
	 * Testa que uma operacao de debito nao altera o saldo da conta quando nao existe saldo suficiente
	 */
	@Test 
	public void testeDebitarForaLimiteSaldoInalterado() {
		Conta c = new Conta("2132",42342);
		boolean excecao = false;
		try {
			c.debitar(32432423);
		} catch (SaldoInsuficienteException e) {
			excecao = true;
		}
		if(!excecao) fail("Nao levantou SaldoInsuficienteException");
		assertEquals("Saldo nao deveria ter mudado", 42342, c.getSaldo(), 0);
	}

	
	/**
	 * Testa que o metodo creditar de conta nao permite creditar valor negativo
	 */
	@Test
	public void testeCreditarNaoMudaSaldo() {
		Conta c = new Conta("6564",2000);
		c.creditar(-1000);
		assertEquals("Credita nao deveria alterar o saldo", 2000, c.getSaldo(), 0);
	}
	
	/**
	 * Testa que um valor positivo e corretamento adicionado ao saldo da conta
	 */
	@Test
	public void testeCreditarOk(){
		//fail("Lembrar de implementar");	
		Conta c = new Conta("6564",2000);
		c.creditar(1000);
		assertEquals(3000, c.getSaldo(), 0);
	}
	
	/**
	 * Testa o metodo equals de conta quando duas contas tem o mesmo numero e saldos diferentes
	 */
	@Test
	public void testeEqualsOk(){
		Conta c1 = new Conta("456", 50);
		Conta c2 = new Conta("456", 3423);
		assertEquals(c1,c2);
	}
	
	/**
	 * Testa o metodo equals enviando objeto diferente de conta
	 */
	@Test
	public void testeEqualsOutroObjeto(){
		Conta c1 = new Conta("456", 50);
		Conta c2 = new Conta("456", 3423);
		assertEquals(c1,c2);
	}
	
	/**
	 * Testa o metodo equal de conta quando duas contas tem o mesmo saldo e numeros diferentes
	 */
	@Test
	public void testeEqualsNok(){
		Conta c1 = new Conta("456", 50);
		Cliente cl1 = new Cliente("123");
		assertTrue("Objetos nao sao iguais",!c1.equals(cl1));
	}	
}
package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class TestePoupanca {

	@Test
	public final void testRenderJurosZero() {
		Poupanca p = new Poupanca("4432",1000);
		double saldoAntes = p.getSaldo();
		p.renderJuros(0);
		assertEquals("Saldo nao deveria mudar", saldoAntes, p.getSaldo(), 0);
	}
	
	@Test
	public final void testRenderJurosCinquentaPorcento() {
		Poupanca p = new Poupanca("4432",1000);
		double saldoAntes = p.getSaldo();
		p.renderJuros(0.5);
		assertEquals(saldoAntes + saldoAntes/2, p.getSaldo(), 0);
	}


}
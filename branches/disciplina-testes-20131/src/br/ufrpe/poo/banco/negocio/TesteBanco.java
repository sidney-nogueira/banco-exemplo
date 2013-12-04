package br.ufrpe.poo.banco.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import br.ufrpe.poo.banco.dados.RepositorioException;

public class TesteBanco {

	@Before
	public void apagarArquivoContas() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("a.txt"));
		bw.close();
	}

	/**
	 * Verifica o cadastramento de uma nova conta.
	 * 
	 */
	@Test
	public void testeCadastrarContaNova() throws RepositorioException,
			ContaJaCadastradaException, ContaNaoEncontradaException,
			InicializacaoSistemaException {

		Conta conta = new Conta("1", 100);
		IBanco banco = BancoGetInstanceRetornaNovaReferencia.getInstance();
		banco.cadastrar(conta);
		assertEquals(100, banco.getSaldo("1"), 0);
	}

	/**
	 * Verifica que nao e permitido cadastrar duas contas com o mesmo numero.
	 * 
	 */
	@Test(expected = ContaJaCadastradaException.class)
	public void testeCadastrarContaExistente() throws RepositorioException,
			ContaJaCadastradaException, ContaNaoEncontradaException,
			InicializacaoSistemaException {

		Conta c1 = new Conta("1", 200);
		Conta c2 = new Conta("1", 300);
		IBanco banco = BancoGetInstanceRetornaNovaReferencia.getInstance();
		banco.cadastrar(c1);
		banco.cadastrar(c2);
		fail("Excecao ContaJaCadastradaException nao levantada");
	}

	/**
	 * Verifica se que saldo da conta e retornado corretamente.
	 * 
	 */
	@Test
	public void testaGetSaldo() throws RepositorioException,
			ContaNaoEncontradaException, InicializacaoSistemaException,
			ContaJaCadastradaException {

		Conta conta = new Conta("1", 100);
		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.cadastrar(conta);
		assertEquals(100, b.getSaldo("1"), 0);
	}

	/**
	 * Verifica que a tentativa de verificar o saldo de uma conta que nao existe
	 * levanta excecao.
	 * 
	 */
	@Test(expected = ContaNaoEncontradaException.class)
	public void testaGetSaldoContaInexistente() throws RepositorioException,
			ContaNaoEncontradaException, InicializacaoSistemaException {

		IBanco banco = BancoGetInstanceRetornaNovaReferencia.getInstance();
		banco.getSaldo("5");
		fail("Excecao ContaNaoEncontradaException nao levantada");
	}

	/**
	 * Verifica se o credito esta sendo executado corretamente em uma conta
	 * corrente.
	 * 
	 */
	@Test
	public void testeCreditar() throws RepositorioException,
			ContaNaoEncontradaException, InicializacaoSistemaException,
			ContaJaCadastradaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		ContaAbstrata conta = new Conta("1", 100);
		b.cadastrar(conta);
		b.creditar("1", 200);
		assertEquals(300, b.getSaldo("1"), 0);
	}

	/**
	 * Verifica a excecao levantada na tentativa de creditar em uma conta que
	 * nao existe.
	 * 
	 */
	@Test(expected = ContaNaoEncontradaException.class)
	public void testeCreditarContaInexistente() throws RepositorioException,
			ContaNaoEncontradaException, InicializacaoSistemaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.creditar("30", 200);
		fail("Excecao ContaNaoEncontradaException nao levantada");
	}

	/**
	 * Verifica que a operacao de debito em conta corrente esta acontecendo
	 * corretamente.
	 * 
	 */
	@Test
	public void testeDebitar() throws RepositorioException,
			ContaNaoEncontradaException, SaldoInsuficienteException,
			InicializacaoSistemaException, ContaJaCadastradaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.cadastrar(new Conta("1", 50));
		b.debitar("1", 50);
		assertEquals(0, b.getSaldo("1"), 0);
	}

	/**
	 * Verifica que tentantiva de debitar em uma conta que nao existe levante
	 * excecao.
	 * 
	 */
	@Test(expected = ContaNaoEncontradaException.class)
	public void testeDebitarContaInexistente() throws RepositorioException,
			ContaNaoEncontradaException, SaldoInsuficienteException,
			InicializacaoSistemaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.debitar("30", 50);
		fail("Excecao ContaNaoEncontradaException nao levantada");
	}

	/**
	 * Verifica que a transferencia entre contas correntes e realizada com
	 * sucesso.
	 * 
	 */
	@Test
	public void testeTransferir() throws RepositorioException,
			ContaNaoEncontradaException, SaldoInsuficienteException,
			InicializacaoSistemaException, ContaJaCadastradaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.cadastrar(new Conta("1", 100));
		b.cadastrar(new Conta("2", 200));
		b.transferir("1", "2", 50);
		assertEquals(50, b.getSaldo("1"), 0);
		assertEquals(250, b.getSaldo("2"), 0);
	}

	/**
	 * Verifica que tentativa de transferir entre contas cujos numeros nao
	 * existe levanta excecao.
	 * 
	 */
	@Test(expected = ContaNaoEncontradaException.class)
	public void testeTransferirContaInexistente() throws RepositorioException,
			ContaNaoEncontradaException, SaldoInsuficienteException,
			InicializacaoSistemaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.transferir("1", "30", 50);
		fail("Excecao ContaNaoEncontradaException nao levantada)");
	}

	/**
	 * Verifica que render juros de uma conta poupanca funciona corretamente
	 * 
	 */
	@Test
	public void testeRenderJuros() throws RepositorioException,
			ContaNaoEncontradaException, RenderJurosPoupancaException,
			InicializacaoSistemaException, ContaJaCadastradaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		Poupanca poupanca = new Poupanca("20", 100);
		b.cadastrar(poupanca);
		double saldoSemJuros = poupanca.getSaldo();
		double saldoComJuros = saldoSemJuros + (saldoSemJuros * 0.008);
		b.renderJuros(poupanca.getNumero());
		assertEquals(saldoComJuros, b.getSaldo("20"), 0);
	}

	/**
	 * Verifica que tentativa de render juros em conta inexistente levanta
	 * excecao.
	 * 
	 */
	@Test(expected = ContaNaoEncontradaException.class)
	public void testeRenderJurosContaInexistente() throws RepositorioException,
			ContaNaoEncontradaException, RenderJurosPoupancaException,
			InicializacaoSistemaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.renderJuros("30");
		fail("Excecao ContaNaoEncontradaException nao levantada");
	}

	/**
	 * Verifica que tentativa de render juros em conta que nao e poupanca
	 * levanta excecao.
	 * 
	 */
	@Test(expected = RenderJurosPoupancaException.class)
	public void testeRenderJurosContaNaoEhPoupanca()
			throws RepositorioException, ContaNaoEncontradaException,
			RenderJurosPoupancaException, InicializacaoSistemaException,
			ContaJaCadastradaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.cadastrar(new Conta("1", 100));
		b.renderJuros("1");
		fail("Excecao RenderJurosPoupancaException nao lancada");
	}

	/**
	 * Verifica que render bonus de uma conta especial funciona corretamente.
	 * 
	 */
	@Test
	public void testeRenderBonus() throws RepositorioException,
			ContaNaoEncontradaException, RenderBonusContaEspecialException,
			InicializacaoSistemaException, RenderJurosPoupancaException,
			ContaJaCadastradaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		ContaEspecial conta = new ContaEspecial("40", 100, 10);
		b.cadastrar(conta);
		b.renderBonus("40");
		assertEquals(110, b.getSaldo("40"), 0);
	}

	/**
	 * Verifica que a tentativa de render bonus em inexistente levanta excecao.
	 * 
	 */
	@Test(expected = ContaNaoEncontradaException.class)
	public void testeRenderBonusContaInexistente() throws RepositorioException,
			ContaNaoEncontradaException, RenderBonusContaEspecialException,
			InicializacaoSistemaException, RenderJurosPoupancaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		b.renderBonus("60");
		fail("Excecao ContaNaoEncontradaException nao levantada");
	}

	/**
	 * Verifica que tentativa de render bonus em conta que nao e especial
	 * levante excecao.
	 */
	@Test(expected = RenderBonusContaEspecialException.class)
	public void testeRenderBonusContaNaoEspecial() throws RepositorioException,
			ContaNaoEncontradaException, RenderBonusContaEspecialException,
			InicializacaoSistemaException, RenderJurosPoupancaException,
			ContaJaCadastradaException {

		IBanco b = BancoGetInstanceRetornaNovaReferencia.getInstance();
		Poupanca poupanca = new Poupanca("20", 100);
		b.cadastrar(poupanca);
		b.renderBonus("20");
		fail("Excecao RenderBonusContaEspecialException nao levantada");
	}

}

package br.ufrpe.poo.banco;

import java.io.File;

import br.ufrpe.poo.banco.dados.RepositorioException;
import br.ufrpe.poo.banco.negocio.Banco;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaEspecial;
import br.ufrpe.poo.banco.negocio.ContaImposto;
import br.ufrpe.poo.banco.negocio.ContaJaCadastradaException;
import br.ufrpe.poo.banco.negocio.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.negocio.IBanco;
import br.ufrpe.poo.banco.negocio.InicializacaoSistemaException;
import br.ufrpe.poo.banco.negocio.Poupanca;
import br.ufrpe.poo.banco.negocio.RenderBonusContaEspecialException;
import br.ufrpe.poo.banco.negocio.RenderJurosPoupancaException;
import br.ufrpe.poo.banco.negocio.SaldoInsuficienteException;

/**
 * Esta classe chama todos os metodos da fachada do banco ao menos uma fez.
 * Exercita alguns fluxos possiveis de utilizacao do banco.
 * 
 * @author sidneynogueira
 * 
 */
public class TesteBanco {

	public static void main(String[] args)
			throws InicializacaoSistemaException, RepositorioException,
			ContaJaCadastradaException, ContaNaoEncontradaException,
			RenderJurosPoupancaException, RenderBonusContaEspecialException,
			SaldoInsuficienteException {

		// apaga o arquivo das contas
		File f = new File("contas.txt");
		if (f.exists()) {
			f.delete();
		}

		IBanco b = Banco.getInstance();

		ContaAbstrata c1 = new Conta("1", 0);
		ContaAbstrata c2 = new Poupanca("2", 0);
		ContaAbstrata c3 = new ContaEspecial("3", 30, 10);
		ContaAbstrata c4 = new ContaImposto("4", 20);

		// inserindo contas com sucesso
		b.cadastrar(c1);
		b.cadastrar(c2);
		b.cadastrar(c3);
		b.cadastrar(c4);

		// tentativa de inserir conta j‡ existente
		try {
			b.cadastrar(c4);
		} catch (ContaJaCadastradaException e) {
			System.out.println("Conta j‡ cadastrada");
		}

		// creditando com sucesso
		b.creditar(c1.getNumero(), 110.00);
		b.creditar(c2.getNumero(), 110.00);
		b.creditar(c3.getNumero(), 80.00);
		b.creditar(c4.getNumero(), 90.00);

		try {
			b.creditar("5", 50.00);
		} catch (ContaNaoEncontradaException e) {
			System.out.println("Conta nao encontrada");
		}

		// imprime saldo das contas depois das operacoes de credito
		// todas tem o mesmo saldo de 110
		System.out.println(c1.getSaldo());
		System.out.println(c2.getSaldo());
		System.out.println(c3.getSaldo());
		System.out.println(c4.getSaldo());

		// imprime saldo das contas recuperando do repositorio
		// todas tem o mesmo saldo de 110
		System.out.println(b.getSaldo(c1.getNumero()));
		System.out.println(b.getSaldo(c2.getNumero()));
		System.out.println(b.getSaldo(c3.getNumero()));
		System.out.println(b.getSaldo(c4.getNumero()));

		// debitando com sucesso
		b.debitar(c1.getNumero(), 10.00);
		b.debitar(c2.getNumero(), 10.00);
		b.debitar(c3.getNumero(), 10.00);
		b.debitar(c4.getNumero(), 10.00);

		// imprime saldo das contas depois das operacoes de debito
		// todas tem o mesmo saldo 100, excecao da conta imposto
		System.out.println(c1.getSaldo());
		System.out.println(c2.getSaldo());
		System.out.println(c3.getSaldo());
		System.out.println(c4.getSaldo());

		// imprime saldo das contas recuperando do repositorio
		// todas tem o mesmo saldo de 100, excecao da conta imposto
		System.out.println(b.getSaldo(c1.getNumero()));
		System.out.println(b.getSaldo(c2.getNumero()));
		System.out.println(b.getSaldo(c3.getNumero()));
		System.out.println(b.getSaldo(c4.getNumero()));

		// operacoes especificas
		b.renderJuros(c2.getNumero());
		System.out.println(c2.getSaldo());
		System.out.println(b.getSaldo(c2.getNumero()));

		try {
			b.renderJuros(c3.getNumero());
		} catch (RenderJurosPoupancaException e) {
			System.out.println("So pode render juros de conta poupanca");
			System.out.println(c3.getSaldo());
		}

		b.renderBonus(c3.getNumero());
		System.out.println(c3.getSaldo());
		System.out.println(b.getSaldo(c3.getNumero()));

		try {
			b.renderBonus(c4.getNumero());
		} catch (RenderBonusContaEspecialException e) {
			System.out.println("So pode render bonus de conta especial");
			System.out.println(c4.getSaldo());
		}
		
		b.transferir(c1.getNumero(), c2.getNumero(), 50);
		System.out.println(c1.getSaldo());
		System.out.println(c2.getSaldo());

	}

}

package br.ufrpe.poo.banco.gui;

import java.util.Scanner;

import br.ufrpe.poo.banco.negocio.Cliente;
import br.ufrpe.poo.banco.negocio.Gerencia;
import br.ufrpe.poo.banco.negocio.IGerencia;
import br.ufrpe.poo.banco.negocio.InicializacaoSistemaException;

public class InterfaceTextualAdministrador {

	private int opcao;
	private Scanner read;
	private IGerencia fachadaGerencia;

	public InterfaceTextualAdministrador() {
		this.read = new Scanner(System.in);
	}

	public void menu() {

		try {
			fachadaGerencia = Gerencia.getInstance();
		} catch (InicializacaoSistemaException e) {
			e.printStackTrace();
		}

		System.out.println("Admin:");
		do {
			try {
				System.out.println("(1) Cliente");
				System.out.println("(2) Conta");
				System.out.println("(0) Sair");
				System.out.print("Opção: ");
				this.opcao = read.nextInt();
				if (this.opcao > 2 || this.opcao < 0)
					throw new OpcaoMenuInvalidaException();
				if (this.opcao == 0) {
					System.out.println("Sistema encerrado...");
					System.exit(0);
				}
				switch (this.opcao) {
				case 1:
					menuCliente();
					break;
				case 2:
					menuConta();
					break;
				default:
					break;
				}

			} catch (OpcaoMenuInvalidaException e) {
				e.printStackTrace();
			}
		} while (true);
	}

	private void menuConta() {
		System.out.println("Conta:");
		do {
			try {
				System.out.println("(1) Cadastrar conta");
				System.out.println("(0) Sair");
				System.out.print("Opção: ");
				if (this.opcao > 1 || this.opcao < 0)
					throw new OpcaoMenuInvalidaException();
				if (this.opcao == 0)
					break;
				switch (this.opcao) {
				case 1:
					cadastrarConta();
					break;
				default:
					break;
				}
			} catch (OpcaoMenuInvalidaException e) {
				e.printStackTrace();
			}
		} while (true);

	}

	private void menuCliente() {
		System.out.println("Cliente:");
		do {
			try {
				System.out.println("(1) Cadastrar cliente");
				System.out.println("(0) Sair");
				System.out.print("Opção: ");
				if (this.opcao > 1 || this.opcao < 0)
					throw new OpcaoMenuInvalidaException();
				if (this.opcao == 0)
					break;
				switch (this.opcao) {
				case 1:
					cadastrarCliente();
					break;
				default:
					break;
				}
			} catch (OpcaoMenuInvalidaException e) {
				e.printStackTrace();
			}
		} while (true);

	}

	private void cadastrarCliente() {
		Cliente novoCliente = new Cliente();
		System.out.println("Novo cliente (cancelar = 0): ");
		System.out.print("Informe o nome do novo cliente: ");
		String nome = read.nextLine();
		if (nome.equals("0"))
			return;
		novoCliente.setNome(nome);
		System.out.println("Informe o cpf do novo cliente: ");
		String cpf = read.next();
		if (cpf.equals("0"))
			return;
		novoCliente.setCpf(cpf);
		do {
			try {
				System.out.println("Cadastrar cliente? (S/N): ");
				String esc = read.next();
				if (esc.equalsIgnoreCase("s")) {
					// fahcadaGerencia.cadastrarCliente(novoCliente);
					System.out.println("Cliente cadastrado com sucesso!");
				} else if (esc.equalsIgnoreCase("n")) {
					System.out.println("Operação cancelada!");
				} else
					throw new OpcaoMenuInvalidaException();
			} catch (OpcaoMenuInvalidaException e) {
				e.printStackTrace();
			}
		} while (true);
	}

	private void cadastrarConta() {
		// TODO Auto-generated method stub

	}

}

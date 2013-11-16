package br.ufrpe.poo.banco.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import br.ufrpe.poo.banco.exceptions.AcessoMenuClienteBloqueadoExeception;
import br.ufrpe.poo.banco.exceptions.ClienteNaoPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.exceptions.EntradaInvalidaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.exceptions.SaldoInsuficienteException;
import br.ufrpe.poo.banco.exceptions.ValorInvalidoException;
import br.ufrpe.poo.banco.negocio.Cliente;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;

public class ClienteMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private GridLayout gridLayout;
	private JButton getSaldoButton;
	private JButton transferirButton;
	private JButton creditarButton;
	private JButton saqueButton;
	private JButton exitButton;
	private Cliente cliente;
	private JOptionPane getSaldoPane;
	private static ClienteMenuFrame clienteMenuFrame;

	public static ClienteMenuFrame getInstanceClienteMenuFrame(){
		if(ClienteMenuFrame.clienteMenuFrame == null){
			ClienteMenuFrame.clienteMenuFrame = new ClienteMenuFrame();
		}
		return ClienteMenuFrame.clienteMenuFrame;
	}

	public ClienteMenuFrame() {
		super();
		try {
			int cont = 0;
			while (true) {
				String cpf = JOptionPane.showInputDialog(null,
						"Para acessar o menu informe o seu CPF (Tentativa = "
								+ cont + "): ", "Senha (CPF)",
						JOptionPane.PLAIN_MESSAGE);
				Cliente achouCliente = AppletClienteMenuFrame.banco
						.procurarCliente(cpf);
					
				if (achouCliente != null) {
					cliente = achouCliente;
					break;
				}
				cont++;
				if (cont == 3)
					throw new AcessoMenuClienteBloqueadoExeception();
			}
			initialize();
		} catch (AcessoMenuClienteBloqueadoExeception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			System.exit(0);
		} 
	}

	private void initialize() {
		this.setTitle("Menu Cliente (Nome: " + cliente.getNome() + ")");
		this.setSize(500, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(getGridLayout());
		this.add(getGetSaldoButton());
		this.add(getCreditarButton());
		this.add(getSaqueButton());
		this.add(getTransferirButton());
		this.add(getExitButton());
	}

	public GridLayout getGridLayout() {
		if (this.gridLayout == null) {
			this.gridLayout = new GridLayout(2, 3, 5, 5);
		}
		return this.gridLayout;
	}

	public JButton getGetSaldoButton() {
		if (this.getSaldoButton == null) {
			this.getSaldoButton = new JButton();
			this.getSaldoButton.setText("Saldo");
			this.getSaldoButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						String numeroConta = JOptionPane.showInputDialog(null,
								"Informe o número da conta: ", "Conta",
								JOptionPane.PLAIN_MESSAGE);
						
						ContaAbstrata achouConta = AppletClienteMenuFrame.banco
								.procurarEmContasDoCliente(cliente, numeroConta);
						String saldo = String.format("Saldo: R$%.2f",
								achouConta.getSaldo());
						
						JOptionPane.showMessageDialog(null, saldo, "Saldo",
								JOptionPane.PLAIN_MESSAGE);
					} catch (ClienteNaoPossuiContaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}

				}
			});
		}
		return this.getSaldoButton;
	}

	public JButton getTransferirButton() {
		if (this.transferirButton == null) {
			this.transferirButton = new JButton();
			this.transferirButton.setText("Transferir");
			this.transferirButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						String numeroContaOrigem = JOptionPane.showInputDialog(
								null,
								"Informe o número de uma de suas contas:",
								"Conta Origem", JOptionPane.PLAIN_MESSAGE);
						ContaAbstrata contaOrigem = AppletClienteMenuFrame.banco
								.procurarEmContasDoCliente(cliente,
										numeroContaOrigem);
						String numeroContaDestino = JOptionPane
								.showInputDialog(null,
										"Informe o número da conta destino:",
										"Conta Destino",
										JOptionPane.PLAIN_MESSAGE);
						ContaAbstrata contaDestino = AppletClienteMenuFrame.banco
								.procurarConta(numeroContaDestino);
						if (contaDestino == null)
							throw new ContaNaoEncontradaException();
						String valor = JOptionPane.showInputDialog(null,
								"Informe o valor da transferência:",
								"Transferência", JOptionPane.PLAIN_MESSAGE);
						if (!valor.matches("[0-9]*"))
							throw new EntradaInvalidaException();
						double valor2 = Double.parseDouble(valor);
						AppletClienteMenuFrame.banco.transferir(contaOrigem,
								contaDestino, valor2);
					} catch (ContaNaoEncontradaException
							| EntradaInvalidaException
							| ClienteNaoPossuiContaException
							| SaldoInsuficienteException | RepositorioException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

		}
		return this.transferirButton;
	}

	public JButton getCreditarButton() {
		if (this.creditarButton == null) {
			this.creditarButton = new JButton();
			this.creditarButton.setText("Depositar");
			this.creditarButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						String numero = JOptionPane.showInputDialog(null,
								"Informe o número da conta:", "Conta",
								JOptionPane.PLAIN_MESSAGE);
						ContaAbstrata conta = AppletClienteMenuFrame.banco
								.procurarEmContasDoCliente(cliente, numero);
						String valor = JOptionPane.showInputDialog(null,
								"Informe o valor a ser depositado:",
								"Depositar", JOptionPane.PLAIN_MESSAGE);
						if (!valor.matches("[0-9]*"))
							throw new EntradaInvalidaException();
						double valor2 = Double.parseDouble(valor);
						AppletClienteMenuFrame.banco.creditar(conta, valor2);
						JOptionPane.showMessageDialog(null,
								"Deposito realizado com sucesso!");
					} catch (ClienteNaoPossuiContaException
							| EntradaInvalidaException | RepositorioException
							| ValorInvalidoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return this.creditarButton;
	}

	public JButton getSaqueButton() {
		if (this.saqueButton == null) {
			this.saqueButton = new JButton();
			this.saqueButton.setText("Saque");
			this.saqueButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						String numero = JOptionPane.showInputDialog(null,
								"Informe o número da conta:", "Conta",
								JOptionPane.PLAIN_MESSAGE);
						ContaAbstrata conta = AppletClienteMenuFrame.banco
								.procurarEmContasDoCliente(cliente, numero);
						String valor = JOptionPane.showInputDialog(null,
								"Informe o valor a ser sacado:", "Saque",
								JOptionPane.PLAIN_MESSAGE);
						if (!valor.matches("[0-9]*"))
							throw new EntradaInvalidaException();
						double valor2 = Double.parseDouble(valor);
						AppletClienteMenuFrame.banco.debitar(conta, valor2);
						JOptionPane.showMessageDialog(null,
								"Saque realizado com sucesso!");
					} catch (ClienteNaoPossuiContaException
							| EntradaInvalidaException | RepositorioException
							| ValorInvalidoException
							| SaldoInsuficienteException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return this.saqueButton;
	}

	private JButton getExitButton() {
		if (this.exitButton == null) {
			this.exitButton = new JButton();
			this.exitButton.setText("Sair");
			this.exitButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
		}
		return this.exitButton;
	}
	
	public void setGetSaldoPane(JOptionPane getSaldoPane) {
		if(this.getSaldoPane == null){
			this.getSaldoPane = new JOptionPane("messagem", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		}
		this.getSaldoPane = getSaldoPane;
	}

}

package br.ufrpe.poo.banco.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.ufrpe.poo.banco.exceptions.CampoVazioException;
import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Cliente;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaEspecial;
import br.ufrpe.poo.banco.negocio.ContaImposto;
import br.ufrpe.poo.banco.negocio.Poupanca;

public class CadastroFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static CadastroFrame instance;

	private JPanel panel;

	private JTextField cpfTextField;
	private JLabel cpfLabel;
	private JTextField nomeTextField;
	private JLabel nomeLabel;
	private JTextField contaTextField;
	private JLabel contaLabel;

	private JButton submeterCadastroButton;
	private JButton cancelarButton;
	private JButton apagarCamposButton;

	private ButtonGroup contasButtonGroup;
	private JRadioButton tipoContaRadioButton;
	private JRadioButton tipoPoupancaRadioButton;
	private JRadioButton tipoEspecialRadioButton;
	private JRadioButton tipoImpostoRadioButton;
	private JLabel contasLabel;

	private ContaAbstrata tipoConta;

	public static CadastroFrame getInstance() {
		if (instance == null) {
			CadastroFrame.instance = new CadastroFrame();
		}
		return CadastroFrame.instance;
	}

	public CadastroFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setTitle("Cadastrar"); // título
		this.setResizable(false); // botão maximizar desativado
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, 350, 300);
		this.setLocationRelativeTo(null); // centraliza na tela
		this.setContentPane(getPanel());
	}

	private JPanel getPanel() {
		if (this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(null);
			this.panel.add(getCpfLabel());
			this.panel.add(getCpfTextField());
			this.panel.add(getNomeLabel());
			this.panel.add(getNomeTextField());
			this.panel.add(getContaLabel());
			this.panel.add(getContaTextField());
			this.panel.add(getSubmeterCadastroButton());
			this.panel.add(getApagarCamposButton());
			this.panel.add(getCancelarButton());
			this.getContasButtonGroup();
			this.panel.add(getTipoContaRadioButton());
			this.panel.add(getTipoPoupancaRadioButton());
			this.panel.add(getTipoEspecialRadioButton());
			this.panel.add(getTipoImpostoRadioButton());
			this.panel.add(getContasLabel());
		}
		return this.panel;
	}

	public JTextField getCpfTextField() {
		if (this.cpfTextField == null) {
			this.cpfTextField = new JTextField();
			this.cpfTextField.setColumns(10);
			this.cpfTextField.setBounds(130, 20, 100, 20);
		}
		return cpfTextField;
	}

	public JLabel getCpfLabel() {
		if (this.cpfLabel == null) {
			this.cpfLabel = new JLabel("CPF: ");
			this.cpfLabel.setBounds(20, 20, 30, 20);
		}
		return cpfLabel;
	}

	public JTextField getNomeTextField() {
		if (this.nomeTextField == null) {
			this.nomeTextField = new JTextField();
			this.nomeTextField.setColumns(10);
			this.nomeTextField.setBounds(130, 55, 130, 20);
		}
		return nomeTextField;
	}

	public JLabel getNomeLabel() {
		if (this.nomeLabel == null) {
			this.nomeLabel = new JLabel("Nome: ");
			this.nomeLabel.setBounds(20, 55, 50, 20);
		}
		return nomeLabel;
	}

	public JTextField getContaTextField() {
		if (this.contaTextField == null) {
			this.contaTextField = new JTextField();
			this.contaTextField.setColumns(10);
			this.contaTextField.setBounds(130, 90, 100, 20);
		}
		return contaTextField;
	}

	public JLabel getContaLabel() {
		if (this.contaLabel == null) {
			this.contaLabel = new JLabel("Número da conta: ");
			this.contaLabel.setBounds(20, 90, 120, 20);
		}
		return contaLabel;
	}

	public JButton getSubmeterCadastroButton() {
		if (this.submeterCadastroButton == null) {
			this.submeterCadastroButton = new JButton("Cadastrar");
			this.submeterCadastroButton.setBounds(10, 200, 100, 40);
			this.submeterCadastroButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						String cpf = getCpfTextField().getText();
						verificarCampoVazio(cpf, "CPF");
						String nome = getNomeTextField().getText();
						verificarCampoVazio(nome, "Nome");
						String numeroConta = getContaTextField().getText();
						verificarCampoVazio(numeroConta, "Número da Conta");

						if (GerenciaMenuFrame.getBanco().procurarCliente(cpf) != null)
							throw new ClienteJaCadastradoException();
						if (GerenciaMenuFrame.getBanco().procurarConta(
								numeroConta) != null)
							throw new ContaJaCadastradaException();

						tipoConta.setNumero(numeroConta);
						tipoConta.setSaldo(0);
						ArrayList<String> contas = new ArrayList<String>();
						contas.add(numeroConta);
						Cliente cliente = new Cliente(nome, cpf, contas);
						GerenciaMenuFrame.getBanco().cadastrarConta(tipoConta);
						GerenciaMenuFrame.getBanco().cadastrarCliente(cliente);
						JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
						esvaziarCampos();
						
					} catch (CampoVazioException | ClienteJaCadastradoException
							| ContaJaCadastradaException | RepositorioException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}

				}
			});
		}
		return submeterCadastroButton;
	}

	protected void verificarCampoVazio(String cpf, String valor)
			throws CampoVazioException {
		if (cpf.equals(""))
			throw new CampoVazioException(valor);

	}

	public JButton getApagarCamposButton() {
		if (this.apagarCamposButton == null) {
			this.apagarCamposButton = new JButton("Desfazer");
			this.apagarCamposButton.setBounds(122, 200, 100, 40);
			this.apagarCamposButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					esvaziarCampos();					
				}
			});
		}
		return apagarCamposButton;
	}

	public JButton getCancelarButton() {
		if (this.cancelarButton == null) {
			this.cancelarButton = new JButton("Cancelar");
			this.cancelarButton.setBounds(235, 200, 100, 40);
			this.cancelarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);			
				}
			});
		}
		return cancelarButton;
	}

	public ButtonGroup getContasButtonGroup() {
		if (this.contasButtonGroup == null) {
			this.contasButtonGroup = new ButtonGroup();
			this.contasButtonGroup.add(getTipoContaRadioButton());
			this.contasButtonGroup.add(getTipoPoupancaRadioButton());
			this.contasButtonGroup.add(getTipoEspecialRadioButton());
			this.contasButtonGroup.add(getTipoImpostoRadioButton());
		}
		return contasButtonGroup;
	}

	public JRadioButton getTipoContaRadioButton() {
		if (this.tipoContaRadioButton == null) {
			this.tipoContaRadioButton = new JRadioButton("Normal", true);
			this.tipoContaRadioButton.setBounds(15, 150, 70, 20);
			this.tipoContaRadioButton
					.addItemListener(new ContasRadioButtonHandler(new Conta()));
		}
		return tipoContaRadioButton;
	}

	public JRadioButton getTipoPoupancaRadioButton() {
		if (this.tipoPoupancaRadioButton == null) {
			this.tipoPoupancaRadioButton = new JRadioButton("Poupança", false);
			this.tipoPoupancaRadioButton.setBounds(90, 150, 90, 20);
			this.tipoPoupancaRadioButton
					.addItemListener(new ContasRadioButtonHandler(
							new Poupanca()));
		}
		return tipoPoupancaRadioButton;
	}

	public JRadioButton getTipoEspecialRadioButton() {
		if (this.tipoEspecialRadioButton == null) {
			this.tipoEspecialRadioButton = new JRadioButton("Especial", false);
			this.tipoEspecialRadioButton.setBounds(179, 150, 85, 20);
			this.tipoEspecialRadioButton
					.addItemListener(new ContasRadioButtonHandler(
							new ContaEspecial()));
		}
		return tipoEspecialRadioButton;
	}

	public JRadioButton getTipoImpostoRadioButton() {
		if (this.tipoImpostoRadioButton == null) {
			this.tipoImpostoRadioButton = new JRadioButton("Imposto", false);
			this.tipoImpostoRadioButton.setBounds(260, 150, 100, 20);
			this.tipoImpostoRadioButton
					.addItemListener(new ContasRadioButtonHandler(
							new ContaImposto()));
		}
		return tipoImpostoRadioButton;
	}

	public JLabel getContasLabel() {
		if (this.contasLabel == null) {
			this.contasLabel = new JLabel("Tipo da Conta:");
			this.contasLabel.setBounds(15, 120, 150, 20);
		}
		return contasLabel;
	}

	public void setContasLabel(JLabel contasLabel) {
		this.contasLabel = contasLabel;
	}

	public class ContasRadioButtonHandler implements ItemListener {

		private ContaAbstrata conta;

		public ContasRadioButtonHandler(ContaAbstrata conta) {
			this.conta = conta;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (conta instanceof Conta)
				tipoConta = new Conta();
			else if (conta instanceof Poupanca)
				tipoConta = new Poupanca();
			else if (conta instanceof ContaEspecial)
				tipoConta = new ContaEspecial();
			else
				tipoConta = new ContaImposto();
		}
	}
	
	private void esvaziarCampos(){
		this.getNomeTextField().setText("");
		this.getCpfTextField().setText("");
		this.getContaTextField().setText("");
	}

}

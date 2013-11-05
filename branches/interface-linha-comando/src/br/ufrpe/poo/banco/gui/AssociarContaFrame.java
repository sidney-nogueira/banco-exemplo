package br.ufrpe.poo.banco.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import br.ufrpe.poo.banco.exceptions.ClienteJaPossuiContaException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.exceptions.ContaJaAssociadaException;
import br.ufrpe.poo.banco.exceptions.ContaJaCadastradaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaEspecial;
import br.ufrpe.poo.banco.negocio.ContaImposto;
import br.ufrpe.poo.banco.negocio.Poupanca;

public class AssociarContaFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static AssociarContaFrame instance;
	private JPanel panel;

	private JLabel cpfClienteLabel;
	private JLabel numeroContaClienteLabel;

	private JTextField cpfClienteTextField;
	private JTextField numeroContaTextField;

	private JButton associarContaButton;
	private JButton cancelarButton;

	private ButtonGroup contasButtonGroup;
	private JRadioButton tipoContaRadioButton;
	private JRadioButton tipoPoupancaRadioButton;
	private JRadioButton tipoEspecialRadioButton;
	private JRadioButton tipoImpostoRadioButton;
	private JLabel contasLabel;

	private ContaAbstrata tipoConta;

	public static AssociarContaFrame getInstance() {
		if (AssociarContaFrame.instance == null) {
			AssociarContaFrame.instance = new AssociarContaFrame();
		}
		return AssociarContaFrame.instance;
	}

	public AssociarContaFrame() {
		super();
		initialiaze();
	}

	private void initialiaze() {
		this.setTitle("Associar Conta"); // título
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
			this.panel.add(getCpfClienteLabel());
			this.panel.add(getCpfClienteTextField());
			this.panel.add(getNumeroContaClienteLabel());
			this.panel.add(getNumeroContaTextField());
			this.panel.add(getAssociarContaButton());
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

	public JLabel getCpfClienteLabel() {
		if (this.cpfClienteLabel == null) {
			this.cpfClienteLabel = new JLabel("CPF: ");
			this.cpfClienteLabel.setBounds(20, 20, 30, 20);
		}
		return cpfClienteLabel;
	}

	public JLabel getNumeroContaClienteLabel() {
		if (this.numeroContaClienteLabel == null) {
			this.numeroContaClienteLabel = new JLabel("Nova Conta: ");
			this.numeroContaClienteLabel.setBounds(20, 55, 100, 20);
		}
		return numeroContaClienteLabel;
	}

	public JTextField getCpfClienteTextField() {
		if (this.cpfClienteTextField == null) {
			this.cpfClienteTextField = new JTextField();
			this.cpfClienteTextField.setColumns(10);
			this.cpfClienteTextField.setBounds(100, 20, 100, 20);
		}
		return cpfClienteTextField;
	}

	public JTextField getNumeroContaTextField() {
		if (this.numeroContaTextField == null) {
			this.numeroContaTextField = new JTextField();
			this.numeroContaTextField.setColumns(10);
			this.numeroContaTextField.setBounds(100, 55, 100, 20);
		}
		return numeroContaTextField;
	}

	public JButton getAssociarContaButton() {
		if (this.associarContaButton == null) {
			this.associarContaButton = new JButton();
			this.associarContaButton.setText("Associar");
			this.associarContaButton.setBounds(60, 200, 100, 40);
			this.associarContaButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						String cpf = getCpfClienteTextField().getText();
						if (cpf.equals(""))
							throw new CampoVazioException("CPF");

						String numeroConta = getNumeroContaTextField()
								.getText();
						if (numeroConta.equals(""))
							throw new CampoVazioException("número da conta");

						GerenciaMenuFrame.getBanco().associarConta(cpf,
								numeroConta);

						tipoConta.setNumero(numeroConta);
						tipoConta.setSaldo(0);

						GerenciaMenuFrame.getBanco().cadastrarConta(tipoConta);

						JOptionPane.showMessageDialog(null,
								"Conta associada ao cliente com sucesso!");
						esvaziarCampos();
					} catch (CampoVazioException
							| ClienteNaoCadastradoException
							| ClienteJaPossuiContaException
							| ContaJaAssociadaException | RepositorioException
							| ContaJaCadastradaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
						esvaziarCampos();
					}

				}
			});
		}
		return associarContaButton;
	}

	public JButton getCancelarButton() {
		if (this.cancelarButton == null) {
			this.cancelarButton = new JButton("Cancelar");
			this.cancelarButton.setBounds(180, 200, 100, 40);
			this.cancelarButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					esvaziarCampos();
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

	public class ContasRadioButtonHandler implements ItemListener {

		private ContaAbstrata conta;

		public ContasRadioButtonHandler(ContaAbstrata conta) {
			this.conta = conta;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (conta instanceof Conta) {
				if (conta instanceof Poupanca)
					tipoConta = new Poupanca();
				else if (conta instanceof ContaEspecial)
					tipoConta = new ContaEspecial();
				else
					tipoConta = new Conta();
			} else
				tipoConta = new ContaImposto();
		}
	}

	private void esvaziarCampos() {
		getCpfClienteTextField().setText("");
		getNumeroContaTextField().setText("");
	}
}

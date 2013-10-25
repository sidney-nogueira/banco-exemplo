package br.ufrpe.poo.banco.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.poo.banco.exceptions.ClienteJaCadastradoException;
import br.ufrpe.poo.banco.exceptions.InicializacaoSistemaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Banco;
import br.ufrpe.poo.banco.negocio.Cliente;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaAbstrata;
import br.ufrpe.poo.banco.negocio.ContaEspecial;
import br.ufrpe.poo.banco.negocio.ContaImposto;
import br.ufrpe.poo.banco.negocio.Poupanca;

public class CadastrarClienteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static CadastrarClienteFrame instance;
	
	private JPanel panel;

	private JTextField nomeClienteTextField;
	private JLabel nomeClienteLabel;
	private JTextField cpfClienteTextField;
	private JLabel cpfClienteLabel;
	private JTextField numeroContaTextField;
	private JLabel numeroContaLabel;

	private ButtonGroup contasButtonGroup;
	private JLabel contasButtonGroupLabel;

	private JRadioButton contaRadioButton;
	private JRadioButton poupancaRadioButton;
	private JRadioButton impostoRadioButton;
	private JRadioButton especialRadioButton;

	private JTabbedPane clienteTabbedPane;
	private JPanel clienteTabbedPanePanel;

	private JButton submeterClienteButton;
	private JButton cancelarClienteButton;
	
	private ContaAbstrata contaSelecionada;
	
	public static CadastrarClienteFrame getInstance() {
		if (CadastrarClienteFrame.instance == null) {
			CadastrarClienteFrame.instance = new CadastrarClienteFrame();
		}
		return CadastrarClienteFrame.instance;
	}

	public CadastrarClienteFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setTitle("Cadastrar Cliente");
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 293);
		this.setLocationRelativeTo(null);
		this.setContentPane(getPanel());

	}

	private JPanel getPanel() {
		if (this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(null);
			this.panel.setBorder(new EmptyBorder(1, 5, 5, 5));
			this.panel.setLayout(new BorderLayout(0, 0));
			this.panel.add(getClienteTabbedPane());
		}
		return this.panel;
	}
	
	public JTextField getCpfClienteTextField() {
		if (this.cpfClienteTextField == null) {
			this.cpfClienteTextField = new JTextField();
			this.cpfClienteTextField.setColumns(10);
			this.cpfClienteTextField.setBounds(70, 15, 250, 20);
		}
		return cpfClienteTextField;
	}

	public JLabel getCpfClienteLabel() {
		if (this.cpfClienteLabel == null) {
			this.cpfClienteLabel = new JLabel("CPF: ");
			this.cpfClienteLabel.setBounds(30, 15, 50, 15);
		}
		return cpfClienteLabel;
	}
	
	public JTextField getNomeClienteTextField() {
		if (this.nomeClienteTextField == null) {
			this.nomeClienteTextField = new JTextField();
			this.nomeClienteTextField.setColumns(10);
			this.nomeClienteTextField.setBounds(70, 40, 250, 20);
		}
		return nomeClienteTextField;
	}

	public JLabel getNomeClienteLabel() {
		if (this.nomeClienteLabel == null) {
			this.nomeClienteLabel = new JLabel("Nome: ");
			this.nomeClienteLabel.setBounds(30, 40, 50, 15);
		}
		return nomeClienteLabel;
	}

	public JTextField getNumeroContaTextField() {
		if (this.numeroContaTextField == null) {
			this.numeroContaTextField = new JTextField();
			this.numeroContaTextField.setColumns(10);
			this.numeroContaTextField.setBounds(135, 65, 185, 20);
		}
		return numeroContaTextField;
	}

	public JLabel getNumeroContaLabel() {
		if (this.numeroContaLabel == null) {
			this.numeroContaLabel = new JLabel("Número da conta: ");
			this.numeroContaLabel.setBounds(30, 65, 120, 15);
		}
		return numeroContaLabel;
	}

	public JTabbedPane getClienteTabbedPane() {
		if (this.clienteTabbedPane == null) {
			this.clienteTabbedPane = new JTabbedPane(JTabbedPane.TOP);
			this.clienteTabbedPane.addTab("Dados do cliente", null,
					getClienteTabbedPanePanel(), null);
		}
		return this.clienteTabbedPane;
	}

	public JPanel getClienteTabbedPanePanel() {
		if (this.clienteTabbedPanePanel == null) {
			this.clienteTabbedPanePanel = new JPanel();
			this.clienteTabbedPanePanel.setLayout(null);
			this.clienteTabbedPanePanel.add(getNomeClienteLabel());
			this.clienteTabbedPanePanel.add(getNomeClienteTextField());
			this.clienteTabbedPanePanel.add(getCpfClienteLabel());
			this.clienteTabbedPanePanel.add(getCpfClienteTextField());
			this.clienteTabbedPanePanel.add(getNumeroContaLabel());
			this.clienteTabbedPanePanel.add(getNumeroContaTextField());
			this.getContasButtonGroup();
			this.clienteTabbedPanePanel.add(getContasButtonGroupLabel());
			this.clienteTabbedPanePanel.add(getContaRadioButton());
			this.clienteTabbedPanePanel.add(getPoupancaRadioButton());
			this.clienteTabbedPanePanel.add(getEspecialRadioButton());
			this.clienteTabbedPanePanel.add(getImpostoRadioButton());
			this.clienteTabbedPanePanel.add(getSubmeterClienteButton());
			this.clienteTabbedPanePanel.add(getCancelarClienteButton());

		}
		return clienteTabbedPanePanel;
	}

	public JRadioButton getContaRadioButton() {
		if (this.contaRadioButton == null) {
			this.contaRadioButton = new JRadioButton("Conta", true);
			this.contaRadioButton.setBounds(30, 115, 100, 20);
			Conta contaNormal = new Conta();
			this.contaRadioButton.addItemListener(new TipoContaRadioButtonHandler(contaNormal));
		}
		return contaRadioButton;
	}

	public JRadioButton getPoupancaRadioButton() {
		if (this.poupancaRadioButton == null) {
			this.poupancaRadioButton = new JRadioButton("Poupança", false);
			this.poupancaRadioButton.setBounds(30, 135, 100, 20);
			Poupanca poupanca = new Poupanca();
			this.contaRadioButton.addItemListener(new TipoContaRadioButtonHandler(poupanca));
		}
		return poupancaRadioButton;
	}

	public JRadioButton getImpostoRadioButton() {
		if (this.impostoRadioButton == null) {
			this.impostoRadioButton = new JRadioButton("Conta Imposto", false);
			ContaImposto contaImposto = new ContaImposto();
			this.contaRadioButton.addItemListener(new TipoContaRadioButtonHandler(contaImposto));
			this.impostoRadioButton.setBounds(170, 115, 150, 20);
		}
		return impostoRadioButton;
	}

	public JRadioButton getEspecialRadioButton() {
		if (this.especialRadioButton == null) {
			this.especialRadioButton = new JRadioButton("Conta Especial", false);
			this.especialRadioButton.setBounds(170, 135, 150, 20);
			ContaEspecial contaEspecial = new ContaEspecial();
			this.contaRadioButton.addItemListener(new TipoContaRadioButtonHandler(contaEspecial));
		}
		return especialRadioButton;
	}

	public ButtonGroup getContasButtonGroup() {
		if (this.contasButtonGroup == null) {
			this.contasButtonGroup = new ButtonGroup();
			this.contasButtonGroup.add(getContaRadioButton());
			this.contasButtonGroup.add(getPoupancaRadioButton());
			this.contasButtonGroup.add(getEspecialRadioButton());
			this.contasButtonGroup.add(getImpostoRadioButton());
		}
		return contasButtonGroup;
	}

	public JLabel getContasButtonGroupLabel() {
		if (this.contasButtonGroupLabel == null) {
			this.contasButtonGroupLabel = new JLabel("Tipo de Conta:");
			this.contasButtonGroupLabel.setBounds(30, 90, 150, 20);
		}
		return contasButtonGroupLabel;
	}

	public JButton getSubmeterClienteButton() {
		if (this.submeterClienteButton == null) {
			this.submeterClienteButton = new JButton("Submeter");
			this.submeterClienteButton.setBounds(110, 190, 100, 30);
			this.submeterClienteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Cliente cliente = new Cliente("joao", "123", new ArrayList<String>());
						GerenciaMenuFrame.banco.cadastrarCliente(cliente);
					} catch (RepositorioException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClienteJaCadastradoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return submeterClienteButton;

	}

	public JButton getCancelarClienteButton() {
		if (this.cancelarClienteButton == null) {
			this.cancelarClienteButton = new JButton("Cancelar");
			this.cancelarClienteButton.setBounds(220, 190, 100, 30);
		}
		return cancelarClienteButton;
	}

	public void setContaSelecionada(ContaAbstrata contaSelecionada) {
		String numero = numeroContaTextField.getText();
		if(contaSelecionada instanceof Conta)
			this.contaSelecionada = new Conta(numero, 0);
		else if(contaSelecionada instanceof Poupanca)
			this.contaSelecionada = new Poupanca(numero, 0);
		else if(contaSelecionada instanceof ContaEspecial)
			this.contaSelecionada = new ContaEspecial(numero, 0, 100);
		else
			this.contaSelecionada = new ContaImposto(numero, 0);
	}
	
	private class TipoContaRadioButtonHandler implements ItemListener{
		
		private ContaAbstrata contaSelecionada;
		
		public TipoContaRadioButtonHandler(ContaAbstrata contaSelecionada){
			this.contaSelecionada = contaSelecionada;
		}
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			setContaSelecionada(this.contaSelecionada);
		}
		
	}

}

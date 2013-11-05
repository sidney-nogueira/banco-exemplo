package br.ufrpe.poo.banco.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.ufrpe.poo.banco.exceptions.CampoVazioException;
import br.ufrpe.poo.banco.exceptions.ClienteNaoCadastradoException;
import br.ufrpe.poo.banco.negocio.Cliente;

public class ConsultarClienteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static ConsultarClienteFrame instance;

	private JPanel panel;

	private JTextField cpfClienteTextField;
	private JLabel cpfClienteLabel;

	private JButton procurarClienteButton;
	private JButton cancelarButton;

	private JTextArea formularioDadosClienteTextArea;

	private JScrollPane formularioDadosClienteScrollPane;

	private String dados;

	public static ConsultarClienteFrame getInstance() {
		if (ConsultarClienteFrame.instance == null) {
			ConsultarClienteFrame.instance = new ConsultarClienteFrame();
		}
		return ConsultarClienteFrame.instance;
	}

	public ConsultarClienteFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setTitle("Consultar cliente"); // título
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
			this.panel.add(getCpfClienteTextField());
			this.panel.add(getCpfClienteLabel());
			this.panel.add(getFormularioDadosClienteScrollPane());
			this.panel.add(getProcurarClienteButton());
			this.panel.add(getCancelarButton());
		}
		return panel;
	}

	public JTextField getCpfClienteTextField() {
		if (this.cpfClienteTextField == null) {
			this.cpfClienteTextField = new JTextField();
			this.cpfClienteTextField.setColumns(10);
			this.cpfClienteTextField.setBounds(60, 180, 120, 20);
		}
		return cpfClienteTextField;
	}

	public JLabel getCpfClienteLabel() {
		if (this.cpfClienteLabel == null) {
			this.cpfClienteLabel = new JLabel("CPF:");
			this.cpfClienteLabel.setBounds(20, 180, 50, 20);
		}
		return cpfClienteLabel;
	}

	public JButton getProcurarClienteButton() {
		if (this.procurarClienteButton == null) {
			this.procurarClienteButton = new JButton("Procurar");
			this.procurarClienteButton.setBounds(190, 175, 100, 30);
			this.procurarClienteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						getFormularioDadosClienteTextArea().setText(
								"Dados:\n\n");
						dados = "";
						
						String cpf = getCpfClienteTextField().getText();
						verificarErroCampoVazio(cpf, "cpf");

						Cliente achouCliente = GerenciaMenuFrame.getBanco()
								.procurarCliente(cpf);

						if (achouCliente == null)
							throw new ClienteNaoCadastradoException();

						String nome = achouCliente.getNome();
						ArrayList<String> contas = new ArrayList<String>();
						contas = achouCliente.getContas();

						dados = "CPF: " + cpf + "\nNome: " + nome
								+ "\nContas: " + contas;

						getFormularioDadosClienteTextArea().append(dados);

					} catch (CampoVazioException
							| ClienteNaoCadastradoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
						esvaziarCampos();
					}

				}
			});
		}
		return procurarClienteButton;
	}

	public JButton getCancelarButton() {
		if (this.cancelarButton == null) {
			this.cancelarButton = new JButton("Cancelar");
			this.cancelarButton.setBounds(115, 230, 100, 30);
			this.cancelarButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					esvaziarCampos();
					dados = "";
					getFormularioDadosClienteTextArea().setText("Dados:\n\n");
					instance.setVisible(false);
				}
			});
		}
		return cancelarButton;
	}

	public JTextArea getFormularioDadosClienteTextArea() {
		if (this.formularioDadosClienteTextArea == null) {
			this.formularioDadosClienteTextArea = new JTextArea("Dados:\n\n");
			this.formularioDadosClienteTextArea.setBounds(50, 10, 250, 150);
			this.formularioDadosClienteTextArea.setEditable(false);
		}
		return formularioDadosClienteTextArea;
	}

	public JScrollPane getFormularioDadosClienteScrollPane() {
		if (this.formularioDadosClienteScrollPane == null) {
			this.formularioDadosClienteScrollPane = new JScrollPane(
					getFormularioDadosClienteTextArea());
			this.formularioDadosClienteScrollPane.setBounds(50, 10, 250, 150);
		}
		return formularioDadosClienteScrollPane;
	}

	private void esvaziarCampos() {
		getCpfClienteTextField().setText("");
	}

	private void verificarErroCampoVazio(String campo, String valor)
			throws CampoVazioException {
		if (campo.equals(""))
			throw new CampoVazioException(valor);
	}

}

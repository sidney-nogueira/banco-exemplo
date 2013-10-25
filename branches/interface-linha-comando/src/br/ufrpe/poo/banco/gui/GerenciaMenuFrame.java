package br.ufrpe.poo.banco.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import br.ufrpe.poo.banco.exceptions.InicializacaoSistemaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Banco;

public class GerenciaMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static Banco banco;
	
	private JPanel panel;
	private JButton casdastrarClienteButton;
	private JButton associarContaClienteButton;
	private JButton removerClienteButton;
	private JButton atualizarClienteButton;
	private JButton removerContaClienteButton;
	private JButton consultarClienteButton;
	private JMenuBar gerenciaMenuMenuBar;

	private static CadastrarClienteFrame cadastrarClienteFrame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				GerenciaMenuFrame frameGerencia = new GerenciaMenuFrame();
				frameGerencia.setVisible(true);
			}
		});
	}

	public GerenciaMenuFrame() {
		super();
		try{
			banco = Banco.getInstance();
		}catch(InicializacaoSistemaException | RepositorioException e){
			erroInicializacaoSistema(e.getMessage());
		}
		initialize();	
	}

	private void erroInicializacaoSistema(String message) {
		JOptionPane.showMessageDialog(this, message);
		System.exit(0);
	}

	private void initialize() {
		this.setTitle("Gerenciamento do Banco");
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 293);
		this.setLocationRelativeTo(null);
		this.setContentPane(getPanel());
	}

	private JPanel getPanel() {
		if (this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(null);
			this.panel.add(getCasdastrarClienteButton());
			this.panel.add(getAssociarContaClienteButton());
			this.panel.add(getAtualizarClienteButton());
			this.panel.add(getConsultarClienteButton());
			this.panel.add(getRemoverClienteButton());
			this.panel.add(getRemoverContaClienteButton());
			this.panel.add(getGerenciaMenuMenuBar());
		}
		return this.panel;
	}

	public JButton getCasdastrarClienteButton() {
		if (this.casdastrarClienteButton == null) {
			this.casdastrarClienteButton = new JButton();
			this.casdastrarClienteButton.setText("Cadastrar Cliente");
			this.casdastrarClienteButton.setBounds(0, 59, 444, 32);
			this.casdastrarClienteButton
					.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							cadastrarClienteFrame = CadastrarClienteFrame
									.getInstance();
							cadastrarClienteFrame.setVisible(true);
						}
					});
		}
		return casdastrarClienteButton;
	}

	public JButton getAssociarContaClienteButton() {
		if (this.associarContaClienteButton == null) {
			this.associarContaClienteButton = new JButton();
			this.associarContaClienteButton
					.setText("Associar Conta ao Cliente");
			this.associarContaClienteButton.setBounds(0, 93, 444, 32);
		}
		return associarContaClienteButton;
	}

	public JButton getAtualizarClienteButton() {
		if (this.atualizarClienteButton == null) {
			this.atualizarClienteButton = new JButton();
			this.atualizarClienteButton.setText("Atualizar Cliente");
			this.atualizarClienteButton.setBounds(0, 127, 444, 32);
		}
		return atualizarClienteButton;
	}

	public JButton getConsultarClienteButton() {
		if (this.consultarClienteButton == null) {
			this.consultarClienteButton = new JButton();
			this.consultarClienteButton.setText("Consultar Cliente");
			this.consultarClienteButton.setBounds(0, 161, 444, 32);
		}
		return consultarClienteButton;
	}

	public JButton getRemoverClienteButton() {
		if (this.removerClienteButton == null) {
			this.removerClienteButton = new JButton();
			this.removerClienteButton.setText("Remover Cliente");
			this.removerClienteButton.setBounds(0, 195, 444, 32);
		}
		return removerClienteButton;
	}

	public JButton getRemoverContaClienteButton() {
		if (this.removerContaClienteButton == null) {
			this.removerContaClienteButton = new JButton();
			this.removerContaClienteButton.setText("Remover Conta");
			this.removerContaClienteButton.setBounds(0, 229, 444, 32);
		}
		return removerContaClienteButton;
	}

	public JMenuBar getGerenciaMenuMenuBar() {
		if (this.gerenciaMenuMenuBar == null) {
			this.gerenciaMenuMenuBar = new JMenuBar();
			this.gerenciaMenuMenuBar.setBounds(0, 0, 444, 21);
		}
		return gerenciaMenuMenuBar;
	}

}

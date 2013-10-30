package br.ufrpe.poo.banco.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import br.ufrpe.poo.banco.exceptions.InicializacaoSistemaException;
import br.ufrpe.poo.banco.exceptions.RepositorioException;
import br.ufrpe.poo.banco.negocio.Banco;

public class GerenciaMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static Banco banco;
	
	private CadastroFrame cadastroFrame;

	private JPanel panel = null;

	private JButton clientesButton = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					GerenciaMenuFrame bancoFrame = new GerenciaMenuFrame();
					bancoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GerenciaMenuFrame() {
		super();
		initialize();

		try {
			GerenciaMenuFrame.banco = Banco.getInstance();
		} catch (InicializacaoSistemaException | RepositorioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
			System.exit(0);
		}

	}

	private void initialize() {
		this.setTitle("Gerência Banco");
		this.setResizable(false); 
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(0, 0, 350, 250);
		this.setLocationRelativeTo(null);
		this.setContentPane(getPanel());
	}

	private JPanel getPanel() {
		if (this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(null);
			this.panel.add(getClientesButton());

		}
		return this.panel;
	}

	private JButton getClientesButton() {
		if (this.clientesButton == null) {
			this.clientesButton = new JButton();
			this.clientesButton.setText("Cadastrar");
			this.clientesButton.setBounds(0, 0, 180, 65);
			this.clientesButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					cadastroFrame = CadastroFrame.getInstance();
					cadastroFrame.setVisible(true);
				}
			});
		}
		return clientesButton;
	}

	public static Banco getBanco() {
		return GerenciaMenuFrame.banco;
	}
}

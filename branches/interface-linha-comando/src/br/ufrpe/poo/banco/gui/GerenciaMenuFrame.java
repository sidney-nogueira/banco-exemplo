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

	private static Banco banco;
	
	private CadastroFrame cadastroFrame;
	private AssociarContaFrame associarContaFrame;
	private ConsultarClienteFrame consultarClienteFrame;
	
	private JPanel panel;

	private JButton cadastrarClienteButton ;
	private JButton associarContaButton;
	private JButton consultarClienteButton;
	private JButton atualizarClienteButton;
	private JButton removerClienteButton;
	private JButton removerContaButton;
	
	private JMenuBar gerenciaMenuBar;

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
		this.setBounds(0, 0, 358, 260);
		this.setLocationRelativeTo(null);
		this.setContentPane(getPanel());
	}

	private JPanel getPanel() {
		if (this.panel == null) {
			this.panel = new JPanel();
			this.panel.setLayout(null);
			this.panel.add(getCadastrarClienteButton());
			this.panel.add(getAssociarContaButton());
			this.panel.add(getConsultarClienteButton());
			this.panel.add(getAtualizarClienteButton());
			this.panel.add(getRemoverClienteButton());
			this.panel.add(getRemoverContaButton());
		}
		return this.panel;
	}

	private JButton getCadastrarClienteButton() {
		if (this.cadastrarClienteButton == null) {
			this.cadastrarClienteButton = new JButton();
			this.cadastrarClienteButton.setText("Cadastrar");
			this.cadastrarClienteButton.setBounds(0, 31, 175, 65);
			this.cadastrarClienteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					cadastroFrame = CadastroFrame.getInstance();
					cadastroFrame.setVisible(true);
				}
			});
		}
		return cadastrarClienteButton;
	}

	public JButton getAssociarContaButton() {
		if(this.associarContaButton == null){
			this.associarContaButton = new JButton();
			this.associarContaButton.setText("Associar Conta");
			this.associarContaButton.setBounds(177, 31, 175, 65);
			this.associarContaButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					associarContaFrame = AssociarContaFrame.getInstance();
					associarContaFrame.setVisible(true);
				}
			});
		}
		return associarContaButton;
	}

	public JButton getConsultarClienteButton() {
		if(this.consultarClienteButton == null){
			this.consultarClienteButton = new JButton();
			this.consultarClienteButton.setText("Consultar Cliente");
			this.consultarClienteButton.setBounds(0, 98, 175, 65);
			this.consultarClienteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					consultarClienteFrame = ConsultarClienteFrame.getInstance();
					consultarClienteFrame.setVisible(true);
					
				}
			});
		}
		return consultarClienteButton;
	}

	public JButton getAtualizarClienteButton() {
		if(this.atualizarClienteButton == null){
			this.atualizarClienteButton = new JButton();
			this.atualizarClienteButton.setText("Atualizar Cliente");
			this.atualizarClienteButton.setBounds(177, 98, 175, 65);
		}
		return atualizarClienteButton;
	}

	public JButton getRemoverClienteButton() {
		if(this.removerClienteButton == null){
			this.removerClienteButton = new JButton();
			this.removerClienteButton.setText("Remover Cliente");
			this.removerClienteButton.setBounds(0, 165, 175, 65);
		}
		return removerClienteButton;
	}


	public JButton getRemoverContaButton() {
		if(this.removerContaButton == null){
			this.removerContaButton = new JButton();
			this.removerContaButton.setText("Remover Conta");
			this.removerContaButton.setBounds(177, 165, 175, 65);
		}
		return removerContaButton;
	}

	public JMenuBar getGerenciaMenuBar() {
		if(this.gerenciaMenuBar == null){
			this.gerenciaMenuBar = new JMenuBar();
		}
		return gerenciaMenuBar;
	}

	public static Banco getBanco() {
		return GerenciaMenuFrame.banco;
	}
}

package br.ufrpe.poo.banco.guiWindowsBuilder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import br.ufrpe.poo.banco.dados.RepositorioException;
import br.ufrpe.poo.banco.negocio.Banco;
import br.ufrpe.poo.banco.negocio.Conta;
import br.ufrpe.poo.banco.negocio.ContaJaCadastradaException;
import br.ufrpe.poo.banco.negocio.ContaNaoEncontradaException;
import br.ufrpe.poo.banco.negocio.IBanco;
import br.ufrpe.poo.banco.negocio.InicializacaoSistemaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class FrameA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IBanco fachada;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameA frame = new FrameA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws InicializacaoSistemaException 
	 */
	public FrameA() throws InicializacaoSistemaException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(212, 5, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(212, 51, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNumeroConta = new JLabel("Numero Conta");
		lblNumeroConta.setBounds(105, 8, 97, 14);
		contentPane.add(lblNumeroConta);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(149, 54, 53, 14);
		contentPane.add(lblValor);

		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				cadastrar();				
			}
		});
		btnCadastro.setBounds(48, 112, 89, 23);
		contentPane.add(btnCadastro);

		JButton btnSaldo = new JButton("Saldo");
		btnSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saldo();
			}
		});
		btnSaldo.setBounds(296, 112, 89, 23);
		contentPane.add(btnSaldo);

		JButton btnNewButton = new JButton("Creditar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
					creditar();				
			}
		});
		btnNewButton.setBounds(172, 112, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//dispose();
				System.exit(0);
			}
		});
		btnFechar.setBounds(172, 172, 89, 23);
		contentPane.add(btnFechar);

		try {
			fachada = Banco.getInstance();
		} catch (InicializacaoSistemaException e) {
			e.printStackTrace();
		}	
	}
	
	public void cadastrar(){
		try{
			String nome = textField.getText();
			textField.setText("");
			double valor = Double.parseDouble(textField_1.getText());
			textField_1.setText("");
			Conta conta = new Conta(nome, valor);
			this.fachada.cadastrar(conta);			
			JOptionPane.showMessageDialog(this, "Conta cadastrada!");
			
		}catch(RepositorioException e){
			e.printStackTrace();
		}catch(ContaJaCadastradaException e){
			JOptionPane.showMessageDialog(this,e.getMessage());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this,"Digite valores validos!");
		}
	}
	public void creditar(){
		try{
			String numero = textField.getText();
			textField.setText("");
			double valor = Double.parseDouble(textField_1.getText());	
			textField_1.setText("");
			this.fachada.creditar(numero, valor);
			JOptionPane.showMessageDialog(this, "Credito confirmado!");			
		}catch(ContaNaoEncontradaException e){
			JOptionPane.showMessageDialog(this,e.getMessage());
		}catch(RepositorioException e){
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}
	
	public void saldo(){
		try{
			String numero = textField.getText();		
			textField.setText("");
			double saldo = this.fachada.getSaldo(numero);
			textField_1.setText("");
			JOptionPane.showMessageDialog(this, "Saldo : "+saldo);
		}catch(RepositorioException e){
			e.printStackTrace();
		}catch(ContaNaoEncontradaException e){
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}	
}

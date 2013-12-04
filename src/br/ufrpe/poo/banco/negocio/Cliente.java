package br.ufrpe.poo.banco.negocio;

public class Cliente {

	public static final int MAX_CONTAS = 2;

	private String rg;
	private ContaAbstrata[] contas;
	private int numContas;

	/**
	 * Constroi um cliente a partir do rg fornecido.
	 * 
	 * @param rg
	 */
	public Cliente(String rg) {
		super();
		this.rg = rg;
		this.contas = new ContaAbstrata[MAX_CONTAS];
		this.numContas = 0;
	}

	/**
	 * Retorna a posicao onde a conta foi encontrada no array de contas.
	 * 
	 * @param conta
	 *            conta a ser procurada.
	 * @return posicao da conta no array. Valor retornado igual a numConta caso
	 *         conta nao exista.
	 */
	public int posicaoConta(ContaAbstrata conta) {
		int i;
		for (i = 0; i < this.numContas; i++) {
			if (this.contas[i].equals(conta.getNumero())) {
				break;
			}
		}
		return i;
	}

	/**
	 * Indica se uma conta esta vinculada ao cliente.
	 * @param conta conta a ser procurada.
	 * @return se conta esta vinculada ao cliente.
	 */
	public boolean possuiConta(ContaAbstrata conta) {
		if (posicaoConta(conta) < this.contas.length)
			return true;
		else
			return false;
	}

	/**
	 * Vincula nova conta ao cliente. Maximo de duas.
	 * @param conta conta a ser vinculada.
	 */
	public void adicionarConta(ContaAbstrata conta) {
		if (possuiConta(conta) && this.numContas < MAX_CONTAS) {
			this.contas[this.numContas++] = conta;
		}
	}

	/**
	 * Retorna o rg do cliente.
	 * @return rg do cliente.
	 */
	public String getRg() {
		return rg;
	}

	/**
	 * Retorna a quantidade de contas vinculadas ao cliente.
	 * @return quantidade de contas.
	 */
	public int quantidadeContas() {
		return this.numContas;
	}

	/**
	 * Retorna as contas vinculadas ao cliente na ordem em que foram adicionadas.
	 * @return contas do cliente.
	 */
	public ContaAbstrata[] getContas() {
		ContaAbstrata[] contas = new ContaAbstrata[this.numContas];
		for (int i = 0; i < this.contas.length; i++) {
			contas[i] = this.contas[i];
		}
		return contas;
	}

	/**
	 * Desvincula as contas do cliente caso as contas tenham saldo 0. Caso
	 * contrario nenhuma mudanca acontece.
	 * 
	 * @return se as contas foram fechadas
	 */
	public boolean desvinculaContas() {
		boolean vazias = true;
		for (int i = 0; i < this.numContas && vazias; i++) {
			if (contas[i].getSaldo() != 0) {
				vazias = false;
			}
		}
		if (vazias) {
			contas = new ContaAbstrata[MAX_CONTAS];
			this.numContas = 0;
		}
		return vazias;
	}

}

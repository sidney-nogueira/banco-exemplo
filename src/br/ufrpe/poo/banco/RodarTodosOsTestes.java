package br.ufrpe.poo.banco;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RodarTodosOsTestes {

	/**
	 * Roda todos os testes e mostra apenas as falhas
	 */
	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(TodosOsTestes.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

	}

}

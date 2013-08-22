package temp;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

public class MockingMinhaLista {

	public static void main(String[] args) {
		
		//cria um mock para lista ligada
		MinhaLista listaMock = mock(MinhaLista.class);
		
		//define comportamento quando o metodo e chamada no mock object
		when(listaMock.recuperar(0)).thenReturn("primeiro");
		
		//chamando metodo do mock
		System.out.println(listaMock.recuperar(0));
		
		//para outros valores nao foi definido retorno especifico, valor padra e retornado
		System.out.println(listaMock.recuperar(3));			

		//usando um matcher para qualquer valor inteiro
		when(listaMock.recuperar(anyInt())).thenReturn("elemento");
		
		//cuidado!!! e permitido passar parametros invalidos no objeto mock
		//voce e que tem que ter controle sobre os valores
		System.out.println(listaMock.adicionar("two"));
		
		// e possivel verificar quantas vezes o metodo de um mock foi utilizado
		// util quando mock e passado como parametro e pode ser chamado varias vezes
		verify(listaMock,times(3)).recuperar(anyInt());


	}

}

package cliente;

/**
 * Esta classe  tem o objetivo de estabelecer um conexao com o servidor por meio
 * de um ip ou local host sendo o cliente o mesmo que o servidor no mesmo computador,
 * no decorrer da classe caso entre em algum catch de erro e printado o erro encontrado 
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 * */

import java.io.*;
import java.net.*;

import comunicado.Comunicado;
import comunicado.Parceiro;
import comunicado.TratadoraDeComunicadoDeDesligamento;

public class Cliente extends Comunicado implements Serializable, Cloneable{

	public static final String HOST_PADRAO = "127.0.0.1";
	public static final int PORTA_PADRAO = 3000;

	public static void main(String[] args) throws Exception{

		if (args.length > 2) {
			System.err.println("Uso esperado: Java client [HOST][PORTA]");
			return;
		}

		 Socket conexao = null;

		try {
			String host = Cliente.HOST_PADRAO;
			int porta = Cliente.PORTA_PADRAO;

			if (args.length > 0)
				host = args[0];

			if (args.length == 2)
				porta = Integer.parseInt(args[1]);

			  conexao = new Socket (host,porta);
			// esse conexao socket é onde ocorre a conexão com servidor usando a maquina e a
			// porta

		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		ObjectOutputStream transmissor = null;

		try {
			 transmissor = 
			 new ObjectOutputStream(conexao.getOutputStream());
			 //Temos que verificar depois mais afundo.

		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		ObjectInputStream receptor = null;

		try {
			 receptor = new ObjectInputStream(conexao.getInputStream());

		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		 Parceiro servidor = null;
		// Conexao com a classe parceiro para fazer conexao direta com o servidor
		// instanciando
		try {
			 servidor = new Parceiro (conexao, receptor , transmissor);

			 //servidor.receba(new Teste());
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta");
			return;
		}

		 TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento =  null;
		 

		// Declaramos um objeto da tratadora de desligamento em seguida iremos
		// instanciar esse objeto

		try {
			tratadoraDeComunicadoDeDesligamento = new
			 TratadoraDeComunicadoDeDesligamento(servidor);

		} catch (Exception erro) {
		} // foi instanciado sem chance de erros

		// Logo abaixo iremos iniciar a execução da thread

		 //tratadoraDeComunicadoDeDesligamento.start();

		// no caso do Professor ele colocou um pedido para o servidor passando
		// os valores do cliente para calcular como

		/// servidor.receba (new PedidoDeOperacao (opcao,valor)); Opcao é o tipo do
		/// cálculo+ - e valor o valor númerico

		// No nosso caso vamos pensar em no momento que o cliente apertar no botão
		// salvar ja janela classe janela fazer um pedido de salvamento..
		
		try {
			new Janela(servidor);
		} catch (Exception erro) {
			System.err.println(erro);
		}
		
	}
}
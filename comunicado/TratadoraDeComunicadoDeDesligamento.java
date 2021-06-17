package comunicado;


/**
 * Esta classe representa um comunicado de desligamento ao cliente
 * esta classe represneta um metodod de thread que ficara todando
 * enquanto nao estiver desligado e o construtor esta classe herda 
 * diretamente de thread.
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 * */

import java.net.*;

public class TratadoraDeComunicadoDeDesligamento extends Thread {
	private Parceiro servidor;
	
	  /**
     * Este metodo representa a instancia de comunicado desligamaento do servidor
     * @param o objeto do parametro vem o parceiro servidor ao qual fez a conexao na main
     * para fazer o desligamento do cliente no servidor
     * @throws caso o servidor seja nulo o servidor objeto vindo pelo parametro e nulo entao
     * lacamos uma excecao ao cliente informando do erro porta invalida
     * */

	public TratadoraDeComunicadoDeDesligamento(Parceiro servidor) throws Exception {
		if (servidor == null)
			throw new Exception("Porta invalida");

		this.servidor = servidor;
	}
	
	/**
     * Este metodo representa um metodo de thread que ficara rodando
     * espiando quando o cliente desejar se descontar do servidor
     * este metodo ficara rodando enquanto o cliente nao chamar este metodo para
     * se desligar da conexao com o servidor um metodo aplicavel a thread.
     * */

	public void run() {
		for (;;) {
			try {
				if (this.servidor.espie() instanceof ComunicadoDeDesligamento) {
					System.out.println("\nO servidor vai ser desligado agora;");
					System.err.println("volte mais tarde!\n");
					System.exit(0);
				}

			} catch (Exception erro) {
				System.err.println("a");
			}
		}
	}
}
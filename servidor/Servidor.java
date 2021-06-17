package servidor;

/**
 * A classe servidor e a classe main ao qual aplicamos o servidor que foi
 * implementado nela verificamos se o vetor de argumento veio algo que foi passado
 * pelo usuario para estabelecer um tipo de porta diferente do pre estabelecido e o
 * metodo que ficara rodando enquanto o servidor estivel disponivel para seus usuarios fazer a conexao
 * nas demais funcionalidades.
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 */

import java.util.*;

import comunicado.ComunicadoDeDesligamento;
import comunicado.Parceiro;

public class Servidor {
	public static String PORTA_PADRAO = "3000";

	public static void main(String[] args) {
		if (args.length > 1) {
			System.err.println("Uso esperado: java Servidor [PORTA]\n");
			return;
		}

		String porta = Servidor.PORTA_PADRAO;

		if (args.length == 1)
			porta = args[0];

		ArrayList<Parceiro> usuarios = new ArrayList<Parceiro>();

		AceitadoraDeConexao aceitadoraDeConexao = null;
		try {
			aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
			aceitadoraDeConexao.start();
		} catch (Exception erro) {
			System.err.println("Escolha uma porta apropriada e liberada para uso!\n");
			return;
		}

		for (;;) {
			System.out.println("O servidor esta ativo! Para desativa-lo,");
			System.out.println("use o comando \"desativar\"\n");
			System.out.print("> ");

			String comando = null;
			try {
				comando = Teclado.getUmString();
			} catch (Exception erro) {
			}

			if (comando.toLowerCase().equals("desativar")) {
				synchronized (usuarios) {
					ComunicadoDeDesligamento comunicadoDeDesligamento = new ComunicadoDeDesligamento();

					for (Parceiro usuario : usuarios) {
						try {
							usuario.receba(comunicadoDeDesligamento);
							usuario.adeus();
						} catch (Exception erro) {
						}
					}
				}

				System.out.println("O servidor foi desativado!\n");
				System.exit(0);
			} else
				System.err.println("Comando invalido!\n");
		}
	}
}
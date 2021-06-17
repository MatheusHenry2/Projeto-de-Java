package servidor;

/**
 * Esta classe e a classe supervisora de conexao onde o nome
 * ja se diz ela supervisiona cada acao de cada usuario que esta conectado
 * no servidor que nos implementamos
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 */

import java.io.*;
import java.net.*;
import java.util.*;

import comunicado.Comunicado;
import comunicado.Desenho;
import comunicado.Lab;
import comunicado.Parceiro;
import comunicado.PedidoDeAbertura;
import comunicado.PedidoParaSair;
import comunicado.PedidoSalvamento;
import bd.daos.Labirintos;
import bd.dbo.Labirinto;

public class SupervisoraDeConexao extends Thread {
	// Logo abaixo tera alteração nesse valor do
	// private double valor=0;
	private Parceiro usuario;
	private Socket conexao;
	private ArrayList<Parceiro> usuarios;

	/**
	 * Este metodo representa a instancia onde o cliente e o servidor irao
	 * estabelecer uma conexoa via socket a classe vem com 2 parametros os usuario e
	 * a conexao que foi realizada
	 * 
	 * @param conexao
	 * @param usuarios
	 * @throws Exception quando algum parametro que vier nulo lancamos uma execao
	 *                   para o usuario
	 */

	public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> usuarios) throws Exception {
		if (conexao == null)
			throw new Exception("Conexao ausente");

		if (usuarios == null)
			throw new Exception("Usuarios ausentes");

		this.conexao = conexao;
		this.usuarios = usuarios;
	}

	/**
	 * Este metodo e um metodo de thread onde ficara rodando enquanto o servidor
	 * estiver ligado aceitando cada requisicao de entrada no servidor do cliente
	 * para salvar seus dados de forma online e aqui temos alguns metodos para ver
	 * se o dado que o cliente mandou e da instancia da classe para podermos salvar
	 * esse dados num logar mais apropriado.
	 */

	public void run() {

		ObjectOutputStream transmissor;
		try {
			transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
		} catch (Exception erro) {
			return;
		}

		ObjectInputStream receptor = null;
		try {
			receptor = new ObjectInputStream(this.conexao.getInputStream());
		} catch (Exception err0) {
			try {
				System.out.println("Erro aqui");
				transmissor.close();
			} catch (Exception falha) {
			} // so tentando fechar antes de acabar a thread

			return;
		}

		try {
			this.usuario = new Parceiro(this.conexao, receptor, transmissor);
		} catch (Exception erro) {
		} // sei que passei os parametros corretos

		try {
			synchronized (this.usuarios) {
				this.usuarios.add(this.usuario);
			}

			for (;;) {

				System.out.println("teste");
				Comunicado comunicado = this.usuario.envie();

				if (comunicado == null)
					return;

				if (comunicado instanceof PedidoParaSair) {

					synchronized (this.usuarios) {
						this.usuarios.remove(this.usuario);
					}
					this.usuario.adeus();
					System.out.println("Cliente desconetado");
				}

				else if (comunicado instanceof PedidoSalvamento) {
					Labirintos.incluir(((PedidoSalvamento) comunicado).getLab());
				} else if (comunicado instanceof PedidoDeAbertura) {
					Labirinto retLab;
					retLab = Labirintos.getLabirinto(((PedidoDeAbertura) comunicado).getEmail(),
							((PedidoDeAbertura) comunicado).getNome());
					usuario.receba(new Desenho(retLab.getLabirinto()));

				}
			}
		} catch (Exception erro) {
			try {

				transmissor.close();
				receptor.close();
			} catch (Exception falha) {
			} // so tentando fechar antes de acabar a thread

			return;
		}
	}
}

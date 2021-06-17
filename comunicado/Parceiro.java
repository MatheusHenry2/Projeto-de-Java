package comunicado;

/**
 * Esta classe representa uma classe de conexão de socket
 * entre o cliente e o servidor nesta classe contem alguns metodos
 * que fazemos o envio e o recebimento de informacoes entre ambos os lados
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 * */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class Parceiro extends Comunicado implements Serializable, Cloneable {
	private Socket conexao;
	private ObjectInputStream receptor;
	private ObjectOutputStream transmissor;

	private Comunicado proximoComunicado = null;

	private Semaphore mutEx = new Semaphore(1, true);

	/**
	 * Este metodo e aplicavel a instancia desta classe ao qual faz a conexao direta
	 * entre cliente e servidor
	 * 
	 * @param o parametro desta classe vem como socket tanto para a conexao com o
	 *          servidor tanto para o recebimento de dados do servidor
	 * @throws se algum parametro passado pelo cliente na main vier nulo entao
	 *            lancamos uma execao ao mesmo.
	 */

	public Parceiro(Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception // se
																													// parametro
																													// nulos
	{
		if (conexao == null)
			throw new Exception("Conexao ausente");

		if (receptor == null)
			throw new Exception("Receptor ausente");

		if (transmissor == null)
			throw new Exception("Transmissor ausente");

		this.conexao = conexao;
		this.receptor = receptor;
		this.transmissor = transmissor;
	}

	/**
	 * Este metodo e aplicavel quando o cliente deseja enviar algum tipo de
	 * informacao em forma de comunicado para o servidor no nosso caso estamos
	 * enviamos o objeto do tipo pedidodesalvamento dados para ser enviado em forma
	 * de comunicado para o servidor.
	 * 
	 * @param o parametro e objeto ao qual estamos enviando para o servidor receber
	 * @throws caso aconteca algum erro na transmissao de dados lancamos uma execao
	 *              para informar que ocorreu algum erro.
	 */

	public void receba(Comunicado x) throws Exception {
		try {
			this.transmissor.writeObject(x);
			this.transmissor.flush();
		} catch (IOException erro) {
			throw new Exception("Erro de transmissao");
		}
	}

	/**
	 * Este e um metodo para visualizar espiar a informacao sem de fato ocupar
	 * espaco e usar processamento e um metodo de visualizar o comunicado de forma
	 * breve.
	 * 
	 * @throws caso de algum erro na recepcao deste comunicado lancamos uma execao
	 *              ao cliente informando o erro do mesmo
	 */

	public Comunicado espie() throws Exception {
		try {
			this.mutEx.acquireUninterruptibly();
			if (this.proximoComunicado == null)
				this.proximoComunicado = (Comunicado) this.receptor.readObject();
			this.mutEx.release();
			return this.proximoComunicado;
		} catch (Exception erro) {
			throw new Exception("Erro de recepcao");
		}
	}

	/**
	 * Este metodo e aplicavel quando o cliente necessita que o servidor envie algum
	 * tipo de informacao como forma de comunicado para o mesmo
	 * 
	 * @throws caso aconteca algum tipo de erro na recepcao deste comunicado enviado
	 *              do servidor informamos entao um erro ao cliente do erro do
	 *              recebimento do comunicado.
	 */

	public Comunicado envie() throws Exception {
		try {
			// System.out.println("1");
			if (this.proximoComunicado == null)
				this.proximoComunicado = (Comunicado) this.receptor.readObject();
			// System.out.println("2");
			Comunicado ret = this.proximoComunicado;
			this.proximoComunicado = null;
			return ret;
		} catch (Exception erro) {
			throw new Exception("Erro de recepcao");
		}
	}

	/**
	 * Este metodo e aplicavel quando o usuario deseja se desconectar do cliente e
	 * tiramos todas conexoes diretas com o cliente e entao ele sera desconectado do
	 * servidor ao qual esta realizando a conexao via Socket.
	 * 
	 * @throws caso aconteca algum tipo de erra na hora de se desconectar do
	 *              servidor entao enviamos uma execao e entao informamos o usuario
	 *              do programa que ocorreu um erro na hora de se desconectar do
	 *              servidor.
	 */

	public void adeus() throws Exception {
		try {
			this.transmissor.close();
			this.receptor.close();
			this.conexao.close();
		} catch (Exception erro) {
			throw new Exception("Erro de desconexao");
		}
	}
}
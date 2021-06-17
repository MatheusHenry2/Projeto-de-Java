package cliente;

/**
 * A classe janela representa uma inteface grafica onde o usuario
 * tem metodos de actions linsteners e acoes para o usuario visualizar os
 * labirintos que serao resolvidos ela classe herda de labirinto uma herança dos
 * metodos que sao aplicaveis a ele implementando serializable e cloneable
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import comunicado.Comunicado;
import comunicado.Desenho;
import comunicado.Lab;
import comunicado.Parceiro;
import comunicado.PedidoDeAbertura;
import comunicado.PedidoParaSair;
import comunicado.PedidoSalvamento;

public class Janela extends Labirinto implements Serializable, Cloneable {
	private JButton botao[] = new JButton[8];
	private JButton botao2[] = new JButton[1];
	private JButton botao3[] = new JButton[1];
	private JButton botao4[] = new JButton[1];
	private JButton botao5[] = new JButton[1];
	JFrame janela = new JFrame("Editor Labirinto");
	JFrame janela2 = new JFrame("Salvamento Online");
	JFrame janela3 = new JFrame("Selecionar Labirinto");
	JFrame janela4 = new JFrame("Selecionar Labirinto");
	JFrame janela5 = new JFrame("Labirintos Do Banco");
	JTextField textField = new JTextField("Nome_Labirinto", 20);
	JTextField textField1 = new JTextField("Data da criacao", 20);
	JTextField textField2 = new JTextField("Data ultimo criacao", 20);
	JTextField textField3 = new JTextField("Email", 20);
	JTextField textField4 = new JTextField("Senha", 20);
	JTextField textField8 = new JTextField("Informe seu email", 20);
	JTextField textField9 = new JTextField("Informe o nome do labirinto", 20);
	JTextArea textField12 = new JTextArea();
	JTextField textField10 = new JTextField("Informe o nome do Labirinto", 20);
	JTextField textField11 = new JTextField("Informe sua senha", 20);
	JTextArea editor = new JTextArea();
	JTextArea labirintos = new JTextArea();
	JTextArea log = new JTextArea(12, 12);
	Parceiro servidor;
	ObjectOutputStream transmissor;
	
	
	/**
	 * Este metodo sao as acoes que cada botao vai ter 
	 * no nosso labirinto cada botao representa sua respectiva funcionalidade
	 * a ser tomada e uma classe que implementa action listener para lidar com
	 * intercoes do cliente, alguns botoes estao sendo configurados com socket para ter
	 * conexao com servidor dos dados requeridos
	 * @throws Exception este metodo apresenta varios catchs dependendo de cada funcionalidade
	 * de cada botao.
	 */


	private class TratadoraDeMouse extends Thread implements ActionListener {
		String nomeArquivo;
		String nomearq;
		String test;
		String nomeLabirinto;
		String dataCriacao;
		String dataUltimaCriacao;
		String conteudolab;

		public void actionPerformed(ActionEvent e) {

			String comando = e.getActionCommand();

			if (comando == "Desconectar") {
				try {
					servidor.receba(new PedidoParaSair());
				} catch (Exception erro) {
				}
				try {
					Thread.sleep(1500);
					janela.setVisible(false);
				} catch (Exception erro) {
				}
			}

			if (comando == "Selecionar Todos Labirintos") {
				janela4.setVisible(true);
			}

			if (comando == "Buscar") {
				System.out.println("Buscar worked");
			}

			if (comando == "Salvar Online") {

				janela2.setVisible(true);
				labirintos.setFont(new Font("Consolas", 4, 20));

			}

			if (comando == "Selecionar Labirinto") {
				janela3.setVisible(true);
			}

			if (comando == "Selecionar") {
				try {
					servidor.receba(new PedidoDeAbertura(textField8.getText(), textField9.getText()));
					Comunicado comunicado2 = null;
					do {
						comunicado2 = (Comunicado) servidor.espie();
					} while (!(comunicado2 instanceof Desenho));
					Desenho desenho = (Desenho) servidor.envie();
					String labBanco;
					labBanco = desenho.getDesenho();

					editor.setFont(new Font("Consolas", 4, 20));
					for (int i = 0; i < labBanco.length(); i++) {
						if (labBanco.charAt(i) == '+') {
							editor.setText(editor.getText() + "\n");
						} else {
							editor.setText(editor.getText() + labBanco.charAt(i));
						}
					}
					janela3.setVisible(false);
					

				} catch (Exception ero) {
					ero.printStackTrace();
					System.err.println("Erro ao enviar selecionar");
				}
			}

			if (comando == "Limpar") {
				textField.setText("");
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				labirintos.setText("");
			}
			if (comando == "Enviar") {

				String str2 = labirintos.getText();
				String nomeLab = textField.getText();
				String firstdate = textField1.getText();
				String lastdate = textField2.getText();
				String email = textField3.getText();
				String senha = textField4.getText();

				if (str2.equals("") || nomeLab.equals("") || firstdate.equals("") || lastdate.equals("")
						|| email.equals("") || senha.equals(""))
					System.err.println("Algum campo esta vazio");

				try {
					FileWriter fileWriter = new FileWriter("C:\\teste.txt");
					PrintWriter pw = new PrintWriter(fileWriter);
					this.nomearq = "C:\\teste.txt";

					pw.print(str2);
					pw.flush();
					pw.close();
					fileWriter.close();

					Labirinto l10 = new Labirinto(this.nomearq);
					l10.resolver();

					String str3 = str2.replace("\n", "+");
					str3 = str3.substring(2);
					//System.out.println(str3);

					Lab user;
					user = new Lab(nomeLab, firstdate, lastdate, str3, email, senha);

					servidor.receba(new PedidoSalvamento(email, senha, user));
					
					System.out.println("Dado salvo no servidor!");

					janela2.setVisible(false);

				} catch (Exception erro) {
					textField.setFont(new Font("Consolas", 4, 20));
					textField1.setFont(new Font("Consolas", 4, 20));
					textField2.setFont(new Font("Consolas", 4, 20));
					textField3.setFont(new Font("Consolas", 4, 20));
					textField4.setFont(new Font("Consolas", 4, 20));
					textField.setText("Labirinto invalido ");
					textField1.setText("Labirinto invalido ");
					textField2.setText("Labirinto invalido ");
					textField3.setText("Labirinto invalido ");
					textField4.setText("Labirinto invalido ");
					labirintos.setText("Labirinto invalido");
				}

			}

			if (comando == "Executar Labirinto") {

				try {
					if (editor.getText().equals("")) {
						log.setFont(new Font("Consolas", 1, 20));
						log.setText("Editor vazio nao tem o que executar");
					}
					log.setText("Labirinto resolvido");
					Labirinto l2 = new Labirinto(this.nomeArquivo);
					l2.resolver();
					this.test = l2.printarResolucao();
					log.setText(this.test);
					editor.setText(l2.printarResolucao());
					editor.setFont(new Font("Consolas", 4, 15));
					editor.setText(l2.toString());
					log.setFont(new Font("Consolas", 1, 20));

				} catch (Exception e1) {
					System.out.println("Anta");
				}
			}

			else if (comando == "Novo Labirinto") {
				editor.setFont(new Font("Monospaced", 4, 20));
				editor.setText("");
				log.setText("");
			} else if (comando == "Abrir Labirinto") {
				JFileChooser file = new JFileChooser();
				file.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int i = file.showSaveDialog(null);
				if (i == 1) {
					editor.setText("");
				} else {
					File arquivo = file.getSelectedFile();
					try {
						Labirinto l1 = new Labirinto(arquivo.getPath());
						this.nomeArquivo = arquivo.getPath();
						editor.setFont(new Font("Consolas", 4, 15));
						editor.setText(l1.toString());

					} catch (Exception e1) {
						log.setFont(new Font("Consolas", 4, 20));
					}

				}

			} else if (comando == "Salvar arquivo de labirinto") {

				if (editor.getText().equals("")) {
					log.setFont(new Font("Consolas", 4, 20));
					log.setText("Editor Vazio anta vai salvar o que?");
				} else {
					String str;
					str = editor.getText();
					log.setText("Labirinto salvo ");

					try {
						FileWriter fileWriter = new FileWriter("C:\\teste.txt");
						PrintWriter pw = new PrintWriter(fileWriter);
						this.nomearq = "C:\\teste.txt";

						pw.print(str);
						pw.flush();
						pw.close();
						fileWriter.close();

						Labirinto l3 = new Labirinto(this.nomearq);
						l3.resolver();
						log.setText(l3.printarResolucao());

						FileWriter fileWriter1 = new FileWriter("C:\\Labirinto2.txt");
						PrintWriter pw1 = new PrintWriter(fileWriter1);
						this.nomearq = "C:\\Labirinto2.txt";

						pw1.print(str);
						pw1.flush();
						pw1.close();
						fileWriter1.close();

					} catch (Exception ioException) {
						log.setFont(new Font("Consolas", 4, 20));
						log.setText("Labirinto invalido para salvar sua anta");
					}
				}

			}
		}
	}
	
	
	/**
	 * estamos no construtor da instancia da janela
	 * onde construiremos a janela que o usuario ira visualizar o labirinto
	 * e solucionar com interacao de interface grafica para  o usuario e tambem
	 * instanciamos da main do cliente o servidor socket que sera essencial
	 * para passar as informacoes dessa janela para o servidor lidar com os dados
	 * @param servidor
	 */

	public Janela(Parceiro servidor) {

		super();
		this.servidor = servidor;
		JPanel botoes = new JPanel();
		botoes.setLayout(new GridLayout(1, 7));

		JPanel botoes1 = new JPanel();
		botoes1.setLayout(new GridLayout(1, 2));

		JPanel botoes2 = new JPanel();
		botoes2.setLayout(new GridLayout(1, 1));

		JPanel botoes3 = new JPanel();
		botoes3.setLayout(new GridLayout(1, 1));

		JPanel botoes4 = new JPanel();
		botoes3.setLayout(new GridLayout(1, 1));

		String texto[] = { "Novo Labirinto", "Abrir Labirinto", "Salvar arquivo de labirinto", "Executar Labirinto",
				"Desconectar", "Salvar Online", "Selecionar Todos Labirintos", "Selecionar Labirinto" };
		String texto2[] = { "Enviar" };
		String texto3[] = { "Limpar" };
		String texto4[] = { "Selecionar" };
		String texto5[] = { "Buscar" };

		TratadoraDeMouse tratadorDeMouse = new TratadoraDeMouse();

		for (int i = 0; i < this.botao.length; i++) {
			this.botao[i] = new JButton(texto[i]);
			this.botao[i].setActionCommand(texto[i]);
			this.botao[i].addActionListener(tratadorDeMouse);
			botoes.add(this.botao[i]);
		}

		for (int i = 0; i < this.botao2.length; i++) {
			this.botao2[i] = new JButton(texto2[i]);
			this.botao2[i].setActionCommand(texto2[i]);
			this.botao2[i].addActionListener(tratadorDeMouse);
			botoes1.add(this.botao2[i]);
		}

		for (int i = 0; i < this.botao3.length; i++) {
			this.botao3[i] = new JButton(texto3[i]);
			this.botao3[i].setActionCommand(texto3[i]);
			this.botao3[i].addActionListener(tratadorDeMouse);
			botoes2.add(this.botao3[i]);
		}

		for (int i = 0; i < this.botao4.length; i++) {
			this.botao4[i] = new JButton(texto4[i]);
			this.botao4[i].setActionCommand(texto4[i]);
			this.botao4[i].addActionListener(tratadorDeMouse);
			botoes3.add(this.botao4[i]);
		}

		for (int i = 0; i < this.botao5.length; i++) {
			this.botao5[i] = new JButton(texto5[i]);
			this.botao5[i].setActionCommand(texto5[i]);
			this.botao5[i].addActionListener(tratadorDeMouse);
			botoes4.add(this.botao5[i]);
		}

		this.janela4.add(textField10, BorderLayout.NORTH);
		this.janela4.add(textField11, BorderLayout.CENTER);
		this.janela4.add(botoes4, BorderLayout.SOUTH);

		this.janela2.setSize(500, 500);
		this.janela2.setLayout(new GridLayout(2, 5));

		this.janela3.setSize(400, 400);
		this.janela3.add(textField8, BorderLayout.NORTH);
		this.janela3.add(textField9, BorderLayout.CENTER);
		this.janela3.add(botoes3, BorderLayout.SOUTH);

		// ############################################################################
		this.janela5.setSize(400, 400);
		this.janela5.add(textField12, BorderLayout.CENTER);

		this.janela4.setSize(400, 400);

		this.janela2.add(textField, BorderLayout.NORTH);
		this.janela2.add(textField1, BorderLayout.NORTH);
		this.janela2.add(textField2, BorderLayout.NORTH);
		this.janela2.add(textField3, BorderLayout.NORTH);
		this.janela2.add(textField4, BorderLayout.NORTH);
		this.janela2.add(labirintos, BorderLayout.NORTH);
		this.janela2.add(botoes1, BorderLayout.SOUTH);
		this.janela2.add(botoes2, BorderLayout.SOUTH);

		this.janela.setSize(2000, 1500);
		this.janela.getContentPane().setLayout(new BorderLayout());

		this.janela.add(botoes, BorderLayout.NORTH);
		this.janela.add(this.editor, BorderLayout.CENTER);

		this.janela.add(this.log, BorderLayout.SOUTH);
		log.setBackground(Color.PINK);
		textField8.setBackground(Color.PINK);
		textField10.setBackground(Color.cyan);
		editor.setBackground(Color.cyan);

		this.janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.janela.setVisible(true);
	}

}
package servidor;

/**
A classe teclado representa respectivamente uma classe
de input de informações contendo varios tipos de dados diferentes
inteiros pontos flutuantes etc seria basicamente a classe de input de informações
que o usuário pode aplicar em varias classes.
*/

import java.io.*;

public class Teclado {
	private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * este método pega o input de dado da pessoa ao qual se aplica a uma string
	 * pelo dado vir em string não tem chance de erros
	 * 
	 * @return retorna a string do input para o usuario ao metodo aplicavel
	 */

	public static String getUmString() {
		String ret = null;

		try {
			ret = teclado.readLine();
		} catch (IOException erro) {
		} // sei que não vai dar erro

		return ret;
	}

	/**
	 * este método pega o input de um byte dado pela pessoa ele converte o input do
	 * byte que sempre vem em string pro tipo byte
	 * 
	 * @return retorna o byte do input so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um byte
	 *                   lançamenos uma execeção
	 */

	public static byte getUmByte() throws Exception {
		byte ret = (byte) 0;

		try {
			ret = Byte.parseByte(teclado.readLine());
		} catch (IOException erro) {
		} // sei que nao vai dar erro
		catch (NumberFormatException erro) {
			throw new Exception("Byte invalido!");
		}

		return ret;
	}

	/**
	 * este método pega o input de um short dado pela pessoa ele converte o input do
	 * short que sempre vem em string pro tipo short
	 * 
	 * @return retorna o short do input so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um short
	 *                   lançamenos uma execeção
	 */

	public static short getUmShort() throws Exception {
		short ret = (short) 0;

		try {
			ret = Short.parseShort(teclado.readLine());
		} catch (IOException erro) {
		} // sei que nao vai dar erro
		catch (NumberFormatException erro) {
			throw new Exception("Short invalido!");
		}

		return ret;
	}

	/**
	 * este método pega o input de um int dado pela pessoa ele converte o input do
	 * int que sempre vem em string pro tipo int
	 * 
	 * @return retorna o input do input so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um int
	 *                   lançamenos uma execeção
	 */

	public static int getUmInt() throws Exception {
		int ret = 0;

		try {
			ret = Integer.parseInt(teclado.readLine());
		} catch (IOException erro) {
		} // sei que nao vai dar erro
		catch (NumberFormatException erro) {
			throw new Exception("Int invalido!");
		}

		return ret;
	}

	/**
	 * este método pega o input de um long dado pela pessoa ele converte o input do
	 * long que sempre vem em string pro tipo long
	 * 
	 * @return retorna o input do long so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um long
	 *                   lançamenos uma execeção
	 */

	public static long getUmLong() throws Exception {
		long ret = 0L;

		try {
			ret = Long.parseLong(teclado.readLine());
		} catch (IOException erro) {
		} // sei que nao vai dar erro
		catch (NumberFormatException erro) {
			throw new Exception("Long invalido!");
		}

		return ret;
	}

	/**
	 * este método pega o input de um float dado pela pessoa ele converte o input do
	 * float que sempre vem em string pro tipo float
	 * 
	 * @return retorna o input do float so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um float
	 *                   lançamenos uma execeção
	 */

	public static float getUmFloat() throws Exception {
		float ret = 0.0F;

		try {
			ret = Float.parseFloat(teclado.readLine());
		} catch (IOException erro) {
		} // sei que nao vai dar erro
		catch (NumberFormatException erro) {
			throw new Exception("Float invalido!");
		}

		return ret;
	}

	/**
	 * este método pega o input de um double dado pela pessoa ele converte o input
	 * do double que sempre vem em string pro tipo double
	 * 
	 * @return retorna o input do double so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um double
	 *                   lançamenos uma execeção
	 */

	public static double getUmDouble() throws Exception {
		double ret = 0.0;

		try {
			ret = Double.parseDouble(teclado.readLine());
		} catch (IOException erro) {
		} // sei que nao vai dar erro
		catch (NumberFormatException erro) {
			throw new Exception("Double invalido!");
		}

		return ret;
	}

	/**
	 * este método pega o input de um boolean dado pela pessoa ele converte o input
	 * do boolean que sempre vem em string pro tipo double
	 * 
	 * @return retorna o input do boolean so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um boolean
	 *                   lançamenos uma execeção
	 */

	public static boolean getUmBoolean() throws Exception {
		boolean ret = false;

		try {
			String str = teclado.readLine();

			if (str == null)
				throw new Exception("Boolean invalido!");

			if (!str.equals("true") && !str.equals("false")) // 0 ou ele 1
				throw new Exception("Boolean invalido!");

			ret = Boolean.parseBoolean(str);
		} catch (IOException erro) {
		} // sei que nao vai dar erro

		return ret;
	}

	/**
	 * este método pega o input de um char dado pela pessoa ele converte o input do
	 * char que sempre vem em string pro tipo char
	 * 
	 * @return retorna o input do char so usuário ao método aplicavel
	 * @throws Exception quando o input da pessoa respectivamente não é um char
	 *                   lançamenos uma execeção
	 */

	public static char getUmChar() throws Exception {
		char ret = ' ';

		try {
			String str = teclado.readLine();

			if (str.length() != 1) // palavra
				throw new Exception("Char invalido!");

			ret = str.charAt(0);
		} catch (IOException erro) {
		} // sei que nao vai dar erro

		return ret;
	}

}
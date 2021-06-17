package cliente;

/**
A classe arquivo representa respectivamente uma classe
de input de informa��es contendo varios tipos de dados diferentes
inteiros pontos flutuantes etc seria basicamente a classe de input de informa��es
que o usu�rio pode aplicar em varias classes para receber o conte�do do arquivo txt.
@author Matheus Henry, Danilo Montovaneli, Matheus camargo
@since 2021.
*/

import java.io.*;

public class Arquivo
{
	
	private BufferedReader arquivo;
	
	/**Cria um novo ponteiro para um arquivo.
	 Este construtor recebe como par�metro um path para o arquivo a ser 
	 manipulado, e instancia um objeto da classe BufferedReader para ler o arquivo.
	 @param nomeArq.
	 */
	public Arquivo(String nomeArq)
	{
		try
		{
			arquivo = new BufferedReader(new FileReader(nomeArq));
			
			
		}
		catch(IOException erro)
		{
			System.err.println("Arquivo Invalido");
		}
		
	}
	
	
	/**Pega uma linha do arquivo.
	 Este m�todo atribui em uma String uma linha do arquivo escolhido
	 para isso, usa-se do m�todo readLine()
	 @return
	 */
	public String getUmString ()
	{
		String ret=null;
		
		try
		{
			ret = arquivo.readLine();
		}
		catch (IOException erro)
		{} // sei que n�o vai dar erro
		
		return ret;
	}
	
	/**Pega um Byte do arquivo.
	 Este m�todo retorna um Byte atrav�s do m�todo ReadLine e para se atribuir
	 � um tipo Byte usa-se o m�todo da wrapper parseByte.
	 @return um byte do arquivo
	 @throws Exception caso n�o seja pego um Byte no arquivo, caso pegue um char por exemplo
	 */
	public byte getUmByte () throws Exception
	{
		byte ret = (byte)0;
		
		try
		{
			ret = Byte.parseByte (arquivo.readLine ());	
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		catch (NumberFormatException erro)
		{
			throw new Exception ("Byte invalido!");
		}
		
		return ret;
	}
	
	/**Pega um short do arquivo.
	 Este m�todo retorna um short atrav�s do m�todo ReadLine e para se atribuir
	 � um tipo short usa-se o m�todo da wrapper parseshort.
	 @return um short do arquivo
	 @throws Exception caso n�o seja pego um short no arquivo, caso pegue um char por exemplo
	 */
	public short getUmShort () throws Exception
	{
		short ret = (short)0;
		
		try
		{
			ret = Short.parseShort (arquivo.readLine ());	
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		catch (NumberFormatException erro)
		{
			throw new Exception ("Short invalido!");
		}
		
		return ret;
	}
	
	/**Pega um int do arquivo.
	 Este m�todo retorna um int atrav�s do m�todo ReadLine e para se atribuir
	 � um tipo int usa-se o m�todo da wrapper parseInt.
	 @return um int do arquivo
	 @throws Exception caso n�o seja pego um int no arquivo, caso pegue um char por exemplo
	 */
	public int getUmInt () throws Exception
	{
		int ret=0;
		
		try
		{
			ret = Integer.parseInt (arquivo.readLine ());	
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		catch (NumberFormatException erro)
		{
			throw new Exception ("Int invalido!");
		}
		
		return ret;
	}
	
	/**Pega um long do arquivo.
	 Este m�todo retorna um long atrav�s do m�todo ReadLine e para se atribuir
	 � um tipo long usa-se o m�todo da wrapper parseLong.
	 @return um long do arquivo
	 @throws Exception caso n�o seja pego um long no arquivo, caso pegue um char por exemplo
	 */
	public long getUmLong () throws Exception
	{
		long ret = 0L;
		
		try
		{
			ret = Long.parseLong (arquivo.readLine ());	
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		catch (NumberFormatException erro)
		{
			throw new Exception ("Long invalido!");
		}
		
		return ret;
	}
	
	/**Pega um float do arquivo.
	 Este m�todo retorna um float atrav�s do m�todo ReadLine e para se atribuir
	 � um tipo long usa-se o m�todo da wrapper parseFloat.
	 @return um float do arquivo
	 @throws Exception caso n�o seja pego um float no arquivo, caso pegue um char por exemplo
	 */
	public float getUmFloat () throws Exception
	{
		float ret = 0.0F;
		
		try
		{
			ret = Float.parseFloat (arquivo.readLine ());	
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		catch (NumberFormatException erro)
		{
			throw new Exception ("Float invalido!");
		}
		
		return ret;
	}
	
	/**Pega um double do arquivo.
	 Este m�todo retorna um double atrav�s do m�todo ReadLine e para se atribuir
	 � um tipo double usa-se o m�todo da wrapper parseDouble.
	 @return um double do arquivo
	 @throws Exception caso n�o seja pego um double no arquivo, caso pegue um char por exemplo
	 */
	public double getUmDouble () throws Exception
	{
		double ret=0.0;
		
		try
		{
			ret = Double.parseDouble (arquivo.readLine ());	
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		catch (NumberFormatException erro)
		{
			throw new Exception ("Double invalido!");
		}
		
		return ret;
	}
	
	/**Pega um Boolean do arquivo.
	 Este m�todo retorna um boolean pego do arquivo. 
	 @return true ou false pego do arquivo
	 @throws Exception caso seja pego uma string inv�lida do arquivo, ou a string � diferente de true ou false
	 */
	public boolean getUmBoolean () throws Exception
	{
		boolean ret=false;
		
		try
		{
			String str = arquivo.readLine();
			
			if (str==null)
				throw new Exception ("Boolean invalido!");
			
			if (!str.equals("true") && !str.equals("false"))
				throw new Exception ("Boolean invalido!");
			
			ret = Boolean.parseBoolean (str);
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		
		return ret;
	}
	
	/**Este m�todo pega um char do arquivo.
	   Ele pega primeiramente uma string do arquivo, valida se esta string tem somente um de tamanho e retorna
	   o caractere no espa�o 0 da string, ou seja, seu primeiro caractere.
	  @return um char do arquivo
	  @throws Exception caso n�o seja pego um char do arquivo, caso pegue uma string de mais de um de tamanho
	 */
	public char getUmChar () throws Exception
	{
		char ret=' ';
		
		try
		{
			String str = arquivo.readLine();
			
			if (str.length() != 1)
				throw new Exception ("Char invalido!");
			
			ret = str.charAt(0);
		}
		catch (IOException erro)
		{} // sei que nao vai dar erro
		
		return ret;
	}
	
}

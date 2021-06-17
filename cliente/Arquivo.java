package cliente;

/**
A classe arquivo representa respectivamente uma classe
de input de informações contendo varios tipos de dados diferentes
inteiros pontos flutuantes etc seria basicamente a classe de input de informações
que o usuário pode aplicar em varias classes para receber o conteúdo do arquivo txt.
@author Matheus Henry, Danilo Montovaneli, Matheus camargo
@since 2021.
*/

import java.io.*;

public class Arquivo
{
	
	private BufferedReader arquivo;
	
	/**Cria um novo ponteiro para um arquivo.
	 Este construtor recebe como parâmetro um path para o arquivo a ser 
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
	 Este método atribui em uma String uma linha do arquivo escolhido
	 para isso, usa-se do método readLine()
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
		{} // sei que não vai dar erro
		
		return ret;
	}
	
	/**Pega um Byte do arquivo.
	 Este método retorna um Byte através do método ReadLine e para se atribuir
	 à um tipo Byte usa-se o método da wrapper parseByte.
	 @return um byte do arquivo
	 @throws Exception caso não seja pego um Byte no arquivo, caso pegue um char por exemplo
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
	 Este método retorna um short através do método ReadLine e para se atribuir
	 à um tipo short usa-se o método da wrapper parseshort.
	 @return um short do arquivo
	 @throws Exception caso não seja pego um short no arquivo, caso pegue um char por exemplo
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
	 Este método retorna um int através do método ReadLine e para se atribuir
	 à um tipo int usa-se o método da wrapper parseInt.
	 @return um int do arquivo
	 @throws Exception caso não seja pego um int no arquivo, caso pegue um char por exemplo
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
	 Este método retorna um long através do método ReadLine e para se atribuir
	 à um tipo long usa-se o método da wrapper parseLong.
	 @return um long do arquivo
	 @throws Exception caso não seja pego um long no arquivo, caso pegue um char por exemplo
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
	 Este método retorna um float através do método ReadLine e para se atribuir
	 à um tipo long usa-se o método da wrapper parseFloat.
	 @return um float do arquivo
	 @throws Exception caso não seja pego um float no arquivo, caso pegue um char por exemplo
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
	 Este método retorna um double através do método ReadLine e para se atribuir
	 à um tipo double usa-se o método da wrapper parseDouble.
	 @return um double do arquivo
	 @throws Exception caso não seja pego um double no arquivo, caso pegue um char por exemplo
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
	 Este método retorna um boolean pego do arquivo. 
	 @return true ou false pego do arquivo
	 @throws Exception caso seja pego uma string inválida do arquivo, ou a string é diferente de true ou false
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
	
	/**Este método pega um char do arquivo.
	   Ele pega primeiramente uma string do arquivo, valida se esta string tem somente um de tamanho e retorna
	   o caractere no espaço 0 da string, ou seja, seu primeiro caractere.
	  @return um char do arquivo
	  @throws Exception caso não seja pego um char do arquivo, caso pegue uma string de mais de um de tamanho
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

package cliente;

/**
A classe Coordenada respresenta uma classe onde respectivamente
a classe guardar posi��es da matriz para guardar emm coordendas com representa��o visuais e m�todos
nela encontramos alguns m�todos como incluir, alterar e pegar as coordendas e alguns
m�todos obrigatorios.
@author Matheus Henry, Danilo Montovaneli, Matheus Camargo
@since 2021.
*/

public class Coordenada implements Cloneable {

	private int x; 
	private int y; 

  /**
   Construi uma nova instancia da coordenada
   para tanto ele pega posi��es de linha e coluna da matriz para construir
   @param O n�mero da linha e da coluna da matriz para cria��o pelo construtor
   @throws Exception caso o n�mero de coluna ou linhas seja menor do que 0
   lan�amos uma exe��o.
  */
	public Coordenada(int linha, int coluna) throws Exception {

		if (linha < 0 || coluna < 0)
			throw new Exception("Coordenadas inv�lidas");

		this.x = linha;
		this.y = coluna;
	}

  /**
  est� m�todo faz altera��o na linha passada pelo usu�rio
  a qual ele quer fazer altera��o e atribuimos ao this.x
  @throws Exeption caso o usu�rio tente fazer altera��o para uma linha menor que 0
  lan�amenos uma exe��o
  */

	public void setLinha(int linha) throws Exception {
		
		if(linha < 0)
			throw new Exception("Linha inv�lida anta");
		
		this.x = linha;
	}

  /**
  est� m�todo faz altera��o na coluna passada pelo usu�rio
  a qual ele quer fazer altera��o e atribuimos ao this.y
  @throws Exeption caso o usu�rio tente fazer altera��o para uma coluna menor que 0
  lan�amenos uma exe��o
  */
     
	public void setColuna(int coluna) throws Exception {
		
		if(coluna < 0)
			throw new Exception("Coluna inv�lida anta");
		
		this.y = coluna;
	}

    /**
    Este m�todo � aplicavel quando o usu�rio quer o usar a linha
    fazemos ele retornar o this.x que se refere a linha
    @return retornamos a linha do atributo this.x
    */

	public int getLinha() {
		return this.x;
	}

    /**
    Este m�todo � aplicavel quando o usu�rio quer o usar a coluna
    fazemos ele retornar o this.y que se refere a linha
    @return retornamos a coluna do atributo this.y
    */
	public int getColuna() {
		return this.y;
	}

     /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de uma Coorndenada.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da agenda representada pela inst�ncia � qual o m�todo for aplicado.
    @return o c�digo de espalhamento da coordenada chamante do m�todo.
    */

	public int hashCode() {

		int ret = 13;

		ret = ret * 13 + new Integer(this.x).hashCode();
		ret = ret * 13 + new Integer(this.y).hashCode();
		
		if(ret < 0) ret = ret - ret;

		return ret;
	}

    /**
    Verifica a igualdade entre duas Coordenadas.
    Verifica se o Object fornecido como par�metro representa uma
    coordenada igual �quela representada pela inst�ncia � qual este
    m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
            for aplicado.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem coordenadas iguais, ou false, caso contr�rio.
    */

	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Coordenada))
			return false;

		Coordenada cord = (Coordenada) obj;

		if (this.x != cord.x)
			return false;
		if (this.y != cord.y)
			return false;

		return true;
	}

     /**
    Gera uma representa��o textual de todo conte�do das coordenadas
    Produz uma representa��o visual de todos dado contido nas coordenadas
    no nosso caso as coordenadas e.
    @return um String contendo todo o conte�do das coordenadas.
    */

	public String toString() {

		String ret;

		ret = "(";

		ret += this.x + "," + this.y;

		ret += ")";

		return ret;
	}

   /**
   est� m�todo � um m�todo construtor modelo onde atribuimos um modelo
   c�pia no exemplo abaixo verificamos se o modelo n�o � invalido para ser atribuido ao this
   caso contr�rio atribuimos ao this deste
   @throws Exception caso o modelo seja null inv�lido lan�amenos uma exec��o
   */

	public Coordenada(Coordenada modelo) throws Exception {

		if (modelo == null)
			throw new Exception("Modelo inv�lido");

		this.x = modelo.x;
		this.y = modelo.y;
	}

    /**
   Este m�todo � o m�todo clone ao qual se refere no nosso caso uma coordenada
   @return retorna uma c�pia da coordenada chamante do m�todo.
  */

	public Object clone() {

		Coordenada ret = null;

		try {
			ret = new Coordenada(this);
		} catch (Exception erro) {
		}
		return ret;
	}

}

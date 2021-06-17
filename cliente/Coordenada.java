package cliente;

/**
A classe Coordenada respresenta uma classe onde respectivamente
a classe guardar posições da matriz para guardar emm coordendas com representação visuais e métodos
nela encontramos alguns métodos como incluir, alterar e pegar as coordendas e alguns
métodos obrigatorios.
@author Matheus Henry, Danilo Montovaneli, Matheus Camargo
@since 2021.
*/

public class Coordenada implements Cloneable {

	private int x; 
	private int y; 

  /**
   Construi uma nova instancia da coordenada
   para tanto ele pega posições de linha e coluna da matriz para construir
   @param O número da linha e da coluna da matriz para criação pelo construtor
   @throws Exception caso o número de coluna ou linhas seja menor do que 0
   lançamos uma exeção.
  */
	public Coordenada(int linha, int coluna) throws Exception {

		if (linha < 0 || coluna < 0)
			throw new Exception("Coordenadas inválidas");

		this.x = linha;
		this.y = coluna;
	}

  /**
  esté método faz alteração na linha passada pelo usuário
  a qual ele quer fazer alteração e atribuimos ao this.x
  @throws Exeption caso o usuário tente fazer alteração para uma linha menor que 0
  lançamenos uma exeção
  */

	public void setLinha(int linha) throws Exception {
		
		if(linha < 0)
			throw new Exception("Linha inválida anta");
		
		this.x = linha;
	}

  /**
  esté método faz alteração na coluna passada pelo usuário
  a qual ele quer fazer alteração e atribuimos ao this.y
  @throws Exeption caso o usuário tente fazer alteração para uma coluna menor que 0
  lançamenos uma exeção
  */
     
	public void setColuna(int coluna) throws Exception {
		
		if(coluna < 0)
			throw new Exception("Coluna inválida anta");
		
		this.y = coluna;
	}

    /**
    Este método é aplicavel quando o usuário quer o usar a linha
    fazemos ele retornar o this.x que se refere a linha
    @return retornamos a linha do atributo this.x
    */

	public int getLinha() {
		return this.x;
	}

    /**
    Este método é aplicavel quando o usuário quer o usar a coluna
    fazemos ele retornar o this.y que se refere a linha
    @return retornamos a coluna do atributo this.y
    */
	public int getColuna() {
		return this.y;
	}

     /**
    Calcula o código de espalhamento (ou código de hash) de uma Coorndenada.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da agenda representada pela instância à qual o método for aplicado.
    @return o código de espalhamento da coordenada chamante do método.
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
    Verifica se o Object fornecido como parâmetro representa uma
    coordenada igual àquela representada pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem coordenadas iguais, ou false, caso contrário.
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
    Gera uma representação textual de todo conteúdo das coordenadas
    Produz uma representação visual de todos dado contido nas coordenadas
    no nosso caso as coordenadas e.
    @return um String contendo todo o conteúdo das coordenadas.
    */

	public String toString() {

		String ret;

		ret = "(";

		ret += this.x + "," + this.y;

		ret += ")";

		return ret;
	}

   /**
   esté método é um método construtor modelo onde atribuimos um modelo
   cópia no exemplo abaixo verificamos se o modelo não é invalido para ser atribuido ao this
   caso contrário atribuimos ao this deste
   @throws Exception caso o modelo seja null inválido lançamenos uma execção
   */

	public Coordenada(Coordenada modelo) throws Exception {

		if (modelo == null)
			throw new Exception("Modelo inválido");

		this.x = modelo.x;
		this.y = modelo.y;
	}

    /**
   Este método é o método clone ao qual se refere no nosso caso uma coordenada
   @return retorna uma cópia da coordenada chamante do método.
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

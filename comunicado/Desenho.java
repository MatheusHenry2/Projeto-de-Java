package comunicado;

/**
 * Esta classe desenho e uma classe que herda de comunicado e e usaada
 * quando o cliente for fazer uma requisicao de pedir o conteudo do labirinto salvo
 * para o servidor
 *@author Matheus Henry, Danilo Montovaneli, Matheus camargo
 *@since 2021.
 */

import java.io.Serializable;

import bd.dbo.Labirinto;

public class Desenho extends Comunicado implements Serializable, Cloneable {

	private String desenho;

	/**
	 * Este metodo e o construtor da classe de desenhos onde o conteudo do labirinto
	 * ira vir do cliente e iramos atribuir no this desta classe o conteudo do
	 * labirnto que salvo
	 * 
	 * @param conteudo do labirinto que veio pelo parametro
	 */

	public Desenho(String desenho) {
		this.desenho = desenho;
	}

	/**
	 * Este metodo e usado quando o cliente quiser fazer uma requisicao de pegar o
	 * conteudo que ele salvou o desenho do labirinto entao retornaremos o mesmo
	 * 
	 * @return o conteudo do labirinto.
	 */

	public String getDesenho() {
		return desenho;
	}

	/**
	 * Este metodo serve para mudar o valor do desenho que esta salvo no this desta
	 * classe basicamente sera utilizado quando o cliente desejar alterar o conteudo
	 * do desenho que foi salvo e aqui mudaremos com o parametro fornecido pelo
	 * mesmo
	 * 
	 * @param desenho conteudo do labirinto que veio pelo parametro
	 */

	public void setDesenho(String desenho) {
		this.desenho = desenho;
	}

	/**
	 * Este metodo é o metodo modelo da classes de desenhos de labirinto onde
	 * retorna o modelo da classe atribuindo no this do objeto da classe
	 * @param modelo do desenho
	 * @throws Exception caso o modelo seja nulo
	 */

	public Desenho(Desenho modelo) throws Exception {

		if (modelo == null) {
			throw new Exception("Modelo Ausente");
		}

		this.desenho = modelo.desenho;

	}

	/**
	 * Este metodo chama o clone para fazer shallow copy do objeto da classe
	 * chamante.
	 * @return Retorna o objeto da classe;
	 * @throws sem chance de erros nessa parte
	 */

	public Object Clone() {

		Desenho ret = null;

		try {

			ret = new Desenho(this);

		} catch (Exception erro) {
			// ignorando Exception, pois sei que não ocorrera
		}

		return ret;

	}

}
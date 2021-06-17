package comunicado;

/**
 * Esta classe representa um Pedido de abertura onde o objetivo e a realizacao
 * da abertura de um labirinto salvo pelo cliente nessa classe foi implementado 
 * metodos obrigatorios como equals toString entre outros.
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 * */

import java.io.Serializable;

public class PedidoDeAbertura extends Comunicado implements Serializable, Cloneable {

	private String email;
	private String nome;

	/**
	 * Este metodo a construcao de uma instancia desta classe o usuario ira
	 * disponibilizar alguns parametros como email e nome para esta classe fazer a
	 * operacao com esses dados com seus metodos aplicaveis
	 * 
	 * @param email
	 * @param nome
	 * @throws Exception
	 */

	public PedidoDeAbertura(String email, String nome) throws Exception {

		if (email == null)
			throw new Exception("Email vazio");
		if (nome == null)
			throw new Exception("Nome vazio");

		this.email = email;
		this.nome = nome;
	}

	/**
	 * Este metodo representa o getter desta classe ao qual e aplicavel quando
	 * deseja-sea visualizar o email que foi salvo no banco servidor
	 * 
	 * @return retorna o email do mesmo.
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * Este metodo representa um setter desta classe ao qual e aplicavel atribuindo
	 * ao obejto o conteudo do email passado
	 * 
	 * @param o conteudo do email passado
	 * 
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Este metodo representa o getter desta classe ao qual e aplicavel quando
	 * deseja-sea visualizar o nome que foi salvo no banco servidor
	 * 
	 * @return retorna o nome do mesmo.
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * Este metodo representa um setter desta classe ao qual e aplicavel atribuindo
	 * ao obejto o conteudo do nome passado
	 * 
	 * @param o conteudo do nome passado
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Este e um metodo ao qual aplica a tecnica de espalhamento das informaccoes
	 * aplicamos o hashcode em atributos primitivos desta classe como email e nome
	 * 
	 * @return retorna o resultado de espalhamento dos 2 atributos primitivos ao
	 *         qual aplicamos hashcode.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Verifica a dados pedido pelo usuario comparandoo com null para verificar se
	 * foi passado dado dentro dos elemento informados pelo cliente
	 * 
	 * @param o objeto ao qual vai ser comparado da mesma instancia ao qual tem 2
	 *          atributos como exemplo email e nome onde e comparado com null
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDeAbertura other = (PedidoDeAbertura) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * Este metodo representa uma forma visual de aplicacao dos atributos primitivos
	 * que fazem parte desta classe
	 * 
	 * @return retorna uma forma visual o atributo email e nome de uma forma que
	 *         sera melhor visualizar qual email e nome solicitado.
	 */

	@Override
	public String toString() {
		return "PedidoDeAbertura [email=" + email + ", nome=" + nome + "]";
	}
	
	/**
	 * Este metodo e um auxiliar do clone para criar um modelo
	 * dos atributos desta classe como clone 
	 * @param modelo copia da classe
	 * @throws Exception caso o modelo do chamante do metodo seja nulo
	 * lancamos uma execao para o usuario
	 */

	public PedidoDeAbertura(PedidoDeAbertura modelo) throws Exception {

		if (modelo == null) {
			throw new Exception("Modelo Ausente");
		}

		this.nome = modelo.nome;
		this.email = modelo.email;
	}
	
	/**
	 * Este metodo chama o clone para fazer shallow copy
	 * do objeto da classe chamante.
	 * @return Retorna o objeto da classe;
	 * @throws sem chance de erros na geracao da copia
	 */

	public Object Clone() {

		PedidoDeAbertura ret = null;

		try {

			ret = new PedidoDeAbertura(this);

		} catch (Exception erro) {
			// ignorando Exception, pois sei que não ocorrera
		}

		return ret;

	}

}
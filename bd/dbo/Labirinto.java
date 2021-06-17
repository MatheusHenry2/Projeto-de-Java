package bd.dbo;

/**
 * Esta classe utilizada para lidar com alguns dados que o servidor 
 * com o banco de dados nelas sao implementadas alguns metodos obrigatorios
 * e alguns setter e getters da informacoes que serao utilizadas no banco de dados
 * a classe e referenciada como classe dbo uma classe para SGBD.
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021. 
 */

import java.io.Serializable;

import comunicado.Lab;

public class Labirinto implements Serializable, Cloneable {
	private String nome;
	private String dataCriacao;
	private String dataUltimaMod;
	private String lab;
	private String email;
	private String senha;

	/**
	 * Este metodo e o construtorda instancia de labirinto onde os dados serao
	 * enviados para esta classe lidar com os dados para manipu-la los no banco de
	 * dados, um construtor da classe labirinto sem chance de erros pois ja
	 * validamos os dados na parte do cliente nao ha chances de os dados chegarem
	 * para ca com algum tipo de erro.
	 * 
	 * @param nome
	 * @param dataCriacao
	 * @param dataUltimaMod
	 * @param lab
	 * @param email
	 * @param senha
	 */

	public Labirinto(String nome, String dataCriacao, String dataUltimaMod, String lab, String email, String senha) {
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.dataUltimaMod = dataUltimaMod;
		this.lab = lab;
		this.email = email;
		this.senha = senha;

	}

	/**
	 * Este metodo e o metodo que ira disponibilizar que o usuario altere o conteudo
	 * do labirinto que foi salvo no banco de dados
	 * 
	 * @param lab
	 */

	public void setLabirinto(String lab) {
		this.lab = lab;
	}

	/**
	 * Este metodo e o get da classe para quando o usuario desejar visualizar qual
	 * nome do labirinto ele salvou no banco de dados ele ira visualizar com esse
	 * get da classe
	 * 
	 * @return o nome do labirinto.
	 */

	public String getNome() {
		return this.nome;
	}

	/**
	 * Este metodo e o get da classe para quando o usuario desejar visualizar qual
	 * na data de criacao do labirinto ele salvou no banco de dados ele ira
	 * visualizar com esse get da classe
	 * 
	 * @return a data de criacao.
	 */

	public String getDataCriacao() {
		return this.dataCriacao;
	}

	/**
	 * Este metodo e o get da classe para quando o usuario desejar visualizar qual a
	 * ultima data de criacao do labirinto ele salvou no banco de dados ele ira
	 * visualizar com esse get da classe
	 * 
	 * @return a ultima data de criacao do usuario.
	 */

	public String getDataUltimaMod() {
		return this.dataUltimaMod;
	}

	/**
	 * Este metodo e o get da classe para quando o usuario desejar visualizar o
	 * conteudo do labirinto que ele salvou nesta classe era ira visualizar todo
	 * labirinto que foi enviado para classe dbo.
	 * 
	 * @return o conteudo do labirinto.
	 */

	public String getLabirinto() {
		return this.lab;
	}

	/**
	 * Este metodo e o get da classe para quando o usuario desejar visualizar o
	 * emaail que ele salvou nesta classe era ira visualizar todo labirinto que foi
	 * enviado para classe dbo.
	 * 
	 * @return o email do cliente
	 */

	public String getEmail() {
		return this.email;
	}

	/**
	 * Este metodo e o get da classe para quando o usuario desejar visualizar a
	 * senha que ele salvou nesta classe era ira visualizar todo labirinto que foi
	 * enviado para classe dbo.
	 * 
	 * @return a senha do cliente.
	 */

	public String getSenha() {
		return senha;
	}

	/**
	 * O metodo da classe toString onde represeta os dados atributos desta classe em
	 * forma de interfac para o usuario conseguir ter uma visualizacao dos dados
	 * salvos
	 * 
	 * @return retorna os atributos em forma de interface printados para o usuario
	 *         visualizar
	 */

	public String toString() {
		return (this.nome + " \n\n " + this.lab + "\n\n Data de criacao: " + this.dataCriacao
				+ "\n Data ultima criacao: " + this.dataUltimaMod + "\n Email do cliente: " + this.email + "\n Senha:"
				+ this.senha);
	}

	/**
	 * Calcula o código de espalhamento (ou código de hash) de uma Labirntos.
	 * Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
	 * hashcode) da agenda representada pela instância à qual o método for aplicado.
	 * 
	 * @return o código de espalhamento do labirintos chamante do método.
	 */

	@Override
	public int hashCode() {

		int ret = 2000;

		ret = ret * 13 + new String(this.nome).hashCode();
		ret = ret * 13 + new String(this.dataCriacao).hashCode();
		ret = ret * 13 + new String(this.dataUltimaMod).hashCode();
		ret = ret * 13 + new String(this.lab).hashCode();
		ret = ret * 13 + new String(this.email).hashCode();
		ret = ret * 13 + new String(this.senha).hashCode();

		if (ret < 0)
			ret = -ret;

		return ret;

	}

	/**
	 * Verifica a igualdade entre dois dados de labirinto. Verifica se o Object
	 * fornecido como parâmetro representa um labirinto igual àquela representada
	 * pela instância à qual este método for aplicado, resultando true em caso
	 * afirmativo, ou false, caso contrário.
	 * 
	 * @param obj o objeto a ser comparado com a instância à qual esse método for
	 *            aplicado.
	 * @return true, caso o Object fornecido ao método e a instância chamante do
	 *         método representarem pilhas iguais, ou false, caso contrário.
	 */

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Lab))
			return false;

		Labirinto labis = (Labirinto) obj;

		if (this.nome != labis.nome)
			return false;

		if (this.dataCriacao != labis.dataCriacao)
			return false;

		if (this.email != labis.email)
			return false;

		if (this.senha != this.senha)
			return false;

		if (this.dataUltimaMod != labis.dataUltimaMod)
			return false;

		if (this.lab != labis.lab)
			return false;

		return true;

	}
	
	/**
	 * Este metodo e um auxiliar do clone para criar um modelo
	 * dos atributos desta classe como clone 
	 * @param modelo
	 * @throws Exception caso o modelo seja nulo lancamos uma execao
	 */

	public Labirinto(Labirinto modelo) throws Exception {

		if (modelo == null) {

			throw new Exception("Modelo Ausente");

		}

		this.nome = modelo.nome;
		this.dataCriacao = modelo.dataCriacao;
		this.dataUltimaMod = modelo.dataUltimaMod;
		this.lab = modelo.lab;
		this.email = modelo.email;
		this.senha = modelo.senha;

	}
	
	/**
	 * Este metodo chama o clone para fazer shallow copy
	 * do objeto da classe chamante.
	 * @return Retorna o objeto da classe;
	 * @throws sem chance de erros na geracao da copia
	 */

	public Object Clone() {

		Labirinto ret = null;

		try {

			ret = new Labirinto(this);

		} catch (Exception erro) {
			// ignorando Exception, pois sei que não ocorrera
		}

		return ret;

	}

}
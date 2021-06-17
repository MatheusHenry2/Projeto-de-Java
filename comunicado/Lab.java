package comunicado;

/**
 * A classe lab representa um conjunto de metodos para salvar as informacoes que o cliente
 * inserir na interface para salvar no banco de dados por exemplo alguns metodos aplicaveis a essa classe
 * sao os metodos obrigatorios e tambem os getters e setters que sao aplicaveis a esta classe
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 * */

import java.io.Serializable;

import bd.dbo.Labirinto;

public class Lab extends Comunicado implements Serializable, Cloneable {

	private String nome;
	private String dataCriacao;
	private String dataUltimaCriacao;
	private String conteudo;
	private String email;
	private String senha;

	/**
	 * Constroi uma nova instancia de acordo com os dados que o cliente enviou pela
	 * janela para salvar, e entao criamos uma nova instancia de Lab fornecendo um
	 * nome para o labirinto uma data de criacao uma data de ultima criacao o
	 * conteudo do labirinto e o email e senha do cliente que fez esse salvamento
	 * que sera recebido por esse servidor
	 * 
	 * @param nome do labirinto, data da criacao deste salvamente data do ultimo
	 *             salvamento deste cliente o conteudo do labirinto e o email e
	 *             senha do cliente que fez esse salvamento.
	 * @throws Quando algum dado que o cliente passou estiver vazio e lancado uma
	 *                execao.
	 */

	public Lab(String name, String dataCriac, String dataUlti, String lab, String email, String senha) {
		this.nome = name;
		this.dataCriacao = dataCriac;
		this.dataUltimaCriacao = dataUlti;
		this.conteudo = lab;
		this.email = email;
		this.senha = senha;
	}

	/**
	 * Quando o servidor desejar alterar o conteudo do labirinto que foi salvo por
	 * ele, quando recebeu do cliente ele ira chamar este metodo onde sera alterado
	 * o this.conteudo que guarda o conteudo do labirinto de acordo com a informacao
	 * que o cliente passou
	 * 
	 * @param o conteudo do labirinto que vira como string pelo parametro para
	 *          alteracao
	 * @throws caso o labirinto que o cliente passou pelo parametro venha vazio
	 *              lancaremos uma execao
	 */

	public void setConteudo(String lab) {
		this.conteudo = lab;
	}
	
	/**
	 * Este metodo e um auxiliar do clone para criar um modelo
	 * dos atributos desta classe como clone 
	 * @param modelo
	 * @throws Exception caso o modelo passado pelo parametro seja nulo lancamos
	 * uma execao
	 */

	public Lab(Lab modelo) throws Exception {

		if (modelo == null) {
			throw new Exception("Modelo Ausente");
		}

		this.nome = modelo.nome;
		this.dataCriacao = modelo.dataCriacao;
		this.dataUltimaCriacao = modelo.dataUltimaCriacao;
		this.conteudo = modelo.conteudo;
		this.email = modelo.email;
		this.senha = modelo.senha;

	}
	
	/**
	 * Este metodo chama o clone para fazer shallow copy
	 * do objeto da classe chamante.
	 * @return Retorna o objeto da classe;
	 * @throws sem chance de erros na geracao da copia da classe Lab
	 */
	
	public Object Clone() {

		Lab ret = null;

		try {

			ret = new Lab(this);

		} catch (Exception erro) {
			// ignorando Exception, pois sei que não ocorrera
		}

		return ret;

	}

	/**
	 * Este metodo e aplicavel quando o cliente desejar visualizar qual email ele
	 * salvou e entao o servidor ira pegar essa informacao para o cliente com um get
	 * 
	 * @return o email salvado pelo servidor pedido pelo cliente
	 */

	public String getEmail() {
		return this.email;
	}

	/**
	 * Este metodo e aplicavel quando o cliente desejar visualizar qual a senha que
	 * ele salvou e entao o servidor ira pegar essa informacao para o cliente com um
	 * get
	 * 
	 * @return a senha salva pelo servidor pedido pelo cliente
	 */

	public String getSenha() {
		return this.senha;
	}

	/**
	 * Este metodo e aplicavel quando o cliente desejar visualizar qual o nome que
	 * ele salvou e entao o servidor ira pegar essa informacao para o cliente com um
	 * get
	 * 
	 * @return o nome salvo pelo servidor pedido pelo cliente
	 */

	public String getNome() {
		return this.nome;
	}

	/**
	 * Este metodo e aplicavel quando o cliente desejar visualizar qual foi a data
	 * de criacao que ele salvou e entao o servidor ira pegar essa informacao para o
	 * cliente com um get
	 * 
	 * @return a data de criação pelo servidor pedido pelo cliente
	 */

	public String getDataCriacao() {
		return this.dataCriacao;
	}

	/**
	 * Este metodo e aplicavel quando o cliente desejar visualizar qual foi a data
	 * da ultima criacao que ele salvou e entao o servidor ira pegar essa informacao
	 * para o cliente com um get
	 * 
	 * @return a data da ultima criacao pelo servidor pedido pelo cliente
	 */

	public String getDataUltimaCriacao() {
		return this.dataUltimaCriacao;
	}

	/**
	 * Este metodo e aplicavel quando o cliente desejar visualizar qual foi o
	 * conteudo que foi salvo para o servidor do seu labirinto
	 * 
	 * @return retorna o labirinto cadastrado pelo servidor pedido pelo cliente
	 */

	public String getConteudo() {
		return this.conteudo;
	}

	/**
	 * Este e um metodo que produz um efeito visual para demonstrar quais
	 * informacoes pega do cliente foram salva pelo servidor no momento de
	 * cadastramento do seu labirinto
	 * 
	 * @return as informacoes gerais como nome do labirinto conteudo do lab data de
	 *         criacao data de ultima alteracao e alguns dados como email e senha
	 *         informada pelo cliente retornando em forma visual
	 */

	@Override
	public String toString() {
		return (this.nome + " \n\n " + this.conteudo + "\n\n Data de criacao: " + this.dataCriacao
				+ "\n Data ultima criacao: " + this.dataUltimaCriacao + "\n Email do cliente: " + this.email
				+ "\n Senha:" + this.senha);
	}

	/**
	 * Calcula o codigo de espalhamento de hash com os dados fornecidos pelo cliente
	 * ao servidor de Lab referenciado pelo cliente para o servidor representado a
	 * instancia a qual ele foi aplicavel
	 * 
	 * @return retorna o codigo de espalhamento de cada variavel de tipo primiritivo
	 *         em hash code para criar pequenas listas para facilitar a busca.
	 */

	@Override
	public int hashCode() {

		int ret = 2000;

		ret = ret * 13 + new String(this.nome).hashCode();
		ret = ret * 13 + new String(this.dataCriacao).hashCode();
		ret = ret * 13 + new String(this.dataUltimaCriacao).hashCode();
		ret = ret * 13 + new String(this.conteudo).hashCode();
		ret = ret * 13 + new String(this.email).hashCode();
		ret = ret * 13 + new String(this.senha).hashCode();

		if (ret < 0)
			ret = -ret;

		return ret;

	}

	/**
	 * Verifica a igualdade entre dados de dois clientes passadas ao servidor, se o
	 * objeto fornecido pelo parametro representa uma instancia igual a fornecida
	 * apresenta entao positivo são dados iguais do mesmo cliente caso contrario
	 * falso
	 * 
	 * @param o objeto a ser comparado a instancia passada do cliente para o servido
	 *          a fim de verificar se as informacao conhecidem
	 * @return true caso objeto fornecido possui todos mesmo atributos a mesma
	 *         instacia do passada do cliente e entao retornamos true caso contrario
	 *         falso
	 * 
	 */

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Lab))
			return false;

		Lab labis = (Lab) obj;

		if (this.nome != labis.nome)
			return false;

		if (this.dataCriacao != labis.dataCriacao)
			return false;

		if (this.email != labis.email)
			return false;

		if (this.senha != labis.senha)
			return false;

		if (this.dataUltimaCriacao != labis.dataUltimaCriacao)
			return false;

		if (this.conteudo != labis.conteudo)
			return false;

		return true;

	}

}
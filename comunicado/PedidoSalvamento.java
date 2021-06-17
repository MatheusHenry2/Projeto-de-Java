package comunicado;

/**
 * A classe pedido de salvamento representa um conjunto de metodos
 * para enviar as informacoes que o cliente salvou em lab e aqui para
 * serem enviadas para o servidor esta classe herda de comunicado
 * para fazer a comunicao dos parametros a serem enviados para o servidor 
 * fazer o controle das informacoes atravez de bancos de dados
 * alguns metodos que temos nesta classe sao alguns metodos obrigatorios e os
 * getter.
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021. 
 * */

import comunicado.Comunicado;
import comunicado.Lab;
import comunicado.PedidoSalvamento;

public class PedidoSalvamento extends Comunicado {
	private String email;
	private String senha;
	private Lab lab;
	
	   
    /**
     * Este metodo construi uma instancia de pedido de salvamento
     * o cliente ira nos enviar informacoes que sao cruciais para o servidor
     * fazer o salvamento no banco de dados ja que esta classe herda de comunicado
     * poderemos enviar o objeto desta classe para o servidor atraves do servidor.receba(objeto da classe)
     * @param o email e senha do cliente e o objeto Lab que vem da outra classe com informacoes
     * do conteudo labirinto e outras informacoes que iremos salvar para o servidor
     * @throws se algum campo das informacoes vier nulo lancaremos um execao avisando que algum campo
     * esta nulo na hora do pedido de salvamento para o servidor.
     * */ 
   

	public PedidoSalvamento(String email, String senha, Lab lab) throws Exception {

		if (email == null || lab == null || senha == null)
			throw new Exception("Campos nulos !");

		this.email = email;
		this.senha = senha;
		this.lab = lab;
	}
	

    /**
     * Este metodo e aplicavel quando o usuario desejar visualizar
     * qual campo esta sendo salvo como pedido de salvamento para o servidor
     * nesse caso o email
     * @return retorna o email que o cliente esta enviando como pedido de salvamento para o servidor.
     * */

	public String getEmail() {
		return this.email;
	}
	
    /**
     * Este metodo e aplicavel quando o usuario deseja
     * visualizar qual campo esta sendo salvo como pedido de salvamento para o servidor
     * nesse caso a senha que sera salva no servidor.
     * @return retorna a senha qual esta sendo enviada para o servidor como pedido de salvamento de informacao.
     * */

	public String getSenha() {
		return this.senha;
	}
	
	   /**
     * Este metodo e aplicavel quando o cliente desejar visualizar qual objeto lab
     * ele esta enviando como pedido de salvamento para o labirinto tratando se de um objeto
     * da instancia de lab nele contem varias informacoes como email senha labirinto datas de alteracao e criacao
     * entre outros atributos da classe referentes a este objeto Lab.
     * @return retorna o objeto da classe Lab.
     * */

	
	public Lab getLab() {
		return this.lab;
	}
	

    /**
     * Este metodo e aplicavel para visualizar a informacao que sera
     * enviada como pedido de salvamento para o servidor
     * o metodo no qual sera utilizado para visualizar de uma forma visual
     * os atributos que serao enviados para o servidor como pedido de salvamento ao mesmo.
     * @return retorna o toostring com efeitos visuais desta classe PedidoSalvamento.
     * */

	@Override
	public String toString() {
		return ("Email" + this.email + "Senha:" + this.senha + "Labirinto" + this.lab);

	}
	
	   /**
	    * Calcula o codigo de espalhamento com os dados fornecidos pelo cliente
	    * para serem enviados para o servidor ao qual a instancia dele esta sendo aplicavel
	    * @return retorna o codigo de hash dos atributos primitivos desta classe para facilitar a busca
	    * e o codigo Hash do objeto lab referente a esta classe.
	    * */

	@Override
	public int hashCode() {

		int ret = 2000;

		ret = ret * 13 + new String(this.email).hashCode();
		ret = ret * 13 + new String(this.senha).hashCode();

		if (this.lab != null)
			ret = ret * 13 + this.lab.hashCode();

		if (ret < 0)
			ret = -ret;

		return ret;
	}
	
	  /**
     * Este metodo verifica a igualdade de dados de dois cliente
     * que estao enviando seus dados para o servidor o objeto que vem
     * fornecido pelo parametro e o objeto que sera comparado com o chamante
     * para verificar a igualdade desses dois.
     * @return retorna positivo caso o objeto do chamante e o objeto do parametro
     * tenham obtido caso verdadeiro em todas comparacoes do equals e retorna falso
     * caso alguma comparacao nao proceda com verdadeiro.
     * */

	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof PedidoSalvamento))
			return false;

		PedidoSalvamento save = (PedidoSalvamento) obj;

		if (this.email != save.email)
			return false;

		if (this.senha != save.senha)
			return false;

		if (!this.lab.equals(obj))
			return false;

		return true;

	}

}
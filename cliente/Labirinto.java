package cliente;

import java.io.*;

/**
 * A classe labirinto representa um conjunto de metodos e atributos que tem como
 * base um labirinto do tipo matriz que realiza nela, repectivamente, As
 * instancias desta classe permite a realizacao de inumeras operacoes tais como,
 * por exemplo, metodos validar entradas e saidas
 * 
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 */

public class Labirinto implements Cloneable {

	private int qtdLinhas;

	private int qtdColunas;

	private char labirinto[][];

	private Pilha<Coordenada> caminho;

	private Pilha<Coordenada> inverso;

	private Coordenada atual = null;

	private Pilha<Coordenada> posAdj; // Posicoes adjacentes a posicao atual

	private Pilha<Pilha<Coordenada>> possibilidades;

	private char cima;

	private char embaixo;

	private char direita;

	private char esquerda;

	private char ondeEstou;

	/**
	 * Constroi uma nova instancia da classe Labirinto. Para tanto, deve ser
	 * fornecido um inteiro que se refere ao numero de linhas e colunas e o arquivo
	 * passado.
	 * 
	 * @param linhas e colunas que deve ser usado como capacidade.
	 * @throws Quando o labirinto for invalido.
	 */

	public Labirinto(String arq) throws Exception {

		Arquivo arq_lab = new Arquivo(arq);

		Arquivo copia = new Arquivo(arq);

		int qtdLinha = arq_lab.getUmInt();

		String str = arq_lab.getUmString();

		int qtdColuna = str.length();

		if (!isLabirintoValido(arq_lab, qtdLinha, qtdColuna)) {

			throw new Exception("Labirinto Invalido");

		}

		this.qtdLinhas = qtdLinha;

		this.qtdColunas = qtdColuna;

		gerarMatriz(copia);

		caminho = new Pilha<Coordenada>(this.qtdColunas * this.qtdLinhas);

	}

	/**
	 * Este construtor e apenas usado para inteface grafica.
	 */

	public Labirinto() {

	}

	/**
	 * Verifica se o labirinto tem saida, procurando pelo caracter S . Este metodo
	 * passa por toda a matriz do labirinto procurando o caracter S, e se encontrada
	 * a saida, retorna true.
	 * 
	 * @return true caso encontre a saida e false caso nao encontre false
	 */

	private boolean temSaida() throws Exception {

		for (int i = 0; i < this.qtdLinhas; i++) {

			for (int j = 0; j < this.qtdColunas; j++) {

				if (this.labirinto[i][j] == 'S') {

					return true;

				}

			}

		}

		return false;

	}

	/**
	 * Verifica se o labirinto possui apenas uma saida. Este metodo passa por todo o
	 * labirinto contando a quantidade de caractere S e retorna true caso tenha
	 * encontrado apenas uma saidada e false caso contrario.
	 * 
	 * @return o valor booleano da validacao da quantidade de saidas
	 * 
	 */

	private boolean umaSaida() {

		int flag = 0;

		for (int i = 0; i < this.qtdLinhas; i++) {

			for (int j = 0; j < this.qtdColunas; j++) {

				if (this.labirinto[i][j] == 'S')

					flag++;

			}

		}

		if (flag == 1)

			return true;

		return false;

	}

	/**
	 * Verifica se o labirinto tem apenas uma entrada. Este metodo percorre todo o
	 * labirinto contando a quantidade de caracteres 'E'
	 * 
	 * @return true caso encontre apenas um caractere 'E' e false caso contrario
	 */

	private boolean umaEntrada() {

		int flag = 0;

		for (int i = 0; i < this.qtdLinhas; i++) {

			for (int j = 0; j < this.qtdColunas; j++) {

				if (this.labirinto[i][j] == 'E')

					flag++;

			}

		}

		if (flag == 1)

			return true;

		return false;

	}

	/**
	 * Constroi uma copia da instancia da classe Labirinto. Para tanto, deve ser
	 * fornecida uma instancia da classe Labirinto para ser utilizada como modelo
	 * para a construcao da nova instancia criada.
	 * 
	 * @param modelo a instancia da classe Labirinto a ser usada como modelo.
	 * @throws Exception se o modelo for null.
	 */

	public Labirinto(Labirinto modelo) throws Exception {

		if (modelo == null) {

			throw new Exception("Modelo Ausente");

		}

		this.qtdColunas = modelo.qtdColunas;

		this.qtdLinhas = modelo.qtdLinhas;

		this.labirinto = modelo.labirinto;

	}

	/**
	 * 
	 * Este metodo e um dos metodos principais do nosso projeto onde ele chama
	 * varios metodo para solucao do labirinto e levando o usuario para a saida do
	 * mesmo este metodo conta com diversos instanciamentos de coordenada para as
	 * pilhas de coordenadas para ser feita a solucao do labirinto
	 * 
	 * @throws Exception  se o labirinto tiver mais de uma saida lancamos uma
	 *                    execaoo
	 * @throws Excepetion se o labirinto nao possuir uma entrada lancaamos uma
	 *                    execao
	 * @throws Excepetion se o labirinto possuir mais de 1 entrada lancamos uma
	 *                    execao
	 * @throws Exception  se o labirinto tiver mais de 1 saida lancamos uma execao
	 */

	public void resolver() throws Exception {

		if (!temSaida()) {

			throw new Exception("Nao tem saida sua anta");

		}

		this.atual = new Coordenada(encontrarEntrada());

		if (this.atual == null) {

			throw new Exception("Nao possui entrada");

		}

		if (!umaEntrada())

			throw new Exception("Possui mais de 1 entrada");

		if (!umaSaida())

			throw new Exception("Possui mais de 1 saida");

		this.caminho = new Pilha<Coordenada>(getQtdColunas() * getQtdLinhas());// 5 e 8 colunas 5* 8 = 40

		this.possibilidades = new Pilha<Pilha<Coordenada>>(getQtdColunas() * getQtdLinhas());

		while (getOndeEstou() != 'S') {

			preencherAdj();

			while (this.posAdj.isVazia()) {

				this.atual = caminho.recupereUmItem();

				this.caminho.removaUmItem();

				this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = ' ';

				this.posAdj = this.possibilidades.recupereUmItem();

				this.possibilidades.removaUmItem();

			}

			this.atual = this.posAdj.recupereUmItem();

			if (getOndeEstou() != 'S') {

				this.posAdj.removaUmItem();

				this.labirinto[this.atual.getLinha()][this.atual.getColuna()] = '*';

				this.caminho.guardeUmItem(this.atual);

				this.possibilidades.guardeUmItem(this.posAdj); //

			}

		}

		System.out.println();

		percorrerInverso();

	}

	/**
	 * Este metodo percorre a pilha de caminho e vai esvaziando ela e jogando todo
	 * conteudo na pilha de inverso para depois o usuario conseguir ver o caminho
	 * percorrido em ordem cronologica perfeitamente
	 */

	private void percorrerInverso() throws Exception {

		inverso = new Pilha<Coordenada>(caminho.getQuantidade());

		while (!caminho.isVazia()) {

			inverso.guardeUmItem(caminho.recupereUmItem());

			caminho.removaUmItem();

		}

	}

	/**
	 * Este metodo printar a resolucao do labirinto em coordenadas ele resulta o
	 * caminho percorrido pelo usuario pelo usuario atravez de uma pilha de inverso
	 * 
	 * @return retorna para o usuario todo caminho percorrido do labirinto em
	 *         coordenadas
	 */

	public String printarResolucao() throws Exception {

		System.out.println("Estas sao as Coordenada que levam a saida:");

		int i = 0;

		String str = "";

		while (!inverso.isVazia()) {
			i++;
			if (i < 23) {
				str = str + inverso.recupereUmItem() + "  ";
				System.out.print(" " + inverso.recupereUmItem());
				inverso.removaUmItem();
			} else {
				str = str + inverso.recupereUmItem() + "   \n";
				System.out.print(" " + inverso.recupereUmItem());
				inverso.removaUmItem();
				i = 0;
			}
		}

		System.out.println("\n");

		return str;

	}

	/**
	 * Este metodo e aplicavel quando o usuario comeca ver as possibilidades que ele
	 * pode andar e comecar guardar cada coordenada da posicao atual dele na pilha
	 * de coordenadas para depois poder se locomover este metodo instancia a
	 * coordenada adjascente atual da posicao dele para pilha de adj para depois
	 * comecar escolher as possibilidades para percorrer todo labirinto e resolvelo.
	 * 
	 */

	private void preencherAdj() throws Exception {

		this.posAdj = new Pilha<Coordenada>(3);

		Coordenada coorAdj;

		coorAdj = coordenadaDeCima(); // (1.2)

		if (coorAdj != null) {

			if (getCima() == ' ' || getCima() == 'S')// da pra ele anda

				this.posAdj.guardeUmItem(coorAdj);

		}

		coorAdj = coordenadaDebaixo();

		if (coorAdj != null && coorAdj.getLinha() < getQtdLinhas()) { // nao da

			if (getEmbaixo() == ' ' || getEmbaixo() == 'S')

				this.posAdj.guardeUmItem(coorAdj);

		}

		coorAdj = coordenadaDaDireita();

		if (coorAdj != null && coorAdj.getColuna() < getQtdColunas()) {// nap da

			if (getDireita() == ' ' || getDireita() == 'S')

				this.posAdj.guardeUmItem(coorAdj);

		}

		coorAdj = coordenadaDaEsquerda();

		if (coorAdj != null) {

			if (getEsquerda() == ' ' || getEsquerda() == 'S') // da pra andar

				this.posAdj.guardeUmItem(coorAdj);

		}

	}

	/**
	 * Este metodo instancia a posicao da coordenada de cima ao qual ele esta no
	 * momento
	 * 
	 * @return o retorno deste metodo e a coordenada da posicao de cima do usuario
	 * @throws Exception caso de execao que a posicao de cima seja invalida
	 *                   retornamos ela como o ret
	 */

	private Coordenada coordenadaDeCima() throws Exception {

		Coordenada ret = null;

		try {

			ret = new Coordenada(this.atual.getLinha() - 1, this.atual.getColuna());

		} catch (Exception erro) {

			return null;

		}

		return ret;

	}

	/**
	 * Este metodo instancia a posicao da coordenada de baixo ao qual ele esta no
	 * momento
	 * 
	 * @return o retorno deste metodo e a coordenada da posicao da baixo do usuario
	 * @throws Exception caso de execao que a posicao da direita seja invalida
	 *                   retornamos ela como o ret
	 */

	private Coordenada coordenadaDebaixo() throws Exception {

		Coordenada ret = null;

		try {

			ret = new Coordenada(this.atual.getLinha() + 1, this.atual.getColuna());

		} catch (Exception erro) {

			return ret;

		}

		return ret;

	}

	/**
	 * Este metodo instancia a posicao da coordenada da direita ao qual ele esta no
	 * momento
	 * 
	 * @return o retorno deste metodo e a coordenada da posicao da direita do
	 *         usuario
	 * @throws Exception caso de execao que a posicao da direita seja Invalida
	 *                   retornamos ela como o ret
	 */

	private Coordenada coordenadaDaDireita() throws Exception {

		Coordenada ret = null;

		try {

			ret = new Coordenada(this.atual.getLinha(), this.atual.getColuna() + 1);

		} catch (Exception erro) {

			return ret;

		}

		return ret;

	}

	/**
	 * Este metodo instancia a posicao da coordenada da esquerda ao qual ele esta no
	 * momento
	 * 
	 * @return o retorno deste metodo e a coordenada da posicao da esquerda do
	 *         usuario
	 * @throws Exception caso de execao que a posicao da esquerda seja invalida
	 *                   retornamos ela como o ret
	 */

	private Coordenada coordenadaDaEsquerda() throws Exception {

		Coordenada ret = null;

		try {

			ret = new Coordenada(this.atual.getLinha(), this.atual.getColuna() - 1);

		} catch (Exception erro) {

			return ret;

		}

		return ret;

	}

	/**
	 * 
	 * Este metodo verificar se o labirinto tem uma entrada e valida o numero de
	 * parede ao mesmo tempo primeiramente o metodo encontrar entrada vai percorrer
	 * todo labirinto procurando um caractere q seja igual a E se encontrar iremos
	 * instanciar essa posicao da matriz para classe coordenada segundamente
	 * validamos se a parede contem caracteres preenchendo ela caso contrario seria
	 * invalida
	 * 
	 * @return ele retorna a coordenada ao qual foi encontrado a entrada do
	 *         labirinto
	 * @throws Exception caso o labirinto percebea que contem uma parede sem
	 *                   caractereses a preenchendo lanï¿½amos uma exeï¿½ï¿½o
	 */

	/**
	 * Este metodo verificar se o labirinto tem uma entrada e valida o numero de
	 * parede ao mesmo tempo primeiramente o metodo encontrar entrada vai percorrer
	 * todo labirinto procurando um caractere q seja igual a E se encontrar iremos
	 * instanciar essa posicao da matriz para classe coordenada segundamente
	 * validamos se a parede contem caracteres preenchendo ela caso contrario seria
	 * invalida
	 * 
	 * @return ele retorna a coordenada ao qual foi encontrado a entrada do
	 *         labirinto
	 * @throws Exception caso o labirinto percebea que contem uma parede sem
	 *                   caractereses a preenchendo lancamos uma execao
	 */

	private Coordenada encontrarEntrada() throws Exception {

		Coordenada ret = null;

		int cont = 0;

		for (int i = 0; i < this.qtdLinhas; i++) {

			for (int j = 0; j < this.qtdColunas; j++) {

				if ((i == 0 || i == this.qtdLinhas - 1)) { // PAREDE DE CIMA E DE BAIXO

					if (this.labirinto[i][j] == 'E') {

						ret = new Coordenada(i, j);

						cont++;

					}

					if (this.labirinto[i][j] == ' ')

						throw new Exception("Sem parede");

				}

				if ((j == 0 || j == this.qtdColunas - 1)) { // PAREDE DA ESQUERDA E DA DIREITA

					if (this.labirinto[i][j] == 'E') {

						ret = new Coordenada(i, j); // (1,0)

						cont++;

					}

					if (this.labirinto[i][j] == ' ')

						throw new Exception("Sem parede");

				}

			}

		}

		if (cont > 1)

			throw new Exception("Mais de duas entradas");

		return ret;

	}

	/**
	 * Este metodo e o metodo clone ao qual se refere no nosso caso ao labirinto
	 * 
	 * @return retorna uma copia do labirinto chamante do metodo
	 */

	public Object Clone() {

		Labirinto ret = null;

		try {

			ret = new Labirinto(this);

		} catch (Exception erro) {

		}

		return ret;

	}

	/**
	 * Este metodo faz a validacao do labirinto ele percorre cada posicao do
	 * labirinto para verificar se o numero de colunas e linhas e valido e um metodo
	 * de validacao para posteriormente a criacao do labirinto no atributo this.
	 * 
	 * @param labn      no 1 parametro temos o arquivo do labirinto que usamos para
	 *                  fazer a validacao
	 * @param qtdLinha  no 2 parametro temos o numero de linhas passado pelo
	 *                  construtor para comecar a validacao
	 * @param qtdColuna no 3 parametro temos o numero de colunas passado pelo
	 *                  construtor para comecaar a validacao
	 * @return ele tem um retorno de booleano caso for valido retornara true caso
	 *         contrario retornara falso
	 */

	private boolean isLabirintoValido(Arquivo lab, int qtdLinha, int qtdColuna) throws Exception {

		String str;

		int coluna;

		for (int i = 2; i <= qtdLinha; i++) {

			str = lab.getUmString();

			coluna = str.length();

			if (qtdColuna != coluna) {

				return false;

			}

		}

		str = lab.getUmString();

		if (str != null) {

			return false;

		}

		return true;

	}

	/**
	 * Este metodo gera uma matriz para ser atribuido ao this ao qual se refere o
	 * atributo do nosso labirinto
	 * 
	 * @param copia ele pega a copia do arquivo instanciado para o arquivo depois da
	 *              validacao, ele constroi cada caractere com essa copia do txt
	 */

	private void gerarMatriz(Arquivo copia) {

		try {

			String str = null;

			copia.getUmInt();

			this.labirinto = new char[this.qtdLinhas][this.qtdColunas];

			for (int i = 0; i < this.qtdLinhas; i++) {

				str = copia.getUmString();

				for (int j = 0; j < this.qtdColunas; j++) {
					str += this.labirinto[i][j] = str.charAt(j);
				}

			}

		} catch (Exception erro) {

		}

	}

	@Override

	/**
	 * Gera uma representação visual do Labirinto produz e resulta uma String com o
	 * Labirinto
	 * 
	 * @return um Labirinto contendo o caminho percorrido
	 */

	public String toString() {

		String ret = "";

		if (this.labirinto == null)

			ret = "Labirinto inexistente";

		else {

			for (int i = 0; i < this.qtdLinhas; i++) {

				for (int j = 0; j < this.qtdColunas; j++) {

					ret += Character.toString(this.labirinto[i][j]);

				}

				ret += "\n";

			}

		}

		return ret;

	}

	/**
	 * Calcula o codigo de espalhamento (ou codigo de hash) de um Labirinto. Calcula
	 * e resulta o codigo de espalhamento (ou codigo de hash, ou ainda o hashcode)
	 * da agenda representada pela instancia e qual o metodo for aplicado.
	 * 
	 * @return o codigo de espalhamento da agenda chamante do metodo.
	 */

	@Override

	public int hashCode() {

		int ret = 7;

		ret = ret * 7 + new Integer(this.qtdLinhas).hashCode();

		ret = ret * 7 + new Integer(this.qtdColunas).hashCode();

		ret = ret * 7 + new Character(this.cima).hashCode();

		ret = ret * 7 + new Character(this.embaixo).hashCode();

		ret = ret * 7 + new Character(this.direita).hashCode();

		ret = ret * 7 + new Character(this.esquerda).hashCode();

		for (int i = 0; i < this.qtdLinhas; i++) {

			for (int j = 0; j < this.qtdColunas; j++)

				ret = ret * 7 + new Character(this.labirinto[i][j]).hashCode();

		}

		if (ret < 0)

			ret = ret - ret;

		return ret;

	}

	/**
	 * Verifica a igualdade entre dois Labirintos Verifica se o Object fornecido
	 * como parametro representa um labirinto igual aquela representada pela
	 * instancia e qual este metodo for aplicado, resultando true em caso
	 * afirmativo, ou false, caso contrario
	 * 
	 * @param obj o objeto a ser comparado com a instancia e qual esse metodo for
	 *            aplicado.
	 * @return true, caso o Object fornecido ao metodo e a instancia chamante do
	 *         metodo representarem labirintos iguais, ou false, caso contrario.
	 * 
	 */

	@Override

	public boolean equals(Object obj) {

		if (this == obj) // endereco

			return true;

		if (obj == null)

			return false;

		if (!(obj instanceof Labirinto))

			return false;

		Labirinto labi = (Labirinto) obj;

		if (this.qtdLinhas != labi.qtdLinhas) // 5 6

			return false;

		if (this.qtdColunas != labi.qtdColunas) // 7 6

			return false;

		for (int i = 0; i < this.qtdLinhas; i++) {

			for (int j = 0; j < this.qtdColunas; j++) {

				if (this.labirinto[i][j] != labi.labirinto[i][j])

					return false;

			}

		}

		return true;

	}

	/**
	 * Obtem o numero de colunas do Labirinto Resulta o numero de colunas do
	 * Labirinto
	 * 
	 * @return o numero de colunas do Labirinto
	 * @throws Exception quando o numero de colunas estiver vazio nao foi atribuido
	 *                   ao this
	 */

	public int getQtdColunas() throws Exception {

		try {

			return this.qtdColunas;

		} catch (Exception erro) {

			throw new Exception("Colunas invalidas");

		}

	}

	/**
	 * Obtem o numero de linhas do labirinto. Resulta o numero de linhas do
	 * labirinto
	 * 
	 * @return o numero de linhas do Labirinto @ throws Exception quando o numero de
	 *         linhas estiver vazio nao foi atribuido ao this.
	 */

	public int getQtdLinhas() throws Exception {

		try {

			return this.qtdLinhas;

		} catch (Exception erro) {

			throw new Exception("Linhas nulas");

		}

	}

	/**
	 * Obtem a linha de cima do labirinto Resulta a linha de cima do Labirinto
	 * 
	 * @return a linha de cima do labirinto @ throws Exception quando nao tem linha
	 *         em cima uma linha nula exemplo
	 */

	private char getCima() throws Exception {

		try {

			return this.labirinto[this.atual.getLinha() - 1][this.atual.getColuna()];

		} catch (Exception erro) {

			throw new Exception("Linha de cima invalida");

		}

	}

	/**
	 * obtem a linha de baixo do labirinto Resulta a linha de baixo do labirinto*
	 * 
	 * @return a linha de baixo do labirinto @ throws Exception quando a linha de
	 * baixo e nula um exemplo
	 */

	private char getEmbaixo() throws Exception {

		try {

			return this.labirinto[this.atual.getLinha() + 1][this.atual.getColuna()];

		} catch (Exception erro) {

			throw new Exception("Linha de baixo invalida");

		}

	}

	/**
	 * obtem a coordenada da linha direita Resulta a posicao da linha a direita do
	 * labirinto
	 * 
	 * @return a linha direita do labirinto
	 * @throws Exception quando nao tem linhas da direita ou nula
	 */

	private char getDireita() throws Exception {

		try {

			return this.labirinto[this.atual.getLinha()][this.atual.getColuna() + 1];

		} catch (Exception erro) {

			throw new Exception("Linha direita invalida");

		}

	}

	/**
	 * Obtem a coordenada da linha esquerda do labirinto Resulta a posicao da linha
	 * esquerda do labirinto
	 * 
	 * @return a coordenada da linha esquerda da posicao atual
	 * @throws Exception quando a linha esquerda da posicao atual e nula
	 */

	private char getEsquerda() throws Exception {

		try {

			return this.labirinto[this.atual.getLinha()][this.atual.getColuna() - 1];

		} catch (Exception erro) {

			throw new Exception("linha esquerda nula");

		}

	}

	/**
	 * obtem a coordenada atual do labirinto Resulta a posicao atual da pessoa no
	 * labirinto
	 * 
	 * @return a coordenada que a pessoa esta no momento atual
	 */

	private char getOndeEstou() {

		return this.labirinto[this.atual.getLinha()][this.atual.getColuna()];

	}
}
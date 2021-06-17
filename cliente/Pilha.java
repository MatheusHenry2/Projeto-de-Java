package cliente;

/**
A classe Pilha representa uma classe genérica onde respectivamente
a classe pode empilhar qualquer tipo de informação no nosso caso estamos
empilhando coordenadas a qual se refere do labirinto
Instâncias desta classe permitem a realização do salvamento de dados
nela encontramos por exemplo métodos para incluir, excluir, recuperar
e os métodos obrigatorios
@author Matheus Henry, Danilo Montovaneli, Matheus camargo
@since 2021.
*/


public class Pilha <X>
{
    private Object[] elemento; // private X[] elemento;
    private int      tamanhoInicial;
    private int      ultimo = -1; // vazia
    
    /*// construtor padrao, pois nao tem parametros
    public Pilha ()
    {
        this.elemento       = new Object [10]; // this.elemento = new X [10];
        this.tamanhoInicial = 10;
    }*/
    
    
    /**
    Constroi uma nova instância da classe pilha 
    para tanto, deve ser fornecido um tamanho como capacidade
    da instância recém criada
    @param o tamanho como número inteiro a ser utilizado como tamanho da pilha
    @throws Exception se a capacidade for negativa ou zero
   */
    
    public Pilha (int tamanho) throws Exception
    {
        if (tamanho<=0)
            throw new Exception ("Tamanho invalido");
            
        this.elemento       = new Object [tamanho]; // this.elemento = new X [tamanho];
        this.tamanhoInicial = tamanho;
    }
    
    /**
    Obtem a quantidade de elementos na pilha
    Resulta o total de elementos da istância a qual este método
    for aplicado
    @return a quantidade de elementos da pilha.
    */
    
    public int getQuantidade ()
    {
        return this.ultimo+1;
    }
    
    /**
    Redimensiona um novo tamanho para pilha
    Quando a pilha estiver cheia e o usuário tenta inserir um dado
    ele ira chamar este método para redimensionar um novo tamanho para guardar os dados
    @param o fator que se refere a quantidade de vezes que o tamanho da pilha
    vai ser aumentado
    */

    
    private void redimensioneSe (float fator)
    {
        // X[] novo = new X [Math.round(this.elemento.length*fator)];
        Object[] novo = new Object [Math.round(this.elemento.length*fator)];
        
        for(int i=0; i<=this.ultimo; i++)
            novo[i] = this.elemento[i];

        this.elemento = novo;
    }
    
    /**
    Adiciona um novo dado para a pilha
    Quando o usuário chamar o método guarde um item ele
    ira guardar esse dado no topo da pilha a qual foi fornecido
    @param um dado gênerico nesse caso estamos guardando coordenadas 
    a qual se refere a classe cordenada onde como exemplo entra (1,0) na pilha
    @throws Exception se o dado for fornecido for nulo ocrorrera uma exeção
    pois não faz sentido guardar algo nulo
    */
   
    public void guardeUmItem (X x) throws Exception
    {
        if (x==null)
            throw new Exception ("Valor ausente");
        
        if (this.ultimo+1==this.elemento.length) // cheia
            this.redimensioneSe (2.0F);
        
        if(x instanceof Cloneable) {
        	Clonador <X> clonador = new Clonador <X>();
        	this.elemento[ultimo+1] = clonador.clone(x);
        }
            
        this.ultimo++;
        this.elemento[this.ultimo]=x;
    }

    /**
    Adiciona um novo dado para a pilha
    Quando o usuário chamar o método guarde um item ele
    ira guardar esse dado no topo da pilha a qual foi fornecido
    @param um dado gênerico nesse caso estamos guardando coordenadas 
    a qual se refere a classe cordenada onde como exemplo entra (1,0) na pilha
    @throws Exception se o dado for fornecido for nulo ocrorrera uma exeção
    pois não faz sentido guardar algo nulo
    */
    
    public X recupereUmItem () throws Exception
    {
        if (this.ultimo==-1)
            throw new Exception ("Pilha vazia");
            
        return (X)this.elemento[this.ultimo];
    }

    /**
    Remove um dado da pilha
    Como se trata de uma pilha ela desempilha o ultimo dado no topo
    ao qual no nosso caso se aplica a coordendas e apos fazer a remocao fazemos um cheeckup
    do tamanho para ver se podemos diminuir o tamanho da pilha para não ficar ocupando espaço de memória
    @throws Exception Quando a pilha estiver vazia e o usuário tentar remover algo
    por lógica lançamos uma exeção pois não tem o que remover
   */
   
    
    public void removaUmItem () throws Exception
    {
        if (this.ultimo==-1) // vazia
            throw new Exception ("Nada a remover");

        this.elemento[this.ultimo] = null;
        this.ultimo--;

        if (this.elemento.length>this.tamanhoInicial &&
            this.ultimo+1<=Math.round(this.elemento.length*0.25F))
            this.redimensioneSe (0.5F);
    }
    
    /*
    public boolean isCheia ()
    {
        if(this.ultimo+1==this.elemento.length)
            return true;
        return false;
    }
    */
    
    /**
    Este método verifica se a pilha chegou no seu tamanho máximo
    ao qual ocorre quando o último tem o tamanho igual ao this.elemento.lenght
    @return ele retorna falso ou verdadeiro se a comparação no caso for true e igual
    se não retorna falso ao qual foi aplicado.
   */
    
    public boolean isCheia ()
    {
        return this.ultimo+1==this.elemento.length;
    }
    /*
    public boolean isVazia ()
    {
        if(this.ultimo==-1)
            return true;
        return false;
    }
    */
    
    /**
    Este método verifica se a pilha chegou no seu tamanho máximo
    ao qual ocorre quando o último tem o tamanho igual ao this.elemento.lenght
    @return ele retorna falso ou verdadeiro se a comparação no caso for true e igual
    se não retorna falso ao qual foi aplicado.
   */
    
    public boolean isVazia ()
    {
        return this.ultimo==-1;
    }
    
    /**
    Gera uma representação textual de todo conteúdo da pilha
    Produz uma representação visual de todos dado contido na pilha
    no nosso caso as coordenadas empilhadas.
    @return um String contendo todo o conteúdo da pilha.
    */
    
    @Override
    public String toString ()
    {
        String ret;
        
        if (this.ultimo==0)
            ret="1 elemento";
        else
            ret=(this.ultimo+1)+" elementos";
            
        if (this.ultimo!=-1)
            ret += ", sendo o ultimo "+this.elemento[this.ultimo];
        
        return ret;
    }
    
    /**
    Verifica a igualdade entre duas pilhas.
    Verifica se o Object fornecido como parâmetro representa uma
    pilha igual àquela representada pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem pilhas iguais, ou false, caso contrário.
    */
    
    
    @Override
    public boolean equals (Object obj)
    {
        if(this==obj) // Comparando o endereço do this que é o chamante com o objeto
            return true;

        if(obj==null) // só estou testando o obj, porque sei que o this NUNCA é null
            return false;

        if(this.getClass()!=obj.getClass())// Comparando se as 2 classes sao do tipo pilha 
            return false;                  // exemplo se ele fazer if(p.equals("puc") o object class retorna string e da false

        Pilha<X> pil = (Pilha<X>) obj; // são objetos que estão em endereços difentes, que ele não é nulo e também que é pilha
                                       // você sabe que esta comparando 2 pilhas uma com a outra e nenhuma é nula

        if(this.ultimo!=pil.ultimo) // Compara se o ultimo elemento das pilhas sao iguais
            return false;           // se retornar false quer dizer que o ultimo elemento nao é igual  pilhas diferentes

        if(this.tamanhoInicial!=pil.tamanhoInicial) // Comparando o tamanho das 2 pilhas se tiverem tamanhos diferentes
            return false;                           // nao sao iguais

        for(int i=0 ; i<=this.ultimo; i++)
            if(!this.elemento[i].equals(pil.elemento[i])) // aqui ele compara cada elemento do vetor da pilha
                return false;                             // se algum retornar false nao sao iguais

        return true;  // se nenhuma validação for aceita retornar true e pilhas iguais ==
    }

    
    /**
    Calcula o código de espalhamento (ou código de hash) de uma pilha.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da agenda representada pela instância à qual o método for aplicado.
    @return o código de espalhamento da pilha chamante do método.
    */
    

    @Override
    public int hashCode ()
    {
        int ret=666/*qualquer positivo*/;

        ret = ret*7/*primo*/ + new Integer(this.ultimo        ).hashCode(); //Voce muliplica os atributo por um primo
        ret = ret*7/*primo*/ + new Integer(this.tamanhoInicial).hashCode(); //Voce muliplica os atributo por um primo

        for (int i=0; i<=this.ultimo; i++)
            ret = ret*7/*primo*/ + this.elemento[i].hashCode(); // multiplicando e acumulando cada indice do vetor por um primo

        if (ret<0) // caso for negativo vire ele positivo
            ret=-ret;

        return ret;
    }
}
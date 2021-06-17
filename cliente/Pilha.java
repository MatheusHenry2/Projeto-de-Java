package cliente;

/**
A classe Pilha representa uma classe gen�rica onde respectivamente
a classe pode empilhar qualquer tipo de informa��o no nosso caso estamos
empilhando coordenadas a qual se refere do labirinto
Inst�ncias desta classe permitem a realiza��o do salvamento de dados
nela encontramos por exemplo m�todos para incluir, excluir, recuperar
e os m�todos obrigatorios
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
    Constroi uma nova inst�ncia da classe pilha 
    para tanto, deve ser fornecido um tamanho como capacidade
    da inst�ncia rec�m criada
    @param o tamanho como n�mero inteiro a ser utilizado como tamanho da pilha
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
    Resulta o total de elementos da ist�ncia a qual este m�todo
    for aplicado
    @return a quantidade de elementos da pilha.
    */
    
    public int getQuantidade ()
    {
        return this.ultimo+1;
    }
    
    /**
    Redimensiona um novo tamanho para pilha
    Quando a pilha estiver cheia e o usu�rio tenta inserir um dado
    ele ira chamar este m�todo para redimensionar um novo tamanho para guardar os dados
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
    Quando o usu�rio chamar o m�todo guarde um item ele
    ira guardar esse dado no topo da pilha a qual foi fornecido
    @param um dado g�nerico nesse caso estamos guardando coordenadas 
    a qual se refere a classe cordenada onde como exemplo entra (1,0) na pilha
    @throws Exception se o dado for fornecido for nulo ocrorrera uma exe��o
    pois n�o faz sentido guardar algo nulo
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
    Quando o usu�rio chamar o m�todo guarde um item ele
    ira guardar esse dado no topo da pilha a qual foi fornecido
    @param um dado g�nerico nesse caso estamos guardando coordenadas 
    a qual se refere a classe cordenada onde como exemplo entra (1,0) na pilha
    @throws Exception se o dado for fornecido for nulo ocrorrera uma exe��o
    pois n�o faz sentido guardar algo nulo
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
    do tamanho para ver se podemos diminuir o tamanho da pilha para n�o ficar ocupando espa�o de mem�ria
    @throws Exception Quando a pilha estiver vazia e o usu�rio tentar remover algo
    por l�gica lan�amos uma exe��o pois n�o tem o que remover
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
    Este m�todo verifica se a pilha chegou no seu tamanho m�ximo
    ao qual ocorre quando o �ltimo tem o tamanho igual ao this.elemento.lenght
    @return ele retorna falso ou verdadeiro se a compara��o no caso for true e igual
    se n�o retorna falso ao qual foi aplicado.
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
    Este m�todo verifica se a pilha chegou no seu tamanho m�ximo
    ao qual ocorre quando o �ltimo tem o tamanho igual ao this.elemento.lenght
    @return ele retorna falso ou verdadeiro se a compara��o no caso for true e igual
    se n�o retorna falso ao qual foi aplicado.
   */
    
    public boolean isVazia ()
    {
        return this.ultimo==-1;
    }
    
    /**
    Gera uma representa��o textual de todo conte�do da pilha
    Produz uma representa��o visual de todos dado contido na pilha
    no nosso caso as coordenadas empilhadas.
    @return um String contendo todo o conte�do da pilha.
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
    Verifica se o Object fornecido como par�metro representa uma
    pilha igual �quela representada pela inst�ncia � qual este
    m�todo for aplicado, resultando true em caso afirmativo,
    ou false, caso contr�rio.
    @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
            for aplicado.
    @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
            m�todo representarem pilhas iguais, ou false, caso contr�rio.
    */
    
    
    @Override
    public boolean equals (Object obj)
    {
        if(this==obj) // Comparando o endere�o do this que � o chamante com o objeto
            return true;

        if(obj==null) // s� estou testando o obj, porque sei que o this NUNCA � null
            return false;

        if(this.getClass()!=obj.getClass())// Comparando se as 2 classes sao do tipo pilha 
            return false;                  // exemplo se ele fazer if(p.equals("puc") o object class retorna string e da false

        Pilha<X> pil = (Pilha<X>) obj; // s�o objetos que est�o em endere�os difentes, que ele n�o � nulo e tamb�m que � pilha
                                       // voc� sabe que esta comparando 2 pilhas uma com a outra e nenhuma � nula

        if(this.ultimo!=pil.ultimo) // Compara se o ultimo elemento das pilhas sao iguais
            return false;           // se retornar false quer dizer que o ultimo elemento nao � igual  pilhas diferentes

        if(this.tamanhoInicial!=pil.tamanhoInicial) // Comparando o tamanho das 2 pilhas se tiverem tamanhos diferentes
            return false;                           // nao sao iguais

        for(int i=0 ; i<=this.ultimo; i++)
            if(!this.elemento[i].equals(pil.elemento[i])) // aqui ele compara cada elemento do vetor da pilha
                return false;                             // se algum retornar false nao sao iguais

        return true;  // se nenhuma valida��o for aceita retornar true e pilhas iguais ==
    }

    
    /**
    Calcula o c�digo de espalhamento (ou c�digo de hash) de uma pilha.
    Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
    hashcode) da agenda representada pela inst�ncia � qual o m�todo for aplicado.
    @return o c�digo de espalhamento da pilha chamante do m�todo.
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
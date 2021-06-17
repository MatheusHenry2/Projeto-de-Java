package cliente;

import java.lang.reflect.*;

public class Clonador<X>
{
    public X clone (X x)
    {        
        // assim obtemos a classe da instancia no objeto
        // x, que, conforme sabemos, � a classe X, e a
        // armazenamos no objeto chamado classe
        Class<?> classe = x.getClass();
        
        // null porque chamaremos um metodo sem parametros
        Class<?>[] tpsParmsForms = null;
        
        // assim obtemos o metodo chamado clone, sem parametros,
        // da Class<?> armazenada no objeto classe (sabemos que
        // a classe � a classe X)
        Method metodo=null;
        try
        {
            metodo = classe.getMethod ("clone",tpsParmsForms);
        }
        catch (NoSuchMethodException erro)
        {}
        
        // null porque chamaremos um metodo sem parametros
        Object[] parmsReais = null;
                
        // assim chamamos o metodo armazenado no objeto chamado
        // metodo, fazendo com que o objeto chamado x seja para
        // ele o objeto chamante (o this) e fazendo com que
        // receba nao receba parametros reais (por isso o vetor
        // nulo chamado parmsReais � fornecido; o resultado da
        // chamada, que certamente � da classe X, mas que est�
        // sendo retornado como Object, tem seu tipo mudado
        // para X e, ent�o, � atribuido ao objeto chamado ret
        X ret=null;
        try
        {
            ret = (X)metodo.invoke(x,parmsReais);
        }
        catch (InvocationTargetException erro)
        {}
        catch (IllegalAccessException erro)
        {}

        // ret = (X)x.clone();
        
        return ret;
    }
}

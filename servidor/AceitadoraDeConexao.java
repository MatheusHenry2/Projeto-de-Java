package servidor;

/**
 * Esta classe aceitadora de conexao tem o papel de validar a conexao entre
 * servidor e cliente criando um vetor de usuarios para ser contabilizados 
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 * */

import java.net.*;
import java.util.*;

import comunicado.Parceiro;

public class AceitadoraDeConexao extends Thread
{
    private ServerSocket        pedido;
    private ArrayList<Parceiro> usuarios;
    private int numerClientes = 0;
    
    
    /** O construtor possui extends de thread um ArrayList que guardara os parceiros
     * que por sua vez sao usuarios conectados ao servidor onde esses usuarios sao contabilizados
     * e mostrados em tempo real a sua conexao com o servidor
     * @throws caso aconteca algum erro na porta usada e com a conexao do usuario
     *  lancamos uma execao para informar que ocorreu algum erro.
     */

    public AceitadoraDeConexao (String porta, ArrayList<Parceiro> usuarios) throws Exception
    {
        if (porta==null)
            throw new Exception ("Porta ausente");

        try
        {
            this.pedido =
            new ServerSocket (Integer.parseInt(porta));
        }
        catch (Exception  erro)
        {
            throw new Exception ("Porta invalida");
        }

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.usuarios = usuarios;
    }
    
    /** o metodo run faz a criacao de um socket de conexao que recebe os pedidos por meio do this
     * e printa para o servidor  os clientes conectados ao servidor  mostrando o endereços de ip dos clientes
     * conectados e aprensenta na tela  de execucao do servidor 
     * */

    public void run ()
    {
        for(;;)
        {
            Socket conexao=null;
            try
            {
                conexao = this.pedido.accept();
                   
                this.numerClientes++;
                System.out.println("Cliente conectado " + conexao.getInetAddress().getHostAddress());
                
            }
            catch (Exception erro)
            {
                continue;
            }

            SupervisoraDeConexao supervisoraDeConexao=null;
            try
            {
                supervisoraDeConexao =
                new SupervisoraDeConexao (conexao, usuarios);
            }
            catch (Exception erro)
            {} // sei que passei parametros corretos para o construtor
            supervisoraDeConexao.start();
        }
    }
}
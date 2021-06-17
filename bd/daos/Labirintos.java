package bd.daos;

/**
 * Esta classe e uma das classes onde ira lidar com as informacoes que o usuario manda
 * para o servidor para fazer o salvamento delas num SGBD para fazer o salvamento das informacoes
 * updates das informacoes excluir etc no proprio banco de dados configurado para suportar a tabela
 * com esses atributos.
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 */

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbo.*;
import comunicado.Lab;

public class Labirintos
{
	
	/**
	 * Este metodo ira verificar se o email e nome que o usuario passou para o servidor
	 * fazer a busca no banco de dados esta realmente cadastrado e fara um select no banco de dados
	 * com essas duas informacoes para verificar se elas estao cadastradas no banco de dados
	 * @param email
	 * @param nome
	 * @return
	 * @throws Exception
	 */
	
    public static boolean cadastrado (String email, String nome) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM labirinto " +
                  "WHERE email = ? and nome = ?";
            //select * from labirinto
            //where nome='danilo' and email='testeemail@gmail.com';

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, email);
            BDOracle.COMANDO.setString (2, nome);
            
            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

            /* // ou, se preferirmos,
            String sql;
            sql = "SELECT COUNT(*) AS QUANTOS " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setInt (1, codigo);
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
            resultado.first();
            retorno = resultado.getInt("QUANTOS") != 0;
            */
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar labirinto");
        }

        return retorno;
    }

    /**
     * Este metodo e aplicavel quando o usuario ira salvar um objeto labirinto
     * que ele forneceu os dados para o servidor e entao o servidor ira salvalos num
     * banco de dados os dados fornecidos
     * @param labirinto objeto que o o usario
     * @throws Exception caso o labirinto venha nulo lancamos uma execao
     */
    
    public static void incluir (Lab labirinto) throws Exception
    {
        if (labirinto==null)
            throw new Exception ("labirinto nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO labirinto " +
                  "(email, nome, data_criacao, data_ultima_modificacao, labirinto, senha) " +
                  "VALUES " +
                  "(?,?,?,?,?,?)";

            BDOracle.COMANDO.prepareStatement (sql);
            
            System.out.println("Inserido com sucesso no banco de dados!");
            BDOracle.COMANDO.setString (1, labirinto.getEmail());
            BDOracle.COMANDO.setString (2, labirinto.getNome());
            BDOracle.COMANDO.setString (3, labirinto.getDataCriacao());
            BDOracle.COMANDO.setString (4, labirinto.getDataUltimaCriacao());
            BDOracle.COMANDO.setString (5, labirinto.getConteudo());
            BDOracle.COMANDO.setString (6, labirinto.getSenha());
            
            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	erro.printStackTrace();
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao inserir labirinto");
        }
    }
    
    /**
     * Este metodo e aplicavel quando o usuario ira exlcluir alguma informacao
     * do banco de dados ele forneceram pelo parametro os dados para termos como
     * referencia qual tabela vai ser alterada
     * @param email
     * @param nome
     * @throws Exception caso nao ha nenhum cadastro com esses atributos.
     */

    public static void excluir (String email, String nome) throws Exception
    {
        if (!cadastrado (email, nome))
            throw new Exception ("Labirinto nao cadastrado");

        try
        {
            String sql;
            
            //delete 
            //from labirinto
            //where email='testeemail@gmail.com' and nome='danilo'
            sql = "DELETE FROM labirinto " +
                  "WHERE email=? and nome=?";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, email);
            BDOracle.COMANDO.setString (2, nome);

            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();        
        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao excluir labirinto");
        }
    }
    
    /**
     * Este metodo ira alterar os atributos que estao salvos no banco de dados
     * ele manda um objeto labirinto pelo paraemtro para fazermos select para procurar
     * se ele realmente existe para fazer a alteracao
     * @param labirinto
     * @throws Exception se o labirinto for igual a nulo ou  nao cadastrado
     */

    public static void alterar (Lab labirinto) throws Exception
    {
        if (labirinto==null)
            throw new Exception ("Labirinto nao fornecido");

        if (!cadastrado (labirinto.getEmail(), labirinto.getNome()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            /*UPDATE Labirinto
				set data_ultima_modificacao='11/11/1111', labirinto='e'
				where email='testeemail@gmail.com' and nome='danilo';*/
            sql = "UPDATE Labirinto " +
                  "SET data_ultima_modificacao=?, labirinto=?" +
                  "WHERE email=? and nome=?";

            BDOracle.COMANDO.prepareStatement (sql);
           
            BDOracle.COMANDO.setString (1, labirinto.getDataUltimaCriacao());
            BDOracle.COMANDO.setString (2, labirinto.getConteudo());
            BDOracle.COMANDO.setString (3, labirinto.getEmail());
            BDOracle.COMANDO.setString (4, labirinto.getNome());

            BDOracle.COMANDO.executeUpdate ();
            BDOracle.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	BDOracle.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados de labirinto");
        }
    }
    
    /**
     * Este metodo e aplicavel quando o usuario ira fazer um select de um labirinto
     * pelos parametros fornecidos pelo usuario
     * @param email
     * @param nome
     * @return o select se encontrou o dado na tabela
     * @throws Exception quando nao ha nenhum cadastro encontrado com os parametros
     */

    public static Labirinto getLabirinto (String email, String nome) throws Exception
    {
        Labirinto labirinto = null;

        try
        {
            String sql;

            /*				select * from labirinto
							where nome='danilo' and email='testeemail@gmail.com';*/
            sql = "SELECT * " +
                  "FROM Labirinto " +
                  "WHERE email=? and nome=? ";

            BDOracle.COMANDO.prepareStatement (sql);

            BDOracle.COMANDO.setString (1, email);
            BDOracle.COMANDO.setString (2, nome);

            MeuResultSet resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            
           
            labirinto = new Labirinto(resultado.getString("NOME"),resultado.getString("DATA_CRIACAO"),
            						  resultado.getString("DATA_ULTIMA_MODIFICACAO"),
            						  resultado.getString("LABIRINTO"),
            						  resultado.getString("EMAIL"),resultado.getString("SENHA"));
        }
        catch (SQLException erro)
        {
        	erro.printStackTrace();
            throw new Exception ("Erro ao procurar labirinto");
        }

        return labirinto;
    }
    
    /**
     * Este metodo e aplicavel quando o usario quiser visualizar todos
     * os labirintos que estao salvos no banco de dados, entao o servidor chama este metodo
     * e tem acesso a todos labirintos que estao salvos no banco de dados.
     * @return todos labirintos que foram salvos no banco de dados
     * @throws Exception caso nao tenha labiri
     */

    public static MeuResultSet getLabirintos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Labirinto";

            BDOracle.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDOracle.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar labirintos");
        }

        return resultado;
    }
}
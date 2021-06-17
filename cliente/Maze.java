package cliente;

import java.io.*;
import java.util.*;

import comunicado.Comunicado;
import comunicado.Lab;


/**
 * Esta classe representa uma lista array
 * de informacoes que sao enviadas do cliente para
 * adicionar em uma lista de dados de todos cliente esta
 * classe tem herança de comunicado para ter a conexao com servidor de envios
 * de objetos desta mesma classe
 * @author Matheus Henry, Danilo Montovaneli, Matheus camargo
 * @since 2021.
 * */


public class Maze extends Comunicado
{
  
  private ArrayList<Lab> labirintos;
  
  /**
   * Este metodo construi uma instancia da clase de Lab
   * esta instancia armazena todos os dados que sao salvos em Lab objeto
   * em um array com todas informacoes referentes a aquela instancia 
   * armazena todas informacoes dos cliente em um array desta instancia.
   * */
  
  public Maze () { 
  labirintos = new ArrayList<Lab>();

  }
  
  /**
   * Este metodo construi uma instancia da clase de Lab
   * esta instancia armazena todos os dados que sao salvos em Lab objeto
   * em um array com todas informacoes referentes a aquela instancia 
   * armazena todas informacoes dos cliente em um array desta instancia.
   * */
 
  public void addLabirinto (Lab novo){
	  labirintos.add(novo);
  }
  
  /**
   * Este metodo construi uma instancia da clase de Lab
   * esta instancia armazena todos os dados que sao salvos em Lab objeto
   * em um array com todas informacoes referentes a aquela instancia 
   * armazena todas informacoes dos cliente em um array desta instancia.
   * */
  
  public double getQtd (){
	  return labirintos.size();
  }
  
  /**
   * Este metodo construi uma instancia da clase de Lab
   * esta instancia armazena todos os dados que sao salvos em Lab objeto
   * em um array com todas informacoes referentes a aquela instancia 
   * armazena todas informacoes dos cliente em um array desta instancia.
   * */
  
  public Lab getLab (int i ){
	  return labirintos.get(i);
  }
  
  

}
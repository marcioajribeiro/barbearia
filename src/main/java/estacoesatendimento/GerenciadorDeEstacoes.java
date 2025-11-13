/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacoesatendimento;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorDeEstacoes {
    private static final EstacaoAtendimento[] estacoes = new EstacaoAtendimento[3];
    
    static {
        estacoes[0] = new EstacaoAtendimento(1, "Cadeira 1",TipoDeEstacao.LAVAGEM_E_SECADOR);
        estacoes[1] = new EstacaoAtendimento(2, "Cadeira 2",TipoDeEstacao.ATIVIDADES_CORRIQUEIRAS);
        estacoes[2] = new EstacaoAtendimento(3, "Cadeira 3",TipoDeEstacao.ATIVIDADES_CORRIQUEIRAS);
        
    }
    
    public static EstacaoAtendimento estacaoDisponivel(TipoDeEstacao tipo) {
        for (EstacaoAtendimento e : estacoes) {
            if (!e.isOcupado()&& e.getTipoDaEstacao()== tipo) {
                return e;
            }
        }
        return null;
    }
    
    public static void listarEstacoes() {
      System.out.println("=== Estações de Atendimento ===");
       for (EstacaoAtendimento e : estacoes) {
           System.out.println(e);
       }
    }
      
    public static void ocuparEstacao(int id) {
      for (EstacaoAtendimento e : estacoes) {
          if (e.getId() == id) {
              e.ocupar();
              System.out.println("Estação " + id + " ocupada.");
              return;
           }
        }
        System.out.println("Estação não encontrada.");
    }
       
      public static void liberarEstacao(int id) {
        for (EstacaoAtendimento e : estacoes) {
           if (e.getId() == id) {
              e.liberar();
              System.out.println("Estação " + id + " liberada.");
              return;
            }
        }
        System.out.println("Estação não encontrada.");
    }
      
      public static EstacaoAtendimento buscarEstação(int id){
          for(EstacaoAtendimento e : estacoes ){
              if(e.getId() == id){
                  return e;
              }
          }
          System.out.println("Estação com ID " + id + " não encontrado.");
          return null;
      }

    public static EstacaoAtendimento[] getEstacoes() {
        return estacoes;
    }       
       
    
}

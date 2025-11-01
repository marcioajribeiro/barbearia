/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleponto;

import controller.GerenciadorGenerico;
import entidades.Funcionario;
import java.util.List;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorPonto extends GerenciadorGenerico {
    private final List<RegistroPonto> pontos;
    private final String caminho = "Json/JsonPonto.json";

    public GerenciadorPonto() {
        this.pontos = super.carregarListas(caminho, RegistroPonto.class);
    }
    
    public RegistroPonto baterEntrada(Funcionario f){
        boolean pontoAberto = false;
        for (RegistroPonto ponto : pontos ){
            if(ponto.getFuncionario().equals(f) && ponto.getSaida() == null){
                pontoAberto = true;
                break;
            }
        }
        
        if (pontoAberto){
            System.err.println("ERRO: " + f.getNome() + " já está com o ponto em aberto");
            return null;
        }else {
            RegistroPonto novoPonto = new RegistroPonto(f);
            this.pontos.add(novoPonto);
            super.salvarLista(caminho, pontos);
            System.out.println("Ponto Registrado.");
            return novoPonto;
        }
    }
    
    public RegistroPonto baterSaida(Funcionario f){
        RegistroPonto registroAberto = null;
        for(RegistroPonto ponto : pontos){
            if(ponto.getFuncionario().equals(f) &&  ponto.getSaida() == null){
                registroAberto = ponto;
                break;
            } 
            }
     
                if (registroAberto != null){
                    registroAberto.pontoDeSaida();
                    super.salvarLista(caminho, pontos);
                    return registroAberto;
                    
                }else {
                    return null;
                }
    }
    
    public void listarPontos(){
        if(pontos.isEmpty()){
            System.out.println("Não há pontos registrados");
        }else {
            System.out.println("-----Registro de Pontos-----");
            pontos.forEach(System.out::println);
        }
        
    }
    
    
}

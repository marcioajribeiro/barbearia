/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamento;

import controller.GerenciadorGenerico;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorAgendamento extends GerenciadorGenerico {
    
    private List<Agendamento> agendaBarbearia;
    private final String caminho ="Json/JsonAgendamento.json";

    public GerenciadorAgendamento() {
        this.agendaBarbearia = super.carregarListas(caminho, Agendamento.class);
    }
    
    public void addAgendamento(Agendamento novoAgendamento){
        novoAgendamento.setId(geradorIdAgendamento());
        agendaBarbearia.add(novoAgendamento);
        super.salvarLista(caminho, agendaBarbearia);
    }
    
    public void alterarStatusCancelado(Agendamento agendamento){
        agendamento.setValor(agendamento.getValor() *0.35);
        agendamento.setStatusPagamento(StatusPagamento.PAGAMENTO_CANCELADO);
        super.salvarLista(caminho, agendaBarbearia);
    }
    
    public void alterarStatusConcluido(Agendamento agendamento){
        agendamento.setStatusPagamento(StatusPagamento.PAGAMENTO_CONCLUIDO);
        super.salvarLista(caminho, agendaBarbearia);
    }
    
    public Agendamento buscarAgendamentoId(int id){
        for (Agendamento agendamento : agendaBarbearia){
            if(agendamento.getId()== id){
                return agendamento;
            }
        }
        System.out.println("Id: " + id + " não encontrado");
        return null;
    } 
    
    public void listarAgendamentos(){
        if(agendaBarbearia.isEmpty()){
            System.out.println("Não há produto cadastrado");
        }else{
            System.out.println("-----Lista de Produtos-----");
            for(Agendamento a : agendaBarbearia){
                System.out.println(a);
            }
        }   
    }
    
    public int geradorIdAgendamento(){
        if(agendaBarbearia.isEmpty()){
            return 1;
        }
        
        Set<Integer> idsExistentes = new HashSet<>();
        for(Agendamento agendamento: agendaBarbearia){
            idsExistentes.add(agendamento.getId());
        }
        
        int novoId=1;
        
        while(idsExistentes.contains(novoId)){
            novoId++;
        }
        
        return novoId;
    }
    
    
    
    
}

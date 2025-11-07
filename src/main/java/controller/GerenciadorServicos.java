/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidades.Servico;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorServicos extends GerenciadorGenerico{
    
    private List<Servico> servicos;
    private final String caminho = "Json/JsonServico.json";
    
    public GerenciadorServicos(){
        this.servicos = super.carregarListas(caminho, Servico.class);
    }
    
    public void addServico(Servico novoServico){
        novoServico.setIdServico(geradorId());
        servicos.add(novoServico);
        System.out.println("Servico salvo");
        super.salvarLista(caminho, servicos);
    }
    
    public void removerServico(Servico servico){
        servicos.remove(servico);
        System.out.println("Novo serviço cadastrado");
        super.salvarLista(caminho, servicos);
    }
    
    public Servico buscarServicoId(int id){
        for(Servico s : servicos){
            if(s.getIdServico() == id){
                return s;
            }
        }
        return null;
    }
    
    public void listarServicos(){
        if(servicos.isEmpty()){
            System.out.println("Não há produto cadastrado");
        }else{
            System.out.println("-----Lista de Produtos-----");
            for(Servico p : servicos){
                System.out.println(p);
            }
        }   
    }
    
    
    public void editarValorServico(Servico s, double novoValor){
        s.setValor(novoValor);
        super.salvarLista(caminho, servicos);
    }
    
    public void editarDuracaoServico(Servico s, int novaDuracao){
        s.setDuracaoMin(novaDuracao);
        super.salvarLista(caminho, servicos);
    }
    
    
    public int geradorId(){
        if(servicos.isEmpty()){
            return 1;
        }
        
        Set<Integer> idsExistentes = new HashSet<>();
        for(Servico s : servicos){
            idsExistentes.add(s.getIdServico());
        }
        
        int novoId = 1;
        
        while(idsExistentes.contains(novoId)){
            novoId++;
        }
        
        return novoId;
    }
    
}

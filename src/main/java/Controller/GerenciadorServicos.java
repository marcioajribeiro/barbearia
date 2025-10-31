/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entidades.Servico;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorServicos extends GerenciadorGenerico{
    
    private List<Servico> servicos;
    private final String caminho = "Json/JsonServico";
    
    public GerenciadorServicos(){
        this.servicos = super.carregarListas(caminho, Servico.class);
    }
    
    public void addServico(Servico novoServico){
        novoServico.setIdServico(geradorId());
        servicos.add(novoServico);
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
    
    public List<Servico> listarServicos(){
        return servicos;
    }
    
    public void editarValorServico(Servico s, double novoValor){
        s.setValor(novoValor);
        super.salvarLista(caminho, servicos);
    }
    
    public void editarDuracaoServico(Servico s, int novaDuracao){
        s.setDuração(novaDuracao);
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

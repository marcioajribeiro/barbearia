/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamento;

import comparator.ComparatorAgendamento;
import controller.GerenciadorGenerico;
import controller.GerenciadorServicos;
import entidades.Funcionario;
import entidades.Servico;
import financeiro.GerenciadorVenda;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorAgendamento extends GerenciadorGenerico {
    
    private List<Agendamento> agendaBarbearia;
    private final GerenciadorVenda gv;
    private final GerenciadorServicos gs;
    private final String caminho ="Json/JsonAgendamento.json";

    public GerenciadorAgendamento(GerenciadorVenda gv, GerenciadorServicos gs) {
        this.agendaBarbearia = super.carregarListas(caminho, Agendamento.class);
        this.gv = gv;
        this.gs =gs;
    }
    
    public void addAgendamento(Agendamento novoAgendamento) throws Exception{
        if (!verificarDisponibilidade(novoAgendamento.getDataHora(), novoAgendamento)) {
            throw new Exception("Horário indisponível para o barbeiro selecionado!");
        }
        novoAgendamento.setId(geradorIdAgendamento());
        agendaBarbearia.add(novoAgendamento);
        super.salvarLista(caminho, agendaBarbearia);
    }
        
   
    
    public void alterarStatusCancelado(Agendamento agendamento){
        agendamento.setValor(agendamento.getValor() *0.35);
        agendamento.setStatusPagamento(StatusPagamento.PAGAMENTO_CANCELADO);
        gv.registrarVendaCancelamento(agendamento);
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
    
    public void listarAgendamentosDoDia(){
        LocalDate hoje = LocalDate.now();
        List<Agendamento> agendaHoje = agendaBarbearia.stream().filter(a -> a.getDataHora().toLocalDate().equals(hoje)).collect(Collectors.toList());
        
        if(agendaHoje.isEmpty()){
            System.out.println("Não há agendamentos para hoje");
        } else{
            System.out.println("----- Agendamentos de hoje (" + hoje + ") -----");
            for(Agendamento a : agendaHoje){
                System.out.println(a);
            }
        }
    }
    
    
    
    public boolean verificarDisponibilidade(LocalDateTime dataHoraDesejada, Agendamento agendamento){
        int duracaoTotalDesejada = agendamento.getServicos().stream().mapToInt(Servico::getDuracaoMin).sum();
        LocalDateTime FinalDataHora = dataHoraDesejada.plusMinutes(duracaoTotalDesejada);
        Set<Integer> idsEstacoesDesejadas = agendamento.getServicos().stream().map(s -> s.getEstacaoUsada().getId()).collect(Collectors.toSet());
        
        
        for(Agendamento agendamentosExistentes : agendaBarbearia){
            if(agendamentosExistentes.getFuncionario().equals(agendamento.getFuncionario())){
                LocalDateTime inicioExistente = agendamentosExistentes.getDataHora();
                int duracaoExistente = agendamentosExistentes.getServicos().stream().mapToInt(Servico::getDuracaoMin).sum();
                LocalDateTime fimExistente = inicioExistente.plusMinutes(duracaoExistente);
                boolean sobrepoe = dataHoraDesejada.isBefore(fimExistente) && FinalDataHora.isAfter(inicioExistente);
                if (sobrepoe) {
            
                    if (agendamentosExistentes.getFuncionario().equals(agendamento.getFuncionario())) {
                        System.err.println("ERRO: O barbeiro " + agendamento.getFuncionario().getNome() + " já está ocupado nesse horário: " + inicioExistente + " até " + fimExistente);
                        return false;
                    }


                    Set<Integer> idsEstacoesExistentes = agendamentosExistentes.getServicos().stream()
                        .map(s -> s.getEstacaoUsada().getId())
                        .collect(Collectors.toSet());

                    for (Integer idDesejado : idsEstacoesDesejadas) {
                        if (idsEstacoesExistentes.contains(idDesejado)) {
                            System.err.println("ERRO: A Estação de Atendimento ID " + idDesejado + " está ocupada por outro agendamento nesse horário.");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
        
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
    
    public void organizarPorMaisRecentes(){
        Collections.sort(agendaBarbearia, new ComparatorAgendamento());
        super.salvarLista(caminho, agendaBarbearia);
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
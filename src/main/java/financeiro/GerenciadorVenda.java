/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import agendamento.Agendamento;
import agendamento.StatusPagamento;
import controller.GerenciadorGenerico;
import entidades.Produto;
import entidades.Servico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafin
 */
public class GerenciadorVenda extends GerenciadorGenerico{

    private List<Venda> vendas;
    private final String caminho = "Json/JsonVendas.json";

    public GerenciadorVenda() {
        this.vendas = super.carregarListas(caminho, Venda.class);
    }
    
    public void registrarVendaCancelamento(Agendamento a){
        if(a.getStatusPagamento() != StatusPagamento.PAGAMENTO_CANCELADO){
        System.out.println("O agendamento não está cancelado. Não é possível registrar venda de cancelamento.");
    }
        List<Produto> produtos = new ArrayList<>(); // normalmente vazio
        List<Servico> servicos = a.getServicos();
        Venda venda = new Venda(a.getCliente(), a.getFuncionario(),produtos,servicos,"N/A", LocalDateTime.now());
        
        venda.setValorTotal(a.getValor());
        venda.setCancelamento(true);
        
        registrarVenda(venda);
    }
    
    

    public void registrarVenda(Venda venda) {
        venda.setIdVenda(geradorIdVenda());
        vendas.add(venda);
        System.out.println("Venda registrada com sucesso!");
        super.salvarLista(caminho, vendas);
    }

    public Venda buscarVendaPorId(int id) {
        for (Venda v : vendas) {
            if (v.getIdVenda() == id) {
                return v;
            }
        }
        return null;
    }

    public void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda v : vendas) {
                System.out.println(v);
            }
        }
    }

    public void removerVenda(Venda venda) {
        vendas.remove(venda);
        System.out.println("Venda removida");
        super.salvarLista(caminho, vendas);
       
    }
    
    public double calcularVendasAnoMes(int ano, int mes){
        double total = 0.0;
        for(Venda v : vendas){
            if(v.getDataHora().getYear() == ano && v.getDataHora().getMonthValue() == mes){
                total+= v.getValorTotal();
            }
        }
        
        return total;
    }


    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public int geradorIdVenda(){
        if(vendas.isEmpty()){
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();
        for(Venda venda: vendas){
            idsExistentes.add(venda.getIdVenda());
        }

        int novoId = 1;

        while(idsExistentes.contains(novoId)){
            novoId++;
        }

        return novoId;

    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import controller.GerenciadorGenerico;
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
    
    

    public void registrarVenda(Venda venda) {
        venda.setIdVenda(geradorIdVenda());
        vendas.add(venda);
        super.salvarLista(caminho, vendas);
        System.out.println("Venda registrada com sucesso!");
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

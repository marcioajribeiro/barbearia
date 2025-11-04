/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import agendamento.Agendamento;
import agendamento.StatusPagamento;
import controller.GerenciadorGenerico;
import controller.GerenciadorProdutos;
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
    private final GerenciadorProdutos gp;

    public GerenciadorVenda(GerenciadorProdutos gp) {
        this.vendas = super.carregarListas(caminho, Venda.class);
        this.gp = gp;
    }

    
    
    public void registrarVendaCancelamento(Agendamento a){
        if(a.getStatusPagamento() != StatusPagamento.PAGAMENTO_CANCELADO){
        System.out.println("O agendamento não está cancelado. Não é possível registrar venda de cancelamento.");
    }
        List<Produto> produtos = new ArrayList<>();
        List<Servico> servicos = a.getServicos();
        Venda venda = new Venda(a.getCliente(), a.getFuncionario(),produtos,servicos,"N/A", LocalDateTime.now());
        
        venda.setValorTotal(a.getValor());
        venda.setCancelamento(true);
        
        registrarVenda(venda);
    }
    
    

    public void registrarVenda(Venda venda) {
    venda.setIdVenda(geradorIdVenda());

 
        for (Produto pVenda : gp.getProduto()) {
            Produto produtoEstoque = gp.buscarProdutoPorId(pVenda.getIdProduto());

            if (produtoEstoque != null) {
                boolean sucesso = gp.removerEstoque(produtoEstoque, 1);
                if (!sucesso) {
                    System.out.println("Erro ao tentar remover do estoque o produto: " + produtoEstoque.getNome());
                }
            } else {
                System.out.println("Produto não encontrado no estoque: " + pVenda.getNome());
            }
        }
    

    vendas.add(venda);
    super.salvarLista(caminho, vendas);
    System.out.println("✅ Venda registrada com sucesso!");
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

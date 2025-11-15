/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordemDeServico;

import controller.GerenciadorClientes;
import controller.GerenciadorFuncionarios;
import controller.GerenciadorServicos;
import controller.GerenciadorGenerico;
import controller.GerenciadorProdutos;
import entidades.Produto;
import entidades.Servico;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafin
 */
public class GerenciadorOs extends GerenciadorGenerico {
    
    private List<OrdemDeServico> gerenciadorOs;
    private final String caminho = "Json/JsonOs.json";
    private final GerenciadorServicos gs ;
    private final GerenciadorClientes gc ;
    private final GerenciadorFuncionarios gf ;
    private final GerenciadorProdutos gp ;

    public GerenciadorOs(GerenciadorServicos gs, GerenciadorClientes gc, GerenciadorFuncionarios gf, GerenciadorProdutos gp) {
        this.gerenciadorOs = super.carregarListas(caminho, OrdemDeServico.class);
        this.gs = gs;
        this.gc = gc;
        this.gf = gf;
        this.gp = gp;
    }
    
    
    
    public void addOrdemServico(OrdemDeServico os) {
    os.setIdOS(geradorIdOS());
    gerenciadorOs.add(os);
    
}
    
    
        
    public OrdemDeServico buscarPorId(int id) {
        for (OrdemDeServico os : gerenciadorOs) {
            if (os.getIdOS() == id) {
                return os;
            }
        }
        return null;
    }
    
    
    public void alterarStatusOS(int id, TipoStatusOs novoStatus) {
        OrdemDeServico os = buscarPorId(id);
        if (os != null) {
            os.setStatusOs(novoStatus);
            System.out.println("Status da Ordem de Serviço " + id + " alterado para: " + novoStatus);
        } else {
            System.out.println("Ordem de Serviço com ID " + id + " não encontrada.");
        }
    }
    
    
     public void listarOS() {
        if (gerenciadorOs.isEmpty()) {
            System.out.println("Nenhuma Ordem de Serviço cadastrada.");
        } else {
            for (OrdemDeServico os : gerenciadorOs) {
                System.out.println(os);
            }
        }
    }
    

    
        public void atualizarLista() {
        super.salvarLista(caminho, gerenciadorOs);
        }
    
        
        public boolean removerOrdemServico(int id) {
        OrdemDeServico os = buscarPorId(id);
            if (os != null) {
                gerenciadorOs.remove(os);
            return true;
        }
            return false;
}
        
        public double calcularValorTotal(OrdemDeServico os) {
            double totalProdutos = os.getProduto().stream().mapToDouble(Produto::getPreco).sum();
            double totalServicos = os.getServicos().stream().mapToDouble(Servico::getValor).sum();
                os.setValorTotal(totalProdutos + totalServicos);
                    return os.getValorTotal();
}
        
        public boolean atualizarOrdemServico(OrdemDeServico osAtualizada) {
            OrdemDeServico osExistente = buscarPorId(osAtualizada.getIdOS());
                if (osExistente != null) {
                    osExistente.setCliente(osAtualizada.getCliente());
                return true;
            }
                return false;
}

    
    
        public int geradorIdOS(){
        if(gerenciadorOs.isEmpty()){
            return 1;
        }
        
        Set<Integer> idExistentes = new HashSet<>();
        
        for(OrdemDeServico d : gerenciadorOs){
            idExistentes.add(d.getIdOS());
        }
        
        int novoId = 1;
        
        while(idExistentes.contains(novoId)){
            novoId++;
        }
        
        return novoId;
        
        
    }
    
}

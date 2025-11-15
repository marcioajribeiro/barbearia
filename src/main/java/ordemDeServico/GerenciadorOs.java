/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordemdeservico;

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
import ordemDeServico.TipoStatusOs;

/**
 *
 * @author rafin
 */
public class GerenciadorOs extends GerenciadorGenerico {
    
    private List<OrdemDeServico> listaOs;
    private final String caminho = "Json/JsonOs.json";
    private final GerenciadorServicos gs ;
    private final GerenciadorClientes gc ;
    private final GerenciadorFuncionarios gf ;
    private final GerenciadorProdutos gp ;

    public GerenciadorOs(GerenciadorServicos gs, GerenciadorClientes gc, GerenciadorFuncionarios gf, GerenciadorProdutos gp) {
        this.listaOs = super.carregarListas(caminho, OrdemDeServico.class);
        this.gs = gs;
        this.gc = gc;
        this.gf = gf;
        this.gp = gp;
    }
    
    
    
    public void addOrdemServico(OrdemDeServico os) {
        os.setIdOS(geradorIdOS());
        listaOs.add(os);
    
}
    
    public void addProduto(Produto produto, OrdemDeServico os) {
        os.getProduto().add(produto);
    }
    
    public void addServico(Servico servico, OrdemDeServico os) {
        os.getServicos().add(servico);
    }
    
    
        
    public OrdemDeServico buscarPorId(int id) {
        for (OrdemDeServico os : listaOs) {
            if (os.getIdOS() == id) {
                return os;
            }
        }
        return null;
    }
    
    
    
    public void listarOS() {
        if (listaOs.isEmpty()) {
            System.out.println("Nenhuma Ordem de Serviço cadastrada.");
        } else {
            for (OrdemDeServico os : listaOs) {
                System.out.println(os);
            }
        }
    }
    

    
    public void atualizarLista() {
        super.salvarLista(caminho, listaOs);
        }
    
        
    public boolean removerOrdemServico(int id) {
        OrdemDeServico os = buscarPorId(id);
            if (os != null) {
                listaOs.remove(os);
            return true;
        }
            return false;
}
        
//        public double calcularValorTotal(OrdemDeServico os) {
//            double totalProdutos = os.getProduto().stream().mapToDouble(Produto::getPreco).sum();
//            double totalServicos = os.getServicos().stream().mapToDouble(Servico::getValor).sum();
//                os.setValorTotal(totalProdutos + totalServicos);
//                    return os.getValorTotal();
//}
        
    public void alterarStatusEmAndamento(OrdemDeServico os) {
        os.setStatusOs(TipoStatusOs.OS_EM_ANDAMENTO);
    }
            
    public void alterarStatusConcluido(OrdemDeServico os) {
        os.setStatusOs(TipoStatusOs.OS_CONCLUIDO);
    }
        
      
    public void alterarStatusCancelado(OrdemDeServico os) {
        os.setStatusOs(TipoStatusOs.OS_CANCELADO);
    }
        
 
    public int geradorIdOS(){
        if(listaOs.isEmpty()){
            return 1;
        }
        
        Set<Integer> idExistentes = new HashSet<>();
        
        for(OrdemDeServico d : listaOs){
            idExistentes.add(d.getIdOS());
        }
        
        int novoId = 1;
        
        while(idExistentes.contains(novoId)){
            novoId++;
        }
        
        return novoId;
        
        
    }
    
}
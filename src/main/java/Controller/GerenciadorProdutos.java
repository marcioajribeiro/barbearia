/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entidades.Produto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafin
 */
public class GerenciadorProdutos extends GerenciadorGenerico {
    
    public List<Produto> produtos;
    public final String caminho = "Json/JsonEstoque.json";

    public GerenciadorProdutos() {
        this.produtos = super.carregarListas(caminho, Produto.class);
    }

    public List<Produto> getProduto() {
        return produtos;
    }
    
    public void addProduto(Produto p){
        p.setIdProduto(geradorId());
        produtos.add(p);
        System.out.println("Produto salvo");
        super.salvarLista(caminho, produtos);
    }
    
    public void listarProdutos(){
        if(produtos.isEmpty()){
            System.out.println("Não há produto cadastrado");
        }else{
            System.out.println("-----Lista de Produtos-----");
            for(Produto p : produtos){
                System.out.println(p);
            }
        }   
    }
    
    public Produto buscarProdutoPorId(int id) {
    for (Produto p : produtos) {
        if (p.getIdProduto()== id) {
            return p;
        }
    }
    
    System.out.println("ID: " + id + " não encontrado");
    return null;
}
    
    public void removerProdutoPorId(Produto p) {
        produtos.remove(p);
        System.out.println("Produto removido com sucesso!");
        super.salvarLista(caminho, produtos);
    
    
}
    public void editarPrecoProduto(Produto p , double precoNovo){
        p.setPreco(precoNovo);
        super.salvarLista(caminho, produtos);
    }
    
    public void editarFornecedorProduto(Produto p, String novofornecedor){
        p.setFornecedor(novofornecedor);
        super.salvarLista(caminho, produtos);
    }
    
    public boolean adicionarEstoque(Produto p ,int quantidade){
        if(quantidade >0){
            p.setQuantidadeEstoque(quantidade + p.getQuantidadeEstoque());
            return true;
        }else{
            return false;
        }
    }
    
    public boolean removerEstoque(Produto p, int quantidade){
        if(quantidade > 0 && p.getQuantidadeEstoque() >= quantidade){
            if(quantidade >0){
                p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
                super.salvarLista(caminho, produtos);
                return true;
            }
            else{
                return false;
            }
        } else{
            return false;
        }
    }
    
    
    public int geradorId(){
        if(produtos.isEmpty()){
            return 1;
        }
        
        Set<Integer> idsExistentes = new HashSet<>();
        for(Produto p: produtos){
            idsExistentes.add(p.getIdProduto());
        }
        
        int novoId = 1;
        
        while(idsExistentes.contains(novoId)){
            novoId++;
        }
        
        return novoId;
        
    }

    

}


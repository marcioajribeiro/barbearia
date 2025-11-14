/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidades.Produto;
import financeiro.Venda;
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
    
    
}
    public void editarPrecoProduto(Produto p , double precoNovo){
        p.setPreco(precoNovo);
    }
    
    public void editarFornecedorProduto(Produto p, String novofornecedor){
        p.setFornecedor(novofornecedor);
    }
    
    public boolean adicionarEstoque(Produto p ,int quantidade){
        if(quantidade >0){
            p.setQuantidadeEstoque(quantidade + p.getQuantidadeEstoque());
            return true;
        }else{
            return false;
        }
    }
    
    public void removerEstoque(Produto p, int quantidade){
        if (quantidade > 0 && p.getQuantidadeEstoque() >= quantidade){
            p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
        } else {
            System.out.println("Quantidade insuficiente em estoque para o produto: " + p.getNome());
        }
}
    
    public void atualizarEstoquePorVenda(Venda venda) {
        for (Produto pVenda : venda.getProdutos()) { 
            Produto produtoEstoque = buscarProdutoPorId(pVenda.getIdProduto());
        if (produtoEstoque != null) {
            removerEstoque(produtoEstoque, 1); 
        }
    }
    }
    
    public void removerEstoqueManual(Produto p, int quantidade){
        if (quantidade > 0 && p.getQuantidadeEstoque() >= quantidade){
            p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
            super.salvarLista(caminho, produtos);
        }   else {
        System.out.println("Quantidade insuficiente para o produto: " + p.getNome());
        }
    }

    public void salvarEstoque(){
        super.salvarLista(caminho, produtos);
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


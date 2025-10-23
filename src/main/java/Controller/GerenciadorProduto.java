/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entidades.Produto;
import java.util.List;

/**
 *
 * @author rafin
 */
public class GerenciadorProduto extends GerenciadorGenerico {
    
    public List<Produto> produto;
    public final String caminho = "Json/JsonEstoque.json";

    public GerenciadorProduto() {
        this.produto = super.carregarListas(caminho, Produto.class);
    }

    public List<Produto> getProduto() {
        return produto;
    }
    
    public void addProduto(Produto p){
        produto.add(p);
        System.out.println("Produto salvo");
        super.salvarLista(caminho, produto);
    }
    
    public void listarProdutos(){
        if(produto.isEmpty()){
            System.out.println("Não há produto cadastrado");
        }else{
            System.out.println("-----Lista de Produtos-----");
            for(Produto p : produto){
                System.out.println(p);
            }
        }   
    }
    
    public Produto buscarProdutoPorId(int id) {
    for (Produto p : produto) {
        if (p.getId() == id) {
            return p;
        }
    }
    
    System.out.println("ID: " + id + " não encontrado");
    return null;
}
    
    public void removerProdutoPorId(int id) {
    Produto p = buscarProdutoPorId(id);
    if (p != null) {
        produto.remove(p);
        System.out.println("Produto removido com sucesso!");
        super.salvarLista(caminho, produto);
    } else {
        System.out.println("Produto com ID " + id + " não encontrado.");
    }
}

    

}


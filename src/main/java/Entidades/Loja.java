/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import java.util.ArrayList;
/**
 *
 * @author rafin
 */
public class Loja {
    
    private ArrayList<Produto> produtos;
    
    public Loja(){
        produtos = new ArrayList<>();
    }
    
    public void AddProduto(Produto p){
        produtos.add(p);
    }

    public Loja(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    public void listarProdutos() { 
        System.out.println("\n--- Produtos disponíveis ---"); 
        for (int i = 0; i < produtos.size(); i++) { 
            System.out.println((i + 1) + ". " + produtos.get(i)); 
        } 
    }
    
    public void venderProduto(int indice, int quantidade) { 
        if (indice >= 0 && indice < produtos.size()) { 
            produtos.get(indice).vender(quantidade); 
        } else { 
            System.out.println("Produto não encontrado!"); 
        } 
    }
}


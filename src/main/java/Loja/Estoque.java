/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafin
 */
public class Estoque {
    
    private Produto produto;
    private Integer quantidade;

    public Estoque(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
    public void adicionarProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            return;
        }

        
        System.out.println(quantidade + " unidades de " + produto.getNome() + " adicionadas ao estoque.");
       
    }
    
     public int verificarEstoque(Produto produto) {
     int quantidade = produto.getQuantidadeInicial();
        System.out.println("Quantidade de " + produto.getNome() + " em estoque: " + quantidade + " unidades.");
            return quantidade;

    }
     
     
     public List<Produto> getProdutos() {
    List<Produto> listaProdutos = new ArrayList<>();
    
    // Verifica se o produto não é nulo antes de acessar seus métodos
    if (produto != null) {
        for (int i = 0; i < produto.getQuantidadeInicial(); i++) {
            listaProdutos.add(produto);
        }
    }

    return listaProdutos;
}
}
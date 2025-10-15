/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loja;

/**
 *
 * @author rafin
 */
public class Produto {
    
    private String nome;
    private double preco;
    private int estoque;
    private int quantidadeInicial;
    private int id;

    public Produto(String nome, double preco, int estoque, int quantidadeInicial, int id) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.quantidadeInicial = quantidadeInicial;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getQuantidadeInicial() {
        return quantidadeInicial;
    }

    public void setQuantidadeInicial(int quantidadeInicial) {
        this.quantidadeInicial = quantidadeInicial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + '}';
    }
   
    
}

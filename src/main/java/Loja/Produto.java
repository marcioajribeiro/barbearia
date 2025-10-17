/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loja;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author rafin
 */
public class Produto {
    private static final AtomicInteger geradorId = new AtomicInteger(0);
    
    private String nome;
    private double preco;
    private int quantidade;
    private int id;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.id = geradorId.incrementAndGet();
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
  @Override
    public String toString() {
        return 
                "ID: " + id + 
                ", Nome: " + nome +
                ", Preço: " + preco +
                ", Quantidade: " + quantidade;

  }
   
    
}

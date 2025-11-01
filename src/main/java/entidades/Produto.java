/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;



/**
 *
 * @author rafin
 */
public class Produto {
    
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private int idProduto;
    private String Fornecedor;

    public Produto(String nome, double preco, int quantidade, String Fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidade;
 
        this.Fornecedor = Fornecedor;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
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

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidade) {
        this.quantidadeEstoque = quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int id) {
        this.idProduto = id;
    }

   
  @Override
    public String toString() {
        return 
                "ID: " + idProduto + 
                ", Nome: " + nome +
                ", Preço: " + preco +
                ", Quantidade: " + quantidadeEstoque;

  }
   
    
}

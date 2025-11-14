/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;



/**
 *Classe para representar um produto disponivel na Barbearia, onde serão contidos, valores e metodos para o mesmo.
 *@author Marcio Antonio
 *@author Rafael Martins
 */
public class Produto {

    /** Nome do produto. */
    private String nome;

    /** Preço unitário do produto. */
    private double preco;

    /** Quantidade disponível em estoque. */
    private int quantidadeEstoque;

    /** Identificador único do produto no sistema. */
    private int idProduto;

    /** Nome do fornecedor responsável pelo produto. */
    private String Fornecedor;

    /**
     * Construtor que inicializa um produto com nome, preço, quantidade e fornecedor.
     *
     * @param nome        o nome do produto
     * @param preco       o preço unitário do produto
     * @param quantidade  a quantidade inicial em estoque
     * @param Fornecedor  o fornecedor do produto
     */
    public Produto(String nome, double preco, int quantidade, String Fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidade;
        this.Fornecedor = Fornecedor;
    }

    /**
     * Retorna o fornecedor do produto.
     *
     * @return o nome do fornecedor
     */
    public String getFornecedor() {
        return Fornecedor;
    }

    /**
     * Define o fornecedor do produto.
     *
     * @param Fornecedor o novo fornecedor
     */
    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return o nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome o novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço unitário do produto.
     *
     * @return o preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço do produto.
     *
     * @param preco o novo preço do produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a quantidade disponível em estoque.
     *
     * @return a quantidade atual em estoque
     */
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /**
     * Define a quantidade de produto em estoque.
     *
     * @param quantidade a nova quantidade em estoque
     */
    public void setQuantidadeEstoque(int quantidade) {
        this.quantidadeEstoque = quantidade;
    }

    /**
     * Retorna o identificador único do produto.
     *
     * @return o ID do produto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * Define o identificador único do produto.
     *
     * @param id o novo ID do produto
     */
    public void setIdProduto(int id) {
        this.idProduto = id;
    }

    /**
     * Retorna uma representação em String do produto,
     * contendo ID, nome, preço e quantidade em estoque.
     *
     * @return uma string representando o produto
     */
    @Override
    public String toString() {
        return
                "ID: " + idProduto +
                        ", Nome: " + nome +
                        ", Preço: " + preco +
                        ", Quantidade: " + quantidadeEstoque;
    }
   
    
}

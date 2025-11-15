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
 * Classe responsável por gerenciar os produtos do sistema.
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe {@link GerenciadorGenerico}
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorProdutos extends GerenciadorGenerico {

    /** Lista de produtos cadastrados no sistema. */
    public List<Produto> produtos;

    /** Caminho do arquivo JSON que armazena as informações do estoque. */
    public final String caminho = "Json/JsonEstoque.json";

    /**
     * Construtor que inicializa o gerenciador e carrega os produtos
     * armazenados no arquivo JSON.
     */
    public GerenciadorProdutos() {
        this.produtos = super.carregarListas(caminho, Produto.class);
    }

    /**
     * Retorna a lista completa de produtos cadastrados.
     *
     * @return lista de produtos
     */
    public List<Produto> getProduto() {
        return produtos;
    }

    /**
     * Adiciona um novo produto ao sistema, gerando automaticamente seu ID.
     *
     * @param p produto a ser adicionado
     */
    public void addProduto(Produto p) {
        p.setIdProduto(geradorId());
        produtos.add(p);
        System.out.println("Produto salvo");
    }

    /**
     * Lista todos os produtos cadastrados no sistema.
     * Exibe mensagem caso nenhum produto esteja registrado.
     */
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Não há produto cadastrado");
        } else {
            System.out.println("-----Lista de Produtos-----");
            for (Produto p : produtos) {
                System.out.println(p);
            }
        }
    }

    /**
     * Busca um produto pelo ID fornecido.
     *
     * @param id identificador do produto
     * @return o produto correspondente ou null caso não seja encontrado
     */
    public Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getIdProduto() == id) {
                return p;
            }
        }

        System.out.println("ID: " + id + " não encontrado");
        return null;
    }

    /**
     * Remove um produto da lista.
     *
     * @param p produto que será removido
     */
    public void removerProdutoPorId(Produto p) {
        produtos.remove(p);
        System.out.println("Produto removido com sucesso!");
    }

    /**
     * Atualiza o preço de um produto.
     *
     * @param p produto a ser alterado
     * @param precoNovo novo valor para o produto
     */
    public void editarPrecoProduto(Produto p, double precoNovo) {
        p.setPreco(precoNovo);
    }

    /**
     * Atualiza o fornecedor de um produto.
     *
     * @param p produto a ser alterado
     * @param novofornecedor nova identificação do fornecedor
     */
    public void editarFornecedorProduto(Produto p, String novofornecedor) {
        p.setFornecedor(novofornecedor);
    }

    /**
     * Adiciona quantidade ao estoque de um produto.
     *
     * @param p produto alvo
     * @param quantidade quantidade a ser adicionada
     * @return true caso a operação seja válida; false caso contrário
     */
    public boolean adicionarEstoque(Produto p, int quantidade) {
        if (quantidade > 0) {
            p.setQuantidadeEstoque(quantidade + p.getQuantidadeEstoque());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove quantidade do estoque de um produto.
     *
     * @param p produto alvo
     * @param quantidade quantidade a ser removida
     */
    public void removerEstoque(Produto p, int quantidade) {
        if (quantidade > 0 && p.getQuantidadeEstoque() >= quantidade) {
            p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
        } else {
            System.out.println("Quantidade insuficiente em estoque para o produto: " + p.getNome());
        }
    }

    /**
     * Atualiza o estoque com base nos produtos presentes em uma venda realizada.
     *
     * @param venda venda contendo produtos adquiridos
     */
    public void atualizarEstoquePorVenda(Venda venda) {
        for (Produto pVenda : venda.getProdutos()) {
            Produto produtoEstoque = buscarProdutoPorId(pVenda.getIdProduto());
            if (produtoEstoque != null) {
                removerEstoque(produtoEstoque, 1);
            }
        }
    }

    /**
     * Remove manualmente uma quantidade do estoque e salva imediatamente a atualização.
     *
     * @param p produto alvo
     * @param quantidade quantidade a ser removida
     */
    public void removerEstoqueManual(Produto p, int quantidade) {
        if (quantidade > 0 && p.getQuantidadeEstoque() >= quantidade) {
            p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
        } else {
            System.out.println("Quantidade insuficiente para o produto: " + p.getNome());
        }
    }

    /**
     * Salva o estado atual do estoque no arquivo JSON.
     */
    public void salvarEstoque() {
        super.salvarLista(caminho, produtos);
    }

    /**
     * Gera um novo ID único baseado nos IDs já existentes na lista.
     *
     * @return o próximo ID disponível
     */
    public int geradorId() {
        if (produtos.isEmpty()) {
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();
        for (Produto p : produtos) {
            idsExistentes.add(p.getIdProduto());
        }

        int novoId = 1;

        while (idsExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}


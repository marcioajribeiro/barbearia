/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import agendamento.StatusAgendamento;
import agendamento.Agendamento;
import controller.GerenciadorGenerico;
import controller.GerenciadorProdutos;
import entidades.Produto;
import entidades.Servico;
import ordemdeservico.OrdemDeServico;
import ordemdeservico.TipoStatusOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável por gerenciar todas as vendas do sistema.
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe {@link GerenciadorGenerico}
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorVenda extends GerenciadorGenerico {
    /** Lista de vendas cadastrados no sistema. */
    private List<Venda> vendas;
    /** Caminho do arquivo JSON usado para persistência dos serviços. */
    private final String caminho = "Json/JsonVendas.json";
    /**
     * Referência ao gerenciador de produtos, utilizada para operações
     * que envolvem manipulação de produtos vinculados às vendas.
     */
    private final GerenciadorProdutos gp;

    /**
     * Construtor que inicializa o gerenciador de vendas carregando os dados do arquivo JSON.
     * @param gp gerenciador de produto usado para operações relacionadas à venda de produtos
     */
    public GerenciadorVenda(GerenciadorProdutos gp) {
        this.vendas = super.carregarListas(caminho, Venda.class);
        this.gp = gp;
    }



    /**
     * Registra uma venda de cancelamento com base em um agendamento cancelado.
     * <p>
     * Caso o agendamento não esteja cancelado, a venda não é registrada.
     *
     * @param a agendamento cancelado que será convertido em venda
     */
    public void registrarVendaCancelamento(Agendamento a) {
        if (a.getStatusAgendamento() != StatusAgendamento.AGENDAMENTO_CANCELADO) {
            System.out.println("O agendamento não está cancelado. Não é possível registrar venda de cancelamento.");
        }

        List<Produto> produtos = new ArrayList<>();
        List<Servico> servicos = a.getServicos();

        Venda venda = new Venda(
                a.getCliente(),
                a.getFuncionario(),
                produtos,
                servicos,
                "N/A",
                LocalDateTime.now()
        );

        venda.setValorTotal(a.getValor());
        venda.setCancelamento(true);
        vendas.add(venda);
    }


    /**
     * Registra uma nova venda adicionando-a à lista e gerando seu ID automaticamente.
     *
     * @param venda venda a ser registrada
     */
    public void registrarVenda(Venda venda) {
        venda.setIdVenda(geradorIdVenda());
        vendas.add(venda);
    }

    /**
     * Busca uma venda pelo seu ID.
     *
     * @param id identificador da venda
     * @return a venda correspondente ou {@code null} caso não encontrada
     */
    public Venda buscarVendaPorId(int id) {
        for (Venda v : vendas) {
            if (v.getIdVenda() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * Lista todas as vendas registradas no sistema exibindo-as no console.
     */
    public void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda v : vendas) {
                System.out.println(v);
            }
        }
    }

    /**
     * Remove uma venda da lista.
     *
     * @param venda venda a ser removida
     */
    public void removerVenda(Venda venda) {
        vendas.remove(venda);
    }



    /**
     * Registra uma nova venda a partir de uma Ordem de Serviço concluída.
     *
     * @param os Ordem de Serviço concluída que será convertida em venda.
     * @param formaPagamento Forma de pagamento utilizada.
     */
    public void registrarVendaOS(OrdemDeServico os, String formaPagamento) {
        if (os.getStatusOs() != TipoStatusOs.ESTADO_CONCLUIDO) {
            System.err.println("ERRO: A OS precisa estar concluída para gerar uma venda.");
            return;
        }

        Venda venda = new Venda(
                os.getCliente(),
                os.getFuncionario(),
                os.getProduto(),
                os.getServicos(),
                formaPagamento,
                os.getDataHora()
        );



        registrarVenda(venda);

    }

    /**
     * Calcula o valor total das vendas realizadas em um determinado mês e ano.
     *
     * @param ano o ano desejado
     * @param mes o mês desejado (1-12)
     * @return soma do valor total das vendas no período
     */
    public double calcularVendasAnoMes(int ano, int mes) {
        double total = 0.0;
        for (Venda v : vendas) {
            if (v.getDataHora().getYear() == ano && v.getDataHora().getMonthValue() == mes) {
                total += v.getValorTotal();
            }
        }
        return total;
    }

    /**
     * Salva a lista de vendas no arquivo JSON configurado.
     */
    public void salvarVendas() {
        super.salvarLista(caminho, vendas);
    }

    /**
     * Retorna a lista de vendas gerenciada pela classe.
     *
     * @return lista de vendas
     */
    public List<Venda> getVendas() {
        return vendas;
    }

    /**
     * Gera automaticamente um ID único para cada nova venda.
     * O método verifica todos os IDs existentes e encontra o menor número disponível.
     *
     * @return um novo ID válido
     */
    public int geradorIdVenda() {
        if (vendas.isEmpty()) {
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();
        for (Venda venda : vendas) {
            idsExistentes.add(venda.getIdVenda());
        }

        int novoId = 1;

        while (idsExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}
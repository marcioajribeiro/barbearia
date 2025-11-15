/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import agendamento.StatusAgendamento;
import interpreter.AplicarDescontoFixo;
import interpreter.AplicarDescontoPercentual;
import agendamento.Agendamento;
import controller.GerenciadorGenerico;
import controller.GerenciadorProdutos;
import entidades.Produto;
import entidades.Servico;
import interpreter.CondicaoDiaSemana;
import interpreter.CondicaoValorMinimo;
import interpreter.ExpressaoDesconto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafin
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
        if (a.getStatusPagamento() != StatusAgendamento.AGENDAMENTO_CANCELADO) {
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
     * Processa uma venda aplicando regras de desconto e calculando o valor total.
     *
     * @param venda venda a ser processada
     */
    private void processarVenda(Venda venda) {
        double valorServicoComDesconto = calcularValorServicoComRegras(venda);
        double valorProdutos = venda.getProdutos().stream().mapToDouble(Produto::getPreco).sum();
        venda.setValorTotal(valorServicoComDesconto + valorProdutos);
    }

    /**
     * Registra uma nova venda adicionando-a à lista e gerando seu ID automaticamente.
     *
     * @param venda venda a ser registrada
     */
    public void registrarVenda(Venda venda) {
        venda.setIdVenda(geradorIdVenda());
        processarVenda(venda);
        vendas.add(venda);
        System.out.println("Venda registrada com sucesso!");
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
        System.out.println("Venda removida");
    }

    /**
     * Calcula o valor dos serviços aplicando regras de desconto baseadas
     * no padrão Interpreter.
     * <p>
     * São aplicadas duas regras:
     * <ul>
     *   <li>Desconto por dia da semana</li>
     *   <li>Desconto por valor mínimo</li>
     * </ul>
     * O desconto final é o menor valor resultante entre as regras.
     *
     * @param venda venda analisada
     * @return valor final dos serviços após aplicação de descontos
     */
    public double calcularValorServicoComRegras(Venda venda) {
        ExpressaoDesconto regraDescontoDoDia =
                new CondicaoDiaSemana(new AplicarDescontoPercentual());

        ExpressaoDesconto regraDescontoValorMinimo =
                new CondicaoValorMinimo(new AplicarDescontoFixo());

        double valorComDescontoDia = regraDescontoDoDia.interpretar(venda);
        double valorComDescontoValor = regraDescontoValorMinimo.interpretar(venda);

        return Math.min(valorComDescontoDia, valorComDescontoValor);
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
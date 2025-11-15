package financeiro;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Márcio Antônio
 * @author Rafael Martins
 */
/**
 * Representa uma venda realizada no sistema da barbearia.
 *
 * <p>Uma venda contém informações sobre o cliente, funcionário responsável,
 * serviços e produtos envolvidos, forma de pagamento, valor total e data/hora.
 * Também permite identificar vendas referentes a cancelamentos de agendamentos.</p>
 */
public class Venda {

    /** Identificador único da venda. */
    private int idVenda;

    /** Cliente que realizou a compra. */
    private Cliente cliente;

    /** Funcionário responsável pelo atendimento. */
    private Funcionario funcionario;

    /** Lista de produtos incluídos na venda. */
    private List<Produto> produtos;

    /** Lista de serviços prestados ao cliente. */
    private List<Servico> servicos;

    /** Valor total da venda (produtos + serviços, aplicando descontos se houver). */
    private double valorTotal;

    /** Forma de pagamento utilizada pelo cliente. */
    private String formaPagamento;

    /** Data e hora em que a venda foi registrada. */
    private LocalDateTime dataHora;

    /** Indica se a venda corresponde a um cancelamento de agendamento. */
    private boolean cancelamento;

    /**
     * Construtor da classe Venda.
     *
     * @param cliente cliente que realizou a compra
     * @param funcionario funcionário responsável pelo atendimento
     * @param produtos lista de produtos adquiridos
     * @param servicos lista de serviços realizados
     * @param formaPagamento forma de pagamento escolhida
     * @param dataHora data e hora da venda
     */
    public Venda(Cliente cliente, Funcionario funcionario, List produtos, List servicos,
                 String formaPagamento, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produtos = produtos;
        this.servicos = servicos;
        this.formaPagamento = formaPagamento;
        this.dataHora = dataHora;
    }

    /**
     * Retorna o identificador único do cliente.
     *
     * @return o ID do cliente
     */
    public int getIdVenda() {
        return idVenda;
    }

    /**
     * Define o identificador único do cliente.
     *
     * @param idVenda novo ID do cliente
     */
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    /** @return cliente associado à venda */
    public Cliente getCliente() {
        return cliente;
    }

    /** @param cliente define o cliente da venda */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /** @return funcionário responsável */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /** @param funcionario define o funcionário responsável */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /** @return lista de produtos da venda */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /** @param produtos redefine os produtos da venda */
    public void setProdutos(List produtos) {
        this.produtos = produtos;
    }

    /** @return lista de serviços realizados */
    public List<Servico> getServicos() {
        return servicos;
    }

    /** @param servicos redefine os serviços da venda */
    public void setServicos(List servicos) {
        this.servicos = servicos;
    }

    /** @return valor total da venda */
    public double getValorTotal() {
        return valorTotal;
    }

    /** @param valorTotal define o valor total da venda */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /** @return forma de pagamento utilizada */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /** @param formaPagamento define a forma de pagamento */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /** @return data e hora da venda */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /** @param dataHora define a data e hora da venda */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    /** @return true se for venda de cancelamento */
    public boolean isCancelamento() {
        return cancelamento;
    }

    /**
     * Define se a venda é referente a cancelamento.
     *
     * @param cancelada true para venda de cancelamento
     */
    public void setCancelamento(boolean cancelada) {
        this.cancelamento = cancelada;
    }

    /**
     * Gera um extrato de venda formatado para impressão.
     * O extrato contém informações como:
     * <ul>
     *     <li>ID da venda</li>
     *     <li>Data e hora da venda (formatada como "dd/MM/yyyy HH:mm:ss")</li>
     *     <li>Dados do cliente (nome)</li>
     *     <li>Valor total da venda</li>
     * </ul>
     *
     * @return Uma String contendo o extrato completo pronto para impressão.
     */
    public String gerarExtratoParaImpressao() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = fmt.format(dataHora);

        String extrato = "================ COMPROVANTE DE VENDA ================\n";
        extrato += String.format("ID da Venda: %d\n", idVenda);
        extrato += String.format("Data/Hora: %s\n", dataFormatada);
        extrato += "---------------- DADOS DO CLIENTE ----------------\n";
        extrato += String.format("Nome: %s\n", cliente.getNome());
        extrato += String.format("VALOR TOTAL: R$ %.2f\n", valorTotal);
        extrato += "=======================================================\n";
        return extrato;
    }

    /**
     * Retorna uma representação em String do produto,
     * contendo ID, o nome do funcionário que executou a venda, os serviços prestados
     * os produtos comprado, valor total em R$, a forma de pagamento e a data da venda.
     *
     * @return uma string representando a venda.
     */
    @Override
    public String toString() {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String dataFormatada = fmt.format(dataHora);
        List<String> nomesServicos = new ArrayList<>();
        List<String> nomesProdutos = new ArrayList<>();

        for (Servico s : servicos) {
            nomesServicos.add(s.getTipoServico());
        }

        if (produtos.isEmpty()) {
            nomesProdutos.add("Nenhum Produto");
        } else {
            for (Produto p : produtos) {
                nomesProdutos.add(p.getNome());
            }
        }

        String base =
                "ID: " + idVenda +
                        " Cliente: " + cliente.getNome() +
                        " Funcionário: " + funcionario.getNome() +
                        " Serviços: " + String.join(", ", nomesServicos) +
                        " Produtos: " + String.join(", ", nomesProdutos) +
                        " Valor Total: R$ " + valorTotal +
                        " Forma de Pagamento: " + formaPagamento +
                        " Data: " + dataFormatada;

        if (!cancelamento) {
            return base;
        } else {
            return base + " CANCELAMENTO";
        }
    }
}

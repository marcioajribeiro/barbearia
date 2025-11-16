package financeiro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import entidades.Cliente;
import entidades.Funcionario;
import java.util.List;
import entidades.Produto;
import entidades.Servico;

/**
 * Representa uma Nota Fiscal emitida para uma Venda.
 * Esta classe é o comprovante fiscal final da transação.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class NotaFiscal {

    private int idNotaFiscal;
    private int idVendaReferencia; // Referência direta à venda
    private Cliente cliente;
    private Funcionario funcionarioResponsavel;
    private List<Servico> servicos;
    private List<Produto> produtos;
    private double valorTotal;
    private LocalDateTime dataEmissao;
    private String formaPagamento;

    /**
     * Constrói uma Nota Fiscal baseada nos dados de uma Venda.
     * @param venda A venda que gerou esta nota fiscal.
     */
    public NotaFiscal(Venda venda) {
        this.idVendaReferencia = venda.getIdVenda();
        this.cliente = venda.getCliente();
        this.funcionarioResponsavel = venda.getFuncionario();
        this.servicos = venda.getServicos();
        this.produtos = venda.getProdutos();
        this.valorTotal = venda.getValorTotal();
        this.dataEmissao = LocalDateTime.now();
        this.formaPagamento = venda.getFormaPagamento();
    }

    /**
     * Obtém o identificador único da nota fiscal.
     *
     * @return o ID da nota fiscal
     */
    public int getIdNotaFiscal() {
        return idNotaFiscal;
    }

    /**
     * Define o identificador único da nota fiscal.
     *
     * @param idNotaFiscal novo ID da nota fiscal
     */
    public void setIdNotaFiscal(int idNotaFiscal) {
        this.idNotaFiscal = idNotaFiscal;
    }

    /**
     * Retorna o identificador da venda à qual esta nota fiscal está vinculada.
     *
     * @return ID da venda de referência
     */
    public int getIdVendaReferencia() {
        return idVendaReferencia;
    }

    /**
     * Obtém o cliente associado à nota fiscal.
     *
     * @return cliente que realizou a compra/serviço
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtém o funcionário responsável pela venda ou emissão da nota fiscal.
     *
     * @return funcionário responsável
     */
    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    /**
     * Retorna a lista de serviços incluídos na nota fiscal.
     *
     * @return lista de serviços cobrados
     */
    public List<Servico> getServicos() {
        return servicos;
    }

    /**
     * Retorna a lista de produtos incluídos na nota fiscal.
     *
     * @return lista de produtos adquiridos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Retorna o valor total cobrado na nota fiscal.
     *
     * @return valor total da nota
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Obtém a data e hora de emissão da nota fiscal.
     *
     * @return data da emissão
     */
    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    /**
     * Obtém a forma de pagamento utilizada na transação.
     *
     * @return forma de pagamento (ex: "Dinheiro", "Cartão", "Pix")
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Retorna uma representação em String da Nota Fiscal.
     * @return string formatada.
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = fmt.format(dataEmissao);

        return "--- NOTA FISCAL ID: " + idNotaFiscal + " ---\n" +
                "  Venda Ref: " + idVendaReferencia + "\n" +
                "  Data Emissão: " + dataFormatada + "\n" +
                "  Cliente: " + cliente.getNome() + "\n" +
                "  Funcionário: " + funcionarioResponsavel.getNome() + "\n" +
                "  Valor Total: R$ " + String.format("%.2f", valorTotal) + "\n" +
                "  Forma Pgto: " + formaPagamento;
    }
}
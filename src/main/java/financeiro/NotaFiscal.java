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


    public int getIdNotaFiscal() {
        return idNotaFiscal;
    }

    public void setIdNotaFiscal(int idNotaFiscal) {
        this.idNotaFiscal = idNotaFiscal;
    }

    public int getIdVendaReferencia() {
        return idVendaReferencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

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
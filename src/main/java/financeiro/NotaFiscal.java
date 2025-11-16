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
     * Gera um comprovante detalhado da Nota Fiscal, incluindo todos os
     * itens, valores unitários e totais.
     *
     * @return Uma String contendo a Nota Fiscal detalhada.
     */
    public String gerarComprovanteDetalhado() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = fmt.format(dataEmissao);
        StringBuilder nf = new StringBuilder();

        nf.append("================ NOTA FISCAL DE SERVIÇOS E PRODUTOS ================\n");
        nf.append(String.format("ID NF: %d | Ref. Venda: %d\n", idNotaFiscal, idVendaReferencia));
        nf.append(String.format("Data Emissão: %s\n", dataFormatada));
        nf.append("---------------- DADOS DA VENDA ----------------\n");
        nf.append(String.format("Cliente: %s (CPF: %s)\n", cliente.getNome(), cliente.getCpf()));
        nf.append(String.format("Funcionário: %s\n", funcionarioResponsavel.getNome()));
        nf.append(String.format("Forma de Pagamento: %s\n", formaPagamento));
        nf.append("---------------- ITENS COBRADOS ----------------\n");

        if (servicos != null && !servicos.isEmpty()) {
            nf.append("SERVIÇOS:\n");
            for (Servico s : servicos) {
                nf.append(String.format("- %s (Duração: %d min) - R$ %.2f\n",
                        s.getTipoServico(), s.getDuracaoMin(), s.getValor()));
            }
            nf.append("\n");
        }

        if (produtos != null && !produtos.isEmpty()) {
            nf.append("PRODUTOS:\n");
            for (Produto p : produtos) {

                nf.append(String.format("- %s (Fornecedor: %s) - R$ %.2f\n",
                        p.getNome(), p.getFornecedor(), p.getPreco()));
            }
            nf.append("\n");
        }

        if ((servicos == null || servicos.isEmpty()) && (produtos == null || produtos.isEmpty())) {
            nf.append("Nenhum item registrado nesta Nota Fiscal.\n\n");
        }


        nf.append("---------------- TOTAL ----------------\n");
        nf.append(String.format("VALOR TOTAL: R$ %.2f\n", valorTotal));
        nf.append("====================================================================\n");

        return nf.toString();
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
            return gerarComprovanteDetalhado();
        }

}
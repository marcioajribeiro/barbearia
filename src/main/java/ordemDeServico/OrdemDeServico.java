/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordemdeservico;


import barbearia.Barbearia;
import barbearia.QuestoesRespondidas;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Representa uma Ordem de Serviço na barbearia

 *
 * @author Marcio
 * @author Rafael Martins
 */
public class OrdemDeServico {

    /**Identificador único da ordem de serviço.*/
    private int idOS;

    /**Cliente associado a esta ordem de serviço*/
    private Cliente cliente;

    /**Funcionário responsável pela execução da ordem de serviço.*/
    private Funcionario funcionario;

    /**Lista de produtos utilizados nesta ordem de serviço.*/
    private List<Produto> produto;

    /**Lista de serviços realizados nesta ordem de serviço.*/
    private List<Servico> servicos;

    /**Data e hora em que a ordem de serviço foi criada.*/
    private LocalDateTime dataHora;

    /**Observações adicionais relacionadas à ordem de serviço.*/
    private String observacoes;

    /**Status atual da ordem de serviços*/
    private TipoStatusOs statusOs;

    /**Valor total calculado da ordem de serviço (produtos + serviços).*/
    private double valorTotal;


    /**
     * Constrói uma nova Ordem de Serviço com cliente, funcionário,
     * lista de produtos, lista de serviços e data/hora.
     * A OS inicia com status "AGUARDANDO" e valor total zerado.
     *
     * @param cliente      Cliente associado à OS.
     * @param funcionario  Funcionário responsável pela execução da OS.
     * @param produto      Lista de produtos utilizados (pode ser vazia).
     * @param servicos     Lista de serviços realizados.
     * @param dataHora     Data e hora da criação da OS.
     */
    public OrdemDeServico(Cliente cliente, Funcionario funcionario,
                          List<Produto> produto, List<Servico> servicos,
                          LocalDateTime dataHora) {

        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produto = produto;
        this.servicos = servicos;
        this.dataHora = dataHora;
        this.statusOs = TipoStatusOs.ESTADO_AGUARDANDO;
        this.valorTotal = 0;
        QuestoesRespondidas.registrarNovaOs();
    }

    /**
     * Gera um extrato detalhado da Ordem de Serviço formatado para impressão.
     * O extrato inclui:
     * <ul>
     *     <li>Informações essenciais da OS (ID, status e data/hora)</li>
     *     <li>Dados do cliente e funcionário</li>
     *     <li>Serviços realizados com valores e duração</li>
     *     <li>Produtos utilizados e seus preços</li>
     *     <li>Valor total acumulado</li>
     *     <li>Observações adicionais (se houver)</li>
     * </ul>
     *
     * @return Uma String contendo o extrato completo e formatado.
     */
    public String gerarExtrato() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = fmt.format(dataHora);
        StringBuilder extrato = new StringBuilder();

        extrato.append("================ ORDEM DE SERVIÇO ================\n");
        extrato.append(String.format("ID da OS: %d\n", idOS));
        extrato.append(String.format("Status: %s\n", statusOs));
        extrato.append(String.format("Data/Hora: %s\n", dataFormatada));
        extrato.append("---------------- DADOS ----------------\n");
        extrato.append(String.format("Cliente: %s (CPF: %s)\n", cliente.getNome(), cliente.getCpf()));
        extrato.append(String.format("Funcionário: %s\n", funcionario.getNome()));
        extrato.append("---------------- ITENS ----------------\n");

        if (servicos != null && !servicos.isEmpty()) {
            extrato.append("SERVIÇOS:\n");
            for (Servico s : servicos) {
                extrato.append(String.format("- %s (Duração: %d min) - R$ %.2f\n",
                        s.getTipoServico(), s.getDuracaoMin(), s.getValor()));
            }
        } else {
            extrato.append("Nenhum Serviço Adicionado.\n");
        }

        extrato.append("\n");

        if (produto != null && !produto.isEmpty()) {
            extrato.append("PRODUTOS:\n");
            for (Produto p : produto) {
                extrato.append(String.format("- %s (Fornecedor: %s) - R$ %.2f\n",
                        p.getNome(), p.getFornecedor(), p.getPreco()));
            }
        } else {
            extrato.append("Nenhum Produto Adicionado.\n");
        }

        extrato.append("---------------- TOTAL ----------------\n");
        extrato.append(String.format("Valor Total: R$ %.2f\n", valorTotal));

        if (observacoes != null && !observacoes.trim().isEmpty()) {
            extrato.append("---------------- OBSERVAÇÕES ----------------\n");
            extrato.append(observacoes).append("\n");
        }

        extrato.append("===============================================\n");
        return extrato.toString();
    }

    /**
     * Retorna o identificador único da OS.
     * @return ID da Ordem de Serviço.
     */
    public int getIdOS() {
        return idOS;
    }

    /**
     * Define o identificador único da OS.
     * @param idOS Novo ID da OS.
     */
    public void setIdOS(int idOS) {
        this.idOS = idOS;
    }

    /**
     * Obtém o cliente associado à OS.
     * @return Cliente da OS.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente associado à OS.
     * @param cliente Novo cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtém o funcionário responsável pela OS.
     * @return Funcionário da OS.
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Define o funcionário responsável.
     * @param funcionario Novo funcionário.
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Retorna a lista de produtos utilizados na OS.
     * @return Lista de produtos.
     */
    public List<Produto> getProduto() {
        return produto;
    }

    /**
     * Define a lista de produtos utilizados.
     * @param produto Lista de produtos.
     */
    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    /**
     * Retorna a lista de serviços realizados.
     * @return Lista de serviços.
     */
    public List<Servico> getServicos() {
        return servicos;
    }

    /**
     * Define a lista de serviços da OS.
     * @param servicos Lista de serviços.
     */
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    /**
     * Retorna a data e hora da criação da OS.
     * @return Data e hora da OS.
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Define a data e hora da OS.
     * @param dataHora Nova data e hora.
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Retorna o valor total calculado da OS.
     * @return Valor total da OS.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o valor total da OS.
     * @param valorTotal Novo valor total.
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Retorna as observações registradas na OS.
     * @return Observações ou null.
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * Define as observações da OS.
     * @param observacoes Texto de observações.
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * Retorna o status atual da OS.
     * @return Status da Ordem de Serviço.
     */
    public TipoStatusOs getStatusOs() {
        return statusOs;
    }

    /**
     * Define o status atual da OS.
     * @param statusOs Novo status.
     */
    public void setStatusOs(TipoStatusOs statusOs) {
        this.statusOs = statusOs;
    }

    /**
     * Retorna uma representação textual da Ordem de Serviço,
     * útil para exibições rápidas e depuração.
     *
     * @return String contendo os dados principais da OS.
     */
    @Override
    public String toString() {
        return "ID: " + idOS +
                ", Cliente: " + cliente.getNome() +
                ", Funcionario: " + funcionario.getNome() +
                ", Produto: " + produto +
                ", Servicos: " + servicos +
                ", DataHora: " + dataHora +
                ", ValorTotal: " + valorTotal +
                ", Observacoes: " + observacoes +
                ", Status: " + statusOs;
    }
}
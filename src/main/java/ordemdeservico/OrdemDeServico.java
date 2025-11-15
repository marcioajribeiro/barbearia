/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordemdeservico;


import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class OrdemDeServico {
    
    private int idOS;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Produto> produto;
    private List<Servico> servicos; 
    private LocalDateTime dataHora;
    private String observacoes;
    private TipoStatusOs statusOs;
    private double valorTotal;
    
    public OrdemDeServico(Cliente cliente, Funcionario funcionario, List<Produto> produto, List<Servico> servicos, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produto = produto;
        this.servicos = servicos;
        this.dataHora = dataHora;
        this.statusOs = TipoStatusOs.ESTADO_AGUARDANDO;
        this.valorTotal = 0;
    }

    /**
     * Gera um extrato detalhado da Ordem de Serviço formatado para impressão.
     * Inclui serviços, produtos, valores, status e observações.
     *
     * @return Uma String contendo o extrato completo da OS.
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

                extrato.append(String.format("- %s (Duração: %d min) - R$ %.2f\n", s.getTipoServico(), s.getDuracaoMin(), s.getValor()));
            }
        } else {
            extrato.append("Nenhum Serviço Adicionado.\n");
        }

        extrato.append("\n");

        if (produto != null && !produto.isEmpty()) {
            extrato.append("PRODUTOS:\n");
            for (Produto p : produto) {
                extrato.append(String.format("- %s (Fornecedor: %s) - R$ %.2f\n", p.getNome(), p.getFornecedor(), p.getPreco()));
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

    public int getIdOS() {
        return idOS;
    }

    public void setIdOS(int idOS) {
        this.idOS = idOS;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getValorTotal() {
       return valorTotal ;
   }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }



    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public TipoStatusOs getStatusOs() {
        return statusOs;
    }

    public void setStatusOs(TipoStatusOs statusOs) {
        this.statusOs = statusOs;
    }

    @Override
    public String toString() {
        return
                "ID: " + idOS +
                        ", Cliente: " + cliente.getNome() +
                        ", Funcionario: " + funcionario.getNome() +
                        ", Produto:  " + produto +
                        ", Servicos: " + servicos +
                        ", DataHora: " + dataHora  +
                        ", ValorTotal: " + valorTotal  +
                        ", Observacoes: " + observacoes  +
                        ", Status: " + statusOs ;
    }
 
      
}
package entidades;

import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import java.time.LocalDateTime;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafin
 */
public class Venda {
    private int idVenda;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Produto> produtos;
    private List<Servico> servicos;
    private double valorTotal;
    private String formaPagamento;
    private LocalDateTime dataHora;

    public Venda(int idVenda, Cliente cliente, Funcionario funcionario, List produtos, List servicos, double valorTotal, String formaPagamento, LocalDateTime dataHora) {
        this.idVenda = idVenda;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produtos = produtos;
        this.servicos = servicos;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.dataHora = dataHora;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
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

    public List getProdutos() {
        return produtos;
    }

    public void setProdutos(List produtos) {
        this.produtos = produtos;
    }

    public List getServicos() {
        return servicos;
    }

    public void setServicos(List servicos) {
        this.servicos = servicos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double calcularValorTotal() {
        double total = 0;
        if (produtos != null) {
            for (Produto p : produtos) {
                total += p.getPreco();
            }
        }
        if (servicos != null) {
            for (Servico s : servicos) {
                total += s.getValor();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "\nVenda #" + idVenda +
                "\nCliente: " + cliente.getNome() +
                "\nFuncionário: " + funcionario.getNome() +
                "\nValor Total: R$ " + valorTotal +
                "\nForma de Pagamento: " + formaPagamento +
                "\nData: " + dataHora + "\n";
    }

}

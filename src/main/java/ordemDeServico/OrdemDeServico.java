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
import java.util.List;
import ordemDeServico.TipoStatusOs;

/**
 *
 * @author rafin
 */
public class OrdemDeServico {
    
    private int idOS;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Produto> produto;
    private List<Servico> servicos; 
    private LocalDateTime dataHora;
    private String observacoes;
    private TipoStatusOs statusOs;
    
    public OrdemDeServico(Cliente cliente, Funcionario funcionario, List<Produto> produto, List<Servico> servicos, LocalDateTime dataHora, String observacoes) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produto = produto;
        this.servicos = servicos;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.statusOs = TipoStatusOs.OS_PENDENTE;
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

    //public double getValorTotal() {
    //    return valorTotal;
   // }

   // public void setValorTotal(double valorTotal) {
   //     this.valorTotal = valorTotal;
    //}

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
                       // ", ValorTotal: " + valorTotal  +
                        ", Observacoes: " + observacoes  +
                        ", Status: " + statusOs ;
    }
 
      
}
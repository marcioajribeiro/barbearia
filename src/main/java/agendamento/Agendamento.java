/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamento;

import entidades.Cliente;
import entidades.Funcionario;
import entidades.Servico;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MARCIO JUNIOR
 */
public class Agendamento {
    private int id;
    private Cliente cliente;
    private Funcionario barbeiro;
    private List<Servico> servicos;
    private LocalDateTime dataHora;
    private double valor;
    private StatusPagamento statusPagamento;

    public Agendamento( Cliente cliente, Funcionario funcionario, List<Servico> servicos, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.barbeiro = funcionario;
        this.servicos = servicos;
        this.dataHora = dataHora;
        this.valor = servicos.stream().mapToDouble(Servico::getValor).sum();
        this.statusPagamento = StatusPagamento.PAGAMENTO_PENDENTE;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return barbeiro;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.barbeiro = funcionario;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        String dataFormatada = fmt.format(dataHora);
        List<String> nomesServicos = new ArrayList<>();
        
        for(Servico s: servicos){
            nomesServicos.add(s.getTipoServico());
        }
        
        return "ID: " + id +
                ", Cliente: " +cliente.getNome() +
                ", Barbeiro: " + barbeiro.getNome() +
                ", Data e Hora: " + dataFormatada +
                ", Serviços: " + String.join(", ", nomesServicos) +
                ", Status: " + statusPagamento;
}
    
    

   
    
    
    
    
    
}

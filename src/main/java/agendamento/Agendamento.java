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
 * Representa um agendamento realizado no sistema da barbearia.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class Agendamento {

    /** Identificador único do agendamento. */
    private int id;

    /** Cliente que solicitou o agendamento. */
    private Cliente cliente;

    /** Barbeiro responsável por realizar o atendimento. */
    private Funcionario barbeiro;

    /** Lista de serviços selecionados no agendamento. */
    private List<Servico> servicos;

    /** Data e hora marcadas para o atendimento. */
    private LocalDateTime dataHora;

    /** Valor total do agendamento, calculado com base nos serviços. */
    private double valor;

    /** Status atual do agendamento (pendente, concluído, cancelado etc.). */
    private StatusAgendamento statusAgendamento;

    /**
     * Cria um novo agendamento.
     * A data deve ser futura; caso seja anterior ao momento atual,
     * será lançada uma IllegalArgumentException.
     * O valor total é calculado automaticamente com base nos serviços informados.
     *
     * @param cliente    o cliente que realizará o agendamento.
     * @param funcionario o barbeiro responsável pelo atendimento.
     * @param servicos   lista de serviços selecionados.
     * @param dataHora   data e hora do agendamento.
     * @throws IllegalArgumentException se a data e hora forem anteriores ao momento atual.
     */
    public Agendamento(Cliente cliente, Funcionario funcionario, List<Servico> servicos, LocalDateTime dataHora) {
        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data Inválida");
        }

        this.cliente = cliente;
        this.barbeiro = funcionario;
        this.servicos = servicos;
        this.dataHora = dataHora;
        this.valor = servicos.stream().mapToDouble(Servico::getValor).sum();
        this.statusAgendamento = StatusAgendamento.AGENDAMENTO_PENDENTE;
    }

    /**
     * Retorna o identificador do agendamento.
     *
     * @return o ID do agendamento.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do agendamento.
     *
     * @param id novo ID do agendamento.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o cliente associado ao agendamento.
     *
     * @return o cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente do agendamento.
     *
     * @param cliente novo cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna o barbeiro responsável pelo atendimento.
     *
     * @return o funcionário barbeiro.
     */
    public Funcionario getFuncionario() {
        return barbeiro;
    }

    /**
     * Define o barbeiro responsável pelo atendimento.
     *
     * @param funcionario novo barbeiro.
     */
    public void setFuncionario(Funcionario funcionario) {
        this.barbeiro = funcionario;
    }

    /**
     * Retorna a lista de serviços selecionados.
     *
     * @return lista de serviços.
     */
    public List<Servico> getServicos() {
        return servicos;
    }

    /**
     * Define os serviços do agendamento.
     *
     * @param servicos nova lista de serviços.
     */
    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    /**
     * Retorna a data e hora do agendamento.
     *
     * @return data e hora.
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Define uma nova data e hora para o agendamento.
     *
     * @param dataHora nova data e hora.
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Retorna o valor total do agendamento.
     *
     * @return o valor calculado.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define manualmente o valor do agendamento.
     * (Geralmente não utilizado, pois o valor é calculado automaticamente.)
     *
     * @param valor novo valor.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna o status atual do agendamento.
     *
     * @return o status.
     */
    public StatusAgendamento getStatusAgendamento() {
        return statusAgendamento;
    }

    /**
     * Define um novo status para o agendamento.
     *
     * @param statusPagamento novo status.
     */
    public void setStatusAgendamento(StatusAgendamento statusPagamento) {
        this.statusAgendamento = statusPagamento;
    }

    /**
     * Retorna uma representação textual do agendamento,
     * contendo cliente, barbeiro, data, serviços e status.
     *
     * @return string formatada com informações do agendamento.
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String dataFormatada = fmt.format(dataHora);
        List<String> nomesServicos = new ArrayList<>();

        for (Servico s : servicos) {
            nomesServicos.add(s.getTipoServico());
        }

        return "ID: " + id +
                ", Cliente: " + cliente.getNome() +
                ", Barbeiro: " + barbeiro.getNome() +
                ", Data e Hora: " + dataFormatada +
                ", Serviços: " + String.join(", ", nomesServicos) +
                ", Status: " + statusAgendamento;
    }
}
    
    

   
    
    
    
    
    


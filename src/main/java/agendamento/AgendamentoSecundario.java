package agendamento;

import entidades.Cliente;
import entidades.Servico;

import java.time.format.DateTimeFormatter;

/**
 * Representa um agendamento secundário na fila de espera da barbearia.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class AgendamentoSecundario {

    /** Identificador da posição do cliente na fila de espera. */
    private int idFila;

    /** Cliente que está aguardando atendimento. */
    private Cliente cliente;

    /** Serviço desejado pelo cliente. */
    private Servico servicoDesejado;

    /**
     * Construtor para criar um agendamento secundário.
     *
     * @param cliente          Cliente que está entrando na fila
     * @param idFila           Identificador da posição na fila
     * @param servicoDesejado  Serviço que o cliente deseja realizar
     */
    public AgendamentoSecundario(Cliente cliente, int idFila, Servico servicoDesejado) {
        this.cliente = cliente;
        this.idFila = idFila;
        this.servicoDesejado = servicoDesejado;
    }

    /**
     * Obtém o identificador da fila.
     *
     * @return id da posição na fila
     */
    public int getIdFila() {
        return idFila;
    }

    /**
     * Define o identificador da fila.
     *
     * @param idFila novo identificador da fila
     */
    public void setIdFila(int idFila) {
        this.idFila = idFila;
    }

    /**
     * Obtém o cliente associado ao agendamento.
     *
     * @return cliente do agendamento
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente do agendamento.
     *
     * @param cliente novo cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtém o serviço desejado pelo cliente.
     *
     * @return serviço solicitado
     */
    public Servico getServicoDesejado() {
        return servicoDesejado;
    }

    /**
     * Define o serviço desejado pelo cliente.
     *
     * @param servicoDesejado novo serviço desejado
     */
    public void setServicoDesejado(Servico servicoDesejado) {
        this.servicoDesejado = servicoDesejado;
    }

    /**
     * Retorna uma representação textual do agendamento secundário,
     * contendo ID da fila, nome do cliente e serviço solicitado.
     *
     * @return texto representando o agendamento
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return "ID: " + idFila +
                ", Cliente: " + cliente.getNome() +
                ", Serviços: " + servicoDesejado.getTipoServico();
    }
}

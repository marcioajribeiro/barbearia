package agendamento;

import entidades.Cliente;
import entidades.Servico;

import java.time.format.DateTimeFormatter;

public class AgendamentoSecundario {

    private int idFila;
    private Cliente cliente;
    private Servico servicoDesejado;

    public AgendamentoSecundario(Cliente cliente, int idFila, Servico servicoDesejado) {
        this.cliente = cliente;
        this.idFila = idFila;
        this.servicoDesejado = servicoDesejado;
    }

    public int getIdFila() {
        return idFila;
    }

    public void setIdFila(int idFila) {
        this.idFila = idFila;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServicoDesejado() {
        return servicoDesejado;
    }

    public void setServicoDesejado(Servico servicoDesejado) {
        this.servicoDesejado = servicoDesejado;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


        return "ID:  " + idFila +
                ", Cliente: " + cliente.getNome() +
                ", Serviços: " + servicoDesejado.getTipoServico();

    }
}

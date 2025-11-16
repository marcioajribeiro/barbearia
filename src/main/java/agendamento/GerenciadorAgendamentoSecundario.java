package agendamento;

import controller.GerenciadorGenerico;
import java.util.*;

/**
 * Classe responsável por gerenciar a fila de espera de agendamentos secundários
 * (clientes que desejam um serviço, mas todas as estações estão ocupadas).
 *
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorAgendamentoSecundario extends GerenciadorGenerico {

    /** Estrutura FIFO que armazena os agendamentos secundários. */
    private final Queue<AgendamentoSecundario> filaDeEspera;

    /** Caminho do arquivo JSON utilizado para persistir a fila. */
    private final String caminho = "Json/JsonFilaDeEspera.json";

    /**
     * Construtor: Carrega os agendamentos secundários armazenados no arquivo JSON
     * e inicia a fila de espera.
     */
    public GerenciadorAgendamentoSecundario() {
        List<AgendamentoSecundario> listaFila =
                super.carregarListas(caminho, AgendamentoSecundario.class);

        this.filaDeEspera = new LinkedList<>(listaFila != null ? listaFila : new LinkedList<>());
    }

    /**
     * Adiciona um novo cliente à fila de espera.
     *
     * @param agendamentoSecundario agendamento a ser inserido na fila
     */
    public void addFila(AgendamentoSecundario agendamentoSecundario) {
        agendamentoSecundario.setIdFila(geradorIdFilaEspera());
        filaDeEspera.offer(agendamentoSecundario);
        System.out.println("Cliente adicionado em lista de espera");
    }

    /**
     * Remove e retorna o próximo cliente da fila de espera (FIFO).
     *
     *
     * @return o próximo agendamento da fila, ou {@code null} caso a fila esteja vazia
     */
    public AgendamentoSecundario chamarProximoDaFila() {
        AgendamentoSecundario proximo = filaDeEspera.poll();
        if (proximo == null) {
            System.out.println("A fila de agendamentos secundários está vazia.");
        }
        return proximo;
    }

    /**
     * Exibe todos os itens da fila de espera no console, mantendo a ordem FIFO.
     *
     */
    public void listarFila() {
        if (filaDeEspera.isEmpty()) {
            System.out.println("A fila de agendamentos secundários está vazia.");
        }

        int posicao = 1;
        System.out.println("----- Fila de Espera (Agendamentos Secundários - FIFO) -----");

        for (AgendamentoSecundario agendamento : filaDeEspera) {
            System.out.println("Posição: " + posicao + " - Agendamento: " + agendamento);
            posicao++;
        }
    }

    /**
     * Salva o estado atual da fila de espera no arquivo JSON.
     *
     */
    public void salvarFila() {
        super.salvarLista(caminho, new ArrayList<>(filaDeEspera));
    }

    /**
     * Gera um novo ID para um agendamento secundário que ainda não esteja presente na fila.
     *
     * @return um ID único para o próximo agendamento da fila
     */
    public int geradorIdFilaEspera() {
        if (filaDeEspera.isEmpty()) {
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();

        for (AgendamentoSecundario agendamento : filaDeEspera) {
            idsExistentes.add(agendamento.getIdFila());
        }

        int novoId = 1;
        while (idsExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}

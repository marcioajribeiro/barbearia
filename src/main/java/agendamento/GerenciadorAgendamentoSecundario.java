package agendamento;

import controller.GerenciadorGenerico;

import java.util.*;

public class GerenciadorAgendamentoSecundario extends GerenciadorGenerico {

    private final Queue<AgendamentoSecundario> filaDeEspera;
    private final String caminho = "Json/JsonFilaEspera.json";

    public GerenciadorAgendamentoSecundario() {
        List<AgendamentoSecundario> listaFila = super.carregarListas(caminho, AgendamentoSecundario.class);
        this.filaDeEspera = new LinkedList<>(listaFila != null ? listaFila : new LinkedList<>());
    }

    public void addFila(AgendamentoSecundario agendamentoSecundario) {
        agendamentoSecundario.setIdFila(geradorIdFilaEspera());
        filaDeEspera.offer(agendamentoSecundario);
        System.out.println("Cliente adicionado em lista de espera");
    }

    public AgendamentoSecundario chamarProximoDaFila(){
        AgendamentoSecundario proximo = filaDeEspera.poll();
        if(proximo == null){
            System.out.println("A fila de agendamentos secundários está vazia.");
        }

        return proximo;
    }

    public void listarFila(){
        if(filaDeEspera.isEmpty()){
            System.out.println("A fila de agendamentos secundários está vazia.");
        }
        int posicao = 1;
        System.out.println("----- Fila de Espera (Agendamentos Secundários - FIFO) -----");
        for(AgendamentoSecundario agendamento : filaDeEspera){
            System.out.println("Posição: " + posicao + " - Agendamento: " + agendamento);
            posicao++;
        }
    }

    public void salvarFila(){
        super.salvarLista(caminho, new ArrayList<>(filaDeEspera));
    }

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

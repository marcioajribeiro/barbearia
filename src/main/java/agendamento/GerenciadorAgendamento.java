/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamento;

import comparator.AgendamentoBarbeiroComparator;
import comparator.AgendamentoDatasRecentesComparator;
import controller.GerenciadorGenerico;
import controller.GerenciadorServicos;
import entidades.Servico;
import financeiro.GerenciadorVenda;
import interpreter.BuscaPorData;
import interpreter.BuscaPorFuncionario;
import interpreter.EBusca;
import interpreter.ExpressaoBusca;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar os agendamentos da barbearia.
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe GerenciadorGenerico
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorAgendamento extends GerenciadorGenerico {

    /** Lista contendo todos os agendamentos registrados no sistema. */
    private List<Agendamento> agendaBarbearia;


    /** Gerenciador de agendamentos secundários (fila de espera). */
    private final GerenciadorAgendamentoSecundario gas;

    /** Caminho do arquivo JSON onde os agendamentos serão armazenados. */
    private final String caminho = "Json/JsonAgendamento.json";

    /**
     * Construtor responsável por carregar a lista de agendamentos
     * e inicializar os gerenciadores necessários.
     *
     * @param gas gerenciador de agendamento secundario
     *
     */
    public GerenciadorAgendamento(GerenciadorAgendamentoSecundario gas) {
        this.agendaBarbearia = super.carregarListas(caminho, Agendamento.class);
        this.gas = gas;
    }

    /**
     * Adiciona um novo agendamento à agenda da barbearia, desde que
     * o horário e recursos desejados estejam disponíveis.
     *
     * @param novoAgendamento objeto contendo todas as informações do agendamento
     * @throws Exception caso exista conflito de horário com o funcionário ou estação de atendimento
     */
    public void addAgendamento(Agendamento novoAgendamento) throws Exception {
        if (!verificarDisponibilidade(novoAgendamento.getDataHora(), novoAgendamento)) {
            throw new Exception("Horário indisponível para o barbeiro selecionado!");
        }
        novoAgendamento.setId(geradorIdAgendamento());
        agendaBarbearia.add(novoAgendamento);
    }

    /**
     * Altera o status do agendamento para cancelado e aplica taxa de reembolso.
     *
     * @param agendamento agendamento que terá o status alterado
     */
    public void alterarStatusCancelado(Agendamento agendamento) {
        agendamento.setValor(agendamento.getValor() * 0.35);
        agendamento.setStatusAgendamento(StatusAgendamento.AGENDAMENTO_CANCELADO);
    }

    /**
     * Altera o status do agendamento para confirmado.
     *
     * @param agendamento agendamento a ser confirmado
     */
    public void alterarStatusConfirmado(Agendamento agendamento) {
        agendamento.setStatusAgendamento(StatusAgendamento.AGENDAMENTO_CONFIRMADO);
    }

    /**
     * Busca um agendamento pelo seu ID.
     *
     * @param id identificador único do agendamento
     * @return o objeto Agendamento, ou null caso não seja encontrado
     */
    public Agendamento buscarAgendamentoId(int id) {
        for (Agendamento agendamento : agendaBarbearia) {
            if (agendamento.getId() == id) {
                return agendamento;
            }
        }
        System.out.println("Id: " + id + " não encontrado");
        return null;
    }

    /**
     * Lista todos os agendamentos marcados para o dia atual.
     */
    public void listarAgendamentosDoDia() {
        LocalDate hoje = LocalDate.now();
        List<Agendamento> agendaHoje = agendaBarbearia.stream()
                .filter(a -> a.getDataHora().toLocalDate().equals(hoje))
                .collect(Collectors.toList());

        if (agendaHoje.isEmpty()) {
            System.out.println("Não há agendamentos para hoje");
        } else {
            System.out.println("----- Agendamentos de hoje (" + hoje + ") -----");
            for (Agendamento a : agendaHoje) {
                System.out.println(a);
            }
        }
    }
    /**
     * Retorna a lista completa de agendamentos da barbearia.
     *
     * @return uma lista contendo todos os agendamentos da barbearia.
     */
    public List<Agendamento> getAgendaBarbearia() {
        return agendaBarbearia;
    }

    /**
     * Realiza uma busca composta usando o operador E (AND) por data e nome de barbeiro.
     * @param dataString A data do agendamento a ser buscado (formato esperado: "dd/MM/yyyy").
     * @param nomeBarbeiro O nome ou parte do nome do barbeiro.
     * @return Uma lista de Agendamentos que ocorrem na data E com o barbeiro especificado.
     */
    public List<Agendamento> buscarInterpreter(String dataString, String nomeBarbeiro) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDesejada = LocalDate.parse(dataString, dateFormatter);


        ExpressaoBusca buscaData = new BuscaPorData(dataDesejada);
        ExpressaoBusca buscaFuncionario = new BuscaPorFuncionario(nomeBarbeiro);


        ExpressaoBusca buscaComposta = new EBusca(buscaData, buscaFuncionario);


        return buscaComposta.interpretar(agendaBarbearia);
    }

    /**
     * Verifica se o horário, funcionário e estações desejadas estão disponíveis
     * para a realização do novo agendamento.
     *
     * @param dataHoraDesejada horário solicitado
     * @param agendamento objeto contendo os serviços e o funcionário
     * @return true se o horário estiver disponível, false caso contrário
     */
    public boolean verificarDisponibilidade(LocalDateTime dataHoraDesejada, Agendamento agendamento) {

        int duracaoTotalDesejada = agendamento.getServicos()
                .stream().mapToInt(Servico::getDuracaoMin).sum();

        LocalDateTime finalDataHora = dataHoraDesejada.plusMinutes(duracaoTotalDesejada);

        Set<Integer> idsEstacoesDesejadas = agendamento.getServicos()
                .stream().map(s -> s.getEstacaoUsada().getId()).collect(Collectors.toSet());

        for (Agendamento existente : agendaBarbearia) {

            if (existente.getFuncionario().equals(agendamento.getFuncionario())) {

                LocalDateTime inicioExistente = existente.getDataHora();
                int duracaoExistente = existente.getServicos().stream().mapToInt(Servico::getDuracaoMin).sum();
                LocalDateTime fimExistente = inicioExistente.plusMinutes(duracaoExistente);

                boolean sobrepoe =
                        dataHoraDesejada.isBefore(fimExistente) &&
                                finalDataHora.isAfter(inicioExistente);

                if (sobrepoe) {

                    System.err.println("ERRO: O barbeiro " +
                            agendamento.getFuncionario().getNome() +
                            " já está ocupado nesse horário: " +
                            inicioExistente + " até " + fimExistente);

                    Set<Integer> idsEstacoesExistentes = existente.getServicos()
                            .stream().map(s -> s.getEstacaoUsada().getId())
                            .collect(Collectors.toSet());

                    for (Integer idDesejado : idsEstacoesDesejadas) {
                        if (idsEstacoesExistentes.contains(idDesejado)) {
                            System.err.println("ERRO: A Estação de Atendimento ID "
                                    + idDesejado +
                                    " está ocupada por outro agendamento nesse horário.");
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Lista todos os agendamentos cadastrados no sistema.
     */
    public void listarAgendamentos() {
        if (agendaBarbearia.isEmpty()) {
            System.out.println("Não há produto cadastrado");
        } else {
            System.out.println("-----Lista de Produtos-----");
            for (Agendamento a : agendaBarbearia) {
                System.out.println(a);
            }
        }
    }

    /**
     * Organiza os agendamentos em ordem dos mais recentes para os mais antigos.
     * Utiliza o AgendamentoDatasRecentesComparator.
     */
    public void organizarPorMaisRecentes() {
        Collections.sort(agendaBarbearia, new AgendamentoDatasRecentesComparator());
    }

    /**
     * Organiza a lista de agendamentos da barbearia em ordem alfabética pelo nome do barbeiro.
     * Utiliza o AgendamentoBarbeiroComparator.
     */
    public void organizarPorBarbeiro() {
        Collections.sort(agendaBarbearia, new AgendamentoBarbeiroComparator());
    }

    /**
     * Salva todos os agendamentos no arquivo JSON.
     */
    public void atulizarAgenda() {
        super.salvarLista(caminho, agendaBarbearia);
    }

    /**
     * Delega a função de adicionar um agendamento secundário ao respectivo gerenciador.
     * @param agendamentoSecundario
     */
    public void adicionarAgendamentoSecundario(AgendamentoSecundario agendamentoSecundario) {
        gas.addFila(agendamentoSecundario);
    }


    /**
     * Delega a função de listar a fila de espera ao respectivo gerenciador.
     */
    public AgendamentoSecundario chamarProximoDaFila() {
        return gas.chamarProximoDaFila();
    }

    /**
     * Salva o estado atual da fila de agendamentos.
     */
    public void salvarFila() {
        gas.salvarFila();
    }


    /**
     * Gera um novo ID único para um agendamento.
     *
     * @return novo ID não utilizado
     */
    public int geradorIdAgendamento() {
        if (agendaBarbearia.isEmpty()) {
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();
        for (Agendamento agendamento : agendaBarbearia) {
            idsExistentes.add(agendamento.getId());
        }

        int novoId = 1;
        while (idsExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}

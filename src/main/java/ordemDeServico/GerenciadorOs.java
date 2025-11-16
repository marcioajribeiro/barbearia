package ordemdeservico;

import agendamento.Agendamento;
import agendamento.StatusAgendamento;
import controller.GerenciadorGenerico;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;

import estacoesatendimento.GerenciadorDeEstacoes;
import java.util.stream.Collectors;

/**
 * Classe responsável por gerenciar o ciclo de vida das Ordens de Serviço (OS),
 * incluindo cálculo de valor, persistência, alteração de status e integração com Agendamentos.
 * * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorOs extends GerenciadorGenerico {

    /**Lista que armazena todas as ordens de serviço registradas no sistema.*/
    private List<OrdemDeServico> listaOs;

    /**Caminho do arquivo JSON utilizado para salvar e carregaros dados das ordens de serviço.*/
    private final String caminho = "Json/JsonOs.json";

    /**Construtor que inicializa o gerenciador e suas dependências.*/
    public GerenciadorOs() {
        this.listaOs = super.carregarListas(caminho, OrdemDeServico.class);
    }


    /**
     * Adiciona uma nova ordem de serviço à lista de ordens registradas.
     *
     * @param o a ordem de serviço que será adicionada à lista
     */
    public void addOrdemServico(OrdemDeServico o) {
        this.listaOs.add(o);
    }

    /**
     * Adiciona um Produto a uma OS existente e recalcula o valor total.
     * * @param Produto a ser adicionado.
     * @param os Ordem de Serviço alvo.
     */
    public void addProduto(Produto produto, OrdemDeServico os) {
        os.getProduto().add(produto);
        calcularValorTotal(os);
    }




    /**
     * Adiciona um Serviço a uma OS existente e recalcula o valor total.
     * * @param Serviço a ser adicionado.
     * @param os Ordem de Serviço alvo.
     */
    public void addServico(Servico servico, OrdemDeServico os) {
        os.getServicos().add(servico);
        calcularValorTotal(os);
    }

    /**
     * Cria e abre uma nova Ordem de Serviço (OS) para o cliente e funcionário informados.
     *
     * @param cliente     o cliente para o qual a ordem de serviço será aberta
     * @param funcionario o funcionário responsável pela execução da ordem de serviço
     * @return a nova  OrdemDeServico criada; ou null se cliente ou funcionário forem inválidos
     */
    public OrdemDeServico abrirOs(Cliente cliente, Funcionario funcionario) {
        if(cliente == null || funcionario == null){
            return null;
        }

        OrdemDeServico os = new OrdemDeServico(cliente, funcionario,
                new ArrayList<>(),new ArrayList<>(),
                LocalDateTime.now());

        addOrdemServico(os);
        return os;

    }

    /**
     * Cria uma nova Ordem de Serviço a partir de um Agendamento,
     * copiando os dados e serviços e alterando o status do Agendamento.
     * @param agendamento que vamos criar a partir.
     */
    public void criarOSaPartirDeAgendamento(Agendamento agendamento) {


        if (agendamento == null) {
            System.out.println("Agendamento não encontrado.");

        }

        if (agendamento.getStatusAgendamento() == StatusAgendamento.AGENDAMENTO_CANCELADO) {
            System.out.println("O Agendamento ID " + agendamento.getId() + " está cancelado e não pode ser convertido em OS.");
        }

        OrdemDeServico novaOs = new OrdemDeServico(
                agendamento.getCliente(),
                agendamento.getFuncionario(),
                new ArrayList<>(),
                agendamento.getServicos(),
                LocalDateTime.now()

        );
        novaOs.setIdOS(geradorIdOS());
        addOrdemServico(novaOs);

        System.out.println("Ordem de Serviço ID " + novaOs.getIdOS() + " criada com sucesso e Agendamento ID " + agendamento.getId() + " está em andamento.");
    }

    /**
     * Busca uma Ordem de Serviço pelo seu ID.
     *
     * @param id ID da OS.
     * @return O objeto OrdemDeServico encontrado ou null.
     */
    public OrdemDeServico buscarPorId(int id) {
        for (OrdemDeServico os : listaOs) {
            if (os.getIdOS() == id) {
                return os;
            }
        }
        return null;
    }

    /**
     * Imprime todas as Ordens de Serviço (OS) associadas a um determinado cliente.
     *
     * @param c o cliente cujas ordens de serviço devem ser impressas
     */
    public void imprimirOsDeCliente(Cliente c) {
        boolean encontrou = false;

        for (OrdemDeServico os : listaOs) {
            if (os.getCliente() == c) {
                System.out.println(os);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma OS encontrada");
        }
    }





    /**
     * Lista todas as Ordens de Serviço cadastradas no sistema.
     */
    public void listarOS() {
        if (listaOs.isEmpty()) {
            System.out.println("Nenhuma Ordem de Serviço cadastrada.");
        } else {
            System.out.println("----- Lista de TODAS as Ordens de Serviço -----");
            for (OrdemDeServico os : listaOs) {
                System.out.println(os);
            }
        }
    }

    /**
     * Altera o status da Ordem de Serviço para "EM ANDAMENTO".
     *
     * @param os a Ordem de Serviço que terá o status atualizado.
     *           Não deve ser {@code null} e deve possuir serviços atribuídos.
     */
    public void alterarStatusEmAndamento(OrdemDeServico os) {
        os.setStatusOs(TipoStatusOs.ESTADO_ANDAMENTO);

        // Marca as estações como ocupadas
        for (Servico s : os.getServicos()) {
            int idEstacao = s.getEstacaoUsada().getId();
            GerenciadorDeEstacoes.ocuparEstacao(idEstacao);
        }
    }

    /**
     * Altera o status da Ordem de Serviço para "CONCLUÍDO".
     * @param os a Ordem de Serviço que terá seu status definido como concluído.
     *           Não deve ser  null e deve possuir serviços associados.
     */
    public void alterarStatusConcluido(OrdemDeServico os, String observacao) {
        os.setStatusOs(TipoStatusOs.ESTADO_CONCLUIDO);
        os.setObservacoes(observacao);

        for (Servico s : os.getServicos()) {
            int idEstacao = s.getEstacaoUsada().getId();
            GerenciadorDeEstacoes.liberarEstacao(idEstacao);
        }
    }

    /**
     * Lista Ordens de Serviço que estão com status PENDENTE ou EM_ANDAMENTO.
     */
    public void listarOSemAberto() {
        List<OrdemDeServico> osEmAberto = listaOs.stream()
                .filter(os -> os.getStatusOs() == TipoStatusOs.ESTADO_AGUARDANDO ||
                        os.getStatusOs() == TipoStatusOs.ESTADO_ANDAMENTO)
                .collect(Collectors.toList());

        if (osEmAberto.isEmpty()) {
            System.out.println("Nenhuma Ordem de Serviço em aberto (Pendente ou Em Andamento) encontrada.");
        } else {
            System.out.println("----- Ordens de Serviço em Aberto (Pendente ou Em Andamento) -----");
            for (OrdemDeServico os : osEmAberto) {
                System.out.println(os);
            }
        }
    }


    /**
     * Salva a lista atual de Ordens de Serviço no arquivo JSON.
     */
    public void atualizarLista() {
        super.salvarLista(caminho, listaOs);
    }



    /**
     * Remove uma Ordem de Serviço pelo seu ID.
     * * @param id ID da OS a ser removida.
     * @return true se removida, false caso contrário.
     */
    public boolean removerOrdemServico(int id) {
        OrdemDeServico os = buscarPorId(id);
        if (os != null) {
            listaOs.remove(os);
            atualizarLista();
            return true;
        }
        return false;
    }

    /**
     * Calcula e define o valor total da Ordem de Serviço com base
     * na soma dos valores de todos os Produtos e Serviços.
     * * @param Ordem de Serviço para calcular o valor.
     * @return O valor total calculado.
     */
    public double calcularValorTotal(OrdemDeServico os) {
        double totalProdutos = os.getProduto().stream().mapToDouble(Produto::getPreco).sum();
        double totalServicos = os.getServicos().stream().mapToDouble(Servico::getValor).sum();
        os.setValorTotal(totalProdutos + totalServicos);
        return os.getValorTotal();
    }

    public List<OrdemDeServico> getListaOs() {
        return listaOs;
    }

    /**
     * Gera um novo ID único para uma Ordem de Serviço.
     * * @return O próximo ID disponível.
     */
    public int geradorIdOS(){
        if(listaOs.isEmpty()){
            return 1;
        }

        Set<Integer> idExistentes = new HashSet<>();

        for(OrdemDeServico d : listaOs){
            idExistentes.add(d.getIdOS());
        }

        int novoId = 1;

        while(idExistentes.contains(novoId)){
            novoId++;
        }

        return novoId;
    }
}
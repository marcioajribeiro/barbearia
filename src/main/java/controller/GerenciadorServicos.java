/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidades.Servico;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável por gerenciar as operações relacionadas aos serviços.
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe {@link GerenciadorGenerico}
 * @author Márcio Antônio
 * @author Rafael MArtins
 */
public class GerenciadorServicos extends GerenciadorGenerico {

    /** Lista contendo todos os serviços cadastrados. */
    private List<Servico> servicos;

    /** Caminho do arquivo JSON usado para persistência dos serviços. */
    private final String caminho = "Json/JsonServico.json";

    /**
     * Construtor padrão. Carrega a lista de serviços a partir do arquivo JSON.
     */
    public GerenciadorServicos() {
        this.servicos = super.carregarListas(caminho, Servico.class);
    }

    /**
     * Adiciona um novo serviço à lista, gerando automaticamente um ID único.
     *
     * @param novoServico serviço a ser adicionado.
     */
    public void addServico(Servico novoServico) {
        novoServico.setIdServico(geradorId());
        servicos.add(novoServico);
        System.out.println("Servico salvo");
    }

    /**
     * Remove um serviço da lista.
     *
     * @param servico serviço que será removido.
     */
    public void removerServico(Servico servico) {
        servicos.remove(servico);
        System.out.println("Novo serviço cadastrado");
    }

    /**
     * Busca um serviço pelo seu ID.
     *
     * @param id identificador do serviço.
     * @return o serviço correspondente ou null caso não exista.
     */
    public Servico buscarServicoId(int id) {
        for (Servico s : servicos) {
            if (s.getIdServico() == id) {
                return s;
            }
        }
        return null;
    }

    /**
     * Lista todos os serviços cadastrados no sistema.
     * Exibe uma mensagem caso a lista esteja vazia.
     */
    public void listarServicos() {
        if (servicos.isEmpty()) {
            System.out.println("Não há serviços cadastrado");
        } else {
            System.out.println("-----Lista de Serviços-----");
            for (Servico p : servicos) {
                System.out.println(p);
            }
        }
    }

    /**
     * Edita o valor monetário de um serviço específico.
     *
     * @param s serviço a ter o valor alterado.
     * @param novoValor novo valor do serviço.
     */
    public void editarValorServico(Servico s, double novoValor) {
        s.setValor(novoValor);
    }

    /**
     * Edita a duração (em minutos) de um serviço.
     *
     * @param s serviço que será atualizado.
     * @param novaDuracao nova duração em minutos.
     */
    public void editarDuracaoServico(Servico s, int novaDuracao) {
        s.setDuracaoMin(novaDuracao);
    }

    /**
     * Salva a lista de serviços no arquivo JSON.
     */
    public void salvarServicos() {
        super.salvarLista(caminho, servicos);
    }

    /**
     * Gera um novo ID único para um serviço.
     * Os IDs são sequenciais começando em 1 e não podem se repetir.
     *
     * @return novo ID disponível.
     */
    public int geradorId() {
        if (servicos.isEmpty()) {
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();
        for (Servico s : servicos) {
            idsExistentes.add(s.getIdServico());
        }

        int novoId = 1;
        while (idsExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}

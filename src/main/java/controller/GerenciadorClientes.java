/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import comparator.ClienteCpfComparator;
import comparator.ClienteNomeComparator;
import entidades.Cliente;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *Classe responsável por gerenciar operações relacionadas aos clientes,
 *Herda funcionalidade genérica de carregamento e salvamento de listas
 *da classe {@link GerenciadorGenerico}
 * @author Márcio Antonio
 * @author Rafael Martins
 */
public class GerenciadorClientes extends GerenciadorGenerico {

    /** Lista de clientes cadastrados no sistema. */
    private List<Cliente> clientes;

    /** Caminho do arquivo JSON utilizado para salvar e carregar os clientes. */
    private final String caminho = "Json/JsonCliente.json";

    /**
     * Construtor que inicializa o gerenciador, carregando os clientes do arquivo JSON.
     */
    public GerenciadorClientes() {
        this.clientes = super.carregarListas(caminho, Cliente.class);
    }

    /**
     * Adiciona um novo cliente à lista, gerando um ID automaticamente.
     *
     * @param cliente o cliente a ser cadastrado
     */
    public void addCliente(Cliente cliente) {
        cliente.setIdCliente(geradorIdCliente());
        clientes.add(cliente);
        System.out.println("Cliente salvo");
    }

    /**
     * Atualiza o arquivo JSON, salvando a lista atual de clientes.
     */
    public void atualizarLista() {
        super.salvarLista(caminho, clientes);
    }

    /**
     * Busca um cliente pelo seu ID.
     *
     * @param id o ID do cliente procurado
     * @return o cliente correspondente ou null se não for encontrado
     */
    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == id) {
                return cliente;
            }
        }
        System.out.println("Cliente com ID " + id + " não encontrado.");
        return null;
    }

    /**
     * Retorna a lista de todos os clientes cadastrados.
     *
     * @return lista de clientes
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Exibe no console todos os clientes cadastrados.
     * Caso a lista esteja vazia, informa que não há clientes registrados.
     */
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados");
        } else {
            System.out.println("-----Lista de Clientes-----");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    /**
     * Busca um cliente pelo CPF.
     *
     * @param cpf o CPF do cliente
     * @return o cliente correspondente ou null caso não seja encontrado
     */
    public Cliente buscarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        System.out.println("CPF: " + cpf + " não encontrado");
        return null;
    }

    /**
     * Remove um cliente da lista.
     *
     * @param cliente o cliente a ser removido
     */
    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
        System.out.println("Cliente removido");
    }


    public void alterarTudoCliente(Cliente c,String novoNome,String novoCpf,String novoEmail, String novoEndereco, String novoTelefone) {
        c.setNome(novoNome);
        c.setCpf(novoCpf);
        c.setEmail(novoEmail);
        c.setEndereco(novoEndereco);
        c.setTelefone(novoTelefone);

    }

    /**
     * Altera o nome de um cliente.
     *
     * @param novoNome o novo nome
     * @param c        o cliente a ser atualizado
     */
    public void alterarNomeCliente(String novoNome, Cliente c) {
        c.setNome(novoNome);
    }

    /**
     * Altera o endereço de um cliente.
     *
     * @param novoEndereco o novo endereço
     * @param c            o cliente a ser atualizado
     */
    public void alterarEnderecoCliente(String novoEndereco, Cliente c) {
        c.setEndereco(novoEndereco);
    }

    /**
     * Altera o telefone de um cliente.
     *
     * @param novoTelefone o novo telefone
     * @param c            o cliente a ser atualizado
     */
    public void alterarTelefoneCliente(String novoTelefone, Cliente c) {
        c.setTelefone(novoTelefone);
    }

    /**
     * Altera o e-mail de um cliente.
     *
     * @param novoEmail o novo e-mail
     * @param c         o cliente a ser atualizado
     */
    public void alterarEmailCliente(String novoEmail, Cliente c) {
        c.setEmail(novoEmail);
    }

    /**
     * Altera o CPF de um cliente.
     *
     * @param Cpf o novo CPF
     * @param c   o cliente a ser atualizado
     */
    public void alterarCpfCliente(String Cpf, Cliente c) {
        c.setCpf(Cpf);
    }

    /**
     * Organiza os agendamentos em ordem dos mais recentes para os mais antigos.
     * Utiliza o ClienteNomeComparator.
     */
    public void organizarPorOrdemAlfabetica() {
        Collections.sort(clientes, new ClienteNomeComparator());
    }
    /**
     * Organiza os agendamentos em ordem dos mais recentes para os mais antigos.
     * Utiliza o ClienteCpfComparator.
     */
    public void organizarPorCpf() {
        Collections.sort(clientes, new ClienteCpfComparator());
    }


    /**
     * Gera um novo ID único para um cliente, baseado nos IDs já existentes.
     *
     * @return o novo ID gerado
     */
    public int geradorIdCliente() {
        if (clientes.isEmpty()) {
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();
        for (Cliente cliente : clientes) {
            idsExistentes.add(cliente.getIdCliente());
        }

        int novoId = 1;
        while (idsExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}
    


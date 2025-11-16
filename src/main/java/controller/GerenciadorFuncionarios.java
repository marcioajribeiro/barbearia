/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidades.Funcionario;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Classe responsável por gerenciar operações relacionadas aos funcionários,
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe {@link GerenciadorGenerico}
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorFuncionarios extends GerenciadorGenerico {

    /** Lista contendo todos os funcionários cadastrados. */
    public List<Funcionario> funcionarios;

    /** Caminho do arquivo JSON usado para salvar e carregar os dados. */
    public final String caminho = "Json/JsonFuncionario.json";

    /**
     * Construtor que inicializa o gerenciador carregando os funcionários
     * armazenados no arquivo JSON.
     */
    public GerenciadorFuncionarios() {
        this.funcionarios = super.carregarListas(caminho, Funcionario.class);
    }

    /**
     * Obtém a lista completa de funcionários.
     *
     * @return lista de funcionários cadastrados
     */
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Adiciona um novo funcionário, gerando automaticamente seu ID.
     *
     * @param f funcionário a ser adicionado
     */
    public void addFuncionario(Funcionario f) {
        f.setIdFuncionario(geradorIdFuncionario());
        funcionarios.add(f);
    }

    /**
     * Busca um funcionário pelo seu ID.
     *
     * @param id ID do funcionário
     * @return funcionário encontrado ou null caso não exista
     */
    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getIdFuncionario() == id) {
                return funcionario;
            }
        }
        System.out.println("Funcionário com ID " + id + " não encontrado.");
        return null;
    }

    /**
     * Lista todos os funcionários cadastrados no sistema.
     * Exibe mensagem caso a lista esteja vazia.
     */
    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há Funcionários cadastrados");
        } else {
            System.out.println("-----Lista de Funcionários-----");
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }
        }
    }

    /**
     * Busca um funcionário pelo CPF.
     *
     * @param cpf CPF do funcionário
     * @return funcionário encontrado ou null caso não exista
     */
    public Funcionario buscarFuncionarioCpf(String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }

        System.out.println("CPF: " + cpf + " não encontrado");
        return null;
    }

    /**
     * Busca um funcionário pelo ID.
     *
     * @param id ID do funcionário
     * @return funcionário encontrado ou null caso não exista
     */
    public Funcionario buscarFuncionarioId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getIdFuncionario() == id) {
                return f;
            }
        }

        System.out.println("Id: " + id + " não encontrado");
        return null;
    }

    /**
     * Remove um funcionário do sistema.
     *
     * @param f funcionário a ser removido
     */
    public void removerFuncionarioCpf(Funcionario f) {
        if (f != null) {
            funcionarios.remove(f);
            System.out.println("Funcionario removido com sucesso");
            super.salvarLista(caminho, funcionarios);
        } else {
            System.out.println("Funcionario não encontrado");
        }
    }

    /**
     * Altera o nome de um funcionário.
     *
     * @param novoNome novo nome
     * @param f funcionário a ser alterado
     */
    public void alterarNomeFuncionario(String novoNome, Funcionario f) {
        f.setNome(novoNome);
    }

    /**
     * Altera o endereço de um funcionário.
     *
     * @param novoEndereco novo endereço
     * @param f funcionário a ser alterado
     */
    public void alterarEnderecoFuncionario(String novoEndereco, Funcionario f) {
        f.setEndereco(novoEndereco);
    }

    /**
     * Altera o telefone de um funcionário.
     *
     * @param novoTelefone novo telefone
     * @param f funcionário a ser alterado
     */
    public void alterarTelefoneFuncionario(String novoTelefone, Funcionario f) {
        f.setTelefone(novoTelefone);
    }

    /**
     * Altera a senha de um funcionário.
     *
     * @param novaSenha nova senha
     * @param f funcionário a ser alterado
     */
    public void alterarSenhaFuncionario(String novaSenha, Funcionario f) {
        f.setSenha(novaSenha);
    }

    /**
     * Altera o cargo de um funcionário.
     *
     * @param novoCargo novo cargo
     * @param f funcionário a ser alterado
     */
    public void alterarCargoFuncionario(String novoCargo, Funcionario f) {
        f.setCargo(novoCargo);
    }

    /**
     * Altera o CPF de um funcionário.
     *
     * @param novoCpf novo CPF
     * @param f funcionário a ser alterado
     */
    public void alterarCpfFuncionario(String novoCpf, Funcionario f) {
        f.setCpf(novoCpf);
    }

    /**
     * Atualiza o arquivo JSON com os dados atuais dos funcionários.
     */
    public void atualizarFuncionario() {
        super.salvarLista(caminho, funcionarios);
    }

    /**
     * Gera automaticamente um ID único para novos funcionários.
     *
     * @return novo ID disponível
     */
    public int geradorIdFuncionario() {
        if (funcionarios.isEmpty()) {
            return 1;
        }

        Set<Integer> idsExistentes = new HashSet<>();
        for (Funcionario funcionario : funcionarios) {
            idsExistentes.add(funcionario.getIdFuncionario());
        }

        int novoId = 1;

        while (idsExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}

    
    
    
    

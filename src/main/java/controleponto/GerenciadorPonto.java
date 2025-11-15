/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleponto;

import controller.GerenciadorGenerico;
import entidades.Funcionario;
import java.util.List;

/**
 * Gerencia os registros de ponto dos funcionários.
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe {@link GerenciadorGenerico}
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorPonto extends GerenciadorGenerico {

    /** Lista contendo todos os registros de ponto realizados. */
    private final List<RegistroPonto> pontos;

    /** Caminho do arquivo JSON onde os registros de ponto são armazenados. */
    private final String caminho = "Json/JsonPonto.json";

    /**
     * Construtor responsável por carregar os registros de ponto do arquivo JSON.
     */
    public GerenciadorPonto() {
        this.pontos = super.carregarListas(caminho, RegistroPonto.class);
    }

    /**
     * Registra a entrada de um funcionário, desde que ele não possua um ponto aberto.
     *
     * @param f funcionário que está registrando entrada.
     * @return o novo RegistroPonto criado, ou null caso já exista
     *         um ponto em aberto para o funcionário.
     */
    public RegistroPonto baterEntrada(Funcionario f) {
        boolean pontoAberto = false;

        for (RegistroPonto ponto : pontos) {
            if (ponto.getFuncionario().equals(f) && ponto.getSaida() == null) {
                pontoAberto = true;
                break;
            }
        }

        if (pontoAberto) {
            System.err.println("ERRO: " + f.getNome() + " já está com o ponto em aberto");
            return null;
        } else {
            RegistroPonto novoPonto = new RegistroPonto(f);
            this.pontos.add(novoPonto);
            System.out.println("Ponto Registrado.");
            return novoPonto;
        }
    }

    /**
     * Registra a saída de um funcionário caso exista um ponto aberto (sem horário de saída).
     *
     * @param f funcionário que está registrando saída.
     * @return o registro de ponto atualizado com a saída, ou null caso não haja ponto aberto.
     */
    public RegistroPonto baterSaida(Funcionario f) {
        RegistroPonto registroAberto = null;

        for (RegistroPonto ponto : pontos) {
            if (ponto.getFuncionario().equals(f) && ponto.getSaida() == null) {
                registroAberto = ponto;
                break;
            }
        }

        if (registroAberto != null) {
            registroAberto.pontoDeSaida();
            super.salvarLista(caminho, pontos);
            return registroAberto;
        } else {
            return null;
        }
    }

    /**
     * Força a atualização e salvamento do arquivo JSON contendo os registros de ponto.
     */
    public void atulizarPontos() {
        super.salvarLista(caminho, pontos);
    }

    /**
     * Lista todos os registros de ponto cadastrados no sistema.
     * Caso não haja registros, exibe uma mensagem informando isso.
     */
    public void listarPontos() {
        if (pontos.isEmpty()) {
            System.out.println("Não há pontos registrados");
        } else {
            System.out.println("-----Registro de Pontos-----");
            pontos.forEach(System.out::println);
        }
    }
}
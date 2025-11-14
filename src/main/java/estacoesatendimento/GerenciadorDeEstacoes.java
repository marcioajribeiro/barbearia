/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacoesatendimento;


/**
 * Classe responsável por gerenciar as estações de atendimento da barbearia.
 * Mantém um conjunto fixo de três estações e fornece métodos utilitários
 * para consulta, ocupação, liberação e listagem das estações.
 */
public class GerenciadorDeEstacoes {

    /** Conjunto fixo de estações de atendimento disponíveis na barbearia. */
    private static final EstacaoAtendimento[] estacoes = new EstacaoAtendimento[3];

    /**Bloco estático responsável por inicializar as estações.*/
    static {
        estacoes[0] = new EstacaoAtendimento(1, "Cadeira 1", TipoDeEstacao.LAVAGEM_E_SECADOR);
        estacoes[1] = new EstacaoAtendimento(2, "Cadeira 2", TipoDeEstacao.ATIVIDADES_CORRIQUEIRAS);
        estacoes[2] = new EstacaoAtendimento(3, "Cadeira 3", TipoDeEstacao.ATIVIDADES_CORRIQUEIRAS);
    }

    /**
     * Retorna a primeira estação disponível do tipo informado.
     *
     * @param tipo o tipo de estação desejado.
     * @return a estação disponível, ou {@code null} se não houver nenhuma livre.
     */
    public static EstacaoAtendimento estacaoDisponivel(TipoDeEstacao tipo) {
        for (EstacaoAtendimento e : estacoes) {
            if (!e.isOcupado() && e.getTipoDaEstacao() == tipo) {
                return e;
            }
        }
        return null;
    }

    /**
     * Exibe no console todas as estações cadastradas com suas informações.
     */
    public static void listarEstacoes() {
        System.out.println("=== Estações de Atendimento ===");
        for (EstacaoAtendimento e : estacoes) {
            System.out.println(e);
        }
    }

    /**
     * Marca uma estação como ocupada a partir do seu ID.
     * Caso a estação seja encontrada, a operação é realizada e uma mensagem é exibida.
     *
     * @param id o identificador da estação a ser ocupada.
     */
    public static void ocuparEstacao(int id) {
        for (EstacaoAtendimento e : estacoes) {
            if (e.getId() == id) {
                e.ocupar();
                System.out.println("Estação " + id + " ocupada.");
                return;
            }
        }
        System.out.println("Estação não encontrada.");
    }

    /**
     * Libera uma estação previamente ocupada, a partir do seu ID.
     * Caso a estação seja encontrada, ela é marcada como livre.
     *
     * @param id o identificador da estação a ser liberada.
     */
    public static void liberarEstacao(int id) {
        for (EstacaoAtendimento e : estacoes) {
            if (e.getId() == id) {
                e.liberar();
                System.out.println("Estação " + id + " liberada.");
                return;
            }
        }
        System.out.println("Estação não encontrada.");
    }

    /**
     * Realiza uma busca por uma estação específica com base no seu ID.
     *
     * @param id o identificador da estação procurada.
     * @return a estação encontrada, ou {@code null} se não existir uma estação com este ID.
     */
    public static EstacaoAtendimento buscarEstação(int id) {
        for (EstacaoAtendimento e : estacoes) {
            if (e.getId() == id) {
                return e;
            }
        }
        System.out.println("Estação com ID " + id + " não encontrada.");
        return null;
    }

    /**
     * Retorna o vetor contendo todas as estações cadastradas.
     *
     * @return array de {EstacaoAtendimento} contendo todas as estações.
     */
    public static EstacaoAtendimento[] getEstacoes() {
        return estacoes;
    }
}

       
    


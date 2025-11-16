package interpreter;

import agendamento.Agendamento;
import interpreter.ExpressaoBusca;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Expressão terminal responsável por filtrar agendamentos com base
 * no nome de um funcionário (barbeiro).
 *

 */
public class BuscaPorFuncionario implements ExpressaoBusca {

    /** Nome (ou parte do nome) do barbeiro utilizado como critério de busca. */
    private final String nomeBarbeiro;

    /**
     * Constrói uma expressão de busca por funcionário.
     * O nome informado é convertido para letras minúsculas para permitir
     * comparações sem distinção entre maiúsculas e minúsculas.
     *
     * @param nomeBarbeiro nome ou fragmento do nome do barbeiro a ser buscado
     */
    public BuscaPorFuncionario(String nomeBarbeiro) {
        this.nomeBarbeiro = nomeBarbeiro.toLowerCase();
    }

    /**
     * Filtra a lista de agendamentos retornando apenas aqueles cujo
     * funcionário associado contém o nome informado.
     *
     * @param agendamentos lista completa de agendamentos a ser analisada
     * @return lista filtrada contendo apenas agendamentos cujo barbeiro
     *         contém o nome buscado (case-insensitive)
     */
    @Override
    public List<Agendamento> interpretar(List<Agendamento> agendamentos) {
        return agendamentos.stream()
                .filter(a -> a.getFuncionario().getNome().toLowerCase().contains(nomeBarbeiro))
                .collect(Collectors.toList());
    }
}

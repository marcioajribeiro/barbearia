package interpreter;

import agendamento.Agendamento;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Expressão Terminal: Busca agendamentos por nome de funcionário (Barbeiro).
 */
public class BuscaPorFuncionario implements ExpressaoBusca {

    private final String nomeBarbeiro;

    public BuscaPorFuncionario(String nomeBarbeiro) {
        this.nomeBarbeiro = nomeBarbeiro.toLowerCase();
    }

    @Override
    public List<Agendamento> interpretar(List<Agendamento> agendamentos) {
        return agendamentos.stream()
                .filter(a -> a.getFuncionario().getNome().toLowerCase().contains(nomeBarbeiro))
                .collect(Collectors.toList());
    }
}
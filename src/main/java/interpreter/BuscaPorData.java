package interpreter;

import agendamento.Agendamento;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Expressão Terminal: Busca agendamentos que ocorrem em uma data específica (dia, mês e ano).
 */
public class BuscaPorData implements ExpressaoBusca {

    private final LocalDate dataDesejada;

    /**
     * Constrói a expressão de busca com a data exata desejada.
     * @param dataDesejada A data que os agendamentos devem ocorrer.
     */
    public BuscaPorData(LocalDate dataDesejada) {
        this.dataDesejada = dataDesejada;
    }

    @Override
    public List<Agendamento> interpretar(List<Agendamento> agendamentos) {
        return agendamentos.stream()
                .filter(a -> a.getDataHora().toLocalDate().isEqual(dataDesejada))
                .collect(Collectors.toList());
    }
}
package interpreter;

import agendamento.Agendamento;
import java.util.List;

/**
 * Interface que representa uma expressão do padrão Interpreter para busca e filtragem.
 * O método interpretar deve filtrar uma lista de Agendamentos.
 */
public interface ExpressaoBusca {
    /**
     * Interpreta a regra de busca e filtra a lista de agendamentos.
     *
     * @param agendamentos Lista completa de agendamentos.
     * @return Uma nova lista contendo apenas os agendamentos que satisfazem a regra.
     */
    List<Agendamento> interpretar(List<Agendamento> agendamentos);
}
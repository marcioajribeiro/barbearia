package interpreter;

import agendamento.Agendamento;
import java.util.List;

/**
 * Expressão Não-Terminal que representa uma operação lógica "E" (AND)
 * entre duas expressões de busca.
 *
 */
public class EBusca implements ExpressaoBusca {

    /** Primeira expressão de busca a ser combinada. */
    private final ExpressaoBusca expressao1;

    /** Segunda expressão de busca a ser combinada. */
    private final ExpressaoBusca expressao2;

    /**
     * Constrói uma expressão composta usando lógica "E" (AND).
     *
     * @param expressao1 primeira expressão a ser considerada
     * @param expressao2 segunda expressão a ser considerada
     */
    public EBusca(ExpressaoBusca expressao1, ExpressaoBusca expressao2) {
        this.expressao1 = expressao1;
        this.expressao2 = expressao2;
    }

    /**
     * Interpreta a lista de agendamentos aplicando as duas expressões
     * e retornando apenas os elementos presentes em ambas.
     * @param agendamentos lista completa de agendamentos a ser filtrada
     * @return lista contendo somente os agendamentos que atendem às
     *         duas condições simultaneamente
     */
    @Override
    public List<Agendamento> interpretar(List<Agendamento> agendamentos) {


        List<Agendamento> resultado1 = expressao1.interpretar(agendamentos);


        List<Agendamento> resultado2 = expressao2.interpretar(agendamentos);

        resultado1.retainAll(resultado2);

        return resultado1;
    }
}
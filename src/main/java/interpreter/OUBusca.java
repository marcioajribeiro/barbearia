package interpreter;

import agendamento.Agendamento;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Expressão Não-Terminal: Combina duas expressões de busca com a lógica "OU" (OR).
 * O resultado final é a união dos resultados das duas expressões.
 */
public class OUBusca implements ExpressaoBusca {

    private final ExpressaoBusca expressao1;
    private final ExpressaoBusca expressao2;

    public OUBusca(ExpressaoBusca expressao1, ExpressaoBusca expressao2) {
        this.expressao1 = expressao1;
        this.expressao2 = expressao2;
    }

    @Override
    public List<Agendamento> interpretar(List<Agendamento> agendamentos) {
        List<Agendamento> resultado1 = expressao1.interpretar(agendamentos);


        List<Agendamento> resultado2 = expressao2.interpretar(agendamentos);


        Set<Agendamento> uniao = new HashSet<>(resultado1);
        uniao.addAll(resultado2);

        return uniao.stream().collect(Collectors.toList());
    }
}
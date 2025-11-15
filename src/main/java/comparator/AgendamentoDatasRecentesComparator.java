package comparator;

import agendamento.Agendamento;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
/**
 * Comparador de agendamentos baseado na proximidade da data em relação à data atual.
 *
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class AgendamentoDatasRecentesComparator implements Comparator<Agendamento> {
    /**
     * Compara dois agendamentos com base próximo de suas datas em relação ao dia atual.
     *
     * @param a1 o primeiro agendamento a ser comparado
     * @param a2 o segundo agendamento a ser comparado
     * @return um valor negativo se a1 estiver mais próximo da data atual que a2,
     *         zero se estiverem igualmente próximos,
     *         ou um valor positivo se a1 estiver mais distante da data atual que a2
     */
    @Override
    public int compare(Agendamento a1, Agendamento a2) {
        if (a1 == null && a2 == null) return 0;
        if (a1 == null) return 1;
        if (a2 == null) return -1;

        LocalDate hoje = LocalDate.now();
        LocalDate data1 = a1.getDataHora().toLocalDate();
        LocalDate data2 = a2.getDataHora().toLocalDate();

        long diff1 = ChronoUnit.DAYS.between(hoje, data1);
        long diff2 = ChronoUnit.DAYS.between(hoje, data2);

        if (diff1 < diff2) {
            return -1;
        } else if (diff1 > diff2) {
            return 1;
        } else {
            return 0;
        }
    }
}

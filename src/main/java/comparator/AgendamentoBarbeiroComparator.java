package comparator;

import agendamento.Agendamento;
import java.util.Comparator;
/**
 * Comparador de agendamentos baseado no nome do funcionário(barbeiro) em ordem alfabetica associado ao agendamento.
 *
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class AgendamentoBarbeiroComparator implements Comparator<Agendamento> {

    /**
     * Compara dois agendamentos com base no nome do funcionário associado.
     *
     * @param a1 o primeiro agendamento a ser comparado
     * @param a2 o segundo agendamento a ser comparado
     * @return um valor negativo se a1 deve vir antes de a2,
     *         zero se forem considerados iguais,
     *         ou um valor positivo se a1 deve vir depois de a2
     */
    @Override
    public int compare(Agendamento a1, Agendamento a2) {

        if (a1 == null && a2 == null) {
            return 0;
        }
        if (a1 == null) {
            return 1;
        }
        if (a2 == null) {
            return -1;
        }

        String n1 = a1.getFuncionario().getNome().toLowerCase();
        String n2 = a2.getFuncionario().getNome().toLowerCase();

        int tamanho = Math.min(n1.length(), n2.length());

        for (int i = 0; i < tamanho; i++) {
            char ch1 = n1.charAt(i);
            char ch2 = n2.charAt(i);

            if (ch1 != ch2) {
                return ch1 - ch2;
            }
        }

        return n1.length() - n2.length();
    }

}

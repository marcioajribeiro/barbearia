package comparator;


import entidades.Cliente;
import java.util.Comparator;

/**
 * Comparador de clientes baseado no CPF.
 *
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class ClienteCpfComparator implements Comparator<Cliente> {
    /**
     * Compara dois clientes com base no CPF.
     *
     * @param c1 o primeiro cliente a ser comparado
     * @param c2 o segundo cliente a ser comparado
     * @return um valor negativo se c1 deve vir antes de c2,
     *         zero se forem considerados iguais,
     *         ou um valor positivo se c1 deve vir depois de c2
     */
    @Override
    public int compare(Cliente c1, Cliente c2) {
        if (c1 == null && c2 == null) {
            return 0;
        }
        if (c1 == null) {
            return 1;
        }
        if (c2 == null) {
            return -1;
        }


        String n1 = c1.getCpf();
        String n2 = c2.getCpf();


        int tamanho= Math.min(n1.length(), n2.length());

        for (int i = 0; i < tamanho; i++) {
            char ch1 = n1.charAt(i);
            char ch2 = n2.charAt(i);

            if (ch1 != ch2) {
                return ch1 - ch2;
            }
        }


        return 0;
    }
}

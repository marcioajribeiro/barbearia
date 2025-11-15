package financeiro;

import controller.GerenciadorGenerico;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável por gerenciar as Notas Fiscais do sistema.
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe GerenciadorGenerico.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorNotaFiscal extends GerenciadorGenerico {

    /** Lista de notas fiscais registradas no sistema. */
    private List<NotaFiscal> notasFiscais;

    /** Caminho do arquivo JSON onde as notas fiscais são persistidas. */
    private final String caminho = "Json/JsonNotaFiscal.json";

    /**
     * Construtor padrão: carrega as notas fiscais armazenadas em JSON.
     */
    public GerenciadorNotaFiscal() {
        this.notasFiscais = super.carregarListas(caminho, NotaFiscal.class);
    }

    /**
     * Adiciona uma nova nota fiscal à lista e gera seu ID.
     * @param notaFiscal A nota fiscal a ser registrada.
     */
    public void addNotaFiscal(NotaFiscal notaFiscal) {
        notaFiscal.setIdNotaFiscal(geradorId());
        notasFiscais.add(notaFiscal);
        System.out.println("Nota Fiscal ID " + notaFiscal.getIdNotaFiscal() + " emitida e salva.");
    }

    /**
     * Salva todas as notas fiscais no arquivo JSON.
     */
    public void salvarNotasFiscais() {
        super.salvarLista(caminho, notasFiscais);
    }

    /**
     * Lista todas as notas fiscais cadastradas no sistema.
     */
    public void listarNotasFiscais() {
        if (notasFiscais.isEmpty()) {
            System.out.println("Nenhuma Nota Fiscal registrada.");
        } else {
            System.out.println("----- Lista de Notas Fiscais -----");
            for (NotaFiscal nf : notasFiscais) {
                System.out.println(nf);
            }
        }
    }

    /**
     * Gera um ID único para cada nova nota fiscal registrada.
     *
     * <p>O ID sempre será o menor número inteiro positivo não utilizado.</p>
     *
     * @return novo ID único
     */
    public int geradorId() {
        if (notasFiscais.isEmpty()) {
            return 1;
        }

        Set<Integer> idExistentes = new HashSet<>();

        for (NotaFiscal nf : notasFiscais) {
            idExistentes.add(nf.getIdNotaFiscal());
        }

        int novoId = 1;

        while (idExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}
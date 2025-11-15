/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import controller.GerenciadorGenerico;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável por gerenciar todas as despesas do sistema.
 * Herda funcionalidade genérica de carregamento e salvamento de listas
 * da classe {@link GerenciadorGenerico}
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class GerenciadorDespesas extends GerenciadorGenerico {

    /** Lista de despesas registradas no sistema. */
    private List<Despesa> despesas;

    /** Caminho do arquivo JSON onde as despesas são persistidas. */
    private final String caminho = "Json/JsonDespesa.json";

    /**
     * Construtor padrão: carrega as despesas armazenadas em JSON.
     */
    public GerenciadorDespesas() {
        this.despesas = super.carregarListas(caminho, Despesa.class);
    }

    /**
     * Registra uma nova despesa de material utilizado ou comprado.
     *
     * @param materialComprado descrição do material comprado
     * @param valor valor gasto no material
     */
    public void registrarDespesaMaterial(String materialComprado, double valor) {
        Despesa novaDespesa = new Despesa(materialComprado, valor, TipoDespesa.DESPESA_MATERIAL, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
    }

    /**
     * Registra o pagamento de salários dos funcionários.
     *
     * @param todosSalarios valor total pago em salários
     */
    public void registrarDespesaSalario(double todosSalarios) {
        Despesa novaDespesa = new Despesa("Pagamento dos salários", todosSalarios, TipoDespesa.DESPESA_SALARIO, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
    }

    /**
     * Registra despesa referente ao pagamento de serviços de limpeza.
     *
     * @param valorLimpeza valor gasto com limpeza
     */
    public void registrarDespesaLimpeza(double valorLimpeza) {
        Despesa novaDespesa = new Despesa("Pagamento para limpeza", valorLimpeza, TipoDespesa.DESPESA_LIMPEZA, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
    }

    /**
     * Registra despesas relacionadas a brindes, cortesias ou presentes.
     *
     * @param cortesia descrição da cortesia oferecida
     * @param valorCortesia valor gasto na cortesia
     */
    public void registrarDespesaCortesias(String cortesia, double valorCortesia) {
        Despesa novaDespesa = new Despesa(cortesia, valorCortesia, TipoDespesa.DESPESA_CORTESIAS, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
    }

    /**
     * Busca uma despesa pelo seu identificador.
     *
     * @param id ID da despesa procurada
     * @return a despesa correspondente ou {@code null} se não encontrada
     */
    public Despesa buscarVendaPorId(int id) {
        for (Despesa d : despesas) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    /**
     * Remove uma despesa da lista.
     *
     * @param d despesa a ser removida
     */
    public void removerDespesa(Despesa d) {
        despesas.remove(d);
        System.out.println("Despesa removida");
    }

    /**
     * Salva todas as despesas no arquivo JSON.
     */
    public void salvarDespesas() {
        super.salvarLista(caminho, despesas);
    }

    /**
     * Calcula o total de despesas de um mês específico de determinado ano.
     *
     * @param ano ano desejado
     * @param mes mês desejado (1 a 12)
     * @return soma dos valores das despesas encontradas
     */
    public double calcularDespesasAnoMes(int ano, int mes) {
        double total = 0.0;

        for (Despesa d : despesas) {
            if (d.getData().getYear() == ano && d.getData().getMonthValue() == mes) {
                total += d.getValor();
            }
        }

        return total;
    }

    /**
     * @return lista completa de despesas cadastradas
     */
    public List<Despesa> getDespesas() {
        return despesas;
    }

    /**
     * Gera um ID único para cada nova despesa registrada.
     *
     * <p>O ID sempre será o menor número inteiro positivo não utilizado.</p>
     *
     * @return novo ID único
     */
    public int geradorId() {
        if (despesas.isEmpty()) {
            return 1;
        }

        Set<Integer> idExistentes = new HashSet<>();

        for (Despesa d : despesas) {
            idExistentes.add(d.getId());
        }

        int novoId = 1;

        while (idExistentes.contains(novoId)) {
            novoId++;
        }

        return novoId;
    }
}

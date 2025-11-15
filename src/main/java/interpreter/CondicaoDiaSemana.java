/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import financeiro.Venda;
import java.time.DayOfWeek;

/**
 *
 *  Representa uma condição de desconto baseada no dia da semana,
 * seguindo o padrão de projeto Interpreter.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class CondicaoDiaSemana implements ExpressaoDesconto {

    /**
     * Dia da semana em que o desconto é aplicado.
     * Por padrão, a quarta-feira DayOfWeek#WEDNESDAY.
     */
    private final DayOfWeek diaDesconto = DayOfWeek.WEDNESDAY;

    /**
     * Expressão de desconto a ser aplicada caso a condição (dia da semana)
     * seja satisfeita.
     */
    private final ExpressaoDesconto expressaoDesconto;

    /**
     * Construtor que define qual expressão de desconto será aplicada
     * quando a venda ocorrer no dia configurado para desconto.
     *
     * @param expressaoSeVerdadeira expressão de desconto aplicada se for o dia certo
     */
    public CondicaoDiaSemana(ExpressaoDesconto expressaoSeVerdadeira) {
        this.expressaoDesconto = expressaoSeVerdadeira;
    }

    /**
     * Interpreta a regra verificando se a data da venda corresponde ao dia
     * configurado para desconto.
     *
     * @param venda objeto Venda usado para avaliar a condição
     * @return valor com desconto aplicado ou valor bruto, dependendo do dia
     */
    @Override
    public double interpretar(Venda venda) {
        if (venda.getDataHora().getDayOfWeek() == diaDesconto) {
            return expressaoDesconto.interpretar(venda);
        }
        return new ValorServicoBruto().interpretar(venda);
    }
}

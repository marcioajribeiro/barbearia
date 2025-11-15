/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import financeiro.Venda;

/**
 *
 * @author MARCIO JUNIOR
 */
public class CondicaoValorMinimo implements ExpressaoDesconto {

    private final double ValorMinimo = 100.0;
    private final ExpressaoDesconto expressaoDesconto;

    /**
     * Constrói a condição, recebendo a expressão de desconto que será aplicada
     * caso o valor mínimo seja atingido.
     * @param expressaoDesconto expressão que será executada quando a condição for satisfeita
     */
    public CondicaoValorMinimo(ExpressaoDesconto expressaoDesconto) {
        this.expressaoDesconto = expressaoDesconto;
    }

    /**
     * Interpreta a regra: calcula o valor bruto da venda e verifica se ele é
     * maior que o valor mínimo definido. Se for, executa a expressão de
     * desconto interna. Se não for, retorna o valor bruto sem desconto.
     *
     * @param venda a venda cujos serviços serão avaliados
     * @return o valor resultante após aplicar (ou não) o desconto
     */
    @Override
    public double interpretar(Venda venda) {
        double valorBruto = new ValorServicoBruto().interpretar(venda);

        if (valorBruto > ValorMinimo) {
            return expressaoDesconto.interpretar(venda);
        }

        return valorBruto;
    }
}
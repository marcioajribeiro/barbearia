/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import financeiro.Venda;

/**
 Representa uma expressão do padrão Interpreter responsável por aplicar
 * um desconto percentual sobre o valor bruto dos serviços da venda
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class AplicarDescontoPercentual implements ExpressaoDesconto {

    /**
     * Percentual de desconto aplicado (20% = 0.2).
     */
    private final double desconto = 0.2;

    /**
     * Aplica o desconto percentual ao valor bruto dos serviços.
     *
     * @param venda objeto Venda usado para obter o valor dos serviços
     * @return valor final após aplicar o desconto percentual
     */
    @Override
    public double interpretar(Venda venda) {
        double valorBruto = new ValorServicoBruto().interpretar(venda);
        return valorBruto * (1 - desconto);
    }
}
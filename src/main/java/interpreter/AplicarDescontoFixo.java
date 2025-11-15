/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import financeiro.Venda;

/**
 * Implementa a expressão do padrão Interpreter responsável por aplicar
 * um desconto fixo sobre o valor bruto dos serviços de uma venda.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class AplicarDescontoFixo implements ExpressaoDesconto {

    /**
     * Valor fixo descontado do total de serviços.
     */
    private final double descontoFixo = 15;

    /**
     * Calcula o valor final dos serviços após aplicar o desconto fixo.
     *
     * @param venda objeto {@link Venda} usado como base para o cálculo do valor bruto
     * @return o valor dos serviços após a subtração do desconto fixo
     */
    @Override
    public double interpretar(Venda venda) {
        double valorBruto = new ValorServicoBruto().interpretar(venda);
        return valorBruto - descontoFixo;
    }
}

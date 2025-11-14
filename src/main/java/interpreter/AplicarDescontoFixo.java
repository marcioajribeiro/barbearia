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
public class AplicarDescontoFixo implements ExpressaoDesconto {
    private final double descontoFixo = 15;

    @Override
    public double interpretar(Venda venda) {
        double valorBruto = new ValorServicoBruto().interpretar(venda);
        

        return valorBruto - descontoFixo;
    }
}

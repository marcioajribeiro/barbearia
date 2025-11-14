/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter;

import financeiro.Venda;

/**
 *
 * @author MARCIO JUNIOR
 */
public class AplicarDescontoPercentual implements ExpressaoDesconto {
    private final double desconto = 0.2;
    

    @Override
    public double interpretar(Venda venda) {
        double valorBruto = new ValorServicoBruto().interpretar(venda);
        
        return valorBruto * (1 - desconto);
    }
    
}

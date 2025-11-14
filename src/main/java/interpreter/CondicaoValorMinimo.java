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
public class CondicaoValorMinimo implements ExpressaoDesconto{

    private final double ValorMinimo = 100.0;
    private final ExpressaoDesconto expressaoDesconto;

    public CondicaoValorMinimo(ExpressaoDesconto expressaoDesconto) {
        this.expressaoDesconto = expressaoDesconto;
    }
    
    @Override
    public double interpretar(Venda venda) {
        double valorBruto = new ValorServicoBruto().interpretar(venda);
        
        
        if (valorBruto > ValorMinimo) {
            return expressaoDesconto.interpretar(venda); 
        }
        
       
        return valorBruto;
    }
}

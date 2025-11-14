/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import financeiro.Venda;


public class AplicarDesconto implements ExpressaoDesconto {
    private final double desconto;

    public AplicarDesconto(double desconto) {
        this.desconto = desconto;
    }
    
    

    @Override
    public double interpretar(Venda venda) {
        double valorBruto = new ValorServicoBruto().interpretar(venda);
        
        return valorBruto * (1 - desconto);
    }
    
}

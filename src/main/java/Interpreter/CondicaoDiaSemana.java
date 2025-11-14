/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import financeiro.Venda;
import java.time.DayOfWeek;

public class CondicaoDiaSemana implements ExpressaoDesconto {
    private final DayOfWeek diaDesconto = DayOfWeek.WEDNESDAY;
    private final ExpressaoDesconto expressaoDesconto;

    public CondicaoDiaSemana(ExpressaoDesconto expressaoSeVerdadeira) {
        this.expressaoDesconto = expressaoSeVerdadeira;
    }
    
    

    @Override
    public double interpretar(Venda venda) {
        if (venda.getDataHora().getDayOfWeek() == diaDesconto) {
            return expressaoDesconto.interpretar(venda);
        }
        
        return new ValorServicoBruto().interpretar(venda);
    }
    
}

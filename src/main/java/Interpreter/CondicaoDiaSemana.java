/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter;

import financeiro.Venda;
import java.time.DayOfWeek;

/**
 *
 * @author MARCIO JUNIOR
 */
public class CondicaoDiaSemana implements ExpressaoDesconto {
    private final DayOfWeek diaDesconto;
    private final ExpressaoDesconto expressaoSeVerdadeira;

    public CondicaoDiaSemana(DayOfWeek diaDesconto, ExpressaoDesconto expressaoSeVerdadeira) {
        this.diaDesconto = diaDesconto;
        this.expressaoSeVerdadeira = expressaoSeVerdadeira;
    }
    
    

    @Override
    public double interpretar(Venda venda) {
        if (venda.getDataHora().getDayOfWeek() == diaDesconto) {
            return expressaoSeVerdadeira.interpretar(venda);
        }
        
        return new ValorServicoBruto().interpretar(venda);
    }
    
}

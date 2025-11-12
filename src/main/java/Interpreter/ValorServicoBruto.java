/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interpreter;

import entidades.Servico;
import financeiro.Venda;

public class ValorServicoBruto implements ExpressaoDesconto {

    @Override
    public double interpretar(Venda venda) {
        return venda.getServicos().stream().mapToDouble(Servico::getValor).sum();
        
    }
  
}

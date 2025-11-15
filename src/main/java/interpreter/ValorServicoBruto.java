/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import entidades.Servico;
import financeiro.Venda;

/**
 * Implementação da interface ExpressaoDesconto que representa a expressão
 * responsável por calcular o valor bruto dos serviços realizados em uma venda,
 * sem aplicar nenhum tipo de desconto.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class ValorServicoBruto implements ExpressaoDesconto {

    /**
     * Calcula o valor bruto dos serviços associados à venda, somando
     * o valor de cada Serviço contido na lista de serviços.
     *
     * @param venda A venda cujos serviços serão avaliados.
     * @return A soma dos valores de todos os serviços da venda.
     */
    @Override
    public double interpretar(Venda venda) {
        return venda.getServicos()
                .stream()
                .mapToDouble(Servico::getValor)
                .sum();
    }
}
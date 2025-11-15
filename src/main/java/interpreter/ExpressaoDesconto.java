/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interpreter;

import financeiro.Venda;

/**
 * Interface que representa uma expressão do padrão Interpreter para cálculo de descontos.
 *
 * O padrão Interpreter permite estruturar regras de negócio como pequenas expressões
 * independentes, que podem ser combinadas, reutilizadas e aplicadas dinamicamente.
 * Nesta aplicação, cada implementação desta interface representa uma regra de desconto
 * que pode ser aplicada a uma {@link Venda}.
 * @author Márcio Antônio
 * @author Rafael Martins
 *
 */
public interface ExpressaoDesconto {

    /**
     * Interpreta uma regra de desconto e retorna o valor total da venda
     * após a aplicação da regra definida pela expressão.
     *
     * @param venda Objeto contendo informações necessárias para o cálculo.
     * @return O valor calculado após aplicar a regra de desconto definida.
     */
    double interpretar(Venda venda);
}
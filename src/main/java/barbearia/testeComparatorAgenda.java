/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barbearia;

import agendamento.GerenciadorAgendamento;
import agendamento.GerenciadorAgendamentoSecundario;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import financeiro.GerenciadorVenda;

/**
 *
 * @author MARCIO JUNIOR
 */
public class testeComparatorAgenda {
    public static void main(String[] args) {
        GerenciadorServicos gs = new GerenciadorServicos();
        GerenciadorProdutos gp = new GerenciadorProdutos();
        GerenciadorVenda gv = new GerenciadorVenda(gp);
        GerenciadorAgendamentoSecundario gas = new GerenciadorAgendamentoSecundario();
        GerenciadorAgendamento ga = new GerenciadorAgendamento( gas);
        
        
        ga.organizarPorMaisRecentes();
    }
    
}

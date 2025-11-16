/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barbearia;

import agendamento.GerenciadorAgendamento;
import agendamento.GerenciadorAgendamentoSecundario;
import controller.GerenciadorClientes;
import controller.GerenciadorFuncionarios;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import entidades.Cliente;
import entidades.Funcionario;
import estacoesatendimento.GerenciadorDeEstacoes;
import financeiro.GerenciadorDespesas;
import financeiro.GerenciadorVenda;
import ordemdeservico.GerenciadorOs;

/**
 *
 * @author rafin
 */
public class QuestoesRespondidas {



    public static void main(String[] args) {

        GerenciadorServicos gs = new GerenciadorServicos();
        GerenciadorProdutos gp = new GerenciadorProdutos();
        GerenciadorVenda gv = new GerenciadorVenda(gp);
        GerenciadorAgendamentoSecundario gas = new GerenciadorAgendamentoSecundario();
        GerenciadorAgendamento ga = new GerenciadorAgendamento(gas);
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        GerenciadorDespesas gd = new GerenciadorDespesas();
        GerenciadorOs gos = new GerenciadorOs();

        //Questão 01: {
        System.out.println("===== Questão 01 =====");
        System.out.println("Diagrama feito");
        // }
        //Questão 02: {
        System.out.println("====Questão 02====");
        System.out.println("Acessar a classe Funcionario, atributo cargo ( Linha 17 )");
        //}
        //Questão 03: {
        System.out.println("====Questão 03====");
        System.out.println("Acessar qualquer entidades");
        // }
        //Questão 04: {
        System.out.println("====Questão 04====");
        System.out.println("Acessar a classe Funcionário ou Cliente em seus construtores");
        // }
        //Questão 05: {
        System.out.println("====Questão 05====");
        System.out.println("Acessar a classe GerenciadorDeEstações ( linha 12 a 19 )");
        // }
        //Questão 06: {
        System.out.println("====Questão 06====");
        Funcionario f = new Funcionario("Rafael", "15322402608", "ADMIN", "admin123", "Rua Barao do Rio Branco", "38999279932");
        gf.addFuncionario(f);
        gf.alterarCargoFuncionario("BARBEIRO", f);
        gf.alterarCpfFuncionario("10936304693", f);
        gf.alterarEnderecoFuncionario("Rua Diamantina", f);
        gf.alterarNomeFuncionario("Marcio", f);
        gf.alterarSenhaFuncionario("barb123", f);
        gf.alterarTelefoneFuncionario("38999184025", f);
        gf.removerFuncionarioCpf(f);
        //}

        //Questão 07: {
        System.out.println("====Questão 07====");
        Cliente c = new Cliente("Rafael", "15322402608", "rafinhamartins@gmail.com", "Rua Barao do Rio Branco", "38999279932");
        gc.addCliente(c);
        gc.alterarCpfCliente("10936304693", c);
        gc.alterarEmailCliente("marcio@gmail.com", c);
        gc.alterarEnderecoCliente("Rua Diamantina", c);
        gc.alterarNomeCliente("Marcio", c);
        gc.alterarTelefoneCliente("38999184025", c);
        gc.removerCliente(c);
        //}

        //Questão 08: {
        System.out.println("====Questão 08====");
        Cliente clienteOs = new Cliente("Rafael", "15322402608", "rafinhamartins@gmail.com", "Rua Barao do Rio Branco", "38999279932");
        Funcionario funcionarioOs = new Funcionario("Rafael", "15322402608", "ADMIN", "admin123", "Rua Barao do Rio Branco", "38999279932");
        gos.abrirOs(c, f);
        gos.imprimirOsDeCliente(c);
        // }
        //Questão 09: {
        System.out.println("====Questão 09====");
        System.out.println("Mostrar Gerenciador Genérico");
        System.out.println("Listas dinamicas carregadas: ");
        System.out.println("Ordem de serviço: " + gos.getListaOs().size() + "Itens");
        System.out.println("Clientes: " + gc.getClientes().size() + "Itens");
        System.out.println("Estoque: " + gp.getProduto().size() + "Itens");

        // }
        //Questão 10: {
        System.out.println("====Questão 10====");
        String extrato = gos.getListaOs().get(0).gerarExtrato();
        System.out.println("Extrato da primeira Ordem de Servico gerado e salvo" );
        System.out.println(extrato);

        // }
        //Questão 11: {
        System.out.println("====Questão 11====");
        System.out.println("Precisar fazer!");
        // }
        //Questão 12: {
        System.out.println("====Questão 12====");
        System.out.println("Precisa fazer!");
        // }
        //Questão 13: {
        System.out.println("====Questão 13====");
        System.out.println("Acessar a classe CompareteNameCliente");
        // }
        //Questão 14: {
        System.out.println("====Questão 14====");
        System.out.println("Mostrar as JSON prontas!");
        // }
        //Questão 15: {
        System.out.println("====Questão 15====");
        System.out.println("Precisa fazer");
        // }
        //Questão 16: {
        System.out.println("====Questão 16====");
        System.out.println("Precisa fazer");
        // }
        //Questão 17: {
        System.out.println("====Questão 17====");
        System.out.println("Precisa fazer");
        // }
        //Questão 18: {
        System.out.println("====Questão 18====");
        System.out.println("Precisa fazer");
        // }

    }



}

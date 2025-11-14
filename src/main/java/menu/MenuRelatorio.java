/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import controller.GerenciadorProdutos;
import financeiro.GerenciadorDespesas;
import financeiro.GerenciadorVenda;
import financeiro.Relatorio;
import java.util.Scanner;

/**
 *
 * @author rafin
 */
public class MenuRelatorio {
    
    public static void exibirMenuRelatorio() {

        Scanner sc = new Scanner(System.in);

        // Instâncias necessárias
        GerenciadorVenda gv = new GerenciadorVenda(new GerenciadorProdutos());
        GerenciadorDespesas gd = new GerenciadorDespesas();
        Relatorio relatorio = new Relatorio(gv, gd);

        int opc;

        do {
            System.out.println("\n=========== MENU RELATÓRIOS ===========");
            System.out.println("1 - Gerar Balanço Mensal");
            System.out.println("2 - Relatório de Despesas");
            System.out.println("3 - Relatório de Vendas");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {

                case 1 -> {
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Mês (1-12): ");
                    int mes = sc.nextInt();
                    relatorio.gerarBalancoMensal(ano, mes);
                }

                case 2 -> {
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Mês (1-12): ");
                    int mes = sc.nextInt();
                    relatorio.gerarRelatorioDespesas(ano, mes);
                }

                case 3 -> {
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Mês (1-12): ");
                    int mes = sc.nextInt();
                    relatorio.gerarRelatorioVendas(ano, mes);
                }

                case 0 -> System.out.println("Voltando...");

                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }
}

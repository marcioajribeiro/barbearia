/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;
import Financeiro.GerenciadorDespesas;
import java.util.Scanner;

/**
 *
 * @author MARCIO JUNIOR
 */


public class MenuDespesa {

    public static void exibirMenuDespesas() {
        Scanner sc = new Scanner(System.in);
        GerenciadorDespesas gd = new GerenciadorDespesas();
        int opc;

        do {
            System.out.println("\n====================================");
            System.out.println("         MENU DE DESPESAS");
            System.out.println("====================================");
            System.out.println("1 - Registrar despesa com materiais");
            System.out.println("2 - Registrar despesa com salários");
            System.out.println("3 - Registrar despesa com limpeza");
            System.out.println("4 - Registrar despesa com cortesias");
            System.out.println("0 - Voltar ao menu principal");
            System.out.println("====================================");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Informe o material comprado: ");
                    String material = sc.nextLine();
                    System.out.print("Informe o valor: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    gd.registrarDespesaMaterial(material, valor);
                    System.out.println(" Despesa com material registrada!");
                }

                case 2 -> {
                    System.out.print("Informe o valor total dos salários pagos: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    gd.registrarDespesaSalario(valor);
                    System.out.println("Despesa com salários registrada!");
                }

                case 3 -> {
                    System.out.print("Informe o valor gasto com limpeza: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    gd.registrarDespesaLimpeza(valor);
                    System.out.println("Despesa com limpeza registrada!");
                }

                case 4 -> {
                    System.out.print("Informe o nome ou tipo da cortesia: ");
                    String cortesia = sc.nextLine();
                    System.out.print("Informe o valor da cortesia: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    gd.registrarDespesaCortesias(cortesia, valor);
                    System.out.println("Despesa com cortesia registrada!");
                }

                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("⚠️ Opção inválida! Tente novamente.");
            }

        } while (opc != 0);
    }
}


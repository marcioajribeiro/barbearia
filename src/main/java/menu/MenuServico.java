/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;
import controller.GerenciadorServicos;
import entidades.Servico;
import java.util.Scanner;
/**
 *
 * @author MARCIO JUNIOR
 */



public class MenuServico {

    public static void exibirMenuServicos() {
        Scanner sc = new Scanner(System.in);
        GerenciadorServicos gs = new GerenciadorServicos();
        int opc;

        do {
            System.out.println("\n----- MENU SERVIÇOS -----");
            System.out.println("1 - Cadastrar Serviço");
            System.out.println("2 - Listar Serviços");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover Serviço");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Nome do serviço: ");
                    String nome = sc.nextLine();
                    System.out.print("Duração (min): ");
                    int duracao = sc.nextInt();
                    System.out.print("Valor: ");
                    double valor = sc.nextDouble();

                    Servico s = new Servico(nome, valor, duracao);
                    gs.addServico(s);
                }
                case 2 -> gs.listarServicos();
                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    Servico s = gs.buscarServicoId(id);
                    if (s != null) System.out.println(s);
                }
                case 4 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    Servico s = gs.buscarServicoId(id);
                    if (s != null) gs.removerServico(s);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }
}

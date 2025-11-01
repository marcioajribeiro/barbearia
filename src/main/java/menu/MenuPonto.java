/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;
import controleponto.GerenciadorPonto;
import controller.GerenciadorFuncionarios;
import entidades.Funcionario;
import java.util.Scanner;
/**
 *
 * @author MARCIO JUNIOR
 */


public class MenuPonto {

    public static void exibirMenuPonto() {
        Scanner sc = new Scanner(System.in);
        GerenciadorPonto gp = new GerenciadorPonto();
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        int opc;

        do {
            System.out.println("\n----- CONTROLE DE PONTO -----");
            System.out.println("1 - Bater Entrada");
            System.out.println("2 - Bater Saída");
            System.out.println("3 - Listar Pontos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("CPF do Funcionário: ");
                    String cpf = sc.nextLine();
                    Funcionario f = gf.buscarFuncionarioCpf(cpf);
                    if (f != null) gp.baterEntrada(f);
                }
                case 2 -> {
                    System.out.print("CPF do Funcionário: ");
                    String cpf = sc.nextLine();
                    Funcionario f = gf.buscarFuncionarioCpf(cpf);
                    if (f != null) gp.baterSaida(f);
                }
                case 3 -> gp.listarPontos();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }
}

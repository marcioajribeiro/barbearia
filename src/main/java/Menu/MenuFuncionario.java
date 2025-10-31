/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;
import Controller.GerenciadorFuncionarios;
import Entidades.Funcionario;
import java.util.Scanner;
/**
 *
 * @author MARCIO JUNIOR
 */



public class MenuFuncionario {

    public static void exibirMenuFuncionarios() {
        Scanner sc = new Scanner(System.in);
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        int opc;

        do {
            System.out.println("\n----- MENU FUNCIONÁRIOS -----");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Remover Funcionário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    Funcionario f = new Funcionario(nome,cpf, cargo,senha,endereco, telefone);
                    gf.addFuncionario(f);
                }
                case 2 -> gf.listarFuncionários();
                case 3 -> {
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    Funcionario f = gf.buscarFuncionarioCpf(cpf);
                    if (f != null) System.out.println(f);
                }
                case 4 -> {
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    Funcionario f = gf.buscarFuncionarioCpf(cpf);
                    if (f != null) gf.removerFuncionarioCpf(f);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }
}

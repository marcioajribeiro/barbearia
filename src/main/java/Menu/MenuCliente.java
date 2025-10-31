package Menu;

import Controller.GerenciadorClientes;
import Entidades.Cliente;
import java.util.Scanner;


public class MenuCliente {

    public static void exibirMenuClientes() {
        Scanner sc = new Scanner(System.in);
        GerenciadorClientes gc = new GerenciadorClientes();
        int opc;

        do {
            System.out.println("\n----- MENU CLIENTES -----");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Buscar Cliente por CPF");
            System.out.println("4 - Remover Cliente");
            System.out.println("0 - Voltar ao Menu Principal");
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
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    Cliente c = new Cliente(nome,cpf,email, endereco, telefone);
                    gc.addCliente(c);
                    System.out.println("Cliente cadastrado com sucesso!");
                }
                case 2 -> gc.listarClientes();
                case 3 -> {
                    System.out.print("Digite o CPF: ");
                    String cpf = sc.nextLine();
                    Cliente c = gc.buscarCliente(cpf);
                    if (c != null) System.out.println(c);
                }
                case 4 -> {
                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente c = gc.buscarCliente(cpf);
                    if (c != null) gc.removerCliente(c);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }
}

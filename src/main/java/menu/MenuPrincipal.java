/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;
import java.util.Scanner;
/**
 *
 * @author MARCIO JUNIOR
 */


public class MenuPrincipal {

    public void exibirMenuPrincipal() throws Exception {
        Scanner sc = new Scanner(System.in);
        int opc;

        do {
            System.out.println("\n====================================");
            System.out.println("      SISTEMA BARBEARIA ");
            System.out.println("====================================");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Funcionários");
            System.out.println("3 - Gerenciar Produtos");
            System.out.println("4 - Gerenciar Serviços");
            System.out.println("5 - Controle de Ponto");
            System.out.println("6 - Gerenciar Despesas");
            System.out.println("7 - Gerenciar Agendamento");
            System.out.println("8 - Gerenciar Vendas");
            System.out.println("0 - Sair");
            System.out.println("====================================");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> MenuCliente.exibirMenuClientes();
                case 2 -> MenuFuncionario.exibirMenuFuncionarios();
                case 3 -> MenuProduto.exibirMenuProdutos();
                case 4 -> MenuServico.exibirMenuServicos();
                case 5 -> MenuPonto.exibirMenuPonto();
                case 6 -> MenuDespesa.exibirMenuDespesas();
                case 7 -> MenuAgendamento.exibirMenuAgendamento();
                case 8 -> MenuVenda.exibirMenuVendas();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);

        sc.close();
    }
}

package menu;

import controller.GerenciadorFuncionarios;
import login.LoginService;
import entidades.Cliente;
import entidades.Funcionario;
import java.util.Scanner;

public class MenuPrincipal {
    
    private Scanner sc = new Scanner(System.in);
    private GerenciadorFuncionarios gerenciadorFuncionarios = new GerenciadorFuncionarios();
    
    private LoginService loginService = new LoginService();

    public void iniciarSistema() {
        System.out.println("====================================");
        System.out.println("     SISTEMA DE BARBEARIA - LOGIN");
        System.out.println("====================================");

        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Funcionario usuarioLogado = loginService.login(cpf, senha);

        if (usuarioLogado != null) {
            if (usuarioLogado.getCargo().equalsIgnoreCase("ADMIN")) {
                exibirMenuAdmin(usuarioLogado);
            } else if (usuarioLogado.getCargo().equalsIgnoreCase("BARBEIRO")) {
                exibirMenuBarbeiro(usuarioLogado);
            } else {
                System.out.println("Cargo não reconhecido. Acesso negado.");
            }
        }
    }

    private void exibirMenuAdmin(Funcionario admin) {
        int opc;
        do {
            System.out.println("\n====================================");
            System.out.println("Bem-vindo, " + admin.getNome() + " (ADMIN)");
            System.out.println("====================================");
            System.out.println("1 - Administração de Funcionário");
            System.out.println("2 - Administração de Cliente");
            System.out.println("3 - Administração de Agendamento");
            System.out.println("4 - Administração de Despesa");
            System.out.println("5 - Administração de Ponto");
            System.out.println("6 - Administração de Produto");
            System.out.println("7 - Administração de Serviços");
            System.out.println("8 - Administração de Vendas");
            System.out.println("9 - Administração de Relatório");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> MenuFuncionario.exibirMenuFuncionarios();
                case 2 -> MenuCliente.exibirMenuClientes();
                case 3 ->{
                try {
                    MenuAgendamento.exibirMenuAgendamento();
                } catch (Exception e) {
                    System.out.println("Erro ao abrir o menu de agendamentos: " + e.getMessage());
                }
            }
                case 4 -> MenuDespesa.exibirMenuDespesas();
                case 5 -> MenuPonto.exibirMenuPonto();
                case 6 -> MenuProduto.exibirMenuProdutos();
                case 7 -> MenuServico.exibirMenuServicos();
                case 8 -> MenuVenda.exibirMenuVendas();
                case 9 -> MenuRelatorio.exibirMenuRelatorio();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    private void exibirMenuBarbeiro(Funcionario barbeiro) {
        int opc;
        do {
            System.out.println("\n====================================");
            System.out.println("Bem-vindo, " + barbeiro.getNome() + " (BARBEIRO)");
            System.out.println("====================================");
            System.out.println("1 - Administração de Cliente");
            System.out.println("2 - Administração de Agendamento");
            System.out.println("3 - Administração de Ponto");
            System.out.println("4 - Administração de Produto");
            System.out.println("5 - Administração de Vendas");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> MenuCliente.exibirMenuClientes();
                case 2 -> {
                try {
                    MenuAgendamento.exibirMenuAgendamento();
                } catch (Exception e) {
                    System.out.println("Erro ao abrir o menu de agendamentos: " + e.getMessage());
                }
            }
                case 3 -> MenuPonto.exibirMenuPonto();
                case 4 -> MenuProduto.exibirMenuProdutos();
                case 5 -> MenuVenda.exibirMenuVendas();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }
}

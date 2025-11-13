package menu;

import controller.GerenciadorFuncionarios;
import login.LoginService;
import entidades.Funcionario;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner sc = new Scanner(System.in);
    private LoginService loginService = new LoginService();
    private GerenciadorFuncionarios gerenciadorFuncionarios = new GerenciadorFuncionarios();

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
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("3 - Remover Funcionário");
            System.out.println("4 - Editar Dados de Funcionário");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> cadastrarFuncionario();
                case 2 -> gerenciadorFuncionarios.listarFuncionarios();
                case 3 -> removerFuncionario();
                case 4 -> editarFuncionario();
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
            System.out.println("1 - Ver agendamentos");
            System.out.println("2 - Registrar atendimento");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> System.out.println("Mostrando agendamentos (em desenvolvimento)...");
                case 2 -> System.out.println("Registrando atendimento (em desenvolvimento)...");
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    private void cadastrarFuncionario() {
        System.out.println("\n--- Cadastro de Funcionário ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Cargo (ADMIN/BARBEIRO): ");
        String cargo = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        Funcionario novo = new Funcionario(nome, cpf, cargo.toUpperCase(), senha, endereco, telefone);
        gerenciadorFuncionarios.addFuncionario(novo);
    }

    private void removerFuncionario() {
        System.out.print("Informe o ID do funcionário a remover: ");
        int id = sc.nextInt();
        sc.nextLine();
        Funcionario f = gerenciadorFuncionarios.buscarFuncionarioPorId(id);
        gerenciadorFuncionarios.removerFuncionarioCpf(f);
    }

    private void editarFuncionario() {
        System.out.print("Informe o ID do funcionário para editar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Funcionario f = gerenciadorFuncionarios.buscarFuncionarioPorId(id);
        if (f == null) return;

        System.out.println("1 - Nome\n2 - Endereço\n3 - Telefone\n4 - Cargo\n5 - Senha");
        System.out.print("Escolha o campo para alterar: ");
        int campo = sc.nextInt();
        sc.nextLine();

        switch (campo) {
            case 1 -> {
                System.out.print("Novo nome: ");
                gerenciadorFuncionarios.alterarNomeFuncionario(sc.nextLine(), f);
            }
            case 2 -> {
                System.out.print("Novo endereço: ");
                gerenciadorFuncionarios.alterarEnderecoFuncionario(sc.nextLine(), f);
            }
            case 3 -> {
                System.out.print("Novo telefone: ");
                gerenciadorFuncionarios.alterarTelefoneFuncionario(sc.nextLine(), f);
            }
            case 4 -> {
                System.out.print("Novo cargo (ADMIN/BARBEIRO): ");
                gerenciadorFuncionarios.alterarCargoFuncionario(sc.nextLine().toUpperCase(), f);
            }
            case 5 -> {
                System.out.print("Nova senha: ");
                gerenciadorFuncionarios.alterarSenhaFuncionario(sc.nextLine(), f);
            }
            default -> System.out.println("Opção inválida!");
        }
    }

}

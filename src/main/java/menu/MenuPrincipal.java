//package menu;
//
//import controller.GerenciadorFuncionarios;
//import login.LoginService;
//import entidades.Funcionario;
//import java.util.Scanner;
//import login.VerificacaoLogin;
//
//public class MenuPrincipal {
//    public void exibir() {
//    Scanner scanner = new Scanner(System.in);
//  //  VerificacaoLogin verificacaoLogin = VerificacaoLogin.getInstancia();
//    //String cargo = verificacaoLogin.getFuncionarioLogado().getCargo();
//    int opcao = -1;
//
//
//    do {
//        System.out.println("\n====================================");
//        System.out.println("  MENU PRINCIPAL - CARGO: " + cargo);
//        System.out.println("====================================");
//
//        // Define e exibe as opções com base no cargo
//        switch (cargo) {
//            case "Administrador":
//                System.out.println("1 - Agendamento");
//                System.out.println("2 - Cliente");
//                System.out.println("3 - Funcionário");    // Apenas Admin
//                System.out.println("4 - Produto");
//                System.out.println("5 - Serviço");
//                System.out.println("6 - Ponto");
//                System.out.println("7 - Despesa");        // Apenas Admin
//                System.out.println("8 - Venda");
//                System.out.println("9 - Relatório");
//                System.out.println("0 - Sair");
//                break;
//                
//            case "Barbeiro":
//                System.out.println("1 - Agendamento");
//                System.out.println("2 - Cliente");
//                System.out.println("3 - Ponto");
//                System.out.println("4 - Venda");
//                System.out.println("0 - Sair");
//                break;
//                
//            case "Recepcionista":
//                System.out.println("1 - Agendamento");
//                System.out.println("2 - Cliente");
//                System.out.println("3 - Produto"); 
//                System.out.println("4 - Venda");
//                System.out.println("0 - Sair");
//                break;
//                
//            default:
//                System.out.println("Cargo não reconhecido. Acesso restrito.");
//                System.out.println("0 - Sair");
//                break;
//        }
//
//        System.out.print("Selecione uma opção: ");
//        opcao = scanner.nextInt();
//
//        // Verifica o cargo novamente para rotear corretamente (estrutura de permissão original)
//        if (cargo.equals("Administrador")) {
//            // ... (roteamento para todas as 1 a 9 opções)
//            switch (opcao) {
//                case 1: new MenuAgendamento().exibirMenuAgendamento();break;
//                case 2: new MenuCliente().exibirMenuClientes();break;
//                case 3: new MenuFuncionario().exibirMenuFuncionarios();break; // Admin
//                case 4: new MenuProduto().exibirMenuProdutos();break;
//                case 5: new MenuServico().exibirMenuServicos();break;
//                case 6: new MenuPonto().exibirMenuPonto();break;
//                case 7: new MenuDespesa().exibirMenuDespesas();break; // Admin
//                case 8: new MenuVenda().exibirMenuVendas();break;
//                case 9: new Relatorio().exibir(); break;
//                case 0: System.out.println("Saindo do sistema."); break;
//                default: System.out.println("Opção inválida.");
//            }
//        } else if (cargo.equals("Barbeiro")) {
//            // ... (roteamento restrito)
//             switch (opcao) {
//                case 1: new MenuAgendamento().exibirMenuAgendamento();break;
//                case 2: new MenuCliente().exibirMenuClientes();break;
//                case 3: new MenuPonto().exibirMenuPonto();break;
//                case 4: new MenuVenda().exibirMenuVendas();break;
//                case 0: System.out.println("Saindo do sistema."); break;
//                default: System.out.println("Opção inválida.");
//            }
//        } 
//        // ... continua com os outros cargos seguindo a lógica da sua permissão original
//        
//    } while (opcao != 0);
//}
//
//}

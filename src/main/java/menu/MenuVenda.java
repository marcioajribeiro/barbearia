package menu;


import agendamento.Agendamento;
import agendamento.GerenciadorAgendamento;
import controller.GerenciadorClientes;
import controller.GerenciadorFuncionarios;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import financeiro.GerenciadorVenda;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import financeiro.Venda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MenuVenda {

    public static void exibirMenuVendas() {
        Scanner sc = new Scanner(System.in);
        GerenciadorAgendamento ga = new GerenciadorAgendamento();
        GerenciadorProdutos gp = new GerenciadorProdutos();
        GerenciadorVenda gv = new GerenciadorVenda(gp);
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        GerenciadorServicos gs = new GerenciadorServicos();
        int opc;

        do {
            System.out.println("\n----- MENU VENDAS -----");
            System.out.println("2 - Registrar Venda Produto");
            System.out.println("3 - Registrar Venda");
            System.out.println("4 - Registrar Venda por cancelamento");
            System.out.println("5 - Listar Vendas");
            System.out.println("6 - Buscar Venda por ID");
            System.out.println("7 - Remover Venda");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 2 -> {
                    
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = gc.buscarCliente(cpf);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }


                    System.out.print("ID do funcionário responsável: ");
                    int idFuncionario = sc.nextInt();
                    sc.nextLine();
                    Funcionario funcionario = gf.buscarFuncionarioPorId(idFuncionario);
                    if (funcionario == null) {
                        System.out.println("Funcionário não encontrado!");
                        break;
                    }


                    List<Produto> listaProdutos = new ArrayList<>();
                    String continua;
                    do {
                        System.out.print("ID do produto: ");
                        int idProduto = sc.nextInt();
                        sc.nextLine();
                        Produto produto = gp.buscarProdutoPorId(idProduto);
                        if (produto != null) {
                            listaProdutos.add(produto);
                        } else {
                            System.out.println("Produto não encontrado!");
                        }

                        System.out.print("Deseja adicionar outro produto? (s/n): ");
                        continua = sc.nextLine();
                    } while (continua.equalsIgnoreCase("s"));
                    
                                        System.out.print("Forma de pagamento (Dinheiro / Cartão / Pix): ");
                    String formaPagamento = sc.nextLine();



                    LocalDateTime dataHora = LocalDateTime.now();


                    Venda venda = new Venda(cliente, funcionario, listaProdutos, new ArrayList<>(), formaPagamento, dataHora);


                    gv.registrarVenda(venda);

                    System.out.println("Venda registrada com sucesso!");


                    
                    
                }
                case 3 -> {

                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = gc.buscarCliente(cpf);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }


                    System.out.print("ID do funcionário responsável: ");
                    int idFuncionario = sc.nextInt();
                    sc.nextLine();
                    Funcionario funcionario = gf.buscarFuncionarioPorId(idFuncionario);
                    if (funcionario == null) {
                        System.out.println("Funcionário não encontrado!");
                        break;
                    }


                    List<Produto> listaProdutos = new ArrayList<>();
                    String continua;
                    do {
                        System.out.print("ID do produto: ");
                        int idProduto = sc.nextInt();
                        sc.nextLine();
                        Produto produto = gp.buscarProdutoPorId(idProduto);
                        if (produto != null) {
                            listaProdutos.add(produto);
                        } else {
                            System.out.println("Produto não encontrado!");
                        }

                        System.out.print("Deseja adicionar outro produto? (s/n): ");
                        continua = sc.nextLine();
                    } while (continua.equalsIgnoreCase("s"));


                    List<Servico> listaServicos = new ArrayList<>();
                    do {
                        System.out.print("ID do serviço: ");
                        int idServico = sc.nextInt();
                        sc.nextLine();
                        Servico servico = gs.buscarServicoId(idServico);
                        if (servico != null) {
                            listaServicos.add(servico);
                        } else {
                            System.out.println("Serviço não encontrado!");
                        }

                        System.out.print("Deseja adicionar outro serviço? (s/n): ");
                        continua = sc.nextLine();
                    } while (continua.equalsIgnoreCase("s"));

                    System.out.print("Forma de pagamento (Dinheiro / Cartão / Pix): ");
                    String formaPagamento = sc.nextLine();



                    LocalDateTime dataHora = LocalDateTime.now();


                    Venda venda = new Venda(cliente, funcionario, listaProdutos, listaServicos, formaPagamento, dataHora);


                    gv.registrarVenda(venda);

                    System.out.println("Venda registrada com sucesso!");
                }
                case 4 -> {
                    System.out.println("Digite Id do agendamento cancelado: ");
                    int id = sc.nextInt();
                    Agendamento a = ga.buscarAgendamentoId(id);
                    gv.registrarVendaCancelamento(a);
                }


                case 5 -> gv.listarVendas();
                case 6 -> {
                    System.out.print("ID da venda: ");
                    int id = sc.nextInt();
                    Venda v = gv.buscarVendaPorId(id);
                    if (v != null) System.out.println(v);
                    else System.out.println("Venda não encontrada.");
                }
                case 7 -> {
                    System.out.print("ID da venda a remover: ");
                    int id = sc.nextInt();
                    Venda v = gv.buscarVendaPorId(id);
                    gv.removerVenda(v);
                }
                case 0 -> System.out.println("Voltar");
                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }
}

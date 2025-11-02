package menu;


import controller.GerenciadorClientes;
import controller.GerenciadorFuncionarios;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import controller.GerenciadorVenda;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import entidades.Venda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MenuVenda {

    public static void exibirMenuVendas() {
        Scanner sc = new Scanner(System.in);
        GerenciadorVenda gv = new GerenciadorVenda();
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        GerenciadorProdutos gp = new GerenciadorProdutos();
        GerenciadorServicos gs = new GerenciadorServicos();
        int opc;

        do {
            System.out.println("\n----- MENU VENDAS -----");
            System.out.println("1 - Registrar Venda");
            System.out.println("2 - Listar Vendas");
            System.out.println("3 - Buscar Venda por ID");
            System.out.println("4 - Remover Venda");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {

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


                    int idVenda = gv.geradorIdVenda();


                    LocalDateTime dataHora = LocalDateTime.now();


                    Venda venda = new Venda(idVenda, cliente, funcionario, listaProdutos, listaServicos, 0.0, formaPagamento, dataHora);


                    gv.registrarVenda(venda);

                    System.out.println("Venda registrada com sucesso!");
                }


                case 2 -> gv.listarVendas();
                case 3 -> {
                    System.out.print("ID da venda: ");
                    int id = sc.nextInt();
                    Venda v = gv.buscarVendaPorId(id);
                    if (v != null) System.out.println(v);
                    else System.out.println("Venda não encontrada.");
                }
                case 4 -> {
                    System.out.print("ID da venda a remover: ");
                    int id = sc.nextInt();
                    gv.removerVenda(id);
                }
                case 0 -> System.out.println("Voltar");
                default -> System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }
}

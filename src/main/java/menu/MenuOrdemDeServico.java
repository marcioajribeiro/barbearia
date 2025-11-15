package menu;

import ordemdeservico.GerenciadorOs;
import ordemdeservico.OrdemDeServico;
import ordemDeServico.TipoStatusOs;

import controller.GerenciadorClientes;
import controller.GerenciadorFuncionarios;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;

import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuOrdemDeServico {

    public static void exibirMenuOs() {

        Scanner sc = new Scanner(System.in);

        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        GerenciadorServicos gs = new GerenciadorServicos();
        GerenciadorProdutos gp = new GerenciadorProdutos();

        // GerenciadorOs EXIGE esses 4 no construtor
        GerenciadorOs gos = new GerenciadorOs(gs, gc, gf, gp);

        int opc;

        do {
            System.out.println("\n----- MENU ORDEM DE SERVIÇO -----");
            System.out.println("1 - Criar Nova OS");
            System.out.println("2 - Listar OS");
            System.out.println("3 - Buscar OS por ID");
            System.out.println("4 - Adicionar Produto na OS");
            System.out.println("5 - Adicionar Serviço na OS");
            System.out.println("6 - Alterar Status da OS");
            System.out.println("7 - Remover OS");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {

                // ============================
                // 1 - Criar Nova OS
                // ============================
                case 1 -> {

                    System.out.print("CPF do cliente: ");
                    String cpfCliente = sc.nextLine();
                    Cliente cliente = gc.buscarCliente(cpfCliente);

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }

                    System.out.print("CPF do funcionário: ");
                    String cpfFuncionario = sc.nextLine();
                    Funcionario func = gf.buscarFuncionarioCpf(cpfFuncionario);

                    if (func == null) {
                        System.out.println("Funcionário não encontrado.");
                        break;
                    }

                    // Seleção de serviços
                    List<Servico> listaServico = new ArrayList<>();
                    String continuaServico;
                    do {
                        System.out.print("ID do serviço: ");
                        int idServico = sc.nextInt();
                        sc.nextLine();
                        Servico servico = gs.buscarServicoId(idServico);
                        if (servico != null) {
                            listaServico.add(servico);
                        } else {
                            System.out.println("Serviço não encontrado!");
                        }

                        System.out.print("Adicionar outro serviço? (s/n): ");
                        continuaServico = sc.nextLine();
                    } while (continuaServico.equalsIgnoreCase("s"));

                    // Seleção de produtos
                    List<Produto> listaProdutos = new ArrayList<>();
                    String continuaProduto;
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

                        System.out.print("Adicionar outro produto? (s/n): ");
                        continuaProduto = sc.nextLine();
                    } while (continuaProduto.equalsIgnoreCase("s"));

                    // Data
                    LocalDateTime dataHora = null;
                    boolean dataValida = false;
                    while (!dataValida) {
                        System.out.print("Data e Hora (dd/MM/yyyy HH:mm): ");
                        String dataStr = sc.nextLine();
                        try {
                            dataHora = LocalDateTime.parse(
                                dataStr.trim(),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                            );
                            dataValida = true;
                        } catch (Exception e) {
                            System.out.println("Formato inválido!");
                        }
                    }

                    System.out.print("Observações: ");
                    String observacoes = sc.nextLine();

                    // Criar OS (sem valor total)
                    OrdemDeServico os = new OrdemDeServico(
                        cliente,
                        func,
                        listaProdutos,
                        listaServico,
                        dataHora,
                        observacoes
                    );

                    gos.addOrdemServico(os);
                    gos.atualizarLista();

                    System.out.println("OS criada com sucesso! ID: " + os.getIdOS());
                }

                // ============================
                // 2 - Listar OS
                // ============================
                case 2 -> gos.listarOS();

                // ============================
                // 3 - Buscar por ID
                // ============================
                case 3 -> {
                    System.out.print("ID da OS: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    OrdemDeServico os = gos.buscarPorId(id);
                    System.out.println(os != null ? os : "OS não encontrada.");
                }

                // ============================
                // 4 - Adicionar Produto
                // ============================
                case 4 -> {
                    System.out.print("ID da OS: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    OrdemDeServico os = gos.buscarPorId(id);
                    if (os == null) {
                        System.out.println("OS não encontrada.");
                        break;
                    }

                    System.out.print("ID do Produto: ");
                    int cod = sc.nextInt();
                    sc.nextLine();

                    Produto p = gp.buscarProdutoPorId(cod);
                    if (p == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    gos.addProduto(p, os);
                    gos.atualizarLista();

                    System.out.println("Produto adicionado!");
                }

                // ============================
                // 5 - Adicionar Serviço
                // ============================
                case 5 -> {
                    System.out.print("ID da OS: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    OrdemDeServico os = gos.buscarPorId(id);
                    if (os == null) {
                        System.out.println("OS não encontrada.");
                        break;
                    }

                    System.out.print("ID do Serviço: ");
                    int cod = sc.nextInt();
                    sc.nextLine();

                    Servico s = gs.buscarServicoId(cod);
                    if (s == null) {
                        System.out.println("Serviço não encontrado.");
                        break;
                    }

                    gos.addServico(s, os);
                    gos.atualizarLista();

                    System.out.println("Serviço adicionado!");
                }

                // ============================
                // 6 - Alterar Status
                // ============================
                case 6 -> {
                    System.out.print("ID da OS: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    OrdemDeServico os = gos.buscarPorId(id);
                    if (os == null) {
                        System.out.println("OS não encontrada.");
                        break;
                    }

                    System.out.println("""
                        1 - Em andamento
                        2 - Concluída
                        3 - Cancelada
                    """);

                    int st = sc.nextInt();
                    sc.nextLine();

                    switch (st) {
                        case 1 -> gos.alterarStatusEmAndamento(os);
                        case 2 -> gos.alterarStatusConcluido(os);
                        case 3 -> gos.alterarStatusCancelado(os);
                        default -> System.out.println("Status inválido!");
                    }

                    gos.atualizarLista();
                    System.out.println("Status alterado!");
                }

                // ============================
                // 7 - Remover OS
                // ============================
                case 7 -> {
                    System.out.print("ID da OS: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (gos.removerOrdemServico(id)) {
                        gos.atualizarLista();
                        System.out.println("OS removida!");
                    } else {
                        System.out.println("OS não encontrada.");
                    }
                }

                // ============================
                // 0 - VOLTAR
                // ============================
                case 0 -> System.out.println("Voltando ao menu principal...");

                default -> System.out.println("Opção inválida.");
            }

        } while (opc != 0);
    }
}
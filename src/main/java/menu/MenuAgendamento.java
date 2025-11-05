/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;
import agendamento.Agendamento;
import agendamento.GerenciadorAgendamento;
import controller.GerenciadorClientes;
import controller.GerenciadorFuncionarios;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Servico;
import financeiro.GerenciadorVenda;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author MARCIO JUNIOR
 */

public class MenuAgendamento {

    public static void exibirMenuAgendamento() throws Exception {
        Scanner sc = new Scanner(System.in);
        GerenciadorVenda gv = new GerenciadorVenda(new GerenciadorProdutos());
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        GerenciadorServicos gs = new GerenciadorServicos();
        GerenciadorAgendamento ga = new GerenciadorAgendamento(gv, gs);
        int opc;

        do {
            System.out.println("\n----- MENU AGENDAMENTO -----");
            System.out.println("1 - Cadastrar Agendamento");
            System.out.println("2 - Listar Agendamentos");
            System.out.println("3 - Buscar Agendamento por ID");
            System.out.println("4 - Cancelar Agendamento");
            System.out.println("5 - Concluir Agendamento");
            System.out.println("6 -  Agenda de hoje");
            System.out.println("7 -  Buscar agenda por dia");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc){
                case 1 -> {
                    // Buscar cliente
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = gc.buscarCliente(cpf);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }

                    // Buscar barbeiro
                    System.out.print("ID do barbeiro: ");
                    int idBarbeiro = sc.nextInt();
                    sc.nextLine(); // consome o ENTER
                    Funcionario barbeiro = gf.buscarFuncionarioId(idBarbeiro);
                    if (barbeiro == null) {
                        System.out.println("Barbeiro não encontrado!");
                        break;
                    }

                    // Adicionar serviços
                    List<Servico> listaServico = new ArrayList<>();
                    String continua;
                    do {
                        System.out.print("ID do serviço: ");
                        int idServico = sc.nextInt();
                        sc.nextLine(); // consome ENTER
                        Servico servico = gs.buscarServicoId(idServico);
                        if (servico != null) {
                            listaServico.add(servico);
                        } else {
                            System.out.println("Serviço não encontrado!");
                        }

                        System.out.print("Deseja adicionar outro serviço? (s/n): ");
                        continua = sc.nextLine();
                    } while (continua.equalsIgnoreCase("s"));

                    // Entrada de data e hora com validação
                    LocalDateTime dataHora = null;
                    boolean dataValida = false;
                    while (!dataValida) {
                        System.out.print("Data e Hora do agendamento (dd/MM/yyyy HH:mm): ");
                        String dataHoraStr = sc.nextLine();
                        try {
                            dataHora = LocalDateTime.parse(dataHoraStr.trim(),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                            dataValida = true;
                        } catch (Exception e) {
                            System.out.println("Formato inválido! Use dd/MM/yyyy HH:mm");
                        }
                    }

                    // Criar e adicionar agendamento
                    Agendamento agendamento = new Agendamento(cliente, barbeiro, listaServico, dataHora);
                    ga.addAgendamento(agendamento);
                    System.out.println("Agendamento cadastrado com sucesso!");
                }

                case 2 -> ga.listarAgendamentos();
                case 3 -> {
                    System.out.print("Digite o ID: ");
                    int id = sc.nextInt();
                    Agendamento agendamento = ga.buscarAgendamentoId(id);
                    System.out.println(agendamento); 
                }
                case 4 ->{
                    System.out.print("Digite o ID: ");
                    int id = sc.nextInt();
                    Agendamento agendamento = ga.buscarAgendamentoId(id);
                    ga.alterarStatusCancelado(agendamento);
                }
                case 5 -> {
                    System.out.print("Digite o ID: ");
                    int id = sc.nextInt();
                    Agendamento agendamento = ga.buscarAgendamentoId(id);
                    ga.alterarStatusConcluido(agendamento);
                }
                case 6 -> {
                    ga.listarAgendamentosDoDia();
                }
                case 7 ->{
                    LocalDate dataHora = null;
                    boolean dataValida = false;
                    while (!dataValida) {
                        System.out.print("Data e Hora do agendamento (dd/MM/yyyy HH:mm): ");
                        String dataHoraStr = sc.nextLine();
                        try {
                            dataHora = LocalDate.parse(dataHoraStr.trim(),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                            dataValida = true;
                        } catch (Exception e) {
                            System.out.println("Formato inválido! Use dd/MM/yyyy HH:mm");
                        }
                    }
                    ga.buscarAgendamentosDia(dataHora);
                }
                    
            }
        } while (opc != 0);
    }
}


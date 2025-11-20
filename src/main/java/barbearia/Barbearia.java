package barbearia;

import agendamento.*;
import controller.*;
import entidades.*;
import estacoesatendimento.*;
import financeiro.*;
import login.*;
import ordemdeservico.*;
import controleponto.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import interpreter.BuscaPorData;
import interpreter.BuscaPorFuncionario;
import interpreter.EBusca;

/**
 * Classe principal para testar e interagir com o sistema da Barbearia através de um Menu.
 */
public class Barbearia {


    private static GerenciadorFuncionarios gf;
    private static GerenciadorClientes gc;
    private static GerenciadorServicos gs;
    private static GerenciadorProdutos gp;
    private static GerenciadorAgendamentoSecundario gas;
    private static GerenciadorAgendamento ga;
    private static GerenciadorOs gos;
    private static GerenciadorVenda gv;
    private static GerenciadorNotaFiscal gnf;
    private static GerenciadorDespesas gd;
    private static GerenciadorPonto gPonto;
    private static Relatorio relatorio;

  
    private static LoginService loginService;
    private static VerificacaoLogin verificacaoLogin;

    private static final Scanner SC = new Scanner(System.in);
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("Iniciando o sistema da Barbearia...");

       
        gf = new GerenciadorFuncionarios();
        gc = new GerenciadorClientes();
        gs = new GerenciadorServicos();
        gp = new GerenciadorProdutos();
        gas = new GerenciadorAgendamentoSecundario();
        ga = new GerenciadorAgendamento(gas);
        gos = new GerenciadorOs();
        gv = new GerenciadorVenda(gp);
        gnf = new GerenciadorNotaFiscal();
        gd = new GerenciadorDespesas();
        gPonto = new GerenciadorPonto();

       
        loginService = new LoginService(gf);
        verificacaoLogin = VerificacaoLogin.getInstance();
        relatorio = new Relatorio(gv, gd);

        
        inicializarGerenteAutomatico();

        boolean sair = false;
        while (!sair) {
            System.out.println("\nBem-vindo ao Login da Barbearia.");
            if (telaLogin()) {
                
                if (menuPrincipal()) { 
                    sair = true;
                }
            } else {
               
                sair = true;
            }
        }

        System.out.println("Sistema encerrado.");
        SC.close();
    }

    /**
     * Tenta criar e exibir as credenciais de um Gerente Padrão se não houver funcionários.
     * @return o objeto Funcionario criado, ou null caso já existam funcionários.
     */
    private static Funcionario inicializarGerenteAutomatico() {
        if (gf.getFuncionarios() == null || gf.getFuncionarios().isEmpty()) {
            final String nome = "GERENTE MASTER";
            final String cpf = "00000000000";
            final String cargo = "GERENTE";
            final String senha = "admin123";

            Funcionario gerente = new Funcionario(nome, cpf, cargo, senha, "Rua Gerente", "99999999999");
            gf.addFuncionario(gerente);
            gf.atualizarFuncionario(); // Salva as credenciais do gerente inicial

            // Exibição das credenciais, conforme solicitado
            System.out.println("\n==============================================");
            System.out.println("   CADASTRO INICIAL DO SISTEMA (GERENTE)      ");
            System.out.println("==============================================");
            System.out.println("ATENÇÃO: Este funcionário foi criado porque não havia nenhum cadastrado.");
            System.out.println("Nome: " + nome);
            System.out.println("Cargo: " + cargo);
            System.out.println("CPF para Login: " + cpf);
            System.out.println("Senha para Login: " + senha);
            System.out.println("==============================================\n");

            return gerente;
        }
        return null;
    }

    /**
     * Interface de login manual.
     * @return true se o login foi bem-sucedido, false se falhou ou se o usuário escolheu sair.
     */
    private static boolean telaLogin() {
        Funcionario usuarioLogado = null;
        int tentativas = 0;
        final int MAX_TENTATIVAS = 3;

        while (usuarioLogado == null && tentativas < MAX_TENTATIVAS) {
            System.out.println("\n--- LOGIN (Digite 0 no CPF para Encerrar o Sistema) ---");
            System.out.print("CPF: ");
            String cpf = SC.nextLine();

           
            if (cpf.equals("0")) {
                return false;
            }

            System.out.print("Senha: ");
            String senha = SC.nextLine();

            usuarioLogado = loginService.login(cpf, senha);
            if (usuarioLogado != null) {
                verificacaoLogin.login(usuarioLogado);
                return true;
            }
            tentativas++;
            if (tentativas < MAX_TENTATIVAS) {
                System.out.println("Tentativas restantes: " + (MAX_TENTATIVAS - tentativas));
            }
        }
        System.out.println("Número máximo de tentativas excedido. Encerrando.");
        return false;
    }

    /**
     * Menu Principal da aplicação.
     * @return true se o usuário escolheu SAIR do sistema, false se apenas fez logout.
     */
    private static boolean menuPrincipal() {
        int opcao;

        do {
            Funcionario f = verificacaoLogin.getUsuarioLogado();
            System.out.println("\n==============================================");
            System.out.printf("Bem-vindo(a), %s | Cargo: %s\n", f.getNome(), f.getCargo());
            System.out.println("================= MENU PRINCIPAL =================");
            System.out.println("1. Gerenciamento de Cadastros (Clientes/Produtos/Serviços/Funcionários)");
            System.out.println("2. Agendamentos e Fila de Espera");
            System.out.println("3. Ordem de Serviço (OS)");
            System.out.println("4. Controle de Ponto");
            System.out.println("5. Financeiro (Vendas/Despesas/Relatórios)");
            System.out.println("0. Sair do Sistema (Logout e Fechamento)"); // Alterado para fechar o sistema
            System.out.println("==============================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: menuCadastros(); break;
                    case 2: menuAgendamento(); break;
                    case 3: menuOrdemServico(); break;
                    case 4: menuPonto(); break;
                    case 5: menuFinanceiro(); break;
                    case 0:
                   
                        verificacaoLogin.logout();
                        System.out.println("Logout realizado. Encerrando o sistema.");
                        return true; 
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            } catch (Exception e) {
                System.err.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
                opcao = -1;
            }
        } while (verificacaoLogin.IsLogado());

        
        return false;
    }

    private static void menuCadastros() throws Exception {
        int opcao;
        do {
            System.out.println("\n========== MENU CADASTROS ==========");
            System.out.println("1. Clientes");
            System.out.println("2. Funcionários (Apenas GERENTE)");
            System.out.println("3. Produtos (Estoque)");
            System.out.println("4. Serviços");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("====================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: menuClientes(); break;
                    case 2:
                      
                        if (verificacaoLogin.getCargoUsuarioLogado().equals("GERENTE")) {
                            menuFuncionarios();
                        } else {
                            System.err.println("Acesso negado. Apenas gerentes podem gerenciar funcionários.");
                        }
                        break;
                    case 3: menuProdutos(); break;
                    case 4: menuServicos(); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("\n========== MENU CLIENTES ==========");
            System.out.println("1. Listar Todos");
            System.out.println("2. Adicionar Novo");
            System.out.println("3. Buscar por CPF");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Alterar Dados do Cliente (Nome/End/Tel/Email/CPF)");
            System.out.println("6. Organizar por Ordem Alfabética (Nome)");
            System.out.println("7. Organizar por CPF");
            System.out.println("8. Salvar Alterações no JSON");
            System.out.println("0. Voltar");
            System.out.println("===================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: gc.listarClientes(); break;
                    case 2:
                        System.out.print("Nome: "); String nome = SC.nextLine();
                        System.out.print("CPF: "); String cpf = SC.nextLine();
                        System.out.print("Email: "); String email = SC.nextLine();
                        System.out.print("Endereço: "); String endereco = SC.nextLine();
                        System.out.print("Telefone: "); String telefone = SC.nextLine();
                        gc.addCliente(new Cliente(nome, cpf, email, endereco, telefone));
                        break;
                    case 3:
                        System.out.print("Digite o CPF para buscar: ");
                        Cliente c = gc.buscarCliente(SC.nextLine());
                        if (c != null) System.out.println(c);
                        break;
                    case 4:
                        System.out.print("Digite o CPF do cliente para remover: ");
                        Cliente cRemover = gc.buscarCliente(SC.nextLine());
                        if (cRemover != null) gc.removerCliente(cRemover);
                        break;
                    case 5: menuAlterarCliente(); break;
                    case 6: gc.organizarPorOrdemAlfabetica(); gc.listarClientes(); break;
                    case 7: gc.organizarPorCpf(); gc.listarClientes(); break;
                    case 8: gc.atualizarLista(); System.out.println("Lista de clientes salva com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuAlterarCliente() {
        System.out.print("Digite o CPF do cliente para alterar: ");
        Cliente c = gc.buscarCliente(SC.nextLine());
        if (c == null) return;

        System.out.println("\n--- Alterar Dados do Cliente: " + c.getNome() + " ---");
        System.out.print("Novo Nome (deixe em branco para manter '" + c.getNome() + "'): ");
        String novoNome = SC.nextLine();
        if (!novoNome.isEmpty()) gc.alterarNomeCliente(novoNome, c);

        System.out.print("Novo CPF (deixe em branco para manter '" + c.getCpf() + "'): ");
        String novoCpf = SC.nextLine();
        if (!novoCpf.isEmpty()) gc.alterarCpfCliente(novoCpf, c);

        System.out.print("Novo Email (deixe em branco para manter '" + c.getEmail() + "'): ");
        String novoEmail = SC.nextLine();
        if (!novoEmail.isEmpty()) gc.alterarEmailCliente(novoEmail, c);

        System.out.print("Novo Endereço (deixe em branco para manter '" + c.getEndereco() + "'): ");
        String novoEndereco = SC.nextLine();
        if (!novoEndereco.isEmpty()) gc.alterarEnderecoCliente(novoEndereco, c);

        System.out.print("Novo Telefone (deixe em branco para manter '" + c.getTelefone() + "'): ");
        String novoTelefone = SC.nextLine();
        if (!novoTelefone.isEmpty()) gc.alterarTelefoneCliente(novoTelefone, c);

        System.out.println("Dados do cliente atualizados **apenas localmente**. Use a opção 'Salvar Alterações no JSON' no menu anterior.");
    }

    private static void menuFuncionarios() {
        int opcao;
        do {
            System.out.println("\n========= MENU FUNCIONÁRIOS =========");
            System.out.println("1. Listar Todos");
            System.out.println("2. Adicionar Novo");
            System.out.println("3. Buscar por CPF");
            System.out.println("4. Remover por CPF");
            System.out.println("5. Alterar Dados do Funcionário (Apenas GERENTE)");
            System.out.println("6. Salvar Alterações no JSON");
            System.out.println("0. Voltar");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: gf.listarFuncionarios(); break;
                    case 2:
                        System.out.print("Nome: "); String nome = SC.nextLine();
                        System.out.print("CPF: "); String cpf = SC.nextLine();
                        System.out.print("Cargo (GERENTE ou BARBEIRO): "); String cargo = SC.nextLine().toUpperCase();
                        System.out.print("Senha: "); String senha = SC.nextLine();
                        System.out.print("Endereço: "); String endereco = SC.nextLine();
                        System.out.print("Telefone: "); String telefone = SC.nextLine();
                        gf.addFuncionario(new Funcionario(nome, cpf, cargo, senha, endereco, telefone));
                        break;
                    case 3:
                        System.out.print("Digite o CPF para buscar: ");
                        Funcionario f = gf.buscarFuncionarioCpf(SC.nextLine());
                        if (f != null) System.out.println(f);
                        break;
                    case 4:
                        System.out.print("Digite o CPF do funcionário para remover: ");
                        Funcionario fRemover = gf.buscarFuncionarioCpf(SC.nextLine());
                        gf.removerFuncionarioCpf(fRemover);
                        break;
                    case 5: menuAlterarFuncionario(); break;
                    case 6: gf.atualizarFuncionario(); System.out.println("Lista de funcionários salva com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuAlterarFuncionario() {
        System.out.print("Digite o CPF do funcionário para alterar: ");
        Funcionario f = gf.buscarFuncionarioCpf(SC.nextLine());
        if (f == null) return;

        System.out.println("\n--- Alterar Dados do Funcionário: " + f.getNome() + " ---");

        System.out.print("Novo Nome (deixe em branco para manter '" + f.getNome() + "'): ");
        String novoNome = SC.nextLine();
        if (!novoNome.isEmpty()) gf.alterarNomeFuncionario(novoNome, f);

        System.out.print("Novo Cargo (GERENTE ou BARBEIRO, deixe em branco para manter '" + f.getCargo() + "'): ");
        String novoCargo = SC.nextLine().toUpperCase();
        if (!novoCargo.isEmpty()) gf.alterarCargoFuncionario(novoCargo, f);

        System.out.print("Nova Senha (deixe em branco para manter): ");
        String novaSenha = SC.nextLine();
        if (!novaSenha.isEmpty()) gf.alterarSenhaFuncionario(novaSenha, f);

        System.out.print("Novo CPF (deixe em branco para manter '" + f.getCpf() + "'): ");
        String novoCpf = SC.nextLine();
        if (!novoCpf.isEmpty()) gf.alterarCpfFuncionario(novoCpf, f);

        System.out.print("Novo Endereço (deixe em branco para manter '" + f.getEndereco() + "'): ");
        String novoEndereco = SC.nextLine();
        if (!novoEndereco.isEmpty()) gf.alterarEnderecoFuncionario(novoEndereco, f);

        System.out.print("Novo Telefone (deixe em branco para manter '" + f.getTelefone() + "'): ");
        String novoTelefone = SC.nextLine();
        if (!novoTelefone.isEmpty()) gf.alterarTelefoneFuncionario(novoTelefone, f);

        System.out.println("Dados do funcionário atualizados **apenas localmente**. Use a opção 'Salvar Alterações no JSON' no menu anterior.");
    }


    private static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n========== MENU PRODUTOS ==========");
            System.out.println("1. Listar Produtos (Estoque)");
            System.out.println("2. Adicionar Novo Produto");
            System.out.println("3. Buscar Produto por ID");
            System.out.println("4. Remover Produto por ID");
            System.out.println("5. Adicionar/Remover Estoque");
            System.out.println("6. Editar Preço/Fornecedor");
            System.out.println("7. Salvar Estoque no JSON");
            System.out.println("0. Voltar");
            System.out.println("===================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: gp.listarProdutos(); break;
                    case 2:
                        System.out.print("Nome do Produto: "); String nome = SC.nextLine();
                        System.out.print("Preço: "); double preco = Double.parseDouble(SC.nextLine());
                        System.out.print("Quantidade Inicial: "); int qtd = Integer.parseInt(SC.nextLine());
                        System.out.print("Fornecedor: "); String fornecedor = SC.nextLine();
                        gp.addProduto(new Produto(nome, preco, qtd, fornecedor));
                        break;
                    case 3:
                        System.out.print("Digite o ID para buscar: ");
                        Produto p = gp.buscarProdutoPorId(Integer.parseInt(SC.nextLine()));
                        if (p != null) System.out.println(p);
                        break;
                    case 4:
                        System.out.print("Digite o ID para remover: ");
                        Produto pRemover = gp.buscarProdutoPorId(Integer.parseInt(SC.nextLine()));
                        if (pRemover != null) gp.removerProdutoPorId(pRemover);
                        break;
                    case 5: menuEstoque(); break;
                    case 6: menuEditarProduto(); break;
                    case 7: gp.salvarEstoque(); System.out.println("Estoque salvo com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar números para ID, Preço e Quantidade.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuEstoque() {
        System.out.print("Digite o ID do produto: ");
        try {
            int id = Integer.parseInt(SC.nextLine());
            Produto p = gp.buscarProdutoPorId(id);
            if (p == null) return;

            System.out.println("Produto: " + p.getNome() + " | Estoque Atual: " + p.getQuantidadeEstoque());
            System.out.print("Quantidade para Adicionar (Positivo) ou Remover (Negativo): ");
            int qtd = Integer.parseInt(SC.nextLine());

            if (qtd > 0) {
                gp.adicionarEstoque(p, qtd);
                System.out.println("Estoque adicionado. Novo total: " + p.getQuantidadeEstoque());
            } else if (qtd < 0) {
                gp.removerEstoqueManual(p, Math.abs(qtd));
                System.out.println("Estoque removido. Novo total: " + p.getQuantidadeEstoque());
            } else {
                System.out.println("Nenhuma alteração de estoque realizada.");
            }
            System.out.println("Estoque atualizado **apenas localmente**. Use a opção 'Salvar Estoque no JSON' no menu anterior.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
        }
    }

    private static void menuEditarProduto() {
        System.out.print("Digite o ID do produto para editar: ");
        try {
            int id = Integer.parseInt(SC.nextLine());
            Produto p = gp.buscarProdutoPorId(id);
            if (p == null) return;

            System.out.println("Produto selecionado: " + p.getNome());
            System.out.print("Novo Preço (deixe em branco para manter R$ " + p.getPreco() + "): ");
            String novoPrecoStr = SC.nextLine();
            if (!novoPrecoStr.isEmpty()) {
                double novoPreco = Double.parseDouble(novoPrecoStr);
                gp.editarPrecoProduto(p, novoPreco);
                System.out.println("Preço atualizado para R$ " + novoPreco);
            }

            System.out.print("Novo Fornecedor (deixe em branco para manter '" + p.getFornecedor() + "'): ");
            String novoFornecedor = SC.nextLine();
            if (!novoFornecedor.isEmpty()) {
                gp.editarFornecedorProduto(p, novoFornecedor);
                System.out.println("Fornecedor atualizado para " + novoFornecedor);
            }
            System.out.println("Dados do produto atualizados **apenas localmente**. Use a opção 'Salvar Estoque no JSON' no menu anterior.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número para o ID ou preço.");
        }
    }

    private static void menuServicos() {
        int opcao;
        do {
            System.out.println("\n========== MENU SERVIÇOS ==========");
            System.out.println("1. Listar Serviços");
            System.out.println("2. Adicionar Novo Serviço");
            System.out.println("3. Buscar Serviço por ID");
            System.out.println("4. Remover Serviço por ID");
            System.out.println("5. Editar Valor/Duração");
            System.out.println("6. Salvar Serviços no JSON");
            System.out.println("0. Voltar");
            System.out.println("===================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: gs.listarServicos(); break;
                    case 2:
                        System.out.print("Tipo do Serviço: "); String tipo = SC.nextLine();
                        System.out.print("Valor: "); double valor = Double.parseDouble(SC.nextLine());
                        System.out.print("Duração (minutos): "); int duracao = Integer.parseInt(SC.nextLine());
                        GerenciadorDeEstacoes.listarEstacoes();
                        System.out.print("ID da Estação Padrão (1, 2 ou 3): ");
                        int idEstacao = Integer.parseInt(SC.nextLine());
                        EstacaoAtendimento estacao = GerenciadorDeEstacoes.buscarEstacao(idEstacao);

                        if (estacao != null) {
                            gs.addServico(new Servico(tipo, valor, duracao, estacao));
                        } else {
                            System.err.println("Estação inválida. Serviço não adicionado.");
                        }
                        break;
                    case 3:
                        System.out.print("Digite o ID para buscar: ");
                        Servico s = gs.buscarServicoId(Integer.parseInt(SC.nextLine()));
                        if (s != null) System.out.println(s);
                        break;
                    case 4:
                        System.out.print("Digite o ID para remover: ");
                        Servico sRemover = gs.buscarServicoId(Integer.parseInt(SC.nextLine()));
                        if (sRemover != null) gs.removerServico(sRemover);
                        break;
                    case 5: menuEditarServico(); break;
                    case 6: gs.salvarServicos(); System.out.println("Serviços salvos com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número para ID, Valor ou Duração.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuEditarServico() {
        System.out.print("Digite o ID do serviço para editar: ");
        try {
            int id = Integer.parseInt(SC.nextLine());
            Servico s = gs.buscarServicoId(id);
            if (s == null) return;

            System.out.println("Serviço selecionado: " + s.getTipoServico());
            System.out.print("Novo Valor (deixe em branco para manter R$ " + s.getValor() + "): ");
            String novoValorStr = SC.nextLine();
            if (!novoValorStr.isEmpty()) {
                double novoValor = Double.parseDouble(novoValorStr);
                gs.editarValorServico(s, novoValor);
                System.out.println("Valor atualizado para R$ " + novoValor);
            }

            System.out.print("Nova Duração (minutos, deixe em branco para manter " + s.getDuracaoMin() + " min): ");
            String novaDuracaoStr = SC.nextLine();
            if (!novaDuracaoStr.isEmpty()) {
                int novaDuracao = Integer.parseInt(novaDuracaoStr);
                gs.editarDuracaoServico(s, novaDuracao);
                System.out.println("Duração atualizada para " + novaDuracao + " min");
            }
            System.out.println("Dados do serviço atualizados **apenas localmente**. Use a opção 'Salvar Serviços no JSON' no menu anterior.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número para ID, Valor ou Duração.");
        }
    }


    private static void menuAgendamento() throws Exception {
        int opcao;
        do {
            System.out.println("\n========= MENU AGENDAMENTOS =========");
            System.out.println("1. Listar Todos Agendamentos");
            System.out.println("2. Listar Agendamentos de Hoje");
            System.out.println("3. Fazer Agendamento");
            System.out.println("4. Buscar Agendamento por ID");
            System.out.println("5. Alterar Status para Confirmado");
            System.out.println("6. Cancelar Agendamento");
            System.out.println("7. Buscar Composta (Data E Barbeiro)");
            System.out.println("8. Organizar por Data Mais Recente");
            System.out.println("9. Organizar por Barbeiro (Alfabética)");
            System.out.println("10. Fila de Espera (Ag. Secundário)");
            System.out.println("11. Salvar Alterações na Agenda (JSON)");
            System.out.println("0. Voltar");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: ga.listarAgendamentos(); break;
                    case 2: ga.listarAgendamentosDoDia(); break;
                    case 3: menuNovoAgendamento(); break;
                    case 4:
                        System.out.print("Digite o ID para buscar: ");
                        Agendamento a = ga.buscarAgendamentoId(Integer.parseInt(SC.nextLine()));
                        if (a != null) System.out.println(a);
                        break;
                    case 5:
                        System.out.print("Digite o ID do agendamento para confirmar: ");
                        Agendamento aConfirmar = ga.buscarAgendamentoId(Integer.parseInt(SC.nextLine()));
                        if (aConfirmar != null) ga.alterarStatusConfirmado(aConfirmar);
                        break;
                    case 6:
                        System.out.print("Digite o ID do agendamento para cancelar: ");
                        Agendamento aCancelar = ga.buscarAgendamentoId(Integer.parseInt(SC.nextLine()));
                        if (aCancelar != null) {
                            ga.alterarStatusCancelado(aCancelar);
                            gv.registrarVendaCancelamento(aCancelar);
                            System.out.println("Agendamento cancelado. Taxa de cancelamento (35%) registrada como venda.");
                            System.out.println("Não se esqueça de salvar a Agenda e as Vendas.");
                        }
                        break;
                    case 7: menuBuscaComposta(); break;
                    case 8: ga.organizarPorMaisRecentes(); ga.listarAgendamentos(); break;
                    case 9: ga.organizarPorBarbeiro(); ga.listarAgendamentos(); break;
                    case 10: menuAgendamentoSecundario(); break;
                    case 11: ga.atulizarAgenda(); System.out.println("Agenda salva com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            } catch (Exception e) {
                System.err.println("Erro ao processar agendamento: " + e.getMessage());
            }
        } while (true);
    }

    private static void menuNovoAgendamento() throws Exception {
        System.out.println("\n--- NOVO AGENDAMENTO ---");
        // Seleção de Cliente
        gc.listarClientes();
        System.out.print("Digite o ID do Cliente: ");
        Cliente cliente = gc.buscarClientePorId(Integer.parseInt(SC.nextLine()));
        if (cliente == null) return;

      
        gf.listarFuncionarios();
        System.out.print("Digite o ID do Funcionário (Barbeiro): ");
        Funcionario barbeiro = gf.buscarFuncionarioId(Integer.parseInt(SC.nextLine()));
        if (barbeiro == null) return;

   
        List<Servico> servicosSelecionados = new ArrayList<>();
        int idServico;
        do {
            gs.listarServicos();
            System.out.print("Digite o ID do Serviço (ou 0 para finalizar a seleção): ");
            idServico = Integer.parseInt(SC.nextLine());
            if (idServico != 0) {
                Servico s = gs.buscarServicoId(idServico);
                if (s != null) {
                    servicosSelecionados.add(s);
                    System.out.println(s.getTipoServico() + " adicionado.");
                } else {
                    System.out.println("Serviço não encontrado.");
                }
            }
        } while (idServico != 0);

        if (servicosSelecionados.isEmpty()) {
            System.out.println("Nenhum serviço selecionado. Agendamento cancelado.");
            return;
        }

       
        LocalDateTime dataHora = null;
        while (dataHora == null) {
            System.out.printf("Data e Hora (Formato %s, ex: 15/11/2025 10:30): ", DATE_TIME_FORMAT.toString());
            String dataHoraStr = SC.nextLine();
            try {
                dataHora = LocalDateTime.parse(dataHoraStr, DATE_TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.err.println("Formato de data/hora inválido. Tente novamente.");
            }
        }

        try {
            Agendamento novoAgendamento = new Agendamento(cliente, barbeiro, servicosSelecionados, dataHora);
            ga.addAgendamento(novoAgendamento);
            System.out.println("Agendamento criado com sucesso! Use a opção 'Salvar Alterações na Agenda (JSON)' para persistir.");
        } catch (IllegalArgumentException e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private static void menuBuscaComposta() {
        System.out.println("\n--- BUSCA COMPOSTA (Data E Barbeiro) ---");
        System.out.printf("Data (Formato %s, ex: 15/11/2025): ", DATE_FORMAT.toString());
        String dataStr = SC.nextLine();
        System.out.print("Nome do Barbeiro (ou parte do nome): ");
        String nomeBarbeiro = SC.nextLine();

        try {
            List<Agendamento> resultados = ga.buscarInterpreter(dataStr, nomeBarbeiro);
            if (resultados.isEmpty()) {
                System.out.println("Nenhum agendamento encontrado que corresponda aos critérios.");
            } else {
                System.out.println("--- Resultados da Busca Composta ---");
                resultados.forEach(System.out::println);
            }
        } catch (DateTimeParseException e) {
            System.err.println("Formato de data inválido. Use " + DATE_FORMAT.toString());
        }
    }

    private static void menuAgendamentoSecundario() throws Exception {
        int opcao;
        do {
            System.out.println("\n====== MENU FILA DE ESPERA (AG. SECUNDÁRIO) ======");
            System.out.println("1. Listar Fila de Espera");
            System.out.println("2. Adicionar à Fila (Ag. Secundário)");
            System.out.println("3. Chamar Próximo da Fila (FIFO)");
            System.out.println("4. Salvar Fila de Espera no JSON");
            System.out.println("0. Voltar");
            System.out.println("=================================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: gas.listarFila(); break;
                    case 2: menuAdicionarAgendamentoSecundario(); break;
                    case 3:
                        AgendamentoSecundario proximo = gas.chamarProximoDaFila();
                        if (proximo != null) {
                            System.out.println("Próximo cliente chamado: " + proximo.getCliente().getNome());
                            System.out.println("Fila atualizada **localmente**. Use a opção 'Salvar Fila de Espera no JSON' para persistir.");
                        }
                        break;
                    case 4: gas.salvarFila(); System.out.println("Fila de espera salva com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuAdicionarAgendamentoSecundario() throws Exception {
        System.out.println("\n--- ADICIONAR À FILA DE ESPERA ---");
        gc.listarClientes();
        System.out.print("Digite o ID do Cliente: ");
        Cliente cliente = gc.buscarClientePorId(Integer.parseInt(SC.nextLine()));
        if (cliente == null) return;

        gs.listarServicos();
        System.out.print("Digite o ID do Serviço Desejado: ");
        Servico servico = gs.buscarServicoId(Integer.parseInt(SC.nextLine()));
        if (servico == null) return;

        AgendamentoSecundario as = new AgendamentoSecundario(cliente, 0, servico);
        gas.addFila(as);
        System.out.println("Cliente adicionado à fila **localmente**. Use a opção 'Salvar Fila de Espera no JSON' no menu anterior.");
    }


    private static void menuOrdemServico() {
        int opcao;
        do {
            System.out.println("\n========== MENU ORDEM DE SERVIÇO (OS) ==========");
            System.out.println("1. Listar Todas OSs");
            System.out.println("2. Listar OSs em Aberto (AGUARDANDO/ANDAMENTO)");
            System.out.println("3. Abrir Nova OS (Manual)");
            System.out.println("4. Criar OS a partir de Agendamento (AGENDAMENTO_PENDENTE)");
            System.out.println("5. Alterar Status para EM ANDAMENTO");
            System.out.println("6. Finalizar OS (CONCLUÍDO) e Emitir Venda/Nota");
            System.out.println("7. Buscar OS por ID");
            System.out.println("8. Remover OS por ID");
            System.out.println("9. Imprimir Extrato Detalhado da OS");
            System.out.println("10. Salvar Alterações na Lista de OS (JSON)");
            System.out.println("0. Voltar");
            System.out.println("=================================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                switch (opcao) {
                    case 1: gos.listarOS(); break;
                    case 2: gos.listarOSEmAberto(); break;
                    case 3: menuAbrirOSManual(); break;
                    case 4: menuCriarOSDeAgendamento(); break;
                    case 5:
                        System.out.print("ID da OS para EM ANDAMENTO: ");
                        OrdemDeServico osAndamento = gos.buscarPorId(Integer.parseInt(SC.nextLine()));
                        if (osAndamento != null) {
                            gos.alterarStatusEmAndamento(osAndamento);
                            System.out.println("OS " + osAndamento.getIdOS() + " marcada como EM ANDAMENTO. Estação(ões) ocupada(s).");
                            System.out.println("OS atualizada **localmente**. Use a opção 'Salvar Alterações na Lista de OS (JSON)' para persistir.");
                        }
                        break;
                    case 6: menuFinalizarOS(); break;
                    case 7:
                        System.out.print("ID da OS para buscar: ");
                        OrdemDeServico osBusca = gos.buscarPorId(Integer.parseInt(SC.nextLine()));
                        if (osBusca != null) System.out.println(osBusca);
                        break;
                    case 8:
                        System.out.print("ID da OS para remover: ");
                        gos.removerOrdemServico(Integer.parseInt(SC.nextLine()));
                        System.out.println("OS removida **localmente**. Use a opção 'Salvar Alterações na Lista de OS (JSON)' para persistir.");
                        break;
                    case 9:
                        System.out.print("ID da OS para imprimir extrato: ");
                        OrdemDeServico osExtrato = gos.buscarPorId(Integer.parseInt(SC.nextLine()));
                        if (osExtrato != null) System.out.println(osExtrato.gerarExtrato());
                        break;
                    case 10: gos.atualizarLista(); System.out.println("Lista de OS salva com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuAbrirOSManual() {
        System.out.println("\n--- ABRIR NOVA OS MANUAL ---");
        gc.listarClientes();
        System.out.print("ID do Cliente: ");
        Cliente cliente = gc.buscarClientePorId(Integer.parseInt(SC.nextLine()));
        if (cliente == null) return;

        gf.listarFuncionarios();
        System.out.print("ID do Funcionário: ");
        Funcionario funcionario = gf.buscarFuncionarioId(Integer.parseInt(SC.nextLine()));
        if (funcionario == null) return;

        OrdemDeServico novaOs = gos.abrirOs(cliente, funcionario);
        if (novaOs != null) {
            System.out.println("OS ID " + novaOs.getIdOS() + " aberta com status AGUARDANDO.");
            System.out.println("OS aberta **localmente**. Use a opção 'Salvar Alterações na Lista de OS (JSON)' para persistir.");
        }
    }

    private static void menuCriarOSDeAgendamento() {
        System.out.println("\n--- CRIAR OS A PARTIR DE AGENDAMENTO ---");
        ga.listarAgendamentos();
        System.out.print("ID do Agendamento: ");
        Agendamento agendamento = ga.buscarAgendamentoId(Integer.parseInt(SC.nextLine()));

        if (agendamento != null) {
            gos.criarOSaPartirDeAgendamento(agendamento);
            System.out.println("OS criada **localmente**. Use a opção 'Salvar Alterações na Lista de OS (JSON)' para persistir.");
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }

    private static void menuFinalizarOS() {
        System.out.print("ID da OS para CONCLUIR e VENDER: ");
        try {
            int id = Integer.parseInt(SC.nextLine());
            OrdemDeServico os = gos.buscarPorId(id);

            if (os == null) {
                System.out.println("OS não encontrada.");
                return;
            }

            if (os.getStatusOs() == TipoStatusOs.ESTADO_CONCLUIDO) {
                System.out.println("OS já está CONCLUÍDA.");
                return;
            }

            System.out.print("Observações finais: ");
            String obs = SC.nextLine();

            gos.alterarStatusConcluido(os, obs);
            System.out.println("OS " + id + " CONCLUÍDA. Estação(ões) liberada(s).");
            System.out.println("OS atualizada **localmente**. Use a opção 'Salvar Alterações na Lista de OS (JSON)'.");

            System.out.print("Forma de Pagamento (Ex: Cartão, Dinheiro, Pix): ");
            String formaPagamento = SC.nextLine();

            // 1. Registra Venda
            gv.registrarVendaOS(os, formaPagamento);
            gv.salvarVendas(); // Vendas são salvas imediatamente por ser uma transação finalizada.

            // 2. Atualiza Estoque (removendo produtos vendidos)
            gp.atualizarEstoquePorVenda(gv.buscarVendaPorId(gv.geradorIdVenda() - 1)); // Pega a última venda registrada
            gp.salvarEstoque(); // Estoque é salvo imediatamente.

            // 3. Emite Nota Fiscal
            NotaFiscal nf = new NotaFiscal(gv.buscarVendaPorId(gv.geradorIdVenda() - 1)); // Passa a venda para a Nota
            gnf.addNotaFiscal(nf);
            gnf.salvarNotasFiscais(); // Notas Fiscais são salvas imediatamente.

            System.out.println("\n--- Nota Fiscal Emitida ---");
            System.out.println(nf.gerarComprovanteDetalhado());
            System.out.println("\nOBS: Vendas, Notas Fiscais e Estoque foram salvos no JSON.");
            System.out.println("Lembre-se de salvar a lista principal de OS.");

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número para o ID.");
        }
    }

    private static void menuPonto() {
        int opcao;
        do {
            System.out.println("\n========== MENU CONTROLE DE PONTO ==========");
            System.out.println("1. Bater Ponto de ENTRADA");
            System.out.println("2. Bater Ponto de SAÍDA");
            System.out.println("3. Listar Todos Registros de Ponto");
            System.out.println("4. Salvar Alterações de Ponto (JSON)");
            System.out.println("0. Voltar");
            System.out.println("============================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                Funcionario fLogado = verificacaoLogin.getUsuarioLogado();
                if (fLogado == null) {
                    System.err.println("Nenhum funcionário logado para bater o ponto.");
                    return;
                }

                switch (opcao) {
                    case 1:
                        gPonto.baterEntrada(fLogado);
                        System.out.println("Ponto de entrada registrado **localmente**. Use a opção 'Salvar Alterações de Ponto (JSON)' para persistir.");
                        break;
                    case 2:
                        gPonto.baterSaida(fLogado);
                        System.out.println("Ponto de saída registrado **localmente**. Use a opção 'Salvar Alterações de Ponto (JSON)' para persistir.");
                        break;
                    case 3: gPonto.listarPontos(); break;
                    case 4: gPonto.atulizarPontos(); System.out.println("Registros de ponto salvos com sucesso no JSON!"); break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            }
        } while (true);
    }


    private static void menuFinanceiro() {
        int opcao;
        String cargo = verificacaoLogin.getCargoUsuarioLogado();
        boolean isGerente = "GERENTE".equals(cargo);

        do {
            System.out.println("\n========== MENU FINANCEIRO ==========");
            // Restrição de acesso para opção 1 (Gerenciar Despesas)
            System.out.println("1. Gerenciar Despesas" + (isGerente ? "" : " (Somente GERENTE)"));
            System.out.println("2. Listar Vendas Registradas");
            System.out.println("3. Listar Notas Fiscais Emitidas");
            // Restrição de acesso para opção 4 (Gerar Relatórios)
            System.out.println("4. Gerar Relatórios Financeiros" + (isGerente ? "" : " (Somente GERENTE)"));
            System.out.println("5. Salvar Vendas e Notas Fiscais (JSON)");
            System.out.println("0. Voltar");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());

                switch (opcao) {
                    case 1:
                        if (isGerente) {
                            menuGerenciarDespesas();
                        } else {
                            System.err.println("Acesso negado. Apenas GERENTES podem gerenciar despesas.");
                        }
                        break;
                    case 2: gv.listarVendas(); break;
                    case 3: gnf.listarNotasFiscais(); break;
                    case 4:
                        if (isGerente) {
                            menuRelatorios();
                        } else {
                            System.err.println("Acesso negado. Apenas GERENTES podem gerar relatórios.");
                        }
                        break;
                    case 5:
                        gv.salvarVendas();
                        gnf.salvarNotasFiscais();
                        System.out.println("Vendas e Notas Fiscais salvas no JSON.");
                        break;
                    case 0: return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                opcao = -1;
            }
        } while (true);
    }

    private static void menuGerenciarDespesas() {
        // Redundância de checagem, já que a navegação é controlada no menuFinanceiro.
        // Apenas para garantir.
        if (!"GERENTE".equals(verificacaoLogin.getCargoUsuarioLogado())) {
            System.err.println("Acesso negado. Apenas GERENTES.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n========== MENU DESPESAS ==========");
            System.out.println("1. Registrar Despesa de Salário");
            System.out.println("2. Registrar Despesa de Material");
            System.out.println("3. Registrar Despesa de Limpeza");
            System.out.println("4. Registrar Despesa de Cortesia");
            System.out.println("5. Listar Todas Despesas (via Relatório)");
            System.out.println("6. Salvar Despesas (JSON)");
            System.out.println("0. Voltar");
            System.out.println("===================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());

                if (opcao >= 1 && opcao <= 4) {
                    System.out.print("Valor da Despesa: R$ ");
                    double valor = Double.parseDouble(SC.nextLine());

                    switch (opcao) {
                        case 1: gd.registrarDespesaSalario(valor); break;
                        case 2:
                            System.out.print("Descrição do Material: "); String descMat = SC.nextLine();
                            gd.registrarDespesaMaterial(descMat, valor);
                            break;
                        case 3: gd.registrarDespesaLimpeza(valor); break;
                        case 4:
                            System.out.print("Descrição da Cortesia: "); String descCort = SC.nextLine();
                            gd.registrarDespesaCortesias(descCort, valor);
                            break;
                    }
                    System.out.println("Despesa registrada **localmente**. Use a opção 'Salvar Despesas (JSON)' para persistir.");
                } else if (opcao == 5) {
                    System.out.println("Digite o Mês (1-12) e Ano para listar:");
                    System.out.print("Mês: "); int mes = Integer.parseInt(SC.nextLine());
                    System.out.print("Ano: "); int ano = Integer.parseInt(SC.nextLine());
                    relatorio.gerarRelatorioDespesas(ano, mes);
                } else if (opcao == 6) {
                    gd.salvarDespesas(); System.out.println("Despesas salvas com sucesso no JSON!");
                } else if (opcao == 0) {
                    return;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número para as opções, valores, mês e ano.");
            }
        } while (true);
    }

    private static void menuRelatorios() {
        // Redundância de checagem, já que a navegação é controlada no menuFinanceiro.
        // Apenas para garantir.
        if (!"GERENTE".equals(verificacaoLogin.getCargoUsuarioLogado())) {
            System.err.println("Acesso negado. Apenas GERENTES.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n========== MENU RELATÓRIOS ==========");
            System.out.println("1. Balanço Mensal (Vendas - Despesas)");
            System.out.println("2. Relatório Detalhado de Vendas");
            System.out.println("3. Relatório Detalhado de Despesas");
            System.out.println("0. Voltar");
            System.out.println("=====================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(SC.nextLine());
                if (opcao >= 1 && opcao <= 3) {
                    System.out.println("Digite o Mês (1-12) e Ano para o relatório:");
                    System.out.print("Mês: "); int mes = Integer.parseInt(SC.nextLine());
                    System.out.print("Ano: "); int ano = Integer.parseInt(SC.nextLine());

                    switch (opcao) {
                        case 1: relatorio.gerarBalancoMensal(ano, mes); break;
                        case 2: relatorio.gerarRelatorioVendas(ano, mes); break;
                        case 3: relatorio.gerarRelatorioDespesas(ano, mes); break;
                    }
                } else if (opcao == 0) {
                    return;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número para as opções, mês e ano.");
                opcao = -1;
            }
        } while (true);
    }
}
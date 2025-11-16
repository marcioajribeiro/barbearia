/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barbearia;


import agendamento.GerenciadorAgendamento;
import agendamento.GerenciadorAgendamentoSecundario;
import comparator.ClienteCpfComparator;
import comparator.ClienteNomeComparator;
import controller.GerenciadorClientes;
import controller.GerenciadorFuncionarios;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import estacoesatendimento.GerenciadorDeEstacoes;
import financeiro.*;
import ordemdeservico.GerenciadorOs;
import ordemdeservico.OrdemDeServico;

import java.time.LocalDateTime;
import java.util.*;



/**
 *
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class QuestoesRespondidas {

    private static int contadorOS=0;

    public static void registrarNovaOs(){
        contadorOS++;
    }

    public static int getQuantidadeInstanciasOS() {
        return contadorOS;
    }

    public static int getTotalOrdensDeServico() {
        GerenciadorOs gos = new GerenciadorOs();

        List<OrdemDeServico> listaOS = gos.getListaOs();

        return (listaOS != null) ? listaOS.size() : 0;
    }

    public static Cliente findCliente(List<Cliente> lista, Cliente clienteProcurado, Comparator<Cliente> comparador) {
        Iterator<Cliente> iterator = lista.iterator();
        while (iterator.hasNext()) {
            Cliente clienteAtual = iterator.next();
            if (comparador.compare(clienteAtual, clienteProcurado) == 0) {
                return clienteAtual;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        GerenciadorNotaFiscal gnf = new GerenciadorNotaFiscal();
        GerenciadorServicos gs = new GerenciadorServicos();
        GerenciadorProdutos gp = new GerenciadorProdutos();
        GerenciadorVenda gv = new GerenciadorVenda(gp);
        GerenciadorAgendamentoSecundario gas = new GerenciadorAgendamentoSecundario();
        GerenciadorAgendamento ga = new GerenciadorAgendamento(gas);
        GerenciadorClientes gc = new GerenciadorClientes();
        GerenciadorFuncionarios gf = new GerenciadorFuncionarios();
        GerenciadorDespesas gd = new GerenciadorDespesas();
        GerenciadorOs gos = new GerenciadorOs();

        //Questão 01: {
        System.out.println("===== Questão 01 =====");
        System.out.println("Diagrama feito");
        // }
        //Questão 02: {
        System.out.println("====Questão 02====");
        System.out.println("Acessar a classe Funcionario, atributo cargo ( Linha 17 )");
        //}
        //Questão 03: {
        System.out.println("====Questão 03====");
        System.out.println("Acessar qualquer entidades");
        // }
        //Questão 04: {
        System.out.println("====Questão 04====");
        System.out.println("Acessar a classe Funcionário ou Cliente em seus construtores");
        // }
        //Questão 05: {
        System.out.println("====Questão 05====");
        System.out.println("Acessar a classe GerenciadorDeEstações ( linha 12 a 19 )");
        // }
        //Questão 06: {
        System.out.println("====Questão 06====");
        Funcionario f = new Funcionario("Rafael", "15322402608", "ADMIN", "admin123", "Rua Barao do Rio Branco", "38999279932");
        gf.addFuncionario(f);
        gf.alterarCargoFuncionario("BARBEIRO", f);
        gf.alterarCpfFuncionario("10936304693", f);
        gf.alterarEnderecoFuncionario("Rua Diamantina", f);
        gf.alterarNomeFuncionario("Marcio", f);
        gf.alterarSenhaFuncionario("barb123", f);
        gf.alterarTelefoneFuncionario("38999184025", f);
        gf.removerFuncionarioCpf(f);
        //}

        //Questão 07: {
        System.out.println("====Questão 07====");
        Cliente client = new Cliente("Rafael", "15322402608", "rafinhamartins@gmail.com", "Rua Barao do Rio Branco", "38999279932");
        gc.addCliente(client);
        gc.alterarCpfCliente("10936304693", client);
        gc.alterarEmailCliente("marcio@gmail.com", client);
        gc.alterarEnderecoCliente("Rua Diamantina", client);
        gc.alterarNomeCliente("Marcio", client);
        gc.alterarTelefoneCliente("38999184025", client);
        gc.removerCliente(client);
        //}

        //Questão 08: {
        System.out.println("====Questão 08====");
        Cliente clienteOs = new Cliente("Rafael", "15322402608", "rafinhamartins@gmail.com", "Rua Barao do Rio Branco", "38999279932");
        Funcionario funcionarioOs = new Funcionario("Rafael", "15322402608", "ADMIN", "admin123", "Rua Barao do Rio Branco", "38999279932");
        gos.abrirOs(clienteOs, funcionarioOs);
        gos.imprimirOsDeCliente(clienteOs);
        // }
        //Questão 09: {
        System.out.println("====Questão 09====");
        System.out.println("Mostrar Gerenciador Genérico");
        System.out.println("Listas dinamicas carregadas: ");
        System.out.println("Ordem de serviço: " + gos.getListaOs().size() + " Itens");
        System.out.println("Clientes: " + gc.getClientes().size() + " Itens");
        System.out.println("Estoque: " + gp.getProduto().size() + " Itens");

        // }
        //Questão 10: {
        System.out.println("====Questão 10====");
        String extrato = gos.getListaOs().get(0).gerarExtrato();
        System.out.println("Extrato da primeira Ordem de Servico gerado e salvo" );
        System.out.println(extrato);

        // }
        //Questão 11: {
        System.out.println("====Questão 11====");
        System.out.println("Variáveis implementadas na classe servico");
        /*
       Usar um atributo private static com getter e setter é melhor para o encapsulamento, porque só a própria
       classe controla o valor e qualquer acesso por métodos específicos, garantindo mais segurança e
       organização. A desvantagem é que exige mais código e torna o acesso menos direto. Já o uso de protected static
       deixa o código mais simples, pois permite que subclasses e classes do mesmo pacote acessem e modifiquem o
       valor diretamente, sem métodos. Porém, isso reduz o controle e aumenta o risco de alterações indevidas,
       prejudicando a segurança e a manutenção do sistema.
         */

        // }
        //Questão 12: {
        System.out.println("====Questão 12====");
        System.out.println("Contagem inicial: " + getTotalOrdensDeServico());
        gos.addOrdemServico(new OrdemDeServico(clienteOs , funcionarioOs, new ArrayList<>(), new ArrayList<>(), LocalDateTime.now()));
        System.out.println("Contagem final: " + getTotalOrdensDeServico());

        // }
        //Questão 13: {
        System.out.println("====Questão 13 e 18====");
        System.out.println("====Questão 13====");
        System.out.println("Mostrar pasta comparator");

        // }
        //Questão 14: {
        System.out.println("====Questão 14====");
        System.out.println("Mostrar Gerenciador generico");
        // }
        //Questão 15: {
        System.out.println("====Questão 15====");
        System.out.println("Classe agendamento secundario e suas funções em gerenciador");
        // }

        //Questão 16: {
        //Instaciar um iterator para a arraylist de pessoas/funcionario/cliente (qual estiver usando)
        //Fazer testes no main em pecorrer o arraylist com chamadas usando o código:
        //while(iterator.hasnext())
        //{
        //imprimir(iterator.next());
        //}
        //
        //Explicar como isso está acontecendo.
        //Qual relação do código acima com o foreach em java?



        System.out.println("====Questão 16====");
        System.out.println("tem q ver com os cara");
        // }


        //Apresentar no main testes do comparator implementado.
        //
        //Utilizar e apresentar no main a aplicação do método sort da classe collections
        // passando o comparator criado para ordenar a lista de pessoas/usuario/cliente
        // (qual estiver usando) com dois paramêtros diferentes. Ou seja, rodar duas vezes.//
        //Questão 17: {
        System.out.println("====Questão 17====");
        System.out.println("--- Iteraçao usando Iterator (while/hasNext/next) ---");

        Iterator<Cliente> iteratorClientes = gc.getClientes().iterator();

        while (iteratorClientes.hasNext()) {
            Cliente clienteAtual = iteratorClientes.next();
            System.out.println("ID: " + clienteAtual.getIdCliente() + " | Nome: " + clienteAtual.getNome());
        }

        System.out.println();

        System.out.println("--- Iteraçao usando laço for-each (Enhanced For Loop) ---");
        for (Cliente clienteAtual : gc.getClientes()) {
            System.out.println("ID: " + clienteAtual.getIdCliente() + " | Nome: " + clienteAtual.getNome());
        }

        //O Iterator permite percorrer uma lista passo a passo, usando hasNext() para verificar
        // se há próximos elementos e next() para acessá-los. Já o foreach faz o mesmo, porém de
        // forma simplificada e automática. Ou seja, o foreach utiliza internamente um Iterator;
        // ele apenas deixa o código mais limpo e fácil de usar.



        // }
        //Questão 18: {
        System.out.println("====Questão 18====");
        List<Cliente> clientesPraOrdenar = new ArrayList<>();
        clientesPraOrdenar.add(new Cliente("Marcos", "11122211100",  "marcos@gmail.com" , "Rua das mercês", "99184025"));
        clientesPraOrdenar.add(new Cliente("Ana", "33322211100",  "ana@gmail.com" , "Rua Diamantina", "99184025"));
        clientesPraOrdenar.add(new Cliente("Bruno", "22233311100","bruno@gmail.com" , "Rua Curvelo", "99184025"));
        clientesPraOrdenar.add(new Cliente("Ricardo", "77788811100", "ricardo@gmail.com", "Rua Lavras", "989001122"));
        clientesPraOrdenar.add(new Cliente("Elaine", "66644411100", "elaine@gmail.com", "Rua Itamarandiba", "987112233"));


        System.out.println("=================Implementação do comparator por nome=====================");
        System.out.println("Antes da ordenação: ");
        clientesPraOrdenar.forEach(c -> System.out.println(" -> " + c.getNome()));
        Collections.sort(clientesPraOrdenar, new ClienteNomeComparator());
        System.out.println("Depois da ordenação: ");
        clientesPraOrdenar.forEach(c -> System.out.println(" -> " + c.getNome()));

        System.out.println("================Implementação do comparator por cpf==================");
        System.out.println("Antes da ordenação: ");
        clientesPraOrdenar.forEach(c -> System.out.println(" -> " + c.getCpf()));
        Collections.sort(clientesPraOrdenar, new ClienteCpfComparator());
        System.out.println("Depois da ordenação: ");
        clientesPraOrdenar.forEach(c -> System.out.println(" -> " + c.getCpf()));

        // }

        // }
        //Questão 19: {
        System.out.println("====Questão 19====");
        Cliente clienteBuscaFind = clientesPraOrdenar.get(0);
        System.out.println("Nome: " + clienteBuscaFind.getNome());
        Cliente resultadoFind = findCliente(clientesPraOrdenar, clienteBuscaFind, new ClienteNomeComparator());
        System.out.println("Resultado do nosso find(): " + (resultadoFind != null ? resultadoFind.getNome() : "Não encontrado"));


        Collections.sort(clientesPraOrdenar, new ClienteCpfComparator());
        int indice = Collections.binarySearch(clientesPraOrdenar, clienteBuscaFind, new ClienteCpfComparator());
        System.out.println("Resultado do binarySearch(): " + (indice >= 0 ? clientesPraOrdenar.get(indice).getNome() + " encontrado no índice " + indice : "Não encontrado"));
        System.out.println();

        //
        //- O método find() realiza uma busca linear. Ele percorre os elementos
        //   um por um, comparando a chave até encontrar o item desejado.
        //   Quando um Comparator é utilizado, ele define exatamente como a
        //   comparação entre os objetos deve ser feita. Ou seja, o Comparator
        //   determina o critério de igualdade ou ordenação que o find() usa
        //   para decidir se o elemento atual é o que está sendo procurado.
        //   É simples, mas pode ser mais lento em listas grandes, pois analisa
        //   cada posição sequencialmente.
        //         *
        // - O método binarySearch() realiza uma busca binária. Para funcionar,
        //   a lista deve estar previamente ordenada usando o mesmo Comparator
        //   que será utilizado na busca. Ele verifica o elemento do meio da lista
        //   e, dependendo da comparação, descarta metade da lista a cada passo.
        //   Isso torna a busca muito mais eficiente, reduzindo drasticamente o
        //   número de comparações.
        //

        // }


        //Questão 20: {
        Funcionario barbeiro = gf.buscarFuncionarioId(1); // Usando o Gerente Master (ID 1 do JSON)
        if (barbeiro == null) {
            System.err.println("ERRO: Funcionário (ID 1) não encontrado. Abortando Questão 20.");
        } else {

            Servico corte = gs.buscarServicoId(1); // Serviço de Corte (ID 1 do JSON)
            Servico barba = gs.buscarServicoId(2); // Serviço de Barba (ID 2 do JSON)

            // Adiciona/Verifica um produto de teste (ID 100) e garante estoque
            Produto gel = gp.buscarProdutoPorId(100);
            if (gel == null) {
                gel = new Produto("Gel Fixador", 5.0, 50, "Fornecedor Teste");
                gel.setIdProduto(100);
                gp.addProduto(gel);
            }
            gp.removerEstoque(gel, gel.getQuantidadeEstoque()); // Zera o estoque
            gp.adicionarEstoque(gel, 100); // Adiciona 100 ao estoque

            if (corte == null || barba == null) {
                System.err.println("ERRO: Serviços (Corte/Barba) não encontrados. Verifique JsonServico.json.");
            } else {

                // Lista de clientes para a simulação
                List<Cliente> clientesSimulacao = new ArrayList<>();
                clientesSimulacao.add(new Cliente("Cliente A", "11111111111", "a@test.com", "Rua 1", "99991111"));
                clientesSimulacao.add(new Cliente("Cliente B", "22222222222", "b@test.com", "Rua 2", "99992222"));
                clientesSimulacao.add(new Cliente("Cliente C", "33333333333", "c@test.com", "Rua 3", "99993333"));
                clientesSimulacao.add(new Cliente("Cliente D", "44444444444", "d@test.com", "Rua 4", "99994444"));
                clientesSimulacao.add(new Cliente("Cliente E", "55555555555", "e@test.com", "Rua 5", "99995555"));
                clientesSimulacao.add(new Cliente("Cliente F", "66666666666", "f@test.com", "Rua 6", "99996666"));
                clientesSimulacao.add(new Cliente("Cliente G", "77777777777", "g@test.com", "Rua 7", "99997777"));
                clientesSimulacao.add(new Cliente("Cliente H", "88888888888", "h@test.com", "Rua 8", "99998888"));
                clientesSimulacao.add(new Cliente("Cliente I", "99999999999", "i@test.com", "Rua 9", "99999999"));
                clientesSimulacao.add(new Cliente("Cliente J", "10101010101", "j@test.com", "Rua 10", "99990000"));

                for (int i = 0; i < clientesSimulacao.size() -9; i++) {
                    Cliente cliente = clientesSimulacao.get(i);
                    String formaPagamento = (i % 2 == 0) ? "Cartão" : "Pix";

                    System.out.printf("\n\n--- Processando Cliente %d: %s ---\n", i + 1, cliente.getNome());

                    // 1. CADASTRO DO CLIENTE
                    gc.addCliente(cliente);
                    System.out.println("1. Cliente Cadastrado (ID: " + cliente.getIdCliente() + ").");

                    // Define serviços e produtos específicos para este cliente
                    List<Servico> servicosOS = new ArrayList<>();
                    List<Produto> produtosOS = new ArrayList<>();

                    if (i % 3 == 0) { // Cliente a cada 3 faz Corte + Barba + Gel
                        servicosOS.add(corte);
                        servicosOS.add(barba);
                        produtosOS.add(gel); // Baixa de 1 unidade de Gel
                        System.out.println("Serviços/Produtos: Corte + Barba + Gel.");
                    } else { // Outros fazem apenas Corte
                        servicosOS.add(corte);
                        System.out.println("Serviços/Produtos: Corte.");
                    }


                    OrdemDeServico os = new OrdemDeServico(cliente, barbeiro, produtosOS, servicosOS, LocalDateTime.now());
                    os.setIdOS(gos.geradorIdOS());

                    double valorTotalOS = servicosOS.stream().mapToDouble(Servico::getValor).sum() + produtosOS.stream().mapToDouble(Produto::getPreco).sum();
                    os.setValorTotal(valorTotalOS);
                    gos.addOrdemServico(os);
                    System.out.printf("2. OS Aberta (ID: %d) - Status: %s | Valor Total: R$ %.2f\n", os.getIdOS(), os.getStatusOs(), os.getValorTotal());


                    gos.alterarStatusEmAndamento(os);
                    System.out.println("3. OS Atualizada - Status: " + os.getStatusOs());

                    GerenciadorDeEstacoes.ocuparEstacao(corte.getEstacaoUsada().getId());
                    System.out.println("Estação " + corte.getEstacaoUsada().getId() + " ocupada.");


                    String observacao = "Atendimento concluído. Cliente satisfeito com a " + formaPagamento;
                    gos.alterarStatusConcluido(os, observacao);
                    System.out.println("4. OS Concluída - Status: " + os.getStatusOs() + " | Estação(ões) liberada(s).");
                    GerenciadorDeEstacoes.liberarEstacao(corte.getEstacaoUsada().getId());




                    gv.registrarVendaOS(os, formaPagamento);
                    Venda venda = gv.buscarVendaPorId(gv.geradorIdVenda() - 1);
                    System.out.println("5. Venda Registrada (ID Venda: " + venda.getIdVenda() + ").");

                    // b) Atualiza Estoque (baixa de produtos)
                    if (!produtosOS.isEmpty()) {
                        int estoqueAntes = gp.buscarProdutoPorId(gel.getIdProduto()).getQuantidadeEstoque();
                        gp.atualizarEstoquePorVenda(venda); // Remove 1 unidade de Gel
                        int estoqueDepois = gp.buscarProdutoPorId(gel.getIdProduto()).getQuantidadeEstoque();
                        System.out.printf("6. Baixa de Estoque para Produto: %s. Antes: %d | Depois: %d.\n", gel.getNome(), estoqueAntes, estoqueDepois);
                    } else {
                        System.out.println("6. Nenhuma baixa de estoque necessária.");
                    }

                    NotaFiscal nf = new NotaFiscal(venda);
                    gnf.addNotaFiscal(nf);
                    System.out.println("7. Nota Fiscal Emitida (ID NF: " + nf.getIdNotaFiscal() + ").");
                    System.out.println(nf.gerarComprovanteDetalhado().trim());
                }

                // --- PERSISTÊNCIA DOS DADOS FINAIS ---
                gos.atualizarLista();
                gc.atualizarLista();
                gp.salvarEstoque();
                gv.salvarVendas();
                gnf.salvarNotasFiscais();
            }
        }
        // }

        //}


    }



}

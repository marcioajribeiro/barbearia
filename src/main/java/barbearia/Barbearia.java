package barbearia;


import agendamento.Agendamento;
import agendamento.GerenciadorAgendamento;
import agendamento.GerenciadorAgendamentoSecundario;
import agendamento.StatusAgendamento;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Servico;
import estacoesatendimento.EstacaoAtendimento;
import estacoesatendimento.GerenciadorDeEstacoes;
import financeiro.GerenciadorVenda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;






/**
 *
 * @author MARCIO JUNIOR
 */
public class Barbearia {

    public static void main(String[] args) throws Exception  {

        GerenciadorServicos gs = new GerenciadorServicos();
        GerenciadorProdutos gp = new GerenciadorProdutos();
        GerenciadorVenda gv = new GerenciadorVenda(gp);
        GerenciadorAgendamentoSecundario gas = new GerenciadorAgendamentoSecundario();
        GerenciadorAgendamento ga = new GerenciadorAgendamento(gas);

        Cliente c1 = new Cliente("João Silva", "123.456.789-10", "joao@gmail.com",
                "Rua A, 100", "389999999");
        Cliente c2 = new Cliente("Pedro Santos", "111.222.333-44", "pedro@gmail.com",
                "Rua B, 200", "389912345");
        Cliente c3 = new Cliente("Lucas Pereira", "555.666.777-88", "lucas@gmail.com",
                "Rua C, 300", "389934567");
        Cliente c4 = new Cliente("Marcos Lima", "999.888.777-66", "marcos@gmail.com",
                "Rua D, 400", "389923456");
        Cliente c5 = new Cliente("Eduardo Alves", "444.333.222-11", "edu@gmail.com",
                "Rua E, 500", "389987654");

// Barbeiros
        Funcionario f1 = new Funcionario("Carlos Barbeiro", "222.333.444-55",
                "Barbeiro", "senha1", "Centro", "389900011");
        Funcionario f2 = new Funcionario("Rafael Corte", "111.222.333-00",
                "Barbeiro", "senha2", "Centro", "389900022");

// Serviços
        Servico corte = new Servico("Corte de Cabelo", 35.0, 30, GerenciadorDeEstacoes.buscarEstação(1));
        Servico barba = new Servico("Barba", 20.0, 20, GerenciadorDeEstacoes.buscarEstação(1));
        Servico sobrancelha = new Servico("Sobrancelha", 15.0, 10, GerenciadorDeEstacoes.buscarEstação(1));
        Servico combo = new Servico("Corte + Barba", 50.0, 50, GerenciadorDeEstacoes.buscarEstação(1));
        List<Servico> listaServico = new ArrayList<>();
        listaServico.add(corte);


// Agendamentos
        Agendamento a1 = new Agendamento( c1, f1,listaServico, LocalDateTime.now().plusHours(1));

        Agendamento a2 = new Agendamento( c2, f1,listaServico, LocalDateTime.now().plusHours(2));

        Agendamento a3 = new Agendamento( c3, f2,
                listaServico, LocalDateTime.now().plusHours(3));

        Agendamento a4 = new Agendamento( c4, f2,
                listaServico, LocalDateTime.now().plusDays(1));

        Agendamento a5 = new Agendamento( c1, f1,
                listaServico, LocalDateTime.now().plusDays(1).plusHours(1));

        Agendamento a6 = new Agendamento( c5, f2,
                Arrays.asList(corte), LocalDateTime.now().plusDays(2));

        Agendamento a7 = new Agendamento( c3, f1,
                Arrays.asList(barba, sobrancelha), LocalDateTime.now().plusDays(2).plusHours(2));

        Agendamento a8 = new Agendamento( c4, f1,
                Arrays.asList(corte), LocalDateTime.now().plusDays(3));

        Agendamento a9 = new Agendamento( c5, f2,
                Arrays.asList(combo), LocalDateTime.now().plusDays(3).plusHours(3));

        Agendamento a10 = new Agendamento( c2, f2,
                Arrays.asList(corte, sobrancelha), LocalDateTime.now().plusDays(4));

        ga.addAgendamento(a1);
        ga.addAgendamento(a2);
        ga.addAgendamento(a3);
        ga.addAgendamento(a4);
        ga.addAgendamento(a5);
        ga.addAgendamento(a6);
        ga.addAgendamento(a7);
        ga.addAgendamento(a8);
        ga.addAgendamento(a9);
        ga.addAgendamento(a10);


    }






}

package barbearia;


import comparator.ClienteCpfComparator;
import comparator.ClienteNomeComparator;
import controller.GerenciadorClientes;
import entidades.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questao18 {


    public static void main(String[] args) {
        List<Cliente> clientesPraOrdenar = new ArrayList<>();
        clientesPraOrdenar.add(new Cliente("Marcos", "11122211100",  "marcos@gmail.com" , "Rua das mercês", "99184025"));
        clientesPraOrdenar.add(new Cliente("Ana", "33322211100",  "ana@gmail.com" , "Rua Diamantina", "99184025"));
        clientesPraOrdenar.add(new Cliente("Bruno", "22233311100","bruno@gmail.com" , "Rua Curvelo", "99184025"));
        clientesPraOrdenar.add(new Cliente("Ricardo", "77788811100", "ricardo@gmail.com", "Rua Lavras", "989001122"));
        clientesPraOrdenar.add(new Cliente("Elaine", "66644411100", "elaine@gmail.com", "Rua Itamarandiba", "987112233"));

        System.out.println("====Questão 18====");
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

    }
}

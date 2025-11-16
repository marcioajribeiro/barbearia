package barbearia;

import comparator.ClienteCpfComparator;
import comparator.ClienteNomeComparator;
import entidades.Cliente;

import java.util.*;

public class Questao19 {
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
        List<Cliente> clientesPraOrdenar = new ArrayList<>();
        clientesPraOrdenar.add(new Cliente("Marcos", "11122211100",  "marcos@gmail.com" , "Rua das mercês", "99184025"));
        clientesPraOrdenar.add(new Cliente("Ana", "33322211100",  "ana@gmail.com" , "Rua Diamantina", "99184025"));
        clientesPraOrdenar.add(new Cliente("Bruno", "22233311100","bruno@gmail.com" , "Rua Curvelo", "99184025"));
        clientesPraOrdenar.add(new Cliente("Ricardo", "77788811100", "ricardo@gmail.com", "Rua Lavras", "989001122"));
        clientesPraOrdenar.add(new Cliente("Elaine", "66644411100", "elaine@gmail.com", "Rua Itamarandiba", "987112233"));


        System.out.println("====Questão 19====");
        Cliente clienteBuscaFind = clientesPraOrdenar.get(0);
        System.out.println("Nome: " + clienteBuscaFind.getNome());
        Cliente resultadoFind = findCliente(clientesPraOrdenar, clienteBuscaFind, new ClienteNomeComparator());
        System.out.println("Resultado do nosso find(): " + (resultadoFind != null ? resultadoFind.getNome() : "Não encontrado"));


        Collections.sort(clientesPraOrdenar, new ClienteCpfComparator());
        int indice = Collections.binarySearch(clientesPraOrdenar, clienteBuscaFind, new ClienteCpfComparator());
        System.out.println("Resultado do binarySearch(): " + (indice >= 0 ? clientesPraOrdenar.get(indice).getNome() + " encontrado no índice " + indice : "Não encontrado"));
        System.out.println();
    }
}

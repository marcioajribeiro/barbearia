/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package barbearia;

import agendamento.GerenciadorAgendamento;
import controller.GerenciadorClientes;
import controller.GerenciadorProdutos;
import controller.GerenciadorServicos;
import entidades.Cliente;
import financeiro.GerenciadorVenda;

/**
 *
 * @author MARCIO JUNIOR
 */
public class testeComparator {
    
     public static void main(String[] args) throws Exception  {
        GerenciadorClientes gc = new GerenciadorClientes();
     

        Cliente c1 = new Cliente(
    "João da Silva",
    "123.456.789-00",
    "joao@gmail.com",
    "Rua das Flores, 123",
    "(38) 99999-0001"
);

    Cliente c2 = new Cliente(
    "Maria Oliveira",
    "987.654.321-00",
    "maria.oliveira@gmail.com",
    "Av. Central, 200",
    "(38) 99999-0002"
);

Cliente c3 = new Cliente(
    "Pedro Santos",
    "456.789.123-00",
    "pedro.santos@yahoo.com",
    "Rua Nova, 45",
    "(38) 99999-0003"
);

Cliente c4 = new Cliente(
    "Ana Costa",
    "159.753.486-00",
    "ana.costa@hotmail.com",
    "Praça da Matriz, 10",
    "(38) 99999-0004"
);

Cliente c5 = new Cliente(
    "Carlos Pereira",
    "258.369.147-00",
    "carlos.pereira@gmail.com",
    "Rua da Independência, 987",
    "(38) 99999-0005"
);

    gc.addCliente(c1); 
    gc.addCliente(c2);
    gc.addCliente(c3);
    gc.addCliente(c4);
    gc.addCliente(c5);
    
    gc.OrdenarOrdemAlfabetica();

        
     }
}

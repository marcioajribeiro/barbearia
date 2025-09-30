package Barbearia;

import Controller.*;
import Entidades.Cliente;




/**
 *
 * @author MARCIO JUNIOR
 */
public class Barbearia {

    public static void main(String[] args) {
        GerenciadorClientes gerenciador = new GerenciadorClientes();
        Cliente c1 = new Cliente(1, "joao@gmail.com", "João Silva", "Rua A, 123", "11999999999", "11111111111");
        Cliente c2 = new Cliente(2, "maria@gmail.com", "Maria Souza", "Av. B, 456", "21988888888", "22222222222");
        Cliente c3 = new Cliente(3, "carlos@gmail.com", "Carlos Pereira", "Praça C, 789", "31977777777", "33333333333");
        Cliente c4 = new Cliente(4, "ana@gmail.com", "Ana Oliveira", "Alameda D, 101", "41966666666", "44444444444");
        Cliente c5 = new Cliente(5, "lucas@gmail.com", "Lucas Santos", "Travessa E, 202", "51955555555", "55555555555");

        gerenciador.addCliente(c1);
        gerenciador.addCliente(c2);
        gerenciador.addCliente(c3);
        gerenciador.addCliente(c4);
        gerenciador.addCliente(c5);
        gerenciador.listarClientes();
        System.out.println(gerenciador.buscarClienteId(3));
        gerenciador.removerClienteId(3);
        
        
     
        
    }
}

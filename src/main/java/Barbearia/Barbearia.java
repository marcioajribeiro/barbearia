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
        Cliente c1 = new Cliente("João Silva", "Rua A, 123", "99999-1111",111111111, "joao@email.com");
        Cliente c2 = new Cliente("Maria Souza", "Av. Central, 456", "99999-2222",222222222, "maria@email.com");
        Cliente c3 = new Cliente("Carlos Pereira", "Praça das Flores, 789", "99999-3333", 333333333, "carlos@email.com");
        Cliente c4 = new Cliente("Ana Oliveira", "Rua B, 321", "99999-4444", 444444444, "ana@email.com");
        Cliente c5 = new Cliente("Fernanda Costa", "Av. Paulista, 654", "99999-5555", 555555555, "fernanda@email.com");
        gerenciador.addCliente(c1);
        gerenciador.addCliente(c2);
        gerenciador.addCliente(c3);
        gerenciador.addCliente(c4);
        gerenciador.addCliente(c5);
        
        
        gerenciador.listarClientes();

    }
}

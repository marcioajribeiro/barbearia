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
        Cliente c1 = new Cliente("Ana Silva","111.111.111-11","ana@email.com","Rua A, 123","99999-1111");
        Cliente c2 = new Cliente("Bruno Souza","222.222.222-22","bruno@email.com","Rua B, 456","99999-2222");
        Cliente c3 = new Cliente("Carla Mendes","333.333.333-33","carla@email.com","Rua C, 789","99999-3333");
        
        gerenciador.addCliente(c1);
        gerenciador.addCliente(c2);
        gerenciador.addCliente(c3);


        
        
        
        
     
        
    }
}

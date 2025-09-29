package Main;

import Controller.GerenciadorGenerico;
import Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author MARCIO JUNIOR
 */
public class Barbearia {

    public static void main(String[] args) {
        String arquivo ="Json/JsonCliente.json";
        List<Cliente> cliente = new ArrayList<>();
        cliente.add(new Cliente("Marcio", "curvelo", "90999009", 7, 12334, "junior@gmail.com"));
        cliente.add(new Cliente("Rafael", "curvelo", "90999009", 7, 12334, "junior@gmail.com"));
        GerenciadorGenerico.salvarLista(arquivo, cliente);

    }
}

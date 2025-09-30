/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entidades.Cliente;
import java.util.List;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorClientes extends GerenciadorGenerico {
    
    private List<Cliente> clientes;
    private final String caminho = "Json/JsonCliente.json";
    
    
    public GerenciadorClientes() {
        this.clientes = GerenciadorGenerico.carregarListas(caminho, Cliente.class);
    }
    
    public void addCliente(Cliente c){
        this.clientes.add(c);
        System.out.println("Cliente Salvo");
        GerenciadorGenerico.salvarLista(caminho, clientes);
    }
    
    public void listarClientes(){
        if (clientes.isEmpty()){
            System.out.println("Não há clientes cadastrados");
        }else{
            System.err.println("-----Lista de Clientes-----");
            for(Cliente cliente : clientes){
                System.out.println(cliente);
            }
        }
    }
    
    public Cliente buscarClienteId(int id){
        for(Cliente cliente : clientes){
            if(cliente.getIdCliente()== id){
                return cliente;
            }
        }
        
        System.out.println("ID: " + id + "não encontrado");
        return null;   
    }
    
    public void removerClienteId(int id){
        Cliente cliente = buscarClienteId(id);
        if(cliente !=null){
            clientes.remove(cliente);
            System.out.println("Cliente removido");
            GerenciadorClientes.salvarLista(caminho, clientes);
        }else {
            System.out.println("Cliente não encontrado");
        }
    }


    
    
    
    
    
    
    
}

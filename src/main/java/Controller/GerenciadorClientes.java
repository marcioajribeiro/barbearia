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
        this.clientes = super.carregarListas(caminho, Cliente.class);
    }
    
    public void addCliente(Cliente c){
        this.clientes.add(c);
        System.out.println("Cliente Salvo");
        super.salvarLista(caminho, clientes);
    }
    
    public void listarClientes(){
        if (clientes.isEmpty()){
            System.out.println("Não há clientes cadastrados");
        }else{
            System.out.println("-----Lista de Clientes-----");
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
            System.out.println("Cliente removido com sucesso");
            super.salvarLista(caminho, clientes);
        }else {
            System.out.println("Cliente não encontrado");
        }
    }
    
    
    public Cliente buscarClienteCpf(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        
        System.out.println("CPF: " + cpf + "não encontrado");
        return null;   
    }
    
      public void removerClienteCpf(String cpf){
        Cliente cliente = buscarClienteCpf(cpf);
        if(cliente !=null){
            clientes.remove(cliente);
            System.out.println("Cliente removido");
            super.salvarLista(caminho, clientes);
        }else {
            System.out.println("Cliente não encontrado");
        }
    }
    
    

    
    
    
    
    
    
    
}

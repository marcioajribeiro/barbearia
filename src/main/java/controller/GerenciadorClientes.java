/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import comparator.ComparatorCliente;
import entidades.Cliente;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorClientes extends GerenciadorGenerico {
    
    private  List<Cliente> clientes;
    private final String caminho = "Json/JsonCliente.json";
    
    
    public GerenciadorClientes() {
        this.clientes = super.carregarListas(caminho, Cliente.class);
    }
    
    public void addCliente(Cliente cliente){
        cliente.setIdCliente(geradorIdCliente());
        clientes.add(cliente);
        System.out.println("Cliente salvo");
        super.salvarLista(caminho, clientes);
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == id) {
                return cliente;
            }
        }
        System.out.println("Cliente com ID " + id + " não encontrado.");
        return null;
    }

    public List<Cliente> getClientes() {
        return clientes;
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
    
    
    public Cliente buscarCliente(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        
        System.out.println("CPF: " + cpf + " não encontrado");
        return null;   
    }
    
      public void removerCliente(Cliente cliente){
            clientes.remove(cliente);
            System.out.println("Cliente removido");
            super.salvarLista(caminho, clientes);
    }
      
      public void alterarNomeCliente(String novoNome, Cliente c){
          c.setNome(novoNome); 
          super.salvarLista(caminho, clientes);
      }
      
      public void alterarEnderecoCliente(String novoEndereco, Cliente c){
          c.setEndereco(novoEndereco);
          super.salvarLista(caminho, clientes);
      }
      
      public void alterarTelefoneCliente(String novoTelefone, Cliente c){
          c.setEndereco(novoTelefone);
          super.salvarLista(caminho, clientes);
      }
      
      public void alterarEmailCliente(String novoEmail, Cliente c){
          c.setEmail(novoEmail);
          super.salvarLista(caminho, clientes);
      }
      
      public void alterarCpfCliente(String Cpf, Cliente c){
          c.setCpf(Cpf);
          super.salvarLista(caminho, clientes);
      }
      
      public void OrdenarOrdemAlfabetica(){
          Collections.sort(clientes, new ComparatorCliente());
          super.salvarLista(caminho, clientes);
      }
      
      
      
      
      public int geradorIdCliente(){
          if(clientes.isEmpty()){
              return 1;
          }
          
          Set<Integer> idsExistentes = new HashSet<>();
          for(Cliente cliente: clientes){
              idsExistentes.add(cliente.getIdCliente());
          }
          
          int novoId = 1;
          
          while(idsExistentes.contains(novoId)){
              novoId++;
          }
          
          return novoId;
          
      }
      
    
}

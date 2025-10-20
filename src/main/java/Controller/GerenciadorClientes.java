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
    
    private  List<Cliente> clientes;
    private final String caminho = "Json/JsonCliente.json";
    
    
    public GerenciadorClientes() {
        this.clientes = super.carregarListas(caminho, Cliente.class);
    }
    
    public void addCliente(Cliente c){
        this.clientes.add(c);
        System.out.println("Cliente Salvo");
        super.salvarLista(caminho, clientes);
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
    
    
    public Cliente buscarClienteCpf(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        
        System.out.println("CPF: " + cpf + " não encontrado");
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
      
      public void alterarNomeCliente(String novoNome, Cliente c){
          c.setNome(novoNome); 
      }
      
      public void alterarEnderecoCliente(String novoEndereco, Cliente c){
          c.setEndereco(novoEndereco);
      }
      
      public void alterarTelefoneCliente(String novoTelefone, Cliente c){
          c.setEndereco(novoTelefone);
      }
      
      public void alterarEmailCliente(String novoEmail, Cliente c){
          c.setEmail(novoEmail);
      }
      
      public void alterarCpfCliente(String Cpf, Cliente c){
          c.setCpf(Cpf);
      }


    
    
    
    
}

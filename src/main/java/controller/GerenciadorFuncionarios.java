/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidades.Funcionario;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorFuncionarios extends GerenciadorGenerico{
    
    public List<Funcionario> funcionarios;
    public final String caminho = "Json/JsonFuncionario.json";

    public GerenciadorFuncionarios() {
        this.funcionarios = super.carregarListas(caminho, Funcionario.class);
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    public void addFuncionario(Funcionario f){
        f.setIdFuncionario(geradorIdFuncionario());
        funcionarios.add(f);
        System.out.println("Funcionário salvo");
        super.salvarLista(caminho, funcionarios);
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getIdFuncionario() == id) {
                return funcionario;
            }
        }
        System.out.println("Funcionário com ID " + id + " não encontrado.");
        return null;
    }
    
    public void listarFuncionarios(){
        if(funcionarios.isEmpty()){
            System.out.println("Não há Funcionários cadastrados");
        }else{
            System.out.println("-----Lista de Clientes-----");
            for(Funcionario f : funcionarios){
                System.out.println(f);
            }
        }   
    }
    
    public Funcionario buscarFuncionarioCpf(String cpf){
        for(Funcionario f : funcionarios){
            if(f.getCpf().equals(cpf)){
                return f;
            }
        }
        
        System.out.println("CPF: " + cpf + "não encontrado");
        return null;
    }
    
    public Funcionario buscarFuncionarioId(int id){
        for(Funcionario f : funcionarios){
            if(f.getIdFuncionario() == id){
                return f;
            }
        }
        
        System.out.println("Id: " + id + "não encontrado");
        return null;
    }
    
    public void removerFuncionarioCpf(Funcionario f){
        if(f != null){
            funcionarios.remove(f);
            System.out.println("Funcionario removido com sucesso");
            super.salvarLista(caminho, funcionarios);
        }else{
            System.out.println("Funcionario não encontrado");
        }
    }
    
    public void alterarNomeFuncionario(String novoNome, Funcionario f){
        f.setNome(novoNome);
        super.salvarLista(caminho, funcionarios);
    }
    
    public void alterarEnderecoFuncionario(String novoEndereco, Funcionario f){
        f.setEndereco(novoEndereco);
        super.salvarLista(caminho, funcionarios);
    }
    
    public void alterarTelefoneFuncionario(String novoTelefone, Funcionario f){
        f.setTelefone(novoTelefone);
        super.salvarLista(caminho, funcionarios);
    }
    
    public void alterarSenhaFuncionario (String novaSenha,Funcionario f){
        f.setSenha(novaSenha);
        super.salvarLista(caminho, funcionarios);
    }
    
    public void alterarCargoFuncionario (String novoCargo, Funcionario f){
        f.setCargo(novoCargo);
        super.salvarLista(caminho, funcionarios);
    }
    
    public void alterarCpfFuncionario (String novoCpf, Funcionario f){
        f.setCpf(novoCpf);
        super.salvarLista(caminho, funcionarios);
    }
    
    public int geradorIdFuncionario(){
          if(funcionarios.isEmpty()){
              return 1;
          }
          
          Set<Integer> idsExistentes = new HashSet<>();
          for(Funcionario funcionario: funcionarios){
              idsExistentes.add(funcionario.getIdFuncionario());
          }
          
          int novoId = 1;
          
          while(idsExistentes.contains(novoId)){
              novoId++;
          }
          
          return novoId;
          
      }
    
    
    
    
}
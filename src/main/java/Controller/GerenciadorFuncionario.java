/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entidades.Funcionario;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorFuncionario extends GerenciadorGenerico{
    
    public List<Funcionario> funcionarios;
    public final String caminho = "Json/JsonFuncionario.json";

    public GerenciadorFuncionario() {
        this.funcionarios = super.carregarListas(caminho, Funcionario.class);
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    public void addFuncionario(Funcionario f){
        funcionarios.add(f);
        System.out.println("Funcionário salvo");
        super.salvarLista(caminho, funcionarios);
    }
    
    public void listarFuncionários(){
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
    
    public void removerFuncionarioCpf(String cpf){
        Funcionario f = buscarFuncionarioCpf(cpf);
        if(f != null){
            funcionarios.remove(f);
            System.out.println("Funcionario removido com sucesso");
            super.salvarLista(caminho, funcionarios);
        }else{
            System.out.println("Funcionario não encontrado");
        }
    }
    
    public void alterarFuncionario(String novoNome, String novoCargo, String novaSenha, String novoEndereco, String novoTelefone){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o CPF do funcionário que deseja alterar: ");
        String cpf = sc.nextLine();
        Funcionario funcParaAlterar = buscarFuncionarioCpf(cpf);
        if (funcParaAlterar != null){
            funcParaAlterar.setNome(novoNome);
            funcParaAlterar.setCargo(novoCargo);
            funcParaAlterar.setSenha(novaSenha);
            funcParaAlterar.setEndereco(novoEndereco);
            funcParaAlterar.setTelefone(novoTelefone);
            super.salvarLista(caminho, funcionarios);
            System.out.println("Alterações salvas");
        }
    }
    
    
    
    
}

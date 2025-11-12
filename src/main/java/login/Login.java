/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import controller.GerenciadorFuncionarios;
import entidades.Funcionario;

/**
 *
 * @author rafin
 */
public class Login {
    
   private GerenciadorFuncionarios gf;
   
    public Login() {
        this.gf = new GerenciadorFuncionarios();
    }
    
    public Funcionario fazerLogin(String cpf, String senha) {
        for (Funcionario f : gf.getFuncionarios()) {
            if (f.getCpf().equals(cpf) && f.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso! Bem-vindo, " + f.getNome());
                return f;
            }
        }
        System.out.println("CPF ou senha incorretos.");
        return null;
    }
}
 

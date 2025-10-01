/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author rafin
 */
public class Funcionario extends Pessoa {
    private static AtomicInteger geradorId = new AtomicInteger(0);
    
    private int idFuncionario;
    private String cargo;
    private String senha;

    public Funcionario(String nome, String cpf, String cargo, String senha, String endereco, String telefone) {
        super(nome, endereco, telefone, cpf);
        this.idFuncionario = geradorId.incrementAndGet();
        this.cargo = cargo;
        this.senha = senha;
    }

   
    public int getIdFuncionario() {
        return idFuncionario;
    }


    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public String toString() {
        return 
                "ID: " + idFuncionario + 
                ", Nome: " + getNome() +
                ", Cargo: " + cargo +
                ", CPF: " + cpfPseudoAnonimizado() + 
                ", Telefone:  " + getTelefone() +
                ", Endereço: " + getEndereco();
   
    }

   
}

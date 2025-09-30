/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author rafin
 */
public class Funcionário extends Pessoa {
    private int idFuncionario;
    private String cargo;
    private String senha;

    public Funcionário(int idFuncionario, String cargo, String senha, String nome, String endereco, String telefone) {
        super(nome, endereco, telefone);
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.senha = senha;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
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

   
}

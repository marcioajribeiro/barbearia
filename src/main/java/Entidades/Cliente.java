/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author MARCIO JUNIOR
 */
public class Cliente extends Pessoa {
    private int idCliente;
    private int cpf;
    private String email;

    public Cliente(String nome, String endereco, String telefone, int idCliente, int cpf, String email) {
        super(nome, endereco, telefone);
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


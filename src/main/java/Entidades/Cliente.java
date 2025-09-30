/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author MARCIO JUNIOR
 */
public class Cliente extends Pessoa {
    private static AtomicInteger geradorId = new AtomicInteger(0);
    
    private int idCliente;
    private String cpf;
    private String email;

    public Cliente(String nome, String endereco, String telefone, String cpf, String email) {
        super(nome, endereco, telefone);
        this.idCliente = geradorId.incrementAndGet();
        this.cpf = cpf;
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return 
                "ID: " + idCliente + 
                ", Nome: " + getNome() +
                ", CPF: " + cpf + 
                ", Telefone:  " + getTelefone() +
                ", Endereço: " + getEndereco() +
                ", Email: " + email ;
    }

    
    
    
}


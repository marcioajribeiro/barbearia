/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;


/**
 *
 * @author MARCIO JUNIOR
 */
public class Cliente extends Pessoa {
    
    private int idCliente;
    private String email;

    public Cliente(String nome,String cpf,String email, String endereco, String telefone) {
        super(nome, endereco, telefone, cpf);
        this.email = email;
    }
    
 

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
                ", CPF: " + cpfPseudoAnonimizado() + 
                ", Telefone:  " + telefoneFormatado() +
                ", Endereço: " + getEndereco() +
                ", Email: " + email ;
    }

    
    
    
}


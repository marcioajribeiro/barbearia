/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author MARCIO JUNIOR
 */
public abstract class Pessoa {
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;

    public Pessoa(String nome, String endereco, String telefone, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }
  

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    public String cpfPseudoAnonimizado() {
        if (cpf == null || cpf.length() != 11) {
            return "CPF inválido";
        }
        return cpf.substring(0, 3) + "..-" + cpf.substring(9, 11);
    }
    
    public String telefoneFormatado() {
        if (telefone == null || telefone.length() != 11) {
            return "Telefone Desconhecido. Formato esperado: (31 9 99999999)";
        }
        return "(" + telefone.substring(0, 2) + ") "
                + telefone.substring(2, 7) + "-"
                + telefone.substring(7);
    }

    
}

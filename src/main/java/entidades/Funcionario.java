/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;



/**
 *
 * @author rafin
 */
public class Funcionario extends Pessoa {
    
    private  int idFuncionario;

    private String cargo;
    private String senha;

    public Funcionario(String nome, String cpf, String cargo, String senha, String endereco, String telefone) {
        super(nome, endereco, telefone, cpf);
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
    
    @Override
    public String toString() {
        return 
                "ID: " + idFuncionario + 
                ", Nome: " + getNome() +
                ", Cargo: " + cargo +
                ", CPF: " + cpfPseudoAnonimizado() + 
                ", Telefone:  " + telefoneFormatado() +
                ", Endereço: " + getEndereco();
   
    }

   
}

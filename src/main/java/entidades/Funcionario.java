/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;



/**
 * Classe para representar um funcionarios da Barbearia, onde serão contidos, valores e metodos para o mesmo.
 * @author Marcio Antonio
 * @author Rafael Martins
 */
public class Funcionario extends Pessoa {

    /** Identificador único do funcionário no sistema. */
    private int idFuncionario;

    /** Cargo ou função exercida pelo funcionário. */
    private String cargo;

    /** Senha de acesso do funcionário ao sistema. */
    private String senha;



    /**
     * Construtor da classe Cliente, garante que o Cliente será criado com atributos já inicializados
     * @param nome
     * @param cpf
     * @param cargo
     * @param senha
     * @param endereco
     * @param telefone
     */
    public Funcionario(String nome, String cpf, String cargo, String senha, String endereco, String telefone) {
        super(nome, endereco, telefone, cpf);
        this.cargo = cargo;
        this.senha = senha;
    }


    /**
     * Retorna o identificador único do funcionário.
     *
     * @return o ID do funcionário.
     */
    public int getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Define o identificador único do funcionário.
     *
     * @param idFuncionario o novo ID a ser atribuído ao funcionário.
     */
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Retorna o cargo ocupado pelo funcionário.
     *
     * @return o cargo do funcionário.
     */
    public String getCargo() {
        return cargo;
    }


    /**
     * Define o cargo ocupado pelo funcionário.
     *
     * @param cargo o novo cargo do funcionário.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    /**
     * Retorna a senha cadastrada para o funcionário.
     *
     * @return a senha do funcionário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do funcionário.
     *
     * @param senha a nova senha a ser atribuída ao funcionário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }


    /**
     *Retorna a representação de um funcionário em String contendo
     * ID, nome, cargo, CPF pseudo anonimizado, telefone e endereço.
     *
     * @return String com informações do funcionário
     */
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

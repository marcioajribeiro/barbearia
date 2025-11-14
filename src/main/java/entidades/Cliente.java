/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;


/**
 * Classe para representar um Cliente da Barbearia, onde serão contidos, valores e metodos para o mesmo.
 * @author Marcio Antonio
 * @author Rafael Martins
 */
public class Cliente extends Pessoa {

    /** Identificador único do cliente no sistema. */
    private int idCliente;

    /** Endereço de e-mail do cliente. */
    private String email;


    /**
     *Construtor da classe Cliente, garante que o Cliente será criado com atributos já inicializados
     * @param nome
     * @param cpf
     * @param email
     * @param endereco
     * @param telefone
     */
    public Cliente(String nome,String cpf,String email, String endereco, String telefone) {
        super(nome, endereco, telefone, cpf);
        this.email = email;
    }


    /**
     * Retorna o identificador único do cliente.
     *
     * @return o ID do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define o identificador único do cliente.
     *
     * @param idCliente o novo ID do cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Retorna o e-mail do cliente.
     *
     * @return o e-mail do cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do cliente.
     *
     * @param email o novo e-mail do cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna uma representação em String do cliente contendo
     * ID, nome, CPF pseudoanonimizado, telefone formatado, endereço e e-mail.
     *
     * @return uma string representando o cliente
     */
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *Classe abstrata que representa uma pessoa no sistema.
 *
 * As subclasses devem estender esta classe para especializar o tipo de pessoa,
 * como Cliente, Colaborador, etc
 * @author Márcio Antônio
 * @author Rafael Martins
 *
 */
public abstract class Pessoa {
    /**Nome da pessoa*/
    private String nome;
    /** Endereço residencial da pessoa. */
    private String endereco;
    /** Número de telefone da pessoa. */
    private String telefone;
    /** CPF da pessoa. */
    private String cpf;

    /**
     * Construtor da classe pessoa, garante que o Cliente será criado com atributos já inicializados
     * @param nome
     * @param endereco
     * @param telefone
     * @param cpf
     */
    public Pessoa(String nome, String endereco, String telefone, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }


    /**
     * Retorna o nome da pessoa.
     *
     * @return o nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome o novo nome da pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o endereço da pessoa.
     *
     * @return o endereço da pessoa
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da pessoa.
     *
     * @param endereco o novo endereço da pessoa
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna o número de telefone da pessoa.
     *
     * @return o telefone da pessoa
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o número de telefone da pessoa.
     *
     * @param telefone o novo telefone da pessoa
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o CPF da pessoa.
     *
     * @return o CPF da pessoa
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF da pessoa.
     *
     * @param cpf o novo CPF da pessoa
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna uma versão pseudoanonimizada do CPF, exibindo apenas
     * os três primeiros dígitos e os dois últimos.
     * <p>
     * Exemplo: {@code 12345678901 → 123..-01}
     * </p>
     * Caso o CPF seja nulo ou tenha tamanho incorreto, retorna uma mensagem de erro.
     *
     * @return o CPF pseudoanonimizado ou uma mensagem de CPF inválido
     */
    public String cpfPseudoAnonimizado() {
        if (cpf == null || cpf.length() != 11) {
            return "CPF inválido";
        }
        return cpf.substring(0, 3) + "..-" + cpf.substring(9, 11);
    }

    /**
     * Retorna o telefone formatado no padrão brasileiro.
     * <p>
     * Formato esperado: {@code (DD) 9XXXX-XXXX}
     * </p>
     * Caso o telefone seja nulo ou não tenha 11 dígitos, retorna
     * uma mensagem indicando formato incorreto.
     *
     * @return o telefone formatado ou uma mensagem de erro
     */
    public String telefoneFormatado() {
        if (this.telefone == null) {
            return "Telefone Desconhecido (NULO)";
        }


        String numeroLimpo = this.telefone.replaceAll("[()\\s-]", "");

        int tamanho = numeroLimpo.length();


        if (tamanho == 11) {
            return String.format("(%s) %s-%s",
                    numeroLimpo.substring(0, 2),
                    numeroLimpo.substring(2, 7),
                    numeroLimpo.substring(7));
        }


        else if (tamanho == 10) {
            return String.format("(%s) %s-%s",
                    numeroLimpo.substring(0, 2),
                    numeroLimpo.substring(2, 6),
                    numeroLimpo.substring(6));
        }


        else if (tamanho == 9) {
            return String.format("%s-%s",
                    numeroLimpo.substring(0, 5),
                    numeroLimpo.substring(5));
        }


        else if (tamanho == 8) {
            return String.format("%s-%s",
                    numeroLimpo.substring(0, 4),
                    numeroLimpo.substring(4));
        }


        else {
            return "Telefone Inválido: " + numeroLimpo + " (Esperado 8, 9, 10 ou 11 dígitos)";
        }
    }



}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacoesatendimento;

/**
 *Classe para representar as estações de atendimento da barbearia onde serão contidos, valores e metodos para o mesmo.
 *
 * @author MARCIO JUNIOR
 * @author Rafael Martins
 */
public class EstacaoAtendimento {
    /** Identificador único da estação de atendimento. */
    private int id;

    /** Descrição da estação (ex.: "Cadeira 1"). */
    private String descricao;

    /** Indica se a estação está atualmente ocupada. */
    private boolean ocupado;

    /** Tipo da estação, conforme enum TipoDeEstacao. */
    private TipoDeEstacao tipoDaEstacao;

    /**
     * Construtor que inicializa uma nova estação de atendimento.
     *
     * @param id              o identificador único da estação
     * @param descricaoo      a descrição da estação
     * @param tipoDaEstacao   o tipo da estação (ex.: CORTE, LAVAGEM)
     */
    public EstacaoAtendimento(int id, String descricaoo, TipoDeEstacao tipoDaEstacao) {
        this.id = id;
        this.descricao = descricaoo;
        this.tipoDaEstacao = tipoDaEstacao;
    }

    /**
     * Retorna o identificador da estação.
     *
     * @return o ID da estação
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da estação.
     *
     * @param id o novo ID da estação
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna a descrição da estação.
     *
     * @return a descrição da estação
     */
    public String getDescricaoo() {
        return descricao;
    }

    /**
     * Define a descrição da estação.
     *
     * @param descricaoo a nova descrição da estação
     */
    public void setDescricaoo(String descricaoo) {
        this.descricao = descricaoo;
    }

    /**
     * Retorna se a estação está ocupada.
     *
     * @return true se a estação estiver ocupada; false se estiver livre
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Define o estado de ocupação da estação.
     *
     * @param ocupado true para marcar como ocupada; false para livre
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Retorna o tipo da estação.
     *
     * @return o tipo da estação
     */
    public TipoDeEstacao getTipoDaEstacao() {
        return tipoDaEstacao;
    }

    /**
     * Define o tipo da estação.
     *
     * @param tipoDaEstacao o novo tipo da estação
     */
    public void setTipoDaEstacao(TipoDeEstacao tipoDaEstacao) {
        this.tipoDaEstacao = tipoDaEstacao;
    }

    /**
     * Marca a estação como ocupada.
     */
    public void ocupar() {
        this.ocupado = true;
    }

    /**
     * Libera a estação, marcando-a como disponível.
     */
    public void liberar() {
        this.ocupado = false;
    }

    /**
     * Retorna uma representação textual da estação,
     * contendo ID, tipo e status de ocupação.
     *
     * @return string representando a estação de atendimento
     */
    @Override
    public String toString() {
        String status = ocupado ? "Ocupado" : "Livre";
        return "Estação: " + id +
                " Tipo: " + tipoDaEstacao +
                " Status: " + status;
    }
}
     
    
    
    


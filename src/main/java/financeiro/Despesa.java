/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma despesa financeira registrada no sistema.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class Despesa {

    /** Identificador único da despesa. */
    private int id;

    /** Descrição da despesa. */
    private String descricao;

    /** Valor da despesa. */
    private double valor;

    /** Tipo da despesa categorizado por {@link TipoDespesa}. */
    private TipoDespesa tipo;

    /** Data e hora em que a despesa foi registrada. */
    private LocalDateTime data;

    /**
     * Constrói uma nova despesa com os dados informados.
     *
     * @param descricao descrição detalhada da despesa.
     * @param valor valor monetário da despesa.
     * @param tipo categoria da despesa.
     * @param data data e hora da despesa.
     */
    public Despesa(String descricao, double valor, TipoDespesa tipo, LocalDateTime data) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    /**
     * Retorna o identificador da despesa.
     *
     * @return o ID da despesa.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da despesa.
     *
     * @param id novo ID da despesa.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna a descrição da despesa.
     *
     * @return a descrição da despesa.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da despesa.
     *
     * @param descricao nova descrição da despesa.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna o valor da despesa.
     *
     * @return o valor monetário da despesa.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da despesa.
     *
     * @param valor novo valor monetário da despesa.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna o tipo da despesa.
     *
     * @return o tipo da despesa.
     */
    public TipoDespesa getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da despesa.
     *
     * @param tipo novo tipo da despesa.
     */
    public void setTipo(TipoDespesa tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna a data e hora da despesa.
     *
     * @return a data da despesa.
     */
    public LocalDateTime getData() {
        return data;
    }

    /**
     * Define a data e hora da despesa.
     *
     * @param data nova data da despesa.
     */
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    /**
     * Retorna uma representação textual da despesa formatada.
     * A data é formatada no padrão dd/MM/yyyy HH:mm:ss.
     *
     * @return string contendo ID, descrição, tipo e data formatada da despesa.
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = fmt.format(data);

        return "ID: " + id +
                ", Descricao: " + descricao +
                ", Tipo: " + tipo +
                ", Data: " + dataFormatada;
    }
}









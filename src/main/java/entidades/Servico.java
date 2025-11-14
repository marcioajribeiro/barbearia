/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import estacoesatendimento.EstacaoAtendimento;



/**
 * Classe para representar um serviço da Barbearia, onde serão contidos, valores e metodos para o mesmo.
 * @author Marcio Antonio
 * @author Rafael Martins
 */
public class Servico {
    /** Identificador único do serviço no sistema. */
    private int idServico;

    /** Nome ou tipo do serviço (ex: Corte, Barba, Sobrancelha). */
    private String tipoServico;

    /** Valor cobrado pelo serviço. */
    private double valor;

    /** Duração estimada do serviço, em minutos. */
    private int duracaoMin;

    /** Estação de atendimento onde o serviço é executado. */
    private EstacaoAtendimento estacaoUsada;

    /**
     * Construtor que inicializa um serviço com tipo, valor, duração e estação utilizada.
     *
     * @param tipoServico    o nome ou tipo do serviço
     * @param valor          o valor cobrado pelo serviço
     * @param duracaoMin     a duração estimada do serviço em minutos
     * @param estacaoUsada   a estação de atendimento onde o serviço será realizado
     */
    public Servico(String tipoServico, double valor, int duracaoMin, EstacaoAtendimento estacaoUsada) {
        this.tipoServico = tipoServico;
        this.valor = valor;
        this.duracaoMin = duracaoMin;
        this.estacaoUsada = estacaoUsada;
    }

    /**
     * Retorna o identificador do serviço.
     *
     * @return o ID do serviço
     */
    public int getIdServico() {
        return idServico;
    }

    /**
     * Define o identificador do serviço.
     *
     * @param idServico o novo ID do serviço
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * Retorna o tipo do serviço.
     *
     * @return o tipo do serviço
     */
    public String getTipoServico() {
        return tipoServico;
    }

    /**
     * Define o tipo do serviço.
     *
     * @param tipoServico o novo tipo do serviço
     */
    public void settipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    /**
     * Retorna o valor cobrado pelo serviço.
     *
     * @return o valor do serviço
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor cobrado pelo serviço.
     *
     * @param valor o novo valor do serviço
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna a duração estimada do serviço, em minutos.
     *
     * @return a duração do serviço
     */
    public int getDuracaoMin() {
        return duracaoMin;
    }

    /**
     * Define a duração estimada do serviço.
     *
     * @param duracaoMin nova duração do serviço, em minutos
     */
    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    /**
     * Retorna a estação de atendimento usada para executar o serviço.
     *
     * @return a estação utilizada
     */
    public EstacaoAtendimento getEstacaoUsada() {
        return estacaoUsada;
    }

    /**
     * Define a estação de atendimento onde o serviço será executado.
     *
     * @param estacaoUsada a nova estação de atendimento
     */
    public void setEstacaoUsada(EstacaoAtendimento estacaoUsada) {
        this.estacaoUsada = estacaoUsada;
    }

    /**
     * Retorna uma representação em String do serviço,
     * contendo ID, tipo, valor e duração prevista.
     *
     * @return string representando o serviço
     */
    @Override
    public String toString() {
        return "ID: " + idServico +
                ", Servico: " + tipoServico +
                ", Valor: " + valor +
                ", Duração: " + duracaoMin;
    }
    
}

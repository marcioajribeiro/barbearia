/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import estacoesatendimento.EstacaoAtendimento;



/**
 *
 * @author rafin
 */
public class Servico {
    private int idServico;
    private String tipoServico;
    private double valor;
    private int duracaoMin;
    private EstacaoAtendimento estacaoUsada;

    public Servico(String tipoServico, double valor, int duracaoMin, EstacaoAtendimento estacaoUsada) {
        this.tipoServico = tipoServico;
        this.valor = valor;
        this.duracaoMin = duracaoMin;
        this.estacaoUsada = estacaoUsada;
    }

    

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void settipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public EstacaoAtendimento getEstacaoUsada() {
        return estacaoUsada;
    }

    public void setEstacaoUsada(EstacaoAtendimento estacaoUsada) {
        this.estacaoUsada = estacaoUsada;
    }

    
    

    @Override
    public String toString() {
        return "ID: " + idServico +
                ", Servico: " + tipoServico +
                ", Valor: " + valor +
                "Duração: " + duracaoMin;
    }
    
    
}

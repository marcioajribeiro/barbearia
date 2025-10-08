/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author rafin
 */
class Servico {
    private static AtomicInteger geradorId = new AtomicInteger(0);
    private int idServico;
    private String descricao;
    private double valor;
    private int duração;

    public Servico(String descricao, double valor, int duração) {
        this.descricao = descricao;
        this.valor = valor;
        this.duração = duração;
        this.idServico = geradorId.incrementAndGet();
    }

    public static AtomicInteger getGeradorId() {
        return geradorId;
    }

    public static void setGeradorId(AtomicInteger geradorId) {
        Servico.geradorId = geradorId;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuração() {
        return duração;
    }

    public void setDuração(int duração) {
        this.duração = duração;
    }

}

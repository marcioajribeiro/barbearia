/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;



/**
 *
 * @author rafin
 */
public class Servico {
    private int idServico;
    private String tipoServico;
    private double valor;
    private int duraçãoMin;

    public Servico(String descricao, double valor, int duração) {
        this.tipoServico = descricao;
        this.valor = valor;
        this.duraçãoMin = duração;
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

    public int getDuracao() {
        return duraçãoMin;
    }

    public void setDuração(int duração) {
        this.duraçãoMin = duração;
    }

    @Override
    public String toString() {
        return "ID: " + idServico +
                ", Servico: " + tipoServico +
                ", Valor: " + valor +
                "Duração: " + duraçãoMin;
    }
    
    
}

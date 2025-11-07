/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacoesatendimento;

/**
 *
 * @author MARCIO JUNIOR
 */
public class EstacaoAtendimento {
    private int id;
    private String descricao;
    private boolean ocupado;
    private TipoDeEstacao tipoDaEstacao;

    public EstacaoAtendimento(int id, String descricaoo, TipoDeEstacao tipoDaEstacao) {
        this.id = id;
        this.descricao = descricaoo;
        this.tipoDaEstacao = tipoDaEstacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoo() {
        return descricao;
    }

    public void setDescricaoo(String descricaoo) {
        this.descricao = descricaoo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public TipoDeEstacao getTipoDaEstacao() {
        return tipoDaEstacao;
    }

    public void setTipoDaEstacao(TipoDeEstacao tipoDaEstacao) {
        this.tipoDaEstacao = tipoDaEstacao;
    }

    
    public void ocupar() {
        this.ocupado = true;
    }
    
     public void liberar() {
        this.ocupado = false;
    }

    @Override
    public String toString() {
        String status = ocupado ? "Ocupado" : "Livre";
        return "Estação: " + id +
                "Tipo: " + tipoDaEstacao +
                "Status: " + status;
    }
     
     
    
    
    
}

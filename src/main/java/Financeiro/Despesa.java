/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Financeiro;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author MARCIO JUNIOR
 */
public class Despesa { 
    
    private int id;
    private String descricao;
    private TipoDespesa tipo;
    private LocalDateTime data;

    public Despesa(int id, String descricao, TipoDespesa tipo, LocalDateTime data) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDespesa getTipo() {
        return tipo;
    }

    public void setTipo(TipoDespesa tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        String dataFormatada = fmt.format(data);
        
        return "Despesa" + " id: " + id 
                + ", descricao: " + descricao 
                + ", tipo: " + tipo + 
                ", data: " + dataFormatada ;
    }
    
    
    
    
    
    
}

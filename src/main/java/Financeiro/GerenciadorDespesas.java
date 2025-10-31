/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Financeiro;

import Controller.GerenciadorGenerico;
import java.util.List;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorDespesas extends GerenciadorGenerico {
    
    
    private List<Despesa> despesas;
    private final String caminho = "Json/JsonDespesas";

    public GerenciadorDespesas() {
        this.despesas = super.carregarListas(caminho, Despesa.class);
    }
    
    
    public void adicionarDespesaMaterial(){
        
    }
    
    
    
}

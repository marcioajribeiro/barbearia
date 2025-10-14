/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlePonto;

import Controller.GerenciadorGenerico;
import java.util.List;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorPonto extends GerenciadorGenerico {
    private List<RegistroPonto> pontos;
    private final String caminho = "Json/JsonPonto.json";

    public GerenciadorPonto() {
        this.pontos = super.carregarListas(caminho, RegistroPonto);
    }
    
    
}

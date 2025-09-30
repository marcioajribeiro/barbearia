/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafin
 */
public class Agendamento {
    private Cliente cliente;
    private String Funcionario;
    private Date data;
    private String tipoAgendamento;
    private Servico servico;

    public Agendamento(Cliente cliente, String Funcionario, String data, String tipoAgendamento, Servico servido) {
        this.cliente = cliente;
        this.Funcionario = Funcionario;
    try{
        this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data);
            } catch (ParseException ex ){
        Logger.getLogger (Agendamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        this.tipoAgendamento = tipoAgendamento;
        this.servico = servico;
    }
    
    
}

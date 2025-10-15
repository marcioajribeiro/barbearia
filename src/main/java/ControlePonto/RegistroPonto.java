/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlePonto;

import Entidades.Funcionario;
import java.time.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *                          
 * @author MARCIO JUNIOR
 */
public class RegistroPonto {
    private  final Funcionario funcionario;
    private final LocalDateTime  entrada;
    private LocalDateTime saida;

    public RegistroPonto(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.entrada = LocalDateTime.now();
        this.saida = null;
    }
    
    
    

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }
    
    public double getHorasTrabalhadas(){
        if (saida ==null){
            return 0;
        }
        
        Duration duracao = Duration.between(entrada, saida );
        
        return duracao.toMinutes() /60.0;
        
    }
    
    
    public void pontoDeSaida(){
        this.saida = LocalDateTime.now();
    } 

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        String entradaFormatada = fmt.format(entrada);
        
        String saidaFormatada = (saida !=null) ? fmt.format(saida) : "O PONTO ESTÁ EM ABERTO";
        
        return "Funcionario: " + funcionario.getNome() +
                ", Entrada: " + entradaFormatada +
                ", Saída: " + saidaFormatada +
                ", Horas: " + getHorasTrabalhadas();
     
    }
    
    
    
    
    
    
    
    
    
}

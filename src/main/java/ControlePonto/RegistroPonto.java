/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlePonto;

import Entidades.Funcionario;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author MARCIO JUNIOR
 */
public class RegistroPonto {
    private  final Funcionario funcionario;
    private final LocalDate  entrada;
    private LocalDate saida;

    public RegistroPonto(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.entrada = LocalDate.now();
        this.saida = null;
    }
    
    
    

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getEntrada() {
        return entrada;
    }

    public LocalDate getSaida() {
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
        this.saida = LocalDate.now();
    } 

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        String saidaFormatada = (saida !=null) ? fmt.format(saida) : "O PONTO ESTÁ EM ABERTO";
        
        return String.format("Funcionário: %s \n"
                + "Entrada: %s \n"
                + "Saída: %s \n"
                + "Horas Trabalhadas: %.2f",
                funcionario.getNome(),
                fmt.format(entrada),
                saidaFormatada,
                getHorasTrabalhadas()
        
        
        );
    }
    
    
    
    
    
    
    
    
    
}

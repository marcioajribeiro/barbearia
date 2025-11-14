/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comparator;

import agendamento.Agendamento;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.time.LocalDateTime;

/**
 *
 * @author rafin
 */
public class ComparatorAgendamento implements Comparator<Agendamento>{
    

    @Override
    public int compare(Agendamento a1, Agendamento a2) {

        
        if (a1 == null && a2 == null){
            return 0;
        }
        if (a1 == null){
            return -1;
        }
        if (a2 == null){
            return 1;
        }

        LocalDateTime d1 = a1.getDataHora();
        LocalDateTime d2 = a2.getDataHora();

        
        if (d1 == null && d2 == null){
            return 0;
        }
        if (d1 == null){
            return -1;
        }
        if (d2 == null){
            return 1;
        }

        
        
        if (d1.getYear() != d2.getYear()) {
            return d1.getYear() - d2.getYear();
        }

        if (d1.getMonthValue() != d2.getMonthValue()) {
            return d1.getMonthValue() - d2.getMonthValue();
        }

        if (d1.getDayOfMonth() != d2.getDayOfMonth()) {
            return d1.getDayOfMonth() - d2.getDayOfMonth();
        }

        if (d1.getHour() != d2.getHour()) {
            return d1.getHour() - d2.getHour();
        }

        if (d1.getMinute() != d2.getMinute()) {
            return d1.getMinute() - d2.getMinute();
        }

        
        if (d1.getSecond() != d2.getSecond()) {
            return d1.getSecond() - d2.getSecond();
        }

        
        return 0;
    }
}

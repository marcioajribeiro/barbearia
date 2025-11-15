/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendamento;

/**
 *Enum que representa o status do agendamento
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public enum StatusAgendamento {

    /**O agendamento foi registrado, porém, ainda não recebeu confirmação.*/
    AGENDAMENTO_PENDENTE,

    /**O agendamento foi confirmado e está ativo.*/
    AGENDAMENTO_CONFIRMADO,

    /**O agendamento foi cancelado e não será realizado.*/
    AGENDAMENTO_CANCELADO;
}

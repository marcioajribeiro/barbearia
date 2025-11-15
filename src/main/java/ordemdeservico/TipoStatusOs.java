/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordemdeservico;

/**
 * Representa os diferentes estados possíveis de uma Ordem de Serviço (OS)
 * dentro do sistema. Cada estado indica o estágio atual do atendimento.
 * @author Márcio Antônio
 * @author rafael Martins
 */
public enum TipoStatusOs {

    /**
     * Indica que a Ordem de Serviço foi criada, mas continua aguardando
     * atendimento.
     */
    ESTADO_AGUARDANDO,

    /**
     * Indica que a Ordem de Serviço está em andamento, ou seja, o serviço
     * está sendo realizado no momento.
     */
    ESTADO_ANDAMENTO,

    /**
     * Indica que a Ordem de Serviço foi concluída, com o serviço finalizado
     * e sem pendências.
     */
    ESTADO_CONCLUIDO;
}

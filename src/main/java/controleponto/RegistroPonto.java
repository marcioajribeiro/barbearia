/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleponto;

import entidades.Funcionario;
import java.time.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa o registro de ponto de um funcionário.
 * @author Márcio Antônio
 * @author Rafael Martins
 */
public class RegistroPonto {

    /** Funcionário ao qual o registro de ponto pertence. */
    private final Funcionario funcionario;

    /** Data e hora em que o funcionário registrou entrada. */
    private final LocalDateTime entrada;

    /** Data e hora em que o funcionário registrou saída. */
    private LocalDateTime saida;

    /**
     * Cria um novo registro de ponto para o funcionário informado.
     * O horário de entrada é registrado automaticamente como o horário atual
     * e o horário de saída permanece indefinido até ser registrado.
     *
     * @param funcionario o funcionário que está registrando o ponto.
     */
    public RegistroPonto(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.entrada = LocalDateTime.now();
        this.saida = null;
    }

    /**
     * Retorna o funcionário associado a este registro de ponto.
     *
     * @return o funcionário que registrou o ponto.
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * Retorna a data e hora do registro de entrada.
     *
     * @return o horário de entrada.
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }

    /**
     * Retorna a data e hora do registro de saída.
     * Pode ser null caso o ponto ainda esteja em aberto.
     *
     * @return o horário de saída ou null caso ainda não tenha sido registrado.
     */
    public LocalDateTime getSaida() {
        return saida;
    }

    /**
     * Registra o horário de saída do funcionário, usando a data e hora atual.
     */
    public void pontoDeSaida() {
        this.saida = LocalDateTime.now();
    }

    /**
     * Calcula o total de horas trabalhadas entre a entrada e a saída.
     * Caso o ponto ainda esteja aberto (sem saída registrada), retorna 0.
     *
     * @return uma string representando o tempo trabalhado em horas e minutos,
     * ou uma mensagem informando que a saída não foi registrada.
     */
    public String getHorasTrabalhadas() {
        if (saida == null) {
            return "Saída ainda não registrada";
        }

        Duration duracao = Duration.between(entrada, saida);

        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart(); // Java 9+

        return horas + "h " + minutos + "min";
    }

    /**
     * Retorna uma representação em String do produto,
     * contendo ID, o nome do funcionario, entrada, saída e as horas trabalhadas.
     *
     * @return uma string representando o ponto
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String entradaFormatada = fmt.format(entrada);
        String saidaFormatada = (saida != null) ?
                fmt.format(saida) : "O PONTO ESTÁ EM ABERTO";

        return "Funcionario: " + funcionario.getNome() +
                ", Entrada: " + entradaFormatada +
                ", Saída: " + saidaFormatada +
                ", Horas Trabalhadas: " + getHorasTrabalhadas();
    }
    
    
    
    
    
    
    
    
    
}

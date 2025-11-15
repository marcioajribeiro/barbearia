/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordemdeservico;


import entidades.Cliente;
import entidades.Funcionario;
import entidades.Produto;
import entidades.Servico;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author rafin
 */
public class OrdemDeServico {
    
    private int idOS;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Produto> produto;
    private List<Servico> servicos; 
    private LocalDateTime dataHora;
    
    
}

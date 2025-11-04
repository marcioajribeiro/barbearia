/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import controller.GerenciadorGenerico;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author MARCIO JUNIOR
 */
public class GerenciadorDespesas extends GerenciadorGenerico {
    
    
    private List<Despesa> despesas;
    private final String caminho = "Json/JsonDespesa.json";

    public GerenciadorDespesas() {
        this.despesas = super.carregarListas(caminho, Despesa.class);
    }
    
    
    
    public void registrarDespesaMaterial(String materialComprado, double valor){
        Despesa novaDespesa = new Despesa(materialComprado, valor, TipoDespesa.DESPESA_MATERIAL, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
        super.salvarLista(caminho, despesas);
    }
    
    public void registrarDespesaSalario(double todosSalarios){
        Despesa novaDespesa = new Despesa("Pagamento dos salarios", todosSalarios, TipoDespesa.DESPESA_SALARIO, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
        super.salvarLista(caminho, despesas);
    }
    
    public void registrarDespesaLimpeza(double valorLimpeza){
        Despesa novaDespesa = new Despesa("Pagamento para limpeza", valorLimpeza, TipoDespesa.DESPESA_LIMPEZA, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
        super.salvarLista(caminho, despesas);
    }
    
    public void registrarDespesaCortesias(String cortesia, double valorCortesia){
        Despesa novaDespesa = new Despesa(cortesia, valorCortesia, TipoDespesa.DESPESA_CORTESIAS, LocalDateTime.now());
        novaDespesa.setId(geradorId());
        despesas.add(novaDespesa);
        super.salvarLista(caminho, despesas);
    }
    
    public Despesa buscarVendaPorId(int id) {
        for (Despesa d :despesas) {
            if (d.getId()== id) {
                return d;
            }
        }
        return null;
    }
    
    
    public void removerDespesa(Despesa d){
        despesas.remove(d);
        System.out.println("Despesa removido");
        super.salvarLista(caminho, despesas);
    }
    
    
    public double calcularDespesasAnoMes(int ano, int mes){
        double total = 0.0;
        for (Despesa d : despesas){
            if(d.getData().getYear() == ano && d.getData().getMonthValue()== mes){
                total+= d.getValor();
            }  
        }
        
        return total;
        
        
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }
    
      
    
    public int geradorId(){
        if(despesas.isEmpty()){
            return 1;
        }
        
        Set<Integer> idExistentes = new HashSet<>();
        
        for(Despesa d : despesas){
            idExistentes.add(d.getId());
        }
        
        int novoId = 1;
        
        while(idExistentes.contains(novoId)){
            novoId++;
        }
        
        return novoId;
        
        
    }
    
    
    
}

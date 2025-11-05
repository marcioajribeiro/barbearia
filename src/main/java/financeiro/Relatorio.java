/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MARCIO JUNIOR
 */
public class Relatorio {
    private final GerenciadorVenda gv;
    private final GerenciadorDespesas gd;
    private final List<Venda> vendas;
    private final List<Despesa> despesa;

    public Relatorio(GerenciadorVenda gv, GerenciadorDespesas gd) {
        this.gv = gv;
        this.gd = gd;
        this.vendas = gv.getVendas();
        this.despesa = gd.getDespesas();
    }
    
    public void gerarBalancoMensal(int ano, int mes){
        double totalDespesas = gd.calcularDespesasAnoMes(ano, mes);
        double totalVendas = gv.calcularVendasAnoMes(ano, mes);
        double saldo = totalVendas - totalDespesas;
        
        System.out.println("======Balanço Mensal de " + mes +"/" +ano +"======");
        System.out.printf("Total de Vendas : R$ %2f \n" , totalVendas);
        System.out.printf("Total de Despesas : R$ %2f \n" , totalDespesas);
        System.out.println("===================================");
        
        if(saldo >0){
            System.out.printf("Lucro do mês: R$ %.2f \n", saldo);
        }else if (saldo <0){
            System.out.printf("Prejuízo do mês: R$ %.2f \n", Math.abs(saldo));
        }else {
            System.out.println("Resultado Neutro");
        }
        
        System.out.println("==============================");
                
    }
    
    public void gerarRelatorioDespesas(int ano, int mes){
        List<Despesa> despesasPeriodo = new ArrayList<>();
        for(Despesa d : despesa){
            if(d.getData().getYear() == ano && d.getData().getMonthValue()== mes){
                despesasPeriodo.add(d);
            }
        }
        
        if(despesasPeriodo.isEmpty()){
            System.out.println("Nenhuma Despesa nesse período");
        }else{
            for(Despesa dp : despesasPeriodo){
                System.out.println(dp);
            }
        }
        
    }
    
    public void gerarRelatorioVendas(int ano, int mes){
        List<Venda> vendasPeriodo = new ArrayList<>();
        for(Venda v : vendas){
            if(v.getDataHora().getYear() == ano && v.getDataHora().getMonthValue() == mes){
                vendasPeriodo.add(v);
            }
        }
        if(vendasPeriodo.isEmpty()){
            System.out.println("Nenhuma Venda nesse período");
        }else{
            for(Venda vp: vendasPeriodo){
                System.out.println(vp);
            }
        }
    }
}
    
  
    
    
    


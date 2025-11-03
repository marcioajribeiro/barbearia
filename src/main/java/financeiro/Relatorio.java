/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeiro;

/**
 *
 * @author MARCIO JUNIOR
 */
public class Relatorio {
    private final GerenciadorVenda gv;
    private final GerenciadorDespesas gd;

    public Relatorio(GerenciadorVenda gv, GerenciadorDespesas gd) {
        this.gv = gv;
        this.gd = gd;
    }
    
    public void gerarBalancoMensal(int ano, int mes){
        double totalDespesas = gd.calcularDespesasAnoMes(ano, mes);
        double totalVendas = gv.calcularVendasAnoMes(ano, mes);
        double saldo = totalVendas = totalDespesas;
        
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

    
    
    
}

package Barbearia;

import ControlePonto.GerenciadorPonto;
import Controller.*;
import Entidades.Cliente;
import Entidades.Funcionario;
import Loja.Produto;




/**
 *
 * @author MARCIO JUNIOR
 */
public class Barbearia {

    public static void main(String[] args)  {
        GerenciadorProduto produtos = new GerenciadorProduto();
        GerenciadorClientes clientes = new GerenciadorClientes();
        GerenciadorFuncionario func = new GerenciadorFuncionario();
        GerenciadorPonto pontos = new GerenciadorPonto();
        
     
        
        Cliente c1 = new Cliente("Ana Silva","111.111.111-11","ana@email.com","Rua A, 123","99999-1111");
        Cliente c2 = new Cliente("Bruno Souza","222.222.222-22","bruno@email.com","Rua B, 456","99999-2222");
        Cliente c3 = new Cliente("Carla Mendes","333.333.333-33","carla@email.com","Rua C, 789","99999-3333");
        
        Funcionario f1 = new Funcionario("João Silva","12345678900","Gerente","senha123","Rua A, 123","38999999999");
        Funcionario f2 = new Funcionario("Maria Souza","98765432100","Atendente","senha456","Rua B, 456","38988888888");
        Funcionario f3 = new Funcionario("Carlos Oliveira","45678912300","Caixa","senha789","Rua C, 789","38977777777");
        
        clientes.addCliente(c1);
        clientes.addCliente(c2);
        clientes.addCliente(c3);
        clientes.removerClienteCpf("333.333.333-33");
 
        func.addFuncionario(f1);
        func.addFuncionario(f2);
        func.addFuncionario(f3);
        clientes.alterarCliente("Márcio", "marcio@gmail.com", "Barão do Rio branco", "99184025");
        pontos.baterEntrada(f1);
        pontos.baterSaida(f1);
        pontos.baterSaida(f2);
        pontos.listarPontos();
        
        
        Produto p1 = new Produto("papel",12,2);
        Produto p2 = new Produto("shapoo", 10, 20);
        
        produtos.addProduto(p1);
        produtos.addProduto(p2);


     
        
    }
}

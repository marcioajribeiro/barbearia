/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;
import controller.GerenciadorProdutos;
import entidades.Produto;
import java.util.Scanner;
/**
 *
 * @author MARCIO JUNIOR
 */



public class MenuProduto {

    public static void exibirMenuProdutos() {
        Scanner sc = new Scanner(System.in);
        GerenciadorProdutos gp = new GerenciadorProdutos();
        int opc;

        do {
            System.out.println("\n----- MENU PRODUTOS -----");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Buscar Produto por ID");
            System.out.println("4 - Remover Produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Fornecedor: ");
                    String fornecedor = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Quantidade inicial: ");
                    int qtd = sc.nextInt();

                    Produto p = new Produto(nome, preco, qtd, fornecedor);
                    gp.addProduto(p);
                }
                case 2 -> gp.listarProdutos();
                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    Produto p = gp.buscarProdutoPorId(id);
                    if (p != null) System.out.println(p);
                }
                case 4 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    Produto p = gp.buscarProdutoPorId(id);
                    if (p != null) gp.removerProdutoPorId(p);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }
}

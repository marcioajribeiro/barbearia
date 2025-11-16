package barbearia;

import entidades.Cliente;
import entidades.Funcionario;
import ordemdeservico.GerenciadorOs;

public class Test {
    public static void main(String[] args) {
        GerenciadorOs gos = new GerenciadorOs();


        Cliente c = new Cliente("Rafael", "15322402608", "rafinhamartins@gmail.com", "Rua Barao do Rio Branco", "38999279932");
        Funcionario f = new Funcionario("Rafael", "15322402608", "ADMIN", "admin123", "Rua Barao do Rio Branco", "38999279932");

        gos.abrirOs(c, f);
        gos.imprimirOsDeCliente(c);
    }
}

package login;

import controller.GerenciadorFuncionarios;
import entidades.Funcionario;

public class LoginService {
    private GerenciadorFuncionarios gerenciadorFuncionarios;

    public LoginService() {
        this.gerenciadorFuncionarios = new GerenciadorFuncionarios();
    }

    public Funcionario login(String cpf, String senha) {
        for (Funcionario f : gerenciadorFuncionarios.getFuncionarios()) {
            if (f.getCpf().equals(cpf) && f.getSenha().equals(senha)) {
                System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + f.getNome());
                return f;
            }
        }
        System.out.println("CPF ou senha incorretos!");
        return null;
    }
}

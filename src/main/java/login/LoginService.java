package login;

import controller.GerenciadorFuncionarios;
import entidades.Funcionario;
/**
 * Serviço responsável por realizar a autenticação de funcionários no sistema.
 * @author Márcio Antônior
 * @author Rafael Martins
 */
public class LoginService {

    /**
     * Gerenciador responsável por armazenar e fornecer a lista de funcionários
     * cadastrados no sistema.
     */
    private GerenciadorFuncionarios gerenciadorFuncionarios;

    /**
     * Construtor que recebe o gerenciador de funcionários utilizado
     * para validação de credenciais.
     *
     * @param gerenciadorFuncionarios instância responsável pela gestão dos funcionários
     */
    public LoginService(GerenciadorFuncionarios gerenciadorFuncionarios) {
        this.gerenciadorFuncionarios = gerenciadorFuncionarios;
    }

    /**
     * Realiza o processo de login verificando CPF e senha fornecidos.
     *
     * <p>Se as credenciais corresponderem a um funcionário cadastrado,
     * uma mensagem de boas-vindas é exibida e o objeto {@link Funcionario}
     * correspondente é retornado.</p>
     *
     * <p>Se não houver correspondência, o método exibe uma mensagem de erro
     * e retorna {@code null}.</p>
     *
     * @param cpf   CPF do funcionário tentando acessar o sistema
     * @param senha senha do funcionário
     * @return o funcionário autenticado ou {@code null} caso as credenciais estejam incorretas
     */
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

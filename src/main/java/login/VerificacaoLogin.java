/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import entidades.Funcionario;

/**
 *
 * @author rafin
 */
public class VerificacaoLogin {

    /**
     * Instância única (Singleton) utilizada para garantir que apenas
     * um controlador de login exista no sistema.
     */
    private static VerificacaoLogin instance;

    /**
     * Funcionário atualmente logado no sistema. Caso nenhum esteja logado, será null.
     */
    private Funcionario usuarioLogado;

    /**
     * Construtor padrão. É público, mas a instância deve ser obtida
     * via #getInstance(). Utilizado internamente pelo Singleton.
     */
    public VerificacaoLogin() {
    }

    /**
     * Retorna a instância única da classe utilizando o padrão Singleton.
     *
     * @return instância de VerificacaoLogin}
     */
    public static VerificacaoLogin getInstance() {
        if (instance == null) {
            instance = new VerificacaoLogin();
        }
        return instance;
    }

    /**
     * Define o funcionário logado no momento.
     *
     * @param funcionario o funcionário autenticado
     */
    public void login(Funcionario funcionario) {
        usuarioLogado = funcionario;
    }

    /**
     * Retorna o funcionário atualmente logado.
     *
     * @return o funcionário logado ou null caso não haja usuário logado
     */
    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * Realiza o logout, removendo o usuário logado do sistema.
     */
    public void logout() {
        usuarioLogado = null;
    }

    /**
     * Verifica se há um usuário autenticado.
     *
     * @return true se houver um usuário logado, false caso contrário
     */
    public boolean IsLogado() {
        return usuarioLogado != null;
    }

    /**
     * Retorna o cargo do usuário atualmente logado.
     *
     * @return cargo do funcionário logado ou null se ninguém estiver logado
     */
    public String getCargoUsuarioLogado() {
        if (IsLogado()) {
            return usuarioLogado.getCargo();
        }
        return null;
    }
}

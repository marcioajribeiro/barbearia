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
    
    private static VerificacaoLogin instance;
    private Funcionario usuarioLogado;

    public VerificacaoLogin() {
    }
    
    public static VerificacaoLogin getInstance() {
        if (instance == null) {
            instance = new VerificacaoLogin();
        }
        return instance;
    }
    
    public void login(Funcionario funcionario) {
        usuarioLogado = funcionario;
    }
    
    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public void logout() {
        usuarioLogado = null;
    }
    
    public boolean IsLogado() {
        return usuarioLogado != null;
    }
    
    public String getCargoUsuarioLogado() {
        if (IsLogado()) {
            return usuarioLogado.getCargo();
        }
        return null;
    }
    
}

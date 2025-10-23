/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comparator;

import Entidades.Cliente;
import java.util.Comparator;

/**
 *
 * @author MARCIO JUNIOR
 */
public class ComparatorClienteId implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        return Integer.compare(c1.getIdCliente(), c2.getIdCliente()); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
  
    
}

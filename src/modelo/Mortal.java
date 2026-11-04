/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

public interface Mortal {
    boolean estaVivo();
    void morir();
    
    default void verificarMuerte(double energia) {
        if (energia <= 0) {
            morir();
        }
    }
}
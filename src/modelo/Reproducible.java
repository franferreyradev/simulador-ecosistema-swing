/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

public interface Reproducible {
    void reproducirse(GestorEcosistema eco);
    boolean puedeReproducirse();
    
    default void intentarReproduccion(GestorEcosistema eco) {
        if (puedeReproducirse()) {
            reproducirse(eco);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class PlantaVenenosa extends Planta implements Peligroso {

    public PlantaVenenosa(String nombre, double energia, int tamanio) {
        super(nombre, energia, tamanio);
    }

    @Override
    public double serComida() {
        setEnergia(0);
        setViva(false);
        return -30.0;
    }

    @Override
    public int getNivelPeligro() { return 80; }

    @Override
    public void mostrarEstado() {
        System.out.println("Planta: " + getNombre() + " (Venenosa) | Tamaño: " + getTamanio() + " | Energía: " + getEnergia());
    }
}

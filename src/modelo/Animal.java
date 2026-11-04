/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public abstract class Animal extends Entidad implements Mortal {
    private int velocidad;
    private double peso;

    public Animal(String nombre, double energia, int velocidad, double peso) {
        super(nombre, energia);
        this.velocidad = velocidad;
        this.peso = peso;
    }

    public abstract void comer(GestorEcosistema eco);

    public void moverse() {
        System.out.println(getNombre() + " se desplazó.");
    }

    @Override
    public boolean estaVivo() { return isViva(); }

    @Override
    public void morir() { setViva(false); }
}

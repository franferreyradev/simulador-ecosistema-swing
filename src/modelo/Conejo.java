/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Conejo extends Animal implements Reproducible {
    
    public Conejo(String nombre, double energia) {
        super(nombre, energia, 10, 2.5);
    }

    @Override
    public void actuar(GestorEcosistema eco) {
        comer(eco);
    }

    @Override
    public void comer(GestorEcosistema eco) {
        ArrayList<Planta> plantas = eco.getPlantas();
        
        if (plantas != null && !plantas.isEmpty()) {
            Random rand = new Random();
            Planta plantaEncontrada = plantas.get(rand.nextInt(plantas.size()));
            
            if (plantaEncontrada.isViva()) {
                double energiaGanada = plantaEncontrada.serComida();
                setEnergia(getEnergia() + energiaGanada);
                System.out.println("Conejo '" + getNombre() + "' comió '" + plantaEncontrada.getNombre() + "' (" + (energiaGanada > 0 ? "+" : "") + energiaGanada + " energia)");
                eco.registrarEvento();
            }
        } else {
            setEnergia(getEnergia() - 15);
            System.out.println("Conejo '" + getNombre() + "' no encontró comida (-15 energia)");
            eco.registrarEvento();
        }
    }

    @Override
    public void mostrarEstado() {
        System.out.print("Conejo: " + getNombre() + " | Energía: " + getEnergia());
        if (getEnergia() < 20) {
            System.out.print(" [PELIGRO: energia=" + getEnergia() + "]");
        }
        System.out.println();
    }

    @Override
    public void reproducirse(GestorEcosistema eco) {
        long conejosVivos = eco.getConejos().stream().filter(Conejo::isViva).count();
        
        if (conejosVivos > 1) {
            System.out.println("Conejo '" + getNombre() + "' se reprodujo.");
            eco.agregarEntidad("conejo");
            eco.registrarEvento();
        }
    }

    @Override
    public boolean puedeReproducirse() {
        return getEnergia() > 60; 
    }
}
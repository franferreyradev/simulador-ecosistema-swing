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
                String msg = "Conejo '" + getNombre() + "' comió '" + plantaEncontrada.getNombre() + "' (" + (energiaGanada > 0 ? "+" : "") + Math.round(energiaGanada * 10.0) / 10.0 + " energía)";
                eco.registrarEvento(msg);
            }
        } else {
            setEnergia(getEnergia() - 15);
            eco.registrarEvento("Conejo '" + getNombre() + "' no encontró comida (-15 energía)");
        }
    }

    @Override
    public void reproducirse(GestorEcosistema eco) {
        long conejosVivos = eco.getConejos().stream().filter(Conejo::isViva).count();
        
        if (conejosVivos > 1) {
            eco.agregarEntidad("conejo");
            eco.registrarEvento("Conejo '" + getNombre() + "' se reprodujo.");
        }
    }

    @Override
    public boolean puedeReproducirse() {
        return getEnergia() >= 40;
    }
}

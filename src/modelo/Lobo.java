package modelo;

import java.util.ArrayList;
import java.util.Random;

public class Lobo extends Animal implements Peligroso {
    
    private int exitosCaza;

    public Lobo(String nombre, double energia) {
        super(nombre, energia, 25, 40.0); 
        this.exitosCaza = 0;
    }

    public int getExitosCaza() { return exitosCaza; }
    public void setExitosCaza(int exitosCaza) { this.exitosCaza = exitosCaza; }

    @Override
    public void actuar(GestorEcosistema eco) {
        comer(eco);
    }

    @Override
    public void comer(GestorEcosistema eco) {
        ArrayList<Conejo> conejos = eco.getConejos();
        
        if (conejos != null && !conejos.isEmpty()) {
            Random rand = new Random();
            Conejo presa = conejos.get(rand.nextInt(conejos.size()));
            
            if (presa.isViva()) {
                double probabilidadExito = Math.min(80, getEnergia());
                
                if (eco.getClimaActual() == Clima.INVIERNO) {
                    probabilidadExito += 20; 
                }
                
                double tirada = rand.nextDouble() * 100;
                
                if (tirada <= probabilidadExito) {
                    presa.setEnergia(0);
                    presa.setViva(false);
                    this.setEnergia(this.getEnergia() + 40);
                    this.exitosCaza++;
                    eco.registrarEvento("Lobo '" + getNombre() + "' cazó a Conejo '" + presa.getNombre() + "' (+40 energía) [éxitos: " + exitosCaza + "]");
                } else {
                    eco.registrarEvento("Lobo '" + getNombre() + "' intentó cazar a Conejo '" + presa.getNombre() + "' pero falló.");
                }
            }
        }
    }

    @Override
    public int getNivelPeligro() {
        return 50 + (getExitosCaza() * 10); 
    }
}

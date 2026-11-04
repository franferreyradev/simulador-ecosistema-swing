package modelo;

public class Planta extends Entidad implements Reproducible {
    private int tamanio;

    public Planta(String nombre, double energia, int tamanio) {
        super(nombre, energia);
        setTamanio(tamanio);
    }

    public void setTamanio(int tamanio) {
        if (tamanio >= 1 && tamanio <= 5) this.tamanio = tamanio;
        else this.tamanio = 1;
    }

    public int getTamanio() { return tamanio; }

    @Override
    public void actuar(GestorEcosistema eco) {
        intentarReproduccion(eco);
    }

    public double serComida() {
        double valorNutritivo = this.tamanio * 10;
        setEnergia(0);
        setViva(false);
        return valorNutritivo;
    }

    @Override
    public void reproducirse(GestorEcosistema eco) {
        Clima clima = eco.getClimaActual();
        double multiplicador = 1.0;
        
        if (clima == Clima.SOLEADO) multiplicador = 1.5;
        if (clima == Clima.LLUVIOSO) multiplicador = 2.0;
        if (clima == Clima.SEQUIA) multiplicador = 0.5;

        int generadas = (int) multiplicador;
        if (Math.random() < (multiplicador - generadas)) {
            generadas++;
        }

        for (int i = 0; i < generadas; i++) {
            eco.agregarEntidad("planta");
        }
        if (generadas > 0) {
            eco.registrarEvento("Planta '" + getNombre() + "' se reprodujo (" + generadas + " nuevas).");
        }
    }

    @Override
    public boolean puedeReproducirse() {
        return getEnergia() >= 30;
    }
}

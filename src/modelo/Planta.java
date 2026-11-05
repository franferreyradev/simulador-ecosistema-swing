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
        if (!eco.puedeReproducirPlanta()) {
            return; // Límite de capacidad de carga ecológica
        }

        Clima clima = eco.getClimaActual();
        double probabilidadBase = 0.50;
        
        if (clima == Clima.SOLEADO) probabilidadBase = 0.65;
        if (clima == Clima.LLUVIOSO) probabilidadBase = 0.80;
        if (clima == Clima.SEQUIA) probabilidadBase = 0.25;

        // Probabilidad según clima para evitar crecimiento descontrolado
        if (Math.random() < probabilidadBase) {
            setEnergia(getEnergia() - 15); // Costo biológico de reproducirse
            eco.agregarEntidad("planta");
            eco.registrarEvento("Planta '" + getNombre() + "' se reprodujo (-15 energía).");
        }
    }

    @Override
    public boolean puedeReproducirse() {
        return getEnergia() >= 30;
    }
}

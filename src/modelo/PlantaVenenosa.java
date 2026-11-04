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
}

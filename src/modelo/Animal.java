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
        // Desplazamiento del animal
    }

    public int getVelocidad() { return velocidad; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    @Override
    public boolean estaVivo() { return isViva(); }

    @Override
    public void morir() { setViva(false); }
}

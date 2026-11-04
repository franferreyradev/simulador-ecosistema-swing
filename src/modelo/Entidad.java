package modelo;

public abstract class Entidad {
    private String nombre;
    private double energia;
    private int edad;
    private boolean viva;

    public Entidad(String nombre, double energia) {
        this.nombre = nombre;
        setEnergia(energia);
        this.edad = 0;
        this.viva = true;
    }

    public abstract void actuar(GestorEcosistema eco);

    public String obtenerEstado() {
        return nombre + " | Edad: " + edad + " | Energía: " + Math.round(energia * 10.0) / 10.0;
    }

    public void envejecer() {
        this.edad++;
        setEnergia(this.energia - 1);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getEnergia() { return energia; }
    public void setEnergia(double energia) {
        if (energia < 0) {
            this.energia = 0;
        } else {
            this.energia = energia;
        }
    }

    public boolean isViva() { return viva; }
    public void setViva(boolean viva) { this.viva = viva; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}

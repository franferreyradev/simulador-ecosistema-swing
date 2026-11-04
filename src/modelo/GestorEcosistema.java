/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Comparator;

public class GestorEcosistema {
    private ArrayList<Planta> plantas = new ArrayList<>();
    private ArrayList<Conejo> conejos = new ArrayList<>();
    private ArrayList<Lobo> lobos = new ArrayList<>();
    private Clima climaActual;
    private int turnoActual = 1;
    private int idGenerador = 1;
    private int lobosTotalesAgregados = 0;

    private int eventosTurnoActual = 0;
    private int maxEventos = 0;
    private int turnoMayorActividad = 1;
    private int nacimientosPlantas = 0, muertesPlantas = 0;
    private int nacimientosConejos = 0, muertesConejos = 0;
    private int nacimientosLobos = 0, muertesLobos = 0;

    private int maxPlantas = 0, turnoMaxPlantas = 1, minPlantas = Integer.MAX_VALUE, turnoMinPlantas = 1;
    private int maxConejos = 0, turnoMaxConejos = 1, minConejos = Integer.MAX_VALUE, turnoMinConejos = 1;
    private int maxLobos = 0, turnoMaxLobos = 1, minLobos = Integer.MAX_VALUE, turnoMinLobos = 1;

    public GestorEcosistema(Clima climaInicial) {
        this.climaActual = climaInicial;
    }

    public ArrayList<Planta> getPlantas() { return plantas; }
    public ArrayList<Conejo> getConejos() { return conejos; }
    public ArrayList<Lobo> getLobos() { return lobos; }
    public Clima getClimaActual() { return climaActual; }
    public int getTurnoActual() { return turnoActual; }

    public void registrarEvento() {
        this.eventosTurnoActual++;
    }

    public void agregarEntidad(String tipo) {
        agregarEntidad(tipo, 50.0 + (Math.random() * 50));
    }

    public void agregarEntidad(String tipo, double energia) {
        switch (tipo.toLowerCase()) {
            case "planta":
                int tamanioRandom = 1 + (int)(Math.random() * 5);
                if (Math.random() < 0.20) {
                    plantas.add(new PlantaVenenosa("HongoToxico-" + idGenerador++, energia, tamanioRandom));
                } else {
                    plantas.add(new Planta("Planta-" + idGenerador++, energia, tamanioRandom));
                }
                nacimientosPlantas++;
                break;
            case "conejo":
                conejos.add(new Conejo("Conejo-" + idGenerador++, energia));
                nacimientosConejos++;
                break;
            case "lobo":
                if (lobosTotalesAgregados < 5) {
                    lobos.add(new Lobo("Lobo-" + idGenerador++, energia));
                    lobosTotalesAgregados++;
                    nacimientosLobos++;
                } else {
                    System.out.println("Límite alcanzado: no se pueden agregar más de 5 lobos en toda la simulación.");
                }
                break;
            case "plantavenenosa":
                int tamRandom = 1 + (int)(Math.random() * 5);
                plantas.add(new PlantaVenenosa("HongoToxico-" + idGenerador++, energia, tamRandom));
                nacimientosPlantas++;
                break;
        }
    }

    public void cambiarClima(Clima nuevo) {
        this.climaActual = nuevo;
        System.out.println("El clima ha cambiado a: " + nuevo);
    }

    public boolean ecosistemaColapsado() {
        return plantas.isEmpty() || conejos.isEmpty() || lobos.isEmpty();
    }

    public void procesarTurno() {
        System.out.println("\n=== TURNO " + turnoActual + " | Clima: " + climaActual + " ===");
        System.out.println("Plantas: " + plantas.size() + " | Conejos: " + conejos.size() + " | Lobos: " + lobos.size());
        System.out.println("Eventos --");

        ArrayList<Reproducible> reproducibles = new ArrayList<>();
        reproducibles.addAll(plantas);
        reproducibles.addAll(conejos);
        
        for (Reproducible r : new ArrayList<>(reproducibles)) {
            if (climaActual == Clima.INVIERNO && r instanceof Planta) continue; 
            r.intentarReproduccion(this);
        }

        for (Conejo c : new ArrayList<>(conejos)) {
            if (c.isViva()) c.actuar(this);
        }

        for (Lobo l : new ArrayList<>(lobos)) {
            if (l.isViva()) l.actuar(this);
        }

        ArrayList<Entidad> todasLasEntidades = new ArrayList<>();
        todasLasEntidades.addAll(plantas);
        todasLasEntidades.addAll(conejos);
        todasLasEntidades.addAll(lobos);

        for (Entidad e : todasLasEntidades) {
            e.envejecer();
            
            if (e instanceof Conejo) {
                if (climaActual == Clima.SOLEADO) e.setEnergia(e.getEnergia() + 5);
                if (climaActual == Clima.LLUVIOSO) e.setEnergia(e.getEnergia() + 3);
                if (climaActual == Clima.SEQUIA) e.setEnergia(e.getEnergia() - 5);
                if (climaActual == Clima.INVIERNO) e.setEnergia(e.getEnergia() - 8);
            }
            if (e instanceof Lobo && climaActual == Clima.LLUVIOSO) {
                e.setEnergia(e.getEnergia() - 5);
            }

            if (e instanceof Mortal) {
                Mortal m = (Mortal) e;
                m.verificarMuerte(e.getEnergia());
            } else if (e.getEnergia() <= 0) {
                e.setViva(false);
            }
        }

        limpiarMuertos();
        mostrarEstado();
        
        if (plantas.size() > maxPlantas) { maxPlantas = plantas.size(); turnoMaxPlantas = turnoActual; }
        if (plantas.size() < minPlantas) { minPlantas = plantas.size(); turnoMinPlantas = turnoActual; }
        
        if (conejos.size() > maxConejos) { maxConejos = conejos.size(); turnoMaxConejos = turnoActual; }
        if (conejos.size() < minConejos) { minConejos = conejos.size(); turnoMinConejos = turnoActual; }
        
        if (lobos.size() > maxLobos) { maxLobos = lobos.size(); turnoMaxLobos = turnoActual; }
        if (lobos.size() < minLobos) { minLobos = lobos.size(); turnoMinLobos = turnoActual; }
        
        if (eventosTurnoActual > maxEventos) {
            maxEventos = eventosTurnoActual;
            turnoMayorActividad = turnoActual;
        }
        eventosTurnoActual = 0; 
        
        turnoActual++;
    }

    private void limpiarMuertos() {
        for (Planta p : plantas) if (!p.isViva()) { muertesPlantas++; registrarEvento(); }
        for (Conejo c : conejos) if (!c.isViva()) { muertesConejos++; registrarEvento(); System.out.println("Conejo '" + c.getNombre() + "' murió."); }
        for (Lobo l : lobos) if (!l.isViva()) { muertesLobos++; registrarEvento(); System.out.println("Lobo '" + l.getNombre() + "' murió."); }

        plantas.removeIf(p -> !p.isViva());
        conejos.removeIf(c -> !c.isViva());
        lobos.removeIf(l -> !l.isViva());
    }

    public void mostrarEstado() {
        System.out.println("Estado: Plantas: " + plantas.size() + " | Conejos: " + conejos.size() + " | Lobos: " + lobos.size());
    }

    public void generarReporteFinal() {
        System.out.println("\n=== REPORTE FINAL ===");
        if (ecosistemaColapsado()) {
            System.out.print("Causa de fin: COLAPSO DEL ECOSISTEMA - Se extinguió la población de: ");
            if (plantas.isEmpty()) System.out.print("Plantas ");
            if (conejos.isEmpty()) System.out.print("Conejos ");
            if (lobos.isEmpty()) System.out.print("Lobos ");
            System.out.println();
        } else {
            System.out.println("Causa de fin: SE ALCANZARON LOS TURNOS CONFIGURADOS");
        }
        
        System.out.println("\n--- ESTADÍSTICAS GLOBALES ---");
        System.out.println("Turno de mayor actividad: Turno " + turnoMayorActividad + " (con " + maxEventos + " eventos registrados)");
        System.out.println("Nacimientos Totales -> Plantas: " + nacimientosPlantas + " | Conejos: " + nacimientosConejos + " | Lobos: " + nacimientosLobos);
        System.out.println("Muertes Totales     -> Plantas: " + muertesPlantas + " | Conejos: " + muertesConejos + " | Lobos: " + muertesLobos);

        System.out.println("\n--- HISTORIAL DE POBLACIONES ---");
        System.out.println("Plantas -> Máximo: " + maxPlantas + " (Turno " + turnoMaxPlantas + ") | Mínimo: " + (minPlantas == Integer.MAX_VALUE ? 0 : minPlantas) + " (Turno " + turnoMinPlantas + ")");
        System.out.println("Conejos -> Máximo: " + maxConejos + " (Turno " + turnoMaxConejos + ") | Mínimo: " + (minConejos == Integer.MAX_VALUE ? 0 : minConejos) + " (Turno " + turnoMinConejos + ")");
        System.out.println("Lobos   -> Máximo: " + maxLobos + " (Turno " + turnoMaxLobos + ") | Mínimo: " + (minLobos == Integer.MAX_VALUE ? 0 : minLobos) + " (Turno " + turnoMinLobos + ")");

        System.out.println("\n--- ENTIDADES DESTACADAS (AL FINALIZAR) ---");
        Planta plantaLongeva = plantas.stream().max(Comparator.comparingInt(Entidad::getEdad)).orElse(null);
        Conejo conejoLongevo = conejos.stream().max(Comparator.comparingInt(Entidad::getEdad)).orElse(null);
        Lobo loboLongevo = lobos.stream().max(Comparator.comparingInt(Entidad::getEdad)).orElse(null);
        Lobo mejorCazador = lobos.stream().max(Comparator.comparingInt(Lobo::getExitosCaza)).orElse(null);

        if (plantaLongeva != null) System.out.println("Planta más longeva: " + plantaLongeva.getNombre() + " (" + plantaLongeva.getEdad() + " turnos)");
        if (conejoLongevo != null) System.out.println("Conejo más longevo: " + conejoLongevo.getNombre() + " (" + conejoLongevo.getEdad() + " turnos)");
        if (loboLongevo != null) System.out.println("Lobo más longevo: " + loboLongevo.getNombre() + " (" + loboLongevo.getEdad() + " turnos)");
        if (mejorCazador != null) System.out.println("Lobo con más cacerías: " + mejorCazador.getNombre() + " (" + mejorCazador.getExitosCaza() + " éxitos)");

        System.out.println("\n--- LISTA DE ENTIDADES PELIGROSAS ---");
        ArrayList<Peligroso> peligrosos = new ArrayList<>();
        
        for (Lobo l : lobos) peligrosos.add(l);
        for (Planta p : plantas) {
            if (p instanceof Peligroso) peligrosos.add((Peligroso) p);
        }
        
        peligrosos.sort((p1, p2) -> Integer.compare(p2.getNivelPeligro(), p1.getNivelPeligro()));
        
        for (Peligroso p : peligrosos) {
            if (p instanceof Entidad) {
                Entidad e = (Entidad) p;
                System.out.println(e.getNombre() + " | Nivel de Peligro: " + p.getNivelPeligro());
            }
        }
    }
}

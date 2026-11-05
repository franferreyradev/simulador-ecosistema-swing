package modelo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Gestor central del Ecosistema (Modelo de dominio).
 * Encapsula el estado, las reglas de evolución turno a turno,
 * la recolección de eventos y el cálculo de métricas para el reporte final.
 * CERO dependencias de Swing, Scanner o System.out.
 */
public class GestorEcosistema {
    private ArrayList<Planta> plantas = new ArrayList<>();
    private ArrayList<Conejo> conejos = new ArrayList<>();
    private ArrayList<Lobo> lobos = new ArrayList<>();
    private Clima climaActual;
    private int turnoActual = 1;
    private int idGenerador = 1;
    private int lobosTotalesAgregados = 0;

    // Registro de eventos para la Vista
    private ArrayList<String> eventosDelTurno = new ArrayList<>();
    private int eventosTurnoActual = 0;
    private int maxEventos = 0;
    private int turnoMayorActividad = 1;

    // Métricas acumuladas
    private int nacimientosPlantas = 0, muertesPlantas = 0;
    private int nacimientosConejos = 0, muertesConejos = 0;
    private int nacimientosLobos = 0, muertesLobos = 0;

    // Historial de picos de población
    private int maxPlantas = 0, turnoMaxPlantas = 1, minPlantas = Integer.MAX_VALUE, turnoMinPlantas = 1;
    private int maxConejos = 0, turnoMaxConejos = 1, minConejos = Integer.MAX_VALUE, turnoMinConejos = 1;
    private int maxLobos = 0, turnoMaxLobos = 1, minLobos = Integer.MAX_VALUE, turnoMinLobos = 1;

    public GestorEcosistema(Clima climaInicial) {
        this.climaActual = climaInicial != null ? climaInicial : Clima.SOLEADO;
    }

    public GestorEcosistema() {
        this(Clima.SOLEADO);
    }

    // ── Capacidad de Carga Ecológica ───────────────────────────
    public static final int CAPACIDAD_MAX_PLANTAS = 60;
    public static final int CAPACIDAD_MAX_CONEJOS = 35;

    public boolean puedeReproducirPlanta() {
        return plantas.size() < CAPACIDAD_MAX_PLANTAS;
    }

    public boolean puedeReproducirConejo() {
        return conejos.size() < CAPACIDAD_MAX_CONEJOS;
    }

    // ── Registro de Eventos ────────────────────────────────────
    public void registrarEvento(String descripcion) {
        this.eventosTurnoActual++;
        this.eventosDelTurno.add(descripcion);
    }

    // ── Altas de Entidades ─────────────────────────────────────
    public boolean agregarEntidad(String tipo) {
        return agregarEntidad(tipo, 50.0 + (Math.random() * 50));
    }

    public boolean agregarEntidad(String tipo, double energia) {
        switch (tipo.toLowerCase()) {
            case "planta":
                int tamanioRandom = 1 + (int) (Math.random() * 5);
                if (Math.random() < 0.20) {
                    plantas.add(new PlantaVenenosa("HongoToxico-" + idGenerador++, energia, tamanioRandom));
                } else {
                    plantas.add(new Planta("Planta-" + idGenerador++, energia, tamanioRandom));
                }
                nacimientosPlantas++;
                return true;

            case "conejo":
                conejos.add(new Conejo("Conejo-" + idGenerador++, energia));
                nacimientosConejos++;
                return true;

            case "lobo":
                if (lobosTotalesAgregados < 5) {
                    lobos.add(new Lobo("Lobo-" + idGenerador++, energia));
                    lobosTotalesAgregados++;
                    nacimientosLobos++;
                    return true;
                }
                return false;

            case "plantavenenosa":
                int tamRandom = 1 + (int) (Math.random() * 5);
                plantas.add(new PlantaVenenosa("HongoToxico-" + idGenerador++, energia, tamRandom));
                nacimientosPlantas++;
                return true;
        }
        return false;
    }

    public void cambiarClima(Clima nuevo) {
        if (nuevo != null && nuevo != this.climaActual) {
            this.climaActual = nuevo;
            registrarEvento("El clima ha cambiado a: " + nuevo);
        }
    }

    public boolean puedeAgregarLobo() {
        return lobosTotalesAgregados < 5;
    }

    public boolean ecosistemaColapsado() {
        return plantas.isEmpty() || conejos.isEmpty() || lobos.isEmpty();
    }

    public String obtenerMotivoColapso() {
        if (!ecosistemaColapsado()) return "";
        StringBuilder sb = new StringBuilder("Extinción de: ");
        if (plantas.isEmpty()) sb.append("Plantas ");
        if (conejos.isEmpty()) sb.append("Conejos ");
        if (lobos.isEmpty()) sb.append("Lobos ");
        return sb.toString().trim();
    }

    // ── Simulación del Turno ───────────────────────────────────
    public ArrayList<String> procesarTurno() {
        eventosDelTurno.clear();
        eventosTurnoActual = 0;

        // 1. Reproducción de plantas y conejos
        ArrayList<Reproducible> reproducibles = new ArrayList<>();
        reproducibles.addAll(plantas);
        reproducibles.addAll(conejos);

        for (Reproducible r : new ArrayList<>(reproducibles)) {
            if (climaActual == Clima.INVIERNO && r instanceof Planta) continue;
            r.intentarReproduccion(this);
        }

        // 2. Conejos buscan comida
        for (Conejo c : new ArrayList<>(conejos)) {
            if (c.isViva()) c.actuar(this);
        }

        // 3. Lobos cazan conejos
        for (Lobo l : new ArrayList<>(lobos)) {
            if (l.isViva()) l.actuar(this);
        }

        // 4. Envejecimiento y consumo de energía según clima
        ArrayList<Entidad> todas = obtenerTodasLasEntidadesVivas();
        for (Entidad e : todas) {
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

        // 5. Limpieza de muertos
        limpiarMuertos();

        // 6. Actualización de picos históricos y actividad
        actualizarEstadisticasHistoricas();

        turnoActual++;
        return new ArrayList<>(eventosDelTurno);
    }

    private void limpiarMuertos() {
        for (Planta p : plantas) {
            if (!p.isViva()) {
                muertesPlantas++;
                registrarEvento("Planta '" + p.getNombre() + "' murió.");
            }
        }
        for (Conejo c : conejos) {
            if (!c.isViva()) {
                muertesConejos++;
                registrarEvento("Conejo '" + c.getNombre() + "' murió.");
            }
        }
        for (Lobo l : lobos) {
            if (!l.isViva()) {
                muertesLobos++;
                registrarEvento("Lobo '" + l.getNombre() + "' murió.");
            }
        }

        plantas.removeIf(p -> !p.isViva());
        conejos.removeIf(c -> !c.isViva());
        lobos.removeIf(l -> !l.isViva());
    }

    private void actualizarEstadisticasHistoricas() {
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
    }

    public ArrayList<Entidad> obtenerTodasLasEntidadesVivas() {
        ArrayList<Entidad> lista = new ArrayList<>();
        lista.addAll(plantas);
        lista.addAll(conejos);
        lista.addAll(lobos);
        return lista;
    }

    // ── Reporte Final ──────────────────────────────────────────
    public ReporteFinal generarReporteFinal() {
        ReporteFinal reporte = new ReporteFinal();

        if (ecosistemaColapsado()) {
            reporte.setCausaFin("COLAPSO DEL ECOSISTEMA (" + obtenerMotivoColapso() + ")");
        } else {
            reporte.setCausaFin("SE COMPLETARON LOS TURNOS CONFIGURADOS");
        }

        reporte.setTurnoMayorActividad(turnoMayorActividad);
        reporte.setMaxEventosTurno(maxEventos);

        reporte.setNacimientosPlantas(nacimientosPlantas);
        reporte.setMuertesPlantas(muertesPlantas);
        reporte.setNacimientosConejos(nacimientosConejos);
        reporte.setMuertesConejos(muertesConejos);
        reporte.setNacimientosLobos(nacimientosLobos);
        reporte.setMuertesLobos(muertesLobos);

        reporte.setMaxPlantas(maxPlantas);
        reporte.setTurnoMaxPlantas(turnoMaxPlantas);
        reporte.setMinPlantas(minPlantas == Integer.MAX_VALUE ? 0 : minPlantas);
        reporte.setTurnoMinPlantas(turnoMinPlantas);

        reporte.setMaxConejos(maxConejos);
        reporte.setTurnoMaxConejos(turnoMaxConejos);
        reporte.setMinConejos(minConejos == Integer.MAX_VALUE ? 0 : minConejos);
        reporte.setTurnoMinConejos(turnoMinConejos);

        reporte.setMaxLobos(maxLobos);
        reporte.setTurnoMaxLobos(turnoMaxLobos);
        reporte.setMinLobos(minLobos == Integer.MAX_VALUE ? 0 : minLobos);
        reporte.setTurnoMinLobos(turnoMinLobos);

        Planta plantaLongeva = plantas.stream().max(Comparator.comparingInt(Entidad::getEdad)).orElse(null);
        Conejo conejoLongevo = conejos.stream().max(Comparator.comparingInt(Entidad::getEdad)).orElse(null);
        Lobo loboLongevo = lobos.stream().max(Comparator.comparingInt(Entidad::getEdad)).orElse(null);
        Lobo mejorCazador = lobos.stream().max(Comparator.comparingInt(Lobo::getExitosCaza)).orElse(null);

        reporte.setPlantaMasLongeva(plantaLongeva != null ? plantaLongeva.getNombre() + " (" + plantaLongeva.getEdad() + " turnos)" : "Ninguna");
        reporte.setConejoMasLongevo(conejoLongevo != null ? conejoLongevo.getNombre() + " (" + conejoLongevo.getEdad() + " turnos)" : "Ninguno");
        reporte.setLoboMasLongevo(loboLongevo != null ? loboLongevo.getNombre() + " (" + loboLongevo.getEdad() + " turnos)" : "Ninguno");
        reporte.setMejorCazador(mejorCazador != null ? mejorCazador.getNombre() + " (" + mejorCazador.getExitosCaza() + " cacerías exitosas)" : "Ninguno");

        ArrayList<Peligroso> peligrosos = new ArrayList<>();
        for (Lobo l : lobos) peligrosos.add(l);
        for (Planta p : plantas) {
            if (p instanceof Peligroso) peligrosos.add((Peligroso) p);
        }
        peligrosos.sort((p1, p2) -> Integer.compare(p2.getNivelPeligro(), p1.getNivelPeligro()));

        ArrayList<String> listaPeligrosos = new ArrayList<>();
        for (Peligroso p : peligrosos) {
            if (p instanceof Entidad) {
                Entidad e = (Entidad) p;
                listaPeligrosos.add(e.getNombre() + " (Peligro: " + p.getNivelPeligro() + ")");
            }
        }
        reporte.setEntidadesPeligrosas(listaPeligrosos);

        return reporte;
    }

    // ── Getters y Setters para Persistencia (DAO) ──────────────
    public ArrayList<Planta> getPlantas() { return plantas; }
    public void setPlantas(ArrayList<Planta> plantas) { this.plantas = plantas; }

    public ArrayList<Conejo> getConejos() { return conejos; }
    public void setConejos(ArrayList<Conejo> conejos) { this.conejos = conejos; }

    public ArrayList<Lobo> getLobos() { return lobos; }
    public void setLobos(ArrayList<Lobo> lobos) { this.lobos = lobos; }

    public Clima getClimaActual() { return climaActual; }
    public void setClimaActual(Clima climaActual) { this.climaActual = climaActual; }

    public int getTurnoActual() { return turnoActual; }
    public void setTurnoActual(int turnoActual) { this.turnoActual = turnoActual; }

    public int getIdGenerador() { return idGenerador; }
    public void setIdGenerador(int idGenerador) { this.idGenerador = idGenerador; }

    public int getLobosTotalesAgregados() { return lobosTotalesAgregados; }
    public void setLobosTotalesAgregados(int lobosTotalesAgregados) { this.lobosTotalesAgregados = lobosTotalesAgregados; }

    public ArrayList<String> getEventosDelTurno() { return eventosDelTurno; }
    public int getEventosTurnoActual() { return eventosTurnoActual; }
    public void setEventosTurnoActual(int eventosTurnoActual) { this.eventosTurnoActual = eventosTurnoActual; }

    public int getMaxEventos() { return maxEventos; }
    public void setMaxEventos(int maxEventos) { this.maxEventos = maxEventos; }

    public int getTurnoMayorActividad() { return turnoMayorActividad; }
    public void setTurnoMayorActividad(int turnoMayorActividad) { this.turnoMayorActividad = turnoMayorActividad; }

    public int getNacimientosPlantas() { return nacimientosPlantas; }
    public void setNacimientosPlantas(int nacimientosPlantas) { this.nacimientosPlantas = nacimientosPlantas; }

    public int getMuertesPlantas() { return muertesPlantas; }
    public void setMuertesPlantas(int muertesPlantas) { this.muertesPlantas = muertesPlantas; }

    public int getNacimientosConejos() { return nacimientosConejos; }
    public void setNacimientosConejos(int nacimientosConejos) { this.nacimientosConejos = nacimientosConejos; }

    public int getMuertesConejos() { return muertesConejos; }
    public void setMuertesConejos(int muertesConejos) { this.muertesConejos = muertesConejos; }

    public int getNacimientosLobos() { return nacimientosLobos; }
    public void setNacimientosLobos(int nacimientosLobos) { this.nacimientosLobos = nacimientosLobos; }

    public int getMuertesLobos() { return muertesLobos; }
    public void setMuertesLobos(int muertesLobos) { this.muertesLobos = muertesLobos; }

    public int getMaxPlantas() { return maxPlantas; }
    public void setMaxPlantas(int maxPlantas) { this.maxPlantas = maxPlantas; }

    public int getTurnoMaxPlantas() { return turnoMaxPlantas; }
    public void setTurnoMaxPlantas(int turnoMaxPlantas) { this.turnoMaxPlantas = turnoMaxPlantas; }

    public int getMinPlantas() { return minPlantas; }
    public void setMinPlantas(int minPlantas) { this.minPlantas = minPlantas; }

    public int getTurnoMinPlantas() { return turnoMinPlantas; }
    public void setTurnoMinPlantas(int turnoMinPlantas) { this.turnoMinPlantas = turnoMinPlantas; }

    public int getMaxConejos() { return maxConejos; }
    public void setMaxConejos(int maxConejos) { this.maxConejos = maxConejos; }

    public int getTurnoMaxConejos() { return turnoMaxConejos; }
    public void setTurnoMaxConejos(int turnoMaxConejos) { this.turnoMaxConejos = turnoMaxConejos; }

    public int getMinConejos() { return minConejos; }
    public void setMinConejos(int minConejos) { this.minConejos = minConejos; }

    public int getTurnoMinConejos() { return turnoMinConejos; }
    public void setTurnoMinConejos(int turnoMinConejos) { this.turnoMinConejos = turnoMinConejos; }

    public int getMaxLobos() { return maxLobos; }
    public void setMaxLobos(int maxLobos) { this.maxLobos = maxLobos; }

    public int getTurnoMaxLobos() { return turnoMaxLobos; }
    public void setTurnoMaxLobos(int turnoMaxLobos) { this.turnoMaxLobos = turnoMaxLobos; }

    public int getMinLobos() { return minLobos; }
    public void setMinLobos(int minLobos) { this.minLobos = minLobos; }

    public int getTurnoMinLobos() { return turnoMinLobos; }
    public void setTurnoMinLobos(int turnoMinLobos) { this.turnoMinLobos = turnoMinLobos; }
}

package modelo;

import java.util.ArrayList;

/**
 * DTO que encapsula todos los datos estadísticos y resultados finales
 * de la simulación para ser presentados de forma prolija en la Vista.
 */
public class ReporteFinal {
    private String causaFin;
    private int turnoMayorActividad;
    private int maxEventosTurno;
    
    // Nacimientos y muertes
    private int nacimientosPlantas;
    private int muertesPlantas;
    private int nacimientosConejos;
    private int muertesConejos;
    private int nacimientosLobos;
    private int muertesLobos;
    
    // Historial y picos de población
    private int maxPlantas;
    private int turnoMaxPlantas;
    private int minPlantas;
    private int turnoMinPlantas;
    
    private int maxConejos;
    private int turnoMaxConejos;
    private int minConejos;
    private int turnoMinConejos;
    
    private int maxLobos;
    private int turnoMaxLobos;
    private int minLobos;
    private int turnoMinLobos;
    
    // Entidades destacadas
    private String plantaMasLongeva;
    private String conejoMasLongevo;
    private String loboMasLongevo;
    private String mejorCazador;
    
    // Entidades peligrosas
    private ArrayList<String> entidadesPeligrosas = new ArrayList<>();

    public ReporteFinal() {
    }

    public String getCausaFin() { return causaFin; }
    public void setCausaFin(String causaFin) { this.causaFin = causaFin; }

    public int getTurnoMayorActividad() { return turnoMayorActividad; }
    public void setTurnoMayorActividad(int turnoMayorActividad) { this.turnoMayorActividad = turnoMayorActividad; }

    public int getMaxEventosTurno() { return maxEventosTurno; }
    public void setMaxEventosTurno(int maxEventosTurno) { this.maxEventosTurno = maxEventosTurno; }

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

    public String getPlantaMasLongeva() { return plantaMasLongeva; }
    public void setPlantaMasLongeva(String plantaMasLongeva) { this.plantaMasLongeva = plantaMasLongeva; }

    public String getConejoMasLongevo() { return conejoMasLongevo; }
    public void setConejoMasLongevo(String conejoMasLongevo) { this.conejoMasLongevo = conejoMasLongevo; }

    public String getLoboMasLongevo() { return loboMasLongevo; }
    public void setLoboMasLongevo(String loboMasLongevo) { this.loboMasLongevo = loboMasLongevo; }

    public String getMejorCazador() { return mejorCazador; }
    public void setMejorCazador(String mejorCazador) { this.mejorCazador = mejorCazador; }

    public ArrayList<String> getEntidadesPeligrosas() { return entidadesPeligrosas; }
    public void setEntidadesPeligrosas(ArrayList<String> entidadesPeligrosas) { this.entidadesPeligrosas = entidadesPeligrosas; }
}

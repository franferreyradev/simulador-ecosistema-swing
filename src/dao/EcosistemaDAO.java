package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import modelo.*;

/**
 * Responsable exclusivo de la persistencia de la simulación en texto plano.
 * Basado en el patrón DAO de la Clase 16.
 */
public class EcosistemaDAO {

    private static final String ARCHIVO = "simulacion.txt";
    private static final String SEPARADOR = "|";

    // ═════════════════════════════════════════════════════════════
    // GUARDAR: GestorEcosistema -> simulacion.txt
    // ═════════════════════════════════════════════════════════════
    public boolean guardarSimulacion(GestorEcosistema eco) {
        if (eco == null) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            // 1. Línea de Estado General
            // METADATA|turnoActual|clima|idGenerador|lobosAgregados|turnoMayorActividad|maxEventos
            bw.write("METADATA" + SEPARADOR
                    + eco.getTurnoActual() + SEPARADOR
                    + eco.getClimaActual().name() + SEPARADOR
                    + eco.getIdGenerador() + SEPARADOR
                    + eco.getLobosTotalesAgregados() + SEPARADOR
                    + eco.getTurnoMayorActividad() + SEPARADOR
                    + eco.getMaxEventos());
            bw.newLine();

            // 2. Línea de Estadísticas Acumuladas
            // STATS|nacPlantas|muePlantas|nacConejos|mueConejos|nacLobos|mueLobos
            bw.write("STATS" + SEPARADOR
                    + eco.getNacimientosPlantas() + SEPARADOR
                    + eco.getMuertesPlantas() + SEPARADOR
                    + eco.getNacimientosConejos() + SEPARADOR
                    + eco.getMuertesConejos() + SEPARADOR
                    + eco.getNacimientosLobos() + SEPARADOR
                    + eco.getMuertesLobos());
            bw.newLine();

            // 3. Línea de Picos de Población (necesaria para el reporte final)
            // PICOS|maxPlantas|turnoMaxPlantas|minPlantas|turnoMinPlantas|maxConejos|turnoMaxConejos|minConejos|turnoMinConejos|maxLobos|turnoMaxLobos|minLobos|turnoMinLobos
            bw.write("PICOS" + SEPARADOR
                    + eco.getMaxPlantas() + SEPARADOR
                    + eco.getTurnoMaxPlantas() + SEPARADOR
                    + eco.getMinPlantas() + SEPARADOR
                    + eco.getTurnoMinPlantas() + SEPARADOR
                    + eco.getMaxConejos() + SEPARADOR
                    + eco.getTurnoMaxConejos() + SEPARADOR
                    + eco.getMinConejos() + SEPARADOR
                    + eco.getTurnoMinConejos() + SEPARADOR
                    + eco.getMaxLobos() + SEPARADOR
                    + eco.getTurnoMaxLobos() + SEPARADOR
                    + eco.getMinLobos() + SEPARADOR
                    + eco.getTurnoMinLobos());
            bw.newLine();

            // 4. Entidades Vivas: Planta, PlantaVenenosa, Conejo, Lobo
            for (Planta p : eco.getPlantas()) {
                if (p instanceof PlantaVenenosa) {
                    bw.write("PLANTAVENENOSA" + SEPARADOR + p.getNombre() + SEPARADOR + p.getEnergia() + SEPARADOR + p.getEdad() + SEPARADOR + p.getTamanio());
                } else {
                    bw.write("PLANTA" + SEPARADOR + p.getNombre() + SEPARADOR + p.getEnergia() + SEPARADOR + p.getEdad() + SEPARADOR + p.getTamanio());
                }
                bw.newLine();
            }

            for (Conejo c : eco.getConejos()) {
                bw.write("CONEJO" + SEPARADOR + c.getNombre() + SEPARADOR + c.getEnergia() + SEPARADOR + c.getEdad() + SEPARADOR + "0");
                bw.newLine();
            }

            for (Lobo l : eco.getLobos()) {
                bw.write("LOBO" + SEPARADOR + l.getNombre() + SEPARADOR + l.getEnergia() + SEPARADOR + l.getEdad() + SEPARADOR + l.getExitosCaza());
                bw.newLine();
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + e.getMessage());
            return false;
        }
    }

    public boolean existeArchivo() {
        return new File(ARCHIVO).exists();
    }
}

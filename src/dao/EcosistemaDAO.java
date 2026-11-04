package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    // ═════════════════════════════════════════════════════════════
    // LEER: simulacion.txt -> GestorEcosistema
    // ═════════════════════════════════════════════════════════════
    public GestorEcosistema cargarSimulacion() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return null; // El controlador sabrá que no hay simulación guardada
        }

        GestorEcosistema eco = new GestorEcosistema();
        ArrayList<Planta> plantas = new ArrayList<>();
        ArrayList<Conejo> conejos = new ArrayList<>();
        ArrayList<Lobo> lobos = new ArrayList<>();
        boolean tieneMetadata = false;
        boolean tieneStats = false;
        boolean tienePicos = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split("\\" + SEPARADOR);
                String tipo = partes[0].toUpperCase();

                switch (tipo) {
                    case "METADATA":
                        eco.setTurnoActual(Integer.parseInt(partes[1]));
                        eco.setClimaActual(Clima.valueOf(partes[2]));
                        eco.setIdGenerador(Integer.parseInt(partes[3]));
                        eco.setLobosTotalesAgregados(Integer.parseInt(partes[4]));
                        eco.setTurnoMayorActividad(Integer.parseInt(partes[5]));
                        eco.setMaxEventos(Integer.parseInt(partes[6]));
                        tieneMetadata = true;
                        break;

                    case "STATS":
                        eco.setNacimientosPlantas(Integer.parseInt(partes[1]));
                        eco.setMuertesPlantas(Integer.parseInt(partes[2]));
                        eco.setNacimientosConejos(Integer.parseInt(partes[3]));
                        eco.setMuertesConejos(Integer.parseInt(partes[4]));
                        eco.setNacimientosLobos(Integer.parseInt(partes[5]));
                        eco.setMuertesLobos(Integer.parseInt(partes[6]));
                        tieneStats = true;
                        break;

                    case "PICOS":
                        eco.setMaxPlantas(Integer.parseInt(partes[1]));
                        eco.setTurnoMaxPlantas(Integer.parseInt(partes[2]));
                        eco.setMinPlantas(Integer.parseInt(partes[3]));
                        eco.setTurnoMinPlantas(Integer.parseInt(partes[4]));
                        eco.setMaxConejos(Integer.parseInt(partes[5]));
                        eco.setTurnoMaxConejos(Integer.parseInt(partes[6]));
                        eco.setMinConejos(Integer.parseInt(partes[7]));
                        eco.setTurnoMinConejos(Integer.parseInt(partes[8]));
                        eco.setMaxLobos(Integer.parseInt(partes[9]));
                        eco.setTurnoMaxLobos(Integer.parseInt(partes[10]));
                        eco.setMinLobos(Integer.parseInt(partes[11]));
                        eco.setTurnoMinLobos(Integer.parseInt(partes[12]));
                        tienePicos = true;
                        break;

                    case "PLANTA":
                        Planta p = new Planta(partes[1], Double.parseDouble(partes[2]), Integer.parseInt(partes[4]));
                        p.setEdad(Integer.parseInt(partes[3]));
                        plantas.add(p);
                        break;

                    case "PLANTAVENENOSA":
                        PlantaVenenosa pv = new PlantaVenenosa(partes[1], Double.parseDouble(partes[2]), Integer.parseInt(partes[4]));
                        pv.setEdad(Integer.parseInt(partes[3]));
                        plantas.add(pv);
                        break;

                    case "CONEJO":
                        Conejo c = new Conejo(partes[1], Double.parseDouble(partes[2]));
                        c.setEdad(Integer.parseInt(partes[3]));
                        conejos.add(c);
                        break;

                    case "LOBO":
                        Lobo l = new Lobo(partes[1], Double.parseDouble(partes[2]));
                        l.setEdad(Integer.parseInt(partes[3]));
                        l.setExitosCaza(Integer.parseInt(partes[4]));
                        lobos.add(l);
                        break;

                    default:
                        throw new IllegalArgumentException("Tipo de línea desconocido: " + partes[0]);
                }
            }

            // Un archivo vacío o incompleto no debe pasar por una simulación válida
            if (!tieneMetadata || !tieneStats || !tienePicos) {
                throw new IllegalArgumentException("Faltan líneas de encabezado");
            }

            eco.setPlantas(plantas);
            eco.setConejos(conejos);
            eco.setLobos(lobos);
            return eco;

        } catch (Exception e) {
            System.err.println("Error al cargar archivo o archivo corrupto: " + e.getMessage());
            return null;
        }
    }

    public boolean existeArchivo() {
        return new File(ARCHIVO).exists();
    }
}

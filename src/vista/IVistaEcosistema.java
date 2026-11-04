package vista;

import java.util.ArrayList;
import modelo.Clima;
import modelo.Entidad;
import modelo.ReporteFinal;

/**
 * Contrato de la Vista para el Controlador (MVC estricto).
 * Permite que el Controlador ordene actualizaciones y reciba acciones
 * sin acoplarse a Swing ni a componentes concretos de NetBeans.
 */
public interface IVistaEcosistema {

    // ── Navegación entre pantallas ─────────────────────────────
    void mostrarPantallaConfiguracion();
    void mostrarPantallaSimulacion();
    void mostrarReporteFinal(ReporteFinal reporte);

    // ── Actualización de componentes visuales ──────────────────
    void actualizarDashboard(int turnoActual, int turnosTotales, Clima clima, int plantasVivas, int conejosVivos, int lobosVivos);
    void actualizarTablaEntidades(ArrayList<Entidad> entidades);
    void mostrarEventosTurno(ArrayList<String> eventos);

    // ── Estado de interacción ──────────────────────────────────
    void configurarPanelIntervencion(boolean visible, boolean puedeAgregarLobo);

    // ── Diálogos y notificaciones ──────────────────────────────
    void mostrarMensaje(String mensaje);
    void mostrarError(String error);
    boolean solicitarConfirmacion(String mensaje, String titulo);
}

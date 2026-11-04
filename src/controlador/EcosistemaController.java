package controlador;

import dao.EcosistemaDAO;
import java.util.ArrayList;
import modelo.Clima;
import modelo.GestorEcosistema;
import modelo.ReporteFinal;
import vista.IVistaEcosistema;

/**
 * Controlador principal del Simulador de Ecosistema (MVC).
 * Intermediario exclusivo entre la Vista, el Modelo y el DAO.
 * Aplica validaciones de rangos, orquesta la evolución turno a turno,
 * coordina la persistencia y actualiza la pantalla sin importar Swing.
 */
public class EcosistemaController {

    private GestorEcosistema modelo;
    private IVistaEcosistema vista;
    private EcosistemaDAO dao;

    private int turnosTotales;
    private boolean simulacionEnCurso;

    public EcosistemaController(GestorEcosistema modelo, IVistaEcosistema vista, EcosistemaDAO dao) {
        this.modelo = modelo;
        this.vista = vista;
        this.dao = dao;
        this.turnosTotales = 30; // valor por defecto
        this.simulacionEnCurso = false;
    }

    // ═════════════════════════════════════════════════════════════
    // ARRANQUE DE LA APLICACIÓN
    // ═════════════════════════════════════════════════════════════
    public void iniciar() {
        vista.mostrarPantallaConfiguracion();
    }

    // ═════════════════════════════════════════════════════════════
    // FLUJO: CONFIGURAR NUEVA SIMULACIÓN
    // ═════════════════════════════════════════════════════════════
    public void configurarNuevaSimulacion(String cantPlantasStr, String cantConejosStr,
                                         String cantLobosStr, String turnosTotalesStr,
                                         String climaStr) {
        // 1. Validación de campos vacíos
        if (cantPlantasStr == null || cantPlantasStr.trim().isEmpty() ||
            cantConejosStr == null || cantConejosStr.trim().isEmpty() ||
            cantLobosStr == null || cantLobosStr.trim().isEmpty() ||
            turnosTotalesStr == null || turnosTotalesStr.trim().isEmpty()) {
            vista.mostrarError("Todos los campos numéricos son obligatorios.");
            return;
        }

        // 2. Validación de formato entero
        int plantas, conejos, lobos, turnos;
        try {
            plantas = Integer.parseInt(cantPlantasStr.trim());
            conejos = Integer.parseInt(cantConejosStr.trim());
            lobos = Integer.parseInt(cantLobosStr.trim());
            turnos = Integer.parseInt(turnosTotalesStr.trim());
        } catch (NumberFormatException e) {
            vista.mostrarError("Las cantidades y turnos deben ser números enteros válidos.");
            return;
        }

        // 3. Validación de rangos obligatorios de la consigna
        if (plantas < 5 || plantas > 30) {
            vista.mostrarError("La cantidad inicial de plantas debe estar entre 5 y 30.");
            return;
        }
        if (conejos < 2 || conejos > 15) {
            vista.mostrarError("La cantidad inicial de conejos debe estar entre 2 y 15.");
            return;
        }
        if (lobos < 1 || lobos > 5) {
            vista.mostrarError("La cantidad inicial de lobos debe estar entre 1 y 5.");
            return;
        }
        if (turnos < 10 || turnos > 50) {
            vista.mostrarError("La cantidad de turnos totales debe estar entre 10 y 50.");
            return;
        }

        // 4. Validación de clima
        Clima climaInicial;
        try {
            climaInicial = Clima.valueOf(climaStr.trim().toUpperCase());
        } catch (Exception e) {
            vista.mostrarError("El clima seleccionado no es válido.");
            return;
        }

        // 5. Inicialización del modelo con las entidades
        this.modelo = new GestorEcosistema(climaInicial);
        for (int i = 0; i < plantas; i++) {
            this.modelo.agregarEntidad("planta");
        }
        for (int i = 0; i < conejos; i++) {
            this.modelo.agregarEntidad("conejo");
        }
        for (int i = 0; i < lobos; i++) {
            this.modelo.agregarEntidad("lobo");
        }

        this.turnosTotales = turnos;
        this.simulacionEnCurso = true;

        // Auto-guardado inicial mediante DAO
        dao.guardarSimulacion(this.modelo);

        // Transición de pantalla y carga inicial de datos en la Vista
        vista.mostrarPantallaSimulacion();
        refrescarVistaCompleta();
        vista.mostrarMensaje("¡Simulación iniciada con éxito! Presione 'Avanzar Turno' para comenzar.");
    }

    // ═════════════════════════════════════════════════════════════
    // FLUJO: AVANZAR TURNO
    // ═════════════════════════════════════════════════════════════
    public void avanzarTurno() {
        if (!simulacionEnCurso || modelo == null) {
            vista.mostrarError("No hay ninguna simulación en curso.");
            return;
        }

        // Verificar si ya terminó antes de avanzar
        if (modelo.ecosistemaColapsado() || modelo.getTurnoActual() > turnosTotales) {
            finalizarSimulacion();
            return;
        }

        // ① Modelo procesa el turno y devuelve la lista de eventos
        ArrayList<String> eventos = modelo.procesarTurno();

        // ② Auto-guardado de seguridad en DAO
        dao.guardarSimulacion(modelo);

        // ③ Actualizar vista con los eventos y el nuevo estado
        vista.mostrarEventosTurno(eventos);
        refrescarVistaCompleta();

        // ④ Evaluar condiciones de fin después del turno
        if (modelo.ecosistemaColapsado()) {
            vista.mostrarError("¡COLAPSO DEL ECOSISTEMA! " + modelo.obtenerMotivoColapso());
            finalizarSimulacion();
        } else if (modelo.getTurnoActual() > turnosTotales) {
            vista.mostrarMensaje("¡Simulación finalizada! Se completaron los " + turnosTotales + " turnos configurados.");
            finalizarSimulacion();
        }
    }

    // ═════════════════════════════════════════════════════════════
    // FLUJO: INTERVENCIÓN DEL JUGADOR (CADA 3 TURNOS)
    // ═════════════════════════════════════════════════════════════
    public void intervenirCambiarClima(String nuevoClimaStr) {
        if (!simulacionEnCurso || modelo == null) {
            vista.mostrarError("No hay una simulación activa.");
            return;
        }
        if (modelo.getTurnoActual() % 3 != 0) {
            vista.mostrarError("Solo se permite intervenir cada 3 turnos.");
            return;
        }

        try {
            Clima nuevoClima = Clima.valueOf(nuevoClimaStr.trim().toUpperCase());
            modelo.cambiarClima(nuevoClima);
            dao.guardarSimulacion(modelo);
            refrescarVistaCompleta();
            vista.mostrarMensaje("Intervención aplicada: el clima cambió a " + nuevoClima);
        } catch (Exception e) {
            vista.mostrarError("Clima seleccionado inválido.");
        }
    }

    public void intervenirAgregarEntidad(String tipo) {
        if (!simulacionEnCurso || modelo == null) {
            vista.mostrarError("No hay una simulación activa.");
            return;
        }
        if (modelo.getTurnoActual() % 3 != 0) {
            vista.mostrarError("Solo se permite intervenir cada 3 turnos.");
            return;
        }

        if ("lobo".equalsIgnoreCase(tipo) && !modelo.puedeAgregarLobo()) {
            vista.mostrarError("Límite alcanzado: no se pueden agregar más de 5 lobos en toda la simulación.");
            return;
        }

        boolean agregada = modelo.agregarEntidad(tipo);
        if (agregada) {
            dao.guardarSimulacion(modelo);
            refrescarVistaCompleta();
            vista.mostrarMensaje("Intervención aplicada: se agregó una entidad del tipo " + tipo);
        } else {
            vista.mostrarError("No se pudo agregar la entidad solicitada.");
        }
    }

    // ═════════════════════════════════════════════════════════════
    // FLUJO: GUARDADO Y RETOMA (DAO)
    // ═════════════════════════════════════════════════════════════
    public void guardarManualmente() {
        if (modelo == null) {
            vista.mostrarError("No hay datos para guardar.");
            return;
        }
        boolean guardadoOk = dao.guardarSimulacion(modelo);
        if (guardadoOk) {
            vista.mostrarMensaje("Simulación guardada con éxito en simulacion.txt");
        } else {
            vista.mostrarError("Error al guardar la simulación. Verifique permisos de escritura.");
        }
    }

    public void cargarPartidaGuardada() {
        if (!dao.existeArchivo()) {
            vista.mostrarError("No se encontró ningún archivo de guardado previo (simulacion.txt).");
            return;
        }

        GestorEcosistema cargado = dao.cargarSimulacion();
        if (cargado == null) {
            vista.mostrarError("El archivo simulacion.txt existe pero está dañado o tiene formato inválido.");
            return;
        }

        this.modelo = cargado;
        this.simulacionEnCurso = true;
        // Asignamos un límite de turnos consistente con el turno actual recuperado
        if (modelo.getTurnoActual() > this.turnosTotales) {
            this.turnosTotales = modelo.getTurnoActual() + 10;
        }

        vista.mostrarPantallaSimulacion();
        refrescarVistaCompleta();
        vista.mostrarMensaje("Partida reanudada exitosamente en el Turno " + modelo.getTurnoActual() + ".");
    }

    // ═════════════════════════════════════════════════════════════
    // FLUJO: REINICIO Y REPORTE FINAL
    // ═════════════════════════════════════════════════════════════
    public void solicitarReporteFinal() {
        if (modelo == null) {
            vista.mostrarError("No hay ninguna simulación registrada para generar un reporte.");
            return;
        }
        ReporteFinal reporte = modelo.generarReporteFinal();
        vista.mostrarReporteFinal(reporte);
    }

    public void reiniciarSimulacion() {
        if (simulacionEnCurso) {
            boolean confirma = vista.solicitarConfirmacion(
                "¿Está seguro de que desea reiniciar? Se perderá el avance no guardado.",
                "Confirmar Reinicio"
            );
            if (!confirma) return;
        }

        this.simulacionEnCurso = false;
        vista.mostrarPantallaConfiguracion();
    }

    private void finalizarSimulacion() {
        this.simulacionEnCurso = false;
        solicitarReporteFinal();
    }

    // ═════════════════════════════════════════════════════════════
    // AUXILIAR: ACTUALIZAR PANTALLA
    // ═════════════════════════════════════════════════════════════
    public void refrescarVistaCompleta() {
        if (modelo == null || vista == null) return;

        // 1. Contadores del Dashboard
        vista.actualizarDashboard(
            modelo.getTurnoActual(),
            turnosTotales,
            modelo.getClimaActual(),
            modelo.getPlantas().size(),
            modelo.getConejos().size(),
            modelo.getLobos().size()
        );

        // 2. Tabla con todas las entidades vivas
        vista.actualizarTablaEntidades(modelo.obtenerTodasLasEntidadesVivas());

        // 3. Panel de intervención (se habilita solo cada 3 turnos y si la simulación sigue viva)
        boolean esTurnoIntervencion = (modelo.getTurnoActual() % 3 == 0) && !modelo.ecosistemaColapsado();
        vista.configurarPanelIntervencion(esTurnoIntervencion, modelo.puedeAgregarLobo());
    }

    // Getters auxiliares para tests o inspección
    public GestorEcosistema getModelo() { return modelo; }
    public boolean isSimulacionEnCurso() { return simulacionEnCurso; }
    public int getTurnosTotales() { return turnosTotales; }
}

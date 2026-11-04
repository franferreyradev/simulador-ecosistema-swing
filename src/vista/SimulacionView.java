package vista;

import controlador.EcosistemaController;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import modelo.Conejo;
import modelo.Entidad;
import modelo.Lobo;
import modelo.Planta;
import modelo.PlantaVenenosa;

/**
 * Dashboard principal de la simulacion.
 * Muestra lo que el controlador le ordena y le delega cada accion del usuario.
 */
public class SimulacionView extends javax.swing.JPanel {

    private static final java.awt.Color COLOR_PRIMARIO = new java.awt.Color(27, 107, 71);
    private static final java.awt.Color COLOR_PRIMARIO_HOVER = new java.awt.Color(21, 85, 56);
    private static final java.awt.Color COLOR_DESHABILITADO = new java.awt.Color(201, 209, 204);
    private static final java.awt.Color COLOR_BADGE_APAGADO = new java.awt.Color(232, 236, 233);
    private static final java.awt.Color COLOR_TEXTO_SECUNDARIO = new java.awt.Color(85, 101, 92);

    private EcosistemaController controlador;
    private int turnoMostrado = 1;

    public SimulacionView() {
        initComponents();
        // El aspecto nativo de Windows ignora los colores de la barra sobre la tarjeta oscura.
        pbTurnos.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        tblEntidades.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblEntidades.getColumnModel().getColumn(1).setPreferredWidth(110);
        tblEntidades.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblEntidades.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblEntidades.getColumnModel().getColumn(4).setPreferredWidth(210);
    }

    public void setControlador(EcosistemaController controlador) {
        this.controlador = controlador;
    }

    public javax.swing.JButton getBotonPrincipal() {
        return btnAvanzarTurno;
    }

    public void actualizarDashboard(int turno, int turnosTotales, String clima, int cantP, int cantC, int cantL) {
        turnoMostrado = turno;
        lblTurno.setText(Math.min(turno, turnosTotales) + " de " + turnosTotales);
        pbTurnos.setMaximum(turnosTotales);
        pbTurnos.setValue(turno - 1);
        lblClima.setText(clima);
        lblPlantas.setText(String.valueOf(cantP));
        lblConejos.setText(String.valueOf(cantC));
        lblLobos.setText(String.valueOf(cantL));
        cmbClimaIntervencion.setSelectedItem(clima);
    }

    public void agregarEventos(List<String> eventos) {
        StringBuilder bloque = new StringBuilder("— Turno " + turnoMostrado + " —\n");
        if (eventos.isEmpty()) {
            bloque.append("Sin eventos.\n");
        }
        for (String ev : eventos) {
            bloque.append("• ").append(ev).append("\n");
        }
        txtLogEventos.append(bloque.append("\n").toString());
        txtLogEventos.setCaretPosition(txtLogEventos.getDocument().getLength());
    }

    public void actualizarTablaEntidades(List<Entidad> entidades) {
        lblTituloEntidades.setText("Entidades vivas (" + entidades.size() + ")");
        DefaultTableModel modelo = (DefaultTableModel) tblEntidades.getModel();
        // Carga en bloque: con el ordenamiento activo, addRow fila por fila traba la pantalla si hay miles de entidades.
        Vector<Vector> filas = modelo.getDataVector();
        filas.clear();
        for (Entidad e : entidades) {
            filas.add(new Vector<Object>(List.of(
                e.getNombre(), tipoDe(e), Math.round(e.getEnergia() * 10.0) / 10.0, e.getEdad(), detalleDe(e)
            )));
        }
        modelo.fireTableDataChanged();
    }

    public void habilitarIntervencion(boolean habilitar, boolean puedeAgregarLobo) {
        cmbClimaIntervencion.setEnabled(habilitar);
        btnCambiarClima.setEnabled(habilitar);
        cmbTipoEntidad.setEnabled(habilitar);
        btnAgregarEntidad.setEnabled(habilitar);
        lblEstadoIntervencion.setText(habilitar ? "Disponible ahora" : "Cada 3 turnos");
        lblEstadoIntervencion.setBackground(habilitar ? COLOR_PRIMARIO : COLOR_BADGE_APAGADO);
        lblEstadoIntervencion.setForeground(habilitar ? java.awt.Color.WHITE : COLOR_TEXTO_SECUNDARIO);

        Object seleccion = cmbTipoEntidad.getSelectedItem();
        cmbTipoEntidad.removeItem("Lobo");
        if (puedeAgregarLobo) {
            cmbTipoEntidad.insertItemAt("Lobo", 2);
        }
        cmbTipoEntidad.setSelectedItem(seleccion);
    }

    public void prepararNuevaPartida() {
        txtLogEventos.setText("");
        turnoMostrado = 1;
        btnAvanzarTurno.setEnabled(true);
        btnAvanzarTurno.setBackground(COLOR_PRIMARIO);
        btnGuardar.setEnabled(true);
        btnVerReporte.setEnabled(false);
    }

    public void marcarFinalizada() {
        habilitarIntervencion(false, false);
        btnAvanzarTurno.setEnabled(false);
        btnAvanzarTurno.setBackground(COLOR_DESHABILITADO);
        btnGuardar.setEnabled(false);
        btnVerReporte.setEnabled(true);
    }

    private String tipoDe(Entidad e) {
        return e instanceof PlantaVenenosa ? "Planta venenosa" : e.getClass().getSimpleName();
    }

    private String detalleDe(Entidad e) {
        if (e instanceof Lobo) {
            Lobo lobo = (Lobo) e;
            return "Cazas: " + lobo.getExitosCaza() + " · Peligro: " + lobo.getNivelPeligro();
        }
        if (e instanceof PlantaVenenosa) {
            PlantaVenenosa venenosa = (PlantaVenenosa) e;
            return "Tamaño: " + venenosa.getTamanio() + " · Peligro: " + venenosa.getNivelPeligro();
        }
        if (e instanceof Planta) {
            return "Tamaño: " + ((Planta) e).getTamanio();
        }
        if (e instanceof Conejo) {
            Conejo conejo = (Conejo) e;
            return "Velocidad: " + conejo.getVelocidad() + " · Peso: " + conejo.getPeso();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlDashboard = new javax.swing.JPanel();
        pnlTarjetaTurno = new javax.swing.JPanel();
        lblCaptionTurno = new javax.swing.JLabel();
        lblTurno = new javax.swing.JLabel();
        pbTurnos = new javax.swing.JProgressBar();
        pnlTarjetaClima = new javax.swing.JPanel();
        lblCaptionClima = new javax.swing.JLabel();
        lblClima = new javax.swing.JLabel();
        pnlTarjetaPlantas = new javax.swing.JPanel();
        lblCaptionPlantas = new javax.swing.JLabel();
        lblPlantas = new javax.swing.JLabel();
        pnlTarjetaConejos = new javax.swing.JPanel();
        lblCaptionConejos = new javax.swing.JLabel();
        lblConejos = new javax.swing.JLabel();
        pnlTarjetaLobos = new javax.swing.JPanel();
        lblCaptionLobos = new javax.swing.JLabel();
        lblLobos = new javax.swing.JLabel();
        pnlEntidades = new javax.swing.JPanel();
        lblTituloEntidades = new javax.swing.JLabel();
        scrEntidades = new javax.swing.JScrollPane();
        tblEntidades = new javax.swing.JTable();
        pnlEventos = new javax.swing.JPanel();
        lblTituloEventos = new javax.swing.JLabel();
        scrEventos = new javax.swing.JScrollPane();
        txtLogEventos = new javax.swing.JTextArea();
        pnlSur = new javax.swing.JPanel();
        pnlIntervencion = new javax.swing.JPanel();
        lblTituloIntervencion = new javax.swing.JLabel();
        lblEstadoIntervencion = new javax.swing.JLabel();
        lblClimaIntervencion = new javax.swing.JLabel();
        cmbClimaIntervencion = new javax.swing.JComboBox<>();
        btnCambiarClima = new javax.swing.JButton();
        lblTipoEntidad = new javax.swing.JLabel();
        cmbTipoEntidad = new javax.swing.JComboBox<>();
        btnAgregarEntidad = new javax.swing.JButton();
        pnlAcciones = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnVerReporte = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAvanzarTurno = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 246, 243));
        setPreferredSize(new java.awt.Dimension(1120, 640));
        setLayout(new java.awt.BorderLayout());

        pnlDashboard.setBackground(new java.awt.Color(244, 246, 243));
        pnlDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 8, 12));
        pnlDashboard.setLayout(new java.awt.GridBagLayout());

        pnlTarjetaTurno.setBackground(new java.awt.Color(28, 43, 35));
        pnlTarjetaTurno.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlTarjetaTurno.setLayout(new java.awt.GridBagLayout());

        lblCaptionTurno.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCaptionTurno.setForeground(new java.awt.Color(232, 240, 235));
        lblCaptionTurno.setText("TURNO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlTarjetaTurno.add(lblCaptionTurno, gridBagConstraints);

        lblTurno.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTurno.setForeground(new java.awt.Color(255, 255, 255));
        lblTurno.setText("1 de 30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlTarjetaTurno.add(lblTurno, gridBagConstraints);

        pbTurnos.setBackground(new java.awt.Color(61, 77, 68));
        pbTurnos.setBorderPainted(false);
        pbTurnos.setForeground(new java.awt.Color(143, 217, 174));
        pbTurnos.setMaximum(30);
        pbTurnos.setPreferredSize(new java.awt.Dimension(120, 8));
        pbTurnos.setToolTipText("Progreso de la simulación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        pnlTarjetaTurno.add(pbTurnos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlDashboard.add(pnlTarjetaTurno, gridBagConstraints);

        pnlTarjetaClima.setBackground(new java.awt.Color(26, 95, 168));
        pnlTarjetaClima.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlTarjetaClima.setLayout(new java.awt.GridBagLayout());

        lblCaptionClima.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCaptionClima.setForeground(new java.awt.Color(232, 240, 235));
        lblCaptionClima.setText("CLIMA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlTarjetaClima.add(lblCaptionClima, gridBagConstraints);

        lblClima.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblClima.setForeground(new java.awt.Color(255, 255, 255));
        lblClima.setText("SOLEADO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlTarjetaClima.add(lblClima, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlDashboard.add(pnlTarjetaClima, gridBagConstraints);

        pnlTarjetaPlantas.setBackground(new java.awt.Color(42, 122, 59));
        pnlTarjetaPlantas.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlTarjetaPlantas.setLayout(new java.awt.GridBagLayout());

        lblCaptionPlantas.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCaptionPlantas.setForeground(new java.awt.Color(232, 240, 235));
        lblCaptionPlantas.setText("PLANTAS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlTarjetaPlantas.add(lblCaptionPlantas, gridBagConstraints);

        lblPlantas.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblPlantas.setForeground(new java.awt.Color(255, 255, 255));
        lblPlantas.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlTarjetaPlantas.add(lblPlantas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlDashboard.add(pnlTarjetaPlantas, gridBagConstraints);

        pnlTarjetaConejos.setBackground(new java.awt.Color(156, 85, 14));
        pnlTarjetaConejos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlTarjetaConejos.setLayout(new java.awt.GridBagLayout());

        lblCaptionConejos.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCaptionConejos.setForeground(new java.awt.Color(232, 240, 235));
        lblCaptionConejos.setText("CONEJOS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlTarjetaConejos.add(lblCaptionConejos, gridBagConstraints);

        lblConejos.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblConejos.setForeground(new java.awt.Color(255, 255, 255));
        lblConejos.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlTarjetaConejos.add(lblConejos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlDashboard.add(pnlTarjetaConejos, gridBagConstraints);

        pnlTarjetaLobos.setBackground(new java.awt.Color(77, 79, 115));
        pnlTarjetaLobos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));
        pnlTarjetaLobos.setLayout(new java.awt.GridBagLayout());

        lblCaptionLobos.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCaptionLobos.setForeground(new java.awt.Color(232, 240, 235));
        lblCaptionLobos.setText("LOBOS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlTarjetaLobos.add(lblCaptionLobos, gridBagConstraints);

        lblLobos.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblLobos.setForeground(new java.awt.Color(255, 255, 255));
        lblLobos.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlTarjetaLobos.add(lblLobos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlDashboard.add(pnlTarjetaLobos, gridBagConstraints);

        add(pnlDashboard, java.awt.BorderLayout.NORTH);

        pnlEntidades.setBackground(new java.awt.Color(244, 246, 243));
        pnlEntidades.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 12, 8, 6));
        pnlEntidades.setLayout(new java.awt.BorderLayout());

        lblTituloEntidades.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTituloEntidades.setForeground(new java.awt.Color(28, 43, 35));
        lblTituloEntidades.setText("Entidades vivas");
        lblTituloEntidades.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 6, 0));
        pnlEntidades.add(lblTituloEntidades, java.awt.BorderLayout.NORTH);

        scrEntidades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 223, 218)));

        tblEntidades.setAutoCreateRowSorter(true);
        tblEntidades.setBackground(new java.awt.Color(255, 255, 255));
        tblEntidades.setFillsViewportHeight(true);
        tblEntidades.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblEntidades.setGridColor(new java.awt.Color(232, 236, 233));
        tblEntidades.setIntercellSpacing(new java.awt.Dimension(10, 1));
        tblEntidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID / Nombre", "Tipo", "Energía", "Edad", "Detalle"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEntidades.setRowHeight(26);
        tblEntidades.setSelectionBackground(new java.awt.Color(220, 239, 228));
        tblEntidades.setSelectionForeground(new java.awt.Color(28, 43, 35));
        tblEntidades.setShowVerticalLines(false);
        scrEntidades.setViewportView(tblEntidades);

        pnlEntidades.add(scrEntidades, java.awt.BorderLayout.CENTER);

        add(pnlEntidades, java.awt.BorderLayout.CENTER);

        pnlEventos.setBackground(new java.awt.Color(244, 246, 243));
        pnlEventos.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 6, 8, 12));
        pnlEventos.setPreferredSize(new java.awt.Dimension(360, 100));
        pnlEventos.setLayout(new java.awt.BorderLayout());

        lblTituloEventos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTituloEventos.setForeground(new java.awt.Color(28, 43, 35));
        lblTituloEventos.setText("Registro de eventos");
        lblTituloEventos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 6, 0));
        pnlEventos.add(lblTituloEventos, java.awt.BorderLayout.NORTH);

        scrEventos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 223, 218)));
        scrEventos.setHorizontalScrollBarPolicy(31);

        txtLogEventos.setEditable(false);
        txtLogEventos.setBackground(new java.awt.Color(255, 255, 255));
        txtLogEventos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtLogEventos.setForeground(new java.awt.Color(28, 43, 35));
        txtLogEventos.setLineWrap(true);
        txtLogEventos.setWrapStyleWord(true);
        txtLogEventos.setMargin(new java.awt.Insets(8, 10, 8, 10));
        scrEventos.setViewportView(txtLogEventos);

        pnlEventos.add(scrEventos, java.awt.BorderLayout.CENTER);

        add(pnlEventos, java.awt.BorderLayout.EAST);

        pnlSur.setBackground(new java.awt.Color(244, 246, 243));
        pnlSur.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 12, 12));
        pnlSur.setLayout(new java.awt.GridBagLayout());

        pnlIntervencion.setBackground(new java.awt.Color(255, 255, 255));
        pnlIntervencion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 223, 218)), javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        pnlIntervencion.setLayout(new java.awt.GridBagLayout());

        lblTituloIntervencion.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTituloIntervencion.setForeground(new java.awt.Color(28, 43, 35));
        lblTituloIntervencion.setText("Intervención");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlIntervencion.add(lblTituloIntervencion, gridBagConstraints);

        lblEstadoIntervencion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstadoIntervencion.setForeground(new java.awt.Color(85, 101, 92));
        lblEstadoIntervencion.setText("Cada 3 turnos");
        lblEstadoIntervencion.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 8, 3, 8));
        lblEstadoIntervencion.setBackground(new java.awt.Color(232, 236, 233));
        lblEstadoIntervencion.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        pnlIntervencion.add(lblEstadoIntervencion, gridBagConstraints);

        lblClimaIntervencion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblClimaIntervencion.setForeground(new java.awt.Color(85, 101, 92));
        lblClimaIntervencion.setText("Clima");
        lblClimaIntervencion.setLabelFor(cmbClimaIntervencion);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        pnlIntervencion.add(lblClimaIntervencion, gridBagConstraints);

        cmbClimaIntervencion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbClimaIntervencion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLEADO", "LLUVIOSO", "SEQUIA", "INVIERNO" }));
        cmbClimaIntervencion.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        pnlIntervencion.add(cmbClimaIntervencion, gridBagConstraints);

        btnCambiarClima.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCambiarClima.setMnemonic(67);
        btnCambiarClima.setText("Cambiar Clima");
        btnCambiarClima.setToolTipText("Aplicar el clima elegido");
        btnCambiarClima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarClimaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        pnlIntervencion.add(btnCambiarClima, gridBagConstraints);

        lblTipoEntidad.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTipoEntidad.setForeground(new java.awt.Color(85, 101, 92));
        lblTipoEntidad.setText("Entidad");
        lblTipoEntidad.setLabelFor(cmbTipoEntidad);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        pnlIntervencion.add(lblTipoEntidad, gridBagConstraints);

        cmbTipoEntidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbTipoEntidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planta", "Conejo", "Lobo", "Planta venenosa" }));
        cmbTipoEntidad.setPreferredSize(new java.awt.Dimension(140, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        pnlIntervencion.add(cmbTipoEntidad, gridBagConstraints);

        btnAgregarEntidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnAgregarEntidad.setMnemonic(69);
        btnAgregarEntidad.setText("Agregar Entidad");
        btnAgregarEntidad.setToolTipText("Agregar una entidad del tipo elegido");
        btnAgregarEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEntidadActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlIntervencion.add(btnAgregarEntidad, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlSur.add(pnlIntervencion, gridBagConstraints);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.GridBagLayout());

        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnSalir.setMnemonic(83);
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("Cerrar la aplicación");
        btnSalir.setPreferredSize(new java.awt.Dimension(80, 40));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlAcciones.add(btnSalir, gridBagConstraints);

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnNuevo.setMnemonic(78);
        btnNuevo.setText("Nueva Simulación");
        btnNuevo.setToolTipText("Volver a la configuración inicial");
        btnNuevo.setPreferredSize(new java.awt.Dimension(150, 40));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        pnlAcciones.add(btnNuevo, gridBagConstraints);

        btnVerReporte.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnVerReporte.setMnemonic(82);
        btnVerReporte.setText("Ver Reporte");
        btnVerReporte.setToolTipText("Disponible al terminar la simulación");
        btnVerReporte.setPreferredSize(new java.awt.Dimension(120, 40));
        btnVerReporte.setEnabled(false);
        btnVerReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReporteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlAcciones.add(btnVerReporte, gridBagConstraints);

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnGuardar.setMnemonic(71);
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Guardar la partida en simulacion.txt");
        btnGuardar.setPreferredSize(new java.awt.Dimension(100, 40));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        pnlAcciones.add(btnGuardar, gridBagConstraints);

        btnAvanzarTurno.setBackground(new java.awt.Color(27, 107, 71));
        btnAvanzarTurno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAvanzarTurno.setForeground(new java.awt.Color(255, 255, 255));
        btnAvanzarTurno.setMnemonic(65);
        btnAvanzarTurno.setText("Avanzar Turno");
        btnAvanzarTurno.setToolTipText("Procesar el siguiente turno");
        btnAvanzarTurno.setBorderPainted(false);
        btnAvanzarTurno.setContentAreaFilled(false);
        btnAvanzarTurno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAvanzarTurno.setOpaque(true);
        btnAvanzarTurno.setPreferredSize(new java.awt.Dimension(210, 40));
        btnAvanzarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarTurnoActionPerformed(evt);
            }
        });
        btnAvanzarTurno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAvanzarTurnoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAvanzarTurnoMouseExited(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        pnlAcciones.add(btnAvanzarTurno, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        pnlSur.add(pnlAcciones, gridBagConstraints);

        add(pnlSur, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarClimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarClimaActionPerformed
        if (controlador != null) {
            controlador.intervenirCambiarClima((String) cmbClimaIntervencion.getSelectedItem());
        }
    }//GEN-LAST:event_btnCambiarClimaActionPerformed

    private void btnAgregarEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEntidadActionPerformed
        if (controlador != null) {
            controlador.intervenirAgregarEntidad(((String) cmbTipoEntidad.getSelectedItem()).replace(" ", ""));
        }
    }//GEN-LAST:event_btnAgregarEntidadActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (controlador != null && controlador.confirmarSalida()) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if (controlador != null) {
            controlador.reiniciarSimulacion();
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnVerReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReporteActionPerformed
        if (controlador != null) {
            controlador.solicitarReporteFinal();
        }
    }//GEN-LAST:event_btnVerReporteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (controlador != null) {
            controlador.guardarManualmente();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAvanzarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarTurnoActionPerformed
        if (controlador != null) {
            controlador.avanzarTurno();
        }
    }//GEN-LAST:event_btnAvanzarTurnoActionPerformed

    private void btnAvanzarTurnoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarTurnoMouseEntered
        if (btnAvanzarTurno.isEnabled()) {
            btnAvanzarTurno.setBackground(COLOR_PRIMARIO_HOVER);
        }
    }//GEN-LAST:event_btnAvanzarTurnoMouseEntered

    private void btnAvanzarTurnoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarTurnoMouseExited
        if (btnAvanzarTurno.isEnabled()) {
            btnAvanzarTurno.setBackground(COLOR_PRIMARIO);
        }
    }//GEN-LAST:event_btnAvanzarTurnoMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarEntidad;
    private javax.swing.JButton btnAvanzarTurno;
    private javax.swing.JButton btnCambiarClima;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerReporte;
    private javax.swing.JComboBox<String> cmbClimaIntervencion;
    private javax.swing.JComboBox<String> cmbTipoEntidad;
    private javax.swing.JLabel lblCaptionClima;
    private javax.swing.JLabel lblCaptionConejos;
    private javax.swing.JLabel lblCaptionLobos;
    private javax.swing.JLabel lblCaptionPlantas;
    private javax.swing.JLabel lblCaptionTurno;
    private javax.swing.JLabel lblClima;
    private javax.swing.JLabel lblClimaIntervencion;
    private javax.swing.JLabel lblConejos;
    private javax.swing.JLabel lblEstadoIntervencion;
    private javax.swing.JLabel lblLobos;
    private javax.swing.JLabel lblPlantas;
    private javax.swing.JLabel lblTipoEntidad;
    private javax.swing.JLabel lblTituloEntidades;
    private javax.swing.JLabel lblTituloEventos;
    private javax.swing.JLabel lblTituloIntervencion;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JProgressBar pbTurnos;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlEntidades;
    private javax.swing.JPanel pnlEventos;
    private javax.swing.JPanel pnlIntervencion;
    private javax.swing.JPanel pnlSur;
    private javax.swing.JPanel pnlTarjetaClima;
    private javax.swing.JPanel pnlTarjetaConejos;
    private javax.swing.JPanel pnlTarjetaLobos;
    private javax.swing.JPanel pnlTarjetaPlantas;
    private javax.swing.JPanel pnlTarjetaTurno;
    private javax.swing.JScrollPane scrEntidades;
    private javax.swing.JScrollPane scrEventos;
    private javax.swing.JTable tblEntidades;
    private javax.swing.JTextArea txtLogEventos;
    // End of variables declaration//GEN-END:variables
}

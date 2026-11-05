package vista;

import controlador.EcosistemaController;

/**
 * Formulario inicial de la simulacion.
 * Solo captura los valores elegidos y los delega al controlador, que es quien los valida.
 */
public class ConfiguracionView extends javax.swing.JPanel {

    private EcosistemaController controlador;

    public ConfiguracionView() {
        initComponents();
    }

    public void setControlador(EcosistemaController controlador) {
        this.controlador = controlador;
    }

    public javax.swing.JButton getBotonPrincipal() {
        return btnIniciar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlMarca = new javax.swing.JPanel();
        lblMarca = new javax.swing.JLabel();
        lblMarcaDescripcion = new javax.swing.JLabel();
        lblComoFunciona = new javax.swing.JLabel();
        lblPaso1 = new javax.swing.JLabel();
        lblPaso2 = new javax.swing.JLabel();
        lblPaso3 = new javax.swing.JLabel();
        lblPaso4 = new javax.swing.JLabel();
        pnlMarcaRelleno = new javax.swing.JPanel();
        pnlContenido = new javax.swing.JPanel();
        pnlTarjeta = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblSeccionPoblacion = new javax.swing.JLabel();
        lblPlantas = new javax.swing.JLabel();
        spnPlantas = new javax.swing.JSpinner();
        lblPlantasRango = new javax.swing.JLabel();
        lblConejos = new javax.swing.JLabel();
        spnConejos = new javax.swing.JSpinner();
        lblConejosRango = new javax.swing.JLabel();
        lblLobos = new javax.swing.JLabel();
        spnLobos = new javax.swing.JSpinner();
        lblLobosRango = new javax.swing.JLabel();
        lblSeccionPartida = new javax.swing.JLabel();
        lblTurnos = new javax.swing.JLabel();
        spnTurnos = new javax.swing.JSpinner();
        lblTurnosRango = new javax.swing.JLabel();
        lblClimaInicial = new javax.swing.JLabel();
        cmbClima = new javax.swing.JComboBox<>();
        pnlBotones = new javax.swing.JPanel();
        btnCargar = new javax.swing.JButton();
        pnlBtnIniciar = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 246, 243));
        setPreferredSize(new java.awt.Dimension(1120, 640));
        setLayout(new java.awt.BorderLayout());

        pnlMarca.setBackground(new java.awt.Color(20, 82, 58));
        pnlMarca.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 32, 32, 32));
        pnlMarca.setPreferredSize(new java.awt.Dimension(340, 100));
        pnlMarca.setLayout(new java.awt.GridBagLayout());

        lblMarca.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setText("<html>Simulador de<br>Ecosistema</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlMarca.add(lblMarca, gridBagConstraints);

        lblMarcaDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMarcaDescripcion.setForeground(new java.awt.Color(207, 227, 216));
        lblMarcaDescripcion.setText("<html>Plantas, conejos y lobos compiten por sobrevivir, turno a turno.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 32, 0);
        pnlMarca.add(lblMarcaDescripcion, gridBagConstraints);

        lblComoFunciona.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblComoFunciona.setForeground(new java.awt.Color(207, 227, 216));
        lblComoFunciona.setText("CÓMO FUNCIONA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlMarca.add(lblComoFunciona, gridBagConstraints);

        lblPaso1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPaso1.setForeground(new java.awt.Color(255, 255, 255));
        lblPaso1.setText("<html>1. Elegir población, turnos y clima inicial.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlMarca.add(lblPaso1, gridBagConstraints);

        lblPaso2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPaso2.setForeground(new java.awt.Color(255, 255, 255));
        lblPaso2.setText("<html>2. Avanzar turno a turno y seguir los eventos.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlMarca.add(lblPaso2, gridBagConstraints);

        lblPaso3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPaso3.setForeground(new java.awt.Color(255, 255, 255));
        lblPaso3.setText("<html>3. Intervenir cada 3 turnos: cambiar el clima o agregar entidades.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlMarca.add(lblPaso3, gridBagConstraints);

        lblPaso4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPaso4.setForeground(new java.awt.Color(255, 255, 255));
        lblPaso4.setText("<html>4. Revisar el reporte al completar los turnos o si una especie se extingue.</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlMarca.add(lblPaso4, gridBagConstraints);

        pnlMarcaRelleno.setOpaque(false);
        pnlMarcaRelleno.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        pnlMarca.add(pnlMarcaRelleno, gridBagConstraints);

        add(pnlMarca, java.awt.BorderLayout.WEST);

        pnlContenido.setBackground(new java.awt.Color(244, 246, 243));
        pnlContenido.setLayout(new java.awt.GridBagLayout());

        pnlTarjeta.setBackground(new java.awt.Color(255, 255, 255));
        pnlTarjeta.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 223, 218)), javax.swing.BorderFactory.createEmptyBorder(28, 32, 28, 32)));
        pnlTarjeta.setPreferredSize(new java.awt.Dimension(560, 500));
        pnlTarjeta.setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(28, 43, 35));
        lblTitulo.setText("Configurar simulación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlTarjeta.add(lblTitulo, gridBagConstraints);

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(85, 101, 92));
        lblSubtitulo.setText("Definir el estado inicial o retomar una partida guardada.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 18, 0);
        pnlTarjeta.add(lblSubtitulo, gridBagConstraints);

        lblSeccionPoblacion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSeccionPoblacion.setForeground(new java.awt.Color(85, 101, 92));
        lblSeccionPoblacion.setText("POBLACIÓN INICIAL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlTarjeta.add(lblSeccionPoblacion, gridBagConstraints);

        lblPlantas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPlantas.setForeground(new java.awt.Color(28, 43, 35));
        lblPlantas.setText("Plantas");
        lblPlantas.setLabelFor(spnPlantas);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 16);
        pnlTarjeta.add(lblPlantas, gridBagConstraints);

        spnPlantas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        spnPlantas.setModel(new javax.swing.SpinnerNumberModel(15, 5, 30, 1));
        spnPlantas.setToolTipText("Cantidad inicial de plantas (5 a 30)");
        spnPlantas.setPreferredSize(new java.awt.Dimension(90, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 12);
        pnlTarjeta.add(spnPlantas, gridBagConstraints);

        lblPlantasRango.setForeground(new java.awt.Color(85, 101, 92));
        lblPlantasRango.setText("Entre 5 y 30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlTarjeta.add(lblPlantasRango, gridBagConstraints);

        lblConejos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblConejos.setForeground(new java.awt.Color(28, 43, 35));
        lblConejos.setText("Conejos");
        lblConejos.setLabelFor(spnConejos);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 16);
        pnlTarjeta.add(lblConejos, gridBagConstraints);

        spnConejos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        spnConejos.setModel(new javax.swing.SpinnerNumberModel(8, 2, 15, 1));
        spnConejos.setToolTipText("Cantidad inicial de conejos (2 a 15)");
        spnConejos.setPreferredSize(new java.awt.Dimension(90, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 12);
        pnlTarjeta.add(spnConejos, gridBagConstraints);

        lblConejosRango.setForeground(new java.awt.Color(85, 101, 92));
        lblConejosRango.setText("Entre 2 y 15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlTarjeta.add(lblConejosRango, gridBagConstraints);

        lblLobos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblLobos.setForeground(new java.awt.Color(28, 43, 35));
        lblLobos.setText("Lobos");
        lblLobos.setLabelFor(spnLobos);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 16);
        pnlTarjeta.add(lblLobos, gridBagConstraints);

        spnLobos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        spnLobos.setModel(new javax.swing.SpinnerNumberModel(3, 1, 5, 1));
        spnLobos.setToolTipText("Cantidad inicial de lobos (1 a 5)");
        spnLobos.setPreferredSize(new java.awt.Dimension(90, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 12);
        pnlTarjeta.add(spnLobos, gridBagConstraints);

        lblLobosRango.setForeground(new java.awt.Color(85, 101, 92));
        lblLobosRango.setText("Entre 1 y 5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlTarjeta.add(lblLobosRango, gridBagConstraints);

        lblSeccionPartida.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSeccionPartida.setForeground(new java.awt.Color(85, 101, 92));
        lblSeccionPartida.setText("PARTIDA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 4, 0);
        pnlTarjeta.add(lblSeccionPartida, gridBagConstraints);

        lblTurnos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTurnos.setForeground(new java.awt.Color(28, 43, 35));
        lblTurnos.setText("Turnos");
        lblTurnos.setLabelFor(spnTurnos);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 16);
        pnlTarjeta.add(lblTurnos, gridBagConstraints);

        spnTurnos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        spnTurnos.setModel(new javax.swing.SpinnerNumberModel(30, 10, 50, 1));
        spnTurnos.setToolTipText("Duración de la simulación (10 a 50 turnos)");
        spnTurnos.setPreferredSize(new java.awt.Dimension(90, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 12);
        pnlTarjeta.add(spnTurnos, gridBagConstraints);

        lblTurnosRango.setForeground(new java.awt.Color(85, 101, 92));
        lblTurnosRango.setText("Entre 10 y 50");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlTarjeta.add(lblTurnosRango, gridBagConstraints);

        lblClimaInicial.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblClimaInicial.setForeground(new java.awt.Color(28, 43, 35));
        lblClimaInicial.setText("Clima inicial");
        lblClimaInicial.setLabelFor(cmbClima);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 16);
        pnlTarjeta.add(lblClimaInicial, gridBagConstraints);

        cmbClima.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbClima.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SOLEADO", "LLUVIOSO", "SEQUIA", "INVIERNO" }));
        cmbClima.setToolTipText("Clima con el que arranca la simulación");
        cmbClima.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        pnlTarjeta.add(cmbClima, gridBagConstraints);

        pnlBotones.setOpaque(false);
        pnlBotones.setLayout(new java.awt.GridBagLayout());

        btnCargar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCargar.setMnemonic('C');
        btnCargar.setText("Cargar Partida Guardada");
        btnCargar.setToolTipText("Retomar la última partida guardada en simulacion.txt");
        btnCargar.setPreferredSize(new java.awt.Dimension(200, 40));
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlBotones.add(btnCargar, gridBagConstraints);

        pnlBtnIniciar.setBackground(new java.awt.Color(27, 107, 71));
        pnlBtnIniciar.setPreferredSize(new java.awt.Dimension(210, 40));
        pnlBtnIniciar.setLayout(new java.awt.GridBagLayout());

        btnIniciar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciar.setMnemonic('I');
        btnIniciar.setText("Iniciar Simulación");
        btnIniciar.setToolTipText("Crear el ecosistema con estos valores");
        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        btnIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarMouseExited(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlBtnIniciar.add(btnIniciar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        pnlBotones.add(pnlBtnIniciar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        pnlTarjeta.add(pnlBotones, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
        pnlContenido.add(pnlTarjeta, gridBagConstraints);

        add(pnlContenido, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        if (controlador != null) {
            controlador.cargarPartidaGuardada();
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        if (controlador != null) {
            controlador.configurarNuevaSimulacion(
                String.valueOf(spnPlantas.getValue()),
                String.valueOf(spnConejos.getValue()),
                String.valueOf(spnLobos.getValue()),
                String.valueOf(spnTurnos.getValue()),
                (String) cmbClima.getSelectedItem()
            );
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnIniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseEntered
        pnlBtnIniciar.setBackground(new java.awt.Color(21, 85, 56));
    }//GEN-LAST:event_btnIniciarMouseEntered

    private void btnIniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarMouseExited
        pnlBtnIniciar.setBackground(new java.awt.Color(27, 107, 71));
    }//GEN-LAST:event_btnIniciarMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JComboBox<String> cmbClima;
    private javax.swing.JLabel lblClimaInicial;
    private javax.swing.JLabel lblComoFunciona;
    private javax.swing.JLabel lblConejos;
    private javax.swing.JLabel lblConejosRango;
    private javax.swing.JLabel lblLobos;
    private javax.swing.JLabel lblLobosRango;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMarcaDescripcion;
    private javax.swing.JLabel lblPaso1;
    private javax.swing.JLabel lblPaso2;
    private javax.swing.JLabel lblPaso3;
    private javax.swing.JLabel lblPaso4;
    private javax.swing.JLabel lblPlantas;
    private javax.swing.JLabel lblPlantasRango;
    private javax.swing.JLabel lblSeccionPartida;
    private javax.swing.JLabel lblSeccionPoblacion;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTurnos;
    private javax.swing.JLabel lblTurnosRango;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlBtnIniciar;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlMarca;
    private javax.swing.JPanel pnlMarcaRelleno;
    private javax.swing.JPanel pnlTarjeta;
    private javax.swing.JSpinner spnConejos;
    private javax.swing.JSpinner spnLobos;
    private javax.swing.JSpinner spnPlantas;
    private javax.swing.JSpinner spnTurnos;
    // End of variables declaration//GEN-END:variables
}

package vista;

import javax.swing.table.DefaultTableModel;
import modelo.ReporteFinal;

/**
 * Dialogo modal con el reporte final de la simulacion.
 * Solo presenta los datos del DTO que le entrega la ventana principal.
 */
public class ReporteDialog extends javax.swing.JDialog {

    public ReporteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(btnCerrar);
        setLocationRelativeTo(parent);
    }

    public void mostrarReporte(ReporteFinal reporte) {
        lblCausaFin.setText(reporte.getCausaFin());
        lblMayorActividad.setText("Turno de mayor actividad: " + reporte.getTurnoMayorActividad()
                + " (" + reporte.getMaxEventosTurno() + " eventos)");

        DefaultTableModel modelo = (DefaultTableModel) tblPoblacion.getModel();
        modelo.setRowCount(0);
        modelo.addRow(new Object[] { "Plantas", reporte.getNacimientosPlantas(), reporte.getMuertesPlantas(),
            pico(reporte.getMaxPlantas(), reporte.getTurnoMaxPlantas()), pico(reporte.getMinPlantas(), reporte.getTurnoMinPlantas()) });
        modelo.addRow(new Object[] { "Conejos", reporte.getNacimientosConejos(), reporte.getMuertesConejos(),
            pico(reporte.getMaxConejos(), reporte.getTurnoMaxConejos()), pico(reporte.getMinConejos(), reporte.getTurnoMinConejos()) });
        modelo.addRow(new Object[] { "Lobos", reporte.getNacimientosLobos(), reporte.getMuertesLobos(),
            pico(reporte.getMaxLobos(), reporte.getTurnoMaxLobos()), pico(reporte.getMinLobos(), reporte.getTurnoMinLobos()) });

        lblPlantaLongeva.setText(reporte.getPlantaMasLongeva());
        lblConejoLongevo.setText(reporte.getConejoMasLongevo());
        lblLoboLongevo.setText(reporte.getLoboMasLongevo());
        lblMejorCazador.setText(reporte.getMejorCazador());
        lstPeligrosas.setListData(reporte.getEntidadesPeligrosas().toArray(new String[0]));
    }

    private String pico(int cantidad, int turno) {
        return cantidad + " (turno " + turno + ")";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        pnlEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCausaFin = new javax.swing.JLabel();
        lblMayorActividad = new javax.swing.JLabel();
        pnlCuerpo = new javax.swing.JPanel();
        pnlPoblacion = new javax.swing.JPanel();
        lblTituloPoblacion = new javax.swing.JLabel();
        scrPoblacion = new javax.swing.JScrollPane();
        tblPoblacion = new javax.swing.JTable();
        pnlDestacados = new javax.swing.JPanel();
        lblTituloDestacados = new javax.swing.JLabel();
        lblTituloPlantaLongeva = new javax.swing.JLabel();
        lblPlantaLongeva = new javax.swing.JLabel();
        lblTituloConejoLongevo = new javax.swing.JLabel();
        lblConejoLongevo = new javax.swing.JLabel();
        lblTituloLoboLongevo = new javax.swing.JLabel();
        lblLoboLongevo = new javax.swing.JLabel();
        lblTituloMejorCazador = new javax.swing.JLabel();
        lblMejorCazador = new javax.swing.JLabel();
        pnlRellenoDestacados = new javax.swing.JPanel();
        pnlPeligrosas = new javax.swing.JPanel();
        lblTituloPeligrosas = new javax.swing.JLabel();
        scrPeligrosas = new javax.swing.JScrollPane();
        lstPeligrosas = new javax.swing.JList<>();
        pnlPie = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte final");
        setBackground(new java.awt.Color(244, 246, 243));
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlEncabezado.setBackground(new java.awt.Color(20, 82, 58));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 24, 18, 24));
        pnlEncabezado.setLayout(new java.awt.GridBagLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Reporte final");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        pnlEncabezado.add(lblTitulo, gridBagConstraints);

        lblCausaFin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCausaFin.setForeground(new java.awt.Color(207, 227, 216));
        lblCausaFin.setText("Causa de fin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        pnlEncabezado.add(lblCausaFin, gridBagConstraints);

        lblMayorActividad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMayorActividad.setForeground(new java.awt.Color(255, 255, 255));
        lblMayorActividad.setText("Turno de mayor actividad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        pnlEncabezado.add(lblMayorActividad, gridBagConstraints);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlCuerpo.setBackground(new java.awt.Color(244, 246, 243));
        pnlCuerpo.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 24, 12, 24));
        pnlCuerpo.setLayout(new java.awt.GridBagLayout());

        pnlPoblacion.setOpaque(false);
        pnlPoblacion.setLayout(new java.awt.BorderLayout());

        lblTituloPoblacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTituloPoblacion.setForeground(new java.awt.Color(28, 43, 35));
        lblTituloPoblacion.setText("Población por especie");
        lblTituloPoblacion.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 6, 0));
        pnlPoblacion.add(lblTituloPoblacion, java.awt.BorderLayout.NORTH);

        scrPoblacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 223, 218)));
        scrPoblacion.setPreferredSize(new java.awt.Dimension(700, 110));

        tblPoblacion.setBackground(new java.awt.Color(255, 255, 255));
        tblPoblacion.setFillsViewportHeight(true);
        tblPoblacion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblPoblacion.setGridColor(new java.awt.Color(232, 236, 233));
        tblPoblacion.setIntercellSpacing(new java.awt.Dimension(10, 1));
        tblPoblacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Especie", "Nacimientos", "Muertes", "Población máxima", "Población mínima"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        tblPoblacion.setRowHeight(26);
        tblPoblacion.setSelectionBackground(new java.awt.Color(220, 239, 228));
        tblPoblacion.setSelectionForeground(new java.awt.Color(28, 43, 35));
        tblPoblacion.setShowVerticalLines(false);
        scrPoblacion.setViewportView(tblPoblacion);

        pnlPoblacion.add(scrPoblacion, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 16, 0);
        pnlCuerpo.add(pnlPoblacion, gridBagConstraints);

        pnlDestacados.setBackground(new java.awt.Color(255, 255, 255));
        pnlDestacados.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 223, 218)), javax.swing.BorderFactory.createEmptyBorder(14, 16, 8, 16)));
        pnlDestacados.setLayout(new java.awt.GridBagLayout());

        lblTituloDestacados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTituloDestacados.setForeground(new java.awt.Color(28, 43, 35));
        lblTituloDestacados.setText("Entidades destacadas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        pnlDestacados.add(lblTituloDestacados, gridBagConstraints);

        lblTituloPlantaLongeva.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTituloPlantaLongeva.setForeground(new java.awt.Color(85, 101, 92));
        lblTituloPlantaLongeva.setText("Planta más longeva");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 12);
        pnlDestacados.add(lblTituloPlantaLongeva, gridBagConstraints);

        lblPlantaLongeva.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPlantaLongeva.setForeground(new java.awt.Color(28, 43, 35));
        lblPlantaLongeva.setText("—");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlDestacados.add(lblPlantaLongeva, gridBagConstraints);

        lblTituloConejoLongevo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTituloConejoLongevo.setForeground(new java.awt.Color(85, 101, 92));
        lblTituloConejoLongevo.setText("Conejo más longevo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 12);
        pnlDestacados.add(lblTituloConejoLongevo, gridBagConstraints);

        lblConejoLongevo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblConejoLongevo.setForeground(new java.awt.Color(28, 43, 35));
        lblConejoLongevo.setText("—");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlDestacados.add(lblConejoLongevo, gridBagConstraints);

        lblTituloLoboLongevo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTituloLoboLongevo.setForeground(new java.awt.Color(85, 101, 92));
        lblTituloLoboLongevo.setText("Lobo más longevo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 12);
        pnlDestacados.add(lblTituloLoboLongevo, gridBagConstraints);

        lblLoboLongevo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblLoboLongevo.setForeground(new java.awt.Color(28, 43, 35));
        lblLoboLongevo.setText("—");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlDestacados.add(lblLoboLongevo, gridBagConstraints);

        lblTituloMejorCazador.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTituloMejorCazador.setForeground(new java.awt.Color(85, 101, 92));
        lblTituloMejorCazador.setText("Mejor cazador");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 12);
        pnlDestacados.add(lblTituloMejorCazador, gridBagConstraints);

        lblMejorCazador.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMejorCazador.setForeground(new java.awt.Color(28, 43, 35));
        lblMejorCazador.setText("—");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 8, 0);
        pnlDestacados.add(lblMejorCazador, gridBagConstraints);

        pnlRellenoDestacados.setOpaque(false);
        pnlRellenoDestacados.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        pnlDestacados.add(pnlRellenoDestacados, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        pnlCuerpo.add(pnlDestacados, gridBagConstraints);

        pnlPeligrosas.setOpaque(false);
        pnlPeligrosas.setPreferredSize(new java.awt.Dimension(300, 170));
        pnlPeligrosas.setLayout(new java.awt.BorderLayout());

        lblTituloPeligrosas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTituloPeligrosas.setForeground(new java.awt.Color(28, 43, 35));
        lblTituloPeligrosas.setText("Entidades peligrosas");
        lblTituloPeligrosas.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 6, 0));
        pnlPeligrosas.add(lblTituloPeligrosas, java.awt.BorderLayout.NORTH);

        scrPeligrosas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(216, 223, 218)));

        lstPeligrosas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lstPeligrosas.setSelectionBackground(new java.awt.Color(220, 239, 228));
        lstPeligrosas.setSelectionForeground(new java.awt.Color(28, 43, 35));
        scrPeligrosas.setViewportView(lstPeligrosas);

        pnlPeligrosas.add(scrPeligrosas, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        pnlCuerpo.add(pnlPeligrosas, gridBagConstraints);

        getContentPane().add(pnlCuerpo, java.awt.BorderLayout.CENTER);

        pnlPie.setBackground(new java.awt.Color(244, 246, 243));
        pnlPie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 24, 16, 24));
        pnlPie.setLayout(new java.awt.GridBagLayout());

        btnCerrar.setBackground(new java.awt.Color(27, 107, 71));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setMnemonic(67);
        btnCerrar.setText("Cerrar");
        btnCerrar.setToolTipText("Volver a la simulación");
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.setOpaque(true);
        btnCerrar.setPreferredSize(new java.awt.Dimension(210, 40));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        pnlPie.add(btnCerrar, gridBagConstraints);

        getContentPane().add(pnlPie, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new java.awt.Color(21, 85, 56));
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        btnCerrar.setBackground(new java.awt.Color(27, 107, 71));
    }//GEN-LAST:event_btnCerrarMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel lblCausaFin;
    private javax.swing.JLabel lblConejoLongevo;
    private javax.swing.JLabel lblLoboLongevo;
    private javax.swing.JLabel lblMayorActividad;
    private javax.swing.JLabel lblMejorCazador;
    private javax.swing.JLabel lblPlantaLongeva;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloConejoLongevo;
    private javax.swing.JLabel lblTituloDestacados;
    private javax.swing.JLabel lblTituloLoboLongevo;
    private javax.swing.JLabel lblTituloMejorCazador;
    private javax.swing.JLabel lblTituloPeligrosas;
    private javax.swing.JLabel lblTituloPlantaLongeva;
    private javax.swing.JLabel lblTituloPoblacion;
    private javax.swing.JList<String> lstPeligrosas;
    private javax.swing.JPanel pnlCuerpo;
    private javax.swing.JPanel pnlDestacados;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlPeligrosas;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlPoblacion;
    private javax.swing.JPanel pnlRellenoDestacados;
    private javax.swing.JScrollPane scrPeligrosas;
    private javax.swing.JScrollPane scrPoblacion;
    private javax.swing.JTable tblPoblacion;
    // End of variables declaration//GEN-END:variables
}

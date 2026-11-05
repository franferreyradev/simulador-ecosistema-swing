package vista;

import controlador.EcosistemaController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Clima;
import modelo.Entidad;
import modelo.ReporteFinal;

/**
 * Ventana de la aplicacion e implementacion del contrato que usa el controlador.
 * Aloja las pantallas de configuracion y simulacion y alterna cual esta visible.
 */
public class VentanaPrincipal extends javax.swing.JFrame implements IVistaEcosistema {

    private EcosistemaController controlador;

    public VentanaPrincipal() {
        initComponents();
    }

    public void setControlador(EcosistemaController controlador) {
        this.controlador = controlador;
        configuracionView.setControlador(controlador);
        simulacionView.setControlador(controlador);
    }

    @Override
    public void mostrarPantallaConfiguracion() {
        simulacionView.setVisible(false);
        configuracionView.setVisible(true);
        getRootPane().setDefaultButton(configuracionView.getBotonPrincipal());
    }

    @Override
    public void mostrarPantallaSimulacion() {
        simulacionView.prepararNuevaPartida();
        configuracionView.setVisible(false);
        simulacionView.setVisible(true);
        getRootPane().setDefaultButton(simulacionView.getBotonPrincipal());
        simulacionView.getBotonPrincipal().requestFocusInWindow();
    }

    @Override
    public void mostrarReporteFinal(ReporteFinal reporte) {
        simulacionView.marcarFinalizada();
        getRootPane().setDefaultButton(null);
        ReporteDialog dialogo = new ReporteDialog(this, true);
        dialogo.mostrarReporte(reporte);
        dialogo.setVisible(true);
    }

    @Override
    public void actualizarDashboard(int turnoActual, int turnosTotales, Clima clima, int plantasVivas, int conejosVivos, int lobosVivos) {
        simulacionView.actualizarDashboard(turnoActual, turnosTotales, clima.name(), plantasVivas, conejosVivos, lobosVivos);
    }

    @Override
    public void actualizarTablaEntidades(ArrayList<Entidad> entidades) {
        simulacionView.actualizarTablaEntidades(entidades);
    }

    @Override
    public void mostrarEventosTurno(ArrayList<String> eventos) {
        simulacionView.agregarEventos(eventos);
    }

    @Override
    public void configurarPanelIntervencion(boolean visible, boolean puedeAgregarLobo) {
        simulacionView.habilitarIntervencion(visible, puedeAgregarLobo);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Simulador de Ecosistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public boolean solicitarConfirmacion(String mensaje, String titulo) {
        Object[] opciones = { "Sí, continuar", "Cancelar" };
        return JOptionPane.showOptionDialog(this, mensaje, titulo, JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]) == 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        configuracionView = new vista.ConfiguracionView();
        simulacionView = new vista.SimulacionView();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Simulador de Ecosistema");
        setMinimumSize(new java.awt.Dimension(1000, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(configuracionView, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(simulacionView, gridBagConstraints);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (controlador == null || controlador.confirmarSalida()) {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.ConfiguracionView configuracionView;
    private vista.SimulacionView simulacionView;
    // End of variables declaration//GEN-END:variables
}

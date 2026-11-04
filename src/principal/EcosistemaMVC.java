package principal;

import controlador.EcosistemaController;
import dao.EcosistemaDAO;
import modelo.GestorEcosistema;
import vista.VentanaPrincipal;

/**
 * Punto de entrada: instancia las piezas del MVC, las conecta y despliega la aplicacion.
 */
public class EcosistemaMVC {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println("No se pudo aplicar el aspecto del sistema: " + ex.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            EcosistemaController controlador = new EcosistemaController(new GestorEcosistema(), ventana, new EcosistemaDAO());
            ventana.setControlador(controlador);
            controlador.iniciar();
            ventana.setVisible(true);
        });
    }
}

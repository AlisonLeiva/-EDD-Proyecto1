package Proyecto1;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author GAMING
 */
public class EDDProyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        Principal p = new Principal();
    }

}

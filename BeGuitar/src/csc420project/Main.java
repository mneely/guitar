/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package csc420project;

/**
 *
 * @author Ethan
 */
import javax.swing.UIManager.*;
import javax.swing.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                GUI.createandshowGUI();

            }
        });
    }
}

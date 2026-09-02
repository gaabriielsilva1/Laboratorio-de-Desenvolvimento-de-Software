/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projeto;

/**
 *
 * @author laboratorio
 */
import javax.swing.SwingUtilities;

public class Projeto {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroPessoa().setVisible(true);
        });
    }
}

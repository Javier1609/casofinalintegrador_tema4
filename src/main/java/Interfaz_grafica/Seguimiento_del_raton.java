package Interfaz_grafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Seguimiento_del_raton extends JFrame {
    private JDesktopPane desktopPane;
    private JLabel labelX;
    private JLabel labelY;

    public void Seguimiento_del_raton() {
        setTitle("Editor de Texto");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JDesktopPane y agregarlo al JFrame
        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        // Crear un botón para abrir nuevas ventanas
        JButton newWindowButton = new JButton("Nueva Ventana");
        newWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un nuevo JInternalFrame y agregarlo al JDesktopPane
                JInternalFrame internalFrame = new JInternalFrame("Documento", true, true, true, true);
                JTextArea textArea = new JTextArea();
                internalFrame.add(new JScrollPane(textArea));
                internalFrame.setSize(200, 200);
                internalFrame.setVisible(true);
                desktopPane.add(internalFrame);
            }
        });

        // Crear las etiquetas para las coordenadas del ratón
        labelX = new JLabel("X: ");
        labelY = new JLabel("Y: ");

        // Agregar un MouseMotionListener al JDesktopPane para actualizar las etiquetas
        desktopPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                labelX.setText("X: " + e.getX());
                labelY.setText("Y: " + e.getY());
            }
        });

        // Agregar el botón y las etiquetas al JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newWindowButton);
        buttonPanel.add(labelX);
        buttonPanel.add(labelY);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Multiplicidad_de_ventanas().setVisible(true);
            }
        });
    }
}
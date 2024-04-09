package Interfaz_grafica;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Barra_desplazamiento extends JFrame {
    private JDesktopPane desktopPane;
    private JLabel labelX;
    private JLabel labelY;
    private JSlider slider;

    public Barra_desplazamiento() {
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
                JScrollPane scrollPane = new JScrollPane(textArea);
                internalFrame.add(scrollPane);
                internalFrame.setSize(200, 200);
                internalFrame.setVisible(true);
                desktopPane.add(internalFrame);

                // Sincronizar el JSlider con la barra de desplazamiento del JScrollPane
                JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
                scrollBar.addAdjustmentListener(new AdjustmentListener() {
                    @Override
                    public void adjustmentValueChanged(AdjustmentEvent e) {
                        slider.setValue(e.getValue());
                    }
                });
                slider.setMaximum(scrollBar.getMaximum());
                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        scrollBar.setValue(slider.getValue());
                    }
                });
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

        // Crear el JSlider
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);

        // Agregar el botón, las etiquetas y el JSlider al JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newWindowButton);
        buttonPanel.add(labelX);
        buttonPanel.add(labelY);
        buttonPanel.add(slider);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Barra_desplazamiento().setVisible(true);
            }
        });
    }
}
package Interfaz_grafica;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.stream.Collectors;

public class Multiplicidad_de_ventanas extends JFrame {
    private JDesktopPane desktopPane;

    public Multiplicidad_de_ventanas() {
        setTitle("Editor de Texto");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JDesktopPane y agregarlo al JFrame
        desktopPane = new JDesktopPane();
        add(desktopPane);

        JButton newWindowButton = new JButton("Nueva Ventana");
        newWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame internalFrame = new JInternalFrame("Documento", true, true, true, true);
                JTextArea textArea = new JTextArea();
                internalFrame.add(new JScrollPane(textArea));
                internalFrame.setSize(200, 200);
                internalFrame.setVisible(true);
                desktopPane.add(internalFrame);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newWindowButton);
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
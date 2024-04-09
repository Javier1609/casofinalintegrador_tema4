package Comparador_y_contador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;

public class Herramientas_de_creador extends JFrame {
    private JTextArea textArea;
    private JButton compareFilesButton;

    public Herramientas_de_creador() {
        compareFilesButton = new JButton("Comparar archivos");
        compareFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue1 = fileChooser.showOpenDialog(null);
                if (returnValue1 == JFileChooser.APPROVE_OPTION) {
                    File selectedFile1 = fileChooser.getSelectedFile();
                    int returnValue2 = fileChooser.showOpenDialog(null);
                    if (returnValue2 == JFileChooser.APPROVE_OPTION) {
                        File selectedFile2 = fileChooser.getSelectedFile();
                        try {
                            String content1 = new String(Files.readAllBytes(selectedFile1.toPath()));
                            String content2 = new String(Files.readAllBytes(selectedFile2.toPath()));
                            if (content1.equals(content2)) {
                                JOptionPane.showMessageDialog(null, "Los archivos son iguales.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Los archivos son diferentes.");
                            }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });

        // Agregar el botón a la interfaz de usuario
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(compareFilesButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
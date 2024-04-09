package Busqueda_y_gestion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Buscador_de_palabras extends JFrame {
    private JTextArea textArea;
    private JButton compareFilesButton;
    private JButton analyzeTextButton;

    public Buscador_de_palabras() {
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
                            JOptionPane.showMessageDialog(null, "Error al leer los archivos.");
                        }
                    }
                }
            }
        });

        analyzeTextButton = new JButton("Análisis de Texto");
        analyzeTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        String content = new String(Files.readAllBytes(selectedFile.toPath()));
                        String[] words = content.split("\\s+");
                        int wordCount = words.length;

                        Map<String, Long> wordFrequency = Arrays.stream(words)
                                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

                        JOptionPane.showMessageDialog(null, "Número de palabras: " + wordCount + "\nFrecuencia de palabras: " + wordFrequency);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
                    }
                }
            }
        });

        // Agregar los botones a la interfaz de usuario
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(compareFilesButton);
        buttonPanel.add(analyzeTextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Buscador_de_palabras().setVisible(true);
            }
        });
    }
}
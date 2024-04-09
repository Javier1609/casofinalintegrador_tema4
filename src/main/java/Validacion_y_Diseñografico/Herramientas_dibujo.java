package Validacion_y_Diseñografico;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Herramientas_dibujo extends JFrame {
    private JDesktopPane desktopPane;
    private JLabel labelX;
    private JLabel labelY;
    private JSlider slider;
    private JTextField emailField;
    private ArrayList<ArrayList<Point>> lines = new ArrayList<>();

    public Herramientas_dibujo() {
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

        // Crear el JTextField y agregar un DocumentListener
        emailField = new JTextField(20);
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateEmail();
            }

            // Método para validar el correo electrónico
            private void validateEmail() {
                String email = emailField.getText();
                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    // Si el correo electrónico es válido, cambiar el color de fondo a verde
                    emailField.setBackground(Color.GREEN);
                } else {
                    // Si el correo electrónico no es válido, cambiar el color de fondo a rojo
                    emailField.setBackground(Color.RED);
                }
            }
        });

        // Agregar el botón, las etiquetas, el JSlider y el JTextField al JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newWindowButton);
        buttonPanel.add(labelX);
        buttonPanel.add(labelY);
        buttonPanel.add(slider);
        buttonPanel.add(emailField);
        add(buttonPanel, BorderLayout.SOUTH);

        // Agregar un MouseAdapter para la herramienta de dibujo
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private ArrayList<Point> currentLine;

            @Override
            public void mousePressed(MouseEvent e) {
                currentLine = new ArrayList<>();
                currentLine.add(e.getPoint());
                lines.add(currentLine);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentLine.add(e.getPoint());
                repaint();
            }
        };

        desktopPane.addMouseListener(mouseAdapter);
        desktopPane.addMouseMotionListener(mouseAdapter);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Herramientas_dibujo().setVisible(true);
            }
        });
    }
}

package Busqueda_y_gestion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda_de_contactos extends JFrame {
    private JTextArea textArea;
    private JButton compareFilesButton;
    private JButton analyzeTextButton;
    private JButton searchWordButton;
    private JButton contactsButton;
    private Agenda agenda;

    public Agenda_de_contactos() {
        compareFilesButton = new JButton("Comparar archivos");
        compareFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // código existente
            }
        });

        analyzeTextButton = new JButton("Análisis de Texto");
        analyzeTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // código existente
            }
        });

        searchWordButton = new JButton("Buscar Palabras");
        searchWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // código existente
            }
        });

        contactsButton = new JButton("Agenda de Contactos");
        contactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Agregar los botones a la interfaz de usuario
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(compareFilesButton);
        buttonPanel.add(analyzeTextButton);
        buttonPanel.add(searchWordButton);
        buttonPanel.add(contactsButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Inicializar la agenda de contactos
        agenda = new Agenda();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Buscador_de_palabras().setVisible(true);
            }
        });
    }

    static class Contacto {
        private String nombre;
        private String email;
        private String numeroTelefono;

        public Contacto(String nombre, String email, String numeroTelefono) {
            this.nombre = nombre;
            this.email = email;
            this.numeroTelefono = numeroTelefono;
        }

        // getters y setters
        public String getNombre() {
            return nombre;
        }

        public String getEmail() {
            return email;
        }

        public String getNumeroTelefono() {
            return numeroTelefono;
        }
    }

    class Agenda {
        private List<Contacto> contactos;

        public Agenda() {
            this.contactos = new ArrayList<>();
        }

        public void agregarContacto(Contacto contacto) {
            contactos.add(contacto);
        }

        public void eliminarContacto(Contacto contacto) {
            contactos.remove(contacto);
        }

        public Contacto buscarContacto(String nombre) {
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equals(nombre)) {
                    return contacto;
                }
            }
            return null;
        }

        // otros métodos según sea necesario
    }
}
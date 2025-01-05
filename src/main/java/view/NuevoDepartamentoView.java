package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
// PANTALLA QUE SE MUESTRA AL QUERER CREAR UN NUEVO DEPARTAMENTO.
public class NuevoDepartamentoView extends JDialog {

    private JTextField nombreField;
    private JButton anadirButton;

    public NuevoDepartamentoView() {
        setTitle("Nuevo Departamento");
        setSize(300, 200); // Aumentamos un poco la altura para hacerlo más espacioso
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Diseño de la vista
        setLayout(new BorderLayout(10, 10)); // Usamos BorderLayout con márgenes

        // Panel para el texto, centrado en la parte superior
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label = new JLabel("Nombre del Departamento:");
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente un poco más grande para la legibilidad
        panelTexto.add(label);
        add(panelTexto, BorderLayout.NORTH);

        // Campo de texto para el nombre del departamento
        nombreField = new JTextField();
        nombreField.setPreferredSize(new Dimension(200, 30)); // Ajustamos el tamaño del campo de texto
        add(nombreField, BorderLayout.CENTER);

        // Botón de añadir departamento, centrado en la parte inferior
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        anadirButton = new JButton("Añadir Departamento");
        panelBoton.add(anadirButton);
        add(panelBoton, BorderLayout.SOUTH);
    }

    // Getters para poder interactuar con los elementos de la vista.

    public String getNombreDepartamento() {
        return nombreField.getText(); // Devuelve el texto ingresado en el JTextField
    }

    // Utilizado para tener una mejor gestión con el evento del botón "Añadir Departamento".
    public void addAnadirDepartamentoListener(ActionListener listener) {
        anadirButton.addActionListener(listener);
    }
}

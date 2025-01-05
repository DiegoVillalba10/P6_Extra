package view;

import model.Departamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

// PANTALLA QUE SE MUESTRA AL QUERER AÑADIR UN NUEVO EMPLEADO.

public class NuevoEmpleadoView extends JDialog {

    private JTextField nombreField;
    private JTextField apellido1Field;
    private JTextField apellido2Field;
    private JTextField dniField;
    private JTextField salarioField;
    private JTextField fechaIncorporacionField;
    private JComboBox<Departamento> departamentoComboBox;
    private JButton anadirButton;
    private JButton fotoButton;
    private byte[] fotoBytes;  // Almacenamos la foto como un arreglo de bytes

    public NuevoEmpleadoView(List<Departamento> departamentos) {
        setTitle("Nuevo Empleado");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Diseño de la vista
        setLayout(new GridLayout(10, 2, 10, 10)); // Agregamos espaciado entre filas y columnas

        // Crear y centrar los JLabel
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(nombreLabel);
        nombreField = new JTextField();
        add(nombreField);

        JLabel apellido1Label = new JLabel("Apellido 1:");
        apellido1Label.setHorizontalAlignment(SwingConstants.CENTER);
        add(apellido1Label);
        apellido1Field = new JTextField();
        add(apellido1Field);

        JLabel apellido2Label = new JLabel("Apellido 2:");
        apellido2Label.setHorizontalAlignment(SwingConstants.CENTER);
        add(apellido2Label);
        apellido2Field = new JTextField();
        add(apellido2Field);

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(dniLabel);
        dniField = new JTextField();
        add(dniField);

        JLabel salarioLabel = new JLabel("Salario:");
        salarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(salarioLabel);
        salarioField = new JTextField();
        add(salarioField);

        JLabel fechaIncorporacionLabel = new JLabel("Fecha Incorporación:");
        fechaIncorporacionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(fechaIncorporacionLabel);
        fechaIncorporacionField = new JTextField();
        add(fechaIncorporacionField);

        JLabel departamentoLabel = new JLabel("Departamento:");
        departamentoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(departamentoLabel);
        departamentoComboBox = new JComboBox<>(departamentos.toArray(new Departamento[0]));
        add(departamentoComboBox);

        // Botón para seleccionar la foto
        fotoButton = new JButton("Seleccionar Foto");
        add(fotoButton);

        // Botón para añadir un empleado
        anadirButton = new JButton("Añadir Empleado");
        add(anadirButton);
    }

    // Getters para poder interactuar con los elementos de la vista.

    public String getNombre() {
        return nombreField.getText();
    }

    public String getApellido1() {
        return apellido1Field.getText();
    }

    public String getApellido2() {
        return apellido2Field.getText();
    }

    public String getDni() {
        return dniField.getText();
    }

    public double getSalario() {
        return Double.parseDouble(salarioField.getText());
    }

    public String getFechaIncorporacion() {
        return fechaIncorporacionField.getText();
    }

    public String getDepartamento() {
        return departamentoComboBox.getSelectedItem().toString();
    }

    public byte[] getFotoBytes() {
        return fotoBytes;
    }

    // Métodos para poder añadir eventos a los botones de manera más organizada.

    public void addAnadirEmpleadoListener(ActionListener listener) {
        anadirButton.addActionListener(listener);
    }

    public void addSeleccionarFotoListener(ActionListener listener) {
        fotoButton.addActionListener(listener);
    }

    // Permite que el usuario seleccione una foto del empleado.

    public void seleccionarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Foto");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Filtros para mostrar solo imágenes
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.fotoBytes = convertirArchivoABytes(selectedFile);  // Convertir la foto a byte[]
        }
    }

    // Convertimos la foto a un archivo de bytes para que sea procesada por la base de datos.
    private byte[] convertirArchivoABytes(File archivo) {
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.FileInputStream fis = new java.io.FileInputStream(archivo);
            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesLeidos);
            }
            fis.close();
            return baos.toByteArray();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

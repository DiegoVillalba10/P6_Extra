package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// CLASE QUE MUESTRA LA LISTA DE EMPLEADOS EXISTENTES.

public class ListaEmpleadosView extends JDialog {
    private final JTable tablaEmpleados;
    private final DefaultTableModel modeloTabla;
    private final JButton botonEliminar;

    public ListaEmpleadosView() {
        setTitle("Lista de Empleados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        // Modelo de la tabla sin la columna de fotos
        modeloTabla = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre", "Apellido1", "Apellido2", "DNI", "Salario", "Fecha Incorporación", "Departamento"}
        );
        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Centrar texto en las columnas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
            tablaEmpleados.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        // Añadir tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        add(scrollPane, BorderLayout.CENTER);

        // Botón Eliminar
        botonEliminar = new JButton("Eliminar");
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonEliminar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // Getters para poder interactuar con los elementos de la vista.

    public JTable getTablaEmpleados() {
        return tablaEmpleados;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }
}

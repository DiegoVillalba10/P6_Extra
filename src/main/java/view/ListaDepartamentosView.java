package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// PANTALLA QUE MUESTRA LA LISTA DE DEPARTAMENTOS EXISTENTES.

public class ListaDepartamentosView extends JDialog {
    private JTable tablaDepartamentos;
    private DefaultTableModel modeloTabla;
    private JButton botonEliminar;

    public ListaDepartamentosView() {
        setTitle("Lista de Departamentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLayout(new BorderLayout());

        // Modelo de la tabla
        modeloTabla = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nombre"}
        );
        tablaDepartamentos = new JTable(modeloTabla);
        tablaDepartamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Centrar texto en las columnas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
            tablaDepartamentos.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        // Añadir tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaDepartamentos);
        add(scrollPane, BorderLayout.CENTER);

        // Botón Eliminar
        botonEliminar = new JButton("Eliminar");
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonEliminar);
        add(panelBotones, BorderLayout.SOUTH);
    }
    // Getters para poder interactuar con los elementos de la vista.

    public JTable getTablaDepartamentos() {
        return tablaDepartamentos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }
}


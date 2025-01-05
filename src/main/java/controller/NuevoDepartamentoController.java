package controller;

import view.NuevoDepartamentoView;
import dao.DepartamentoDAO;
import model.Departamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class NuevoDepartamentoController {

    private NuevoDepartamentoView nuevoDepartamentoView;
    private DepartamentoDAO departamentoDAO;

    public NuevoDepartamentoController(NuevoDepartamentoView nuevoDepartamentoView, DepartamentoDAO departamentoDAO) {
        this.nuevoDepartamentoView = nuevoDepartamentoView;
        this.departamentoDAO = departamentoDAO;
        // Evento que se ejecuta al elegir añadir un nuevo departamento.
        nuevoDepartamentoView.addAnadirDepartamentoListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el nombre del nuevo departamento
                    String nombreDepartamento = nuevoDepartamentoView.getNombreDepartamento();

                    // Crear el nuevo departamento
                    Departamento nuevoDepartamento = new Departamento(nombreDepartamento);

                    // Insertar en base de datos
                    departamentoDAO.insertarDepartamento(nuevoDepartamento);

                    // Mensaje de éxito.
                    JOptionPane.showMessageDialog(nuevoDepartamentoView, "Departamento agregado exitosamente.");

                    // Cerrar la ventana
                    nuevoDepartamentoView.dispose();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

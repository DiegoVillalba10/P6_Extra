package controller;

import model.Departamento;
import model.Empleado;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import utils.BaseDatos;
import view.*;
import dao.DepartamentoDAO;
import dao.EmpleadoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import java.util.List;

public class MainController {

    private final MainView mainView;
    private final DepartamentoDAO departamentoDAO;
    private final EmpleadoDAO empleadoDAO;

    public MainController(MainView mainView, DepartamentoDAO departamentoDAO, EmpleadoDAO empleadoDAO) {
        this.mainView = mainView;
        this.departamentoDAO = departamentoDAO;
        this.empleadoDAO = empleadoDAO;

        // Declaramos los eventos que ocurrirán al interactuar con los elementos de la vista principal.

        // 1. Nuevo Empleado.
        mainView.getItemNuevoEmpleado().addActionListener(e -> {
            try {
                // Obtener los departamentos desde el DAO
                List<Departamento> departamentos = departamentoDAO.listarDepartamentos();

                // Pasar la lista de departamentos al constructor de NuevoEmpleadoView
                NuevoEmpleadoView nuevoEmpleadoView = new NuevoEmpleadoView(departamentos);
                new NuevoEmpleadoController(nuevoEmpleadoView, empleadoDAO, departamentoDAO);
                nuevoEmpleadoView.setVisible(true);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(mainView, "Error al cargar los departamentos.");
                ex.printStackTrace();

            }
        });

        // 2. Lista de empleados.
        mainView.getItemListarEmpleados().addActionListener(e -> {
            try {
                listarEmpleados();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // 3. Nuevo departamento.

        mainView.getItemNuevoDepartamento().addActionListener(e -> {
            // Crear la vista para nuevo departamento y pasar el DAO
            NuevoDepartamentoView nuevoDepartamentoView = new NuevoDepartamentoView();
            new NuevoDepartamentoController(nuevoDepartamentoView, departamentoDAO);
            nuevoDepartamentoView.setVisible(true);
        });

        // 4. Lista de departamentos.
        mainView.getItemListarDepartamentos().addActionListener(e -> {
            try {
                listarDepartamentos();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // 5. Informes de Jasper a PDF
        mainView.getItemInforme().addActionListener(e -> generarInforme());
    }

    /* Estos métodos son utilizados por los eventos anteriores, se ha optado por realizar programación funcional para
    que el código sea más legible y limpio.
     */

        // Utilizado para obtener los departamentos de nuestra base de datos.
    private void listarDepartamentos() throws SQLException {
        ListaDepartamentosView listaDepartamentosView = new ListaDepartamentosView();
        DefaultTableModel dtm = listaDepartamentosView.getModeloTabla();

        // Obtener departamentos desde la base de datos
        List<Departamento> departamentos = departamentoDAO.listarDepartamentos();

        // Llenar la tabla con los datos
        for (Departamento departamento : departamentos) {
            dtm.addRow(new Object[]{
                    departamento.getId(),
                    departamento.getNombre()
            });
        }

        // Añadir funcionalidad al botón "Eliminar"
        listaDepartamentosView.getBotonEliminar().addActionListener(e -> {
            int filaSeleccionada = listaDepartamentosView.getTablaDepartamentos().getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtener el ID del departamento seleccionado
                int departamentoId = (int) dtm.getValueAt(filaSeleccionada, 0);
                int confirmacion = JOptionPane.showConfirmDialog(listaDepartamentosView,
                        "¿Estás seguro de que deseas eliminar este departamento?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        // Eliminar departamento de la base de datos
                        departamentoDAO.eliminarDepartamento(departamentoId);
                        MainView.actualizarEstado(MainView.labelEstado);

                        // Eliminar fila de la tabla
                        dtm.removeRow(filaSeleccionada);
                        JOptionPane.showMessageDialog(listaDepartamentosView, "Departamento eliminado correctamente.");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(listaDepartamentosView, "Error al eliminar el departamento.");
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(listaDepartamentosView, "Por favor, selecciona un departamento.");
            }
        });

        listaDepartamentosView.setVisible(true);
    }

    // Obtener la lista de empleados desde nuestra base de datos.

    private void listarEmpleados() throws SQLException {
        ListaEmpleadosView listaEmpleadosView = new ListaEmpleadosView();
        DefaultTableModel dtm = listaEmpleadosView.getModeloTabla();

        // Obtener empleados desde la base de datos
        List<Empleado> empleados = empleadoDAO.obtenerEmpleados();

        // Llenar la tabla con los datos
        for (Empleado empleado : empleados) {
            dtm.addRow(new Object[]{
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getApellido1(),
                    empleado.getApellido2(),
                    empleado.getDni(),
                    empleado.getSalario(),
                    empleado.getFechaIncorporacion(),
                    empleado.getDepartamentoId()
            });
        }

        // Añadir funcionalidad al botón "Eliminar"
        listaEmpleadosView.getBotonEliminar().addActionListener(e -> {
            int filaSeleccionada = listaEmpleadosView.getTablaEmpleados().getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtener el ID del empleado seleccionado
                int empleadoId = (int) dtm.getValueAt(filaSeleccionada, 0);
                int confirmacion = JOptionPane.showConfirmDialog(listaEmpleadosView,
                        "¿Estás seguro de que deseas eliminar este empleado?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        // Eliminar empleado de la base de datos
                        empleadoDAO.eliminarEmpleado(empleadoId);
                        MainView.actualizarEstado(MainView.labelEstado);

                        // Eliminar fila de la tabla
                        dtm.removeRow(filaSeleccionada);
                        JOptionPane.showMessageDialog(listaEmpleadosView, "Empleado eliminado correctamente.");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(listaEmpleadosView, "Error al eliminar el empleado.");
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(listaEmpleadosView, "Por favor, selecciona un empleado.");
            }
        });

        listaEmpleadosView.setVisible(true);
    }
        // Utilizado para la generación de informes mediante archivos previamente compilados en JasperSoft.
        private void generarInforme() {
            try {
                // Usamos un selector de archivo para que el usuario elija los archivos de formato Jasper.
                JFileChooser fileChooser = new JFileChooser("src/reports/");
                fileChooser.setMultiSelectionEnabled(true); // Permitir selección múltiple
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos Jasper", "jasper"));
                fileChooser.setDialogTitle("Selecciona los informes");

                // Mostrar el cuadro de diálogo y verificar si el usuario seleccionó archivos
                int result = fileChooser.showOpenDialog(mainView);
                if (result != JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(mainView, "No se ha seleccionado ningún archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener los archivos seleccionados
                File[] selectedFiles = fileChooser.getSelectedFiles();
                if (selectedFiles.length == 0) {
                    JOptionPane.showMessageDialog(mainView, "No se ha seleccionado ningún archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                System.out.println("Archivos seleccionados para generar informes:");
                for (File file : selectedFiles) {
                    System.out.println("- " + file.getName());
                }

                // Establecer la conexión con la base de datos
                try (Connection connection = BaseDatos.getConnection()) {
                    for (File selectedFile : selectedFiles) {
                        String reportPath = selectedFile.getAbsolutePath();

                        // Verificar si el archivo seleccionado es válido
                        if (!reportPath.endsWith(".jasper")) {
                            JOptionPane.showMessageDialog(mainView, "El archivo seleccionado no es un informe válido: " + selectedFile.getName(), "Error", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }

                        System.out.println("Cargando el informe: " + selectedFile.getName());

                        // Llenar el informe con la conexión a la base de datos
                        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, null, connection);

                        // Ruta donde se guardará el archivo PDF
                        String outputPath = "src/reports/" + selectedFile.getName().replace(".jasper", ".pdf");

                        // Exportar el informe a PDF
                        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);

                        // Mostrar el informe en el visor de Jasper.
                        JasperViewer.viewReport(jasperPrint, false);

                        System.out.println("Informe generado correctamente: " + outputPath);
                    }

                    // Mostrar un mensaje final
                    JOptionPane.showMessageDialog(mainView, "Todos los informes seleccionados han sido procesados.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(mainView, "Error al conectar a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (JRException e) {
                    JOptionPane.showMessageDialog(mainView, "Error al generar los informes.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainView, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
}

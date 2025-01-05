package controller;

import dao.DepartamentoDAO;
import dao.EmpleadoDAO;
import model.Departamento;
import model.Empleado;
import view.NuevoEmpleadoView;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class NuevoEmpleadoController {

    private final NuevoEmpleadoView nuevoEmpleadoView;
    private final EmpleadoDAO empleadoDAO;
    private final DepartamentoDAO departamentoDAO;

    public NuevoEmpleadoController(NuevoEmpleadoView nuevoEmpleadoView, EmpleadoDAO empleadoDAO, DepartamentoDAO departamentoDAO) {
        this.nuevoEmpleadoView = nuevoEmpleadoView;
        this.empleadoDAO = empleadoDAO;
        this.departamentoDAO = departamentoDAO;

        nuevoEmpleadoView.addAnadirEmpleadoListener(e -> {
            try {
                // Obtenemos los datos del formulario
                String nombre = nuevoEmpleadoView.getNombre();
                String apellido1 = nuevoEmpleadoView.getApellido1();
                String apellido2 = nuevoEmpleadoView.getApellido2();
                String dni = nuevoEmpleadoView.getDni();
                String salarioStr = String.valueOf(nuevoEmpleadoView.getSalario()); // Obtener salario como String
                String fecha = nuevoEmpleadoView.getFechaIncorporacion();
                String departamentoNombre = nuevoEmpleadoView.getDepartamento();
                byte[] fotoBytes = nuevoEmpleadoView.getFotoBytes(); // Obtener los bytes de la foto

                // Validaciones de campos obligatorios
                if (esCampoVacio(nombre)) {
                    mostrarError("El campo 'Nombre' no puede estar vacío.");
                    return;
                }
                if (esCampoVacio(apellido1)) {
                    mostrarError("El campo 'Primer Apellido' no puede estar vacío.");
                    return;
                }
                if (esCampoVacio(apellido2)) {
                    mostrarError("El campo 'Segundo Apellido' no puede estar vacío.");
                    return;
                }

                // Validamos el formato del DNI
                if (!esDniValido(dni)) {
                    mostrarError("El DNI ingresado no tiene un formato válido.");
                    return; // No continuamos con el flujo si el DNI no es válido
                }

                // Transformamos el salario a un valor double para nuestra base de datos
                double salario = Double.parseDouble(salarioStr);

                // Formateamos la fecha de incorporación
                java.sql.Date fechaIncorporacion = parseFecha(fecha);

                // Obtenemos el ID del departamento
                int departamentoId = obtenerIdDepartamento(departamentoNombre);

                // Creamos el nuevo empleado con sus atributos
                Empleado nuevoEmpleado = new Empleado(nombre, apellido1, apellido2, dni, salario, fechaIncorporacion, departamentoId, fotoBytes);

                // Insertar el empleado en la base de datos
                empleadoDAO.insertarEmpleado(nuevoEmpleado);

                // Mostramos el mensaje de éxito
                JOptionPane.showMessageDialog(nuevoEmpleadoView, "Empleado agregado exitosamente.");

                // Cerrar la ventana
                nuevoEmpleadoView.dispose();

            } catch (SQLException | ParseException ex) {
                JOptionPane.showMessageDialog(nuevoEmpleadoView, "Error al agregar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Agregar el listener para el botón "Seleccionar Foto"
        nuevoEmpleadoView.addSeleccionarFotoListener(e -> {
            // Llamamos a seleccionarFoto para que abra el cuadro de diálogo
            nuevoEmpleadoView.seleccionarFoto();
        });
    }

    // Validar que el DNI tiene el formato correcto (8 dígitos seguidos de una letra)
    private boolean esDniValido(String dni) {
        return dni != null && dni.matches("\\d{8}[A-Za-z]");
    }

    // Validar si un campo está vacío o es nulo
    private boolean esCampoVacio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    // Mostrar un mensaje de error
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(nuevoEmpleadoView, mensaje, "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }

    // Necesario para obtener la clave foránea (departamento_id) del empleado. Podemos obtener el nombre y de ahí el ID.
    private int obtenerIdDepartamento(String departamentoNombre) throws SQLException {
        // Listamos los departamentos
        List<Departamento> departamentos = departamentoDAO.listarDepartamentos();
        for (Departamento dep : departamentos) {
            // Si el nombre del departamento elegido coincide con algún nombre de un departamento...
            if (dep.getNombre().equals(departamentoNombre)) {
                // Devolvemos la ID que coincide
                return dep.getId();
            }
        }
        // Como no coincide no tiene un valor el ID
        return -1;
    }

    // Utilizado para transformar la fecha a nuestro formato
    private java.sql.Date parseFecha(String fecha) throws ParseException {
        if (fecha == null || fecha.isEmpty()) {
            throw new ParseException("La fecha no puede estar vacía.", 0);
        }
        // Declaramos el formato de fecha simple a ser utilizado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Transformamos la fecha pasada como argumento en el formato anteriormente establecido
        java.util.Date utilDate = dateFormat.parse(fecha);
        // Devolvemos una variable Date para ser añadida a la base de datos al crear un empleado
        return new java.sql.Date(utilDate.getTime());
    }
}

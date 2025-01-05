package dao;

import model.Empleado;
import utils.BaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.BaseDatos.getConnection;

public class EmpleadoDAO {
    private Connection connection;

    public EmpleadoDAO() throws SQLException {
        this.connection = getConnection();
    }

    // Utilizado para insertar un empleado en nuestra base de datos.
    public void insertarEmpleado(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO Empleado (nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, departamento_id, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido1());
            stmt.setString(3, empleado.getApellido2());
            stmt.setString(4, empleado.getDni());
            stmt.setDouble(5, empleado.getSalario());
            stmt.setDate(6, new java.sql.Date(empleado.getFechaIncorporacion().getTime()));
            stmt.setInt(7, empleado.getDepartamentoId());
            stmt.setBytes(8, empleado.getFoto());
            stmt.executeUpdate();
        }
    }
    // Utilizado para obtener la lista de empleados existentes.
    public List<Empleado> obtenerEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido1, apellido2, dni, salario, fecha_incorporacion, departamento_id, foto FROM empleado";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido1(rs.getString("apellido1"));
                empleado.setApellido2(rs.getString("apellido2"));
                empleado.setDni(rs.getString("dni"));
                empleado.setSalario(rs.getDouble("salario"));
                empleado.setFechaIncorporacion(rs.getDate("fecha_incorporacion"));
                empleado.setDepartamentoId(rs.getInt("departamento_id"));

                // Leer foto como byte[]
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    empleado.setFoto(blob.getBytes(1, (int) blob.length()));
                }

                empleados.add(empleado);
            }
        }
        return empleados;
    }
    // Utilizado para eliminar un empleado desde la vista encargada de la gestión de los empleados.
    public void eliminarEmpleado(int empleadoId) throws SQLException {
        String query = "DELETE FROM empleado WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, empleadoId);
            ps.executeUpdate();
        }
    }
    // Obtenemos el total de empleados, utilizado para ofrecer dicha información al usuario.
    public int totalEmpleados() throws SQLException {
        return obtenerEmpleados().size();
    }
}

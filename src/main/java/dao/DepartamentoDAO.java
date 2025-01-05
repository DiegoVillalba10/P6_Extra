package dao;

import model.Departamento;
import utils.BaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {
    private Connection connection;

    public DepartamentoDAO() throws SQLException {
        this.connection = BaseDatos.getConnection();
    }

    // Utilizado para insertar un departamento en nuestra base de datos.
    public void insertarDepartamento(Departamento departamento) throws SQLException {
        String sql = "INSERT INTO Departamento (nombre) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, departamento.getNombre());
            stmt.executeUpdate();
        }
    }
    // Utilizado para obtener la lista de departamentos existentes.
    public List<Departamento> listarDepartamentos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();
        String sql = "SELECT * FROM departamento";
        try (Connection connection = BaseDatos.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)
             ; ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(resultSet.getInt("id"));
                departamento.setNombre(resultSet.getString("nombre"));
                departamentos.add(departamento);
            }
        }
        return departamentos;
    }
    // Utilizado para eliminar un departamento desde la vista que gestiona los departamentos existentes.
    public void eliminarDepartamento(int departamentoId) throws SQLException {
        String query = "DELETE FROM departamento WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, departamentoId);
            ps.executeUpdate();
        }
    }
    // Devuelve el total de departamentos, utilizado para ofrecer información al usuario.
    public int totalDepartamentos() throws SQLException {
        return listarDepartamentos().size();
    }
}

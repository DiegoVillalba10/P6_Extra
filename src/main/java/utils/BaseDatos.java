package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// CLASE UTILIZADA ESTÁTICAMENTE PARA REALIZAR LA CONEXIÓN CON NUESTRA BASE DE DATOS.
public class BaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_empleados";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

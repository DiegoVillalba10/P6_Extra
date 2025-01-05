import controller.MainController;
import dao.DepartamentoDAO;
import dao.EmpleadoDAO;
import utils.BaseDatos;
import view.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Iniciar conexión a base de datos
            DepartamentoDAO departamentoDAO = new DepartamentoDAO();
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            
            // Creamos la vista principal.
            MainView mainView = new MainView();
            // Le asignamos el controlador principal con sus funcionalidades.
            MainController mainController = new MainController(mainView, departamentoDAO, empleadoDAO);
            // También se añade DepartamentoDAO y EmpleadoDAO para poder interactuar con la base de datos.
            
            // Mostrar la vista principal
            mainView.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.");
        }
    }
}

package view;

import dao.DepartamentoDAO;
import dao.EmpleadoDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

// PANTALLA PRINCIPAL DEL PROGRAMA QUE ALBERGA LAS DIFERENTES FUNCIONALIDADES DE ESTE.

public class MainView extends JFrame {

    private final JMenuBar menuBar;
    private final JMenu menuEmpleado;
    private final JMenu menuDepartamento;
    private final JMenu menuInformes;
    private final JMenuItem itemNuevoEmpleado;
    private final JMenuItem itemNuevoDepartamento;
    private final JMenuItem itemInforme;
    private final JMenuItem itemListarDepartamentos;
    private final JMenuItem itemListarEmpleados;

    public static JLabel labelEstado;

    public MainView() {
        setTitle("Gestión de Empleados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear JMenuBar
        menuBar = new JMenuBar();

        // Menú Empleado
        menuEmpleado = new JMenu("Empleado");
        itemNuevoEmpleado = new JMenuItem("Nuevo Empleado");
        itemListarEmpleados = new JMenuItem("Lista de Empleados");
        menuEmpleado.add(itemNuevoEmpleado);
        menuEmpleado.add(itemListarEmpleados);

        // Menú Departamento
        menuDepartamento = new JMenu("Departamento");
        itemNuevoDepartamento = new JMenuItem("Nuevo Departamento");
        itemListarDepartamentos = new JMenuItem("Lista de Departamentos");
        menuDepartamento.add(itemNuevoDepartamento);
        menuDepartamento.add(itemListarDepartamentos);

        // Menú Informes
        menuInformes = new JMenu("Informes");
        itemInforme = new JMenuItem("Generar Informe");
        menuInformes.add(itemInforme);

        // Añadir los menús al JMenuBar
        menuBar.add(menuEmpleado);
        menuBar.add(menuDepartamento);
        menuBar.add(menuInformes);

        // Establecer el JMenuBar
        setJMenuBar(menuBar);

        // Configuramos el panel de bienvenida.
        configurarPanelBienvenida();
    }

    private void configurarPanelBienvenida() {
        JTextArea areaBienvenida = new JTextArea();

        areaBienvenida.setText(
                "Bienvenido a la Aplicación de Gestión de Empleados y Departamentos.\n\n" +
                "Desde aquí podrás: \n" +
                "- Gestionar empleados y sus datos.\n" +
                "- Gestionar departamentos de la empresa.\n" +
                "- Generar informes de empleados y departamentos.\n\n" +
                "Selecciona una opción del menú superior para comenzar."
        );

        areaBienvenida.setFont(new Font("Arial", Font.BOLD, 15));
        areaBienvenida.setEditable(false);
        areaBienvenida.setBackground(getBackground());
        areaBienvenida.setLineWrap(true);
        areaBienvenida.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollBienvenida = new JScrollPane(areaBienvenida);
        scrollBienvenida.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelEstado = new JLabel("Cargando datos...");
        panelInferior.add(labelEstado);

        actualizarEstado(labelEstado);

        add(scrollBienvenida, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public static void actualizarEstado(JLabel labelEstado) {
        SwingUtilities.invokeLater(() -> {
            try {
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                int totalEmpleados = empleadoDAO.totalEmpleados();
                int totalDepartamentos = departamentoDAO.totalDepartamentos();
                labelEstado.setText("Total de empleados: " + totalEmpleados + " | Total de departamentos: " + totalDepartamentos);
            } catch (Exception e) {
                labelEstado.setText("Error al cargar datos.");
            }
        });
    }

    // Getters para poder interactuar con los elementos de la vista.

    public JMenuItem getItemNuevoEmpleado() {
        return itemNuevoEmpleado;
    }

    public JMenuItem getItemNuevoDepartamento() {
        return itemNuevoDepartamento;
    }

    public JMenuItem getItemInforme() {
        return itemInforme;
    }

    public JMenuItem getItemListarDepartamentos() {
        return itemListarDepartamentos;
    }

    public JMenuItem getItemListarEmpleados() {
        return itemListarEmpleados;
    }

    public JMenuBar getMenu() {
        return menuBar;
    }
}

package model;

import java.util.Date;
// CLASE MODELO QUE REFLEJA LA TABLA 'Empleado' DE LA BASE DE DATOS.
public class Empleado {
    // Atributos.
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    private double salario;
    private Date fechaIncorporacion;
    private int departamentoId;
    private byte[] foto;

    // Constructores.
    public Empleado(int id, String nombre, String apellido1, String apellido2, String dni, double salario, Date fechaIncorporacion, int departamentoId, byte[] foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.salario = salario;
        this.fechaIncorporacion = fechaIncorporacion;
        this.departamentoId = departamentoId;
        this.foto = foto;
    }

    public Empleado(String nombre, String apellido1, String apellido2, String dni, double salario, Date fechaIncorporacion, int departamentoId, byte[] foto) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.salario = salario;
        this.fechaIncorporacion = fechaIncorporacion;
        this.departamentoId = departamentoId;
        this.foto = foto;
    }

    public Empleado() {

    }

    // Getters y Setters.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(Date fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}

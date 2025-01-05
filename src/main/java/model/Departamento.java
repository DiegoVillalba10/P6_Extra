package model;
// CLASE MODELO QUE REFLEJA LA TABLA 'Departamento' DE LA BASE DE DATOS.
public class Departamento {
    // Atributos
    private int id;
    private String nombre;
    // Constructores
    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public Departamento() {

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

    // ToString().
    @Override
    public String toString() {
        return nombre;
    }
}

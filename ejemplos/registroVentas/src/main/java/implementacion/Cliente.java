package implementacion;

/**
 *Clase Cliente que nos permite crear clientes, representa una entidad de la tabla "cliente" 
 * en la base de datos ventas
 * @author alba_
 */
public class Cliente {
    //declaramos los atributos, cada uno corresponde a una columna en la tabla "cliente" de la base de datos:
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private int categoria;

    //declaramos los distintos constructores ya que en la base de datos hay campos que permiten nulos:
    /**
     * constructor con todos los argumentos
     * @param id
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param ciudad
     * @param categoria 
     */
    public Cliente(int id, String nombre, String apellido1, String apellido2, String ciudad, int categoria) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.ciudad = ciudad;
        this.categoria = categoria;
    }

    /**
     * constructor con todos los argumentos menos el id, que se genera automáticamente al 
     * insertar un cliente en la base de datos
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param ciudad
     * @param categoria 
     */
    public Cliente(String nombre, String apellido1, String apellido2, String ciudad, int categoria) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.ciudad = ciudad;
        this.categoria = categoria;
    }

    /**
     * constructor con cuatro argumentos, cuando el apellido2 y la categoría sean nulos
     * @param id
     * @param nombre
     * @param apellido1
     * @param ciudad 
     */
    public Cliente(int id, String nombre, String apellido1, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.ciudad = ciudad;
    }

    /**
     * constructor similar al anterior pero sin el id
     * @param nombre
     * @param apellido1
     * @param ciudad 
     */
    public Cliente(String nombre, String apellido1, String ciudad) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.ciudad = ciudad;
    }

    /**
     * consructor sin el argumento categoria
     * @param id
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param ciudad 
     */
    public Cliente(int id, String nombre, String apellido1, String apellido2, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.ciudad = ciudad;
    }

    /**
     * constructor con 3 argumentos
     * @param id
     * @param nombre
     * @param apellido1 
     */
    public Cliente(int id, String nombre, String apellido1) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }
    
    /**
     * constructor con dos argumentos
     * @param nombre 
     */
    public Cliente(String nombre, String apellido1) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }

    /**
     * constructor con todos los argumentos menos apellido2
     * @param id
     * @param nombre
     * @param apellido1
     * @param ciudad
     * @param categoria 
     */
    public Cliente(int id, String nombre, String apellido1, String ciudad, int categoria) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.ciudad = ciudad;
        this.categoria = categoria;
    }

    /**
     * constructor con todos los argumentos menos id y apellido2
     * la base de datos
     * @param nombre
     * @param apellido1
     * @param ciudad
     * @param categoria 
     */
    public Cliente(String nombre, String apellido1, String ciudad, int categoria) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.ciudad = ciudad;
        this.categoria = categoria;
    }

    //declaramos setters and getters
    public int getId() {
        return id;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * método que representa en formato de cadena el objeto Cliente
     * @return 
     */
    @Override
    public String toString() {
        return "Cliente: " + "id = " + id + ", nombre = " + nombre + ", apellido1 = " + apellido1 
            + ", apellido2 = " + apellido2 + ", ciudad = " + ciudad + ", categoria = " + categoria;
    }
}
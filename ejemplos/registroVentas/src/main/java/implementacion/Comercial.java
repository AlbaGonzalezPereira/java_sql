package implementacion;

/**
 *Clase que nos permite crear comerciales y que representa una entidad de la tabla "comercial" 
 * en la base de datos ventas
 * @author alba_
 */
public class Comercial {
    //declaramos los atributos, cada uno corresponde a una columna en la tabla "comercial" de la base de datos:
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;

    //declaramos diferentes constructores ya que se permiten campos nulos
    /**
     * constructor con todos los argumentos
     * @param id
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param comision 
     */
    public Comercial(int id, String nombre, String apellido1, String apellido2, float comision) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.comision = comision;
    }

    /**
     * constructor con todos los argumentos menos el id, que se genera de manera automática
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param comision 
     */
    public Comercial(String nombre, String apellido1, String apellido2, float comision) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.comision = comision;
    }

    /**
     * constructor con los argumentos no nulos
     * @param id
     * @param nombre
     * @param apellido1 
     */
    public Comercial(int id, String nombre, String apellido1) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }
    
    /**
     * constructor con 4 argumentos
     * @param id
     * @param nombre
     * @param apellido1
     * @param apellido2 
     */
    public Comercial(int id, String nombre, String apellido1, String apellido2) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }
    
    //declaramos los getter y setter
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

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    /**
     * método que representa en formato de cadena el objeto Comercial
     * @return 
     */
    @Override
    public String toString() {
        return "Comercial: " + "id = " + id + ", nombre = " + nombre + ", apellido1 = " + apellido1
            + ", apellido2 = " + apellido2 + ", comision = " + comision;
    }
}


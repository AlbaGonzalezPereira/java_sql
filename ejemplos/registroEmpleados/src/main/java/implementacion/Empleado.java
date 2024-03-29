package implementacion;

/**
 *
 * @author alba_
 */
public class Empleado {
    private int codigo;
    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int codigoDepartamento;  

    public Empleado(int codigo, String nif, String nombre, String apellido1, String apellido2, int codigoDepartamento) {
        this.codigo = codigo;
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.codigoDepartamento = codigoDepartamento;
    }

    public Empleado(int codigo, String nif, String nombre, String apellido1, String apellido2) {
        this.codigo = codigo;
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Empleado(String nif, String nombre, String apellido1, String apellido2, int codigoDepartamento) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.codigoDepartamento = codigoDepartamento;
    }
    
    public Empleado(int codigo, String nif, String nombre, String apellido1) {
        this.codigo = codigo;
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    @Override
    public String toString() {
        return "Empleado: " + "codigo=" + codigo + ", nif=" + nif + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", codigoDepartamento=" + codigoDepartamento;
    }
    
}

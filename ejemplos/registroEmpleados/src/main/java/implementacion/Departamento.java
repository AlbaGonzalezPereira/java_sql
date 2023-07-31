package implementacion;

/**
 *
 * @author alba_
 */
public class Departamento {
    private int codigo;
    private String nombre;
    private double presupuesto;
    private double gastos;

    public Departamento(int codigo, String nombre, double presupuesto, double gastos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.gastos = gastos;
    }

    public Departamento(String nombre, double presupuesto, double gastos) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.gastos = gastos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    @Override
    public String toString() {
        return "Departamento: " + "codigo=" + codigo + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", gastos=" + gastos;
    }
    
}

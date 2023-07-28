package implementacion;

import java.time.LocalDate;

/**
 *Clase Peido que nos permite crear pedidos, representa una entidad de la tabla "pedido" 
 * en la base de datos ventas
 * @author alba_
 */
public class Pedido {
     //declaramos los atributos, cada uno corresponde a una columna en la tabla "pedido" de la base de datos:
    public int id;
    public double total;
    public LocalDate fecha;
    public int id_cliente;
    public int id_comercial;
    
    //declaramos diferentes constructores
    public Pedido (double total, LocalDate fecha, int id_cliente, int id_comercial) {
        this.total=total;
        this.fecha=fecha;
        this.id_cliente=id_cliente;
        this.id_cliente=id_comercial;
    }

    public Pedido(int id, double total, LocalDate fecha, int id_cliente, int id_comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_comercial = id_comercial;
    }

    public Pedido(double total, int id_cliente, int id_comercial) {
        this.total = total;
        this.id_cliente = id_cliente;
        this.id_comercial = id_comercial;
    }
    
    //añadimos getter y setter
    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_comercial() {
        return id_comercial;
    }

    public void setId_comercial(int id_comercial) {
        this.id_comercial = id_comercial;
    }

    /**
     * método que representa en formato de cadena el objeto Pedido
     * @return 
     */
    @Override
    public String toString() {
        return "Pedido: " + "id=" + id + ", total=" + total + ", fecha=" + fecha 
            + ", id_cliente=" + id_cliente + ", id_comercial=" + id_comercial;
    }
 
    
} //Cierra clase Pedido

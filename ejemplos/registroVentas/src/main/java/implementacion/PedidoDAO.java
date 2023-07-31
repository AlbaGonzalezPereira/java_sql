package implementacion;

import DB.DB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *clase que contiene los métodos para realizar consultas, insercciones, modificaciones, etc
 * en la tabla pedido de la bbdd ventas
 * @author alba_
 */
public class PedidoDAO {

    /**
     * método que inserta un pedido en la tabla pedido de base de datos ventas
     * @param pedido
     * @return - número de elementos insertados
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static int insertarPedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO pedido(total, fecha, id_cliente, id_comercial) VALUES (?, ?, ?, ?);";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setDouble(1, pedido.getTotal());
            String fechaFormateada = pedido.getFecha().toString();
            stmt.setString(2, fechaFormateada);
            stmt.setInt(3, pedido.getId_cliente());
            stmt.setInt(4, pedido.getId_comercial());
            return stmt.executeUpdate(); // Ejecuta el insert y retorna el número de filas afectadas
        } catch (SQLException ex) {
            return -1; // Si ocurre un error
        }
    }

    /**
     * método que inserta un pedido sin fecha
     * @param pedido
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static int insertarPedidoSF(Pedido pedido) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO pedido(total,id_cliente, id_comercial) VALUES (?, ?, ?);";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setDouble(1, pedido.getTotal());
            stmt.setInt(2, pedido.getId_cliente());
            stmt.setInt(3, pedido.getId_comercial());
            return stmt.executeUpdate(); // Ejecuta el insert y retorna el número de filas afectadas
        } catch (SQLException ex) {
            return -1; // Si ocurre un error
        }
    }

    /**
     * método que actualiza un pedido
     * @param pedido
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int updatePedido(Pedido pedido) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Pedido SET total=?, fecha=?, id_cliente=?, id_comercial=? WHERE id=?;";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setDouble(1, pedido.getTotal());
            java.sql.Date date = new java.sql.Date(0000-00-00);
            // Convertir LocalDate a java.sql.Date utilizando la clase Date de java.sql
            String fechaFormateada = pedido.getFecha().toString();
            stmt.setString(2, fechaFormateada);
//            stmt.setDate(2, date.valueOf(pedido.getFecha()));
            stmt.setInt(3, pedido.getId_cliente());
            stmt.setInt(4, pedido.getId_comercial());
            stmt.setInt(5, pedido.getId());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }
   
    /**
     * método que devuelve todos los pedidos ordenados por id_cliente
     * @return
     * @throws Exception 
     */
    public static Pedido[] cargarPedido() throws Exception {
    String sql = "SELECT * FROM Pedido ORDER BY id_cliente;";
    Pedido p = null;
    ArrayList<Pedido> pedidos = new ArrayList<>();

    try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            int id = resultado.getInt("id");
            double total = resultado.getDouble("total");
            LocalDate fecha = null;
            Date fechaBD = resultado.getDate("fecha");
            if (fechaBD != null) {
                fecha = fechaBD.toLocalDate();
            }
            int id_cliente = resultado.getInt("id_cliente");
            int id_comercial = resultado.getInt("id_comercial");

            p = new Pedido(id, total, fecha, id_cliente, id_comercial);
            pedidos.add(p);
        }
    }
    return pedidos.toArray(new Pedido[0]);
}


    /**
     * método que selecciona un pedido según el id de un cliente dado
     * @param idCli
     * @return
     * @throws ClassNotFoundException 
     */
    public static ArrayList<String> clientePedido(int idCli) throws ClassNotFoundException {
        String sql = "SELECT total,fecha,id_comercial FROM Pedido WHERE id_cliente=?;";
        ArrayList<String> lista = new ArrayList<>(); //Declaramos lista a devolver

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, idCli);
            try ( ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    lista.add(rs.getDouble(1) + " " + rs.getDate(2).toLocalDate()
                            + " " + rs.getInt(3));
                }
            }
        } catch (SQLException ex) {
            return null;
        }
        return lista;
    } 

    /**
     * método que elimina un pedido según un id dado
     * @param id
     * @return
     * @throws ClassNotFoundException 
     */
    public static int eliminarPedido(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM pedido WHERE id=?";
        int numEliminados = 0;
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            numEliminados = stmt.executeUpdate();
            return numEliminados;
        } catch (SQLException ex) {
            System.out.println("No se ha podido eliminar el pedido");
            return -1;
        }
    } 

    /**
     * método que busca un pedido según un id dado
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Pedido buscarPedido(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Pedido WHERE id=?";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                java.sql.Date fechaSQL = rs.getDate("fecha");
                LocalDate fecha = fechaSQL.toLocalDate();
                //LocalDate fecha = (LocalDate) rs.getObject("fecha");
                int id_cliente = rs.getInt("id_cliente");
                int id_comercial = rs.getInt("id_comercial");
                return new Pedido(id, total, fecha, id_cliente, id_comercial);
            }
        }
        return null;
    }

}//fin class

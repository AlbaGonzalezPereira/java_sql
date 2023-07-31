package implementacion;

import DB.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *clase que contiene los métodos para realizar consultas, insercciones, modificaciones, etc
 * en la tabla cliente de la bbdd ventas
 * @author alba_
 */
public class ClienteDAO {
    
    /**
     * método que nos permite insertar un cliente
     * @param cliente
     * @return - devuelve el número de clientes insertados  o -1 en caso de error
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int insertarCliente(Cliente cliente) throws SQLException, 
            ClassNotFoundException {
        //Declaramos consulta 
        String sql = "INSERT INTO Cliente (nombre, apellido1, apellido2, "
            + "ciudad, categoria) VALUES (?,?,?,?,?);";
        //Declaramos conexion e a clase para modificar os valores
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            //agregamos los valores
            stmt.setString(1, cliente.getNombre()); //Primer value
            stmt.setString(2, cliente.getApellido1()); //Segundo value
            stmt.setString(3, cliente.getApellido2());
            stmt.setString(4, cliente.getCiudad());
            stmt.setInt(5, cliente.getCategoria()); //Último value
            return stmt.executeUpdate();
        }catch(SQLException ex){
            return -1;
        }//return 1;
    }
   
    /**
     * método que nos permite actualizar un cliente
     * @param cliente
     * @return - devuelve el número de clientes actualizados o -1 en caso de error
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int updateCliente(Cliente cliente) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Cliente SET nombre=?, apellido1=?, apellido2=?, "
                + "ciudad=?, categoria=? WHERE id=?;";

        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido1());
            stmt.setString(3, cliente.getApellido2());
            stmt.setString(4, cliente.getCiudad());
            stmt.setInt(5, cliente.getCategoria());
            stmt.setInt(6, cliente.getId());
            return stmt.executeUpdate();
        }catch(SQLException ex){
            return -1;
        }//return 1;//si no hacemos el return stmt.executeUpdate();
    }
    
    /**
     * método que nos permite cargar todos los cliente que hay
     * @return - devuelve un array de clientes 
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Cliente[] cargarCliente() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Cliente"; //Seria la cosulta que quieras hacer en la BBDD 
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cl = null;
        
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);){
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    cl = new Cliente(rs.getString("nombre"),rs.getString("apellido1"),
                    rs.getString("apellido2"), rs.getString("ciudad"), rs.getInt("categoria"));
                    listaClientes.add(cl);
            }
        }
        return listaClientes.toArray(new Cliente[0]);
    }
    
    /**
     * método que nos permite eliminar un cliente según su id
     * @param id - id del cliente
     * @return - devuelve el número de clientes eliminados
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static int eliminarCliente(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Cliente WHERE id=?";
        
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }

    /**
     * método que busca un cliente por su id
     * @param id - id del cliente
     * @return - devuelve el Cliente a buscar o null en caso de no encontrarlo
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public static Cliente buscarCliente(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Cliente WHERE id=?";
        Cliente cl=null;
        
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                return new Cliente(id,nombre,apellido1,apellido2);
            }
        }
        return null;        
    }
}

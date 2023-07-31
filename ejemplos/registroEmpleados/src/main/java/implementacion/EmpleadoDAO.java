package implementacion;

import DB.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alba_
 */
public class EmpleadoDAO {
    public static int insertarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO Empleado(nif, nombre, apellido1, apellido2, codigo_departamento) VALUES (?,?,?,?,?);";
        try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, empleado.getNif());
            stmt.setString(2, empleado.getNombre());    
            stmt.setString(3, empleado.getApellido1());
            stmt.setString(4, empleado.getApellido2());
            stmt.setInt(5, empleado.getCodigoDepartamento());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            return -1;
        }
        return 1;
    }

    public static Empleado recuperarEmpleado(String nif) throws SQLException {
        String sql = "SELECT * FROM empleado WHERE nif=?;";
        Empleado e = null;
        try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nif);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                e = new Empleado(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getString(4), resultado.getString(5), resultado.getInt(6));
            }
        }
        return e;
    }

    public static int modificarEmpleado(String ape2, int cd, String nif) {
        String sql = "UPDATE empleado SET apellido2=?, codigoDepartamento=? WHERE nif=?;";
        int numMod;
        try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, ape2);
            stmt.setInt(4, cd);
            stmt.setString(5, nif);
            numMod = stmt.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }

        if (numMod > 0) {
            return 1;//Si se modifico dato
        }
        return 0;//No se modifico dato
    }

    public static Empleado[] listarEmpleados() throws SQLException {
        String sql = "SELECT * FROM Empleado";
        Empleado e = null;
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                e = new Empleado(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getString(4), resultado.getString(5), resultado.getInt(6));
                empleados.add(e);
            }   
        }
        return empleados.toArray(new Empleado[0]);
    }
    
    public static int updateEmpleado(Empleado empleado) {
        String sql = "UPDATE Empleado SET nombre=?, apellido1=?, apellido2=?, codigo_departamento=? WHERE codigo=?";
        try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido1());
            stmt.setString(3, empleado.getApellido2());
            stmt.setInt(4, empleado.getCodigoDepartamento());
            stmt.setInt(5, empleado.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            return -1;
        }
    }
    
    public static int eliminarEmpleado(String dni){
       String sql="DELETE FROM empleado WHERE nif=?";
       try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, dni);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            return -1;  
        }  
    }
  
     public static Empleado buscarEmpleado(int codigo) throws SQLException {
        String sql = "SELECT * FROM Empleado WHERE codigo = ?";
        Connection con = DB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                String nif = rs.getString("nif");
                String apellido1 = rs.getString("apellido1");
                String apellido2  = rs.getString("apellido2");
                int cd = rs.getInt("codigo_departamento");
                return new Empleado(codigo, nif, nombre, apellido1, apellido2,cd);
            }
        }
        return null; // Retorna null si no se encuentra un departamento con el código especificado
    }
     
     public static Empleado buscarEmpleadoNif(String nif) throws SQLException {
        String sql = "SELECT * FROM Empleado WHERE nif = ?";
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nif);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int codigo = rs.getInt("codigo");
                nif = rs.getString("nif");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2  = rs.getString("apellido2");
                 int cd = rs.getInt("codigo_departamento");
                return new Empleado(codigo, nif, nombre, apellido1, apellido2,cd);
            }
        }
        return null; // Retorna null si no se encuentra un departamento con el código especificado
    }
}
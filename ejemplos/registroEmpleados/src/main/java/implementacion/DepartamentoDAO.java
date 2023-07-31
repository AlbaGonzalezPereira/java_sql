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
public class DepartamentoDAO {

    public static int insertarDepartamento(Departamento departamento) throws SQLException {
        String sql = "INSERT INTO Departamento(nombre,presupuesto,gastos) VALUES(?,?,?)";     
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, departamento.getNombre());
            stmt.setDouble(2, departamento.getPresupuesto());
            stmt.setDouble(3, departamento.getGastos());
            stmt.executeUpdate();
        }catch (SQLException ex) {
            return -1;
        }
        return 1;
    }
    
     public static int updateDepartamento(Departamento departamento) throws SQLException {
        String sql = "UPDATE Departamento SET nombre=?, presupuesto=?, gastos=? WHERE codigo=?";
            try (Connection con = DB.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, departamento.getNombre());
                stmt.setDouble(2, departamento.getPresupuesto());
                stmt.setDouble(3, departamento.getGastos());
                stmt.setInt(4, departamento.getCodigo());
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                return -1;
            }    
    }
     
    public static int eliminarDepartamento(int codigo) throws SQLException {
        String sql = "DELETE FROM Departamento WHERE codigo=?";    
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, codigo);
            return stmt.executeUpdate();
        }
    }

    public static ArrayList<Departamento> cargarDepartamentos() throws SQLException {
        String sql = "SELECT * FROM Departamento";
        ArrayList<Departamento> lista = new ArrayList<>();
        Departamento dept = null; 
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dept = new Departamento(rs.getString(1), rs.getDouble(2), rs.getDouble(3));
                lista.add(dept);
            }
        }
        return lista;
    }
  
    public static Departamento buscarDepartamento(int codigo) throws SQLException {
        String sql = "SELECT * FROM Departamento WHERE codigo = ?";   
        try (Connection con = DB.getConnection();PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                double presupuesto = rs.getDouble("presupuesto");
                double gastos = rs.getDouble("gastos");
                return new Departamento(codigo, nombre, presupuesto, gastos);
            }
        }
        return null; // Retorna null si no se encuentra un departamento con el código especificado
    }
}


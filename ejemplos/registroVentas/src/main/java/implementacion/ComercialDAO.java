package implementacion;

import DB.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * clase que contiene los métodos para realizar consultas, insercciones,
 * modificaciones, etc en la tabla comercial de la bbdd ventas
 *
 * @author alba_
 */
public class ComercialDAO {

    /**
     * método que inserta un comercial en la base de datos
     *
     * @param comercial
     * @return - número de inserciones
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int insertarComercial(Comercial comercial) throws SQLException, ClassNotFoundException {
        //Declaramos consulta 
        String sql = "INSERT INTO Comercial (nombre, apellido1, apellido2, "
                + "comision) VALUES (?, ?, ?, ?);";
        //Declaramos conexion e a clase para modificar os valores
        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            //agregamos los valores
            stmt.setString(1, comercial.getNombre());
            stmt.setString(2, comercial.getApellido1());
            stmt.setString(3, comercial.getApellido2());
            stmt.setFloat(4, comercial.getComision());
            return stmt.executeUpdate();
        }
    }

    /**
     * método que actualiza un comercial
     *
     * @param comercial
     * @return - número de elementos actualizados
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int updateComercial(Comercial comercial) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Comercial SET nombre=?, apellido1=?, apellido2=?, "
                + "comision=? WHERE id=?;";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setString(1, comercial.getNombre());
            stmt.setString(2, comercial.getApellido1());
            stmt.setString(3, comercial.getApellido2());
            stmt.setFloat(4, comercial.getComision());
            stmt.setInt(5, comercial.getId());
            return stmt.executeUpdate();
        }
    }

    /**
     * método que carga todos los comerciales
     *
     * @return - devuelve un array de Comerciales
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Comercial[] cargarComercial() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Comercial"; //Seria la cosulta que quieras hacer en la BBDD 
        ArrayList<Comercial> comerciantes = new ArrayList<>();
        Comercial co = null;

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                co = new Comercial(r.getString("nombre"), r.getString("apellido1"),
                        r.getString("apellido2"), r.getFloat("comision"));
                comerciantes.add(co);
            }
        }
        return comerciantes.toArray(new Comercial[0]);
    }

    /**
     * método que elimina un comercial
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int eliminarComercial(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Comercial WHERE id=?";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }

    /**
     * método que busca un comercial por id
     *
     * @param id
     * @return - devuelve un comercial o null en caso de no encontrarlo
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Comercial buscarComercial(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Comercial WHERE id=?";

        try ( Connection con = DB.getConnection();  PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                return new Comercial(id, nombre, apellido1, apellido2);
            }
        }
        return null;
    }
}

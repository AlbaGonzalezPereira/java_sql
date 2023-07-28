package DB;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * clase que se utiliza para establecer y gestionar la conexión a la base de datos MariaDB llamada ventas
 * @author alba_
 */
public class DB implements Closeable {

    private static DB db = null;
    public static String DRIVER = "org.mariadb.jdbc.Driver";
    public static String URL = "jdbc:mariadb://localhost/ventas?allowPublicKeyRetrieval=true&useSSL=false;";
    public static String USER = "root";
    public static String PASS = "";

    private Connection con = null;

    /**
     * método que nos permite obtener una conexión a la base de datos
     * @return
     * @throws SQLException 
     */
    public static DB open() throws SQLException {
        if (DB.db == null) {
            DB.db = new DB();
        }
        return DB.db;
    }

    /**
     * método que obtiene y devolver la conexión a la base de datos
     * @return
     * @throws ClassNotFoundException 
     */
    public static Connection getConnection() throws ClassNotFoundException {
        if (DB.db == null) {
            throw new RuntimeException("Debes abrir antes la conexión con open");
        }
        try {
            if (DB.db.con == null || DB.db.con.isClosed()) {
                DB.db.connect();
            }
            return DB.db.con;
        } catch (RuntimeException | SQLException ex) {
            throw new RuntimeException("Error obteniendo conexión con la BBDD: " + ex.getMessage());
        }
    }

    /**
     * constructor privado que se utiliza para crear una instancia de DB y establecer automáticamente la conexión
     * @throws SQLException 
     */
    private DB() throws SQLException {
        connect();//establecemos la conexión
    }

    /**
     * método que realiza la conexión a la base de datos con los valores de URL, USER y PASS
     * @throws SQLException 
     */
    private final void connect() throws SQLException {
        con = DriverManager.getConnection(DB.URL, DB.USER, DB.PASS);
    }

    /**
     * método que cierra la conexión
     * @throws IOException 
     */
    public void close() throws IOException {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            throw new IOException(ex);
        }
    }
}

## **CONECTARSE A UNA BASE DE DATOS CON JAVA CON WINDOWS**

### **JDBC Driver para SQL Server**

Microsoft proporciona un controlador **Java Database Connectivity** (JDBC) para SQL Server, Azure SQL Database y Azure SQL Managed Instance.

El controlador proporciona **conectividad de base de datos** de Java desde cualquier aplicación de Java, servidor de aplicaciones o applet habilitado para Java, mediante las interfaces de programación de aplicaciones (API) estándar JDBC.

#### **Introducción**
* Paso 1: Configurar el entorno de desarrollo para el desarrollo de Java.
* Paso 2: Crear una base de datos SQL.
* paso 3: Creación del proyecto Java dependencias.
* Paso 4: Prueba de concepto de la conexión a SQL mediante Java.

## **Paso 1: Configuración del entorno de desarrollo para el desarrollo de Java**

* Tenemos que identificar qué versión del controlador JDBC usará en función de su entorno.
* Después, descargaremos e instalaremos el controlador JDBC.
* Establecemos la ruta de acceso de clase en función de la versión del controlador.

## **Paso 2: Creación de una base de datos SQL**
```sql
CREATE TABLE alumno (

    id_alumno MEDIUMINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    email VARCHAR(320),
    PRIMARY KEY (id_alumno)
);

INSERT INTO alumno (nombre, apellidos, edad, email)
VALUES  ('María', 'López Martínez', 18, NULL),
        ('José', 'García González', 23, "josexyz@gmail.com"),
        ('Ana', "Del Campo Rodríguez", 19, "anukyfield@gmail.com"),
        ('Martín', "Suárez Trevejo", 24, NULL);
```

## Paso 3: Creación del proyecto java y dependencias
- Creamos un proyecto java Maven
- en el archivo ``pom.xml`` que está dentro de la carpeta Projects files incluimos las dependencias de mariaDB:
```xml
<dependencies> <!--Insertamos as dependencias, que será o driver-->
    <dependency>
        <!-- https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client -->
        <groupId>org.mariadb.jdbc</groupId> <!--cando compilemos descargarase o driver-->
        <artifactId>mariadb-java-client</artifactId>
        <version>3.1.3</version>
    </dependency>
    </dependencies>
``` 
## Paso 4: Prueba de concepto de la conexión a SQL mediante Java
El siguiente ejemplo está simplificado para que se entienda el código.

**PASO 1: CONECTAR**
Creamos la clase de conexión para conectarse a SQL Database.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PrincipalConexion {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String conUrl =
                "jdbc:mariadb://localhost/alumnos?allowPublicKeyRetrieval=true&useSSL=false");
                        + "database=alumnos;"
                        + "user=root;"
                        + "password="";"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try (Connection connection = DriverManager.getConnection(conUrl);) {
            // Code here.
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

**PASO 2: EJECUTAR UNA CONSULTA**

En este ejemplo, se ejecuta una instrucción SELECT y se devuelven las filas seleccionadas.
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PrincipalConexion {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String conUrl =
                "jdbc:mariadb://localhost/alumnos?allowPublicKeyRetrieval=true&useSSL=false");
                        + "database=alumnos;"
                        + "user=root;"
                        + "password="";"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(conUrl);
                Statement stmt = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String sql = "SELECT nombre, apellidos FROM alumno WHERE edad > 20";
            resultSet = stmt.executeQuery(sql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

**PASO 3: INSERTAR DATOS**

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PrincipalConexion {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String conUrl =
                "jdbc:mariadb://localhost/alumnos?allowPublicKeyRetrieval=true&useSSL=false");
                        + "database=alumnos;"
                        + "user=root;"
                        + "password="";"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        
        ResultSet resultSet = null;

        String sql = "INSERT INTO alumno nombre, apellidos, edad, email) VALUES ('Pepe', 'Cobo Dios', 50, pepeCobo@gmail.com');";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(conUrl);
                PreparedStatement prepsInsert = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsert.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsert.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
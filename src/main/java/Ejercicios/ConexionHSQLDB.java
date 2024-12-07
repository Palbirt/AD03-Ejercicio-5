package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConexionHSQLDB {

	public static void main(String[] args) {
		
		// Configurar la URL de la base de datos en fichero
        String url = "jdbc:hsqldb:file:./testdb"; // Ruta al fichero de la base de datos
        String user = "SA";  // Usuario por defecto en HSQLDB
        String password = "";  // Contraseña vacía por defecto

        // Usamos try-with-resources para manejar automáticamente la conexión
        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Verificar si la conexión es válida
            if (connection.isValid(0)) {
                System.out.println("Conexión válida a la base de datos HSQLDB.");
            } else {
                System.out.println("La conexión no es válida.");
            }

            // Crear una tabla de ejemplo para asegurarnos de que la conexión funciona
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS prueba (id INT PRIMARY KEY, nombre VARCHAR(50))");
                //stmt.executeUpdate("INSERT INTO prueba (id, nombre) VALUES (1, 'Ejemplo')");
                
                // Consultar los datos insertados
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM prueba")) {
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}

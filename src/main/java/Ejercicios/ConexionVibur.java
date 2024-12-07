package Ejercicios;

import org.vibur.dbcp.ViburDBCPDataSource;
import java.sql.Connection;

public class ConexionVibur {

	public static void main(String[] args) {
		
		// Configuración del pool de conexiones Vibur
        ViburDBCPDataSource dataSource = new ViburDBCPDataSource();

        try {
            // Configurar las propiedades de conexión
            dataSource.setJdbcUrl("jdbc:hsqldb:file:./testdb"); // Base de datos en fichero
            dataSource.setUsername("SA"); // Usuario por defecto de HSQLDB
            dataSource.setPassword("");  // Contraseña por defecto es vacía
            dataSource.setPoolInitialSize(1); // Número inicial de conexiones en el pool
            dataSource.setPoolMaxSize(5);    // Número máximo de conexiones en el pool
            dataSource.start(); // Inicializar el pool de conexiones

            // Probar la conexión
            try (Connection connection = dataSource.getConnection()) {
                if (connection.isValid(0)) {
                    System.out.println("Vibur: connection.isValid(0) = " + connection.isValid(0));
                } else {
                    System.out.println("La conexión a la base de datos no es válida.");
                }
            } 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el pool de conexiones
            if ( dataSource != null ) dataSource.terminate();
        }

	}

}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_inventario";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";

    public Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion exitosa a MySQL.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado.");
        } catch (SQLException e) {
            System.out.println("Error de BD: " + e.getMessage());
        }
        return conexion;
    }

    public void desconectar(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }
}

package principal;

import java.sql.Connection;

import controlador.ControladorPrincipal;
import dao.Conexion; // Importamos tu clase de conexión
import vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
        
        // --- 1. PRUEBA DE BASE DE DATOS ---
        System.out.println("Iniciando prueba de conexión con MySQL...");
        Conexion con = new Conexion();
        Connection conexionActiva = con.conectar();
        
        if (conexionActiva != null) {
            // Si la conexión fue exitosa, la cerramos por ahora para no dejarla colgada
            con.desconectar(conexionActiva);
        }
        System.out.println("----------------------------------------");
        
        // --- 2. INICIO DE LA INTERFAZ GRÁFICA ---
        try {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ControladorPrincipal controlador = new ControladorPrincipal(ventana);
            ventana.setVisible(true);
            System.out.println("Interfaz gráfica iniciada con éxito.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al iniciar la interfaz: " + e.getMessage());
        }
    }
}
package principal;

import java.sql.Connection;

import controlador.ControladorPrincipal;
import dao.Conexion;
import vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando prueba de conexion con MySQL...");
        Conexion con = new Conexion();
        Connection conexionActiva = con.conectar();

        if (conexionActiva != null) {
            con.desconectar(conexionActiva);
        }
        System.out.println("----------------------------------------");

        try {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ControladorPrincipal controlador = new ControladorPrincipal(ventana);
            ventana.setVisible(true);
            System.out.println("Interfaz grafica iniciada con exito.");
        } catch (Exception e) {
            System.out.println("Ocurrio un error al iniciar la interfaz: " + e.getMessage());
        }
    }
}

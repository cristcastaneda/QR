package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatosInicialesDAO {

    public void crearDatosSiHaceFalta() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            if (conexion == null || tieneDatos(conexion, "PRODUCTO")) {
                return;
            }
            conexion.setAutoCommit(false);
            insertarClientes(conexion);
            insertarEmpleados(conexion);
            insertarProveedores(conexion);
            insertarProductos(conexion);
            insertarMaquinas(conexion);
            conexion.commit();
        } catch (SQLException e) {
            try { if (conexion != null) conexion.rollback(); } catch (SQLException ex) { System.out.println("Error al revertir datos iniciales: " + ex); }
            System.out.println("Error al crear datos iniciales: " + e);
        } finally {
            try { if (conexion != null) conexion.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error al restaurar autocommit: " + e); }
            con.desconectar(conexion);
        }
    }

    private boolean tieneDatos(Connection conexion, String tabla) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM " + tabla;
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() && rs.getInt("total") > 0;
        }
    }

    private void insertarClientes(Connection conexion) throws SQLException {
        ejecutar(conexion, "INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?)",
            "101", "Distribuciones La Sabana S.A.S.", "Calle 80 # 68-45, Bogota", "901245778-3", "Mariana Duarte", "601 482 1930");
        ejecutar(conexion, "INSERT INTO CLIENTE VALUES (?, ?, ?, ?, ?, ?)",
            "102", "Cafe Andino del Valle", "Carrera 5 # 12-40, Armenia", "900732114-8", "Jorge Salcedo", "606 741 2208");
    }

    private void insertarEmpleados(Connection conexion) throws SQLException {
        ejecutar(conexion, "INSERT INTO EMPLEADO VALUES (?, ?, ?, ?, ?)",
            "201", "Laura Martinez Rojas", "Empleado de bodega", "310 584 9021", Date.valueOf(LocalDate.of(2024, 2, 12)));
        ejecutar(conexion, "INSERT INTO EMPLEADO VALUES (?, ?, ?, ?, ?)",
            "202", "Andres Felipe Mora", "Operador comercial", "317 442 8806", Date.valueOf(LocalDate.of(2023, 9, 4)));
    }

    private void insertarProveedores(Connection conexion) throws SQLException {
        ejecutar(conexion, "INSERT INTO PROVEEDOR VALUES (?, ?, ?, ?, ?, ?)",
            "301", "Comercializadora Nutresa S.A.S.", "890903628-9", "604 335 1902", "Carrera 52 # 2-38, Medellin", "Claudia Restrepo");
        ejecutar(conexion, "INSERT INTO PROVEEDOR VALUES (?, ?, ?, ?, ?, ?)",
            "302", "Super de Alimentos S.A.S.", "890680065-2", "606 887 9620", "Km 10 Via Magdalena, Manizales", "Hector Molina");
    }

    private void insertarProductos(Connection conexion) throws SQLException {
        Object[][] productos = {
            {"401", "Chocoramo", "Ponques", 2500.00, "Unidad"},
            {"402", "Papas Margarita Limon 39g", "Snacks salados", 2200.00, "Unidad"},
            {"403", "Galletas Festival Fresa", "Galletas", 1600.00, "Unidad"},
            {"404", "Chocolatina Jet 12g", "Chocolates", 1000.00, "Unidad"},
            {"405", "Todo Rico Original 45g", "Snacks salados", 2500.00, "Unidad"},
            {"406", "Gomitas Trululu Casquitos", "Dulces", 2000.00, "Unidad"},
            {"407", "Mani Moto Original", "Snacks", 1800.00, "Unidad"},
            {"408", "Barquillos Piazza Chocolate", "Galletas", 1800.00, "Unidad"},
            {"409", "Nucita Doble Sabor", "Dulces", 1200.00, "Unidad"},
            {"4010", "Barrilete", "Dulces", 800.00, "Unidad"}
        };
        for (Object[] producto : productos) {
            ejecutar(conexion, "INSERT INTO PRODUCTO VALUES (?, ?, ?, ?, ?)", producto);
        }
    }

    private void insertarMaquinas(Connection conexion) throws SQLException {
        ejecutar(conexion, "INSERT INTO MAQUINA VALUES (?, ?, ?, ?, ?, ?)",
            "501", "SN-BG-24018", "Bodega principal Bogota", "Activo", Date.valueOf(LocalDate.of(2025, 1, 20)), "101");
        ejecutar(conexion, "INSERT INTO MAQUINA VALUES (?, ?, ?, ?, ?, ?)",
            "502", "SN-QD-24044", "Planta Armenia linea 2", "Activo", Date.valueOf(LocalDate.of(2025, 3, 7)), "102");
    }

    private void ejecutar(Connection conexion, String sql, Object... valores) throws SQLException {
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (int i = 0; i < valores.length; i++) {
                ps.setObject(i + 1, valores[i]);
            }
            ps.executeUpdate();
        }
    }
}

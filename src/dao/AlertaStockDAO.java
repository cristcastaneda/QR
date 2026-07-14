package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.AlertaStock;

public class AlertaStockDAO {

    public List<AlertaStock> obtenerAlertas() {
        List<AlertaStock> alertas = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT ib.cod_prod, p.nom_prod, 'Bodega' AS ubicacion, ib.cant_disp AS cantidad_actual, 100 AS stock_min "
                + "FROM INVENTARIO_BODEGA ib INNER JOIN PRODUCTO p ON p.cod_prod = ib.cod_prod "
                + "WHERE ib.cant_disp < 100 ORDER BY ib.cod_prod";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                alertas.add(new AlertaStock(
                    rs.getString("cod_prod"),
                    rs.getString("nom_prod"),
                    rs.getString("ubicacion"),
                    rs.getInt("cantidad_actual"),
                    rs.getInt("stock_min")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar alertas de stock: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return alertas;
    }
}

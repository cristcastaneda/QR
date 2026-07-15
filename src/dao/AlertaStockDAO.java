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
        String sqlStock = "SELECT ib.cod_prod, p.nom_prod, 'Bodega' AS ubicacion, ib.cant_disp AS cantidad_actual, 100 AS stock_min "
                + "FROM INVENTARIO_BODEGA ib INNER JOIN PRODUCTO p ON p.cod_prod = ib.cod_prod "
                + "WHERE ib.cant_disp < 100 ORDER BY ib.cod_prod";
        String sqlVencimientos = "SELECT doc.cod_prod, p.nom_prod, CONCAT('Orden ', oc.cod_ord_com) AS ubicacion, "
                + "doc.cant_sol AS cantidad_actual, COALESCE(ib.stock_min, 100) AS stock_min, doc.fecha_venc_lote "
                + "FROM DETALLE_ORDEN_COMPRA doc "
                + "INNER JOIN ORDEN_COMPRA oc ON oc.cod_ord_com = doc.cod_ord_com "
                + "INNER JOIN PRODUCTO p ON p.cod_prod = doc.cod_prod "
                + "LEFT JOIN INVENTARIO_BODEGA ib ON ib.cod_prod = doc.cod_prod "
                + "WHERE oc.estado_ord_com = 'Completado' "
                + "AND doc.fecha_venc_lote BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, INTERVAL 1 MONTH) "
                + "ORDER BY doc.fecha_venc_lote, doc.cod_prod";

        try (PreparedStatement ps = conexion.prepareStatement(sqlStock);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                alertas.add(new AlertaStock(
                    "Stock bajo",
                    rs.getString("cod_prod"),
                    rs.getString("nom_prod"),
                    rs.getString("ubicacion"),
                    rs.getInt("cantidad_actual"),
                    rs.getInt("stock_min"),
                    null
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar alertas de stock: " + e);
        }

        try (PreparedStatement ps = conexion.prepareStatement(sqlVencimientos);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                alertas.add(new AlertaStock(
                    "Vence pronto",
                    rs.getString("cod_prod"),
                    rs.getString("nom_prod"),
                    rs.getString("ubicacion"),
                    rs.getInt("cantidad_actual"),
                    rs.getInt("stock_min"),
                    rs.getDate("fecha_venc_lote").toLocalDate()
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

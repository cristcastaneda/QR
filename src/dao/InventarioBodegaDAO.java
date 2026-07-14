package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import modelo.InventarioBodega;

public class InventarioBodegaDAO {

    public List<InventarioBodega> obtenerInventarios() {
        List<InventarioBodega> inventarios = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT cod_prod, cant_disp, 100 AS stock_min, fecha_ult_act FROM INVENTARIO_BODEGA ORDER BY cod_prod";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                InventarioBodega inv = new InventarioBodega();
                inv.setCodProd(rs.getString("cod_prod"));
                inv.setCantDisp(rs.getInt("cant_disp"));
                inv.setStockMin(rs.getInt("stock_min"));
                if (rs.getDate("fecha_ult_act") != null) {
                    inv.setFechaUltAct(rs.getDate("fecha_ult_act").toLocalDate());
                }
                inventarios.add(inv);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar inventario de bodega: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return inventarios;
    }
}

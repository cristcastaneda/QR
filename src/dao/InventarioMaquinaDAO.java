package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.InventarioMaquina;

public class InventarioMaquinaDAO {

    public List<InventarioMaquina> obtenerInventarios() {
        List<InventarioMaquina> inventarios = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT cod_maq, cod_prod, cant_act, 20 AS stock_min, fecha_ult_rec FROM INVENTARIO_MAQUINA ORDER BY cod_maq, cod_prod";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                InventarioMaquina inv = new InventarioMaquina();
                inv.setCodMaq(rs.getString("cod_maq"));
                inv.setCodProd(rs.getString("cod_prod"));
                inv.setCantAct(rs.getInt("cant_act"));
                inv.setStockMin(rs.getInt("stock_min"));
                if (rs.getDate("fecha_ult_rec") != null) {
                    inv.setFechaUltRec(rs.getDate("fecha_ult_rec").toLocalDate());
                }
                inventarios.add(inv);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar inventario de maquina: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return inventarios;
    }
}

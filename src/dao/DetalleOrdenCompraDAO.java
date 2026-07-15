package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleOrdenCompra;

public class DetalleOrdenCompraDAO {

    public boolean registrarDetalle(DetalleOrdenCompra detalle) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return registrarDetalle(conexion, detalle);
        } catch (SQLException e) {
            System.out.println("Error al registrar detalle de orden: " + e);
            return false;
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarDetalle(Connection conexion, DetalleOrdenCompra detalle) throws SQLException {
        String sql = "INSERT INTO DETALLE_ORDEN_COMPRA (cod_ord_com, cod_prod, fecha_venc_lote, cant_sol, precio_uni, sub_total) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, detalle.getCodOrdCom());
            ps.setString(2, detalle.getCodProd());
            ps.setDate(3, Date.valueOf(detalle.getFechaVencLote()));
            ps.setInt(4, detalle.getCantSol());
            ps.setDouble(5, detalle.getPrecioUni());
            ps.setDouble(6, detalle.getSubTotal());
            ps.execute();
            return true;
        }
    }

    public List<DetalleOrdenCompra> obtenerDetalles() {
        List<DetalleOrdenCompra> detalles = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM DETALLE_ORDEN_COMPRA ORDER BY cod_ord_com, cod_prod";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                detalles.add(new DetalleOrdenCompra(
                    rs.getString("cod_ord_com"),
                    rs.getString("cod_prod"),
                    rs.getDate("fecha_venc_lote").toLocalDate(),
                    rs.getInt("cant_sol"),
                    rs.getDouble("precio_uni"),
                    rs.getDouble("sub_total")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar detalles de orden: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return detalles;
    }
}

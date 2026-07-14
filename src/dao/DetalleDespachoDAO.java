package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleDespacho;

public class DetalleDespachoDAO {

    public boolean registrarDetalle(Connection conexion, DetalleDespacho detalle) throws SQLException {
        String sql = "INSERT INTO DETALLE_DESPACHO (cod_desp, cod_prod, cantidad_desp) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, detalle.getCodDesp());
            ps.setString(2, detalle.getCodProd());
            ps.setInt(3, detalle.getCantidadDesp());
            ps.execute();
            return true;
        }
    }

    public List<DetalleDespacho> obtenerDetalles() {
        List<DetalleDespacho> detalles = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM DETALLE_DESPACHO ORDER BY cod_desp, cod_prod";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                detalles.add(new DetalleDespacho(
                    rs.getString("cod_desp"),
                    rs.getString("cod_prod"),
                    rs.getInt("cantidad_desp")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar detalles de despacho: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return detalles;
    }
}

package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Despacho;
import modelo.DetalleDespacho;

public class DespachoDAO {

    public String obtenerSiguienteCodigo() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return CodigoUtil.siguienteCodigo(conexion, "DESPACHO", "cod_desp", "70");
        } catch (SQLException e) {
            System.out.println("Error al generar codigo de despacho: " + e);
            return "701";
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarDespachoConDetalles(Despacho despacho, List<DetalleDespacho> detalles) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();

        try {
            conexion.setAutoCommit(false);
            validarDisponibilidadBodega(conexion, detalles);
            registrarDespacho(conexion, despacho);
            DetalleDespachoDAO detalleDAO = new DetalleDespachoDAO();
            for (DetalleDespacho detalle : detalles) {
                detalleDAO.registrarDetalle(conexion, detalle);
                moverInventario(conexion, despacho.getCodMaq(), detalle);
            }
            conexion.commit();
            return true;
        } catch (SQLException e) {
            try { conexion.rollback(); } catch (SQLException ex) { System.out.println("Error al revertir despacho: " + ex); }
            System.out.println("Error al registrar despacho: " + e);
            return false;
        } finally {
            try { conexion.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error al restaurar autocommit: " + e); }
            con.desconectar(conexion);
        }
    }

    private void registrarDespacho(Connection conexion, Despacho despacho) throws SQLException {
        String sql = "INSERT INTO DESPACHO (cod_desp, fecha_desp, cod_emp, cod_maq) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, despacho.getCodDesp());
            ps.setDate(2, Date.valueOf(despacho.getFechaDesp()));
            ps.setString(3, despacho.getCodEmp());
            ps.setString(4, despacho.getCodMaq());
            ps.execute();
        }
    }

    private void validarDisponibilidadBodega(Connection conexion, List<DetalleDespacho> detalles) throws SQLException {
        String sql = "SELECT cant_disp FROM INVENTARIO_BODEGA WHERE cod_prod = ?";
        for (DetalleDespacho detalle : detalles) {
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, detalle.getCodProd());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next() || rs.getInt("cant_disp") < detalle.getCantidadDesp()) {
                        throw new SQLException("Stock insuficiente en bodega para " + detalle.getCodProd());
                    }
                }
            }
        }
    }

    private void moverInventario(Connection conexion, String codMaq, DetalleDespacho detalle) throws SQLException {
        String restarBodega = "UPDATE INVENTARIO_BODEGA SET cant_disp = cant_disp - ?, fecha_ult_act = CURRENT_DATE WHERE cod_prod = ?";
        try (PreparedStatement ps = conexion.prepareStatement(restarBodega)) {
            ps.setInt(1, detalle.getCantidadDesp());
            ps.setString(2, detalle.getCodProd());
            ps.executeUpdate();
        }

        String sumarMaquina = "INSERT INTO INVENTARIO_MAQUINA (cod_maq, cod_prod, cant_act, stock_min, fecha_ult_rec) VALUES (?, ?, ?, 20, CURRENT_DATE) "
                + "ON DUPLICATE KEY UPDATE cant_act = cant_act + VALUES(cant_act), stock_min = 20, fecha_ult_rec = CURRENT_DATE";
        try (PreparedStatement ps = conexion.prepareStatement(sumarMaquina)) {
            ps.setString(1, codMaq);
            ps.setString(2, detalle.getCodProd());
            ps.setInt(3, detalle.getCantidadDesp());
            ps.executeUpdate();
        }
    }

    public List<Despacho> obtenerDespachos() {
        List<Despacho> listaDespachos = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM DESPACHO ORDER BY cod_desp";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Despacho d = new Despacho();
                d.setCodDesp(rs.getString("cod_desp"));
                if (rs.getDate("fecha_desp") != null) {
                    d.setFechaDesp(rs.getDate("fecha_desp").toLocalDate());
                }
                d.setCodEmp(rs.getString("cod_emp"));
                d.setCodMaq(rs.getString("cod_maq"));
                listaDespachos.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar despachos: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return listaDespachos;
    }
}

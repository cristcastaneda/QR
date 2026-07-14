package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleOrdenCompra;
import modelo.OrdenCompra;

public class OrdenCompraDAO {

    public String obtenerSiguienteCodigo() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return CodigoUtil.siguienteCodigo(conexion, "ORDEN_COMPRA", "cod_ord_com", "60");
        } catch (SQLException e) {
            System.out.println("Error al generar codigo de orden: " + e);
            return "601";
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarOrdenConDetalles(OrdenCompra orden, List<DetalleOrdenCompra> detalles) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();

        try {
            conexion.setAutoCommit(false);
            registrarOrden(conexion, orden);
            DetalleOrdenCompraDAO detalleDAO = new DetalleOrdenCompraDAO();
            for (DetalleOrdenCompra detalle : detalles) {
                detalleDAO.registrarDetalle(conexion, detalle);
            }
            if ("Completado".equalsIgnoreCase(orden.getEstadoOrdCom())) {
                sumarDetallesABodega(conexion, detalles);
            }
            conexion.commit();
            return true;
        } catch (SQLException e) {
            try { conexion.rollback(); } catch (SQLException ex) { System.out.println("Error al revertir orden: " + ex); }
            System.out.println("Error al registrar orden de compra: " + e);
            return false;
        } finally {
            try { conexion.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error al restaurar autocommit: " + e); }
            con.desconectar(conexion);
        }
    }

    private void registrarOrden(Connection conexion, OrdenCompra orden) throws SQLException {
        String sql = "INSERT INTO ORDEN_COMPRA (cod_ord_com, fecha_ord_com, estado_ord_com, fecha_rec_ord_com, cod_pro, cod_emp) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, orden.getCodOrdCom());
            ps.setDate(2, Date.valueOf(orden.getFechaOrdCom()));
            ps.setString(3, orden.getEstadoOrdCom());
            if (orden.getFechaRecOrdCom() == null) {
                ps.setDate(4, null);
            } else {
                ps.setDate(4, Date.valueOf(orden.getFechaRecOrdCom()));
            }
            ps.setString(5, orden.getCodPro());
            ps.setString(6, orden.getCodEmp());
            ps.execute();
        }
    }

    public boolean completarOrden(String codOrdCom) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();

        try {
            conexion.setAutoCommit(false);
            String estado = obtenerEstadoOrden(conexion, codOrdCom);
            if (!"En proceso".equalsIgnoreCase(estado)) {
                conexion.rollback();
                return false;
            }
            List<DetalleOrdenCompra> detalles = obtenerDetallesPorOrden(conexion, codOrdCom);
            sumarDetallesABodega(conexion, detalles);
            String sql = "UPDATE ORDEN_COMPRA SET estado_ord_com = 'Completado', fecha_rec_ord_com = CURRENT_DATE WHERE cod_ord_com = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, codOrdCom);
                ps.executeUpdate();
            }
            conexion.commit();
            return true;
        } catch (SQLException e) {
            try { conexion.rollback(); } catch (SQLException ex) { System.out.println("Error al revertir completar orden: " + ex); }
            System.out.println("Error al completar orden: " + e);
            return false;
        } finally {
            try { conexion.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error al restaurar autocommit: " + e); }
            con.desconectar(conexion);
        }
    }

    private String obtenerEstadoOrden(Connection conexion, String codOrdCom) throws SQLException {
        String sql = "SELECT estado_ord_com FROM ORDEN_COMPRA WHERE cod_ord_com = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codOrdCom);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("estado_ord_com");
                }
            }
        }
        return "";
    }

    private List<DetalleOrdenCompra> obtenerDetallesPorOrden(Connection conexion, String codOrdCom) throws SQLException {
        List<DetalleOrdenCompra> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_ORDEN_COMPRA WHERE cod_ord_com = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codOrdCom);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new DetalleOrdenCompra(
                        rs.getString("cod_ord_com"),
                        rs.getString("cod_prod"),
                        rs.getInt("cant_sol"),
                        rs.getDouble("precio_uni"),
                        rs.getDouble("sub_total")
                    ));
                }
            }
        }
        return detalles;
    }

    private void sumarDetallesABodega(Connection conexion, List<DetalleOrdenCompra> detalles) throws SQLException {
        for (DetalleOrdenCompra detalle : detalles) {
            String sql = "INSERT INTO INVENTARIO_BODEGA (cod_prod, cant_disp, stock_min, fecha_ult_act) VALUES (?, ?, 100, CURRENT_DATE) "
                    + "ON DUPLICATE KEY UPDATE cant_disp = cant_disp + VALUES(cant_disp), stock_min = 100, fecha_ult_act = CURRENT_DATE";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, detalle.getCodProd());
                ps.setInt(2, detalle.getCantSol());
                ps.executeUpdate();
            }
        }
    }

    public List<OrdenCompra> obtenerOrdenes() {
        List<OrdenCompra> listaOrdenes = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM ORDEN_COMPRA ORDER BY cod_ord_com";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OrdenCompra oc = new OrdenCompra();
                oc.setCodOrdCom(rs.getString("cod_ord_com"));
                if (rs.getDate("fecha_ord_com") != null) {
                    oc.setFechaOrdCom(rs.getDate("fecha_ord_com").toLocalDate());
                }
                oc.setEstadoOrdCom(rs.getString("estado_ord_com"));
                if (rs.getDate("fecha_rec_ord_com") != null) {
                    oc.setFechaRecOrdCom(rs.getDate("fecha_rec_ord_com").toLocalDate());
                }
                oc.setCodPro(rs.getString("cod_pro"));
                oc.setCodEmp(rs.getString("cod_emp"));
                listaOrdenes.add(oc);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ordenes: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return listaOrdenes;
    }
}

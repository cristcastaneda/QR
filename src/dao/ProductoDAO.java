package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

public class ProductoDAO {

    public String obtenerSiguienteCodigo() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return CodigoUtil.siguienteCodigo(conexion, "PRODUCTO", "cod_prod", "40");
        } catch (SQLException e) {
            System.out.println("Error al generar codigo de producto: " + e);
            return "401";
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarProducto(Producto producto) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "INSERT INTO PRODUCTO (cod_prod, nom_prod, categ_prod, fecha_venc, precio_ven_prod, unidad_med_prod) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, producto.getCodProd());
            ps.setString(2, producto.getNomProd());
            ps.setString(3, producto.getCategProd());
            ps.setDate(4, Date.valueOf(producto.getFechaVenc()));
            ps.setDouble(5, producto.getPrecioVenProd());
            ps.setString(6, producto.getUnidadMedProd());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e);
            return false;
        } finally {
            con.desconectar(conexion);
        }
    }

    public List<Producto> obtenerProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM PRODUCTO ORDER BY cod_prod";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodProd(rs.getString("cod_prod"));
                p.setNomProd(rs.getString("nom_prod"));
                p.setCategProd(rs.getString("categ_prod"));
                if (rs.getDate("fecha_venc") != null) {
                    p.setFechaVenc(rs.getDate("fecha_venc").toLocalDate());
                }
                p.setPrecioVenProd(rs.getDouble("precio_ven_prod"));
                p.setUnidadMedProd(rs.getString("unidad_med_prod"));
                listaProductos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return listaProductos;
    }
}

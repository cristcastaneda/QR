package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;

public class ProveedorDAO {

    public String obtenerSiguienteCodigo() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return CodigoUtil.siguienteCodigo(conexion, "PROVEEDOR", "cod_pro", "30");
        } catch (SQLException e) {
            System.out.println("Error al generar codigo de proveedor: " + e);
            return "301";
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarProveedor(Proveedor proveedor) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "INSERT INTO PROVEEDOR (cod_pro, nom_pro, nit_pro, tel_pro, dir_pro, con_pro) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, proveedor.getCodPro());
            ps.setString(2, proveedor.getNomPro());
            ps.setString(3, proveedor.getNitPro());
            ps.setString(4, proveedor.getTelPro());
            ps.setString(5, proveedor.getDirPro());
            ps.setString(6, proveedor.getConPro());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar proveedor: " + e);
            return false;
        } finally {
            con.desconectar(conexion);
        }
    }

    public List<Proveedor> obtenerProveedores() {
        List<Proveedor> listaProveedores = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM PROVEEDOR ORDER BY cod_pro";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setCodPro(rs.getString("cod_pro"));
                p.setNomPro(rs.getString("nom_pro"));
                p.setNitPro(rs.getString("nit_pro"));
                p.setTelPro(rs.getString("tel_pro"));
                p.setDirPro(rs.getString("dir_pro"));
                p.setConPro(rs.getString("con_pro"));
                listaProveedores.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return listaProveedores;
    }
}

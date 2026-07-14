package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Maquina;

public class MaquinaDAO {

    public String obtenerSiguienteCodigo() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return CodigoUtil.siguienteCodigo(conexion, "MAQUINA", "cod_maq", "50");
        } catch (SQLException e) {
            System.out.println("Error al generar codigo de maquina: " + e);
            return "501";
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarMaquina(Maquina maquina) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "INSERT INTO MAQUINA (cod_maq, cod_serie, ubic_maq, estado_maq, fecha_ins_maq, cod_cli) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, maquina.getCodMaq());
            ps.setString(2, maquina.getCodSerie());
            ps.setString(3, maquina.getUbicMaq());
            ps.setString(4, maquina.getEstadoMaq());
            ps.setDate(5, Date.valueOf(maquina.getFechaInsMaq()));
            ps.setString(6, maquina.getCodCli());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar maquina: " + e);
            return false;
        } finally {
            con.desconectar(conexion);
        }
    }

    public List<Maquina> obtenerMaquinas() {
        List<Maquina> listaMaquinas = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM MAQUINA ORDER BY cod_maq";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Maquina m = new Maquina();
                m.setCodMaq(rs.getString("cod_maq"));
                m.setCodSerie(rs.getString("cod_serie"));
                m.setUbicMaq(rs.getString("ubic_maq"));
                m.setEstadoMaq(rs.getString("estado_maq"));
                if (rs.getDate("fecha_ins_maq") != null) {
                    m.setFechaInsMaq(rs.getDate("fecha_ins_maq").toLocalDate());
                }
                m.setCodCli(rs.getString("cod_cli"));
                listaMaquinas.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar maquinas: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return listaMaquinas;
    }
}

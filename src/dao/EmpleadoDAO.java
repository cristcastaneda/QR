package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;

public class EmpleadoDAO {

    public String obtenerSiguienteCodigo() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return CodigoUtil.siguienteCodigo(conexion, "EMPLEADO", "cod_emp", "20");
        } catch (SQLException e) {
            System.out.println("Error al generar codigo de empleado: " + e);
            return "201";
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarEmpleado(Empleado empleado) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "INSERT INTO EMPLEADO (cod_emp, nom_emp, cargo_emp, tel_emp, fecha_ing_emp) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getCodEmp());
            ps.setString(2, empleado.getNomEmp());
            ps.setString(3, empleado.getCargoEmp());
            ps.setString(4, empleado.getTelEmp());
            ps.setDate(5, Date.valueOf(empleado.getFechaIngEmp()));
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar empleado: " + e);
            return false;
        } finally {
            con.desconectar(conexion);
        }
    }

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM EMPLEADO ORDER BY cod_emp";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setCodEmp(rs.getString("cod_emp"));
                emp.setNomEmp(rs.getString("nom_emp"));
                emp.setCargoEmp(rs.getString("cargo_emp"));
                emp.setTelEmp(rs.getString("tel_emp"));
                if (rs.getDate("fecha_ing_emp") != null) {
                    emp.setFechaIngEmp(rs.getDate("fecha_ing_emp").toLocalDate());
                }
                listaEmpleados.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return listaEmpleados;
    }

    public boolean eliminarEmpleado(String codEmp) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "DELETE FROM EMPLEADO WHERE cod_emp = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codEmp);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e);
            return false;
        } finally {
            con.desconectar(conexion);
        }
    }
}

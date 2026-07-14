package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDAO {

    public String obtenerSiguienteCodigo() {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        try {
            return CodigoUtil.siguienteCodigo(conexion, "CLIENTE", "cod_cli", "10");
        } catch (SQLException e) {
            System.out.println("Error al generar codigo de cliente: " + e);
            return "101";
        } finally {
            con.desconectar(conexion);
        }
    }

    public boolean registrarCliente(Cliente cliente) {
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "INSERT INTO CLIENTE (cod_cli, nom_cli, dir_cli, nit, con_cli, tel_cli) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getCodCli());
            ps.setString(2, cliente.getNomCli());
            ps.setString(3, cliente.getDirCli());
            ps.setString(4, cliente.getNit());
            ps.setString(5, cliente.getConCli());
            ps.setString(6, cliente.getTelCli());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: " + e);
            return false;
        } finally {
            con.desconectar(conexion);
        }
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        Conexion con = new Conexion();
        Connection conexion = con.conectar();
        String sql = "SELECT * FROM CLIENTE ORDER BY cod_cli";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCodCli(rs.getString("cod_cli"));
                cli.setNomCli(rs.getString("nom_cli"));
                cli.setDirCli(rs.getString("dir_cli"));
                cli.setNit(rs.getString("nit"));
                cli.setConCli(rs.getString("con_cli"));
                cli.setTelCli(rs.getString("tel_cli"));
                listaClientes.add(cli);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e);
        } finally {
            con.desconectar(conexion);
        }
        return listaClientes;
    }
}

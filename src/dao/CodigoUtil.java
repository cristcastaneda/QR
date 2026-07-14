package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodigoUtil {

    private CodigoUtil() {
    }

    public static String siguienteCodigo(Connection conexion, String tabla, String columna, String prefijo) throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(CAST(" + columna + " AS CHAR), ?) AS UNSIGNED)) AS ultimo FROM " + tabla
                + " WHERE CAST(" + columna + " AS CHAR) LIKE ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, prefijo.length() + 1);
            ps.setString(2, prefijo + "%");
            try (ResultSet rs = ps.executeQuery()) {
                int siguiente = 1;
                if (rs.next()) {
                    siguiente = rs.getInt("ultimo") + 1;
                }
                return prefijo + siguiente;
            }
        }
    }
}

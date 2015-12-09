package ecommerce.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Gustavo
 */
public class Conexao {

    private static final String nomeDb = "ecommerce";
//    private static final String nomeDb = "carlos_senac_ecommerce_trabalho";
    private static final String userDb = "root";
    private static final String senhaDb = "zaxs1425";

    public static Connection abrirConexao() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nomeDb, userDb, senhaDb);
    }

    private static void fecha(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void fechaConexao(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        fecha(conn, stmt, rs);
    }

    public static void fechaConexao(Connection conn, Statement stmt) throws Exception {
        fecha(conn, stmt, null);
    }

    public static void fechaConexao(Connection conn) throws Exception {
        fecha(conn, null, null);
    }

    
}

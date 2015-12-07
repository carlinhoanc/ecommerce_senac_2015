package ecommerce.dao;

import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Gustavo
 */
public class UsuarioDaoImp implements UsuarioDao {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    @Override
    public boolean salvar(Object obj) throws Exception {
        boolean flag = true;
        Usuario u = (Usuario) obj;
        try {
            String query = "INSERT INTO usuario (email,senha,tipoUsuario) VALUES(?,?,?)";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, u.getEmail());
            pstm.setString(2, u.getSenha());
            pstm.setString(3, u.getTpUsuario());
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            rs.next();
            u.setCodigo(rs.getInt(1));
        } catch (Exception e) {
            System.out.println("Erro conexão usuario " + e.getMessage());
            flag = false;
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

    @Override
    public boolean alterar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object pesquisar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) throws Exception {
        boolean flag = true;
        try {
            String query = "UPDATE usuario SET ativo = false WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro conexão Desativar usuario " + e.getMessage());
            flag = false;
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

    @Override
    public Usuario salvar(Usuario u) throws Exception {
        // Usuario u = (Usuario) obj;
        try {
            String query = "INSERT INTO usuario (email,senha,tipoUsuario) VALUES(?,?,?)";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, u.getEmail());
            pstm.setString(2, u.getSenha());
            pstm.setString(3, u.getTpUsuario());
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            rs.next();
            u.setCodigo(rs.getInt(1));
        } catch (Exception e) {
            System.out.println("Erro conexão usuario " + e.getMessage());

        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return u;
    }

    @Override
    public Pessoa altenticar(Usuario u) throws Exception {
        Pessoa p = null;
        try {
            String query = "SELECT p.nome, u.email, u.tipoUsuario FROM usuario u JOIN pessoa p ON u.codigo = p.idUsuario WHERE u.email = ? AND u.senha = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, u.getEmail());
            pstm.setString(2, u.getSenha());
            rs = pstm.executeQuery();
            if (rs.next()) {
                p = new Pessoa();
                u.setEmail(rs.getString("u.email"));
                u.setTpUsuario(rs.getString("u.tipoUsuario"));
                p.setNome(rs.getString("p.nome"));
                p.setUsuario(u);
            }
        } catch (Exception e) {
            System.out.println("Erro conexão altenticar " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return p;
    }

}

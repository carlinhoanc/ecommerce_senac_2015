package ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ecommerce.entidade.Marca;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public class MarcaDaoImp implements MarcaDao {
    
    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    
    @Override
    public boolean salvar(Object obj) throws Exception {
        Marca m = (Marca) obj;
        boolean flag = true;
        try {
            String query = "INSERT INTO marca (nome, descricao,ativo) values(?,?,?)";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, m.getNome());
            pstm.setString(2, m.getDescricao());
            pstm.setBoolean(3, m.isAtivo());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro de conexão " + e.getMessage());
            flag = false;
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }
    
    @Override
    public boolean alterar(Object obj) throws Exception {
        Marca m = (Marca) obj;
        boolean flag = true;
        try {
            String query = "UPDATE marca SET nome =?, descricao = ?, ativo = ? WHERE codigo = ? ";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, m.getNome());
            pstm.setString(2, m.getDescricao());
            pstm.setBoolean(3, m.isAtivo());
            pstm.setInt(4, m.getCodigo());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro conexao altarar " + e.getMessage());
            flag = false;
        }
        return flag;
    }
    
    @Override
    public Object pesquisar(int id) throws Exception {
        Marca m = null;
        try {
            String query = "SELECT * FROM marca WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                m = new Marca();
                m.setCodigo(rs.getInt("codigo"));
                m.setNome(rs.getString("nome"));
                m.setDescricao(rs.getString("descricao"));
                m.setAtivo(rs.getBoolean("ativo"));
            }
        } catch (Exception e) {
            System.out.println("Erro conexão pesquisar " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return m;
    }
    
    @Override
    public boolean excluir(int id) throws Exception {
        boolean flag = true;
        String query = "DELETE FROM marca WHERE codigo = ?";
        try {
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, id);
            pstm.executeQuery();
        } catch (Exception e) {
            System.out.println("Erro na conexão excluir " + e.getMessage());
            flag = false;
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }
    
    @Override
    public List<Marca> listar() throws Exception {
        List<Marca> marcas = new ArrayList();
        try {
            String query = "SELECT * FROM marca WHERE ativo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setBoolean(1, true);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Marca m = new Marca();
                m.setCodigo(rs.getInt("codigo"));
                m.setNome(rs.getString("nome"));
                m.setDescricao(rs.getString("descricao"));
                marcas.add(m);
            }
        } catch (Exception e) {
            System.out.println("Erro ao conexão listar " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return marcas;
    }
    
    @Override
    public List<Marca> listarEdicao() throws Exception {
        List<Marca> marcas = new ArrayList();
        String query = "SELECT * FROM marca";
        conn = Conexao.abrirConexao();
        pstm = conn.prepareCall(query);
        rs = pstm.executeQuery();
        while (rs.next()) {
            Marca m = new Marca();
            m.setCodigo(rs.getInt("codigo"));
            m.setNome(rs.getString("nome"));
            m.setDescricao(rs.getString("descricao"));
            m.setAtivo(rs.getBoolean("ativo"));
            if (m.isAtivo()) {
                m.setAtivoString("sim");
            } else {
                m.setAtivoString("nao");
            }
            marcas.add(m);
        }
        return marcas;
    }
}

package ecommerce.dao;

import ecommerce.entidade.CategoriaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public class CategoriaProdutoDaoImp implements CategoriaProdutoDao {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    @Override
    public boolean salvar(Object obj) throws Exception {
        CategoriaProduto cp = (CategoriaProduto) obj;
        boolean flag = false;
        try {
            String query = "INSERT INTO categoriaproduto (nome, descricao, ativo) VALUES(?,?,?)";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, cp.getNome());
            pstm.setString(2, cp.getDescricao());
            pstm.setBoolean(3, cp.isAtivo());
            pstm.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("Erro conexão salvar Categoria MSN : " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

    @Override
    public boolean alterar(Object obj) throws Exception {
        CategoriaProduto cp = (CategoriaProduto) obj;
        boolean flag = false;
        try {
            String query = "UPDATE categoriaproduto SET nome = ?, descricao = ?, ativo = ? WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, cp.getNome());
            pstm.setString(2, cp.getDescricao());
            pstm.setBoolean(3, cp.isAtivo());
            pstm.setInt(4, cp.getCodigo());
            pstm.executeUpdate();
            flag = true;
        } catch (Exception e) {
            System.out.println("Erro conexão Alterar CTG, MSG :" + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

    @Override
    public Object pesquisar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategoriaProduto> listar() throws Exception {
        List<CategoriaProduto> categorias = new ArrayList();
        try {
            String query = "SELECT * FROM categoriaproduto WHERE ativo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setBoolean(1, true);
            rs = pstm.executeQuery();
            while (rs.next()) {
                CategoriaProduto cp = new CategoriaProduto();
                cp.setCodigo(rs.getInt("codigo"));
                cp.setNome(rs.getString("nome"));
                cp.setDescricao(rs.getString("descricao"));
                categorias.add(cp);
            }
        } catch (Exception e) {
            System.out.println("Erro conexão listar Categorias " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return categorias;
    }

    @Override
    public List<CategoriaProduto> listarEdicao() throws Exception {
        List<CategoriaProduto> categorias = new ArrayList();
        try {
            String query = "SELECT * FROM categoriaproduto";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                CategoriaProduto cp = new CategoriaProduto();
                cp.setCodigo(rs.getInt("codigo"));
                cp.setNome(rs.getString("nome"));
                cp.setDescricao(rs.getString("descricao"));
                cp.setAtivo(rs.getBoolean("ativo"));
                cp.setAtivoString("sim");
                if (!cp.isAtivo()) {
                    cp.setAtivoString("nao");
                }
                categorias.add(cp);
            }
        } catch (Exception e) {
            System.out.println("Erro conexão listar Categorias " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return categorias;
    }

}

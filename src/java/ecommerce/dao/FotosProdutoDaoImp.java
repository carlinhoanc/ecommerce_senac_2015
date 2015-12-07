package ecommerce.dao;

import ecommerce.entidade.FotosProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public class FotosProdutoDaoImp implements FotosProdutoDao {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    @Override
    public boolean salvar(Object obj) throws Exception {
        FotosProduto fp = (FotosProduto) obj;
        boolean flag = false;
        try {
            String query = "INSERT INTO fotosproduto (nome,caminho,tipo,tamanho,idProduto,principal) VALUES(?,?,?,?,?,?)";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, fp.getNome());
            pstm.setString(2, fp.getCaminho());
            pstm.setString(3, fp.getTipo());
            pstm.setInt(4, fp.getTamanho());
            pstm.setInt(5, fp.getIdProduto());
            pstm.setBoolean(6, fp.isPrincipal());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro conexao salver fotos :" + e.getMessage());
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
        FotosProduto fp = null;
        try {
            String query = "SELECT caminho, codigo FROM fotosProduto WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                fp = new FotosProduto();
                fp.setCaminho(rs.getString("caminho"));
                fp.setCodigo(rs.getInt("codigo"));
            }
        } catch (Exception e) {
            System.out.println("erro conexão " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return fp;

    }

    @Override
    public boolean excluir(int id) throws Exception {
        boolean flag = false;
        try {
            String query = "DELETE FROM fotosproduto WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro conexão MSN : " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

    @Override
    public List<FotosProduto> pesquisaImgProduto(int idProduto) throws Exception {
        List<FotosProduto> imagens = new ArrayList();
        try {
            String query = "SELECT * FROM fotosproduto WHERE idProduto = ? ";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, idProduto);
            rs = pstm.executeQuery();
            while (rs.next()) {
                FotosProduto fp = new FotosProduto();
                fp.setCodigo(rs.getInt("codigo"));
                fp.setNome(rs.getString("nome"));
                fp.setPrincipal(rs.getBoolean("principal"));
                imagens.add(fp);
            }
        } catch (Exception e) {
            System.out.println("Erro conexão Fotos " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return imagens;
    }

    @Override
    public void ativerImgPrincipal(int idProduto, int idImg) throws Exception {
        try {
            String query1 = "UPDATE fotosproduto SET principal = ? WHERE idProduto = ?";
            String query2 = "UPDATE fotosproduto SET principal = ? WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query1);
            pstm.setBoolean(1, false);
            pstm.setInt(2, idProduto);
            pstm.executeUpdate();
            pstm = conn.prepareCall(query2);
            pstm.setBoolean(1, true);
            pstm.setInt(2, idImg);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao alterar " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
    }

    @Override
    public List<FotosProduto> bustaImgPrincipal(int idProduto) throws Exception {
        List<FotosProduto> fps = new ArrayList();
        try {
            String query = "SELECT * FROM fotosproduto WHERE idProduto = ? AND principal = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, idProduto);
            pstm.setBoolean(2, true);
            rs = pstm.executeQuery();
            if (rs.next()) {
                FotosProduto fp = new FotosProduto();
                fp.setNome(rs.getString("nome"));
                fps.add(fp);
            }
        } catch (Exception e) {
            System.out.println("Erro conexao buscarImgPrincipal : " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return fps;
    }

}

package ecommerce.dao;

import ecommerce.entidade.Endereco;
import ecommerce.entidade.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Gustavo
 */
public class EnderecoDaoImp implements EnderecoDao {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    @Override
    public boolean salvar(Object obj) throws Exception {
        boolean flag = true;
        Pessoa p = (Pessoa) obj;
        try {
            String query = "INSERT INTO endereco (rua, numero, complemento, bairro, cidade, cep, idPessoa, estado) VALUES (?,?,?,?,?,?,?,?)";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, p.getEndereco().getRua());
            pstm.setInt(2, p.getEndereco().getNumero());
            pstm.setString(3, p.getEndereco().getComplemento());
            pstm.setString(4, p.getEndereco().getBairro());
            pstm.setString(5, p.getEndereco().getCidade());
            pstm.setString(6, p.getEndereco().getCep());
            pstm.setInt(7, p.getCodigo());
            pstm.setString(8, p.getEndereco().getEstado());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro conexão salvar Endereço " + e.getMessage());
            flag = false;
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

    @Override
    public boolean alterar(Object obj) throws Exception {
        boolean flag = true;
        Pessoa p = (Pessoa) obj;
        try {
            String query = "UPDADE endereco SET rua =?, numero =?, complemento= ?, bairro = ?, cidade = ?, cep = ? WHERE idPessoa = ? AND codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, p.getEndereco().getRua());
            pstm.setInt(2, p.getEndereco().getNumero());
            pstm.setString(3, p.getEndereco().getComplemento());
            pstm.setString(4, p.getEndereco().getBairro());
            pstm.setString(5, p.getEndereco().getCidade());
            pstm.setString(6, p.getEndereco().getCep());
            pstm.setInt(7, p.getCodigo());
            pstm.setInt(8, p.getEndereco().getCodigo());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro conexão alterar endereco " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

    @Override
    public Object pesquisar(int id) throws Exception {
        Endereco e = null;
        try {
            String query = "SELECT * FROM endereco WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                e = new Endereco();
                e.setCodigo(rs.getInt("codigo"));
                e.setCep(rs.getString("cep"));
                e.setRua(rs.getString("rua"));
                e.setNumero(rs.getInt("numero"));
                e.setComplemento(rs.getString("complemento"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));

            }
        } catch (Exception ex) {
            System.out.println("Erro conexao pesquisar endereço " + ex.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return e;
    }

    @Override
    public boolean excluir(int id) throws Exception {
        boolean flag = true;
        try {
            String query = "DELETE FORM endereco WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, id);
            pstm.executeQuery();
        } catch (Exception e) {
            System.out.println("Erro conexão excluir endereco " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
        return flag;
    }

}
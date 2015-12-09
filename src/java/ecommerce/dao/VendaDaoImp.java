package ecommerce.dao;

import ecommerce.entidade.Produto;
import ecommerce.entidade.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Jéssica
 */
public class VendaDaoImp implements VendaDao {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    /**
     * Este método é responsável por salvar os dados da venda, 
     * percorre a lista de produtos para obter o valor total da venda e insere
     * 
     * @param obj
     * @return
     * @throws Exception 
     */
    @Override
    public boolean salvar(Object obj) throws Exception {
        double somaValor = 0;
        boolean flag = true;
        Venda v = (Venda) obj;
        
        //soma valor de todos os produtos
        for (Produto p : v.getProdutos()) {
            somaValor = somaValor + p.getValorVenda();
        }

        //seta valor total da venda
        v.setValorTotal(somaValor);
        
        try {
            conn = Conexao.abrirConexao();
            String query = "insert into venda (protocolo, dataVenda, valorTotal, idPessoa, idStatus) values (?,?,?,?,?)";
            pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, v.getProtocolo());
            pstm.setDate(2, new java.sql.Date(v.getDataVenda().getTime()));
            pstm.setDouble(3, v.getValorTotal());
            pstm.setInt(4, v.getPessoa().getCodigo());
            pstm.setDouble(5, v.getStatusVenda().getCodigo());
            pstm.executeUpdate();

            rs = pstm.getGeneratedKeys();
            rs.next();
            v.setCodigo(rs.getInt(1));

            //salva idVenda e idProduto na tabela pedido
            salvarPedido(v);
        } catch (Exception e) {
            System.out.println("Erro ao salvar venda: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return flag;
    }

    /**
     * Este método é responsável por inserir idVenda e idProduto na tabela pedido
     * que é um relacionamento n > m entre a tabela venda e produto
     * @param v
     * @throws java.lang.Exception
    */
    public void salvarPedido(Venda v) throws Exception {
        try {
            conn = Conexao.abrirConexao();

            for (Produto p : v.getProdutos()) {
                String query = "insert into pedido (idVenda, idProduto) values (?,?)";
                pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, v.getCodigo());
                pstm.setInt(2, p.getCodigo());
                pstm.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar pedido: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }
    }

    @Override
    public boolean alterar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object pesquisar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

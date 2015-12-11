package ecommerce.dao;

import ecommerce.entidade.CategoriaProduto;
import ecommerce.entidade.Marca;
import ecommerce.entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public class ProdutoDaoImp implements ProdutoDao {

    private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    @Override
    public boolean salvar(Object obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método responsável por alterar todos os dados do produto de acordo 
     * com o código informado.
     * @param obj
     * @return
     * @throws Exception 
     */
    @Override
    public boolean alterar(Object obj) throws Exception {
        boolean flag = false;
        Produto p = (Produto) obj;
        try {
            String query = "UPDATE produto SET nome = ?, descricao = ?, quantidade = ?, valorCompra =?, valorVenda = ?,"
                    + " idCategoriaProduto = ?, idMarca = ?, ativo = ? WHERE codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getDescricao());
            pstm.setInt(3, p.getQuantidade());
            pstm.setDouble(4, p.getValorCompra());
            pstm.setDouble(5, p.getValorVenda());
            pstm.setInt(6, p.getCategoria().getCodigo());
            pstm.setInt(7, p.getMarca().getCodigo());
            pstm.setBoolean(8, p.isAtivo());
            pstm.setInt(9, p.getCodigo());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o produto: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm);
        }

        return flag;
    }

    @Override
    public Object pesquisar(int id) throws Exception {
        return null;
    }

    @Override
    public boolean excluir(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método responsável por salvar dados do produto retornando o código inserido.
     * 
     * @param produto
     * @return
     * @throws Exception 
     */
    @Override
    public Produto salvarProduto(Produto produto) throws Exception {
        try {
            String query = "INSERT INTO produto (nome,descricao,quantidade,valorCompra,valorVenda,acessos,dataCadastro,idCategoriaProduto,idMarca,ativo) VALUES(?,?,?,?,?,?,?,?,?,?)";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setInt(3, produto.getQuantidade());
            pstm.setDouble(4, produto.getValorCompra());
            pstm.setDouble(5, produto.getValorVenda());
            pstm.setInt(6, 0);
            pstm.setDate(7, new java.sql.Date(produto.getDataCadastro().getTime()));
            pstm.setInt(8, produto.getCategoria().getCodigo());
            pstm.setInt(9, produto.getMarca().getCodigo());
            pstm.setBoolean(10, produto.isAtivo());
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            rs.next();
            produto.setCodigo(rs.getInt(1));

        } catch (Exception e) {
            System.out.println("Erro ao salvar o produto: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return produto;
    }

    /**
     * Método responsável por listar todos os produtos cadastrados no banco de dados.
     * @return
     * @throws Exception 
     */
    @Override
    public List<Produto> listarProdutos() throws Exception {
        List<Produto> produtos = new ArrayList();
        try {
            String query = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                    + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Marca m = new Marca();
                CategoriaProduto cp = new CategoriaProduto();
                Produto p = new Produto();
                m.setCodigo(rs.getInt("m.codigo"));
                m.setNome(rs.getString("m.nome"));
                cp.setCodigo(rs.getInt("cp.codigo"));
                cp.setNome(rs.getString("cp.nome"));
                p.setCodigo(rs.getInt("p.codigo"));
                p.setNome(rs.getString("p.nome"));
                p.setDescricao(rs.getString("p.descricao"));
                p.setQuantidade(rs.getInt("p.quantidade"));
                p.setValorCompra(rs.getDouble("p.valorCompra"));
                p.setValorVenda(rs.getDouble("p.valorVenda"));
                p.setDataCadastro(rs.getDate("p.dataCadastro"));
                p.setMarca(m);
                p.setCategoria(cp);
                p.setAtivo(rs.getBoolean("p.ativo"));
                if (p.isAtivo() == true) {
                    p.setAtivoString("Sim");
                } else {
                    p.setAtivoString("Não");
                }
                produtos.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos cadastrados: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return produtos;
    }

    /**
     * Método responsável por listar os oito produtos mais acessados no site.
     * @return
     * @throws Exception 
     */
    @Override
    public List<Produto> listarProdutosAtivosSiteAcessos() throws Exception {
        List<Produto> produtos = new ArrayList();
        try {
            String query = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                    + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo WHERE p.ativo = ? AND p.quantidade > ? ORDER BY p.acessos DESC LIMIT 8";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setBoolean(1, true);
            pstm.setInt(2, 2);
            rs = pstm.executeQuery();
            FotosProdutoDao fpDao = new FotosProdutoDaoImp();
            while (rs.next()) {
                Marca m = new Marca();
                CategoriaProduto cp = new CategoriaProduto();
                Produto p = new Produto();
                m.setNome(rs.getString("m.nome"));
                cp.setNome(rs.getString("cp.nome"));
                p.setCodigo(rs.getInt("p.codigo"));
                p.setNome(rs.getString("p.nome"));
                p.setDescricao(rs.getString("p.descricao"));
                p.setValorVenda(rs.getDouble("p.valorVenda"));
                p.setMarca(m);
                p.setCategoria(cp);
                p.setFotos(fpDao.buscaImgPrincipal(p.getCodigo()));
                produtos.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos mais acessados: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return produtos;
    }

    /**
     * Método responsável por listar produtos pelo filtro escolhido pelo administador.
     * @param idCategoria
     * @param idMarca
     * @param ativo
     * @return
     * @throws Exception 
     */
    @Override
    public List<Produto> filtroProdutoAdmin(int idCategoria, int idMarca, String ativo) throws Exception {
        List<Produto> produtos = new ArrayList();
        String queryTudo = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo "
                + "WHERE m.codigo = ? AND cp.codigo = ? AND p.ativo = ?";
        String queryCateg = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo "
                + "WHERE cp.codigo = ?";
        String queryMarca = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo "
                + "WHERE m.codigo = ?";
        String queryAtivo = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo "
                + "WHERE p.ativo = ?";
        String queryCategMarca = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo "
                + "WHERE cp.codigo = ? AND m.codigo = ?";
        String queriMarcaAtivo = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo "
                + "WHERE m.codigo = ? AND p.ativo = ?";
        String queriAtivoCeteg = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo "
                + "WHERE p.ativo = ? AND cp.codigo = ?";
        try {
            boolean atv = true;
            if (ativo != null) {
                if (ativo.equals("nao")) {
                    atv = false;
                }
            }
            conn = Conexao.abrirConexao();
            if ((idCategoria != 0) && (idMarca != 0) && (ativo != null)) {
                pstm = conn.prepareCall(queryTudo);
                pstm.setInt(1, idMarca);
                pstm.setInt(2, idCategoria);
                pstm.setBoolean(3, atv);
                rs = pstm.executeQuery();
            } else {
                if ((idCategoria != 0) && (idMarca != 0)) {
                    pstm = conn.prepareCall(queryCategMarca);
                    pstm.setInt(1, idCategoria);
                    pstm.setInt(2, idMarca);
                    rs = pstm.executeQuery();
                } else if ((idCategoria != 0) && (ativo != null)) {
                    pstm = conn.prepareCall(queriAtivoCeteg);
                    pstm.setBoolean(1, atv);
                    pstm.setInt(2, idCategoria);
                    rs = pstm.executeQuery();
                } else if ((idMarca != 0) && (ativo != null)) {
                    pstm = conn.prepareCall(queriMarcaAtivo);
                    pstm.setInt(1, idMarca);
                    pstm.setBoolean(2, atv);
                    rs = pstm.executeQuery();
                } else if (idCategoria != 0) {
                    pstm = conn.prepareCall(queryCateg);
                    pstm.setInt(1, idCategoria);
                    rs = pstm.executeQuery();
                } else if (idMarca != 0) {
                    pstm = conn.prepareCall(queryMarca);
                    pstm.setInt(1, idMarca);
                    rs = pstm.executeQuery();
                } else if (ativo != null) {
                    pstm = conn.prepareCall(queryAtivo);
                    pstm.setBoolean(1, atv);
                    rs = pstm.executeQuery();
                } else {
                    produtos = listarProdutos();
                }
            }
            if (rs != null) {
                while (rs.next()) {
                    Marca m = new Marca();
                    CategoriaProduto cp = new CategoriaProduto();
                    Produto p = new Produto();
                    m.setCodigo(rs.getInt("m.codigo"));
                    m.setNome(rs.getString("m.nome"));
                    cp.setCodigo(rs.getInt("cp.codigo"));
                    cp.setNome(rs.getString("cp.nome"));
                    p.setCodigo(rs.getInt("p.codigo"));
                    p.setNome(rs.getString("p.nome"));
                    p.setDescricao(rs.getString("p.descricao"));
                    p.setQuantidade(rs.getInt("p.quantidade"));
                    p.setValorCompra(rs.getDouble("p.valorCompra"));
                    p.setValorVenda(rs.getDouble("p.valorVenda"));
                    p.setDataCadastro(rs.getDate("p.dataCadastro"));
                    p.setMarca(m);
                    p.setCategoria(cp);
                    p.setAtivo(rs.getBoolean("p.ativo"));
                    if (p.isAtivo() == true) {
                        p.setAtivoString("Sim");
                    } else {
                        p.setAtivoString("Não");
                    }
                    produtos.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao filtrar produtos: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return produtos;
    }

    /**
     * Método responsável por pesquisar os dados do produto selecionado.
     * @param idProduto
     * @return
     * @throws Exception 
     */
    @Override
    public Produto pesqProdutoSelectSite(int idProduto) throws Exception {
        Produto produto = null;
        try {
            String query = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                    + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo WHERE p.codigo = ?";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, idProduto);
            rs = pstm.executeQuery();
            if (rs.next()) {
                FotosProdutoDao fpDao = new FotosProdutoDaoImp();
                Marca m = new Marca();
                CategoriaProduto cp = new CategoriaProduto();
                produto = new Produto();
                m.setCodigo(rs.getInt("m.codigo"));
                m.setNome(rs.getString("m.nome"));
                cp.setCodigo(rs.getInt("cp.codigo"));
                cp.setNome(rs.getString("cp.nome"));
                produto.setCodigo(rs.getInt("p.codigo"));
                produto.setNome(rs.getString("p.nome"));
                produto.setDescricao(rs.getString("p.descricao"));
                produto.setQuantidade(rs.getInt("p.quantidade"));
                produto.setValorCompra(rs.getDouble("p.valorCompra"));
                produto.setValorVenda(rs.getDouble("p.valorVenda"));
                produto.setDataCadastro(rs.getDate("p.dataCadastro"));
                produto.setFotos(fpDao.pesquisaImgProduto(produto.getCodigo()));
                produto.setMarca(m);
                produto.setCategoria(cp);
                marcaVisualizacao(produto.getCodigo());
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar dados do produto: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return produto;
    }

    /**
     * Método responsável por inserir número de acessos de determinado produto.
     * 
     * @param idProduto
     * @throws Exception 
     */
    public void marcaVisualizacao(int idProduto) throws Exception {
        String queryConsuta = "SELECT acessos FROM produto WHERE codigo = ?";
        String queryUpdade = "UPDATE produto SET acessos = ? WHERE codigo = ?";
        try {
            int acessos = 0;
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(queryConsuta);
            pstm.setInt(1, idProduto);
            rs = pstm.executeQuery();
            if (rs.next()) {
                acessos = rs.getInt("acessos");
            }
            ++acessos;
            pstm = conn.prepareCall(queryUpdade);
            pstm.setInt(1, acessos);
            pstm.setInt(2, idProduto);
            pstm.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro ao registrar acessos do produto: " + ex.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }

    }

    /**
     * Método responsável por listar os últimos oito produtos cadastrados no site.
     * @return
     * @throws Exception 
     */
    @Override
    public List<Produto> listarProdutosAtivosSiteRecentes() throws Exception {
        List<Produto> produtos = new ArrayList();
        try {
            String query = "SELECT p.*, m.*, cp.* FROM produto p JOIN marca m ON p.idMarca = m.codigo "
                    + "JOIN categoriaproduto cp ON p.idCategoriaProduto = cp.codigo WHERE p.ativo = ? AND p.quantidade > ? ORDER BY p.codigo DESC LIMIT 8";
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setBoolean(1, true);
            pstm.setInt(2, 2);
            rs = pstm.executeQuery();
            FotosProdutoDao fpDao = new FotosProdutoDaoImp();
            while (rs.next()) {
                Marca m = new Marca();
                CategoriaProduto cp = new CategoriaProduto();
                Produto p = new Produto();
                m.setNome(rs.getString("m.nome"));
                cp.setNome(rs.getString("cp.nome"));
                p.setCodigo(rs.getInt("p.codigo"));
                p.setNome(rs.getString("p.nome"));
                p.setDescricao(rs.getString("p.descricao"));
                p.setValorVenda(rs.getDouble("p.valorVenda"));
                p.setMarca(m);
                p.setCategoria(cp);
                p.setFotos(fpDao.buscaImgPrincipal(p.getCodigo()));
                produtos.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar últimos produtos inseridos: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return produtos;
    }

    /**
     * Método responsável por verificar a quantidade do produto de acordo com o código informado.
     * @param idProduto
     * @param quantidade
     * @return
     * @throws Exception 
     */
    @Override
    public boolean verificaQuantidadeProduto(int idProduto, int quantidade) throws Exception {
        boolean flag = false;
        String query = "SELECT quantidade FROM produto WHERE codigo = ? AND quantidade >= ?";
        try {
            conn = Conexao.abrirConexao();
            pstm = conn.prepareCall(query);
            pstm.setInt(1, idProduto);
            pstm.setInt(2, quantidade);
            rs = pstm.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar quantidade do produto: " + e.getMessage());
        } finally {
            Conexao.fechaConexao(conn, pstm, rs);
        }
        return flag;
    }
}

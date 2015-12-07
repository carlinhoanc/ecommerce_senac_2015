package ecommerce.dao;

import ecommerce.entidade.Produto;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface ProdutoDao extends BaseDao {

    Produto salvarProduto(Produto produto) throws Exception;

    List<Produto> listarProdutos() throws Exception;

    List<Produto> listarProdutosAtivosSite() throws Exception;
    
    List<Produto> filtroProdutoAdmin(int idCategoria, int idMarca, String ativo) throws Exception;
    
    Produto pesqProdutoSelectSite(int idProduto)throws Exception;
}

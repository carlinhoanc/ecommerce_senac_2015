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

    /**
     * Metodo responsanvel por pesquiar os 8 (oito) produtos mais acessados
     *
     * @return List produtos
     * @throws Exception
     */
    List<Produto> listarProdutosAtivosSiteAcessos() throws Exception;

    /**
     * Metodo responsanvel por pesquiar os 8 (oito) produtos que foram inseridos
     * recentemente
     *
     * @return List produtos
     * @throws Exception
     */
    List<Produto> listarProdutosAtivosSiteRecentes() throws Exception;

    List<Produto> filtroProdutoAdmin(int idCategoria, int idMarca, String ativo) throws Exception;

    Produto pesqProdutoSelectSite(int idProduto) throws Exception;
}

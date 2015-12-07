package ecommerce.dao;

import ecommerce.entidade.FotosProduto;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface FotosProdutoDao extends BaseDao {

    List<FotosProduto> pesquisaImgProduto(int idProduto) throws Exception;

    void ativerImgPrincipal(int idProduto, int idImg) throws Exception;

    List<FotosProduto> bustaImgPrincipal(int idProduto) throws Exception;
}

package ecommerce.dao;

import ecommerce.entidade.CategoriaProduto;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface CategoriaProdutoDao extends BaseDao {

    List<CategoriaProduto> listar() throws Exception;

    List<CategoriaProduto> listarEdicao() throws Exception;
}

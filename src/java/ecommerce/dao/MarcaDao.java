package ecommerce.dao;

import ecommerce.entidade.Marca;
import java.util.List;

/**
 *
 * @author Gustavo
 */
public interface MarcaDao extends BaseDao {

    List<Marca> listar() throws Exception;

    List<Marca> listarEdicao() throws Exception;

}

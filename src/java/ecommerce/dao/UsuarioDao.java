package ecommerce.dao;

import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Usuario;

/**
 *
 * @author Gustavo
 */
public interface UsuarioDao extends BaseDao {

    Usuario salvar(Usuario u) throws Exception;
    
    Pessoa autenticar(Usuario u)throws Exception;
}

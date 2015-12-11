package ecommerce.dao;

import ecommerce.entidade.Venda;
import java.util.List;
/**
 *
 * @author Jéssica
 */
public interface VendaDao extends BaseDao{
    /**
     * Metodo responsavel por listar os vendas pendentes
     * @return
     * @throws Exception 
     */
    List<Venda> listarVendaPendente() throws Exception;
    
    /**
     * Metodo responsavel por listar os vendas que estão com estatos de pagamento OK e ainda nao foram para entrega
     * @return
     * @throws Exception 
     */
    List<Venda> listarVendaDespachar() throws Exception;
}

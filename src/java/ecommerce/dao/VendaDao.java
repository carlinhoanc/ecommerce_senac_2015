package ecommerce.dao;

import ecommerce.entidade.Venda;
import java.util.List;

/**
 *
 * @author Jéssica
 */
public interface VendaDao extends BaseDao {

    /**
     * Metodo responsavel por listar os vendas pendentes
     *
     * @return
     * @throws Exception
     */
    List<Venda> listarVendaPendente() throws Exception;

    /**
     * Metodo responsavel por listar os vendas que estão com estatos de
     * pagamento OK e ainda nao foram para entrega
     *
     * @return
     * @throws Exception
     */
    List<Venda> listarVendaDespachar() throws Exception;

    /**
     * Metodo responsavel por modificar o estatus da venda para aprivado
     *
     * @param idVenda
     * @return
     * @throws Exception
     */
    boolean aprovarVenda(int idVenda) throws Exception;

    /**
     * Metodo responsavel por regeitar a venda especifica informado pelo a
     *
     * @param idVenda
     * @return
     * @throws Exception
     */
    boolean regeitarVenda(int idVenda) throws Exception;

    /**
     * Metodo responsavel por mudar os estatos da venda para enviado
     *
     * @param idVenda
     * @return
     * @throws Exception
     */
    boolean despacharVenda(int idVenda) throws Exception;

   
}

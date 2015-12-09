package ecommerce.controle;

import ecommerce.dao.VendaDao;
import ecommerce.dao.VendaDaoImp;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Produto;
import ecommerce.entidade.Status;
import ecommerce.entidade.Venda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jéssica
 */
public class ControleVenda {
    
    private List<Produto> listaProduto;
    
    private Pessoa pessoa;
    private Venda venda;
    
    public static void main(String[] args) throws Exception {
        List<Produto> listaProduto = new ArrayList();
        Produto p = new Produto();
        p.setCodigo(6);
        p.setValorVenda(10);
        listaProduto.add(p);
        
        p = new Produto();
        p.setCodigo(7);
        p.setValorVenda(20);
        listaProduto.add(p);
       
       Pessoa pessoa = new Pessoa();
       pessoa.setCodigo(8);
       
       Status s = new Status();
       s.setCodigo(1);
       
       Venda venda = new Venda();
       venda.setProdutos(listaProduto);
       venda.setProtocolo("0123456");
       venda.setDataVenda(new Date());
       venda.setPessoa(pessoa);
       venda.setStatusVenda(s);
       
       VendaDao vDao = new VendaDaoImp();
       vDao.salvar(venda);
    }
}

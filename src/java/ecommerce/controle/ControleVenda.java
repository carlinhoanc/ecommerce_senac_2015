package ecommerce.controle;

import ecommerce.dao.VendaDao;
import ecommerce.dao.VendaDaoImp;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Produto;
import ecommerce.entidade.Venda;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Jéssica
 */
@ManagedBean
public class ControleVenda {

    private List<Produto> listaProduto;
    private Pessoa pessoa;
    private Venda venda;
    private DataModel modelVendaPendente;
    private DataModel modelVendaDespachar;
    private VendaDao vDao;

//    public static void main(String[] args) throws Exception {
//        List<Produto> listaProduto = new ArrayList();
//        Produto p = new Produto();
//        p.setCodigo(6);
//        p.setValorVenda(10);
//        listaProduto.add(p);
//        
//        p = new Produto();
//        p.setCodigo(7);
//        p.setValorVenda(20);
//        listaProduto.add(p);
//       
//       Pessoa pessoa = new Pessoa();
//       pessoa.setCodigo(8);
//       
//       Status s = new Status();
//       s.setCodigo(1);
//       
//       Venda venda = new Venda();
//       venda.setProdutos(listaProduto);
//       venda.setProtocolo("0123456");
//       venda.setDataVenda(new Date());
//       venda.setPessoa(pessoa);
//       venda.setStatusVenda(s);
//       
//       VendaDao vDao = new VendaDaoImp();
//       vDao.salvar(venda);
//    }
    public DataModel getModelVendaPendente() {
        return modelVendaPendente;
    }

    public DataModel getModelVendaDespachar() {
        return modelVendaDespachar;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void listarVendasPendentes() {
        vDao = new VendaDaoImp();
        try {
            List<Venda> vendas = vDao.listarVendaPendente();
            modelVendaPendente = new ListDataModel(vendas);
        } catch (Exception e) {
            System.out.println("Erro controle MSG : " + e.getMessage());
        }
    }

    public void listarVendasDespache() {
        vDao = new VendaDaoImp();
        try {
            List<Venda> vendas = vDao.listarVendaDespachar();
            modelVendaDespachar = new ListDataModel(vendas);
        } catch (Exception e) {
            System.out.println("Erro controle MSG : " + e.getMessage());
        }
    }
}

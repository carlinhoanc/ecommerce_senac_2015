package ecommerce.controle;

import ecommerce.dao.VendaDao;
import ecommerce.dao.VendaDaoImp;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Produto;
import ecommerce.entidade.Venda;
import ecommerce.util.MD5;
import ecommerce.util.SessionContext;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Jéssica
 */
@ManagedBean
public class ControleVenda {
    
    private List<Produto> carrinho;
    private Pessoa pessoa;
    private Venda venda;
    private DataModel modelVendaPendente;
    private DataModel modelVendaDespachar;
    private VendaDao vDao;
    private boolean renderiza = false;
    
    private FacesContext contexto;
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

    @PostConstruct
    public void inicia() {
        Pessoa p = (Pessoa) SessionContext.getInstance().getUsuarioLogado();
        if (p != null) {
            if (p.getUsuario().getTpUsuario().equals("admin")) {
                listarVendasDespache();
                listarVendasPendentes();
            }
        }
    }
    
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
    
    public List<Produto> getCarrinho() {
        return carrinho;
    }
    
    public boolean isRenderiza() {
        return renderiza;
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
            System.out.println("Erro ao listar despache: " + e.getMessage());
        }
    }
    
    private Venda carregaModalVenda(DataModel model) {
        Venda v = (Venda) model.getRowData();
        return v;
    }
    
    public void aprovarVanda() {
        vDao = new VendaDaoImp();
        Venda v = carregaModalVenda(modelVendaPendente);
        try {
            contexto = FacesContext.getCurrentInstance();
            if (vDao.aprovarVenda(v.getCodigo())) {
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com secesso!!", null));
            } else {
                System.out.println("DEU RUIM CACHOEIRA!!");
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao  realizada operação!!", null));
            }
            listarVendasDespache();
            listarVendasPendentes();
        } catch (Exception e) {
            System.out.println("Erro ao aprovar a venda: " + e.getMessage());
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro critico!!!\nContate o administrador do sitema!!", null));
        }
    }
    
    public void rejeitarVenda() {
        vDao = new VendaDaoImp();
        Venda v = carregaModalVenda(modelVendaPendente);
        try {
            contexto = FacesContext.getCurrentInstance();
            if (vDao.rejeitarVenda(v.getCodigo())) {
                System.out.println("BOA CACHOEIRA!!");
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com secesso!!", null));
            } else {
                System.out.println("DEU RUIM CACHOEIRA!!");
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao  realizada operação!!", null));
            }
        } catch (Exception e) {
            System.out.println("Erro ao rejeitar a venda: " + e.getMessage());
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro critico!!!\nContate o administrador do sitema!!", null));
        }
    }
    
    public void despacharVenda() {
        vDao = new VendaDaoImp();
        Venda v = carregaModalVenda(modelVendaDespachar);
        try {
            contexto = FacesContext.getCurrentInstance();
            if (vDao.despacharVenda(v.getCodigo())) {
                System.out.println("BOA CACHOEIRA!!");
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com secesso!!", null));
            } else {
                System.out.println("DEU RUIM CACHOEIRA!!");
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao  realizada operação!!", null));
            }
            listarVendasDespache();
        } catch (Exception e) {
            System.out.println("Erro ao despachar venda:" + e.getMessage());
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro critico!!!\nContate o administrador do sitema!!", null));
        }
    }
    
    public List<Venda> comprasUsuario() {
        vDao = new VendaDaoImp();
        try {
            Pessoa p = SessionContext.getInstance().getUsuarioLogado();
            List<Venda> compras = vDao.comprasUsuario(p.getCodigo());
            return compras;
        } catch (Exception e) {
            System.out.println("Erro ao listar compras do usuario: " + e.getMessage());
        }
        return null;
    }

    /**
     * Valida de o usuario esta ira verificar se o usuario esta logado para
     * redirecionamento de pagina.
     *
     * @return
     */
    public String validacoesParaCompra() {
        // this.carrinho = carrinho;
        Pessoa p = SessionContext.getInstance().getUsuarioLogado();
        if (p == null) {
            return "/login.faces?faces-redirect=true&cmd=" + MD5.criptografia("compra"); // bloco de tela que ira pedir para o usuario logar ou se cadastrar;
        } else {
            renderiza = true;
            return "venda.faces"; // bloco de tela que finalizario a venda;
        }
        //return null;
    }

//    public boolean ativaFinalizarCompra() {
//       
//        return renderiza;
//    }
}

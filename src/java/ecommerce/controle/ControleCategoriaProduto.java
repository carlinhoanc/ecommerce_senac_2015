package ecommerce.controle;

import ecommerce.dao.CategoriaProdutoDao;
import ecommerce.dao.CategoriaProdutoDaoImp;
import ecommerce.entidade.CategoriaProduto;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class ControleCategoriaProduto {

    private CategoriaProduto cp;

    private CategoriaProdutoDao cpDao;

    private DataModel modelCategoriaProd;

    public CategoriaProduto getCp() {
        if (cp == null) {
            cp = new CategoriaProduto();
            cp.setAtivoString("sim");
        }
        return cp;
    }

    public void setCp(CategoriaProduto cp) {
        this.cp = cp;
    }

    public DataModel getModelCategoriaProd() {
        return modelCategoriaProd;
    }

    public void salvar() {
        cpDao = new CategoriaProdutoDaoImp();
        try {
            if (cp.getAtivoString().equals("sim")) {
                cp.setAtivo(true);
            } else {
                cp.setAtivo(false);
            }
            if (cp.getCodigo() == 0) {
                cpDao.salvar(cp);
            } else {
                cpDao.alterar(cp);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar Categorias MSN : " + e.getMessage());
        }
        listaCtgEdicao();
    }

    public void listaCtgEdicao() {
        cpDao = new CategoriaProdutoDaoImp();
        try {
            List<CategoriaProduto> categorias = cpDao.listarEdicao();
            modelCategoriaProd = new ListDataModel(categorias);
        } catch (Exception e) {
            System.out.println("Erro controle CTG listar Edição MSN :" + e.getMessage());
        }
    }

    public void alteraCtg() {
        cp = (CategoriaProduto) modelCategoriaProd.getRowData();
    }

    public String paginaCtg() {
        listaCtgEdicao();
        return "categoria_produto";
    }
}

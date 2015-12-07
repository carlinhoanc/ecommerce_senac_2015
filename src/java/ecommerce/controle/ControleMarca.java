package ecommerce.controle;

import ecommerce.dao.MarcaDao;
import ecommerce.dao.MarcaDaoImp;
import ecommerce.entidade.Marca;
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
public class ControleMarca {

    private Marca marca;

    private MarcaDao mDao;

    private DataModel modelMarca;

    public Marca getMarca() {
        if (marca == null) {
            marca = new Marca();
            marca.setAtivoString("sim");
        }
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public DataModel getModelMarca() {
        return modelMarca;
    }

    /* Metodos que manipulão Marca*/
    public void salvar() {
        mDao = new MarcaDaoImp();
        if (marca == null) {
            marca = new Marca();
            marca.setAtivoString("sim");
        }
        try {
            if (marca.getAtivoString().equals("sim")) {
                marca.setAtivo(true);
            } else {
                marca.setAtivo(false);
            }
            if (marca.getCodigo() == 0) {
                mDao.salvar(marca);
            } else {
                mDao.alterar(marca);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar/Alterar Marca " + e.getMessage());
        }
        marca = null;
        pesquiarMarcas();
    }

    public void pesquiarMarcas() {
        mDao = new MarcaDaoImp();
        try {
            List<Marca> marcas = mDao.listarEdicao();
            modelMarca = new ListDataModel(marcas);
        } catch (Exception e) {
            System.out.println("Erro ao listar Marcas Edição " + e.getMessage());
        }
    }

    public void alterarMarca() {
        marca = (Marca) modelMarca.getRowData();
    }

    public String paginaMarca() {
        pesquiarMarcas();
        return "marcas";
    }
}
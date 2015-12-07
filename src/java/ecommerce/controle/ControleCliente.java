package ecommerce.controle;

import ecommerce.dao.PessoaDao;
import ecommerce.dao.PessoaDaoImp;
import ecommerce.entidade.Endereco;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Usuario;
import ecommerce.util.WebServiceCep;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class ControleCliente {

    private Pessoa pessoa;
    private Endereco endereco;
    private Usuario usuario;
    private PessoaDao pDao;
//    private String classShowHide;
    
//    @PostConstruct
//    public void inicia() {
//        System.out.println("Passu construtor");
//        classShowHide = "show";
//    }

    public Pessoa getPessoa() {
        if (pessoa == null) {
            pessoa = new Pessoa();
            pessoa.setDataCadastro(new Date());
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
//            endereco.setRua("rua");
//            endereco.setBairro("bairro");
//            endereco.setCidade("cidade");
//            endereco.setEstado("es");
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

//    public String getShow() {
//        return classShowHide;
//    }

    public void salvar() {
        pDao = new PessoaDaoImp();
        usuario.setTpUsuario("usuario");
        pessoa.setUsuario(usuario);
        pessoa.setEndereco(endereco);

        try {
            if (pessoa.getCodigo() == 0) {
                pDao.salvar(pessoa);
            } else {
                pDao.alterar(pessoa);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar Pessoa " + e.getMessage());
        }

    }

    public void pesquisaCep() {
        if (endereco.getCep() != null) {
            try {
                WebServiceCep wsc = WebServiceCep.searchCep(endereco.getCep());
                endereco.setRua(wsc.getLogradouroFull());
                endereco.setCidade(wsc.getCidade());
                endereco.setBairro(wsc.getBairro());
                endereco.setEstado(wsc.getUf());
            } catch (Exception e) {
                System.out.println("Erro ao pesquisar Cep : " + e.getMessage());
            }
        }
    }

//    public void showHide(String clas) {
//        if (clas.equals("show")) {
//            classShowHide = "show";
//        } else {
//            classShowHide = "hide";
//        }
//    }
}

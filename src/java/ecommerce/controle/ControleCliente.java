package ecommerce.controle;

import ecommerce.dao.PessoaDao;
import ecommerce.dao.PessoaDaoImp;
import ecommerce.entidade.Endereco;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Usuario;
import ecommerce.util.MD5;
import ecommerce.util.SessionContext;
import ecommerce.util.WebServiceCep;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@SessionScoped
public class ControleCliente {

    private Pessoa pessoa;
    private Endereco endereco;
    private Usuario usuario;
    private PessoaDao pDao;

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

    public void salvar() {
        pDao = new PessoaDaoImp();
        pessoa.setEndereco(endereco);

        try {
            if (pessoa.getCodigo() == 0) {
                usuario.setSenha(MD5.criptografia(usuario.getSenha()));
                usuario.setTpUsuario("usuario");
                pessoa.setUsuario(usuario);
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

    public String pesquisaDadosUsuarioLogado() {
        Pessoa p = SessionContext.getInstance().getUsuarioLogado();
        pDao = new PessoaDaoImp();
        try {
            pessoa = (Pessoa) pDao.pesquisar(p.getCodigo());
            endereco = pessoa.getEndereco();
            return "dados_do_usuario.faces";
        } catch (Exception e) {
            System.out.println("Erro ao pesquiar Dados do usuario logado : " + e.getMessage());
        }
        return null;
    }
}

package ecommerce.controle;

import ecommerce.dao.UsuarioDao;
import ecommerce.dao.UsuarioDaoImp;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Usuario;
import ecommerce.util.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@SessionScoped
public class ControleUsuario {

    private Usuario usuario;

    private UsuarioDao uDao;

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String altenticarAdmin() {
        try {
            uDao = new UsuarioDaoImp();
            Pessoa p = uDao.autenticar(usuario);
            if (p == null) {
                System.out.println("Usuário ou senha incorretos!");
            } else {
                SessionContext.getInstance().setAttribute("usuarioLogado", p);
                return "/paginasAdmin/venda_pendente_despacho.faces?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário : " + e.getMessage());
        }
        return null;
    }
    public String logoutAdmin(){
        SessionContext.getInstance().encerrarSessao();
        return "../../admin/index.faces";
    }
    public Pessoa getUserLogado(){
      return (Pessoa) SessionContext.getInstance().getUsuarioLogado();
    }
}

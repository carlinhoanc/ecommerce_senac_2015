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

    public String altenticar() {
        try {
            uDao = new UsuarioDaoImp();
            Pessoa p = uDao.altenticar(usuario);
            if (p == null) {
                System.out.println("Deu RUIM usuario ou senha incorretoss");
            } else {
                SessionContext.getInstance().setAttribute("usuarioLogado", p);
               
                System.out.println("Boa garoto");
                return "/paginasAdmin/produtos.faces?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("Erro ao altenticar Usuario : " + e.getMessage());
        }
        return null;
    }
    public String logout(){
        SessionContext.getInstance().encerrarSessao();
        return "../login.faces?faces-redirect=true";
    }
    public Pessoa getUserLogado(){
      return (Pessoa) SessionContext.getInstance().getUsuarioLogado();
    }
}

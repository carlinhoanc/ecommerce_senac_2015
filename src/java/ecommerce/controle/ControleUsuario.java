package ecommerce.controle;

import ecommerce.dao.UsuarioDao;
import ecommerce.dao.UsuarioDaoImp;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Usuario;
import ecommerce.util.MD5;
import ecommerce.util.SessionContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@SessionScoped
public class ControleUsuario {

    private Usuario usuario;

    private UsuarioDao uDao;

    private FacesContext contexto;
    private String novaSenha;
    private String cmd;

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String altenticarUsuario() {
        try {
            contexto = FacesContext.getCurrentInstance();
            uDao = new UsuarioDaoImp();
            usuario.setSenha(MD5.criptografia(usuario.getSenha()));
            Pessoa p = uDao.autenticar(usuario);
            if (p == null) {
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário ou senha incorreto", null));
                System.out.println("Usuário ou senha incorretos!");
            } else {
                if (cmd.equals("")) {
                    SessionContext.getInstance().setAttribute("usuarioLogado", p);
                    return "index.faces?faces-redirect=true";
                } else if (cmd.equals("compra")) {
                    SessionContext.getInstance().setAttribute("usuarioLogado", p);
                    return "venda.faces?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
        }
        return null;
    }

    public String altenticarAdmin() {
        try {
            contexto = FacesContext.getCurrentInstance();
            uDao = new UsuarioDaoImp();
            usuario.setSenha(MD5.criptografia(usuario.getSenha()));
            Pessoa p = uDao.autenticar(usuario);
            if (p == null) {
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário ou senha incorreto", null));
                System.out.println("Usuário ou senha incorretos!");
            } else {
                SessionContext.getInstance().setAttribute("usuarioLogado", p);
                return "/paginasAdmin/venda_pendente_despacho.faces?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário admin: " + e.getMessage());
        }
        return null;
    }

    public String logoutAdmin() {
        SessionContext.getInstance().encerrarSessao();
        return "../../admin/index.faces?faces-redirect=true";
    }

    public String logoutUsuario() {
        SessionContext.getInstance().encerrarSessao();
        return "/index.faces?faces-redirect=true";
    }

    public Pessoa getUserLogado() {
        return (Pessoa) SessionContext.getInstance().getUsuarioLogado();
    }

    public String pesqUsuarioAltera() {
        Pessoa p = getUserLogado();
        usuario = p.getUsuario();
        return "dados_conta_usuario.faces";
    }

    public void salvar() {
        Pessoa p = getUserLogado();
        try {
            contexto = FacesContext.getCurrentInstance();
            if (p.getUsuario().getSenha().equals(MD5.criptografia(usuario.getSenha()))) {
                if ((!usuario.getEmail().equals("")) || (!novaSenha.equals(""))) {
                    uDao = new UsuarioDaoImp();
                    usuario.setSenha(MD5.criptografia(novaSenha));
                    usuario.setCodigo(p.getUsuario().getCodigo());
                    if (uDao.alteraDadosUsuario(usuario)) {
                        System.out.println("deu bom");
                        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!!", null));
                    } else {
                        System.out.println("deu rium");
                        contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!!", null));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar dados do usuario : " + e.getMessage());
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro critico!!!\nContate o administrador do sitema!!", null));
        }
    }
}

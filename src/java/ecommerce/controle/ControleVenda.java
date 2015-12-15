package ecommerce.controle;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoletoHTML;
import ecommerce.dao.VendaDao;
import ecommerce.dao.VendaDaoImp;
import ecommerce.entidade.Pessoa;
import ecommerce.entidade.Produto;
import ecommerce.entidade.Status;
import ecommerce.entidade.Venda;
import ecommerce.util.MD5;
import ecommerce.util.Protocolo;
import ecommerce.util.SessionContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private FacesContext contexto;
    private StreamedContent file;
    private String caminhoBoleto;

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

    public String getCaminhoBoleto() {
        return caminhoBoleto;
    }

    public void setCaminhoBoleto(String caminhoBoleto) {
        this.caminhoBoleto = caminhoBoleto;
    }

    public String redirecionaPaginaVenda() {
        Pessoa p = SessionContext.getInstance().getUsuarioLogado();
        String url;
        if (p != null) {
            url = "venda.faces?cmd=" + MD5.criptografia("finalizarCompra");
        } else {
            url = "venda.faces";

        }
        return url;
    }

    public void salvarVendaCartao(Pessoa p, List<Produto> carrinho) {
        vDao = new VendaDaoImp();
        System.out.println("Entrou");
        try {
            venda = new Venda();
            venda.setPessoa(p);
            venda.setProdutos(carrinho);
            venda.setDataVenda(new Date());
            Status s = new Status();
            s.setCodigo(1);
            venda.setStatusVenda(s);
            venda.setProtocolo(Protocolo.getNumeroProtocolo());
            venda.setBoletoCartao("Cartão");
            venda.setNumeroBoletoCartao("33444556667730000888");
            vDao.salvar(venda);

        } catch (Exception e) {
            System.out.println("Erro ao salvar " + e.getMessage());
        }

    }

    /**
     * Metodo que gera o boleto AVISO! ainda nao esta funcionado quando o
     * comendo de geração vem da tela de venda Mais funciona quando eu uso a
     * public static void main para execultar
     */
    public void salvarVendaBoleto(Pessoa p, List<Produto> carrinho) {
        vDao = new VendaDaoImp();
        System.out.println("Entrou");
        try {
            venda = new Venda();
            venda.setPessoa(p);
            venda.setProdutos(carrinho);
            venda.setDataVenda(new Date());
            Status s = new Status();
            s.setCodigo(1);
            venda.setStatusVenda(s);
            venda.setProtocolo(Protocolo.getNumeroProtocolo());
            venda.setBoletoCartao("Boleto");
            String nomeBoletoNumero = Protocolo.getNumeroProtocolo();
            venda.setNumeroBoletoCartao(nomeBoletoNumero);
            vDao.salvar(venda);
            gerarBoleto(nomeBoletoNumero);
        } catch (Exception e) {
            System.out.println("Erro ao salvar " + e.getMessage());
        }
    }

    private void gerarBoleto(String nome) {
        Datas datas = Datas.novasDatas()
                .comDocumento(1, 5, 2008)
                .comProcessamento(1, 5, 2008)
                .comVencimento(2, 5, 2008);

        Endereco enderecoBeneficiario = Endereco.novoEndereco()
                .comLogradouro("Servidão Julia Alexandre Florindo, 90")
                .comBairro("Barra da Lagoa")
                .comCep("88061-423")
                .comCidade("Florianópolis")
                .comUf("SC");

        //Quem emite o boleto
        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                .comNomeBeneficiario("Gustavo Humberto Agostinho")
                .comAgencia("1824").comDigitoAgencia("4")
                .comCodigoBeneficiario("76000")
                .comDigitoCodigoBeneficiario("5")
                .comNumeroConvenio("1207113")
                .comCarteira("18")
                .comEndereco(enderecoBeneficiario)
                .comNossoNumero("9000206");

        Endereco enderecoPagador = Endereco.novoEndereco()
                .comLogradouro(venda.getPessoa().getEndereco().getRua() + "," + venda.getPessoa().getEndereco().getNumero())
                .comBairro(venda.getPessoa().getEndereco().getBairro())
                .comCep(venda.getPessoa().getEndereco().getCep())
                .comCidade(venda.getPessoa().getEndereco().getCidade())
                .comUf(venda.getPessoa().getEndereco().getEstado());

        //Quem paga o boleto
        Pagador pagador = Pagador.novoPagador()
                .comNome(venda.getPessoa().getNome())
                .comDocumento(venda.getPessoa().getCpfCnpj())
                .comEndereco(enderecoPagador);

        Banco banco = new BancoDoBrasil();
        String[] prod = new String[venda.getProdutos().size()];
        int i = 0;
        for (Produto p : venda.getProdutos()) {
            prod[i] = "Produto : " + p.getNome() + " Quantidade : " + p.getQuantidade() + " Valor Unidade : R$" + p.getValorVenda();
            i++;
        }
        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comBeneficiario(beneficiario)
                .comPagador(pagador)
                .comValorBoleto(venda.getValorTotal())
                .comNumeroDoDocumento(nome)
                .comInstrucoes(prod)
                .comLocaisDePagamento("Caixa Econômica Federal", "Banco do Brasil");

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
        // Para gerar um array de bytes a partir de um PDF  
        byte[] bPDF = gerador.geraPDF();

        try {
            File e = new File(getRealPath() + "\\tempBoleto\\");
            e.mkdirs();
            FileOutputStream fos = new FileOutputStream(getRealPath() + "\\tempBoleto\\" + nome + ".pdf");
            fos.write(bPDF);
            fos.close();
            caminhoBoleto = nome + ".pdf";
        } catch (Exception ex) {
            System.out.println("Erro ao gravar Boleto MSG " + ex.getMessage());
        }
    }

    public String getRealPath() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
        return context.getRealPath("/");
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
            return "/venda.faces?faces-redirect=true&cmd=" + MD5.criptografia("finalizarCompra"); // bloco de tela que finalizario a venda;
        }
        //return null;
    }

//    public static void main(String[] args) {
//        Datas datas = Datas.novasDatas()
//                .comDocumento(1, 5, 2008)
//                .comProcessamento(1, 5, 2008)
//                .comVencimento(2, 5, 2008);
//
//        Endereco enderecoBeneficiario = Endereco.novoEndereco()
//                .comLogradouro("Av das Empresas, 555")
//                .comBairro("Bairro Grande")
//                .comCep("01234-555")
//                .comCidade("São Paulo")
//                .comUf("SP");
//
//        //Quem emite o boleto
//        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
//                .comNomeBeneficiario("Fulano de Tal")
//                .comAgencia("1824").comDigitoAgencia("4")
//                .comCodigoBeneficiario("76000")
//                .comDigitoCodigoBeneficiario("5")
//                .comNumeroConvenio("1207113")
//                .comCarteira("18")
//                .comEndereco(enderecoBeneficiario)
//                .comNossoNumero("9000206");
//
//        Endereco enderecoPagador = Endereco.novoEndereco()
//                .comLogradouro("Av dos testes, 111 apto 333")
//                .comBairro("Bairro Teste")
//                .comCep("01234-111")
//                .comCidade("São Paulo")
//                .comUf("SP");
//
//        //Quem paga o boleto
//        Pagador pagador = Pagador.novoPagador()
//                .comNome("PORRAAAAAAAAA")
//                .comDocumento("111.222.333-12")
//                .comEndereco(enderecoPagador);
//
//        Banco banco = new BancoDoBrasil();
//
//        Boleto boleto = Boleto.novoBoleto()
//                .comBanco(banco)
//                .comDatas(datas)
//                .comBeneficiario(beneficiario)
//                .comPagador(pagador)
//                .comValorBoleto("200.00")
//                .comNumeroDoDocumento("1234")
//                .comInstrucoes("instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4", "instrucao 5")
//                .comLocaisDePagamento("local 1", "local 2");
//
//       //GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
//        GeradorDeBoletoHTML gerador = new GeradorDeBoletoHTML(boleto);
//        gerador.geraPDF("BancoDoBrasil.pdf");
//        // Para gerar um boleto em PDF  
//      //  gerador.geraPDF("BancoDoBrasil.pdf");
//
//        // Para gerar um boleto em PNG  
//        gerador.geraPNG("BancoDoBrasil.png");
//
//        // Para gerar um array de bytes a partir de um PDF  
//        byte[] bPDF = gerador.geraPDF();
//
//        // Para gerar um array de bytes a partir de um PNG  
//        byte[] bPNG = gerador.geraPNG();
//
//        try {
//            // File f = new File();
//            FileOutputStream fos = new FileOutputStream("C:\\Users\\Gustavo\\Desktop\\arq\\BancoDoBrasil.pdf");
//            fos.write(bPDF);
//            fos.close();
////            out.write(bPDF);
////            out.close();
//        } catch (IOException ex) {
////            Logger.getLogger(Boletoto.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Erro ao gravar Boleto MSG " + ex.getMessage());
//        }
//    }
}

package ecommerce.controle;

import ecommerce.dao.CategoriaProdutoDao;
import ecommerce.dao.CategoriaProdutoDaoImp;
import ecommerce.dao.FotosProdutoDao;
import ecommerce.dao.FotosProdutoDaoImp;
import ecommerce.dao.MarcaDao;
import ecommerce.dao.MarcaDaoImp;
import ecommerce.dao.ProdutoDao;
import ecommerce.dao.ProdutoDaoImp;
import ecommerce.entidade.CategoriaProduto;
import ecommerce.entidade.FotosProduto;
import ecommerce.entidade.Marca;
import ecommerce.entidade.Produto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@SessionScoped
public class ControleProdutos {

    private Produto produto;
    private Marca marca;
    private CategoriaProduto cp;
    private FotosProduto fotoProd;
    private String ativo;
    private List<Marca> marcas;
    private List<CategoriaProduto> categoProdutos;
    private List<Produto> produtosSite;
    private List<Produto> carrinhoCompra;
    private StreamedContent file;

    private ProdutoDao pDao;
    private FotosProdutoDao fpDao;
    private MarcaDao mDao;

    private DataModel modelImgProd;
    private DataModel modelProduto;
    private DataModel modelMarca;

    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void inicia() {

//        Pessoa p = (Pessoa) SessionContext.getInstance().getUsuarioLogado();
//        if (p.getUsuario().getTpUsuario().equals("admin")) {
//            produto = new Produto();
//            produto.setDataCadastro(new Date());
//        } else {
//            if (produto == null) {
//                produto = new Produto();
//            }
//        }
    }

    public DataModel getModelProduto() {
        return modelProduto;
    }

    public Produto getP() {
        if (produto == null) {
            produto = new Produto();
            produto.setDataCadastro(new Date());
        }
        return produto;
    }

    public void setP(Produto p) {
        this.produto = p;
    }

    public FotosProduto getFotoProd() {
        if (fotoProd == null) {
            fotoProd = new FotosProduto();
        }
        return fotoProd;
    }

    public void setFotoProd(FotosProduto fotoProd) {
        this.fotoProd = fotoProd;
    }

    public DataModel getModelImgProd() {
        return modelImgProd;
    }

    public DataModel getModelMarca() {
        return modelMarca;
    }

    public String getAtivo() {
        ativo = "sim";
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Marca getM() {
        if (marca == null) {
            marca = new Marca();
        }
        return marca;
    }

    public void setM(Marca m) {
        this.marca = m;
    }

    public CategoriaProduto getCp() {
        if (cp == null) {
            cp = new CategoriaProduto();
        }
        return cp;
    }

    public void setCp(CategoriaProduto cp) {
        this.cp = cp;
    }

    public List<Produto> getProdutosSite() {
        return produtosSite;
    }

    public List<Produto> getCarrinhoCompra() {
        return carrinhoCompra;
    }

//    @PostConstruct
//    public void inicia() {
//       
//    }
    public List<SelectItem> getComboboxMarcas() {
        List<SelectItem> listMarcas = new ArrayList<SelectItem>();
        MarcaDao mDao = new MarcaDaoImp();
        try {
            for (Marca marca : mDao.listar()) {
                listMarcas.add(new SelectItem(marca.getCodigo(), marca.getNome()));
            }
            return listMarcas;
        } catch (Exception e) {
            System.out.println("Erro listar Marcas \nMetodo getComboboxMarcas() \nMSG :" + e.getMessage());
        }
        return null;
    }

    public List<SelectItem> getComboboxCtgProd() {
        CategoriaProdutoDao cpDao = new CategoriaProdutoDaoImp();
        List<SelectItem> listCategorias = new ArrayList<SelectItem>();
        try {
            for (CategoriaProduto categoria : cpDao.listar()) {
                listCategorias.add(new SelectItem(categoria.getCodigo(), categoria.getNome()));
            }
            return listCategorias;
        } catch (Exception e) {
            System.out.println("Erro listar Categorias \nMetodo getComboboxCtgProd() \nMSG :" + e.getMessage());
        }
        return null;
    }

    public void salvar() {
        pDao = new ProdutoDaoImp();
        produto.setCategoria(cp);
        produto.setMarca(marca);
        if (ativo.equals("sim")) {
            produto.setAtivo(true);
        } else {
            produto.setAtivo(false);
        }
        try {
            if (produto.getCodigo() == 0) {
                produto = pDao.salvarProduto(produto);
            } else {
                pDao.alterar(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro salvar Produto \nMetodo salvar() \nMSG :" + e.getMessage());
        }
    }

    /**
     * Metodo responsavel por pegar o caminho relativo da pasta onde sera salvo
     * o aqruivo
     *
     * @return
     */
    public String getRealPath() {
        ExternalContext externalContext
                = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
        return context.getRealPath("/");
    }

    /**
     * Metodo responsavel por pegar o aquivo(Imagen do produto) da tela
     *
     * @param event
     */
    public void handleFileUploadAction(FileUploadEvent event) {
        fpDao = new FotosProdutoDaoImp();
        fotoProd = new FotosProduto();

        UploadedFile file = event.getFile();
        fotoProd.setNome(file.getFileName());
        byte[] conteudo;
        try {
            conteudo = IOUtils.toByteArray(file.getInputstream());
            String caminho = getRealPath() + "\\imagensProdutos\\" + fotoProd.getNome();
            System.out.println("CAMINHO :" + caminho);
            ///  File e = new File(getRealPath() + "web\\imagensProdutos\\");
            // e.mkdirs();
            criaArquivo(conteudo, caminho);
            fotoProd.setCaminho(caminho);
            fotoProd.setTamanho(conteudo.length);
        } catch (Exception ex) {
            System.out.println("ERRRRRRRRRRRRRRROooo >>>>>>>>>>" + ex.getMessage() + "\n\n" + ex.getLocalizedMessage());
        }
        String nomeArquivo = fotoProd.getNome();
        int e = nomeArquivo.lastIndexOf(".");
        fotoProd.setTipo(nomeArquivo.substring(e));
        fotoProd.setIdProduto(produto.getCodigo());
        fotoProd.setPrincipal(false);
        try {
            fpDao.salvar(fotoProd);
        } catch (Exception ex) {
            System.out.println("Erro ao salvar Fotos do produto !!!!!!!!!!! " + ex.getMessage());
        }
        // System.out.println("NOME " + cb.getNome() + "\ncaminho" + cb.getCaminho() + "\n tamanho" + cb.getTamanho());
        // downloadComprovante();
        pesquisarImagensPriduto();
    }

    /**
     * Metodo responsavel por salvar o arquivo na pasta comprocantesBancarios
     *
     * @param bytes
     * @param arquivo
     * @throws java.io.IOException
     */
    public void criaArquivo(byte[] bytes, String arquivo) throws IOException {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(arquivo);
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException   >>>>> " + ex.getMessage() + "\n" + ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.out.println("IOException   >>>>> " + ex.getMessage() + "\n" + ex.getLocalizedMessage());
        }
    }

    public void deletaImagenProduto() {
        fpDao = new FotosProdutoDaoImp();
        try {
            fotoProd = (FotosProduto) modelImgProd.getRowData();
            fotoProd = (FotosProduto) fpDao.pesquisar(fotoProd.getCodigo());
            fpDao.excluir(fotoProd.getCodigo());
            new File(fotoProd.getCaminho()).delete();
            pesquisarImagensPriduto();
        } catch (Exception ex) {
            System.out.println("Erro ao excluir Imagen " + ex.getMessage());
        }
    }

    public void pesquisarImagensPriduto() {
        fpDao = new FotosProdutoDaoImp();
        try {
            List<FotosProduto> imgs = fpDao.pesquisaImgProduto(produto.getCodigo());
            modelImgProd = new ListDataModel(imgs);
        } catch (Exception e) {
            System.out.println("Erro pesquisar imagen do produto \npesquisarImagensPriduto() \nMSG :" + e.getMessage());
        }
    }

    public void listarTodosProdutos() {
        pDao = new ProdutoDaoImp();
        try {
            List<Produto> produtos = pDao.listarProdutos();
            modelProduto = new ListDataModel(produtos);
        } catch (Exception e) {
            System.out.println("Erro controle listar Produtos MSN" + e.getMessage());
        }
    }

    public void alterarProduto() {
        produto = (Produto) modelProduto.getRowData();
        marca = produto.getMarca();
        cp = produto.getCategoria();
        pesquisarImagensPriduto();
    }

    private void limpa() {
        produto = null;
        marca = null;
        cp = null;
    }

    public String paginaProduto() {
        limpa();
        produto = new Produto();
        marca = new Marca();
        cp = new CategoriaProduto();
//        listarTodosProdutos();
        return "produtos";
    }

    public List<Produto> listaProdutosSiteAcessos() {
        pDao = new ProdutoDaoImp();
        try {
            produtosSite = null;
            produtosSite = pDao.listarProdutosAtivosSiteAcessos();
        } catch (Exception e) {
            System.out.println("Erro ao listar produto site : " + e.getMessage());
        }
        return produtosSite;
    }

    public List<Produto> listaProdutosSiteUltimosSalvos() {
        pDao = new ProdutoDaoImp();
        try {
            produtosSite = null;
            produtosSite = pDao.listarProdutosAtivosSiteRecentes();
        } catch (Exception e) {
            System.out.println("Erro ao listar produto site : " + e.getMessage());
        }
        return produtosSite;
    }

    public void adicionarImagemPrincipal() {
        try {
            fotoProd = (FotosProduto) modelImgProd.getRowData();
            fpDao = new FotosProdutoDaoImp();
            fpDao.ativerImgPrincipal(produto.getCodigo(), fotoProd.getCodigo());
            pesquisarImagensPriduto();
        } catch (Exception e) {
            System.out.println("Erro ativar a imagem " + e.getMessage());
        }
    }

    public String produtoSelectSite(int idProduto) {
        try {
            pDao = new ProdutoDaoImp();
            produto = pDao.pesqProdutoSelectSite(idProduto);
        } catch (Exception e) {
            System.out.println("Erro controle produtoSelecionado() MSN :" + e.getMessage());
        }
        return "produto_selecionado.faces";
    }

    public void filtroProdutoAdmin() {
        try {
            modelProduto = null;
            pDao = new ProdutoDaoImp();
            List<Produto> prods = pDao.filtroProdutoAdmin(cp.getCodigo(), marca.getCodigo(), produto.getAtivoString());
            modelProduto = new ListDataModel(prods);
        } catch (Exception e) {
            System.out.println("Erro controle filtroProdutoAdmin() MSN :" + e.getMessage());
        }
        cp = null;
        marca = null;
        ativo = null;
    }

    public void adicionarProdutoCarrinho() {
        produto.setQuantidade(1);
        if (carrinhoCompra == null) {
            carrinhoCompra = new ArrayList();
        }
        carrinhoCompra.add(produto);
    }

    public List<Produto> listarCarrinho() {
        return carrinhoCompra;
    }

    public void addMiasProduto(int idProduto, int quantidade) {
        pDao = new ProdutoDaoImp();
        ++quantidade;
        try {
            Produto p;
            if (pDao.verificaQuantidadeProduto(idProduto, quantidade)) {
                p = new Produto();
                for (Produto prod : carrinhoCompra) {
                    if (prod.getCodigo() == idProduto) {
                        p = prod;
                        carrinhoCompra.remove(prod);
                        break;
                    }
                }
                p.setQuantidade(quantidade);
                carrinhoCompra.add(p);
            }else{
                System.out.println("Ultrapassa o estoque porra");            
            }
        } catch (Exception e) {
            System.out.println("FUDEU DEU ERRO MSG : " + e.getMessage());
        }
        listarCarrinho();
    }

    public void removeProdutoCarrinho(int idProduto, int quantidade) {
        --quantidade;
        if (quantidade > 0) {
            Produto p;
            p = new Produto();
            for (Produto prod : carrinhoCompra) {
                if (prod.getCodigo() == idProduto) {
                    p = prod;
                    carrinhoCompra.remove(prod);
                    break;
                }
            }
            p.setQuantidade(quantidade);
            carrinhoCompra.add(p);

        } else {
            for (Produto prod : carrinhoCompra) {
                if (prod.getCodigo() == idProduto) {
                    carrinhoCompra.remove(prod);
                    break;
                }
            }
        }
        listarCarrinho();
    }
}

package ecommerce.entidade;

/**
 *
 * @author Jéssica
 */
public class Marca {

    private int codigo;
    private String nome;
    private String descricao;
    private boolean ativo; //criar campo no banco de dados
    private String ativoString;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getAtivoString() {
        return ativoString;
    }

    public void setAtivoString(String ativoString) {
        this.ativoString = ativoString;
    }

}

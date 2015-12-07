package ecommerce.entidade;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Jéssica
 */
public class Venda {

    private int codigo;
    private String protocolo;
    private Date dataVenda;
    private double valorTotal; // trocar tipo no banco de dados
    private Status statusVenda;
    private List<Produto> produtos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(Status statusVenda) {
        this.statusVenda = statusVenda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}

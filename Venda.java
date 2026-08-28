package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Venda implements Serializable{
    private Sessao sessao;
    private List<String> assentosVendidos;
    private List<Produto> produtosComprados;
    private double valorTotal;
    private String dataVenda;
    private Funcionario responsavel;
    public Venda(Sessao sessao List<String> assentos, List<Produto>produtos, String data, Funcionario func){
        this.sessao = sessao;
        this. assentosVendidos = assentos;
        this.produtosComprados = produtos;
        this.dataVenda = data;
        this.responsavel = func;
        calcularValorTotal();
    }
    private void calcularValorTotal(){
        valorTotal = assentosVendidos.size() * sessao.getValorIngresso();
        for(Produto p : produtosComprados){
            valorTotal += p.getPreco();
        }
    }
    public double getValorTotal(){
        return valorTotal;
    }
    public Sessao getSessao(){
        return sessao;
    }
    public List<Produto> getProdutosComprados(){
        return produtosComprados;
    }
    public String getDataVenda(){
        return dataVenda;
    }
    @Override
    public String toString(){
        return "Data: " + dataVenda + "Filme: " + sessao.getFilme().getNome() + "Assentos: " + assentosVendidos + "Total: R$" + String.format("%.2f", valorTotal);
    }
}
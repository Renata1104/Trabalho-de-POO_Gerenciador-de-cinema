package model;
import java.io.Serializable;
public class Produto implements vendavel, Serializable{
    private String nome;
    private String categoria;
    private double precoUnitario;
    public Produto(String nome, String categoria, double precoUnitario){
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
    }
    @Override
    public double getPreco(){
        return precoUnitario;
    }
    @Override
    public String getDescricao(){
        return nome + " ( " + categoria + " ) ";
    }
    public String getNome (){
        return nome;
    }
    public String getCategoria(){
        return categoria;
    }
    @Override
    public String toString(){
        return nome + "Categoria: " + categoria + "Preço: R$" + String.format("%.2f", precoUnitario);
    }
}
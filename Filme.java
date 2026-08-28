package model;
import java.io.Serializable;
public class Filme implements Serializable{
    private String nome;
    private String genero;
    private int duracaoMinutos;
    private String classificacaoEtaria;
    private boolean emCartaz;
    public Filme(String nome, String genero, int duracaoMinutos, String classificacaoEtaria, boolean emCartaz){
        this.nome = nome;
        this.genero = genero;
        this.duracaoMinutos = duracaoMinutos;
        this.classificacaoEtaria = classificacaoEtaria;
        this.emCartaz = true;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getGenero (){
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
    public int getDuracaoMinutos(){
        return duracaoMinutos;
    }
    public void setDuracaoMinutos(int duracaoMinutos){
        this.duracaoMinutos = duracaoMinutos;
    }
    public String getClassificacaoEtaria(){
        return classificacaoEtaria;
    }
    public void setClassificacaoEtaria(String classificacaoEtaria){
        this.classificacaoEtaria = classificacaoEtaria;
    }
    public boolean isEmcartaz(){
        return emCartaz;
    }
    public void setEmCartaz(boolean emCartaz){
        this.emCartaz = emCartaz;
    }
    @Override
    public String toString(){
        return "Filme: " + nome + " Gênero: " + genero + "Duração" + duracaoMinutos + "min" "Classificação: " + classificacaoEtaria;
    }
}
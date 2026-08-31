package model;
import java.io.Serializable;
public class Filme implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String genero;
    private int duracaoMinutos;
    private String classificacaoEtaria;
    private boolean emCartaz;

    public Filme(String nome, String genero, int duracaoMinutos, String classificacaoEtaria) {
        this.nome = nome;
        this.genero = genero;
        this.duracaoMinutos = duracaoMinutos;
        this.classificacaoEtaria = classificacaoEtaria;
        this.emCartaz = true;
    }
    public boolean isEmCartaz() {
        return emCartaz;
    }

    public void setEmCartaz(boolean emCartaz) {
        this.emCartaz = emCartaz;
    }
    public String getNome() { return nome; }
    public String getGenero() { return genero; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public String getClassificacaoEtaria() { return classificacaoEtaria; }
}

package model;
import java.io.Serializable;
public class Sala implements Serializable{
    private int numeroSala;
    private int quantidadeFileiras;
    private int assentosPorFileira;
    private int totalAssentos;
    public Sala(int numeroSala, int quantidadeFileiras, int assentosPorFileira){
        this.numeroSala = numeroSala;
        this.quantidadeFileiras = quantidadeFileiras;
        this.assentosPorFileira = assentosPorFileira;
    }
    public int getNumeroSala(){
        return numeroSala;
    }
    public void setNumeroSala(int numeroSala){
        this.numeroSala = numeroSala;
    }
    public int getQuantidadeFileiras(){
        return quantidadeFileiras;
    }
    public void setQuantidadeFileiras(int quantidadeFileiras){
        this.quantidadeFileiras = quantidadeFileiras;
    }
    public int getAssentosPorFileira(){
        return assentosPorFileira;
    }
    public void setAssentosPorFileira(int assentosPorFileira){
        this.assentosPorFileira = assentosPorFileira;
    }
    public int getTotalAssentos(){
        return quantidadeFileiras * assentosPorFileira;
    }
    @Override
    public String toString(){
        return "Sala: " + numeroSala + "|" + quantidadeFileiras + "Fileiras: " + assentosPorFileira + "assentos/fileira";
    }
}
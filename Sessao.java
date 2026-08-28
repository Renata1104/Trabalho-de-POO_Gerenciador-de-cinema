package model;
import java.io.Serializable;
import java.HashMap;
import java.Map;
public class Sessao implements Serializable{
    private Filme filme;
    private Sala sala;
    private String dataHorario;
    private double valorIngresso;
    private Map<String, Boolean> mapaAssentos;
    public Sessao (Filme filme, Sala sala, String dataHorario, double valorIngresso){
        this.filme = filme;
        this.sala = sala;
        this.dataHorario = dataHorario;
        this.valorIngresso = valorIngresso;
        this.mapaAssentos = new HashMap<>();
        inicializarAssentos();
    }
    private void inicializarAssentos(){
        char letraFileira = 'A';
        for(int f = 0; f<sala.getQuantidadeFileiras(); f++){
            for(int a = 1; a <= sala.getAssentosPorFileira(); a++){
                String codigo = letraFileira + String.valueOf(a);
                mapaAssentos.put(codigo,false);
            }
            letraFileira++;
        }
    }
    public boolean reservarAssento(String codigoAssento){
        if(mapaAssentos.containsKey(codigoAssento)&& !mapaAssentos.get(codigoAssento)){
            mapaAssentos.put(codigoAssento, true);
            return true;
        }
        return false;
    }
    public Filme getFilme(){
        return filme;
    }
    publis Sala getSala(){
        return sala;
    }
    public String getDataHorario(){
        return dataHorario;
    }
    public double getValorIngresso(){
        return valorIngresso;
    }
    public Map<String, Boolean> getMapaAssentos(){
        return mapaAssentos;
    }
    @Override
    public String toString(){
        return "Sessão: " + filme.getNome() + "Sala: " + sala.getNumeroSala() + "Data/Horário: " + dataHorario + "Valor: R$" + String.format("%.2f",valorIngresso);
    }
}
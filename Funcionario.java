package model;
import java.io.Serializable;
public class Funcionario extends Pessoa implements Serializable{
    private String funcao;
    private String turno;
    public Funcionario(String nome, String id, String funcao, String turno){
        super(nome, id);
        this.funcao = funcao;
        this.turno = turno;
    }
    public String getFuncao(){
        return funcao;
    }
    public void setFuncao(String funcao){
        this.funcao = funcao;
    }
    public String getTurno(){
        return turno;
    }
    public void setTurno(String turno){
        this.turno = turno;
    }
    @Override
    public String toString(){
        return "ID: " + getId() + "Nome: " + getNome() + "Função: " + funcao + "Turno: " + turno;
    }
}
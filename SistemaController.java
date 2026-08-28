package controller;
import model.*;
import java.io.*;
import java.util.*;
public class SistemaController {
    private Set<Filme> filmes;
    private Set<Sala> salas;
    private Set<Funcionario> funcionarios;
    private Set<Produto> produtos;
    private List<Sessao> sessoes;
    private List<Venda> vendas;
    public SistemaController() {
        filmes = new LinkedHashSet<>();
        salas = new LinkedHashSet<>();
        funcionarios = new LinkedHashSet<>();
        produtos = new LinkedHashSet<>();
        sessoes = new ArrayList<>();
        vendas = new ArrayList<>();
        carregarDados();
    }
    public boolean cadastrarFilme(Filme f){
        return filmes.add(f); 
    }
    public boolean cadastrarSala(Sala s){
        return salas.add(s);
    }
    public boolean cadastrarFuncionario(Funcionario f){
        for (Funcionario func : funcionarios){
            if (func.getId().equals(f.getId())) 
            return false;
        }
        return funcionarios.add(f);
    }
    public boolean cadastrarProduto(Produto p){ 
        return produtos.add(p);
    }
    public boolean cadastrarSessao(Sessao s){
        for (Sessao existente : sessoes){
            if (existente.getSala().getNumeroSala() == s.getSala().getNumeroSala() && existente.getDataHorario().equals(s.getDataHorario())){
                return false;
            }
        }
        return sessoes.add(s);
    }
    public Venda realizarVenda(Sessao s, List<String> assentos, List<Produto> prods, String data, Funcionario func){
        for (String assento : assentos){
            if (!s.reservarAssento(assento)) 
            return null;
        }
        Venda novaVenda = new Venda(s, assentos, prods, data, func);
        vendas.add(novaVenda);
        return novaVenda;
    }
    public List<Filme> getFilmesEmCartaz(){
        List<Filme> lista = new ArrayList<>();
        for (Filme f : filmes) 
            if (f.isEmCartaz()) lista.add(f);
            return lista;
    }
    public void gerarRelatorio(String caminho){
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminho))){
            pw.println("Relatório geral do cinema");
            pw.println("Filmes em cartaz: " + getFilmesEmCartaz().size());
            for (Filme f : getFilmesEmCartaz()) pw.println("- " + f.getNome());
            pw.println("\nTotal de vendas realizadas: " + vendas.size());
            double faturamento = 0;
            for (Venda v : vendas) faturamento += v.getValorTotal();
            pw.println("Faturamento total: R$" + String.format("%.2f", faturamento));
            pw.println("\nData do relatório: " + new Date());
        } catch (IOException e){
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }
    public void salvarDados(){
        salvarArquivoBinario("filmes.dat", filmes);
        salvarArquivoBinario("salas.dat", salas);
        salvarArquivoBinario("funcionarios.dat", funcionarios);
        salvarArquivoTexto("sessoes.txt", sessoes);
        salvarArquivoTexto("vendas.txt", vendas);
    }
    @SuppressWarnings("unchecked")
    public void carregarDados() {
        filmes = (Set<Filme>) carregarArquivoBinario("filmes.dat");
        salas = (Set<Sala>) carregarArquivoBinario("salas.dat");
        funcionarios = (Set<Funcionario>) carregarArquivoBinario("funcionarios.dat");
        if (filmes == null) filmes = new LinkedHashSet<>();
        if (salas == null) salas = new LinkedHashSet<>();
        if (funcionarios == null) funcionarios = new LinkedHashSet<>();
    }
    private void salvarArquivoBinario(String caminho, Object obj){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))){
            oos.writeObject(obj);
        } catch (IOException e){}
    }
    private Object carregarArquivoBinario(String caminho){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))){
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e){ return null; }
    }
    private void salvarArquivoTexto(String caminho, List<?> lista){
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminho))){
            for (Object item : lista) pw.println(item.toString());
        } catch (IOException e){}
    }
    public Set<Filme> getFilmes(){ return filmes; }
    public Set<Sala> getSalas(){ return salas; }
    public Set<Funcionario> getFuncionarios(){ return funcionarios; }
    public Set<Produto> getProdutos(){ return produtos; }
    public List<Sessao> getSessoes(){ return sessoes; }
    public List<Venda> getVendas(){ return vendas; }
}

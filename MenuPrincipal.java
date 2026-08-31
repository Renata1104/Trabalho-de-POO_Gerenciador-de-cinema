package view;
import controller.SistemaController;
import model.*;
import java.util.*;
public class MenuPrincipal {
    private Scanner entrada;
    private SistemaController controle;
    public MenuPrincipal(){
        entrada = new Scanner(System.in);
        controle = new SistemaController();
    }
    public void exibirMenu(){
        int opcao;
        do {
            System.out.println("\n Sistema de Cinema");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Cadastrar Sala");
            System.out.println("3. Cadastrar Funcionário");
            System.out.println("4. Cadastrar Produto");
            System.out.println("5. Criar Sessão");
            System.out.println("6. Realizar Venda");
            System.out.println("7. Exibir Filmes em Cartaz");
            System.out.println("8. Gerar Relatório");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao){
                case 1: 
                cadastrarFilme(); 
                break;
                case 2: 
                cadastrarSala(); 
                break;
                case 3: 
                cadastrarFuncionario(); 
                break;
                case 4: 
                cadastrarProduto(); 
                break;
                case 5: 
                cadastrarSessao(); 
                break;
                case 6: 
                realizarVenda(); 
                break;
                case 7: 
                exibirFilmesCartaz(); 
                break;
                case 8: 
                controle.gerarRelatorio("relatorio_cinema.txt"); 
                System.out.println("Relatório gerado!"); 
                break;
                case 9: 
                controle.salvarDados();
                System.out.println("Dados salvos. Saindo..."); 
                break;
                default:
                System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }
    private void cadastrarFilme(){
        System.out.print("Nome do filme: "); 
        String nome = entrada.nextLine();
        System.out.print("Gênero: "); 
        String gen = entrada.nextLine();
        System.out.print("Duração (min): "); 
        int dur = entrada.nextInt(); 
        entrada.nextLine();
        System.out.print("Classificação etária: ");
        String clas = entrada.nextLine();
        if (controle.cadastrarFilme(new Filme(nome, gen, dur, clas))) System.out.println("Filme cadastrado!");
        else System.out.println("Erro ao cadastrar.");
    }
    private void cadastrarSala(){
        System.out.print("Número da sala: "); 
        int num = entrada.nextInt();
        System.out.print("Quantidade de fileiras: "); 
        int file = entrada.nextInt();
        System.out.print("Assentos por fileira: "); 
        int ass = entrada.nextInt(); entrada.nextLine();
        if (controle.cadastrarSala(new Sala(num, file, ass))) System.out.println("Sala cadastrada!");
        else System.out.println("Erro ao cadastrar.");
    }
    private void cadastrarFuncionario(){
        System.out.print("Nome: "); 
        String nome = entrada.nextLine();
        System.out.print("ID: "); 
        String id = entrada.nextLine();
        System.out.print("Função: "); 
        String func = entrada.nextLine();
        System.out.print("Turno (manhã/tarde/noite): "); 
        String turn = entrada.nextLine();
        if (controle.cadastrarFuncionario(new Funcionario(nome, id, func, turn))) System.out.println("Funcionário cadastrado!");
        else System.out.println("ID já existente!");
    }
    private void cadastrarProduto(){
        System.out.print("Nome do produto: "); 
        String nome = entrada.nextLine();
        System.out.print("Categoria: "); 
        String cat = entrada.nextLine();
        System.out.print("Preço unitário: "); 
        double preco = entrada.nextDouble(); 
        entrada.nextLine();
        if (controle.cadastrarProduto(new Produto(nome, cat, preco))) System.out.println("Produto cadastrado!");
        else System.out.println("Erro ao cadastrar.");
    }
    private void cadastrarSessao() {
        if (controle.getFilmes().isEmpty() || controle.getSalas().isEmpty()){
            System.out.println("Cadastre filmes e salas primeiro!"); 
            return;
        }
        System.out.println("Filmes disponíveis:");
        for (Filme f : controle.getFilmes()) System.out.println("- " + f.getNome());
        System.out.print("Nome do filme para sessão: "); 
        String nomeFilme = entrada.nextLine();
        Filme filmeEscolhido = null;
        for (Filme f : controle.getFilmes()) if (f.getNome().equalsIgnoreCase(nomeFilme)) filmeEscolhido = f;
        if (filmeEscolhido == null) {
            System.out.println("Filme não encontrado!"); 
            return; 
        }
        System.out.println("Salas disponíveis:");
        for (Sala s : controle.getSalas()) System.out.println("- Sala " + s.getNumeroSala());
        System.out.print("Número da sala: "); 
        int numSala = entrada.nextInt(); 
        entrada.nextLine();
        Sala salaEscolhida = null;
        for (Sala s : controle.getSalas()) 
            if (s.getNumeroSala() == numSala) salaEscolhida = s;
            if (salaEscolhida == null) { System.out.println("Sala não encontrada!");
            return;
        }
        System.out.print("Data e horário (ex: 10/07/2026 19:00): "); 
        String data = entrada.nextLine();
        System.out.print("Valor do ingresso: R$"); double valor = entrada.nextDouble();
        entrada.nextLine();
        if (controle.cadastrarSessao(new Sessao(filmeEscolhido, salaEscolhida, data, valor))) System.out.println("Sessão criada!");
        else System.out.println("Já existe sessão nesta sala e horário!");
    }
    private void realizarVenda(){
        if (controle.getSessoes().isEmpty() || controle.getFuncionarios().isEmpty()){
            System.out.println("Cadastre sessões e funcionários primeiro!");
            return;
        }
        System.out.println("Sessões disponíveis:");
        for (int i = 0; i < controle.getSessoes().size(); i++) System.out.println((i+1) + ". " + controle.getSessoes().get(i));
        System.out.print("Número da sessão: "); 
        int idx = entrada.nextInt() - 1; 
        entrada.nextLine();
        Sessao sessaoEscolhida = controle.getSessoes().get(idx);
        System.out.print("Códigos dos assentos (ex: A1 A2 B3): "); 
        String codigos = entrada.nextLine();
        List<String> assentos = Arrays.asList(codigos.split(" "));
        List<Produto> produtosEscolhidos = new ArrayList<>();
        if (!controle.getProdutos().isEmpty()){
            System.out.println("Produtos disponíveis:");
            for (Produto p : controle.getProdutos()) System.out.println("- " + p);
            System.out.print("Deseja comprar produtos? (s/n): ");
            if (entrada.nextLine().equalsIgnoreCase("s")){
                System.out.print("Nomes dos produtos: "); 
                String nomes = entrada.nextLine();
                for (String n : nomes.split(" ")){
                    for (Produto p : controle.getProdutos()) if (p.getNome().equalsIgnoreCase(n)) produtosEscolhidos.add(p);
                }
            }
        }
        System.out.print("ID do funcionário responsável: "); 
        String idFunc = entrada.nextLine();
        Funcionario funcResp = null;
        for (Funcionario f : controle.getFuncionarios()) if (f.getId().equals(idFunc)) funcResp = f;
        if (funcResp == null){
            System.out.println("Funcionário não encontrado!"); 
            return; 
        }
        Venda nova = controle.realizarVenda(sessaoEscolhida, assentos, produtosEscolhidos, new Date().toString(), funcResp);
        if (nova != null) System.out.println("Venda realizada! Total: R$" + String.format("%.2f", nova.getValorTotal()));
        else System.out.println("Um ou mais assentos já estão ocupados!");
    }
    private void exibirFilmesCartaz() {
        System.out.println("\n Filmes em Cartaz");
        for (Filme f : controle.getFilmesEmCartaz()) System.out.println(f);
    }
}
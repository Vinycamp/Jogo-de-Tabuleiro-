import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        new Clear();
        System.out.println("Bem Vindo ao melhor jogo de tabuleiro do mundo todinho!\n");
        do {
            System.out.println("Escolha uma das opcoes abaixo:");
            System.out.println("1 - Jogar     2 - Debug     3 - Sair\n");
            opcao = scanner.nextLine();
            ArrayList<Jogador> jogadores;
            Jogo jogo;
            switch (opcao) {
                case "1":
                    jogadores = pedirInformacoesDosJogadores();
                    jogo = new Jogo(jogadores);
                    jogo.jogar(false);
                    new Clear();
                    break;
                case "2":
                    jogadores = pedirInformacoesDosJogadores();
                    jogo = new Jogo(jogadores);
                    jogo.jogar(true);
                    new Clear();
                    break;
                case "3":
                    new Clear();
                    System.out.println("Saindo...");
                    break;
                default:
                    new Clear();
                    System.out.println("Voce digitou uma opcao incorreta. Tente Novamente!\n");
            }
        } while (!opcao.equals("3"));
    }

    public static ArrayList<Jogador> pedirInformacoesDosJogadores() {
        Scanner scanner = new Scanner(System.in);

        // Pedir quantidade de jogadores
        String stringNumeroDeJogadores;
        ArrayList<String> opcoesValidas = new ArrayList<>();
        opcoesValidas.add("2");
        opcoesValidas.add("3");
        opcoesValidas.add("4");
        opcoesValidas.add("5");
        opcoesValidas.add("6");
        new Clear();
        do {
            System.out.println("Quantos jogadores irão jogar?\n");
            stringNumeroDeJogadores = scanner.nextLine();
            new Clear();
            if (!opcoesValidas.contains(stringNumeroDeJogadores)) {
                System.out.println("Você digitou uma quantidade inválida. Digite um número entre 2 e 6.\n");
            }
        } while (!opcoesValidas.contains(stringNumeroDeJogadores));
        boolean jaEscolheu = false;
        boolean sairDoLoop;
        ArrayList<String> tiposDosJogadores = new ArrayList<>();

        int numeroDeJogadores = Integer.parseInt(stringNumeroDeJogadores);
        do {
            sairDoLoop = false;
            // Pedir tipo de cada jogador
            opcoesValidas.clear();
            opcoesValidas.add("1");
            opcoesValidas.add("2");
            opcoesValidas.add("3");
            String tipoDoJogador;
            for (int i = 0; i < numeroDeJogadores; i++) {
                new Clear();
                do {
                    if (jaEscolheu) {
                        System.out.println("Você está alterando o tipo dos jogadores.\n");
                    }
                    System.out.println("Selecione o tipo do jogador " + (i + 1) + ":\n");
                    System.out.println("1 - Jogador Normal     2 - Jogador com Sorte");
                    System.out.println("3 - Jogador Azarado\n");
                    if (jaEscolheu) { // quando não for a primeira vez escolhendo, dar a opção do usuário
                        // apenas apertar Enter para não alterar
                        if (tiposDosJogadores.get(i).equals("1")) {
                            System.out.println("Pressione Enter para o jogador " + (i + 1)
                                    + " continuar sendo Jogador Normal");
                        }
                        if (tiposDosJogadores.get(i).equals("2")) {
                            System.out.println("Pressione Enter para o jogador " + (i + 1)
                                    + " continuar sendo Jogador com Sorte");
                        }
                        if (tiposDosJogadores.get(i).equals("3")) {
                            System.out.println("Pressione Enter para o jogador " + (i + 1)
                                    + " continuar sendo Jogador Azarado");
                        }
                    }
                    System.out.println("");
                    tipoDoJogador = scanner.nextLine();
                    new Clear();
                    if (!(jaEscolheu && tipoDoJogador.isEmpty())) { // verificar se o usuário digitou uma
                        // opção válida
                        if (!opcoesValidas.contains(tipoDoJogador)) {
                            System.out.println("Você digitou uma opção incorreta. Tente novamente!\n");
                        } else {
                            if (!jaEscolheu) {
                                tiposDosJogadores.add(tipoDoJogador);
                            } else {
                                tiposDosJogadores.set(i, tipoDoJogador);
                            }
                        }
                    } else {
                        break;
                    }
                } while (!opcoesValidas.contains(tipoDoJogador));
            }
            jaEscolheu = true;

            // Mostrar jogadores e verificar se não são todos iguais
            opcoesValidas.clear();
            opcoesValidas.add("s");
            opcoesValidas.add("n");
            String userInput;
            do {
                for (int i = 0; i < numeroDeJogadores; i++) {
                    if (tiposDosJogadores.get(i).equals("1")) {
                        System.out.println("Jogador " + (i + 1) + ": Normal");
                    }
                    if (tiposDosJogadores.get(i).equals("2")) {
                        System.out.println("Jogador " + (i + 1) + ": Com Sorte");
                    }
                    if (tiposDosJogadores.get(i).equals("3")) {
                        System.out.println("Jogador " + (i + 1) + ": Azarado");
                    }
                }
                System.out.println("\nDeseja continuar?");
                System.out.println("s - sim     n - não\n");
                userInput = scanner.nextLine().toLowerCase();
                new Clear();
                switch (userInput) {
                    case "s":
                        sairDoLoop = true;
                        boolean todosIguais = true;
                        String primeiroJogador = tiposDosJogadores.getFirst();
                        for (int i = 0; i < numeroDeJogadores; i++) {
                            if (!primeiroJogador.equals(tiposDosJogadores.get(i))) {
                                todosIguais = false;
                                break;
                            }
                        }
                        if (todosIguais) {
                            new Clear();
                            System.out.println("Deve haver pelo menos dois jogadores diferentes.");
                            System.out.println("\nPressione Enter para continuar.");
                            scanner.nextLine();
                            sairDoLoop = false;
                        }
                        break;
                    case "n":
                        break;
                    default:
                        System.out.println("Você digitou uma opção incorreta. Tente novamente!\n");
                        break;
                }
            } while (!opcoesValidas.contains(userInput));
        } while (!sairDoLoop);

        // Instanciar os jogadores
        ArrayList<Jogador> jogadores = new ArrayList<>();
        for (int i = 0; i < numeroDeJogadores; i++) {
            if (tiposDosJogadores.get(i).equals("1")) {
                Jogador j = new JogadorNormal("azul");
                jogadores.add(j);
            }
            if (tiposDosJogadores.get(i).equals("2")) {
                Jogador j = new JogadorComSorte("azul");
                jogadores.add(j);
            }
            if (tiposDosJogadores.get(i).equals("3")) {
                Jogador j = new JogadorAzarado("azul");
                jogadores.add(j);
            }
        }

        jaEscolheu = false;
        do {
            sairDoLoop = false;
            // Decidir as cores dos jogadores
            opcoesValidas.clear();
            opcoesValidas.add("azul");
            opcoesValidas.add("verde");
            opcoesValidas.add("roxo");
            opcoesValidas.add("amarelo");
            opcoesValidas.add("vermelho");
            opcoesValidas.add("cinza");
            for (int i = 0; i < numeroDeJogadores; i++) {
                new Clear();
                String corDoJogador;
                do {
                    if (jaEscolheu) {
                        System.out.println("Você está alterando a cor dos jogadores.\n");
                    }
                    System.out.println("Selecione a cor do jogador " + (i + 1) + ":");
                    System.out.println("Azul, Verde, Roxo, Amarelo, Vermelho, Cinza");
                    if (jaEscolheu) { // quando não for a primeira vez escolhendo, dar a opção do usuário
                        // apenas apertar Enter para não alterar
                        System.out.println("\nPressione Enter para o jogador " + (i + 1)
                                + " continuar tendo a cor " + jogadores.get(i).getCor());
                    }
                    System.out.println("");
                    corDoJogador = scanner.nextLine().toLowerCase();
                    new Clear();
                    if (!(jaEscolheu && corDoJogador.isEmpty())) { // verificar se o usuário digitou uma
                        // opção válida
                        if (!opcoesValidas.contains(corDoJogador)) {
                            System.out.println("Você digitou uma opção incorreta. Tente novamente!\n");
                        } else {
                            jogadores.get(i).setCor(corDoJogador);
                        }
                    } else {
                        break;
                    }
                } while (!opcoesValidas.contains(corDoJogador));
            }
            jaEscolheu = true;

            // Mostrar jogadores e verificar se todos possuem cores diferentes
            opcoesValidas.clear();
            opcoesValidas.add("s");
            opcoesValidas.add("n");
            String userInput;
            do {
                for (int i = 0; i < numeroDeJogadores; i++) {
                    System.out.println("Jogador " + (i + 1) + ": " + jogadores.get(i).getCor());
                }
                System.out.println("\nDeseja continuar?");
                System.out.println("s - sim     n - não\n");
                userInput = scanner.nextLine().toLowerCase();
                new Clear();
                switch (userInput) {
                    case "s":
                        sairDoLoop = true;
                        boolean todosDiferentes = true;
                        for (int i = 0; i < numeroDeJogadores; i++) {
                            for (int j = i + 1; j < numeroDeJogadores; j++) {
                                if (Objects.equals(jogadores.get(i).getCor(), jogadores.get(j).getCor())) {
                                    todosDiferentes = false;
                                    break;
                                }
                            }
                        }
                        if (!todosDiferentes) {
                            new Clear();
                            System.out.println("Todos os jogadores devem ter cores diferentes.");
                            System.out.println("\nPressione Enter para continuar.");
                            scanner.nextLine();
                            sairDoLoop = false;
                        }
                        break;
                    case "n":
                        break;
                    default:
                        System.out.println("Você digitou uma opção incorreta. Tente novamente!\n");
                        break;
                }
            } while (!opcoesValidas.contains(userInput));
        } while (!sairDoLoop);
        return jogadores;
    }
}
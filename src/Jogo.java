import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Jogo {
    private ArrayList<Jogador> jogadores;
    private int numeroDeJogadores;

    public Jogo(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.numeroDeJogadores = jogadores.size();
    }

    public void jogar(boolean debug) {
        Scanner scanner = new Scanner(System.in);
        int turno = 0;
        int jogador = 0;
        boolean formatoCorreto = false;
        ArrayList<Integer> posicoesEspeciais = new ArrayList<>();
        ArrayList<Integer> dados = new ArrayList<>();
        dados.add(0);
        dados.add(1);
        new Clear();
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println("O jogo está começando!\n");
        do {
            if (this.jogadores.get(jogador).getJogaProximoTurno()) { // jogador se move
                // início do turno
                System.out.println("Turno: " + (turno + 1) + "\n");
                tabuleiro.mostrarTabuleiro(this.jogadores);
                System.out.println("\nÉ a vez do jogador: " + this.jogadores.get(jogador).getCor());
                System.out.println("Pressione Enter para jogar os dados.\n");
                if (debug) {
                    System.out.println("Digite a cor do jogador e uma casa para mudar sua posição.");
                    System.out.println("Exemplo: azul 30\n");
                    String userInput = scanner.nextLine().toLowerCase();
                    String[] partes = userInput.split(" ");
                    formatoCorreto = true;
                    if (!(partes.length == 2)) {
                        formatoCorreto = false;
                    }
                    if (formatoCorreto) {
                        ArrayList<String> opcoesValidas = new ArrayList<>();
                        opcoesValidas.add("azul");
                        opcoesValidas.add("verde");
                        opcoesValidas.add("roxo");
                        opcoesValidas.add("amarelo");
                        opcoesValidas.add("vermelho");
                        opcoesValidas.add("cinza");
                        if (!opcoesValidas.contains(partes[0])) {
                            formatoCorreto = false;
                        }
                        opcoesValidas.clear();
                        for (int i = 0; i < 40; i++) {
                            opcoesValidas.add("" + (i + 1));
                        }
                        if (!opcoesValidas.contains(partes[1])) {
                            formatoCorreto = false;
                        }
                    }
                    if (formatoCorreto) {
                        for (int i = 0; i < numeroDeJogadores; i++) {
                            if (Objects.equals(jogadores.get(i).getCor(), partes[0])) {
                                jogador = i;
                                jogadores.get(i).setPosicao(Integer.parseInt(partes[1]) - 1);
                                this.jogadores.get(jogador).aumentarQuantidadeDeJogadas();
                                break;
                            }
                        }
                    } else {
                        // movimentar jogador
                        dados = this.jogadores.get(jogador).jogarDados();
                        this.jogadores.get(jogador).aumentarQuantidadeDeJogadas();

                        // mostrar dados
                        new Clear();
                        System.out.println("┌───┐   ┌───┐");
                        System.out.println("│ " + dados.get(0) + " │   │ " + dados.get(1) + " │");
                        System.out.println("└───┘   └───┘\n");
                        System.out.println("Soma dos dados: " + (dados.get(0) + dados.get(1)));
                        System.out.println("Você foi para a casa: " + (jogadores.get(jogador).getPosicao() + 1) + "\n");
                        scanner.nextLine();
                    }
                    formatoCorreto = false;
                } else {
                    scanner.nextLine();

                    // movimentar jogador
                    dados = this.jogadores.get(jogador).jogarDados();
                    this.jogadores.get(jogador).aumentarQuantidadeDeJogadas();

                    // mostrar dados
                    new Clear();
                    System.out.println("┌───┐   ┌───┐");
                    System.out.println("│ " + dados.get(0) + " │   │ " + dados.get(1) + " │");
                    System.out.println("└───┘   └───┘\n");
                    System.out.println("Soma dos dados: " + (dados.get(0) + dados.get(1)));
                    if (jogadores.get(jogador).getPosicao() > 39) {
                        System.out.println("Você foi para a casa 40 e ainda ficou "
                                + (jogadores.get(jogador).getPosicao() - 39) + " de sobra.\n");
                    } else {
                        System.out.println("Você foi para a casa: " + (jogadores.get(jogador).getPosicao() + 1) + "\n");
                    }
                    scanner.nextLine();
                }

                posicoesEspeciais.clear();
                posicoesEspeciais.add(9); // casa 10
                posicoesEspeciais.add(24); // casa 25
                posicoesEspeciais.add(37); // casa 38
                if (posicoesEspeciais.contains(this.jogadores.get(jogador).getPosicao())) {
                    this.casaPresidio(jogador);
                    scanner.nextLine();
                }
                posicoesEspeciais.clear();
                posicoesEspeciais.add(12); // casa 13
                if (posicoesEspeciais.contains(this.jogadores.get(jogador).getPosicao())) {
                    this.casaSurpresa(jogador);
                    scanner.nextLine();
                }
                posicoesEspeciais.clear();
                posicoesEspeciais.add(4); // casa 5
                posicoesEspeciais.add(14); // casa 15
                posicoesEspeciais.add(29); // casa 30
                if (posicoesEspeciais.contains(this.jogadores.get(jogador).getPosicao())) {
                    this.casaDaSorte(jogador);
                    scanner.nextLine();
                }
                posicoesEspeciais.clear();
                posicoesEspeciais.add(16); // casa 17
                posicoesEspeciais.add(26); // casa 27
                if (posicoesEspeciais.contains(this.jogadores.get(jogador).getPosicao())) {
                    this.casaMalandra(jogador);
                    scanner.nextLine();
                }
                posicoesEspeciais.clear();
                posicoesEspeciais.add(19); // casa 20
                posicoesEspeciais.add(34); // casa 35
                if (posicoesEspeciais.contains(this.jogadores.get(jogador).getPosicao())) {
                    this.casaMagica(jogador);
                    scanner.nextLine();
                }
            } else { // jogador não se move
                this.jogadores.get(jogador).setJogaProximoTurno(true);
            }
            // fim do jogo
            if (this.jogadores.get(jogador).getPosicao() >= 39) {
                new Clear();
                tabuleiro.mostrarTabuleiro(jogadores);
                System.out.println("\nParabéns! " + this.jogadores.get(jogador).getCor() + " venceu!");
                for (int i = 0; i < numeroDeJogadores; i++) {
                    System.out.println(this.jogadores.get(i).getCor() + " fez "
                            + this.jogadores.get(i).getQuantidadeDeJogadas() + " jogadas.");
                }
                System.out.println("\nPressione Enter para voltar ao menu principal.");
                scanner.nextLine();
                break;
            }
            // passar a vez para o próximo jogador
            if (jogador != this.numeroDeJogadores - 1) {
                if (dados.get(0) != dados.get(1)) {
                    if (!(debug && formatoCorreto)) {
                        jogador++;
                    }
                } else { // o jogador joga novamente se tiver tirado dados iguais
                    new Clear();
                    if (this.jogadores.get(jogador).getJogaProximoTurno()) {
                        System.out.println("Você tirou dados iguais!");
                        System.out.println("Você jogará novamente.");
                    } else {
                        System.out.println("Você sairá do presídio por ter tirado dados iguais.");
                        System.out.println("Você jogará no próximo turno.");
                    }
                    scanner.nextLine();
                }
            } else {
                jogador = 0;
                turno++;
            }
            new Clear();
        } while (true);
    }

    public void casaPresidio(int jogador) {
        Tabuleiro tabuleiro = new Tabuleiro();
        this.jogadores.get(jogador).setJogaProximoTurno(false);
        new Clear();
        tabuleiro.mostrarTabuleiro(this.jogadores);
        System.out.println("\nVocê caiu no presídio!");
        System.out.println("Você não jogará no próximo turno.");
    }

    public void casaSurpresa(int jogador) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        ArrayList<String> opcoesValidas = new ArrayList<>();
        opcoesValidas.add("1");
        opcoesValidas.add("2");
        opcoesValidas.add("3");
        String carta;
        new Clear();
        tabuleiro.mostrarTabuleiro(this.jogadores);
        do {
            System.out.println("\nVocê caiu na casa surpresa!");
            System.out.println("Você escolherá uma carta que mudará a sua sorte!\n");
            System.out.println("Escolha uma carta:\n");
            System.out.println("┌─────┐ ┌─────┐ ┌─────┐");
            System.out.println("│     │ │     │ │     │");
            System.out.println("│  1  │ │  2  │ │  3  │");
            System.out.println("│     │ │     │ │     │");
            System.out.println("└─────┘ └─────┘ └─────┘\n");
            carta = scanner.nextLine();
            new Clear();
            if (!opcoesValidas.contains(carta)) {
                System.out.println("Você digitou uma opção incorreta. Tente novamente!\n");
            }
        } while (!opcoesValidas.contains(carta));
        int resultado = (int) (Math.random() * 3);
        String resultadoDaCarta = "";
        String tipoDoJogador = "";
        Jogador novoTipo = new JogadorNormal("");
        switch (resultado) {
            case 0:
                resultadoDaCarta = "Normal";
                tipoDoJogador = "normal";
                novoTipo = new JogadorNormal(this.jogadores.get(jogador).getCor(),
                        this.jogadores.get(jogador).getPosicao(),
                        this.jogadores.get(jogador).getQuantidadeDeJogadas());
                break;
            case 1:
                resultadoDaCarta = "Sorte";
                tipoDoJogador = "com sorte";
                novoTipo = new JogadorComSorte(this.jogadores.get(jogador).getCor(),
                        this.jogadores.get(jogador).getPosicao(),
                        this.jogadores.get(jogador).getQuantidadeDeJogadas());
                break;
            case 2:
                resultadoDaCarta = "Azar ";
                tipoDoJogador = "azarado";
                novoTipo = new JogadorAzarado(this.jogadores.get(jogador).getCor(),
                        this.jogadores.get(jogador).getPosicao(),
                        this.jogadores.get(jogador).getQuantidadeDeJogadas());
                break;
        }
        new Clear();
        System.out.println("┌─────┐ ┌─────┐ ┌─────┐");
        System.out.println("│     │ │     │ │     │");
        switch (carta) {
            case "1":
                System.out.println("│" + resultadoDaCarta + "│ │  2  │ │  3  │");
                break;
            case "2":
                System.out.println("│  1  │ │" + resultadoDaCarta + "│ │  3  │");
                break;
            case "3":
                System.out.println("│  1  │ │  2  │ │" + resultadoDaCarta + "│");
                break;
            default:
                break;
        }
        System.out.println("│     │ │     │ │     │");
        System.out.println("└─────┘ └─────┘ └─────┘\n");
        System.out.println("Agora você é um jogador " + tipoDoJogador + "!\n");
        this.jogadores.set(jogador, novoTipo);
    }

    public void casaDaSorte(int jogador) {
        Tabuleiro tabuleiro = new Tabuleiro();
        new Clear();
        tabuleiro.mostrarTabuleiro(this.jogadores);
        System.out.println("\nVocê caiu na casa da sorte!");
        if (this.jogadores.get(jogador) instanceof JogadorAzarado) {
            System.out.println("Porém, você não tem sorte :(");
            System.out.println("Você não ganhará o bônus de andar 3 casas.");
        } else {
            System.out.println("Você andará 3 casas YaY");
            this.jogadores.get(jogador).setPosicao(this.jogadores.get(jogador).getPosicao() + 3);
        }
    }

    public void casaMalandra(int jogador) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        new Clear();
        tabuleiro.mostrarTabuleiro(this.jogadores);
        ArrayList<String> opcoesValidas = new ArrayList<>();
        opcoesValidas.add("azul");
        opcoesValidas.add("verde");
        opcoesValidas.add("roxo");
        opcoesValidas.add("amarelo");
        opcoesValidas.add("vermelho");
        opcoesValidas.add("cinza");
        String buscarCor;
        boolean deuCerto = false;
        System.out.println("\nVocê caiu na casa malandra!\n");
        do {
            do {
                this.mostrarJogadores();
                System.out.println("\nEscolha alguém para voltar para o inicio do jogo.\n");
                buscarCor = scanner.nextLine().toLowerCase();
                new Clear();
                if (!opcoesValidas.contains(buscarCor)) {
                    System.out.println("Voce digitou uma opcao incorreta. Tente novamente!\n");
                }
            } while (!opcoesValidas.contains(buscarCor));

            for (int i = 0; i < this.numeroDeJogadores; i++) {
                if (Objects.equals(this.jogadores.get(i).getCor(), buscarCor)) {
                    this.jogadores.get(i).setPosicao(0);
                    System.out.println(
                            "Agora o jogador " + this.jogadores.get(i).getCor() + " voltou para o começo do jogo :)");
                    deuCerto = true;
                    break;
                }
            }
            if (!deuCerto) {
                System.out.println("Não existe jogador " + buscarCor + " nesta partida. Tente Novamente!\n");
            }
        } while (!deuCerto);
    }

    public void casaMagica(int jogador) {
        Tabuleiro tabuleiro = new Tabuleiro();
        new Clear();
        tabuleiro.mostrarTabuleiro(this.jogadores);
        System.out.println("\nVocê caiu na casa mágica!");
        int menorPosicao = 40;
        int indiceJogadorComMenorPosicao = 0;
        for (int i = 0; i < this.numeroDeJogadores; i++) {
            if (this.jogadores.get(i).getPosicao() < menorPosicao) {
                menorPosicao = this.jogadores.get(i).getPosicao();
                indiceJogadorComMenorPosicao = i;
            }
        }
        this.jogadores.get(indiceJogadorComMenorPosicao).setPosicao(this.jogadores.get(jogador).getPosicao());
        this.jogadores.get(jogador).setPosicao(menorPosicao);
        if (jogador != indiceJogadorComMenorPosicao) {
            System.out.println("Você trocará com o jogador que está mais atrás.");
            System.out.println(
                    "Que será o jogador " + this.jogadores.get(indiceJogadorComMenorPosicao).getCor());
        } else {
            System.out.println("Você é o último jogador :/ não será trocado.");
        }
    }

    public void mostrarJogadores() {
        for (int i = 0; i < this.numeroDeJogadores; i++) {
            System.out.println("Jogador " + this.jogadores.get(i).getCor() + " está na casa: "
                    + (this.jogadores.get(i).getPosicao() + 1));
        }
    }
}

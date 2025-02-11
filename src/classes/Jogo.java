package classes;

import casas.Casa;
import casas.FabricaCasa;
import jogadores.Jogador;
import jogadores.FabricaJogador;
import validadores.FabricaValidador;

import java.util.ArrayList;
import java.util.Objects;

public class Jogo {
    private Tabuleiro tabuleiro = Tabuleiro.getInstancia();
    private Screen screen = new Screen();
    private FabricaJogador fabricaJogador = new FabricaJogador();
    private FabricaValidador fabricaValidador = new FabricaValidador();
    private FabricaCasa fabricaCasa = new FabricaCasa();

    public void configTabuleiro(int numCasas) {
        screen.clear();
        String request = "Defina as casas do tabuleiro.\n\n";
        request += "Digite o número correspondente à opção que deseja.\n\n";
        request += "(1) Definir casas manualmente     (2) Definir casas aleatórias\n";
        String userInput = screen.input(request, fabricaValidador.criarIntegerValidador(1, 2));
        if (userInput.equals("1")) {
            ArrayList<Casa> casas = new ArrayList<>();
            for (int i = 0; i < numCasas; i++) {
                casas.add(fabricaCasa.criarCasaNormal());
            }
            screen.clear();
            do {
                request = "Digite o número da casa que deseja adicionar um efeito.\n\n";
                request += "Digite 0 para sair.\n";
                userInput = screen.input(request, fabricaValidador.criarIntegerValidador(0, numCasas));
                if (userInput.equals("0")) {
                    break;
                }
                int numero = Integer.parseInt(userInput) - 1;
                screen.clear();
                request = "Digite o efeito que deseja adicionar à casa.\n\n";
                request += "(1) Casa da sorte   (2) Casa de troca   (3) Casa do azar\n";
                request += "(4) Casa mágica     (5) Casa malandra   (6) Casa presídio\n";
                request += "(7) Casa surpresa\n";
                userInput = screen.input(request, fabricaValidador.criarIntegerValidador(0, 7));
                if (userInput.equals("0")) {
                    break;
                }
                switch (userInput) {
                    case "1":
                        casas.set(numero, fabricaCasa.criarCasaDaSorte());
                        break;
                    case "2":
                        casas.set(numero, fabricaCasa.criarCasaDeTroca());
                        break;
                    case "3":
                        casas.set(numero, fabricaCasa.criarCasaDoAzar());
                        break;
                    case "4":
                        casas.set(numero, fabricaCasa.criarCasaMagica());
                        break;
                    case "5":
                        casas.set(numero, fabricaCasa.criarCasaMalandra());
                        break;
                    case "6":
                        casas.set(numero, fabricaCasa.criarCasaPresidio());
                        break;
                    case "7":
                        casas.set(numero, fabricaCasa.criarCasaSurpresa());
                        break;
                }
                screen.clear();
            } while(true);
            this.tabuleiro.configCasas(casas);
        } else {
            this.tabuleiro.criarCasasAleatorias(numCasas);
        }
    }

    public void configJogadores(int numJogadores) {
        String request = "";

        // Pedir tipo de cada jogador
        ArrayList<String> tiposDosJogadores = new ArrayList<>();
        boolean estaAlterando = false;
        do {
            screen.clear();
            for (int i = 0; i < numJogadores; i++) {
                if (estaAlterando) {
                    // quando não for a primeira vez escolhendo, dar a opção do usuário apenas
                    // apertar Enter para não alterar
                    request = "Você está alterando o tipo dos jogadores.\n\n";
                    request += "Selecione o tipo do jogador " + (i + 1) + ":\n\n";
                    request += "1 - Jogador Normal     2 - Jogador com Sorte\n";
                    request += "3 - Jogador Azarado\n\n";
                    if (tiposDosJogadores.get(i).equals("1")) {
                        request += "Pressione Enter para o jogador " + (i + 1) + " continuar sendo Jogador Normal\n";
                    }
                    if (tiposDosJogadores.get(i).equals("2")) {
                        request += "Pressione Enter para o jogador " + (i + 1) + " continuar sendo Jogador com Sorte\n";
                    }
                    if (tiposDosJogadores.get(i).equals("3")) {
                        request += "Pressione Enter para o jogador " + (i + 1) + " continuar sendo Jogador Azarado\n";
                    }
                    String tipoDoJogador = screen.input(request, fabricaValidador.criarIntegerValidador(1, 3, true));
                    if (tipoDoJogador != "") {
                        tiposDosJogadores.set(i, tipoDoJogador);
                    }
                } else {
                    request = "Selecione o tipo do jogador " + (i + 1) + ":\n\n";
                    request += "1 - Jogador Normal     2 - Jogador com Sorte\n";
                    request += "3 - Jogador Azarado\n";
                    String tipoDoJogador = screen.input(request, fabricaValidador.criarIntegerValidador(1, 3));
                    tiposDosJogadores.add(tipoDoJogador);
                }
                screen.clear();
            }

            // Mostrar jogadores e verificar se não são todos iguais
            screen.clear();
            request = "";
            for (int i = 0; i < numJogadores; i++) {
                if (tiposDosJogadores.get(i).equals("1")) {
                    request += "Jogador " + (i + 1) + ": Normal\n";
                }
                if (tiposDosJogadores.get(i).equals("2")) {
                    request += "Jogador " + (i + 1) + ": Com Sorte\n";
                }
                if (tiposDosJogadores.get(i).equals("3")) {
                    request += "Jogador " + (i + 1) + ": Azarado\n";
                }
            }
            request += "\nDeseja continuar?\n";
            request += "s - sim     n - não\n";
            String userInput = screen.input(request, fabricaValidador.criarBooleanValidador("s", "n"));
            if (userInput.equals("s")) {
                boolean todosIguais = true;
                String tipoPrimeiroJogador = tiposDosJogadores.get(0);
                for (int i = 0; i < numJogadores; i++) {
                    if (!tipoPrimeiroJogador.equals(tiposDosJogadores.get(i))) {
                        todosIguais = false;
                        break;
                    }
                }
                if (todosIguais) {
                    screen.clear();
                    request = "Deve haver pelo menos dois jogadores diferentes.\n";
                    request += "Pressione Enter para continuar.\n";
                    screen.input(request);
                } else {
                    break;
                }
            }
            estaAlterando = true;
        } while (true);

        // Instanciar os jogadores
        ArrayList<Jogador> jogadores = new ArrayList<>();
        for (int i = 0; i < numJogadores; i++) {
            if (tiposDosJogadores.get(i).equals("1")) {
                Jogador j = fabricaJogador.criarJogadorNormal("");
                jogadores.add(j);
            }
            if (tiposDosJogadores.get(i).equals("2")) {
                Jogador j = fabricaJogador.criarJogadorComSorte("");
                jogadores.add(j);
            }
            if (tiposDosJogadores.get(i).equals("3")) {
                Jogador j = fabricaJogador.criarJogadorAzarado("");
                jogadores.add(j);
            }
        }

        // Pedir as cores dos jogadores
        estaAlterando = false;
        do {
            String corDoJogador;
            screen.clear();
            for (int i = 0; i < numJogadores; i++) {
                if (estaAlterando) {
                    // quando não for a primeira vez escolhendo, dar a opção do usuário apenas
                    // apertar Enter para não alterar
                    request = "Você está alterando a cor dos jogadores.\n\n";
                    request += "Selecione a cor do jogador " + (i + 1) + ":\n";
                    request += "Azul, Verde, Roxo, Amarelo, Vermelho, Cinza\n";
                    request += "\n";
                    request += "Pressione Enter para o jogador " + (i + 1) + " continuar tendo a cor "
                            + jogadores.get(i).getCor() + "\n";
                    corDoJogador = screen.input(request, fabricaValidador.criarCoresValidador(true));
                    if (corDoJogador != "") {
                        jogadores.get(i).setCor(corDoJogador);
                    }
                } else {
                    request = "Selecione a cor do jogador " + (i + 1) + ":\n";
                    request += "Azul, Verde, Roxo, Amarelo, Vermelho, Cinza\n";
                    corDoJogador = screen.input(request, fabricaValidador.criarCoresValidador());
                    jogadores.get(i).setCor(corDoJogador);
                }
                screen.clear();
            }

            // Mostrar jogadores e verificar se todos possuem cores diferentes
            request = "";
            for (int i = 0; i < numJogadores; i++) {
                request += "Jogador " + (i + 1) + ": " + jogadores.get(i).getCor() + "\n";
            }
            request += "\n";
            request += "Deseja continuar?\n";
            request += "s - sim     n - não\n";
            String userInput = screen.input(request, fabricaValidador.criarBooleanValidador("s", "n"));
            if (userInput.equals("s")) {
                boolean todosDiferentes = true;
                for (int i = 0; i < numJogadores; i++) {
                    for (int j = i + 1; j < numJogadores; j++) {
                        if (jogadores.get(i).getCor().equals(jogadores.get(j).getCor())) {
                            todosDiferentes = false;
                            break;
                        }
                    }
                }
                if (!todosDiferentes) {
                    screen.clear();
                    request = "Todos os jogadores devem ter cores diferentes.\n";
                    request += "Pressione Enter para continuar.\n";
                    screen.input(request);
                } else {
                    break;
                }
            }
            estaAlterando = true;
        } while (true);

        this.tabuleiro.setJogadores(jogadores);
        this.tabuleiro.setNumJogadores(numJogadores);
    }

    public void start() {
        String request;
        String userInput;

        screen.clear();
        do {
            request = "Escolha uma das opcoes abaixo:\n";
            request += "1 - Jogar     2 - Debug     3 - Reset     0 - Sair\n";
            userInput = screen.input(request, fabricaValidador.criarIntegerValidador(1, 3));
            switch (userInput){
                case "1":
                    this.loop(false);
                    break;
                case "2":
                    this.loop(true);
                    screen.clear();
                    break;
                case "3":
                    // Pedir número de casas do tabuleiro
                    screen.clear();
                    System.out.println("Bem Vindo ao melhor jogo de tabuleiro do mundo todinho!\n");
                    int numCasas = Integer.parseInt(screen.input("Digite o número de casas do tabuleiro.\n", fabricaValidador.criarIntegerValidador()));

                    // Configurar tabuleiro
                    this.configTabuleiro(numCasas);

                    // Pedir número de jogadores
                    screen.clear();
                    int numJogadores = Integer.parseInt(screen.input("Digite o número de jogadores que irá jogar.\n", fabricaValidador.criarIntegerValidador()));

                    // Configurar jogadores
                    this.configJogadores(numJogadores);
                    break;
            }
            screen.clear();
        } while (!userInput.equals("0"));
        System.out.println("Saindo...");
    }

    public void loop(boolean debug) {
        String request = "";
        int turno = 0;
        int indexJogador = 0;
        boolean jogadaForcada = false;
        ArrayList<Integer> dados = new ArrayList<>();
        dados.add(0);
        dados.add(1);
        screen.clear();
        System.out.println("O jogo está começando!\n");
        do {
            Jogador jogador = this.tabuleiro.getJogadores().get(indexJogador);
            if (jogador.getJogaProximoTurno()) { // jogador se move
                // início do turno
                request = "Turno: " + (turno + 1) + "\n\n";
                request += this.tabuleiro.getTabuleiro() + "\n\n";
                request += "É a vez do jogador: " + jogador.getCor() + "\n";
                request += "Pressione Enter para jogar os dados.\n";
                if (debug) { // debug
                    jogadaForcada = false;
                    request += "\nDigite a cor do jogador e uma casa para mudar sua posição.\n";
                    request += "Exemplo: azul 30\n";
                    String userInput = screen.input(request, fabricaValidador.criarDebugValidador(this.tabuleiro.getNumCasas()));
                    String[] corENumero = userInput.split(" ");
                    if (corENumero.length != 2) { // apertou Enter
                        request = "";
                        dados = this.tabuleiro.movimentarJogador(indexJogador);
                        this.mostrarDados(dados);
                        if (!jogador.getItens().isEmpty()) {
                            if (jogador.getItens().contains("óculos escuros")) {
                                jogador.setMoedas(jogador.getMoedas() + 5);
                                jogador.setPosicao(jogador.getPosicao() + 3);
                                request += "Você possui óculos escuros, então ganhou \033[33m5 moedas\033[0m e andou 3 casas a mais.\n\n";
                            } else if (jogador.getItens().contains("moleton")) {
                                jogador.setMoedas(jogador.getMoedas() + 4);
                                jogador.setPosicao(jogador.getPosicao() + 2);
                                request += "Você possui um moleton, então ganhou \033[33m4 moedas\033[0m e andou 2 casas a mais.\n\n";
                            } else if (jogador.getItens().contains("boné")) {
                                jogador.setMoedas(jogador.getMoedas() + 3);
                                jogador.setPosicao(jogador.getPosicao() + 1);
                                request += "Você possui um boné, então ganhou \033[33m3 moedas\033[0m e andou 1 casa a mais.\n\n";
                            }
                        }
                        if (jogador.getPosicao() > this.tabuleiro.getNumCasas() - 1) {
                            request += "\nVocê foi para a casa " + this.tabuleiro.getNumCasas() + " e ainda ficou " + (jogador.getPosicao() - this.tabuleiro.getNumCasas()-1) + " de sobra.\n";
                            jogador.setPosicao(this.tabuleiro.getNumCasas() - 1);
                        } else {
                            request += "\nVocê foi para a casa: " + (jogador.getPosicao() + 1) + "\n";
                        }
                        request += "Pressione Enter para continuar.\n";
                        screen.input(request);
                        if (jogador.isGanharaMoedas()) {
                            screen.clear();
                            request = "Por ter tirado dados iguais, você ganhará \033[33m" + (dados.get(0) + dados.get(1)) + " moedas\033[0m.\n";
                            request += "Pressione Enter para continuar.\n";
                            screen.input(request);
                            jogador.setMoedas(jogador.getMoedas() + dados.get(0) + dados.get(1));
                            jogador.setGanharaMoedas(false);
                        }
                    } else { // forçou a jogada
                        String cor = corENumero[0];
                        String numero = corENumero[1];
                        for (int i = 0; i < this.tabuleiro.getNumJogadores(); i++) {
                            if (Objects.equals(this.tabuleiro.getJogadores().get(i).getCor(), cor)) {
                                indexJogador = i;
                                jogador = this.tabuleiro.getJogadores().get(indexJogador);
                                jogador.setPosicao(Integer.parseInt(numero) - 1);
                                jogador.aumentarQuantidadeDeJogadas();
                                break;
                            }
                        }
                        jogadaForcada = true;
                    }
                } else { // não debug
                    screen.input(request);
                    request = "";
                    dados = this.tabuleiro.movimentarJogador(indexJogador);
                    this.mostrarDados(dados);
                    if (!jogador.getItens().isEmpty()) {
                        if (jogador.getItens().contains("óculos escuros")) {
                            jogador.setMoedas(jogador.getMoedas() + 5);
                            jogador.setPosicao(jogador.getPosicao() + 3);
                            request += "\nVocê possui óculos escuros, então ganhou \033[33m5 moedas\033[0m e andou 3 casas a mais.\n\n";
                        } else if (jogador.getItens().contains("moleton")) {
                            jogador.setMoedas(jogador.getMoedas() + 4);
                            jogador.setPosicao(jogador.getPosicao() + 2);
                            request += "\nVocê possui um moleton, então ganhou \033[33m4 moedas\033[0m e andou 2 casas a mais.\n\n";
                        } else if (jogador.getItens().contains("boné")) {
                            jogador.setMoedas(jogador.getMoedas() + 3);
                            jogador.setPosicao(jogador.getPosicao() + 1);
                            request += "Você possui um boné, então ganhou \033[33m3 moedas\033[0m e andou 1 casa a mais.\n\n";
                        }
                    }
                    if (jogador.getPosicao() > this.tabuleiro.getNumCasas() - 1) {
                        request += "\nVocê foi para a casa " + this.tabuleiro.getNumCasas() + " e ainda ficou " + (jogador.getPosicao() - this.tabuleiro.getNumCasas()-1) + " de sobra.\n";
                        jogador.setPosicao(this.tabuleiro.getNumCasas() - 1);
                    } else {
                        request += "\nVocê foi para a casa: " + (jogador.getPosicao() + 1) + "\n";
                    }
                    request += "Pressione Enter para continuar.\n";
                    screen.input(request);
                    if (jogador.isGanharaMoedas()) {
                        screen.clear();
                        request = "Por ter tirado dados iguais, você ganhará \033[33m" + (dados.get(0) + dados.get(1)) + " moedas\033[0m.\n";
                        request += "Pressione Enter para continuar.\n";
                        screen.input(request);
                        jogador.setMoedas(jogador.getMoedas() + dados.get(0) + dados.get(1));
                        jogador.setGanharaMoedas(false);
                    }
                }
                screen.clear();
                if (jogador.getPosicao() < this.tabuleiro.getNumCasas()){
                    int posicaoDoJogador = jogador.getPosicao();
                    do {
                        System.out.println(this.tabuleiro.getTabuleiro());
                        this.tabuleiro.checarCasasEspeciais(indexJogador);
                        if (jogador.getPosicao() == posicaoDoJogador) {
                            break;
                        }
                        posicaoDoJogador = jogador.getPosicao();
                        screen.clear();
                    } while(true);
                }
            } else { // jogador não se move
                jogador.setJogaProximoTurno(true);
            }
            // fim do jogo
            if (jogador.getPosicao() >= this.tabuleiro.getNumCasas() - 1) {
                screen.clear();
                request = this.tabuleiro.getTabuleiro() + "\n";
                request += "\nParabéns! Jogador " + jogador.getCor() + " venceu!\n\n";
                for (int i = 0; i < this.tabuleiro.getNumJogadores(); i++) {
                    request += this.tabuleiro.getJogadores().get(i).getCor() + " fez " + this.tabuleiro.getJogadores().get(i).getQuantidadeDeJogadas() + " jogadas e terminou com \033[33m" + this.tabuleiro.getJogadores().get(i).getMoedas() + " moedas\033[0m.\n";
                }
                request += "\nPressione Enter para voltar ao menu principal.\n";
                screen.input(request);
                break;
            }
            // passar a vez para o próximo jogador
            if (dados.get(0) != dados.get(1)) {
                if (!jogadaForcada) {
                    if (indexJogador != this.tabuleiro.getNumJogadores() - 1) { // não é o último jogador
                        indexJogador++;
                    } else { // é o último jogador
                        indexJogador = 0;
                        turno++;
                    }
                }
            } else { // o jogador joga novamente se tiver tirado dados iguais
                screen.clear();
                if (jogador.getJogaProximoTurno()) {
                    request = "\nVocê tirou dados iguais!\n\n";
                    request += "Você jogará novamente e ganhará moedas igual a soma dos dados da nova jogada.\n";
                    request += "Pressione Enter para continuar.\n";
                    jogador.setGanharaMoedas(true);
                } else {
                    if (indexJogador != this.tabuleiro.getNumJogadores() - 1) { // não é o último jogador
                        indexJogador++;
                    } else { // é o último jogador
                        indexJogador = 0;
                        turno++;
                    }
                }
                screen.input(request);
            }
            screen.clear();
        } while (true);
    }

    public void mostrarDados(ArrayList<Integer> dados) {
        screen.clear();
        System.out.println("┌───┐   ┌───┐");
        System.out.println("│ " + dados.get(0) + " │   │ " + dados.get(1) + " │");
        System.out.println("└───┘   └───┘\n");
        System.out.println("Soma dos dados: " + (dados.get(0) + dados.get(1)));
    }
}
package mains;

import classes.Jogo;
import classes.Screen;
import validadores.FabricaValidador;

public class Main {
    private static final FabricaValidador fabricaValidador = new FabricaValidador();
    private static final Screen screen = new Screen();

    public static void main(String[] args) {
        // Iniciarlizar jogo
        Jogo jogo = new Jogo();

        // Pedir número de casas do tabuleiro
        screen.clear();
        System.out.println("Bem Vindo ao melhor jogo de tabuleiro do mundo todinho!\n");
        int numCasas = Integer.parseInt(screen.input("Digite o número de casas do tabuleiro.\n", fabricaValidador.criarIntegerValidador()));

        // Configurar tabuleiro
        jogo.configTabuleiro(numCasas);

        // Pedir número de jogadores
        screen.clear();
        int numJogadores = Integer.parseInt(screen.input("Digite o número de jogadores que irá jogar.\n", fabricaValidador.criarIntegerValidador()));

        // Configurar jogadores
        jogo.configJogadores(numJogadores);

        // Iniciar jogo
        jogo.start();
    }
}
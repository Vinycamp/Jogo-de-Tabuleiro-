package casas;

import validadores.FabricaValidador;

import java.util.Objects;

public class CasaMalandra extends Casa {
    private FabricaValidador fabricaValidador = new FabricaValidador();

    public CasaMalandra() {
        this.caractere = "Ø";
    }

    public void aplicarEfeito() {
        do {
            String request = "\nVocê caiu em uma casa malandra!\n";
            for (int i = 0; i < tabuleiro.getNumJogadores(); i++) {
                request += "Jogador " + tabuleiro.getJogadores().get(i).getCor() + ": " + tabuleiro.getJogadores().get(i).getPosicao() + "\n";
            }
            request += "\nDigite a cor do jogador que ira voltar para o inicio do jogo.\n";
            String buscarCor = screen.input(request, fabricaValidador.criarCoresValidador());
            screen.clear();

            for (int i = 0; i < tabuleiro.getNumJogadores(); i++) {
                if (Objects.equals(tabuleiro.getJogadores().get(i).getCor(), buscarCor)) {
                    tabuleiro.getJogadores().get(i).setPosicao(0);
                    request =
                            "Agora o jogador " + tabuleiro.getJogadores().get(i).getCor() + " voltou para o começo do jogo :)\n";
                    request += "Pressione enter para continuar!\n";
                    screen.input(request);
                    return;
                }
            }
            System.out.println("Não existe jogador " + buscarCor + " nesta partida. Tente Novamente!\n");
        } while (true);
    }
}

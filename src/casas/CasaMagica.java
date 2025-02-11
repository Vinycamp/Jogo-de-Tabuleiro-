package casas;

public class CasaMagica extends Casa {

    public CasaMagica() {
        this.caractere = "+";
    }

    public void aplicarEfeito() {
        String request = "\nVocê caiu em uma casa mágica!\n";
        int menorPosicao = tabuleiro.getNumCasas();
        int indiceJogadorComMenorPosicao = 0;
        for (int i = 0; i < tabuleiro.getNumJogadores(); i++) {
            if (tabuleiro.getJogadores().get(i).getPosicao() < menorPosicao) {
                menorPosicao = tabuleiro.getJogadores().get(i).getPosicao();
                indiceJogadorComMenorPosicao = i;
            }
        }
        tabuleiro.getJogadores().get(indiceJogadorComMenorPosicao).setPosicao(jogador.getPosicao());
        jogador.setPosicao(menorPosicao);
        if (indexJogador != indiceJogadorComMenorPosicao) {
            request += "Você trocará com o jogador que está mais atrás.\n";
            request +=
                    "Que será o jogador " + tabuleiro.getJogadores().get(indiceJogadorComMenorPosicao).getCor() + "\n";
        } else {
            request += "Você é o último jogador :/ não será trocado.\n";
        }
        request += "Pressione enter para continuar!\n";
        screen.input(request);
    }
}

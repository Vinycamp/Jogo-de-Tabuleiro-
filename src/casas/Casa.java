package casas;

import classes.Screen;
import classes.Tabuleiro;
import jogadores.Jogador;

public abstract class Casa {
    protected String caractere;
    protected Tabuleiro tabuleiro;
    protected Screen screen = new Screen();
    protected Jogador jogador;
    protected int indexJogador;

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void setJogador(int indexJogador) {
        this.indexJogador = indexJogador;
        this.jogador = tabuleiro.getJogadores().get(indexJogador);
    }

    public abstract void aplicarEfeito();

    public String getCaractere() {
        return caractere;
    }

}

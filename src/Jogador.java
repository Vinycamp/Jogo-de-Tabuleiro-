import java.util.ArrayList;

public abstract class Jogador {
    protected String cor;
    protected int posicao;
    protected boolean jogaProximoTurno;
    protected int quantidadeDeJogadas;


    public int getQuantidadeDeJogadas() {
        return quantidadeDeJogadas;
    }

    public boolean getJogaProximoTurno() {
        return jogaProximoTurno;
    }

    public void setJogaProximoTurno(boolean jogaProximoTurno) {
        this.jogaProximoTurno = jogaProximoTurno;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Jogador(String cor){
        this.cor = cor;
        this.posicao = 0;
        this.jogaProximoTurno = true;
        this.quantidadeDeJogadas = 0;
    }

    public Jogador(String cor, int posicao, int quantidadeDeJogadas){
        this.cor = cor;
        this.posicao = posicao;
        this.jogaProximoTurno = true;
        this.quantidadeDeJogadas = quantidadeDeJogadas;
    }

    public void aumentarQuantidadeDeJogadas(){
        this.quantidadeDeJogadas++;
    }

    public abstract ArrayList<Integer> jogarDados();
}

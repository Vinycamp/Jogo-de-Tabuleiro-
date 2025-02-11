package jogadores;

import java.util.ArrayList;

public abstract class Jogador {
    protected String cor;
    protected int posicao;
    protected int moedas;
    protected boolean jogaProximoTurno;
    protected boolean ganharaMoedas = false;
    protected int quantidadeDeJogadas;
    protected ArrayList<String> itens = new ArrayList<String>();


    public boolean isGanharaMoedas() {
        return ganharaMoedas;
    }

    public void setGanharaMoedas(boolean ganharaMoedas) {
        this.ganharaMoedas = ganharaMoedas;
    }

    public ArrayList<String> getItens() {
        return itens;
    }

    public int getMoedas() {
        return moedas;
    }
    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }
    public void setQuantidadeDeJogadas(int quantidadeDeJogadas) {
        this.quantidadeDeJogadas = quantidadeDeJogadas;
    }
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
        this.ganharaMoedas = false;
        this.quantidadeDeJogadas = 0;
        this.moedas = 0;
    }

    public void aumentarQuantidadeDeJogadas(){
        this.quantidadeDeJogadas++;
    }

    public abstract ArrayList<Integer> jogarDados();
}

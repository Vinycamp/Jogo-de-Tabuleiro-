package jogadores;

public class FabricaJogador {
    public Jogador criarJogadorNormal(String cor){
        return new JogadorNormal(cor);
    }
    public Jogador criarJogadorAzarado(String cor) {
        return new JogadorAzarado(cor);
    }
    public Jogador criarJogadorComSorte(String cor) {
        return new JogadorComSorte(cor);
    }
}

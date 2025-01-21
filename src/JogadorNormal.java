import java.util.ArrayList;

public class JogadorNormal extends Jogador{
    public JogadorNormal(String cor){
        super(cor);
    }

    public JogadorNormal(String cor, int posicao, int quantidadeDeJogadas){
        super(cor, posicao, quantidadeDeJogadas);
    }

    @Override
    public ArrayList<Integer> jogarDados() {
        int dado1 = (int)(Math.random()*6)+1;
        int dado2 = (int)(Math.random()*6)+1;
        int soma = dado1 + dado2;
        this.posicao += soma;
        ArrayList<Integer> dados = new ArrayList<>();
        dados.add(dado1);
        dados.add(dado2);
        return dados;
    }
}

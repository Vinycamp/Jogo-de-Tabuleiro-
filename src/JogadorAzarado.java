import java.util.ArrayList;

public class JogadorAzarado extends Jogador{
    public JogadorAzarado(String cor){
        super(cor);
    }

    public JogadorAzarado(String cor, int posicao, int quantidadeDeJogadas){
        super(cor, posicao, quantidadeDeJogadas);
    }

    @Override
    public ArrayList<Integer> jogarDados() {
        int dado1 = 0;
        int dado2 = 0;
        int soma = 7;
        while (soma > 6){
            dado1 = (int)(Math.random()*6)+1;
            dado2 = (int)(Math.random()*6)+1;
            soma = dado1 + dado2;
        }
        this.posicao += soma;
        ArrayList<Integer> dados = new ArrayList<>();
        dados.add(dado1);
        dados.add(dado2);
        return dados;
    }
}

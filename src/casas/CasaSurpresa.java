package casas;

import jogadores.Jogador;
import jogadores.FabricaJogador;
import validadores.FabricaValidador;

public class CasaSurpresa extends Casa {
    private FabricaJogador fabricaJogador = new FabricaJogador();
    private FabricaValidador fabricaValidador = new FabricaValidador();

    public CasaSurpresa() {
        this.caractere = "?";
    }

    public void aplicarEfeito() {
        String request;
        String carta;
        request = "\nVocê caiu em uma casa surpresa!\n";
        request += "Você escolherá uma carta que mudará a sua sorte!\n\n";
        request += "Escolha uma carta:\n\n";
        request += "┌─────┐ ┌─────┐ ┌─────┐\n";
        request += "│     │ │     │ │     │\n";
        request += "│  1  │ │  2  │ │  3  │\n";
        request += "│     │ │     │ │     │\n";
        request += "└─────┘ └─────┘ └─────┘\n\n";
        carta = screen.input(request, fabricaValidador.criarIntegerValidador(1, 3));
        screen.clear();
        int resultado = (int) (Math.random() * 3);
        String resultadoDaCarta = "";
        String tipoDoJogador = "";

        Jogador novoTipo = fabricaJogador.criarJogadorNormal("");
        String cor = jogador.getCor();
        int posicao = jogador.getPosicao();
        int quantidadeDeJogadas = jogador.getQuantidadeDeJogadas();
        int moedas = jogador.getMoedas();
        switch (resultado) {
            case 0:
                resultadoDaCarta = "Normal";
                tipoDoJogador = "normal";
                novoTipo = fabricaJogador.criarJogadorNormal(cor);
                break;
            case 1:
                resultadoDaCarta = "Sorte";
                tipoDoJogador = "com sorte";
                novoTipo = fabricaJogador.criarJogadorComSorte(cor);
                break;
            case 2:
                resultadoDaCarta = "Azar ";
                tipoDoJogador = "azarado";
                novoTipo = fabricaJogador.criarJogadorAzarado(cor);
                break;
        }
        novoTipo.setPosicao(posicao);
        novoTipo.setQuantidadeDeJogadas(quantidadeDeJogadas);
        novoTipo.setMoedas(moedas);
        tabuleiro.getJogadores().set(this.indexJogador, novoTipo);
        screen.clear();
        request = "┌─────┐ ┌─────┐ ┌─────┐\n";
        request += "│     │ │     │ │     │\n";
        switch (carta) {
            case "1":
                request += "│" + resultadoDaCarta + "│ │  2  │ │  3  │\n";
                break;
            case "2":
                request += "│  1  │ │" + resultadoDaCarta + "│ │  3  │\n";
                break;
            case "3":
                request += "│  1  │ │  2  │ │" + resultadoDaCarta + "│\n";
                break;
            default:
                break;
        }
        request += "│     │ │     │ │     │\n";
        request += "└─────┘ └─────┘ └─────┘\n\n";
        request += "Agora você é um jogador " + tipoDoJogador + "!\n\n";
        request += "Pressione Enter para continuar.\n";
        screen.input(request);
    }
}

package casas;

import jogadores.JogadorAzarado;

public class CasaDaSorte extends Casa {

    public CasaDaSorte() {
        this.caractere = "!";
    }

    public void aplicarEfeito() {
        String request = "\nVocê caiu em uma casa da sorte!\n";
        if (jogador instanceof JogadorAzarado) {
            request += "Porém, você não tem sorte :(\n";
            request += "Você não ganhará o bônus de andar 3 casas e receber 3 moedas.\n";
        } else {
            request += "Você andará \033[33m3 casas\033[0m YaY\n";
            request += "E receberá \033[33m3 moedas\033[0m\n";
            jogador.setPosicao(jogador.getPosicao() + 3);
            jogador.setMoedas(jogador.getMoedas() + 3);
        }
        request += "Pressione enter para continuar!\n";
        screen.input(request);
    }
}

package casas;

import jogadores.JogadorComSorte;

public class CasaDoAzar extends Casa{

    public CasaDoAzar() {
        this.caractere = "-";
    }

    public void aplicarEfeito() {
        String request = "\nVocê caiu em uma casa do azar!\n";
        if (jogador instanceof JogadorComSorte) {
            request += "Porém, você tem sorte :D\n";
            request += "Você não perderá 3 moedas.\n";
        } else {
            request += "E perderá 3 moedas\n";
            if (jogador.getMoedas() < 3) {
                jogador.setMoedas(0);
            } else {
                jogador.setMoedas(jogador.getMoedas() - 3);
            }
        }
        request += "Pressione enter para continuar!\n";
        screen.input(request);
    }
}

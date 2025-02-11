package casas;

public class CasaNormal extends Casa {
    public CasaNormal() {
        this.caractere = " ";
    }

    public void aplicarEfeito() {
        jogador.setMoedas(jogador.getMoedas()+1);
        String request = "\nVocê caiu em uma casa normal!\n\n";
        request += "Você ganhou 1 moeda.\n";
        request += "Pressione enter para continuar!\n";
        screen.input(request);
    }
}

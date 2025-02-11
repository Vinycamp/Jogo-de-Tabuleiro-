package casas;

import validadores.FabricaValidador;

public class CasaDeTroca extends Casa{
    public CasaDeTroca() {
        this.caractere = "T";
    }

    public void aplicarEfeito() {
        FabricaValidador fabricaValidador = new FabricaValidador();
        String request = "\nVocê caiu em uma casa de troca!\n";
        request += "Você pode trocar suas moedas por itens especias: \n\n";
        request += "- $7 boné: a cada rodada, ganha \033[33m3 moedas\033[0m e anda \033[32m1 casa\033[0m a mais.\n";
        request += "- $15 moleton: a cada rodada, ganha \033[33m4 moedas\033[0m e anda \033[32m2 casas\033[0m a mais.\n";
        request += "- $27 óculos escuros: a cada rodada, ganha \033[33m5 moedas\033[0m e anda \033[32m3 casas\033[0m a mais.\n";
        request += "Você possui \033[33m" + this.jogador.getMoedas() + " moedas\033[0m.\n";
        request += "Digite o nome do item que deseja comprar ou digite 'sair' para sair da loja.\n";
        request += "(Você só pode comprar um item por vez)\n";
        String userInput = screen.inputIgnorarAcentos(request, fabricaValidador.criarLojaValidador());
        screen.clear();
        request = "";
        switch (userInput){
            case "bone":
                if (this.jogador.getMoedas() >= 7){
                    this.jogador.setMoedas(this.jogador.getMoedas() - 7);
                    this.jogador.getItens().add("boné");
                    request += "Você comprou um boné.\n";
                    request += "Agora você possui \033[33m" + this.jogador.getMoedas() + " moedas\033[0m.\n";
                } else {
                    request += "Você não possui moedas suficientes para comprar um boné.\n";
                }
                break;
            case "moleton":
                if (this.jogador.getMoedas() >= 15){
                    this.jogador.setMoedas(this.jogador.getMoedas() - 15);
                    this.jogador.getItens().add("moleton");
                    request += "Você comprou um moleton.\n";
                    request += "Agora você possui \033[33m" + this.jogador.getMoedas() + " moedas\033[0m.\n";
                } else {
                    request += "Você não possui moedas suficientes para comprar um moleton.\n";
                }
                break;
            case "oculos escuros":
                if (this.jogador.getMoedas() >= 27){
                    this.jogador.setMoedas(this.jogador.getMoedas() - 27);
                    this.jogador.getItens().add("óculos escuros");
                    request += "Você comprou uns óculos escuros.\n";
                    request += "Agora você possui \033[33m" + this.jogador.getMoedas() + " moedas\033[0m.\n";
                } else {
                    request += "Você não possui moedas suficientes para comprar uns óculos escuros.\n";
                }
                break;
            case "sair":
                request += "Você saiu da loja.\n";
                break;
        }
        request += "Pressione enter para continuar.\n";
        screen.input(request);
    }
}

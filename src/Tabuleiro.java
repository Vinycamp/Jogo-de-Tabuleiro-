import java.util.ArrayList;
import java.util.Objects;

public class Tabuleiro {
    private String corDoTabuleiro;
    private String corDosNumeros;

    public Tabuleiro(String corDoTabuleiro, String corDosNumeros) {
        this.corDoTabuleiro = "\033[" + corDoTabuleiro + "m";
        this.corDosNumeros = "\033[" + corDosNumeros + "m";
    }

    public Tabuleiro() {
        this.corDoTabuleiro = "\033[0m";
        this.corDosNumeros = "\033[0m";
    }

    public void mostrarTabuleiroVazio() {
        /*
         * "┌─────┬───────────┬───────────┬───────────┐  ! Casa da Sorte:"
         * "│     │     ·!   !│     ·     │     ·?   ?│  Nesta casa você andará 3 casas a frente, mas se você for um jogador azarado"
         * "│  1  │  4  ·  5  │  8  ·  9  │ 12  · 13  │  tenho uma péssima noticia pra você."
         * "│     │     ·!   !│     ·     │     ·?   ?│"
         * "│· · · · · · · · · · · · · · · · · · · · ·│  # Presidio:"
         * "│     ·     │     ·     │#   #·     │     │  Nesta casa você ficará sem jogar por um turno, mas se você cair aqui tirando"
         * "│  2  ·  3  │  6  ·  7  │ 10  · 11  │ 14  │  dois dados iguais então pagará fiança, e poderá jogar novamente."
         * "│     ·     │     ·     │#   #·     │     │"
         * "├───────────┼───────────┴─────┬─────┘· · ·│  ? Casa Surpresa:"
         * "│     ·     │                 │     ·!   !│  Nesta casa você poderá mudar sua sorte, mas tome cuidado pra não ser um azarado."
         * "│ 39  · 40  │                 │ 16  · 15  │"
         * "│     ·     │                 │     ·!   !│  Ø Casa Malandra:"
         * "│· · · ─────┤                 │· · · ─────┤  Nesta casa você escolherá um competidor para voltar ao começo do jogo, sendo assim"
         * "│#   #·     │                 │Ø   Ø·     │  um grande vigarista."
         * "│ 38  · 37  │                 │ 17  · 18  │"
         * "│#   #·     │                 │Ø   Ø·     │  + Casa Mágica:"
         * "├───── · · ·│                 ├───── · · ·│  Nesta casa você troca automaticamente com o jogador mais atrás, mas se caso for"
         * "│+   +·     │                 │+   +·     │  você então permanecerá na solidão."
         * "│ 35  · 36  │                 │ 20  · 19  │"
         * "│+   +·     │                 │+   +·     │"
         * "│· · ·┌─────┴─────┬───────────┤· · · ─────┤"
         * "│     │     ·!   !│Ø   Ø·     │     ·     │"
         * "│ 34  │ 31  · 30  │ 27  · 26  │ 21  · 22  │"
         * "│     │     ·!   !│Ø   Ø·     │     ·     │"
         * "│· · · · · · · · · · · · · · ·└───── · · ·│"
         * "│     ·     │     ·     │#   #·     ·     │"
         * "│ 33  · 32  │ 29  · 28  │ 25  · 24  · 23  │"
         * "│     ·     │     ·     │#   #·     ·     │"
         * "└───────────┴───────────┴─────────────────┘"
         */
        System.out.println(corDoTabuleiro + "┌─────┬───────────┬───────────┬───────────┐  ! Casa da Sorte:");
        System.out.println(corDoTabuleiro
                + "│     │     ·!   !│     ·     │     ·?   ?│  Nesta casa você andará 3 casas a frente, mas se você for um jogador azarado");
        System.out.println(
                corDoTabuleiro + "│  " + "\033[0m" + "1" + corDoTabuleiro + "  │  " + "\033[0m" + "4" + corDoTabuleiro
                        + "  ·  " + "\033[0m" + "5" + corDoTabuleiro + "  │  " + "\033[0m" + "8" + corDoTabuleiro
                        + "  ·  " + "\033[0m" + "9" + corDoTabuleiro + "  │ " + "\033[0m" + "12" + corDoTabuleiro
                        + "  · " + "\033[0m" + "13" + corDoTabuleiro + "  │  tenho uma péssima noticia pra você.");
        System.out.println(corDoTabuleiro + "│     │     ·!   !│     ·     │     ·?   ?│");
        System.out.println(corDoTabuleiro + "│· · · · · · · · · · · · · · · · · · · · ·│  # Presidio:");
        System.out.println(corDoTabuleiro
                + "│     ·     │     ·     │#   #·     │     │  Nesta casa você ficará sem jogar por um turno, mas se você cair aqui tirando");
        System.out.println(corDoTabuleiro + "│  " + "\033[0m" + "2" + corDoTabuleiro + "  ·  " + "\033[0m" + "3"
                + corDoTabuleiro + "  │  " + "\033[0m" + "6" + corDoTabuleiro + "  ·  " + "\033[0m" + "7"
                + corDoTabuleiro + "  │ " + "\033[0m" + "10" + corDoTabuleiro + "  · " + "\033[0m" + "11"
                + corDoTabuleiro + "  │ " + "\033[0m" + "14" + corDoTabuleiro
                + "  │  dois dados iguais então pagará fiança, e poderá jogar novamente.");
        System.out.println(corDoTabuleiro + "│     ·     │     ·     │#   #·     │     │");
        System.out.println(corDoTabuleiro + "├───────────┼───────────┴─────┬─────┘· · ·│  ? Casa Surpresa:");
        System.out.println(corDoTabuleiro
                + "│     ·     │                 │     ·!   !│  Nesta casa você poderá mudar sua sorte, mas tome cuidado pra não ser um azarado.");
        System.out.println(corDoTabuleiro + "│ " + "\033[0m" + "39" + corDoTabuleiro + "  · " + "\033[0m" + "40"
                + corDoTabuleiro + "  │                 │ " + "\033[0m" + "16" + corDoTabuleiro + "  · " + "\033[0m"
                + "15" + corDoTabuleiro + "  │");
        System.out.println(corDoTabuleiro + "│     ·     │                 │     ·!   !│  Ø Casa Malandra:");
        System.out.println(corDoTabuleiro
                + "│· · · ─────┤                 │· · · ─────┤  Nesta casa você escolherá um competidor para voltar ao começo do jogo, sendo assim");
        System.out.println(corDoTabuleiro + "│#   #·     │                 │Ø   Ø·     │  um grande vigarista.");
        System.out.println(corDoTabuleiro + "│ " + "\033[0m" + "38" + corDoTabuleiro + "  · " + "\033[0m" + "37"
                + corDoTabuleiro + "  │                 │ " + "\033[0m" + "17" + corDoTabuleiro + "  · " + "\033[0m"
                + "18" + corDoTabuleiro + "  │");
        System.out.println(corDoTabuleiro + "│#   #·     │                 │Ø   Ø·     │  + Casa Mágica:");
        System.out.println(corDoTabuleiro
                + "├───── · · ·│                 ├───── · · ·│  Nesta casa você troca automaticamente com o jogador mais atrás, mas se caso for");
        System.out.println(
                corDoTabuleiro + "│+   +·     │                 │+   +·     │  você então permanecerá na solidão.");
        System.out.println(corDoTabuleiro + "│ " + "\033[0m" + "35" + corDoTabuleiro + "  · " + "\033[0m" + "36"
                + corDoTabuleiro + "  │                 │ " + "\033[0m" + "20" + corDoTabuleiro + "  · " + "\033[0m"
                + "19" + corDoTabuleiro + "  │");
        System.out.println(corDoTabuleiro + "│+   +·     │                 │+   +·     │");
        System.out.println(corDoTabuleiro + "│· · ·┌─────┴─────┬───────────┤· · · ─────┤");
        System.out.println(corDoTabuleiro + "│     │     ·!   !│Ø   Ø·     │     ·     │");
        System.out.println(corDoTabuleiro + "│ " + "\033[0m" + "34" + corDoTabuleiro + "  │ " + "\033[0m" + "31"
                + corDoTabuleiro + "  · " + "\033[0m" + "30" + corDoTabuleiro + "  │ " + "\033[0m" + "27"
                + corDoTabuleiro + "  · " + "\033[0m" + "26" + corDoTabuleiro + "  │ " + "\033[0m" + "21"
                + corDoTabuleiro + "  · " + "\033[0m" + "22" + corDoTabuleiro + "  │");
        System.out.println(corDoTabuleiro + "│     │     ·!   !│Ø   Ø·     │     ·     │");
        System.out.println(corDoTabuleiro + "│· · · · · · · · · · · · · · ·└───── · · ·│");
        System.out.println(corDoTabuleiro + "│     ·     │     ·     │#   #·     ·     │");
        System.out.println(corDoTabuleiro + "│ " + "\033[0m" + "33" + corDoTabuleiro + "  · " + "\033[0m" + "32"
                + corDoTabuleiro + "  │ " + "\033[0m" + "29" + corDoTabuleiro + "  · " + "\033[0m" + "28"
                + corDoTabuleiro + "  │ " + "\033[0m" + "25" + corDoTabuleiro + "  · " + "\033[0m" + "24"
                + corDoTabuleiro + "  · " + "\033[0m" + "23" + corDoTabuleiro + "  │");
        System.out.println(corDoTabuleiro + "│     ·     │     ·     │#   #·     ·     │");
        System.out.println(corDoTabuleiro + "└───────────┴───────────┴─────────────────┘");
        System.out.print("\033[0m");
    }

    public void mostrarTabuleiro(ArrayList<Jogador> jogadores) {
        int numeroDeJogadores = jogadores.size();
        ArrayList<ArrayList<String>> casas = new ArrayList<>();
        ArrayList<ArrayList<String>> cores = new ArrayList<>();
        String ordemDosJogadores = "";
        for (int jogador = 0; jogador < numeroDeJogadores; jogador++) {
            if (Objects.equals(jogadores.get(jogador).getCor(), "azul")) {
                ordemDosJogadores += "\033[34mA\033[0m";
            }
            if (Objects.equals(jogadores.get(jogador).getCor(), "verde")) {
                ordemDosJogadores += "\033[32mV\033[0m";
            }
            if (Objects.equals(jogadores.get(jogador).getCor(), "roxo")) {
                ordemDosJogadores += "\033[35mR\033[0m";
            }
            if (Objects.equals(jogadores.get(jogador).getCor(), "amarelo")) {
                ordemDosJogadores += "\033[33mA\033[0m";
            }
            if (Objects.equals(jogadores.get(jogador).getCor(), "vermelho")) {
                ordemDosJogadores += "\033[31mV\033[0m";
            }
            if (Objects.equals(jogadores.get(jogador).getCor(), "cinza")) {
                ordemDosJogadores += "\033[30mC\033[0m";
            }
            if (!(jogador == numeroDeJogadores - 1)) {
                ordemDosJogadores += " -> ";
            }
        }
        for (int i = 0; i < 40; i++) {
            casas.add(new ArrayList<>());
            cores.add(new ArrayList<>());
            for (int jogador = 0; jogador < numeroDeJogadores; jogador++) {
                if (jogadores.get(jogador).getPosicao() == i) {
                    if (Objects.equals(jogadores.get(jogador).getCor(), "azul")) {
                        casas.get(i).add("A");
                        cores.get(i).add("\033[34m");
                    }
                    if (Objects.equals(jogadores.get(jogador).getCor(), "verde")) {
                        casas.get(i).add("V");
                        cores.get(i).add("\033[32m");
                    }
                    if (Objects.equals(jogadores.get(jogador).getCor(), "roxo")) {
                        casas.get(i).add("R");
                        cores.get(i).add("\033[35m");
                    }
                    if (Objects.equals(jogadores.get(jogador).getCor(), "amarelo")) {
                        casas.get(i).add("A");
                        cores.get(i).add("\033[33m");
                    }
                    if (Objects.equals(jogadores.get(jogador).getCor(), "vermelho")) {
                        casas.get(i).add("V");
                        cores.get(i).add("\033[31m");
                    }
                    if (Objects.equals(jogadores.get(jogador).getCor(), "cinza")) {
                        casas.get(i).add("C");
                        cores.get(i).add("\033[30m");
                    }
                }
            }
            if (casas.get(i).size() == 0) {
                if (i <= 8) {
                    casas.get(i).add("" + (i + 1));
                    cores.get(i).add(corDosNumeros);
                } else {
                    String string_i = "" + (i + 1);
                    casas.get(i).add("" + string_i.charAt(1));
                    cores.get(i).add(corDosNumeros);
                    casas.get(i).add("" + string_i.charAt(0));
                    cores.get(i).add(corDosNumeros);
                }
            }
            int quantidadeDeEspacos = 6 - casas.get(i).size();
            for (int j = 0; j < quantidadeDeEspacos; j++) {
                casas.get(i).add(" ");
                cores.get(i).add("\033[0m");
            }
        }
        System.out.println(corDoTabuleiro + "┌─────┬───────────┬───────────┬───────────┐  ! Casa da Sorte:");
        System.out.println(corDoTabuleiro + "│ " + cores.get(0).get(4) + casas.get(0).get(4) + cores.get(0).get(3)
                + casas.get(0).get(3) + cores.get(0).get(5) + casas.get(0).get(5) + corDoTabuleiro + " │ "
                + cores.get(3).get(4) + casas.get(3).get(4) + cores.get(3).get(3) + casas.get(3).get(3)
                + cores.get(3).get(5) + casas.get(3).get(5) + corDoTabuleiro + " ·!" + cores.get(4).get(4)
                + casas.get(4).get(4) + cores.get(4).get(3) + casas.get(4).get(3) + cores.get(4).get(5)
                + casas.get(4).get(5) + corDoTabuleiro + "!│ " + cores.get(7).get(4) + casas.get(7).get(4)
                + cores.get(7).get(3) + casas.get(7).get(3) + cores.get(7).get(5) + casas.get(7).get(5) + corDoTabuleiro
                + " · " + cores.get(8).get(4) + casas.get(8).get(4) + cores.get(8).get(3) + casas.get(8).get(3)
                + cores.get(8).get(5) + casas.get(8).get(5) + corDoTabuleiro + " │ " + cores.get(11).get(4)
                + casas.get(11).get(4) + cores.get(11).get(3) + casas.get(11).get(3) + cores.get(11).get(5)
                + casas.get(11).get(5) + corDoTabuleiro + " ·?" + cores.get(12).get(4) + casas.get(12).get(4)
                + cores.get(12).get(3) + casas.get(12).get(3) + cores.get(12).get(5) + casas.get(12).get(5)
                + corDoTabuleiro + "?│  Nesta casa você andará 3 casas a frente, mas se você for um jogador azarado");
        System.out.println(corDoTabuleiro + "│ " + cores.get(0).get(1) + casas.get(0).get(1) + cores.get(0).get(0)
                + casas.get(0).get(0) + cores.get(0).get(2) + casas.get(0).get(2) + corDoTabuleiro + " │ "
                + cores.get(3).get(1) + casas.get(3).get(1) + cores.get(3).get(0) + casas.get(3).get(0)
                + cores.get(3).get(2) + casas.get(3).get(2) + corDoTabuleiro + " · " + cores.get(4).get(1)
                + casas.get(4).get(1) + cores.get(4).get(0) + casas.get(4).get(0) + cores.get(4).get(2)
                + casas.get(4).get(2) + corDoTabuleiro + " │ " + cores.get(7).get(1) + casas.get(7).get(1)
                + cores.get(7).get(0) + casas.get(7).get(0) + cores.get(7).get(2) + casas.get(7).get(2) + corDoTabuleiro
                + " · " + cores.get(8).get(1) + casas.get(8).get(1) + cores.get(8).get(0) + casas.get(8).get(0)
                + cores.get(8).get(2) + casas.get(8).get(2) + corDoTabuleiro + " │ " + cores.get(11).get(1)
                + casas.get(11).get(1) + cores.get(11).get(0) + casas.get(11).get(0) + cores.get(11).get(2)
                + casas.get(11).get(2) + corDoTabuleiro + " · " + cores.get(12).get(1) + casas.get(12).get(1)
                + cores.get(12).get(0) + casas.get(12).get(0) + cores.get(12).get(2) + casas.get(12).get(2)
                + corDoTabuleiro + " │  tenho uma péssima noticia pra você.");
        System.out.println(corDoTabuleiro + "│     │     ·!   !│     ·     │     ·?   ?│");
        System.out.println(corDoTabuleiro + "│· · · · · · · · · · · · · · · · · · · · ·│  # Presidio:");
        System.out.println(corDoTabuleiro + "│ " + cores.get(1).get(4) + casas.get(1).get(4) + cores.get(1).get(3)
                + casas.get(1).get(3) + cores.get(1).get(5) + casas.get(1).get(5) + corDoTabuleiro + " · "
                + cores.get(2).get(4) + casas.get(2).get(4) + cores.get(2).get(3) + casas.get(2).get(3)
                + cores.get(2).get(5) + casas.get(2).get(5) + corDoTabuleiro + " │ " + cores.get(5).get(4)
                + casas.get(5).get(4) + cores.get(5).get(3) + casas.get(5).get(3) + cores.get(5).get(5)
                + casas.get(5).get(5) + corDoTabuleiro + " · " + cores.get(6).get(4) + casas.get(6).get(4)
                + cores.get(6).get(3) + casas.get(6).get(3) + cores.get(6).get(5) + casas.get(6).get(5) + corDoTabuleiro
                + " │#" + cores.get(9).get(4) + casas.get(9).get(4) + cores.get(9).get(3) + casas.get(9).get(3)
                + cores.get(9).get(5) + casas.get(9).get(5) + corDoTabuleiro + "#· " + cores.get(10).get(4)
                + casas.get(10).get(4) + cores.get(10).get(3) + casas.get(10).get(3) + cores.get(10).get(5)
                + casas.get(10).get(5) + corDoTabuleiro + " │ " + cores.get(13).get(4) + casas.get(13).get(4)
                + cores.get(13).get(3) + casas.get(13).get(3) + cores.get(13).get(5) + casas.get(13).get(5)
                + corDoTabuleiro + " │  Nesta casa você ficará sem jogar por um turno, mas se você cair aqui tirando");
        System.out.println(corDoTabuleiro + "│ " + cores.get(1).get(1) + casas.get(1).get(1) + cores.get(1).get(0)
                + casas.get(1).get(0) + cores.get(1).get(2) + casas.get(1).get(2) + corDoTabuleiro + " · "
                + cores.get(2).get(1) + casas.get(2).get(1) + cores.get(2).get(0) + casas.get(2).get(0)
                + cores.get(2).get(2) + casas.get(2).get(2) + corDoTabuleiro + " │ " + cores.get(5).get(1)
                + casas.get(5).get(1) + cores.get(5).get(0) + casas.get(5).get(0) + cores.get(5).get(2)
                + casas.get(5).get(2) + corDoTabuleiro + " · " + cores.get(6).get(1) + casas.get(6).get(1)
                + cores.get(6).get(0) + casas.get(6).get(0) + cores.get(6).get(2) + casas.get(6).get(2) + corDoTabuleiro
                + " │ " + cores.get(9).get(1) + casas.get(9).get(1) + cores.get(9).get(0) + casas.get(9).get(0)
                + cores.get(9).get(2) + casas.get(9).get(2) + corDoTabuleiro + " · " + cores.get(10).get(1)
                + casas.get(10).get(1) + cores.get(10).get(0) + casas.get(10).get(0) + cores.get(10).get(2)
                + casas.get(10).get(2) + corDoTabuleiro + " │ " + cores.get(13).get(1) + casas.get(13).get(1)
                + cores.get(13).get(0) + casas.get(13).get(0) + cores.get(13).get(2) + casas.get(13).get(2)
                + corDoTabuleiro + " │  dois dados iguais então pagará fiança, e poderá jogar novamente.");
        System.out.println(corDoTabuleiro + "│     ·     │     ·     │#   #·     │     │");
        System.out.println(corDoTabuleiro + "├───────────┼───────────┴─────┬─────┘· · ·│  ? Casa Surpresa:");
        System.out.println(corDoTabuleiro + "│ " + cores.get(38).get(4) + casas.get(38).get(4) + cores.get(38).get(3)
                + casas.get(38).get(3) + cores.get(38).get(5) + casas.get(38).get(5) + corDoTabuleiro + " · "
                + cores.get(39).get(4) + casas.get(39).get(4) + cores.get(39).get(3) + casas.get(39).get(3)
                + cores.get(39).get(5) + casas.get(39).get(5) + corDoTabuleiro + " │                 │ "
                + cores.get(15).get(4) + casas.get(15).get(4) + cores.get(15).get(3) + casas.get(15).get(3)
                + cores.get(15).get(5) + casas.get(15).get(5) + corDoTabuleiro + " ·!" + cores.get(14).get(4)
                + casas.get(14).get(4) + cores.get(14).get(3) + casas.get(14).get(3) + cores.get(14).get(5)
                + casas.get(14).get(5) + corDoTabuleiro
                + "!│  Nesta casa você poderá mudar sua sorte, mas tome cuidado pra não ser um azarado.");
        System.out.println(corDoTabuleiro + "│ " + cores.get(38).get(1) + casas.get(38).get(1) + cores.get(38).get(0)
                + casas.get(38).get(0) + cores.get(38).get(2) + casas.get(38).get(2) + corDoTabuleiro + " · "
                + cores.get(39).get(1) + casas.get(39).get(1) + cores.get(39).get(0) + casas.get(39).get(0)
                + cores.get(39).get(2) + casas.get(39).get(2) + corDoTabuleiro + " │                 │ "
                + cores.get(15).get(1) + casas.get(15).get(1) + cores.get(15).get(0) + casas.get(15).get(0)
                + cores.get(15).get(2) + casas.get(15).get(2) + corDoTabuleiro + " · " + cores.get(14).get(1)
                + casas.get(14).get(1) + cores.get(14).get(0) + casas.get(14).get(0) + cores.get(14).get(2)
                + casas.get(14).get(2) + corDoTabuleiro + " │");
        System.out.println(corDoTabuleiro + "│     ·     │                 │     ·!   !│  Ø Casa Malandra:");
        System.out.println(corDoTabuleiro
                + "│· · · ─────┤                 │· · · ─────┤  Nesta casa você escolherá um competidor para voltar ao começo do jogo, sendo assim");
        System.out.println(corDoTabuleiro + "│#" + cores.get(37).get(4) + casas.get(37).get(4) + cores.get(37).get(3)
                + casas.get(37).get(3) + cores.get(37).get(5) + casas.get(37).get(5) + corDoTabuleiro + "#· "
                + cores.get(36).get(4) + casas.get(36).get(4) + cores.get(36).get(3) + casas.get(36).get(3)
                + cores.get(36).get(5) + casas.get(36).get(5) + corDoTabuleiro + " │                 │Ø"
                + cores.get(16).get(4) + casas.get(16).get(4) + cores.get(16).get(3) + casas.get(16).get(3)
                + cores.get(16).get(5) + casas.get(16).get(5) + corDoTabuleiro + "Ø· " + cores.get(17).get(4)
                + casas.get(17).get(4) + cores.get(17).get(3) + casas.get(17).get(3) + cores.get(17).get(5)
                + casas.get(17).get(5) + corDoTabuleiro + " │  um grande vigarista.");
        System.out.println(corDoTabuleiro + "│ " + cores.get(37).get(1) + casas.get(37).get(1) + cores.get(37).get(0)
                + casas.get(37).get(0) + cores.get(37).get(2) + casas.get(37).get(2) + corDoTabuleiro + " · "
                + cores.get(36).get(1) + casas.get(36).get(1) + cores.get(36).get(0) + casas.get(36).get(0)
                + cores.get(36).get(2) + casas.get(36).get(2) + corDoTabuleiro + " │                 │ "
                + cores.get(16).get(1) + casas.get(16).get(1) + cores.get(16).get(0) + casas.get(16).get(0)
                + cores.get(16).get(2) + casas.get(16).get(2) + corDoTabuleiro + " · " + cores.get(17).get(1)
                + casas.get(17).get(1) + cores.get(17).get(0) + casas.get(17).get(0) + cores.get(17).get(2)
                + casas.get(17).get(2) + corDoTabuleiro + " │");
        System.out.println(corDoTabuleiro + "│#   #·     │                 │Ø   Ø·     │  + Casa Mágica:");
        System.out.println(corDoTabuleiro
                + "├───── · · ·│                 ├───── · · ·│  Nesta casa você troca automaticamente com o jogador mais atrás, mas se caso for");
        System.out.println(corDoTabuleiro + "│+" + cores.get(34).get(4) + casas.get(34).get(4) + cores.get(34).get(3)
                + casas.get(34).get(3) + cores.get(34).get(5) + casas.get(34).get(5) + corDoTabuleiro + "+· "
                + cores.get(35).get(4) + casas.get(35).get(4) + cores.get(35).get(3) + casas.get(35).get(3)
                + cores.get(35).get(5) + casas.get(35).get(5) + corDoTabuleiro + " │                 │+"
                + cores.get(19).get(4) + casas.get(19).get(4) + cores.get(19).get(3) + casas.get(19).get(3)
                + cores.get(19).get(5) + casas.get(19).get(5) + corDoTabuleiro + "+· " + cores.get(18).get(4)
                + casas.get(18).get(4) + cores.get(18).get(3) + casas.get(18).get(3) + cores.get(18).get(5)
                + casas.get(18).get(5) + corDoTabuleiro + " │  você então permanecerá na solidão.");
        System.out.println(corDoTabuleiro + "│ " + cores.get(34).get(1) + casas.get(34).get(1) + cores.get(34).get(0)
                + casas.get(34).get(0) + cores.get(34).get(2) + casas.get(34).get(2) + corDoTabuleiro + " · "
                + cores.get(35).get(1) + casas.get(35).get(1) + cores.get(35).get(0) + casas.get(35).get(0)
                + cores.get(35).get(2) + casas.get(35).get(2) + corDoTabuleiro + " │                 │ "
                + cores.get(19).get(1) + casas.get(19).get(1) + cores.get(19).get(0) + casas.get(19).get(0)
                + cores.get(19).get(2) + casas.get(19).get(2) + corDoTabuleiro + " · " + cores.get(18).get(1)
                + casas.get(18).get(1) + cores.get(18).get(0) + casas.get(18).get(0) + cores.get(18).get(2)
                + casas.get(18).get(2) + corDoTabuleiro + " │");
        System.out.println(corDoTabuleiro + "│+   +·     │                 │+   +·     │");
        System.out.println(corDoTabuleiro + "│· · ·┌─────┴─────┬───────────┤· · · ─────┤  " + ordemDosJogadores);
        System.out.println(corDoTabuleiro + "│ " + cores.get(33).get(4) + casas.get(33).get(4) + cores.get(33).get(3)
                + casas.get(33).get(3) + cores.get(33).get(5) + casas.get(33).get(5) + corDoTabuleiro + " │ "
                + cores.get(30).get(4) + casas.get(30).get(4) + cores.get(30).get(3) + casas.get(30).get(3)
                + cores.get(30).get(5) + casas.get(30).get(5) + corDoTabuleiro + " ·!" + cores.get(29).get(4)
                + casas.get(29).get(4) + cores.get(29).get(3) + casas.get(29).get(3) + cores.get(29).get(5)
                + casas.get(29).get(5) + corDoTabuleiro + "!│Ø" + cores.get(26).get(4) + casas.get(26).get(4)
                + cores.get(26).get(3) + casas.get(26).get(3) + cores.get(26).get(5) + casas.get(26).get(5)
                + corDoTabuleiro + "Ø· " + cores.get(25).get(4) + casas.get(25).get(4) + cores.get(25).get(3)
                + casas.get(25).get(3) + cores.get(25).get(5) + casas.get(25).get(5) + corDoTabuleiro + " │ "
                + cores.get(20).get(4) + casas.get(20).get(4) + cores.get(20).get(3) + casas.get(20).get(3)
                + cores.get(20).get(5) + casas.get(20).get(5) + corDoTabuleiro + " · " + cores.get(21).get(4)
                + casas.get(21).get(4) + cores.get(21).get(3) + casas.get(21).get(3) + cores.get(21).get(5)
                + casas.get(21).get(5) + corDoTabuleiro + " │");
        System.out.println(corDoTabuleiro + "│ " + cores.get(33).get(1) + casas.get(33).get(1) + cores.get(33).get(0)
                + casas.get(33).get(0) + cores.get(33).get(2) + casas.get(33).get(2) + corDoTabuleiro + " │ "
                + cores.get(30).get(1) + casas.get(30).get(1) + cores.get(30).get(0) + casas.get(30).get(0)
                + cores.get(30).get(2) + casas.get(30).get(2) + corDoTabuleiro + " · " + cores.get(29).get(1)
                + casas.get(29).get(1) + cores.get(29).get(0) + casas.get(29).get(0) + cores.get(29).get(2)
                + casas.get(29).get(2) + corDoTabuleiro + " │ " + cores.get(26).get(1) + casas.get(26).get(1)
                + cores.get(26).get(0) + casas.get(26).get(0) + cores.get(26).get(2) + casas.get(26).get(2)
                + corDoTabuleiro + " · " + cores.get(25).get(1) + casas.get(25).get(1) + cores.get(25).get(0)
                + casas.get(25).get(0) + cores.get(25).get(2) + casas.get(25).get(2) + corDoTabuleiro + " │ "
                + cores.get(20).get(1) + casas.get(20).get(1) + cores.get(20).get(0) + casas.get(20).get(0)
                + cores.get(20).get(2) + casas.get(20).get(2) + corDoTabuleiro + " · " + cores.get(21).get(1)
                + casas.get(21).get(1) + cores.get(21).get(0) + casas.get(21).get(0) + cores.get(21).get(2)
                + casas.get(21).get(2) + corDoTabuleiro + " │");
        System.out.println(corDoTabuleiro + "│     │     ·!   !│Ø   Ø·     │     ·     │");
        System.out.println(corDoTabuleiro + "│· · · · · · · · · · · · · · ·└───── · · ·│");
        System.out.println(corDoTabuleiro + "│ " + cores.get(32).get(4) + casas.get(32).get(4) + cores.get(32).get(3)
                + casas.get(32).get(3) + cores.get(32).get(5) + casas.get(32).get(5) + corDoTabuleiro + " · "
                + cores.get(31).get(4) + casas.get(31).get(4) + cores.get(31).get(3) + casas.get(31).get(3)
                + cores.get(31).get(5) + casas.get(31).get(5) + corDoTabuleiro + " │ " + cores.get(28).get(4)
                + casas.get(28).get(4) + cores.get(28).get(3) + casas.get(28).get(3) + cores.get(28).get(5)
                + casas.get(28).get(5) + corDoTabuleiro + " · " + cores.get(27).get(4) + casas.get(27).get(4)
                + cores.get(27).get(3) + casas.get(27).get(3) + cores.get(27).get(5) + casas.get(27).get(5)
                + corDoTabuleiro + " │#" + cores.get(24).get(4) + casas.get(24).get(4) + cores.get(24).get(3)
                + casas.get(24).get(3) + cores.get(24).get(5) + casas.get(24).get(5) + corDoTabuleiro + "#· "
                + cores.get(23).get(4) + casas.get(23).get(4) + cores.get(23).get(3) + casas.get(23).get(3)
                + cores.get(23).get(5) + casas.get(23).get(5) + corDoTabuleiro + " · " + cores.get(22).get(4)
                + casas.get(22).get(4) + cores.get(22).get(3) + casas.get(22).get(3) + cores.get(22).get(5)
                + casas.get(22).get(5) + corDoTabuleiro + " │");
        System.out.println(corDoTabuleiro + "│ " + cores.get(32).get(1) + casas.get(32).get(1) + cores.get(32).get(0)
                + casas.get(32).get(0) + cores.get(32).get(2) + casas.get(32).get(2) + corDoTabuleiro + " · "
                + cores.get(31).get(1) + casas.get(31).get(1) + cores.get(31).get(0) + casas.get(31).get(0)
                + cores.get(31).get(2) + casas.get(31).get(2) + corDoTabuleiro + " │ " + cores.get(28).get(1)
                + casas.get(28).get(1) + cores.get(28).get(0) + casas.get(28).get(0) + cores.get(28).get(2)
                + casas.get(28).get(2) + corDoTabuleiro + " · " + cores.get(27).get(1) + casas.get(27).get(1)
                + cores.get(27).get(0) + casas.get(27).get(0) + cores.get(27).get(2) + casas.get(27).get(2)
                + corDoTabuleiro + " │ " + cores.get(24).get(1) + casas.get(24).get(1) + cores.get(24).get(0)
                + casas.get(24).get(0) + cores.get(24).get(2) + casas.get(24).get(2) + corDoTabuleiro + " · "
                + cores.get(23).get(1) + casas.get(23).get(1) + cores.get(23).get(0) + casas.get(23).get(0)
                + cores.get(23).get(2) + casas.get(23).get(2) + corDoTabuleiro + " · " + cores.get(22).get(1)
                + casas.get(22).get(1) + cores.get(22).get(0) + casas.get(22).get(0) + cores.get(22).get(2)
                + casas.get(22).get(2) + corDoTabuleiro + " │");
        System.out.println(corDoTabuleiro + "│     ·     │     ·     │#   #·     ·     │");
        System.out.println(corDoTabuleiro + "└───────────┴───────────┴─────────────────┘");
        System.out.print("\033[0m");
    }
}
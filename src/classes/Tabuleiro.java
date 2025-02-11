package classes;

import casas.Casa;
import casas.FabricaCasa;
import jogadores.Jogador;
import java.util.ArrayList;

public class Tabuleiro {
    private String tabuleiro = "";
    private String corDoTabuleiro;
    private String corDosNumeros;

    private ArrayList<Jogador> jogadores;
    private int numJogadores;
    private ArrayList<Casa> casas = new ArrayList<Casa>();
    private int numCasas;

    private FabricaCasa fabricaCasa = new FabricaCasa();
    private int indexCasa = 0;
    private int indexLinha = 0;
    private boolean casaVazia = false;

    public static Tabuleiro instancia;

    private Tabuleiro() {
        this.corDoTabuleiro = "\033[0m";
        this.corDosNumeros = "\033[0m";
    }

    public static Tabuleiro getInstancia() {
        if (instancia == null)
            instancia = new Tabuleiro();
        return instancia;
    }

    private Tabuleiro(String corDoTabuleiro, String corDosNumeros) {
        this.corDoTabuleiro = "\033[" + corDoTabuleiro + "m";
        this.corDosNumeros = "\033[" + corDosNumeros + "m";
    }

    public static Tabuleiro getInstancia(String corDoTabuleiro, String corDosNumeros) {
        if (instancia == null)
            instancia = new Tabuleiro(corDoTabuleiro, corDosNumeros);
        return instancia;
    }

    public void configCasas(ArrayList<Casa> casas) {
        this.casas = casas;
        this.numCasas = casas.size();
    }

    public void criarCasasAleatorias(int numCasas) {
        this.numCasas = numCasas;
        for (int i = 0; i < this.numCasas; i++) {
            int aleatorio = (int) (Math.random() * 14);
            if (aleatorio == 0) {
                this.casas.add(fabricaCasa.criarCasaDaSorte());
            } else if (aleatorio == 1) {
                this.casas.add(fabricaCasa.criarCasaDoAzar());
            } else if (aleatorio == 2) {
                this.casas.add(fabricaCasa.criarCasaMagica());
            } else if (aleatorio == 3) {
                this.casas.add(fabricaCasa.criarCasaMalandra());
            } else if (aleatorio == 4) {
                this.casas.add(fabricaCasa.criarCasaPresidio());
            } else if (aleatorio == 5) {
                this.casas.add(fabricaCasa.criarCasaSurpresa());
            } else if (aleatorio >= 6 && aleatorio <= 7) {
                this.casas.add(fabricaCasa.criarCasaDeTroca());
            } else {
                this.casas.add(fabricaCasa.criarCasaNormal());
            }
        }
    }

    public ArrayList<Integer> movimentarJogador(int jogador) {
        ArrayList<Integer> dados = this.jogadores.get(jogador).jogarDados();
        this.jogadores.get(jogador).aumentarQuantidadeDeJogadas();
        return dados;
    }

    public void checarCasasEspeciais(int jogador) {
        int posicao = this.jogadores.get(jogador).getPosicao();
        this.casas.get(posicao).setTabuleiro(this);
        this.casas.get(posicao).setJogador(jogador);
        this.casas.get(posicao).aplicarEfeito();
    }

    public void atualizarTabuleiro() {
        this.indexLinha = 0;
        this.indexCasa = 0;
        int comprimento = 10;
        this.tabuleiro = "";
        for (int i = 0; i < numCasas; i++) {
            this.addCasa(this.casas.get(i), numCasas, comprimento);
        }
        if (numCasas < comprimento) {
            comprimento = numCasas;
        }
        String[] linhasVetor = tabuleiro.split("\n");
        ArrayList<String> linhas = new ArrayList<>();
        for (int j = 0; j < linhasVetor.length; j++) {
            linhas.add(linhasVetor[j]);
        }
        String espacamento = "";
        for (int j = 0; j < 6 * comprimento + 1; j++) {
            espacamento += " ";
        }
        for (int i = 0; i < this.jogadores.size(); i++) {
            String letra = "";
            String cor = "";
            Jogador jogador = this.jogadores.get(i);
            String corDoJogador = this.jogadores.get(i).getCor();
            switch (corDoJogador) {
                case "azul":
                    letra = "A";
                    cor = "\033[34m";
                    break;
                case "verde":
                    letra = "V";
                    cor = "\033[32m";
                    break;
                case "roxo":
                    letra = "R";
                    cor = "\033[35m";
                    break;
                case "amarelo":
                    letra = "A";
                    cor = "\033[33m";
                    break;
                case "vermelho":
                    letra = "V";
                    cor = "\033[31m";
                    break;
                case "cinza":
                    letra = "C";
                    cor = "\033[30m";
                    break;
                default:
                    break;
            }
            String itens = "";
            for (int j = 0; j < jogador.getItens().size(); j++) {
                if (j != jogador.getItens().size() - 1) {
                    itens += jogador.getItens().get(j) + ", ";
                } else {
                    itens += jogador.getItens().get(j);
                }
            }
            int index = 2 * i + 1;
            int faltam = index - linhas.size() + 1;
            if (faltam < 0) {
                espacamento = "";
                if (((int) ((this.numCasas - 1) / comprimento) + 1) % 2 == 1 && faltam >= -3) {
                    for (int j = 0; j < (6 * (comprimento - this.numCasas % comprimento)); j++) {
                        espacamento += " ";
                    }
                }
                linhas.set(index, linhas.get(index) + espacamento + " " + cor + letra + this.corDoTabuleiro + " Moedas: \033[33m" + jogador.getMoedas() + "\033[0m, itens: " + itens);
            } else {
                espacamento = "";
                for (int j = 0; j < 6 * comprimento + 1; j++) {
                    espacamento += " ";
                }
                if (faltam == 2) {
                    linhas.add("");
                }
                linhas.add(espacamento + " " + cor + letra + this.corDoTabuleiro + " Moedas: \033[33m" + jogador.getMoedas() + "\033[0m, itens: " + itens);
            }
        }
        this.tabuleiro = "";
        for (String linha : linhas) {
            this.tabuleiro += linha + "\n";
        }
        this.tabuleiro = corDoTabuleiro + this.tabuleiro + "\033[0m";
    }

    public String getTabuleiroVazio() {
        this.tabuleiro = "";
        for (int i = 0; i < numCasas; i++) {
            this.addCasaVazia(this.casas.get(i), numCasas, 10);
        }
        this.indexLinha = 0;
        this.indexCasa = 0;
        return corDoTabuleiro + this.tabuleiro + "\033[0m";
    }

    public String getTabuleiro() {
        this.atualizarTabuleiro();
        return this.tabuleiro;
    }

    public void addCasaVazia(Casa casa, int numCasas, int comprimento) {
        this.casaVazia = true;
        this.addCasa(casa, numCasas, comprimento);
        this.casaVazia = false;
    }

    public void addCasa(Casa casa, int numCasas, int comprimento) {
        // separar o tabuleiro em linhas
        String[] linhas = tabuleiro.split("\n");
        if (linhas.length == 1) {
            linhas = new String[5];
            for (int i = 0; i < linhas.length; i++) {
                linhas[i] = "";
            }
        } else if (indexCasa % comprimento == 0) {
            String[] linhasAntigas = linhas;
            linhas = new String[linhas.length + 4];
            for (int i = 0; i < linhas.length; i++) {
                if (i < linhasAntigas.length) {
                    linhas[i] = linhasAntigas[i];
                } else {
                    linhas[i] = "";
                }
            }
            indexLinha++;
        }

        // fazer a primeira linha
        if (indexLinha == 0) {
            if ((indexCasa) % comprimento == 0) {
                if (indexCasa + 1 == numCasas) {
                    linhas[0] += "┌─────┐";
                } else {
                    linhas[0] += "┌──────";
                }
            } else if (indexCasa != comprimento - 1 && indexCasa != numCasas - 1) {
                linhas[0] += "──────";
            } else {
                linhas[0] += "─────┐";
            }
        }

        // definir o conteúdo da casa
        ArrayList<String> conteudoDaCasa = new ArrayList<>();
        if (casaVazia) {
            conteudoDaCasa.add("   ");
            if (indexCasa + 1 < 10) {
                conteudoDaCasa.add(corDosNumeros + " " + (indexCasa + 1) + " " + corDoTabuleiro);
            } else if (indexCasa + 1 < 100) {
                conteudoDaCasa.add(corDosNumeros + (indexCasa + 1) + " " + corDoTabuleiro);
            } else {
                conteudoDaCasa.add(corDosNumeros + (indexCasa + 1) + corDoTabuleiro);
            }
        } else {
            conteudoDaCasa.add("");
            conteudoDaCasa.add("");
            int indexLetra = 0;
            String letra = "";
            String cor = "";
            for (int i = 0; i < this.numJogadores; i++) {
                if (this.jogadores.get(i).getPosicao() == indexCasa) {
                    String corDoJogador = this.jogadores.get(i).getCor();
                    switch (corDoJogador) {
                        case "azul":
                            letra = "A";
                            cor = "\033[34m";
                            break;
                        case "verde":
                            letra = "V";
                            cor = "\033[32m";
                            break;
                        case "roxo":
                            letra = "R";
                            cor = "\033[35m";
                            break;
                        case "amarelo":
                            letra = "A";
                            cor = "\033[33m";
                            break;
                        case "vermelho":
                            letra = "V";
                            cor = "\033[31m";
                            break;
                        case "cinza":
                            letra = "C";
                            cor = "\033[30m";
                            break;
                    }
                    switch (indexLetra) {
                        case 0:
                            conteudoDaCasa.set(1, " "+cor+letra+corDoTabuleiro+" ");
                            break;
                        case 1:
                            conteudoDaCasa.set(1, cor+letra+corDoTabuleiro+conteudoDaCasa.get(1).substring(1, conteudoDaCasa.get(1).length()-1)+" ");
                            break;
                        case 2:
                            conteudoDaCasa.set(1, conteudoDaCasa.get(1).substring(0, conteudoDaCasa.get(1).length()-1)+cor+letra+corDoTabuleiro);
                            break;
                        case 3:
                            conteudoDaCasa.set(0, " "+cor+letra+corDoTabuleiro+" ");
                            break;
                        case 4:
                            conteudoDaCasa.set(0, cor+letra+corDoTabuleiro+conteudoDaCasa.get(0).substring(1, conteudoDaCasa.get(0).length()-1)+" ");
                            break;
                        case 5:
                            conteudoDaCasa.set(0, conteudoDaCasa.get(0).substring(0, conteudoDaCasa.get(0).length()-1)+cor+letra+corDoTabuleiro);
                            break;
                    }
                    indexLetra++;
                }
            }
            if (indexLetra <= 3) {
                conteudoDaCasa.set(0, "   ");
            }
            if (indexLetra == 0) {
                if (indexCasa + 1 < 10) {
                    conteudoDaCasa.set(1, corDosNumeros + " " + (indexCasa + 1) + " " + corDoTabuleiro);
                } else if (indexCasa + 1 < 100) {
                    conteudoDaCasa.set(1, corDosNumeros + (indexCasa + 1) + " " + corDoTabuleiro);
                } else {
                    conteudoDaCasa.set(1, corDosNumeros + (indexCasa + 1) + corDoTabuleiro);
                }
            }
        }

        String c = casa.getCaractere();
        // linha par
        if (indexLinha % 2 == 0) {
            // adicionar nova casa
            if ((indexCasa) % comprimento == 0) {
                if (indexCasa + 1 == numCasas) {
                    linhas[4 * indexLinha + 1] += "│" + c + conteudoDaCasa.get(0) + c + "│";
                    linhas[4 * indexLinha + 2] += "│ " + conteudoDaCasa.get(1) + " │";
                    linhas[4 * indexLinha + 3] += "│" + c + "   " + c + "│";
                } else {
                    linhas[4 * indexLinha + 1] += "│" + c + conteudoDaCasa.get(0) + c + "·";
                    linhas[4 * indexLinha + 2] += "│ " + conteudoDaCasa.get(1) + " ·";
                    linhas[4 * indexLinha + 3] += "│" + c + "   " + c + "·";
                }
            } else if (!((indexCasa + 1) % comprimento == 0 || indexCasa + 1 == numCasas)) {
                linhas[4 * indexLinha + 1] += c + conteudoDaCasa.get(0) + c + "·";
                linhas[4 * indexLinha + 2] += " " + conteudoDaCasa.get(1) + " ·";
                linhas[4 * indexLinha + 3] += c + "   " + c + "·";
            } else {
                linhas[4 * indexLinha + 1] += c + conteudoDaCasa.get(0) + c + "│";
                linhas[4 * indexLinha + 2] += " " + conteudoDaCasa.get(1) + " │";
                linhas[4 * indexLinha + 3] += c + "   " + c + "│";
            }

            if ((indexCasa) % comprimento == 0) {
                if (indexCasa + 19 < numCasas) {
                    linhas[4 * indexLinha + 4] += "├──────";
                } else {
                    if (indexCasa + 2 * (comprimento - ((indexCasa + 1) % comprimento)) + 1 == numCasas) {
                        linhas[4 * indexLinha + 4] += "└─────┬";
                    } else {
                        if (indexCasa + 1 == numCasas) {
                            linhas[4 * indexLinha + 4] += "└─────┘";
                        } else {
                            linhas[4 * indexLinha + 4] += "└──────";
                        }
                    }
                }
            } else if (!((indexCasa + 1) % comprimento == 0 || indexCasa + 1 == numCasas)) {
                if (indexCasa + 2 * (comprimento - ((indexCasa + 1) % comprimento)) + 1 == numCasas) {
                    linhas[4 * indexLinha + 4] += "─────┬";
                } else {
                    linhas[4 * indexLinha + 4] += "──────";
                }
            } else {
                if (indexCasa + 1 != numCasas) {
                    linhas[4 * indexLinha + 4] += "· · ·┤";
                } else {
                    linhas[4 * indexLinha + 4] += "─────┘";
                }
            }
            // linha ímpar
        } else {
            String[] linhasAntigas = new String[4];
            for (int i = 0; i < 4; i++) {
                linhasAntigas[i] = linhas[4 * indexLinha + i + 1];
            }

            // adicionar espaços vazios
            int quantidadeDeEspacos = comprimento - 1 - indexCasa % comprimento;
            linhas[4 * indexLinha + 1] = "";
            linhas[4 * indexLinha + 2] = "";
            linhas[4 * indexLinha + 3] = "";
            linhas[4 * indexLinha + 4] = "";
            for (int i = 0; i < quantidadeDeEspacos; i++) {
                linhas[4 * indexLinha + 1] += "      ";
                linhas[4 * indexLinha + 2] += "      ";
                linhas[4 * indexLinha + 3] += "      ";
                linhas[4 * indexLinha + 4] += "      ";
            }

            // adicionar nova casa
            if ((indexCasa) % comprimento == 0) {
                if (indexCasa + 1 == numCasas) {
                    linhas[4 * indexLinha + 1] += "│" + c + conteudoDaCasa.get(0) + c + "│";
                    linhas[4 * indexLinha + 2] += "│ " + conteudoDaCasa.get(1) + " │";
                    linhas[4 * indexLinha + 3] += "│" + c + "   " + c + "│";
                } else {
                    linhas[4 * indexLinha + 1] += "·" + c + conteudoDaCasa.get(0) + c + "│";
                    linhas[4 * indexLinha + 2] += "· " + conteudoDaCasa.get(1) + " │";
                    linhas[4 * indexLinha + 3] += "·" + c + "   " + c + "│";
                }
            } else if (!((indexCasa + 1) % comprimento == 0 || indexCasa + 1 == numCasas)) {
                linhas[4 * indexLinha + 1] += "·" + c + conteudoDaCasa.get(0) + c;
                linhas[4 * indexLinha + 2] += "· " + conteudoDaCasa.get(1) + " ";
                linhas[4 * indexLinha + 3] += "·" + c + "   " + c;
            } else {
                linhas[4 * indexLinha + 1] += "│" + c + conteudoDaCasa.get(0) + c;
                linhas[4 * indexLinha + 2] += "│ " + conteudoDaCasa.get(1) + " ";
                linhas[4 * indexLinha + 3] += "│" + c + "   " + c;
            }

            if ((indexCasa) % comprimento == 0) {
                if (indexCasa + 19 < numCasas) {
                    linhas[4 * indexLinha + 4] += "──────┤";
                } else {
                    if (indexCasa + 2 * (comprimento - ((indexCasa + 1) % comprimento)) + 1 == numCasas) {
                        linhas[4 * indexLinha + 4] += "┬─────┘";
                    } else {
                        if (indexCasa + 1 == numCasas) {
                            linhas[4 * indexLinha + 4] += "└─────┘";
                        } else {
                            linhas[4 * indexLinha + 4] += "──────┘";
                        }
                    }
                }
            } else if (!((indexCasa + 1) % comprimento == 0 || indexCasa + 1 == numCasas)) {
                if (indexCasa + 2 * (comprimento - ((indexCasa + 1) % comprimento)) + 1 == numCasas) {
                    linhas[4 * indexLinha + 4] += "┬─────";
                } else {
                    linhas[4 * indexLinha + 4] += "──────";
                }
            } else {
                if (indexCasa + 1 != numCasas) {
                    linhas[4 * indexLinha + 4] += "├· · ·";
                } else {
                    linhas[4 * indexLinha + 4] += "└─────";
                }
            }

            // adicionar casas anteriores
            if (indexCasa % comprimento != 0) {
                linhas[4 * indexLinha + 1] += linhasAntigas[0].substring(quantidadeDeEspacos * 6 + 6,
                        linhasAntigas[0].length());
                linhas[4 * indexLinha + 2] += linhasAntigas[1].substring(quantidadeDeEspacos * 6 + 6,
                        linhasAntigas[1].length());
                linhas[4 * indexLinha + 3] += linhasAntigas[2].substring(quantidadeDeEspacos * 6 + 6,
                        linhasAntigas[2].length());
                linhas[4 * indexLinha + 4] += linhasAntigas[3].substring(quantidadeDeEspacos * 6 + 6,
                        linhasAntigas[3].length());
            }
        }

        tabuleiro = String.join("\n", linhas);
        this.indexCasa++;
    }

    public ArrayList<Jogador> getJogadores() {
        return this.jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public int getNumJogadores() {
        return this.numJogadores;
    }

    public void setNumJogadores(int numJogadores) {
        this.numJogadores = numJogadores;
    }

    public int getNumCasas() {
        return this.numCasas;
    }
}

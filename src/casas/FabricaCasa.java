package casas;

public class FabricaCasa {
    public Casa criarCasaDaSorte() {
        return new CasaDaSorte();
    }

    public Casa criarCasaDoAzar() {
        return new CasaDoAzar();
    }

    public Casa criarCasaMagica() {
        return new CasaMagica();
    }

    public Casa criarCasaMalandra() {
        return new CasaMalandra();
    }

    public Casa criarCasaNormal() {
        return new CasaNormal();
    }

    public Casa criarCasaPresidio() {
        return new CasaPresidio();
    }

    public Casa criarCasaSurpresa() {
        return new CasaSurpresa();
    }

    public Casa criarCasaDeTroca() {
        return new CasaDeTroca();
    }
}
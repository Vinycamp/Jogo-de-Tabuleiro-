package validadores;

public class FabricaValidador {
    public Validador criarBooleanValidador() {
        return new BooleanValidador();
    }
    public Validador criarBooleanValidador(String boolean1, String boolean0) {
        return new BooleanValidador(boolean1, boolean0);
    }
    public Validador criarCoresValidador() {
        return new CoresValidador();
    }
    public Validador criarCoresValidador(boolean aceitarEnter) {
        return new CoresValidador(aceitarEnter);
    }
    public Validador criarDebugValidador(int numCasas) {
        return new DebugValidador(numCasas);
    }
    public Validador criarIntegerValidador() {
        return new IntegerValidador();
    }
    public Validador criarIntegerValidador(int min, int max) {
        return new IntegerValidador();
    }
    public Validador criarIntegerValidador(boolean aceitarEnter) {
        return new IntegerValidador(aceitarEnter);
    }
    public Validador criarIntegerValidador(int min, int max, boolean aceitarEnter) {
        return new IntegerValidador(min, max, aceitarEnter);
    }
    public Validador criarLojaValidador() {
        return new LojaValidador();
    }
}
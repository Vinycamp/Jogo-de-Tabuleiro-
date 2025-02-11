package validadores;

import exceptions.InvalidUserInputException;

public class DebugValidador implements Validador {
    private int numCasas;

    public DebugValidador(int numCasas) {
        this.numCasas = numCasas;
    }

    public void validar(String userInput) throws InvalidUserInputException {
        String[] corENumero = userInput.split(" ");
        if (corENumero.length != 2) {
            if (!userInput.equals("")) {
                throw new InvalidUserInputException();
            }
            return;
        }
        String cor = corENumero[0];
        String numero = corENumero[1];
        new CoresValidador().validar(cor);
        new IntegerValidador(1, numCasas).validar(numero);
    }
}

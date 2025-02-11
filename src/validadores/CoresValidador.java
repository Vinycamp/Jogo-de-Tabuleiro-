package validadores;

import exceptions.InvalidUserInputException;

import java.util.ArrayList;

public class CoresValidador implements Validador {
    private boolean aceitarEnter = false;

    public CoresValidador() {
    }

    public CoresValidador(boolean aceitarEnter) {
        this.aceitarEnter = aceitarEnter;
    }

    public void validar(String userInput) throws InvalidUserInputException {
        ArrayList<String> respostasValidas = new ArrayList<>();
        respostasValidas.add("azul");
        respostasValidas.add("verde");
        respostasValidas.add("roxo");
        respostasValidas.add("amarelo");
        respostasValidas.add("vermelho");
        respostasValidas.add("cinza");
        if (this.aceitarEnter) {
            respostasValidas.add("");
        }
        for (int i = 0; i < userInput.length(); i++) {
            if (!respostasValidas.contains(userInput)) {
                throw new InvalidUserInputException();
            }
        }
    }
}

package validadores;

import java.util.ArrayList;

import exceptions.InvalidUserInputException;

public class BooleanValidador implements Validador {
    private String boolean1 = "1";
    private String boolean0 = "0";

    public BooleanValidador() {
    }

    public BooleanValidador(String boolean1, String boolean0) {
        this.boolean1 = boolean1;
        this.boolean0 = boolean0;
    }

    public void validar(String userInput) throws InvalidUserInputException {
        ArrayList<String> respostasValidas = new ArrayList<>();
        respostasValidas.add(boolean1);
        respostasValidas.add(boolean0);
        for (int i = 0; i < userInput.length(); i++) {
            if (!respostasValidas.contains(String.valueOf(userInput.charAt(i)))) {
                throw new InvalidUserInputException();
            }
        }
    }
}
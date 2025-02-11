package validadores;

import java.util.ArrayList;

import exceptions.InvalidUserInputException;

public class IntegerValidador implements Validador {
    private int min = 0;
    private int max = 0;
    private boolean limited = false;
    private boolean aceitarEnter = false;

    public IntegerValidador() {
    }

    public IntegerValidador(int min, int max) {
        this.min = min;
        this.max = max;
        this.limited = true;
    }

    public IntegerValidador(boolean aceitarEnter) {
        this.aceitarEnter = aceitarEnter;
    }

    public IntegerValidador(int min, int max, boolean aceitarEnter) {
        this.min = min;
        this.max = max;
        this.limited = true;
        this.aceitarEnter = aceitarEnter;
    }

    public void validar(String userInput) throws InvalidUserInputException {
        ArrayList<String> respostasValidas = new ArrayList<>();
        respostasValidas.add("0");
        respostasValidas.add("1");
        respostasValidas.add("2");
        respostasValidas.add("3");
        respostasValidas.add("4");
        respostasValidas.add("5");
        respostasValidas.add("6");
        respostasValidas.add("7");
        respostasValidas.add("8");
        respostasValidas.add("9");
        if (this.aceitarEnter) {
            respostasValidas.add("");
        }
        for (int i = 0; i < userInput.length(); i++) {
            if (!respostasValidas.contains(String.valueOf(userInput.charAt(i)))) {
                throw new InvalidUserInputException();
            }
        }
        if (limited) {
            int userInputInt = Integer.parseInt(userInput);
            if (userInputInt < min || userInputInt > max) {
                throw new InvalidUserInputException();
            }
        }
    }
}

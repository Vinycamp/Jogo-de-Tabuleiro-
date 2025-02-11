package validadores;

import exceptions.InvalidUserInputException;

import java.util.ArrayList;

public class LojaValidador implements Validador{
    public void validar(String userInput) throws InvalidUserInputException {
        ArrayList<String> respostasValidas = new ArrayList<>();
        respostasValidas.add("bone");
        respostasValidas.add("moleton");
        respostasValidas.add("oculos escuros");
        respostasValidas.add("sair");
        if (!respostasValidas.contains(userInput)) {
            throw new InvalidUserInputException();
        }
        if (userInput.isEmpty()) {
            throw new InvalidUserInputException();
        }
    }
}

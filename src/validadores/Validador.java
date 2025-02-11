package validadores;

import exceptions.InvalidUserInputException;

public interface Validador {
    public abstract void validar(String userInput) throws InvalidUserInputException;
}

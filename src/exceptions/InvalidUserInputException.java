package exceptions;

public class InvalidUserInputException extends Exception {
    public InvalidUserInputException() {
        super("Você digitou uma opção inválida.");
    }
}
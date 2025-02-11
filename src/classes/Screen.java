package classes;

import exceptions.InvalidUserInputException;
import validadores.Validador;
import java.util.Scanner;

public class Screen {
    private boolean toLowerCase = true;
    private boolean ignorarAcentos = false;

    public String inputIgnorarAcentos(String request, Validador validador, boolean toLowerCase) {
        this.toLowerCase = toLowerCase;
        this.ignorarAcentos = true;
        String userInput = this.input(request, validador);
        this.ignorarAcentos = false;
        this.toLowerCase = true;
        return userInput;
    }

    public String inputIgnorarAcentos(String request, Validador validador) {
        this.ignorarAcentos = true;
        String userInput = this.input(request, validador);
        this.ignorarAcentos = false;
        return userInput;
    }

    public String input(String request, Validador validador, boolean toLowerCase) {
        this.toLowerCase = toLowerCase;
        String userInput = this.input(request, validador);
        this.toLowerCase = true;
        return userInput;
    }

    public String input(String request, Validador validador) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println(request);
            if (this.toLowerCase) {
                userInput = scanner.nextLine().toLowerCase();
            } else {
                userInput = scanner.nextLine();
            }
            if (this.ignorarAcentos) {
                userInput = userInput.replaceAll("ó", "o");
                userInput = userInput.replaceAll("é", "e");
            }
            try {
                validador.validar(userInput);
                break;
            } catch (InvalidUserInputException e) {
                this.clear();
                System.out.println("Você digitou uma opção inválida. Tente novamente.\n");
            }
        } while (true);
        return userInput;
    }

    public String input(String request) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println(request);
        userInput = scanner.nextLine();
        return userInput;
    }

    public void clear(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("");
        }
    }

    public void clear() {
        for (int i = 0; i < 60; i++) {
            System.out.println("");
        }
    }
}
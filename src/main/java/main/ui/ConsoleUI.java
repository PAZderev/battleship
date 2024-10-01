package main.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI implements UserInterface {
    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleUI(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    @Override
    public void displayMessage(String message) {
        out.println(message);
    }

    @Override
    public int[] getMove() {
        int row = -1, col = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                out.println("Введите координаты выстрела (строка и столбец от 1 до 10 через пробел):");
                String[] input = scanner.nextLine().trim().split("\\s+");
                if (input.length != 2) {
                    throw new NumberFormatException();
                }
                row = Integer.parseInt(input[0]) - 1; // Преобразуем в индексы от 0 до 9
                col = Integer.parseInt(input[1]) - 1;
                if (row < 0 || row >= 10 || col < 0 || col >= 10) {
                    out.println("Координаты вне диапазона. Пожалуйста, введите числа от 1 до 10.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                out.println("Неверный формат ввода. Пожалуйста, введите два числа, разделенных пробелом.");
            }
        }
        return new int[]{row, col};
    }
}

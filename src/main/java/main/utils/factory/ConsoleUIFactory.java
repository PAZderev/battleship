package main.utils.factory;


import main.ui.ConsoleUI;
import main.ui.UserInterface;

public class ConsoleUIFactory {
    private static UserInterface instance;

    private ConsoleUIFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static UserInterface getInstance() {
        if (instance == null) {
            instance = new ConsoleUI(System.in, System.out);
        }
        return instance;
    }
}

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NaturalNumbersInterpretation {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        while(true) {
            System.out.println("\n" +
                    "Please insert a comma separated sequence of numbers to generate phone number combinations" +
                    " or type exit to terminate the application");
            try {
                String keyBoardInput = SCANNER.nextLine();
                if(keyBoardInput.trim().equalsIgnoreCase("exit")) {
                    System.out.println("Terminating the application...");
                    System.exit(0);
                }
                PhoneCombinationInterpreter.interpret(keyBoardInput);
            } catch (IllegalArgumentException exception) {
                LOGGER.log(Level.SEVERE, exception.getMessage());
            }
        }
    }
}
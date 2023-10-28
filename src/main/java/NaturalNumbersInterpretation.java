import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NaturalNumbersInterpretation {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {
        while(true) {
            System.out.println("""
                Choose a number:
                0. Terminate the application
                1. Phone number validation
                2. Identify natural number ambiguities""");
            String keyBoardInput = SCANNER.nextLine();
            switch (keyBoardInput) {
                case "0" :
                    System.exit(0);
                    break;
                case "1" :
                    phoneNumberValidation();
                    break;
                case "2" :
                    identifyNaturalNumberAmbiguities();
                    break;
                default:
                    System.out.println("You must choose a number between 0-2\n");
                    break;
            }
        }
    }

    private static void phoneNumberValidation() {
        System.out.println("\nPlease insert a comma separated sequence of numbers to validate phone number is Greek");
        try {
            String keyBoardInput = String.join("", SCANNER.nextLine().split("\\s"));
            if(PhoneNumberValidator.isValidGreekNumber(keyBoardInput)) {
                System.out.println(keyBoardInput + "  [phone number: VALID]");
            } else {
                System.out.println(keyBoardInput + "  [phone number: INVALID]");
            }
        } catch (IllegalArgumentException exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage());
        }
    }

    private static void identifyNaturalNumberAmbiguities() {
        System.out.println("\nPlease insert a comma separated sequence of numbers to identify phone number combinations");
        try {
            String keyBoardInput = SCANNER.nextLine();
            PhoneCombinationInterpreter.interpret(keyBoardInput);
        } catch (IllegalArgumentException exception) {
            LOGGER.log(Level.SEVERE, exception.getMessage());
        }
    }
}
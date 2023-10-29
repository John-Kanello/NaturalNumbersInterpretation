import java.util.Arrays;
import java.util.List;

public class PhoneCombinationInterpreter {
    public static void interpret(String input) {
        if(InputValidator.isValidInput(input)) {
            String[] numbers = input
                    .trim()
                    .replaceAll("\\s{2,}", " ")
                    .split("\\s");
            if(Arrays.stream(numbers).map(String :: length).mapToInt(len -> len).anyMatch(length -> length > 3)) {
                throw new IllegalArgumentException(PhoneCombinationInterpreter.class.getSimpleName() +
                        ": Number cannot be longer than 3 digits\n");
            }
            List<String> combinations = PhoneCombinationGenerator.generateCombinations(numbers);
            interpretCombinations(combinations);
        }
    }

    private static void interpretCombinations(List<String> combinations) {
        for(int i = 0; i < combinations.size(); i++) {
            String combination = combinations.get(i);
            if(PhoneNumberValidator.isValidGreekNumber(combination)) {
                System.out.println("Interpretation " + i + ": " + combination + "  [phone number: VALID]");
            } else {
                System.out.println("Interpretation " + i + ": " + combination + "  [phone number: INVALID]");
            }
        }
        System.out.println();
    }
}
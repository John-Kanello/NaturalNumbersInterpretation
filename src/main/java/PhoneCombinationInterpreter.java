import java.util.Arrays;
import java.util.List;

public class PhoneCombinationInterpreter {
    public static void interpret(String input) {
        if(input == null || input.isEmpty() ) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        for(char c : input.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isSpaceChar(c)) {
                throw new IllegalArgumentException("Phone number can only contains numbers and spaces");
            }
        }
        String[] numbers = input
                .trim()
                .replaceAll("\\s{2,}", " ")
                .split("\\s");
        if(Arrays.stream(numbers).map(String :: length).mapToInt(len -> len).anyMatch(length -> length > 3)) {
            throw new IllegalArgumentException("Number cannot be longer than 3 digits");
        }
        List<String> combinations = PhoneCombinationGenerator.generateCombinations(numbers);
        evaluateCombinations(combinations);
    }

    private static void evaluateCombinations(List<String> combinations) {
        if(combinations.isEmpty()) {
            System.out.println("No valid combinations were found");
        }
        for(int i = 0; i < combinations.size(); i++) {
            String combination = combinations.get(i);
            if(PhoneNumberValidator.isValidGreekNumber(combination)) {
                System.out.println("Interpretation " + i + ": " + combination + "  [phone number: VALID]");
            } else {
                System.out.println("Interpretation " + i + ": " + combination + "  [phone number: INVALID]");
            }
        }
    }
}
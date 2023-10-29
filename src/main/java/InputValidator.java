public class InputValidator {
    public static boolean isValidInput(String input) {
        if(input == null || input.isEmpty() ) {
            throw new IllegalArgumentException("Phone number cannot be null or empty\n");
        }
        for(char c : input.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isSpaceChar(c)) {
                throw new IllegalArgumentException("Phone number can only contains numbers and spaces\n");
            }
        }
        return true;
    }
}

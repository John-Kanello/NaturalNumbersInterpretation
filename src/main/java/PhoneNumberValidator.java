public class PhoneNumberValidator {
    public static boolean isValidGreekNumber(String input) {
        if(input == null || input.isEmpty()) {
            return false;
        }
        String phoneNumber = String.join("", input.split("\\s"));
        for(char c : phoneNumber.toCharArray()) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        int textLength = phoneNumber.length();
        if(textLength != 10 && textLength != 14) {
            return false;
        }
        if(textLength == 10 && !phoneNumber.startsWith("2") && !phoneNumber.startsWith("69")) {
            return false;
        }
        if(textLength == 14 && !phoneNumber.startsWith("00302") && !phoneNumber.startsWith("003069")) {
            return false;
        }
        return true;
    }
}
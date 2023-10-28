public class PhoneNumberValidator {
    public static boolean isValidGreekNumber(String input) {
        if(input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        for(char c : input.toCharArray()) {
            if(!Character.isDigit(c)) {
                throw new IllegalArgumentException("Phone number can only contains numbers and spaces");
            }
        }
        String phoneNumber = String.join("", input.split("\\s"));
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

    public static void validate(String phoneNumber) {
        if(isValidGreekNumber(phoneNumber)) {
            System.out.println(phoneNumber + "  [phone number: VALID]");
        } else {
            System.out.println(phoneNumber + "  [phone number: INVALID]");
        }
    }
}
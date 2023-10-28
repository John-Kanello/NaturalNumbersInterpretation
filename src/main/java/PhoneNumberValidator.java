public class PhoneNumberValidator {
    public static boolean isValidGreekNumber(String phoneNumber) {
        if(phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        for(char c : phoneNumber.toCharArray()) {
            if(!Character.isDigit(c)) {
                throw new IllegalArgumentException("Phone number can only contains numbers and spaces");
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
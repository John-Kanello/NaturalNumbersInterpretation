public class PhoneNumberValidator {
    public static boolean isValidGreekNumber(String input) {
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
            System.out.println(phoneNumber.replaceAll("\\s","") + "  [phone number: VALID]");
        } else {
            System.out.println(phoneNumber.replaceAll("\\s","") + "  [phone number: INVALID]");
        }
        System.out.println();
    }
}
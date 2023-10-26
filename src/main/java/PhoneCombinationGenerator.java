import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PhoneCombinationGenerator {

    public static List<String> generateCombinations(String[] numbers) {
        return generateCombinations(numbers, 0, "", new ArrayList<>(), true);
    }
    private static List<String> generateCombinations(String[] numbers,
                                                     int index,
                                                     String phoneNumber,
                                                     List<String> combinations,
                                                     boolean canExtendCombination) {
        if(index == numbers.length) {
            if(!phoneNumber.startsWith("2") && !phoneNumber.startsWith("69")
                    && !phoneNumber.startsWith("00302") && !phoneNumber.startsWith("003069")) {
                return combinations;
            }
            combinations.add(phoneNumber);
            return combinations;
        }
        // 1st case
        // Checks for combinations inside the current phoneNumber
        // For example 69 can be interpreted as 69 or 609
        // Another example is 425 which in Greek can be interpreted as (425, 40025, 400205)
        String currNumber = numbers[index];
        StringBuilder prefixNumber = new StringBuilder(currNumber);
        StringBuilder suffixNumber = new StringBuilder();
        for(int i = currNumber.length() - 1; i > 0; i--) {
            suffixNumber.insert(0, currNumber.charAt(i));
            if(currNumber.charAt(i) == '0') {
                continue;
            }
            prefixNumber.setCharAt(i, '0');
            if(prefixNumber.toString().equals("0".repeat(prefixNumber.length()))) {
                break;
            }
            // Splits numbers and explores for future combinations
            // For example 78 can be split to 708
            generateCombinations(numbers, index + 1, phoneNumber + prefixNumber + suffixNumber, combinations, true);
            // Backtracking step
            // As an example assume we have the numbers 640 and 2.
            // These two numbers can also be interpreted as 60042.
            // To achieve this behavior we concatenate the running phoneNumber(empty string in this case) with the
            // prefixNumber which is 600, then set the number at the current index to suffixNumber. Finally, we check
            // if the next number which is this case is 2 can be combined with the suffixNumber which is 40.
            // This way, we can generate the combination 60042.
            numbers[index] = suffixNumber.toString();
            generateCombinations(numbers,index,phoneNumber + prefixNumber, combinations, false);
            numbers[index] = currNumber;
        }
        // 2nd case
        // Checks if currentNumber can be combined with future numbers
        // For example "60 9" can be interpreted as 69
        // Similarly, "200 20 2" can be interpreted as 222
        BigInteger sum = new BigInteger(currNumber);
        for(int i = index + 1; i < numbers.length; i++) {
            // We can only combine numbers if the first number is greater than the second and
            // the first number ends with '0's equal or greater to the length of iTh number.
            String sumString = String.valueOf(sum);
            int len = numbers[i].length();
            if(len < sumString.length() && sumString.substring(sumString.length() - len).equals("0".repeat(len))) {
                sum = sum.add(new BigInteger(numbers[i]));
                generateCombinations(numbers, i + 1, phoneNumber + sum, combinations, true);
            } else {
                break;
            }
        }
        if(!canExtendCombination) { // cannot use current number while backtracking
            return combinations;    // as it will result in duplicate combination
        }
        // 3rd case
        // We simply extend the running phoneNumber with the current number
        return generateCombinations(numbers, index + 1, phoneNumber + currNumber, combinations, true);
    }
}
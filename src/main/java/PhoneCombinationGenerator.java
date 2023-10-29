import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhoneCombinationGenerator {
    public static List<String> generateCombinations(String[] numbers) {
        return generateCombinations(numbers, 0, "", new ArrayList<>(), true);
    }
    private static List<String> generateCombinations(String[] numbers,
                                                     int index,
                                                     String combination,
                                                     List<String> combinations,
                                                     boolean canExtendCombination) {
        if(index == numbers.length) {
            combinations.add(combination);
            return combinations;
        }
         /*
         1st case
         Check for combinations inside the current combination by splitting the number to its addends.
         For example 69 can be interpreted as 69 or 609
         Another example is 425 which can be interpreted as (425, 40025, 400205, 4205)
         */
        findCombinationsInsideNumber(numbers, index, combination, combinations);
        /*
        2nd case
         Check if the current number can be combined with future numbers.
         For example "60 9" can be interpreted as 69
         Similarly, "200 20 2" can be interpreted as 222
         */
        combineNumberWithFutureNumbers(numbers, index, combination, combinations);
         /*
         3rd case
         Extend the running combination by concatenating it with the current number.
         Note, we cannot extend the combination if we have split the current number to its addends.
         See findCombinationsInsideNumberAtIndex for further explanation.
         */
        if(!canExtendCombination) {
            return combinations;
        }
        return generateCombinations(numbers, index + 1, combination + numbers[index], combinations, true);
    }

    private static void findCombinationsInsideNumber(String[] numbers,
                                                     int index,
                                                     String combination,
                                                     List<String> combinations) {
        String currNumber = numbers[index];
        // If the number is smaller than or equal to 20, no combinations can be formed by
        // breaking the number to its addends in the English language.
        // Note, Integer.parseInt erases leading zeroes so numbers like 021 are considered greater than 20
        if(Integer.parseInt(currNumber) <= 20) {
            return;
        }
        // I use 2 StringBuilder objects to break the original number to its addends.
        // prefixNumber is the left and greater addend of the number and suffixNumber is the right and smaller addend.
        // The greater addend we will have to be divisible by 10 to a form a valid split
        StringBuilder prefixNumber = new StringBuilder(currNumber);
        StringBuilder suffixNumber = new StringBuilder();
        // Edge case
        // If the final two digits of the number are in the range [11-19],
        // then that part of the number cannot be split to its addends.
        // That is because, the numbers eleven...nineteen have no ambiguous uttering in the English language
        // and 10 cannot be added with a number from [1-9] to form a valid combination
        int finalTwoDigitsOfNumber = Integer.parseInt(currNumber.substring(currNumber.length() - 2));
        int startingIndex = currNumber.length() - 1;
        if(finalTwoDigitsOfNumber >= 11 && finalTwoDigitsOfNumber <= 19) {
            suffixNumber.insert(0, currNumber.charAt(startingIndex));
            prefixNumber.setCharAt(startingIndex, '0');
            startingIndex--;
        }
        // Continuously shrink the prefixNumber and expand the suffixNumber
        // while the prefixNumber is not a sequence of '0's
        for (int i = startingIndex; i > 0; i--) {
            suffixNumber.insert(0, currNumber.charAt(i));
            if (currNumber.charAt(i) == '0') {
                continue;
            }
            prefixNumber.setCharAt(i, '0');
            if (prefixNumber.toString().equals("0".repeat(prefixNumber.length()))) {
                break;
            }
            // Concatenate the running combination with the greater addend and smaller addend of the current number
            generateCombinations(numbers, index + 1, combination + prefixNumber + suffixNumber, combinations, true);
            // Backtracking step
            // As an example, assume we have the numbers 640 and 2.
            // These two numbers can also be interpreted as 60042 which means we will split 640 to 600 + 40
            // and then concatenate 40 with 2.
            // To achieve this behavior we set the number at the current index to suffixNumber which is 40, and we
            // concatenate the running combination(empty string in this case) with the prefixNumber which is 600.
            // Then, we can concatenate 40 and 2 to form the combination 60042.
            // Finally, we have to restore the number at the current index to its original state.
            // Note, canExtendCombination parameter is false because otherwise we will get duplicate combinations
            numbers[index] = suffixNumber.toString();
            generateCombinations(numbers, index, combination + prefixNumber, combinations, false);
            numbers[index] = currNumber;
        }
    }

    private static void combineNumberWithFutureNumbers(String[] numbers,
                                                       int index,
                                                       String combination,
                                                       List<String> combinations) {
        String currNumber = numbers[index];
        // Keep track of leading zeroes because BigInteger erases them
        int numOfLeadingZeroes = countLeadingZeroes(currNumber);
        String leadingZeroes = "0".repeat(numOfLeadingZeroes);
        // Convert the current number to a BigInteger object and continuously add future numbers to the sum
        // while the sum is not dibisible by '10' or the next number starts with '0'.
        // That is because, a number ending with '10' cannot form a valid combination when adding with a number.
        // Also, a number ending with '0' is not a valid number which can be added to the sum
        BigInteger sum = new BigInteger(currNumber);
        for(int i = index + 1; i < numbers.length; i++) {
            if(sum.toString().endsWith("10") || numbers[i].startsWith("0")) {
                break;
            }
            // We can only combine numbers if the first number is greater than the second in length
            // and the first number ends with '0's equal or greater to the length of the current number
            String sumString = String.valueOf(sum);
            int sumStringLen = sumString.length();
            String nextNumber = numbers[i];
            int numberLen = nextNumber.length();
            if(sumStringLen > numberLen && sumString.substring(sumStringLen - numberLen).equals("0".repeat(numberLen))) {
                sum = sum.add(new BigInteger(numbers[i]));
                generateCombinations(numbers, i + 1, combination + leadingZeroes + sum, combinations, true);
                // Backtracking step
                // Check if the larger addend of the next number can be combined with the current number
                // The next number must not be in the range [11-19] and must not be divisible by 10.
                // Set the next number to be the smaller addend.
                // Concatenate the running combination with the sum of the current number with the larger addend.
                // Backtrack to the original state of the array
                if(numberLen == 2 && !nextNumber.startsWith("1") && !nextNumber.endsWith("0")) {
                    String largerAddend = nextNumber.substring(0, numberLen - 1) + "0";
                    String smallerAddend = nextNumber.substring(numberLen - 1);
                    String nextSum = String.valueOf(new BigInteger(currNumber).add(new BigInteger(largerAddend)));
                    numbers[i] = smallerAddend;
                    generateCombinations(numbers, i, combination + nextSum, combinations, true);
                    numbers[i] = nextNumber;
                }
            } else {
                break;
            }
        }
    }

    private static int countLeadingZeroes(String number) {
        int leadingZeroes = 0;
        for(int i = 0; i < number.length(); i++) {
            if(number.charAt(i) != '0') {
                break;
            }
            leadingZeroes++;
        }
        return leadingZeroes;
    }
}
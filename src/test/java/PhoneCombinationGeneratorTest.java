import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneCombinationGeneratorTest {

    @Test
    public void formCombinationBySplittingNumberToAddends() {
        assertEquals(Stream.of("2106930664", "210693664", "2106093664", "21060930664")
                        .sorted()
                        .collect(Collectors.toList()),
                PhoneCombinationGenerator.generateCombinations(new String[]{"2", "10", "69", "30", "6", "6", "4"})
                        .stream()
                        .sorted()
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void formCombinationByCombiningNumberWithTheNextNumber() {
        assertEquals(Stream.of("2106930664", "210693664", "2106093664",
                "21060930664")
                .sorted()
                .collect(Collectors.toList()),
                PhoneCombinationGenerator.generateCombinations(new String[]{"2", "10", "69", "30", "6", "6", "4"})
                        .stream()
                        .sorted()
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void formCombinationsBySplittingNumberToAddendsAndCombiningNumberWithTheNextNumber() {
        assertEquals(Stream.of("00306097241352","003060972413502","003060972041352"
                ,"0030609720413502", "00306097002041352", "003060970020413502",
                                "0030609700241352", "00306097002413502", "0030697241352",
                                "00306972413502", "00306972041352", "003069720413502",
                                "0030697002041352", "00306970020413502", "003069700241352",
                                "0030697002413502")
                .sorted()
                .collect(Collectors.toList()),
                PhoneCombinationGenerator.generateCombinations(
                        new String[]{"0", "0", "30", "69", "700" ,"24" ,"1", "3", "50", "2"})
                        .stream()
                        .sorted()
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void formCombinationBySequentiallyConcatenatingNumbers() {
        assertEquals(List.of("2101234567"),
                PhoneCombinationGenerator.generateCombinations(new String[]{"2","1","0","1","2","3","4","5","6","7"}));
    }
}
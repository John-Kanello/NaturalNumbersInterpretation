import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {

    @Test
    public void validatePhoneNumberStartingWith2isValid() {
        assertTrue(PhoneNumberValidator.isValidGreekNumber("2106966489"));
    }

    @Test
    public void validatePhoneNumberStartingWith2isInvalid() {
        assertFalse(PhoneNumberValidator.isValidGreekNumber("21012345678"));
    }

    @Test
    public void validatePhoneNumberStartingWith69isValid() {
        assertTrue(PhoneNumberValidator.isValidGreekNumber("6912345678"));
    }

    @Test
    public void validatePhoneNumberIsInvalid() {
        assertFalse(PhoneNumberValidator.isValidGreekNumber("6012983745"));
    }

    @Test
    public void validatePhoneNumberContainingNonDigitsIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            PhoneNumberValidator.isValidGreekNumber("69312319f");
        });
    }
}
package qwerty.ui;

import org.junit.jupiter.api.Test;

import qwerty.exceptions.InvalidIndexException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    
    @Test
    void testValidateIndexThrowsExceptionForInvalidString() {

        String invalidDesc = "invalid";

        assertThrows(InvalidIndexException.class, () -> {
            Parser.validateIndex(invalidDesc);
        });
    }

}

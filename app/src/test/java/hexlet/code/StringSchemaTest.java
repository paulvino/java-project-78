package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    void testIsValidWithEmptyString() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
    }

    @Test
    void testIsValidWithNullString() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(null));
    }

    @Test
    void testIsValidWithRequiredString() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("Hello"));
    }

    @Test
    void testIsValidWithMinLengthString() {
        Validator v = new Validator();
        StringSchema schema = v.string().minLength(3);

        assertTrue(schema.isValid("Hello"));
        assertFalse(schema.isValid("Hi"));
        assertTrue(schema.isValid("Aha"));
    }

    @Test
    void testIsValidWithRequiredAndMinLengthString() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(2);

        assertTrue(schema.isValid("Om"));
        assertTrue(schema.isValid("Yoyoyo"));
        assertFalse(schema.isValid("."));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsValidWithContainsString() {
        Validator v = new Validator();
        StringSchema schema = v.string().contains("pre");

        assertTrue(schema.isValid("Pereprepanation"));
        assertFalse(schema.isValid("perperpendic"));
    }

    @Test
    void testIsValidAllConditionsString() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(5).contains("ma");

        assertTrue(schema.isValid("We only humans after all"));
        assertTrue(schema.isValid("In does not even matter"));
        assertFalse(schema.isValid("wops"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("America is great, isn't it?"));
    }
}

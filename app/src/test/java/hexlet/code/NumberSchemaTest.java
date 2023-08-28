package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {

    @Test
    void testIsValidWithNullNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
    }

    @Test
    void testIsValidWithRequiredNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("3"));
        assertTrue(schema.isValid(3));
    }

    @Test
    void testIsValidWithPositiveNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(-2));

        schema.positive();

        assertFalse(schema.isValid(-2));
        assertTrue(schema.isValid(2));
    }

    @Test
    void testIsValidWithRequiredAndPositiveNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number().required().positive();

        assertTrue(schema.isValid(1));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-3));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsValidWithRangeNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number().range(2, 4);

        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(4));
        assertTrue(schema.isValid(3));
        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(5));
    }

    @Test
    void testIsValidAllConditionsNumber() {
        Validator v1 = new Validator();
        NumberSchema schema1 = v1.number().required().positive().range(2, 5);

        assertTrue(schema1.isValid(2));
        assertTrue(schema1.isValid(5));
        assertFalse(schema1.isValid(0));

        Validator v2 = new Validator();
        NumberSchema schema2 = v2.number().required().positive().range(-3, 4);
        assertFalse(schema2.isValid(-2));
        assertTrue(schema2.isValid(1));
    }
}

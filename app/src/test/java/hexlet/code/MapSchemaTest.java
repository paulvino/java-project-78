package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {
    @Test
    void testIsValidWithNullMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
    }

    @Test
    void testIsValidWithRequiredMap() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap()));
    }

    @Test
    void testIsValidWithSizeOfMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, String> data = new HashMap<>();

        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }
}

package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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

        schema.sizeOf(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testIsValidWithShapeMap() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "VarisTheMermaid");
        human1.put("age", 153);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Obi-Wan");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Fetus");
        human4.put("age", -3);
        assertFalse(schema.isValid(human4));
    }
}

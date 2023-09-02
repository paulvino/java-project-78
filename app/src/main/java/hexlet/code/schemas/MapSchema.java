package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        addCheck("typeData", value -> value instanceof Map<?, ?> || value == null);
    }

    public MapSchema required() {
        addCheck("required", value -> value instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeOf(int size) {
        addCheck("sizeOf", value -> ((Map) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addCheck("shape", value -> schemas.entrySet()
                .stream()
                .allMatch(el -> el.getValue()
                                .isValid(((Map) value)
                                .get(el.getKey()))));
        return this;
    }
}

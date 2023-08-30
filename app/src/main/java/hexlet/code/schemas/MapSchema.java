package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        checks = new LinkedHashMap<>();
        addCheck("typeData", value -> value instanceof Map<?, ?> || value == null);
    }

    public MapSchema required() {
        addCheck("required", value -> value instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> ((Map) value).size() == size);
        return this;
    }
}

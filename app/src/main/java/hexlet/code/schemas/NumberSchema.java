package hexlet.code.schemas;

import java.util.LinkedHashMap;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        checks = new LinkedHashMap<>();
        addCheck("typeData", value -> value instanceof Integer || value == null);
    }

    public NumberSchema required() {
        addCheck("required", value -> value instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", value -> ((int) value > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> (int) value >= min && (int) value <= max);
        return this;
    }
}

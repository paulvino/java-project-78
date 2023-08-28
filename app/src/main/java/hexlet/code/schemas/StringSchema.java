package hexlet.code.schemas;

import java.util.LinkedHashMap;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        checks = new LinkedHashMap<>();
        addCheck("typeData", value -> value instanceof String || value == null);
    }

    public StringSchema required() {
        addCheck("required", value -> value instanceof String && !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int num) {
        addCheck("minLength", value -> ((String) value).length() >= num);
        return this;
    }

    public StringSchema contains(String str) {
        addCheck("contains", value -> value.toString().contains(str));
        return this;
    }
}

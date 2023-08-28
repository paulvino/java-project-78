package hexlet.code.schemas;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected Map<String, Predicate<Object>> checks = new LinkedHashMap<>();

    protected final void addCheck(String name, Predicate<Object> check) {
        checks.put(name, check);
    }

    public boolean isValid(Object value) {
        for (Predicate<Object> validate: checks.values()) {
            if (!validate.test(value)) {
                return false;
            }
        }
        return true;
    }
}

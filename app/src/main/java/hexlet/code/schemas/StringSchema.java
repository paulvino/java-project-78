package hexlet.code.schemas;

public final class StringSchema {
    private boolean required;
    private boolean minLength;
    private boolean contains;
    private int minLengthOfString;
    private String content;

    public StringSchema() {
        required = false;
        minLength = false;
        contains = false;
    }

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int num) {
        minLength = true;
        minLengthOfString = num;
        return this;
    }

    public StringSchema contains(String str) {
        contains = true;
        content = str;
        return this;
    }

    public boolean isValid(Object data) {
        return isRequired(data) && isMinLength(data) && isContains(data);
    }

    private boolean isRequired(Object data) {
        if (required) {
            return data != null && !data.equals("");
        }
        return true;
    }

    private boolean isMinLength(Object data) {
        if (minLength) {
            return data.toString().length() >= minLengthOfString;
        }
        return true;
    }

    private boolean isContains(Object data) {
        if (contains) {
            return data.toString().contains(content);
        }
        return true;
    }
}

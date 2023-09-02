# Data validator

### Hexlet tests and linter status:
[![Actions Status](https://github.com/paulvino/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/paulvino/java-project-78/actions)
[![Actions Status](https://github.com/paulvino/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/paulvino/java-project-78/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/bdbaf3df903401712f4d/maintainability)](https://codeclimate.com/github/paulvino/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/bdbaf3df903401712f4d/test_coverage)](https://codeclimate.com/github/paulvino/java-project-78/test_coverage)

### Description:
Data validator is a library that can be used to check the correctness of any data. First of all, we are talking about form data filled in by users. The [yup](https://github.com/jquense/yup) library is taken as a basis for the project.

It is three types of data supported for the moment: String, Integer (Number) and Map. To get started with the library just create a new Validator object:

``` java
Validator v = new Validator();
```

Then select one of the supported schemes (a new Validator object must be created for a new validation scheme, otherwise the library may not work correctly).

#### String Schema
String schema contains three validation methods:
* required() - string can not be null or empty
* minLength() - string must be longer than limiter, passed as parameter (or the same length)
* contains() - string must contain the substring, passed as parameter

``` java
Validator vStr = new Validator();
StringSchema stringSchema = vStr.string();

stringSchema.isValid(""); // true
stringSchema.isValid(null); // true

stringSchema.required();
stringSchema.isValid(""); // false
stringSchema.isValid(null); // false
stringSchema.isValid("Hello!"); // true

stringSchema.minLength(2);
stringSchema.isValid("Om"); // true
stringSchema.isValid("Yoyoyo"); // true
stringSchema.isValid("."); // false

stringSchema.contains("ma");
stringSchema.isValid("We only humans after all"); // true;
stringSchema.isValid("It does not even matter"); // true
assertFalse(schema.isValid("whoops")); // false 
```

#### Number Schema
Number schema contains three validation methods:
* required() - number can not be null, passed data must be type Integer
* positive() - number must be more than zero
* range() - number must be greater than the minimum value passed as a parameter and less than the maximum value

``` java 
Validator vNum = new Validator();
NumberSchema numberSchema = vNum.number();

numberSchema.isValid(null); // true
numberSchema.isValid(0); // true

numberSchema.required();
numberSchema.isValid(null); // false
numberSchema.isValid(0); // true

numberSchema.positive();
numberSchema.isValid(0); // false
numberSchema.isValid(-1); // false
numberSchema.isValid(3); // true

numberSchema.range(-2, 6);
numberSchema.isValid(0); // false, because validation methods complement (not replace) each other
numberSchema.isValid(-1); // false
numberSchema.isValid(3); // true
```

#### Map Schema
Map schema contains three validation methods:
* required() - passed value must not equal null and must be a Map type
* sizeof() - number of key-value pairs must not be less than the transferred value
* shape() - allows you to describe validation for the values of each key of a Map object

``` java
Validator vMap = new Validator();
MapSchema mapSchema = vMap.map();

mapSchema.isValid(null); // true

mapSchema.required();
mapSchema.isValid(null); // false

Map<String, String> data = new HashMap<>();
mapSchema.isValid(data); // true

mapSchema.sizeof(2);
mapSchema.isValid(data); // false

data.put("key1", "value1");
data.put("key2", "value2");
mapSchema.isValid(data); // true


Validator vMapShape = new Validator();
MapSchema mapSchemaShape = vMapShape.map();
Map<String, BaseSchema> schemas = new HashMap<>();

schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

mapSchemaShape.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "VarisTheMermaid");
human1.put("age", 153);
mapSchemaShape.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Obi-Wan");
human2.put("age", null);
mapSchemaShape.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
mapSchemaShape.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Fetus");
human4.put("age", -3);
mapSchemaShape.isValid(human4); // false
```
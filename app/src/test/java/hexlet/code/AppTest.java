package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    void test1() {
        String expected = "Hello, World!";
        String actual = App.sayHello();
        assertThat(actual).isEqualTo(expected);
    }
}

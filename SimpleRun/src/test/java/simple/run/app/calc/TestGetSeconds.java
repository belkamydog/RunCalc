package simple.run.app.calc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestGetSeconds {
    @Test
    void testOnlyMinute() throws Exception {
        Assertions.assertEquals(60, RunCalc.getSeconds("00:01:00"));
    }

    @Test
    void testOnlySeconds() throws Exception {
        Assertions.assertEquals(15, RunCalc.getSeconds("00:00:15"));
    }

    @Test
    void testOnlyHour() throws Exception {
        Assertions.assertEquals(3600, RunCalc.getSeconds("01:00:00"));
    }

    @Test
    void testSecAndMin() throws Exception {
        Assertions.assertEquals(120, RunCalc.getSeconds("00:01:60"));
    }

    @Test
    void testHourAndMinute() throws Exception {
        Assertions.assertEquals(3660, RunCalc.getSeconds("1:01:00"));
    }

    @Test
    void testAllData() throws Exception {
        Assertions.assertEquals(3661, RunCalc.getSeconds("1:01:01"));
    }

    @Test
    void testIntOverflow() throws Exception {
        Assertions.assertEquals(2147483647, RunCalc.getSeconds("0:00:2147483647"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11.11.12", "test", "", "12:12::", ":::", "1:::1"})
    void testWrongFormat(String time) {
        Throwable throwable = assertThrows(Exception.class, () -> RunCalc.getSeconds(time));
        assertEquals("Uncorrected time format", throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"11:1a:12", "a1:11:1.", "1:1:21474836471"})
    void testWrongFormatParseIntAndOverflow(String time) {
        Throwable throwable = assertThrows(Exception.class, () -> RunCalc.getSeconds(time));
        assertNotNull(throwable);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-11:10:12", "1:-11:1", "1:1:-1"})
    void testWrongFormatValueLessThenZero(String time) {
        Throwable throwable = assertThrows(Exception.class, () -> RunCalc.getSeconds(time));
        assertEquals("Value can't be less then 0", throwable.getMessage());
    }
}

package junit;

import static org.junit.jupiter.api.Assertions.*;

import codeQA.Numbers;
import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    void sumTest() {
        Numbers numbers = new Numbers();
        assertEquals(7, numbers.sumNumbers(2,5));
    }

    @Test
    void oddTest() {
        Numbers numbers = new Numbers();
        assertEquals(false, numbers.checkOddNumber(10));
    }

}
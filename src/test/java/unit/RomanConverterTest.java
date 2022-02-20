package unit;

import app.converter.RomanConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RomanConverterTest {

    @Test
    public void mainDigitsConvertionCorrectly() {
        //Arrange
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(5);
        expected.add(10);
        expected.add(50);
        expected.add(100);
        expected.add(500);
        expected.add(1000);

        ArrayList<Integer> result = new ArrayList<>();

        //Act
        result.add(RomanConverter.convertoToNumber("I"));
        result.add(RomanConverter.convertoToNumber("V"));
        result.add(RomanConverter.convertoToNumber("X"));
        result.add(RomanConverter.convertoToNumber("L"));
        result.add(RomanConverter.convertoToNumber("C"));
        result.add(RomanConverter.convertoToNumber("D"));
        result.add(RomanConverter.convertoToNumber("M"));

        //Assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void romanToNumberComplexConvertion() {
        //Arrange
        String romanNumber="MCMXCIV";
        int expected = 1994;

        //Act
        int result = RomanConverter.convertoToNumber(romanNumber);

        //Assert
        Assertions.assertEquals(expected,result);
    }
}

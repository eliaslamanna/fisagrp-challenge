package app.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class RomanConverter {

    private static Map<Character,Integer> romanDictionaty = new HashMap<>();

    static {
        romanDictionaty.put('I',1);
        romanDictionaty.put('V',5);
        romanDictionaty.put('X',10);
        romanDictionaty.put('L',50);
        romanDictionaty.put('C',100);
        romanDictionaty.put('D',500);
        romanDictionaty.put('M',1000);
    }

    public static int convertoToNumber(String roman) {
        int[] result = {0};

        Stream.iterate(0, n -> n + 1)
                .limit(roman.length())
                .forEach(i -> {
                    char ch = roman.charAt(i);
                    if(i > 0 && romanDictionaty.get(ch) > romanDictionaty.get(roman.charAt(i-1))) {
                        result[0] += romanDictionaty.get(ch) - 2*romanDictionaty.get(roman.charAt(i-1));
                    } else {
                        result[0] += romanDictionaty.get(ch);
                    }
                });

        return result[0];
    }

}

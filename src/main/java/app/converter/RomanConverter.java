package app.converter;

import java.util.HashMap;
import java.util.Map;

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
        int result=0;

        for(int i = 0; i < roman.length() ;i++) {
            char ch = roman.charAt(i);

            if(i > 0 && romanDictionaty.get(ch) > romanDictionaty.get(roman.charAt(i-1))) {
                result += romanDictionaty.get(ch) - 2*romanDictionaty.get(roman.charAt(i-1));
            } else {
                result += romanDictionaty.get(ch);
            }
        }

        return result;
    }

}

import java.util.ArrayList;
import java.util.List;

public class InputScanner {
    public static boolean checkInt0, checkInt1, checkString = false;
    public static List<String> CheckedInt = new ArrayList<>();

    public static boolean CheckInt(String code) {
        checkInt1 = false;
        for (char c : code.toCharArray()) {
            if (Character.isDigit(c)) {
                checkInt1 = true;
            }
        }
        return checkInt1;
    }

    public static int DigitCounter(String program) {

        int DigitCount = 0;

        for (char c : program.toCharArray()) {
            if (Character.isDigit(c)) {
                DigitCount++;
                checkInt0 = true;
            }
        }
        return DigitCount;
    }

    public static boolean NotANumber(String code) {
        boolean checkInt1 = false;
        for (char c : code.toCharArray()) {
            if (!(Character.isDigit(c) || c == '-' || Variables.VarInt.containsKey(code))){
                checkInt1 = true;
            }
        }
        return checkInt1;
    }
    public static boolean NotADigit(String code) {
        boolean checkInt1 = false;
        for (char c : code.toCharArray()) {
            if (!(Character.isDigit(c) || c == '-')){
                checkInt1 = true;
            }
        }
        return checkInt1;
    }

    public static Boolean CheckString(String program) {
        checkString = false;
        Character quoteChar = '\"';
        for (Character c : program.toCharArray()) {
            if (c.equals(quoteChar)) {
                checkString = true;
                break;
            }
        }
        return checkString;
    }

    public static boolean CheckVarInt(String code) {
        for (String c : Variables.VarInt.keySet()) {
            if (Variables.VarInt.containsKey(code) && !CheckedInt.contains(c)) {
                CheckedInt.add(c);
                return true;
            }
        }
        return false;
    }

    public static boolean CheckBool(String code) {
        return code.contains("true") || code.contains("false");
    }
}

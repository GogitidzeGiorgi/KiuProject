
public class InputCounter {
    public static boolean checkInt0, checkInt1, checkString=false;
    public static int WordCounter(String program) {
        String assign = ":=";
        int index = 0;
        int count = 0;
        while (true) {
            int pos = program.indexOf(assign, index);
            if (pos < 0) break;
            count++;
            index = pos + 1;
        }
        return count;
    } /// Never used
    public static boolean checkInt(String code){
        checkInt1 = false;
        for (char c : code.toCharArray()) {
            if (Character.isDigit(c)) {
                checkInt1 = true;
            }
        }
        return checkInt1;
    }
    public static boolean checkNotInt(String code){
        boolean checkInt1 = false;
        System.out.println(code.toCharArray());
        for (char c : code.toCharArray()) {
            if (!Character.isDigit(c) && c!='-') {
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

    public static Boolean CheckString(String program) {
        int StringIndex = 0;
        int StringCount = 0;
        Character quoteChar = '\"';

        for (Character c : program.toCharArray()) {
            if (c.equals(quoteChar)) {
                checkString=true;
            }
        }
        String S_assign2 = "\"";

        while (true) {
            int pos = program.indexOf(S_assign2, StringIndex);
            if (pos < 0) break;
            StringCount++;
            StringIndex = pos + 1;
        }
        return checkString;
    }
//    public static int StringCounter(String program) {
//        int StringIndex = 0;
//        int StringCount = 0;
//        Character quoteChar = '\"';
//
//        for (Character c : program.toCharArray()) {
//            if (c.equals(quoteChar)) {
//                checkString=true;
//            }
//        }
//        String S_assign2 = "\"";
//
//        while (true) {
//            int pos = program.indexOf(S_assign2, StringIndex);
//            if (pos < 0) break;
//            StringCount++;
//            StringIndex = pos + 1;
//        }
//        return StringCount/2;
//    }
}

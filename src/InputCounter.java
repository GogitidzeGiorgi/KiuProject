
public class InputCounter {
    public static boolean checkInt, checkString=false;
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
    } /// Not used

    public static int IntCounter(String program) {
        int IntCount = 0;


        for (char c : program.toCharArray()) {
            if (Character.isDigit(c)) {
                IntCount++;
                checkInt = true;
            }
        }
//        }
        return IntCount;
    }

    public static int StringCounter(String program) {
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


        return StringCount/2;
    }
}

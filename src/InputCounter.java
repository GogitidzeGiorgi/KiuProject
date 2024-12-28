public class InputCounter {
    public static boolean isDigit, isInt, checkString=false;
    public static int WordCounter(String program) {
        String[] words = new String[0];
        if (program.contains(":=")) {
            String[] parts = program.split(":=");
            if (parts.length < 2) {
                return -1;
            }
            String value = parts[1].strip();
            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }
            words = value.split("\\s+");
        }
        return words.length;
    } /// Never used
    public static boolean checkInt1(String code){
        isInt = false;
        for (char c : code.toCharArray()) {
            if (Character.isDigit(c)) {
                isInt = true;
            }

        }
        return isInt;
    }
    public static int DigitCounter(String program) {
        int DigitCount = 0;
        for (char c : program.toCharArray()) {
            if (Character.isDigit(c)) {
                DigitCount++;
                isDigit = true;
            }
        }
        return DigitCount;
    } /// Never used

    public static int StringCounter(String program) {
        int StringCount = 0;
        if(program.contains(":=")) {
            String[] parts = program.split(":=");
            if(parts.length == 2) {
                return -1;
            }
            int StringIndex = 0;
            int isString = 0;
            Character quoteChar = '\"';

            for (Character c : program.toCharArray()) {
                if (c.equals(quoteChar)) {
                    isString++;
                    if (isString == 2) {
                        checkString = true;
                        break;
                    }
                }
            }
            String S_assign2 = "\"";
            if (checkString) {
                while (true) {
                    int pos = program.indexOf(S_assign2, StringIndex);
                    if (pos < 0) break;
                    StringCount++;
                    StringIndex = pos + 1;
                }
            }
        }
        return StringCount / 2;
    }
}

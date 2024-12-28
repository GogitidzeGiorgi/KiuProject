import java.util.HashMap;
import java.util.Map;

public class ArithmeticNumber {
    public Map<String, Integer> variables = new HashMap<>();
    static char operator;
    String stripmed;

    public int arithmetic(String printed) {
        stripmed = printed.replaceAll(" ", "");
        if (stripmed.contains("+")) {
            operator = '+';
        } else if (stripmed.contains("-")) {
            operator = '-';
        } else if (stripmed.contains("*")) {
            operator = '*';
        } else if (stripmed.contains("/")) {
            operator = '/';
        } else if (stripmed.contains("%")) {
            operator = '%';
        } else {
            // Chooses operator, if no operator is found,
            // we treat it as a single number or variable, so we just get value.
            return getValue(stripmed);
        }

        int value = 0;
        switch (operator) {
            case '+':
                String[] addNumbers = stripmed.split("\\+");
                for (String number : addNumbers) {
                    value += getValue(number);
                }
                break;

            case '-':
                String[] subNumbers = stripmed.split("-");
                value = getValue(subNumbers[0]); // Start with the first number
                for (int i = 1; i < subNumbers.length; i++) {
                    value -= getValue(subNumbers[i]);
                }
                break;

            case '*':
                value = 1; // Reset value for multiplication
                String[] mulNumbers = stripmed.split("\\*");
                for (String number : mulNumbers) {
                    value *= getValue(number);
                }
                break;

            case '/':
                String[] divNumbers = stripmed.split("/");
                value = getValue(divNumbers[0]); // Start with the first number
                for (int i = 1; i < divNumbers.length; i++) {
                    int divisor = getValue(divNumbers[i]);
                    if (divisor == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    value /= divisor;
                }
                break;

            case '%':
                String[] modNumbers = stripmed.split("%");
                value = getValue(modNumbers[0]); // Start with the first number
                for (int i = 1; i < modNumbers.length; i++) {
                    value %= getValue(modNumbers[i]);
                }
                break;
        }
        return value;
        //Depending on any operator chosen, String is split by operator into String[] and calls getValue
    }

    private int getValue(String varOrNum) {
        if (variables.containsKey(varOrNum)) {
            return variables.get(varOrNum);
        }
        return Integer.parseInt(varOrNum);
        //If we call getValue method from String or String[]'s every value(variable or Integer) it checks it.
        //If String appears to be variable, which was created and stored into Map-key before, it gets it's value
        //If it was regular String, it is Parsed into Integer
    }
}
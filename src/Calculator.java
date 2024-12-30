import java.util.HashMap;
import java.util.Map;

public class Calculator {
    static Map<String, Integer> calcVariables = new HashMap<>();
    static char operator;

    public static void handleCalculation(String line) throws ArithmeticException {
        String[] parts;
        String varName = "";
        String expression;
        int value = 0;

        // Check for augmented assignment operators
        // need to be checked

        if (line.contains("+=")) {
            parts = line.split("\\+=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue + value);
            }
        } else if (line.contains("-=")) {
            parts = line.split("\\-=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue - value);
            }
        } else if (line.contains("*=")) {
            parts = line.split("\\*=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue * value);
            }
        } else if (line.contains("/=")) {
            parts = line.split("/=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            int divisor = Integer.parseInt(expression);
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue / value);
            }
        } else if (line.contains("%=")) {
            parts = line.split("%=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue % value);
            }
        } else if (line.contains(":=")) {
            parts = line.split(":=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            Variables.VarInt.put(varName, value);
        } else if (line.contains("=")) {
            parts = line.split("=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            Variables.VarInt.put(varName, value);
        }
        // Update the variable in the map
    }

    /// Arithmetic


    static String stripped;

    static int arithmetic(String expression) {
        String stripped = expression.replaceAll(" ", "");
        int value = 0;

        // Determine the operator present in the expression
        if (stripped.contains("+")) {
            operator = '+';
        } else if (stripped.contains("-")) {
            operator = '-';
        } else if (stripped.contains("*")) {
            operator = '*';
        } else if (stripped.contains("/")) {
            operator = '/';
        } else if (stripped.contains("%")) {
            operator = '%';
        } else {

            switch (operator) {
                case '+':
                    String[] addNumbers = stripped.split("\\+");
                    for (String number : addNumbers) {
                        if (!InputScanner.NotANumber(number)) {
                            value += getValue(number);
                        } else {
                        }
                    }
                    break;

                case '-':
                    String[] subNumbers = stripped.split("-");
                    if (InputScanner.NotANumber(subNumbers[0]) && subNumbers[0] != null) {
                        value = getValue(subNumbers[0]);
                    } else if (getValue(subNumbers[0]) != null) {
                        value = getValue(subNumbers[0]);
                    } else {
                    }
                    for (int i = 1; i < subNumbers.length; i++) {
                        value -= getValue(subNumbers[i]);
                    }
                    break;

                case '*':
                    value = 1; // Reset value for multiplication
                    String[] mulNumbers = stripped.split("\\*");
                    for (String number : mulNumbers) {
                        value *= getValue(number);
                    }
                    break;

                case '/':
                    String[] divNumbers = stripped.split("/");
                    value = Integer.parseInt(divNumbers[0]);
                    for (int i = 1; i < divNumbers.length; i++) {
                        int divisor = getValue(divNumbers[i]);
                        if (divisor == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        value /= divisor;
                    }
                    break;

                case '%':
                    String[] modNumbers = stripped.split("%");
                    value = Integer.parseInt(modNumbers[0]);
                    for (int i = 1; i < modNumbers.length; i++) {
                        int moder = getValue(modNumbers[i]);
                        if (moder == 0) {
                            throw new ArithmeticException("Modulo by zero");
                        }
                        value %= moder;
                    }
                    break;

                default:
                    value = getValue(stripped);
                    break;
            }
            return value;
            //Depending on any operator chosen, String is split by operator into String[] and calls Variables.VarInt.ge}
        }return value;
    }

    private static Integer getValue(String varOrNum) {
        if (Variables.VarInt.containsKey(varOrNum)) {
            return Variables.VarInt.get(varOrNum);
        }
        if (!InputScanner.NotANumber(varOrNum)) {
            return Integer.parseInt(varOrNum);
        }else{
            return null;
        }
        //If we call getValue method from String or String[]'s every value(variable or Integer) it checks it.
        //If String appears to be variable, which was created and stored into Map-key before, it gets it's value
        //If it was regular String, it is Parsed into Integer
    }
}


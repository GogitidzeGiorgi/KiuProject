import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    static char operator;
    static int value = 0;
    public static boolean retBool;
    static String[] parts;
    static String varName;
    static String expression;

    public static void handleCalculation(String line) throws ArithmeticException {


        // Check for augmented assignment operators
        // need to be checked

        if (line.contains("==")) {
            parts = line.split("==");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression); // Assuming this returns an int or a boolean value

            // Assuming you have a method to get the value of the variable
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                boolean isEqual = (currentValue == value);
            }
        } else if(line.contains("++")){
            varName = line.replaceAll("\\+\\+", "");
            if(Variables.VarInt.containsKey(varName)){
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue + 1);
            }
        } else if(line.contains("--")){
            varName = line.replaceAll("--", "");
            if(Variables.VarInt.containsKey(varName)){
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue - 1);
            }

        } else if (line.contains("+=")) {
            parts = line.split("\\+=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue + value);
            }
        } else if (line.contains("-=")) {
            parts = line.split("-=");
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
            value = arithmetic(expression);
            if(value == 0){
                throw new ArithmeticException("Division by zero");
            }
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue / value);
            }
        } else if (line.contains("%=")) {
            parts = line.split("%=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if(value == 0){
                throw new ArithmeticException("Modulo by zero");
            }
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue % value);
            }

        }
//         Update the variable in the map
    }

    /// Arithmetic


    static String stripped;

    static int arithmetic(String input) {
        boolean isvar = false;
        String calcVar = ""; // b := 5 + 2
        if(input.contains(":=") ){
            isvar = true;
            parts = input.split(":=");
            if(!parts[0].strip().contains(" ")){
                expression = parts[1].strip();
                calcVar = parts[0].strip();
            }
        }else if(input.contains("=") ) {
            isvar = true;
            parts = input.split("=");
            if (!parts[0].strip().contains(" ")) {
                expression = parts[1].strip();
                calcVar = parts[0].strip();
            }
        }
        // a = 5 + 2;
        //  5 + 2

        String stripped = expression.replaceAll(" ", "");

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
        }

        switch (operator) {
            case '+':
                String[] addNumbers = stripped.split("\\+");
                for (String number : addNumbers) {
                    if(!InputScanner.NotANumber(String.valueOf(getValue(number)))){value += getValue(number);}
                }
                break;

            case '-':
                String[] subNumbers = stripped.split("-");
                if (InputScanner.NotANumber(subNumbers[0]) && subNumbers[0] != null) {
                    value = getValue(subNumbers[0]);
                } else if (getValue(subNumbers[0]) != null) {
                    value = getValue(subNumbers[0]);
                }
                for (int i = 1; i < subNumbers.length; i++) {
                    value -= getValue(subNumbers[i]);
                }
                break;

            case '*':
                value = 1; // Reset value for multiplication
                String[] mulNumbers = stripped.split("\\*");
                for (String number : mulNumbers) {
                    if(!InputScanner.NotANumber(number)){
                        value *= getValue(number);
//                        }
//                        else{
//                            if(Variables.VarInt.get(number)!=null &&){
//                                value *= Variables.VarInt.get(number);}
//                            value *= getValue(number)
                    }
                }
//                    Variables.VarInt.put(varName, value);
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
                if(!InputScanner.NotADigit(modNumbers[0])){
                    value = Integer.parseInt(modNumbers[0]);
                }else if (!InputScanner.NotANumber(modNumbers[0])){
                    value = Variables.VarInt.get(modNumbers[0]);
                }
                for (int i = 1; i < modNumbers.length; i++) {
                    int moder = getValue(modNumbers[i]);
                    if (moder == 0) {
                        System.out.println(("Modulo by zero"));
                    }else{
                        value %= moder;
                    }
                }
                break;

            default:
                value = getValue(stripped);
                break;

        }


        if(isvar){
            Variables.assign(calcVar +" := "+value);
        }
        //Depending on any operator chosen, String is split by operator into String[] and calls Variables.VarInt.ge}
        return value;

    }

    public static Integer getValue(String varOrNum) {
        if (varOrNum == null || varOrNum.isEmpty()) {
            return null; // or throw an IllegalArgumentException
        }
        if (Variables.VarInt.containsKey(varOrNum)) {
            return Variables.VarInt.get(varOrNum);
        }
        try {
            return Integer.parseInt(varOrNum);
        } catch (NumberFormatException e) {
            // Handle the case where varOrNum is not a valid integer
            return null; // or throw an IllegalArgumentException
        }
    }
}


import java.util.HashMap;
import java.util.Map;

public class Calculator {
    static Map<String, Integer> calcVariables = new HashMap<>();

    public static void handleCalculation(String line) throws ArithmeticException {
        String[] parts;
        String varName;
        String expression;
        int value = 0;

        // Check for augmented assignment operators
        // need to be checked
        if (line.contains("+=")) {
            parts = line.split("\\+=");
            varName = parts[0].trim();
            expression = parts[1].trim();
            value = calcVariables.getOrDefault(varName, 0) + Integer.parseInt(expression);
        } else if (line.contains("-=")) {
            parts = line.split("\\-=");
            varName = parts[0].trim();
            expression = parts[1].trim();
            value = calcVariables.getOrDefault(varName, 0) - Integer.parseInt(expression);
        } else if (line.contains("*=")) {
            parts = line.split("\\*=");
            varName = parts[0].trim();
            expression = parts[1].trim();
            value = calcVariables.getOrDefault(varName, 0) * Integer.parseInt(expression);
        } else if (line.contains("/=")) {
            parts = line.split("/=");
            varName = parts[0].trim();
            expression = parts[1].trim();
            int divisor = Integer.parseInt(expression);
            if (divisor == 0) {
                throw new ArithmeticException("Division by zero");
            }
            value = calcVariables.getOrDefault(varName, 0) / divisor;
        } else if (line.contains("%=")) {
            parts = line.split("%=");
            varName = parts[0].trim();
            expression = parts[1].trim();
            int divisor = Integer.parseInt(expression);
            if (divisor == 0) {
                throw new ArithmeticException("Modulo by zero");
            }
            value = calcVariables.getOrDefault(varName, 0) % divisor;
        } else {
            // Handle standard assignments and expressions
//            parts = line.split(":=");
//            varName = parts[0].trim();
//            expression = parts[1].trim();
//            value = evaluateExpression(expression);
        }

        // Update the variable in the map
//        calcVariables.put(varName, value);
    }

    static int evaluateExpression(String expression) {
        String trimmedExpression = expression.replaceAll(" ", "");
        char operator = ' ';
        int value = 0;

        // Determine the operator present in the expression
        if (trimmedExpression.contains("+")) {
            operator = '+';
        } else if (trimmedExpression.contains("-")) {
            operator = '-';
        } else if (trimmedExpression.contains("*")) {
            operator = '*';
        } else if (trimmedExpression.contains("/")) {
            operator = '/';
        } else if (trimmedExpression.contains("%")) {
            operator = '%';
        }

        switch (operator) {
            case '+':
                String[] addNumbers = trimmedExpression.split("\\+");
                for (String number : addNumbers) {
                    value += Integer.parseInt(number);
                }
                break;

            case '-':
                String[] subNumbers = trimmedExpression.split("-");
                if(InputScanner.NotANumber(subNumbers[0]) && subNumbers[0] != null){
                value = Integer.parseInt(subNumbers[0]);}else if(Variables.VarInt.get(subNumbers[0]) != null){
                    value = Variables.VarInt.get(subNumbers[0]);
                }else{
                    System.out.println("Null");
                }
                for (int i = 1; i < subNumbers.length; i++) {
                    value -= Integer.parseInt(subNumbers[i]);
                }
                break;

            case '*':
                value = 1; // Reset value for multiplication
                String[] mulNumbers = trimmedExpression.split("\\*");
                for (String number : mulNumbers) {
                    value *= Integer.parseInt(number);
                }
                break;

            case '/':
                String[] divNumbers = trimmedExpression.split("/");
                value = Integer.parseInt(divNumbers[0]);
                for (int i = 1; i < divNumbers.length; i++) {
                    int divisor = Integer.parseInt(divNumbers[i]);
                    if (divisor == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    value /= divisor;
                }
                break;

            case '%':
                String[] modNumbers = trimmedExpression.split("%");
                value = Integer.parseInt(modNumbers[0]);
                for (int i = 1; i < modNumbers.length; i++) {
                    int divisor = Integer.parseInt(modNumbers[i]);
                    if (divisor == 0) {
                        throw new ArithmeticException("Modulo by zero");
                    }
                    value %= divisor;
                }
                break;

            default:
                value = Integer.parseInt(trimmedExpression);
                break;
        }

        return value;
    }
}

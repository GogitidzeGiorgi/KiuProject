import java.util.HashMap;
import java.util.Map;

public class Calculator {
    public Map<String, Integer> variables = new HashMap<>();

    public void handleAssignment(String line) throws ArithmeticException {
        String[] parts = line.split(":=");
        String varName = parts[0].trim();
        String expression = parts[1].trim();
        int value = 0;
        String trimmedExpression = expression.replaceAll(" ", "");
        char operator = ' ';

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
                for (String number : subNumbers) {
                    value -= Integer.parseInt(number);
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
                for(int i = 1; i < divNumbers.length; i++) {
                    if(Integer.parseInt(divNumbers[i]) == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                }
                for (int i = 1; i < divNumbers.length; i++) {
                    value /= Integer.parseInt(divNumbers[i]); // Note: This seems incorrect; typically, you'd divide.
                }
                break;

            case '%':
                String[] modNumbers = trimmedExpression.split("%");
                value = Integer.parseInt(modNumbers[0]) * Integer.parseInt(modNumbers[1]);
                for (int i = 1 ; i < modNumbers.length; i++) {
                    value %= Integer.parseInt(modNumbers[i]);
                }
                break;

            default:
                value = Integer.parseInt(trimmedExpression);
                break;
        }
        variables.put(varName, value);
    }

    public void handlePrintln(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        System.out.println(variables.get(varName) + "\n");
    }
}

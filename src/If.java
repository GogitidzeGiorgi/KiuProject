import java.util.ArrayList;
import java.util.List;

public class If extends Calculator {

    public static void handleIf(String line) {
        Interpreter.skipNextLine = true;
        String condition = line.substring(line.indexOf("if") + 2, line.lastIndexOf("{")).strip();
        List<String> parts = new ArrayList<>(List.of(condition.split(" ")));

        boolean ifConditionMet = evaluateCondition(parts.get(0).trim(), parts.get(1).trim(), parts.get(2).trim());

        if (ifConditionMet) {
            Interpreter.skipNextLine = false; // Execute the 'if' block
            checkElse(line, !ifConditionMet);
        } else {
            Interpreter.skipNextLine = true; // Skip the 'if' block
            checkElse(line, !ifConditionMet); // Look for and handle 'else' block
        }
    }

    public static void checkElse(String line, boolean ifTrue) {
        if (ifTrue) {
            int elseIndex = line.indexOf("else");
            if (elseIndex != -1) { // 'else' block exists
                String elseBlock = line.substring(elseIndex + 4).strip(); // Get everything after 'else'

                if (elseBlock.startsWith("{")) {
                    // Execute the 'else' block if it starts with '{'
                    Interpreter.skipNextLine = false; // Allow execution of the 'else' block
                } else {
                    throw new IllegalArgumentException("Malformed 'else' statement.");
                }
            }else{
                System.out.println("elseNotFound");}
        }else {Interpreter.skipNextLine =true;}
    }

    public static boolean evaluateCondition(String left, String operator, String right) {
        int leftValue = getValue(left);
        int rightValue = getValue(right);
        return switch (operator) {
            case "==" -> leftValue == rightValue;
            case "!=" -> leftValue != rightValue;
            case ">" -> leftValue > rightValue;
            case "<" -> leftValue < rightValue;
            case ">=" -> leftValue >= rightValue;
            case "<=" -> leftValue <= rightValue;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
}

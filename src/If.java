import java.util.ArrayList;
import java.util.List;

public class If extends Calculator {
    public static boolean skipElse;
    public static boolean goToBool;


    public static void handleIf(String line) {
        goToBool = false;
        // Extract the condition inside the `if` statement
        String condition = line.substring(line.indexOf("if") + 2, line.lastIndexOf("{")).strip();
        List<String> parts = new ArrayList<>(List.of(condition.split(" ")));
        System.out.println(parts);
      for(String s : Variables.VarBool.keySet()) {
          if(line.contains(s)) {
              goToBool = true;
              if(Variables.VarBool.get(s)){

                  Interpreter.skipNextLine = false;
                  skipElse = true;
                  // Execute the 'if' block

              }else{
                  skipElse = false;
                  Interpreter.skipNextLine = true; // Skip the 'if' block
                  handleElse(line); // Handle the `else` block if the `if` condition is not met
              }
          }
      }
        // Evaluate the `if` condition
        if(!goToBool) {
            boolean ifConditionMet = evaluateCondition(parts.get(0).trim(), parts.get(1).trim(), parts.get(2).trim());
            if (ifConditionMet) {
                Interpreter.skipNextLine = false;
                skipElse = true;
                // Execute the 'if' block
            } else {
                skipElse = false;
                Interpreter.skipNextLine = true; // Skip the 'if' block
                handleElse(line); // Handle the `else` block if the `if` condition is not met
            }
        }

    }

    public static void handleElse(String line) {
        int elseIndex = line.indexOf("else");
        if (elseIndex != -1) { // Check if an `else` block exists
            String elseBlock = line.substring(elseIndex + 4).strip(); // Get everything after 'else'

            if (elseBlock.startsWith("{")) {
                Interpreter.skipNextLine = false; // Execute the 'else' block
            } else {
                throw new IllegalArgumentException("Malformed 'else' statement.");
            }
        }
    }

    public static boolean evaluateCondition(String left, String operator, String right) {
        System.out.println(left+ " "+ operator+ " "+right);
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

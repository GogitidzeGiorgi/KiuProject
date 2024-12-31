import java.util.*;

public class Loops extends Calculator {

    static void handleWhileLoop(int startIndex, String[] lines) {
        // Extract the loop condition
        String condition = lines[startIndex].replaceAll("for|\\{|}", "").trim();
        String[] splittedCond = condition.split(" ");

        if (splittedCond.length != 3) {
            throw new IllegalArgumentException("Malformed loop condition: " + condition);
        }

        // Extract the loop body
        List<String> loopBody = new ArrayList<>();
        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i].strip();
            if (line.equals("}")) {
                break;
            }
            loopBody.add(line);
        }

        String body = String.join("\n", loopBody);

        // Parse the condition parts
        String leftOperand = splittedCond[0];
        String operator = splittedCond[1];
        String rightOperand = splittedCond[2];

        // Execute the loop
        while (evaluateCondition(leftOperand, operator, rightOperand)) {
            // Evaluate the body of the loop
            Interpreter.eval(body);

            // Update the condition variables
            // (Assuming Interpreter.eval updates the variables correctly)
        }
    }

    // Helper method to evaluate the loop condition
    private static boolean evaluateCondition(String left, String operator, String right) {
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


    static void handleForLoop(int startIndex, String[] lines) {
        int endPoint = 0;

        // Parse the for-loop header
        String initialization;
        String condition;
        String increment;
        String loopHeader = lines[startIndex].strip();
        if (loopHeader.contains(";")) {
            String[] headerParts = loopHeader.replace("for", "").strip().split(";");
            initialization = headerParts[0].strip();
            condition = headerParts[1].strip();
            increment = headerParts[2].strip();
        } else {
            String[] headerParts = loopHeader.replace("for", "").strip().split(" ");
            initialization = headerParts[0].strip();
            condition = headerParts[1].strip();
            increment = headerParts[2].strip();
        }
        // Handle initialization
        Variables.assign(initialization); // i := 0

        String[] InitializationArr = initialization.split(" ");

        if (!InputScanner.NotANumber(InitializationArr[2])) {
            Variables.VarInt.put(InitializationArr[0], Integer.valueOf(InitializationArr[2]));
        } else {
            Variables.VarInt.get(InitializationArr[2]);
        }

        // Extract loop variable
        String loopVar = initialization.split(":=")[0].strip();

        // Ensure the loop variable is initialized correctly
        if (!Variables.VarInt.containsKey(loopVar)) {
            throw new IllegalStateException("Loop variable " + loopVar + " is not initialized.");
        }

        String[] conditionParts = condition.split(" ");


        if (conditionParts.length > 1) {
            // check if endpoint is variable or number
            if (conditionParts[2].matches("\\d+")) {
                endPoint = Integer.parseInt(conditionParts[2]);
            } else {
                if (Variables.VarInt.get(conditionParts[2]) != null) {
                    endPoint = Variables.VarInt.get(conditionParts[2]);
                } else {
                    System.out.println("VarInt.get(conditionParts[2]) + Null");
                }
            }
        }
        List<String> loopBody = new ArrayList<>();
        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i].strip(); // Get the current line in the loop
            if (line.contains("}")) {
                break; // End of the loop body
            }
            loopBody.add(line);
        }
        String result = String.join("\n", loopBody);
        loopi(result, condition, increment, loopVar, endPoint);
    }

    static void loopi(String line, String condition, String increment, String loopVar, int endPoint) {
        // Initialize the loop variable
        int currentValue = Variables.VarInt.get(loopVar);

        // Loop until the condition is met
        while (evaluateCondition(condition, currentValue, endPoint)) {
            // Execute the line of code in the loop
            Interpreter.eval(line);

            // Update the loop variable based on the increment
            if (increment.contains("++")) {
                currentValue++;
            } else if (increment.contains("--")) {
                currentValue--;
            }

            // Update the variable in the map
            Variables.VarInt.put(loopVar, currentValue);
        }
    }

    private static boolean evaluateCondition(String condition, int currentValue, int endPoint) {
        // Implement logic to evaluate the condition based on currentValue and endPoint
        // For example, if condition is "i <= n", return currentValue <= endPoint
        if (condition.contains("<=")) {
            return currentValue <= endPoint - 1;
        } else if (condition.contains("<")) {
            return currentValue < endPoint - 1;
        } else if (condition.contains(">=")) {
            return currentValue >= endPoint - 1;
        } else if (condition.contains(">")) {
            return currentValue > endPoint - 1;
        } else if (condition.contains("==")) {
            return currentValue == endPoint - 1;
        } else if (condition.contains("!=")) {
            return currentValue != endPoint - 1;
        }
        return false;
    }
}
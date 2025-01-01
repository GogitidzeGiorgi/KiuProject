import java.util.*;

public class Loops extends Calculator {
    static void handleWhileLoop(int startIndex, String[] lines) {
        String condition = lines[startIndex].replace("for", "").replace("{", "").trim();
        String[] splitCond = condition.split(" ");

        List<String> loopBody = new ArrayList<>();
        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i].strip();
            if (line.equals("}"))break;
            loopBody.add(line);
        }

        String body = String.join("\n", loopBody);
        String leftOperand = splitCond[0];
        String operator = splitCond[1];
        String rightOperand = splitCond[2];

        while (evaluateConditionWhile(leftOperand, operator, rightOperand)) {

            Interpreter.eval(body);
        }
        Interpreter.skipNextLine = true;
    }
    //Method is called in case Interpreter sees While loop, which in GoLang is For with only condition.
    // It determines how big it's body is and iterates through it until certain condition is met.


    static void handleForLoop(int startIndex, String[] lines) {
        String initialization;
        String condition;
        String increment;
        String loopHeader = lines[startIndex].strip();

        String[] headerParts = loopHeader.replace("for", "").replace("{", "").strip().split(";");

        initialization = headerParts[0].strip();
        condition = headerParts[1].strip();
        increment = headerParts[2].strip();

        Variables.assign(initialization);

        String[] InitializationArr = initialization.split(" ");

        Variables.VarInt.put(InitializationArr[0], Calculator.getValue(InitializationArr[2]));


        String loopVar = initialization.split(":=")[0].strip();

        if (!Variables.VarInt.containsKey(loopVar)) {
            throw new IllegalStateException("Loop variable " + loopVar + " is not initialized.");
        }

        String[] conditionParts = condition.split(" ");

        int condRight = 0;
        if (conditionParts.length > 1) {
            condRight = arithmetic(conditionParts[2]);
        }
        List<String> loopBody = new ArrayList<>();
        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i].strip();
            if (line.equals("}")) break;
            loopBody.add(line);
        }
        String result = String.join("\n", loopBody);
        loop(result, condition, increment, loopVar, condRight);
    }
    //Method is called in case Interpreter sees While loop, which in GoLang is For with only condition.
    // It determines how big it's body is and iterates through it until certain condition is met.



    static void loop(String line, String condition, String increment, String loopVar, int endPoint) {
        String[] splitCond = condition.split(" ");
        int leftOperand = arithmetic(splitCond[0]);
        int currentValue = arithmetic(loopVar);

        while (evaluateConditionFor(condition, leftOperand, endPoint, loopVar)) {
            Interpreter.eval(line);
            if (increment.contains("++")) {
                currentValue++;
            } else if (increment.contains("--")) {
                currentValue--;
            }
            Variables.VarInt.put(loopVar, currentValue);
            leftOperand = arithmetic(splitCond[0]);
        }
        Interpreter.skipNextLine = true;
    }
    //In case there are difficult condition parts, like -  i*i <= n
    // It creates leftOperand, which firstly does operation(Or nothing), and is checked in certain condition, while
    //while currentValue increases/decreases and updates leftOperand for new operations.

    private static boolean evaluateConditionFor(String condition, int leftOperand, int endPoint, String loopVar) {
        if (condition.contains("<=")) {
            return leftOperand <= endPoint ;
        } else if (condition.contains("<")) {
            return leftOperand < endPoint ;
        } else if (condition.contains(">=")) {
            return leftOperand >= endPoint ;
        } else if (condition.contains(">")) {
            return leftOperand > endPoint ;
        } else if (condition.contains("==")) {
            return leftOperand == endPoint;
        } else if (condition.contains("!=")) {
            return leftOperand < endPoint || leftOperand > endPoint;
        }
        Variables.VarInt.replace(loopVar, 0);
        return false;
    }
    //This method checks certain conditions for every operator chosen and restores loopVar
    private static boolean evaluateConditionWhile(String left, String operator, String right) {
        int leftValue;
        if(getValue(left) == null) {
            leftValue = arithmetic(left);
        } else {
            leftValue = getValue(left);
        }
        int rightValue;
        if(getValue(right) == null) {
            rightValue = arithmetic(right);
        } else {
            rightValue = getValue(right);
        }

        return switch (operator) {
            case "==" -> leftValue == rightValue;
            case "!=" -> leftValue < rightValue || leftValue > rightValue;
            case ">" -> leftValue > rightValue;
            case "<" -> leftValue < rightValue;
            case ">=" -> leftValue >= rightValue;
            case "<=" -> leftValue <= rightValue;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
    //This method checks certain conditions for every operator chosen and restores loopVar
}

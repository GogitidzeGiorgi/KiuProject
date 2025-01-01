import java.util.*;

public class Loops {
    static void handleWhileLoop(int startIndex, String[] lines) {
        String condition = lines[startIndex].replace("for", "").replace("{", "").trim();
        int braceCount = 1;

        String[] splittedCond = condition.split(" ");

        List<String> loopBody = new ArrayList<>();
        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i].strip();
            if (line.equals("}")) braceCount ++;
            if(braceCount == 2) break;
            loopBody.add(line);
        }

        String body = String.join("\n", loopBody);

        String leftOperand = splittedCond[0];
        String operator = splittedCond[1];
        String rightOperand = splittedCond[2];

        while (evaluateConditionWhile(leftOperand, operator, rightOperand)) {

            Interpreter.eval(body);
        }
        Interpreter.skipNextLine = true;
    }


    static void handleForLoop(int startIndex, String[] lines) {
        int endPoint = 0;

        String initialization;
        String condition;
        String increment;
        String loopHeader = lines[startIndex].strip();

        String[] headerParts = loopHeader.replace("for", "").replace("{", "").strip().split(";");
        initialization = headerParts[0].strip();   //[i:=1]        index 2
        condition = headerParts[1].strip();     // [i<=n]
        increment = headerParts[2].strip();     // [i++]

        Variables.assign(initialization); //  i := 1

        String[] InitializationArr = initialization.split(" ");

        if (!InputScanner.NotANumber(InitializationArr[2])) {
            Variables.VarInt.put(InitializationArr[0], Calculator.getValue(InitializationArr[2]));
        }

        String loopVar = initialization.split(":=")[0].strip();

        if (!Variables.VarInt.containsKey(loopVar)) {
            throw new IllegalStateException("Loop variable " + loopVar + " is not initialized.");
        }

        String[] conditionParts = condition.split(" ");    // [i] [<=] [n]


        if (conditionParts.length > 1) {
            if (!InputScanner.NotANumber(conditionParts[2])) {
                endPoint = Calculator.getValue(conditionParts[2]);
            } else {
                System.out.println("VarInt.get(conditionParts[2]) + Null");
            }
        }
        List<String> loopBody = new ArrayList<>();
        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i].strip();
            if (line.contains("}")) {

                break;
            }
            loopBody.add(line);
        }
        String result = String.join("\n", loopBody);
        loopi(result, condition, increment, loopVar, endPoint);
    }

    static void loopi(String line, String condition, String increment, String loopVar, int endPoint) {
        int currentValue = Variables.VarInt.get(loopVar);

        while (evaluateConditionFor(condition, currentValue, endPoint, loopVar)) {
            Interpreter.eval(line);
            if (increment.contains("++")) {
                currentValue++;
            } else if (increment.contains("--")) {
                currentValue--;
            }
            Variables.VarInt.put(loopVar, currentValue);
        } Interpreter.skipNextLine = true;

    }

    private static boolean evaluateConditionFor(String condition, int currentValue, int endPoint, String loopVar) {
        if (condition.contains("<=")) {
            return currentValue <= endPoint - 1;
        } else if (condition.contains("<")) {
            return currentValue < endPoint - 1;
        } else if (condition.contains(">=")) {
            return currentValue >= endPoint + 1;
        } else if (condition.contains(">")) {
            return currentValue > endPoint + 1;
        } else if (condition.contains("==")) {
            return currentValue == endPoint;
        } else if (condition.contains("!=")) {
            return currentValue < endPoint - 1 || currentValue > endPoint + 1;
        }
        Variables.VarInt.replace(loopVar, 0);
        return false;
    }
    private static boolean evaluateConditionWhile(String left, String operator, String right) {
        int leftValue = Calculator.getValue(left);
        int rightValue = Calculator.getValue(right);

        return switch (operator) {
            case "==" -> leftValue == rightValue;
            case "!=" -> leftValue < rightValue - 1 || leftValue > rightValue + 1;
            case ">" -> leftValue > rightValue;
            case "<" -> leftValue < rightValue;
            case ">=" -> leftValue >= rightValue;
            case "<=" -> leftValue <= rightValue;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
}



//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class GoLangInterpreter {
//
//    public void interpret(String goCode) {
//        // Call the method to process while loops
//        processWhileLoops(goCode);
//    }
//
//    private void processWhileLoops(String goCode) {
//        // Regular expression to match GoLang while loop syntax (for with condition)
//        String whileLoopRegex = "for\\s+([^\\{]+)\\s*\\{([^}]*)\\}";
//        Pattern pattern = Pattern.compile(whileLoopRegex);
//        Matcher matcher = pattern.matcher(goCode);
//
//        while (matcher.find()) {
//            String condition = matcher.group(1).trim();
//            String body = matcher.group(2).trim();
//
//            // Here you would evaluate the condition and execute the body
//            // For demonstration, we will just print the condition and body
//            System.out.println("While Loop Condition: " + condition);
//            System.out.println("While Loop Body: " + body);
//
//            // You would need to implement the logic to evaluate the condition
//            // and execute the body accordingly.
//        }
//    }
//
//    public static void main(String[] args) {
//        GoLangInterpreter interpreter = new GoLangInterpreter();
//        String goCode = "for x < 10 { x++ }"; // Example GoLang while loop
//        interpreter.interpret(goCode);
//    }
//}
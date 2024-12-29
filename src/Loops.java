public class Loops {
    static void handleWhileLoop(String line) {
        int reversed= Variables.variables.get("reversed");
        int n = Variables.variables.get("n");

        while(n != 0){
            int digit = n%10;
            reversed= reversed*10 + digit;
            n /= 10;
        }
        Variables.variables.replace("n", n);
        Variables.variables.replace("reversed", reversed);
    }

    static void handleForLoop(int startIndex, String[] lines) {
        // Parse the for-loop header
        String loopHeader = lines[startIndex].trim();
        String[] headerParts = loopHeader.replace("for", "").trim().split(";");
        String initialization = headerParts[0].trim();
        String condition = headerParts[1].trim();
        String increment = headerParts[2].trim();

        // Handle initialization
        Variables.handleAssignment(initialization);

        // Extract loop variable
        String loopVar = initialization.split(":=")[0].trim();

        // Ensure the loop variable is initialized correctly
        if (!Variables.variables.containsKey(loopVar)) {
            throw new IllegalStateException("Loop variable " + loopVar + " is not initialized.");
        }

        String[] conditionParts = condition.split(" ");

        int endPoint = 0;

        // check if endpoint is variable or number
        if(conditionParts[2].matches("\\d+")){
            endPoint = Integer.parseInt(conditionParts[2]);
        }else {
            endPoint = Variables.variables.get(conditionParts[2]);
        }

//        for (int i = startIndex; i < lines.length; i++) {
//                String line = lines[i].trim();
//                if (line.equals("}")) {
//                    break; // Exit when the loop body ends
//                }
//
//                // Only pass arithmetic expressions to handleArithmetic (e.g., sum += i)
//                if (line.contains("+") || line.contains("-") || line.contains("*") || line.contains("/")) {
//                    eval(line); // Call eval to process the arithmetic expression
//                }
//            }

        // generate correct loop
        if (condition.contains("<") && increment.contains("++")) {
            for (int i = Variables.variables.get(loopVar); i < endPoint; i++) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        }else if(condition.contains("<") && increment.contains("--")){
            for (int i = Variables.variables.get(loopVar); i < endPoint; i--) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        }else if (condition.contains(">") && increment.contains("++")) {
            for (int i = Variables.variables.get(loopVar); i > endPoint; i++) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        }else if (condition.contains(">") && increment.contains("--")) {
            for (int i = Variables.variables.get(loopVar); i > endPoint; i--) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        } else if (condition.contains(">=") && increment.contains("++")) {
            for (int i = Variables.variables.get(loopVar); i >= endPoint; i++) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        } else if (condition.contains(">=") && increment.contains("--")) {
            for (int i = Variables.variables.get(loopVar); i >= endPoint; i--) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        } else if (condition.contains("<=") && increment.contains("++")) {
            for (int i = Variables.variables.get(loopVar); i <= endPoint; i++) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        }else if (condition.contains("<=") && increment.contains("--")) {
            for (int i = Variables.variables.get(loopVar); i <= endPoint; i--) {
                Calculator.handleCalculation(lines[startIndex + 1]);
            }
        }

        // Loop execution (as long as the condition is true)
//        while (evaluateCondition(condition)) {
//            // Process the body of the loop (starting from the next line after the header)
//            for (int i = startIndex; i < lines.length; i++) {
//                String line = lines[i].trim();
//                if (line.equals("}")) {
//                    break; // Exit when the loop body ends
//                }
//
//                // Only pass arithmetic expressions to handleArithmetic (e.g., sum += i)
//                if (line.contains("+") || line.contains("-") || line.contains("*") || line.contains("/")) {
//                    eval(line); // Call eval to process the arithmetic expression
//                }
//            }
//        }
    }

}

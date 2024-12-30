import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loops {
    static void handleWhileLoop(String line) {


        int reversed= Variables.VarInt.get("reversed");
        int n = Variables.VarInt.get("n");

        while(n != 0){
            int digit = n%10;
            reversed= reversed*10 + digit;
            n /= 10;
        }
        Variables.VarInt.replace("n", n);
        Variables.VarInt.replace("reversed", reversed);
    }

    static void handleForLoop(int startIndex, String[] lines) {
        boolean isLoop = true;
        int endPoint = 0;

        // Parse the for-loop header
        String initialization; // i := 0
        String condition; // i <= N
        String increment; // i++
        String loopHeader = lines[startIndex].strip();
        if(loopHeader.contains(";")) {
            String[] headerParts = loopHeader.replace("for", "").strip().split(";");
            initialization = headerParts[0].strip();
            condition = headerParts[1].strip();
            increment = headerParts[2].strip();
        }
        else {
            String[] headerParts = loopHeader.replace("for", "").strip().split(" ");
            initialization = headerParts[0].strip();
            condition = headerParts[1].strip();
            increment = headerParts[2].strip();
        }
        // Handle initialization
        Variables.assign(initialization);
        String[] loop = initialization.split(" ");
        if(!InputScanner.NotANumber(loop[2])){
            Variables.VarInt.put(loop[0], Integer.valueOf(loop[2]));
        }else{
            Variables.VarInt.get(loop[2]);
        }

        // Extract loop variable
        String loopVar = initialization.split(":=")[0].strip();

        // Ensure the loop variable is initialized correctly
        if (!Variables.VarInt.containsKey(loopVar)) {
            throw new IllegalStateException("Loop variable " + loopVar + " is not initialized.");
        }

        String[] conditionParts = condition.split(" ");


        if(conditionParts.length>1) {
            // check if endpoint is variable or number
            if (conditionParts[2].matches("\\d+")) {
                endPoint = Integer.parseInt(conditionParts[2]);
            } else {
                endPoint = Variables.VarInt.get(conditionParts[2]);
            }
        }
        int lineCount = -1;
        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i].strip(); // \n
            if (line.contains("}")) {
                isLoop = false;
                break;
            }
            loopi(line, condition, increment, loopVar, endPoint, loop); ///AFK
        }

    }
    static void loopi(String line, String condition, String increment, String loopVar, int endPoint, String[] loop) {

        if (condition.contains("<=") && increment.contains("++")) {
            for (int i = Variables.VarInt.get(loopVar); i <= endPoint; i++) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0]) +1);
            }
        }else if(condition.contains("<=") && increment.contains("--")){
            for (int i = Variables.VarInt.get(loopVar); i <= endPoint; i--) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0])- 1);
            }
        }else if (condition.contains(">=") && increment.contains("++")) {
            for (int i = Variables.VarInt.get(loopVar); i >= endPoint; i++) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0]) +1);
            }
        }else if (condition.contains(">=") && increment.contains("--")) {
            for (int i = Variables.VarInt.get(loopVar); i >= endPoint; i--) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0]) -1);
            }
        } else if (condition.contains(">") && increment.contains("++")) {
            for (int i = Variables.VarInt.get(loopVar); i > endPoint; i++) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0]) +1);
            }
        } else if (condition.contains(">") && increment.contains("--")) {
            for (int i = Variables.VarInt.get(loopVar); i > endPoint; i--) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0]) - 1);
            }
        } else if (condition.contains("<") && increment.contains("++")) {
            for (int i = Variables.VarInt.get(loopVar); i < endPoint; i++) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0]) +1);
            }
        }else if (condition.contains("<") && increment.contains("--")) {
            for (int i = Variables.VarInt.get(loopVar); i < endPoint; i--) {
                Calculator.handleCalculation(line);
                Variables.VarInt.replace(loop[0], Variables.VarInt.get(loop[0]) - 1);
            }

        }
        Variables.VarInt.replace("i", 0);
    }
}
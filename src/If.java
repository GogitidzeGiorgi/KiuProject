import java.util.ArrayList;
import java.util.List;

public class If extends Calculator {
    public static boolean skipElse;
    public static boolean goToBool;


    public static void handleIf(String line) {
        System.out.println(Variables.VarBool.entrySet());
        goToBool = false;
        String condition = line.substring(line.indexOf("if") + 2, line.lastIndexOf("{")).strip();
        List<String> parts = new ArrayList<>(List.of(condition.split(" ")));
      for(String s : Variables.VarBool.keySet()) {
          if(line.contains(s)) {
              goToBool = true;
              System.out.println("GoToBool");
              if(Variables.VarBool.get(s)){
                  Interpreter.skipNextLine = false;
                  System.out.println(line + ".true");

                  skipElse = true;
              }else{
                  skipElse = false;
                  Interpreter.skipNextLine = true;
                  System.out.println(line + ".false");
                  handleElse(line);
              }
          }
      }
        if(!goToBool) {
            boolean ifConditionMet = evaluateCondition(parts.get(0).trim(), parts.get(1).trim(), parts.get(2).trim());
            if (ifConditionMet) {
                Interpreter.skipNextLine = false;
                skipElse = true;
                System.out.println(line + ".true");
            } else {
                System.out.println(line + ".false");

                skipElse = false;
                Interpreter.skipNextLine = true;
                handleElse(line);
            }
        }else{
            goToBool = false;
        }

    }

    public static void handleElse(String line) {
        int elseIndex = line.indexOf("else");
        if (elseIndex != -1) {
            String elseBlock = line.substring(elseIndex + 4).strip();

            if (elseBlock.startsWith("{")) {
                Interpreter.skipNextLine = false;
            } else {
                throw new IllegalArgumentException("Malformed 'else' statement.");
            }
        }
    }

    public static boolean evaluateCondition(String left, String operator, String right) {
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
            case "!=" -> leftValue != rightValue;
            case ">" -> leftValue > rightValue;
            case "<" -> leftValue < rightValue;
            case ">=" -> leftValue >= rightValue;
            case "<=" -> leftValue <= rightValue;
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
}

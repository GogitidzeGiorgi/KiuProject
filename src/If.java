import java.util.ArrayList;
import java.util.List;

public class If {

    public static void handleIf(String line) {
        String Line = line.substring(line.indexOf("if") + 2, line.lastIndexOf("{")).strip();
        List<String> parts = new ArrayList<>(List.of(Line.split(" ")));
        int value = 0;
//        inIf = true;
//        if(Line.contains("}")){inIf = false;}
        boolean ifStatement = (Line.contains("{"));

        String ifLine = Line.substring(Line.indexOf("if") + 1, Line.indexOf("{") + 1);
        List<String> ifParts = new ArrayList<>(List.of(ifLine.split(" ")));

        String ifVar = ifParts.get(0).strip();
        String operator = "";
        int leftBool = 0;
        int rightBool = 0;
        boolean leftint = false, rightint = false;

        if (Line.contains("<")) {
            operator = "<";
            if (Calculator.getValue(parts.get(0)) !=null && Calculator.getValue(parts.get(2)) !=null ){
                leftBool = Integer.parseInt(parts.get(0));
                rightBool = Integer.parseInt(parts.get(2));
                boolean istrue = leftBool < rightBool;
            } else {
                System.out.println("Null in if");
//            else if (InputScanner.NotANumber(parts.get(0)) && InputScanner.NotANumber(parts.get(0)))
            }
//            {
//                if (Variables.VarInt.get(parts.get(0)) != null) {
//                    leftBool = Variables.VarInt.get(parts.get(0));
//                } else {
//                    System.out.println("NullPointerException");
//                }
//                boolean istrue = leftBool < rightBool;
//                if (istrue) {
//                    Interpreter.skipNextLine = false;
//                } else {
//                    Interpreter.skipNextLine = true;
//                }
//            }else if (InputScanner.DigitCounter(parts.get(0)) == 0 && InputScanner.DigitCounter(parts.get(2)) > 0) {
//                if (Variables.VarInt.get(parts.get(0)) != null) {
//                    leftBool = Variables.VarInt.get(parts.get(0));
//                } else {
//                    System.out.println("NullPointerException");
//                }
//                boolean istrue = leftBool < rightBool;
//                if (istrue) {
//                    Interpreter.skipNextLine = false;
//                } else {
//                    Interpreter.skipNextLine = true;
//                }
//            }else if (InputScanner.DigitCounter(parts.get(0)) == 0 && InputScanner.DigitCounter(parts.get(2)) > 0) {
//                if (Variables.VarInt.get(parts.get(0)) != null) {
//                    leftBool = Variables.VarInt.get(parts.get(0));
//                } else {
//                    System.out.println("NullPointerException");
//                }
//                boolean istrue = leftBool < rightBool;
//                if (istrue) {
//                    Interpreter.skipNextLine = false;
//                } else {
//                    Interpreter.skipNextLine = true;
//                }
//            }

        }
        if (InputScanner.DigitCounter(parts.get(2)) > 0) {
            rightBool = Integer.parseInt(parts.get(2));
            rightint = true;
        } else {
            System.out.println("right:" + parts.get(2));
        }
        if (leftint && rightint && leftBool < rightBool) {
            System.out.println(leftBool + "<" + rightBool);
        } else if (Line.contains(">")) {
            operator = ">";
        } else if (Line.contains("==")) {
            operator = "==";
        }



    }
}

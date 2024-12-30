import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interpreter extends Calculator {

    //    static boolean nextLine = true;
//    static int forLine = -1;
//
//    public static void eval(String code) {
//        String[] lines = code.split("\n");
//        System.out.println(Arrays.toString(lines));
//        for (int i = 0; i < lines.length; i++) {
//            lines[i] = lines[i].strip();
//            if (!nextLine) {continue;}
//                if (lines[i].isEmpty()) continue;
//
//            if (lines[i].contains(":=")&&!lines[i].contains("+=")&&!lines[i].contains("-=")&&!lines[i].contains("*=")&&!lines[i].contains("/=")) {
//                Variables.assign(lines[i]);
//            }
//            if (lines[i].startsWith("fmt.Println")) {
//                handlePrint(lines[i]);
//            }
//            if ((lines[i].contains("for") && lines[i].contains("++")) || (lines[i].contains("for") && lines[i].contains("--"))) {
//                forLine = i;
//                Loops.handleForLoop(i, lines);
//            } else if (lines[i].contains("for")) {
//                forLine = i;
//                Loops.handleWhileLoop(lines[i]);
//            }
//            if (lines[i].contains("=")) {
//                if (!(lines[i].contains("*") || lines[i].contains("+") || lines[i].contains("/"))) {
//                    Variables.assign(lines[i]);
//                } else {
//                    Calculator.handleCalculation(lines[i]);
//                }
//            }
//            if (lines[i].startsWith("print")) {
//                handlePrint(lines[i]);
//            }
//            if (lines[i].contains("for")) {
//                String forooo = lines[i];
//                String[] arr = new String[0];
//                for (int j = i; !lines[j].contains("}"); j++) {
//                    Loops.handleForLoop(i, lines);
//                }
//            }
//
//            if (lines[i].contains("if")) {
//                handleIf(lines[i]);
//            }
//            if (lines[i].contains("b = (x == reversed)")) {
//                handleBool(lines[i]);
//            }else{if (lines[i].contains("}") && Loops.isLoop){
//            nextLine = true;
//            i = forLine;
//            }
//        }
//    }
//}
//
//
//    private static void handleBool(String line) {
//
//    }
//
////    private static void handleReturn(String line) {
////        String  returnVal = line.substring(line.indexOf("return")+6).strip();
////        boolean retBool;
////        if (returnVal == "false"){
////            retBool = false;                                                  *Work in Progress*
////        }
////        if (returnVal == "true"){
////            retBool = true;
////        }
////    }
//
//    public static void handlePrint(String line) {
//        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).strip();
//
//        if(Variables.VarInt.containsKey(varName)){
//            System.out.println("Program Output:"+Variables.VarInt.get(varName));
//        }else if(Variables.VarBool.containsKey(varName)){
//            System.out.println("Program Output:"+Variables.VarBool.get(varName));
//        }else if(Variables.VarString.containsKey(varName)){
//            System.out.println("Program Output:"+Variables.VarString.get(varName));
//        }
//
////        Integer value = Variables.VarInt.get(varName);
////        if (value != null) {
////            System.out.print(value);
////        } else {
////            System.out.println("Variable " + varName + " is not defined.");
////        }
//    }
//    static void handlePrintln(String line) {
//        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).strip();
//        Integer value = Variables.VarInt.get(varName);
//        if (value != null) {
//            System.out.println(value);
//        } else {
//            System.out.println("Variable " + varName + " is not defined.");
//        }
//    }
//
//    public static void handleFor(String line) {
//        List<String> lines = new ArrayList<>(List.of(line.split(" ")));
////        Variables.VarBool.get(line.indexOf(lines(0))) = (x == reversed);
//
//
//    }
//
//    public static void handleIf(String line) {
//        nextLine = false;
//        String Line = line.substring(line.indexOf("if")+2, line.lastIndexOf("{")).strip();
//        List<String> parts = new ArrayList<>(List.of(Line.split(" ")));
//        int value = 0;
////        inIf = true;
////        if(Line.contains("}")){inIf = false;}
//        boolean ifStatement = (Line.contains("{"));
//
//        String ifLine = Line.substring(Line.indexOf("if")+1, Line.indexOf("{")+1);
//
//        List<String > ifParts = new ArrayList<>(List.of(ifLine.split(" ")));
//
//        String ifVar = ifParts.get(0).strip();
//        String  operator = "";
//        int leftBool = 0;
//        int rightBool = 0;
//        boolean leftint = false, rightint = false;
//
//        if (Line.contains("<")) {
//            operator = "<";
//            if (InputScanner.DigitCounter(parts.get(0)) > 0 && InputScanner.DigitCounter(parts.get(2)) > 0) {
//                leftBool = Integer.parseInt(parts.get(0));
//                leftint = true;
//                boolean istrue = leftBool < rightBool;
//            } else if (InputScanner.DigitCounter(parts.get(0)) == 0 && InputScanner.DigitCounter(parts.get(2)) > 0){
//                if(Variables.VarInt.get(parts.get(0))!=null) {
//                    leftBool = Variables.VarInt.get(parts.get(0));
//                }else{System.out.println("NullPointerException");}
//                boolean istrue = leftBool < rightBool;
//                if(istrue){
//                    System.out.println("true");nextLine = true;
//                }else{nextLine = false;}
//            }System.out.println("false nextLine:"+nextLine);
//        }
//        if(InputScanner.DigitCounter(parts.get(2))>0){
//            rightBool = Integer.parseInt(parts.get(2));
//            rightint = true;
//        }else{
//            System.out.println("right:" + parts.get(2));
//        }
//        if(leftint && rightint && leftBool<rightBool){System.out.println(leftBool + "<" + rightBool);}
//
//        else if (Line.contains(">")) {
//            operator = ">";
//        } else if (Line.contains("==")) {
//            operator = "==";
//        }
//
//        if (InputScanner.CheckInt(ifParts.get(0).replace(';', ' ').strip()) && Line.contains(":=")) {
//            int start = Integer.parseInt(ifParts.get(0).replace(';', ' ').strip());
//            int end = Variables.VarInt.get(ifParts.get(0).replace(';', ' ').strip());
//
//            int sum = Variables.VarInt.get("sum");
//
//            for (int i = start; i <= end; i++) {
//                sum += i;
//            }
//            Variables.VarInt.replace("sum", sum);
//        }
//
//    }
//    public static void handleOr(String line) {
//        String orLine = line.substring(line.indexOf("||")+2).strip();
//    }
    public static void eval(String code) {
        String[] lines = code.split("\n");
        System.out.println(Arrays.toString(lines));
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();

            if (lines[i].isEmpty()) continue;

            if (lines[i].contains(":=")) {
                Variables.assign(lines[i]);
            }
            if (lines[i].startsWith("fmt.Println")) {
                Print.handlePrint(lines[i]);
            }
            if((lines[i].contains("for") && lines[i].contains("++")) || (lines[i].contains("for") && lines[i].contains("--"))){
                Loops.handleForLoop(i, lines);
            }else if(lines[i].contains("for")){
                Loops.handleWhileLoop(lines[i]);
            }
        }
    }
}




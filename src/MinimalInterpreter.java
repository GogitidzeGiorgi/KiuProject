import java.util.*;

public class MinimalInterpreter {

    //    public static Map<String, Integer> ifVariables = new HashMap<>(); May need later
    static boolean nextLine = true;

    public static void eval(String code) {
        String[] lines = code.split("\n");
        for (String line : lines) {
            line = line.trim();
            if(nextLine) {

                if (line.isEmpty()) continue;

                if (line.contains(":=")) {
                    Variables.assign(line);
                }
                if (line.startsWith("print")) {
                    handlePrint(line);
                }
                if (line.contains("for")) {
                    handleLoop(line);
                }
                if (line.contains("if")) {
                    handleIf(line);
                }
//                if (line.contains("return")) {
//                    handleReturn(line);          *Work in Progress*
//                }
            }
        }
    }

//    private static void handleReturn(String line) {
//        String  returnVal = line.substring(line.indexOf("return")+6).trim();
//        boolean retBool;
//        if (returnVal == "false"){
//            retBool = false;                                                  *Work in Progress*
//        }
//        if (returnVal == "true"){
//            retBool = true;
//        }
//    }

    public static void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();

        if(Variables.VarInt.containsKey(varName)){
            System.out.println("Program Output:"+Variables.VarInt.get(varName));
        }else if(Variables.VarBool.containsKey(varName)){
            System.out.println("Program Output:"+Variables.VarBool.get(varName));
        }else if(Variables.VarString.containsKey(varName)){
            System.out.println("Program Output:"+Variables.VarString.get(varName));
        }

//        Integer value = Variables.VarInt.get(varName);
//        if (value != null) {
//            System.out.print(value);
//        } else {
//            System.out.println("Variable " + varName + " is not defined.");
//        }
    }
    static void handlePrintln(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        Integer value = Variables.VarInt.get(varName);
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Variable " + varName + " is not defined.");
        }
    }

    public static void handleLoop(String line) {
//        String[] parts = line.split(" ");
//        String loopVar = parts[1].trim();
//        if (InputScanner.CheckInt(parts[3].replace(';', ' ').trim())) {
//            int start = Integer.parseInt(parts[3].replace(';', ' ').trim());
//            int end = Variables.VarInt.get(parts[6].replace(';', ' ').trim());
//
//            int sum = Variables.VarInt.get("sum");
//
//            for (int i = start; i <= end; i++) {
//                sum += i;
//            }
//            Variables.VarInt.replace("sum", sum);
//        }
    }

    public static void handleIf(String line) {
        String Line = line.substring(line.indexOf("if")+2, line.lastIndexOf("{")).trim();
        List<String> parts = new ArrayList<>(List.of(Line.split(" ")));
        int value = 0;

        boolean ifStatement = (Line.contains("{"));

        String ifLine = Line.substring(Line.indexOf("if")+1, Line.indexOf("{")+1);

        List<String > ifParts = new ArrayList<>(List.of(ifLine.split(" ")));

        String ifVar = ifParts.get(0).trim();
        String  operator = "";
        int leftBool = 0;
        int rightBool = 0;
        boolean leftint = false, rightint = false;

        if (Line.contains("<")) {
            operator = "<";
            if (InputScanner.DigitCounter(parts.get(0)) > 0 && InputScanner.DigitCounter(parts.get(2)) > 0) {
                leftBool = Integer.parseInt(parts.get(0));
                leftint = true;
                boolean istrue = leftBool < rightBool;
            } else if (InputScanner.DigitCounter(parts.get(0)) == 0 && InputScanner.DigitCounter(parts.get(2)) > 0){

               if(Variables.VarInt.get(parts.get(0))==null) {
                   leftBool = Variables.VarInt.get(parts.get(0));
                   System.out.println("NullPointerException");;
               }
                boolean istrue = leftBool < rightBool;
                if(istrue){
                    if (leftBool<rightBool){
                        nextLine = true;
                    }else{ nextLine = false;}
                }
            }
        }
        if(InputScanner.DigitCounter(parts.get(2))>0){
            rightBool = Integer.parseInt(parts.get(2));
            rightint = true;
        }else{
            System.out.println("right:" + parts.get(2));
        }
        if(leftint && rightint && leftBool<rightBool){System.out.println(leftBool + "<" + rightBool);}

        else if (Line.contains(">")) {
            operator = ">";
        } else if (Line.contains("==")) {
            operator = "==";
        }

        if (InputScanner.CheckInt(ifParts.get(0).replace(';', ' ').trim()) && Line.contains(":=")) {
            int start = Integer.parseInt(ifParts.get(0).replace(';', ' ').trim());
            int end = Variables.VarInt.get(ifParts.get(0).replace(';', ' ').trim());

            int sum = Variables.VarInt.get("sum");

            for (int i = start; i <= end; i++) {
                sum += i;
            }
            Variables.VarInt.replace("sum", sum);
        }

    }
    public static void handleOr(String line) {
        String orLine = line.substring(line.indexOf("||")+2).trim();
    }
}


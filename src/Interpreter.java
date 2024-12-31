import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interpreter {
    public static  boolean skipNextLine = false;
    public static void eval(String code) {
        String[] lines = code.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
            if(!lines[i].contains("}")&&skipNextLine){
                skipNextLine = false;
                continue;
            } if(lines[i].contains("else")&&If.skipElse){
                skipNextLine = true;
                continue;
            }
            if (lines[i].isEmpty()) continue;
            if(lines[i].contains("break")){

            }

//            if (!If.nextLine){
//                If.nextLine = true;
//                continue;
//            }
//
//            if (lines[i].contains("if")) {
//                If.handleIf(lines[i]);
//            }


            if(lines[i].contains("if")){
                If.handleIf(lines[i]);
            }
            if(lines[i].contains("for") && lines[i].contains("++") || (lines[i].contains("for") && lines[i].contains("--"))){
                Loops.handleForLoop(i, lines);
            }else if(lines[i].contains("for")){

                Loops.handleWhileLoop(i, lines);
            }else if(lines[i].contains("+=")||lines[i].contains("-=")||lines[i].contains("*=")||lines[i].contains("/=")
                    ||lines[i].contains("%=")) {
                Calculator.handleCalculation(lines[i]);
            }else if(lines[i].contains("+")||containsSub(lines[i])||lines[i].contains("*")||lines[i].contains("/")
                    ||lines[i].contains("%")){
                Calculator.arithmetic(lines[i]);
            }else if (lines[i].contains("=") && !(lines[i].contains("==") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("+") || lines[i].contains("!"))){
                Variables.assign(lines[i]);
            }
            if (lines[i].startsWith("fmt.Println")) {
                Print.handlePrint(lines[i]);
            }
        }
    }

    private static boolean containsSub(String line) {
        Boolean boolVal = true;
        List<String> Boolwords = new ArrayList<>(List.of(line.split("=")));
        if (Boolwords.size() == 2 && Boolwords.get(1).contains("-"));{
            boolVal = false;
        }


        return boolVal;
    }
}




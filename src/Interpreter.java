import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    public static  boolean skipNextLine = false;
    /// skipNextLine is for checking if the following lines should be skipped
    /// It's used for if, else and for statements
    /// is it's true lines are skipped until interpreter has caught "}"
    public static void eval(String code) {
        String[] lines = code.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
            if(lines[i].contains("}")){
                skipNextLine = false;
            }
            if(skipNextLine){
                continue;
            }

            if(lines[i].contains("else")&&If.skipElse){
                skipNextLine = true;
                continue;
            }
            if (lines[i].isEmpty()) continue;
            if(lines[i].contains("break")){
                skipNextLine = true;
                continue;
            }
            ///  if line contains break this makes it so that it stops iterating through loop

            if(lines[i].contains("if")){
                If.handleIf(lines[i]);
            }
            ///  if line contains if it's handled by handleIf

            if(lines[i].contains("for") && lines[i].contains("++") || (lines[i].contains("for") && lines[i].contains("--"))){
                Loops.handleForLoop(i, lines);
                ///  if it checks as for loop it's handled by handleForLoop

            }else if(lines[i].contains("for")){

                Loops.handleWhileLoop(i, lines);
                ///  if it checks as "short" for it's handled by handleWhileLoop

            }else if(lines[i].contains("+=")||lines[i].contains("-=")||lines[i].contains("*=")||lines[i].contains("/=")
                    ||lines[i].contains("%=")) {
                Calculator.handleCalculation(lines[i]);
            }else if(lines[i].contains("+")||containsSub(lines[i])||lines[i].contains("*")||lines[i].contains("/")
                    ||lines[i].contains("%")){
                Calculator.arithmetic(lines[i]);
            }else if (lines[i].contains("=") && !(lines[i].contains("==") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("+") || lines[i].contains("!"))){
                Variables.assign(lines[i]);
            }
            if (lines[i].trim().contains("fmt.Println")) {
                Print.handlePrintln(lines[i]);
            }else if (lines[i].trim().contains("fmt.Print")) {
                Print.handlePrint(lines[i]);
            }
            //Interpreter checks every String's line in correct order to determine what operation must be done and which
            //method should be called.
        }
    }

    private static boolean containsSub(String line) {
        boolean boolVal = true;
        List<String> boolWords = new ArrayList<>(List.of(line.split("=")));
        if(boolWords.size()>1){
            if (!(boolWords.get(1).indexOf(1)=='-'||boolWords.get(0).indexOf(1)=='-')) {
                boolVal = false;
            }
        }
        if(!line.contains("-")) {
            boolVal =  false;
        }
        return boolVal;
    }
    //There were problems with assigning Value to negative Integer, so instead of putting code as contains("-")
    //we invented containsSub method, which is called in Interpreter.
}






import java.util.Arrays;

public class Interpreter extends Calculator{
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
                MinimalInterpreter.handlePrint(lines[i]);
            }
            if((lines[i].contains("for") && lines[i].contains("++")) || (lines[i].contains("for") && lines[i].contains("--"))){
                Loops.handleForLoop(i, lines);
            }else if(lines[i].contains("for")){
                Loops.handleWhileLoop(lines[i]);
            }
        }
    }
}

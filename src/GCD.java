import java.util.HashMap;
import java.util.Map;

public class GCD {
    private final Map<String, Integer> variables = new HashMap<>();

    public void eval(String code) {
        String[] lines = code.split("\n");
        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) continue;

            if (line.contains(":=")) {
                handleAssignment(line);
            }
            if (line.startsWith("fmt.Println")) {
                handlePrint(line);
            }
            if (line.contains("for")) {
                handleWhileLoop(line);
            }
        }
    }

    private void handleAssignment(String line) {
        String[] parts = line.split(":=");
        String valueName = parts[0].trim();
        String value = parts[1].trim();

        try {
            variables.put(valueName, Integer.parseInt(value));
        } catch (NumberFormatException e) {
            variables.put(valueName, variables.get(value));
        }
    }

    private void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).trim();
        Integer value = variables.get(varName);
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Variable " + varName + " is not defined.");
        }
    }

    private void handleWhileLoop(String line) {

        int a = variables.get("a");
        int b = variables.get("b");

        while(b != 0){
            int temp = b;
            b= a % b;
            a = temp;
        }
        variables.replace("a", a);
        variables.replace("b", b);
    }
}

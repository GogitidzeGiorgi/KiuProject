import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Factorial {
    private final Map<String, Integer> variables = new HashMap<>();

    public void eval(String code) {
        String[] lines = code.split("\n");
        System.out.println(Arrays.toString(lines));
        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) continue;

            if (line.contains(":=")) {
                handleAssignment(line);
            }
            if (line.startsWith("print")) {
                handlePrint(line);
            }
            if (line.contains("for")) {
                handleLoop(line);
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

    private void handleLoop(String line) {
        String[] parts = line.split(" ");
        String loopVar = parts[1].trim();
        int start = Integer.parseInt(parts[3].replace(';', ' ').trim());
        int end = variables.get(parts[6].replace(';', ' ').trim());

        int factorial = variables.get("factorial");

        for (int i = start; i <= end; i++) {
            factorial *= i;
        }
        variables.replace("factorial", factorial);
    }
}
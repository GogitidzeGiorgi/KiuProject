public class MinimalInterpreter extends Calculator {
    public void eval(String code) {
        String[] lines = code.split("\n"); // Split by statement terminator
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Handle variable assignment
            if (line.contains(":=")) {
                handleAssignment(line);
            }
            // Handle print statements
            else if (line.startsWith("Println")) {
                handlePrintln(line);
            }
        }
    }


    public static void main(String[] args) {
        MinimalInterpreter interpreter = new MinimalInterpreter();

        // Example program: Calculate and print the sum of 10 and 20
        String program = """
            n := 9
            sum := 2
            Println(n + sum)
        """;

        interpreter.eval(program);
    }
}
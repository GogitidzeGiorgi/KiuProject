public class MinimalInterpreter extends Assignment {
    public void eval(String code) {
        String[] lines = code.split("\n"); // Split by statement terminator, which for GoLang is Enter
        for (String line : lines) {
            line = line.strip();
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

    public void handlePrintln(String line) {
        //Any operation with Variables and Numbers works in Println() too.
        String printed = line.substring(line.indexOf('(') + 1, line.indexOf(')')).strip();
        if (printed.isEmpty()) {
            System.out.println(); //If it's empty just type nothing
        } else {
            if (variables.containsKey(printed)) {
                System.out.println(variables.get(printed) + "\n"); // If it is only Variable, we print it's value
            } else {
                try {
                    int result = arithmetic(printed); //If it is Variable + Number, we call methods again.
                    System.out.println(result + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid number format\n"); //All are catches for exceptions
                } catch (Exception e) {                                   //i.e. division by 0 and etc.
                    System.out.println("Error: " + e.getMessage() + "\n");
                }
            }
        }
    }


    public static void main(String[] args) {
        MinimalInterpreter interpreter = new MinimalInterpreter();
        
        String program = """
            n := 2
            sum := 2 + n
            Println(3)
        """;

        interpreter.eval(program);
    }
}
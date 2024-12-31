public class Print {
    static void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).strip();
        Integer value = Variables.VarInt.get(varName);
        if (value != null) {
            System.out.println(value);
        } else {
            System.out.println("Variable " + varName + " is not defined.");
        }
    }

    public static void main(String[] args) {
        System.out.println(12345 % 10);
    }
}
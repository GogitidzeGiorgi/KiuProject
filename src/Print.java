public class Print {
    static void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).strip();
        if(varName.startsWith("\"") && varName.endsWith("\"")) {
            varName = varName.substring(1, varName.length() - 1);
        }else {
            Integer value = Variables.VarInt.get(varName);
            if (value != null) {
                System.out.println(value);
            } else {
                System.out.println("Variable " + varName + " is not defined.");
            }
        }
    }
}
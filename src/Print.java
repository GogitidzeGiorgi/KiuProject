public class Print {
    static void handlePrint(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).strip();
        if(varName.startsWith("\"") && varName.endsWith("\"")) {
            varName = varName.substring(1, varName.length() - 1);
            System.out.print(varName);
        }else if(Variables.VarInt.containsKey(varName)) {
            Integer value = Variables.VarInt.get(varName);
            System.out.print(value);
        }else if(Variables.VarBool.containsKey(varName)) {
            Boolean value = Variables.VarBool.get(varName);
            System.out.print(value);
        }else if(Variables.VarString.containsKey(varName)) {
            String value = Variables.VarString.get(varName);
            System.out.print(value);
        }else {
            System.out.print("Variable " + varName + " is not defined.");
        }
        //Whether we decide to use Print or Println, it prints either Variable's Integer/Boolean/String value or raw string
        //i.e. Println("Raw String, not stored")
    }

    static void handlePrintln(String line) {
        String varName = line.substring(line.indexOf('(') + 1, line.indexOf(')')).strip();
        if(varName.startsWith("\"") && varName.endsWith("\"")) {
            varName = varName.substring(1, varName.length() - 1);
            System.out.println(varName);
        }else if(Variables.VarInt.containsKey(varName)) {
            Integer value = Variables.VarInt.get(varName);
            System.out.println(value);
        }else if(Variables.VarBool.containsKey(varName)) {
            Boolean value = Variables.VarBool.get(varName);
            System.out.println(value);
        }else if(Variables.VarString.containsKey(varName)) {
            String value = Variables.VarString.get(varName);
            System.out.println(value);
        }else {
            System.out.println("Variable " + varName + " is not defined.");
        }
    }
    //Only difference between these two methods is that, Println prints out anything and uses \n command, which regular
    //Print doesn't do.
}
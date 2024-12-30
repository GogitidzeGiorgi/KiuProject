import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variables extends Calculator {
    public static Map<String, Integer> VarInt = new HashMap<>();    /// Integer "storage"
    public static Map<String, String> VarString = new HashMap<>();  /// String "storage"
    public static Map<String, Boolean> VarBool = new HashMap<>();   /// Boolean "storage"
    public static boolean reassign;
    public static List<String> IntList;


    public static void assign(String code) {
        reassign = !code.contains(":=");

        if (reassign) {
            IntList = List.of(code.split("="));
        } else {
            IntList = List.of(code.split(":="));
        } //
       String line =  code.replaceAll(" ", "");
            if (!InputScanner.NotANumber(IntList.get(1).strip()) && !IntList.get(0).trim().contains(" ") && !IntList.get(0).trim().isEmpty()) {   // [b] [num]
                Variables.Integer();
            } else if (InputScanner.CheckBool(line)) {
                Variables.Boolean(line);
            } else if (InputScanner.CheckString(line)) {
                Variables.String(line);
            }
      }

    private static void Integer() {
                       // true                                  // true                                      true                true                                true
            if (!InputScanner.NotANumber(IntList.get(1).trim()) && !IntList.get(0).contains("-") && (!(IntList.get(1).contains("+"))  &&  !(IntList.get(1).contains("*")  && !(IntList.get(1).contains("/"))))) {
                                 // If [a] is any other String, for not Integer value
                if (VarString.containsKey(IntList.get(0).strip()) || VarBool.containsKey(IntList.get(0).strip())) {
                    System.out.println("Error: Another Variable is assigned to that name");
                } else { //if [a] is New or assigned to number
                            // [5] is a number   &&  reassign is false, so it's :=
                    if (!InputScanner.NotANumber(IntList.get(1).strip()) && !reassign) {
                                   // if [a] is already defined
                        if (VarInt.containsKey(IntList.get(0).strip())) {
//                            VarInt.put(IntList.get(0).strip(), Integer.valueOf(IntList.get(1).strip()));

                        System.out.println("Error: no new variables on left side of :=" + IntList.get(0).strip());
                        } else { // if [a] is not defined
                            VarInt.put(IntList.get(0).strip(), getValue(IntList.get(1).strip()));
                        }
                    } else if (!InputScanner.NotANumber(IntList.get(1).strip()) && reassign) {
                        VarInt.put(IntList.get(0).strip(), getValue(IntList.get(1).strip()));

                    }
                }
            }
        }


    private static void String(String line) {
        List<String> StringList ;
        if (reassign) {
            StringList = List.of(line.split("="));
        } else {
            StringList = List.of(line.split(":="));
        }
        if (InputScanner.CheckString(StringList.get(1))) {
            if (VarInt.containsKey(StringList.get(0).strip())) {
                System.out.println("Error: Another Variable is assigned to name:" + StringList.get(0).strip());
            } else if (!reassign) {
                if (VarString.containsKey(StringList.get(0).strip())) {
                    System.out.println("Error: no new variables on left side of :=");
                } else {
                    VarString.put(StringList.get(0).strip(), StringList.get(1).strip());
                }
            }else{
                VarString.put(StringList.get(0).strip(), StringList.get(1).strip());
            }
        }

    }

    private static void Boolean(String line) {
        List<String> BoolList;
        if (reassign) {
            BoolList = List.of(line.split("="));
        } else {
            BoolList = List.of(line.split(":="));
        }
        if (InputScanner.CheckBool(BoolList.get(1))) {
            if (VarString.containsKey(BoolList.get(0).strip()) || VarInt.containsKey(BoolList.get(0).strip())) {
                System.out.println("Error: Another Variable is assigned to that name");
            } else {
                if (!reassign) {
                    if (VarBool.containsKey(BoolList.get(0).strip())) {
                        System.out.println("Error: no new variables on left side of :=");
                    } else {
                        if (line.contains("true")) {
                            VarBool.put(BoolList.get(0).strip(), true);
                        } else {
                            VarBool.put(BoolList.get(0).strip(), false);
                        }
                    }
                }else{
                    if (line.contains("true")) {
                        VarBool.put(BoolList.get(0).strip(), true);
                    } else {
                        VarBool.put(BoolList.get(0).strip(), false);
                    }
                }
            }
        }
    }
}


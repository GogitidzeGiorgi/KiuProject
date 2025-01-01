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
        }
        String line =  code.replaceAll(" ", "");
        if (!InputScanner.NotANumber(IntList.get(1).strip()) && !IntList.get(0).trim().contains(" ") && !IntList.get(0).trim().isEmpty()) {   // [b] [num]
            Variables.Integer();
        } else if (InputScanner.CheckBool(line)) {
            Variables.Boolean(line);
        } else if (InputScanner.CheckString(line)) {
            Variables.String(line);
        }
        //Here are put variables, which are assigned to single elements -  a := 5, there are no operations.
        //It also chooses depending on what value element holds, whether variable is String, Integer or boolean.
        //reassign is created in case code contains =
        //Difference is that in GoLang reassigning new value to Variable is prohibided with operator ":=", which is used
        //only to initialize new variables.
    }

    private static void Integer() {
        if (!InputScanner.NotANumber(IntList.get(1).trim()) && !IntList.get(0).contains("-") && (!(IntList.get(1).contains("+"))  &&  !(IntList.get(1).contains("*")  && !(IntList.get(1).contains("/"))))) {
            if (VarString.containsKey(IntList.get(0).strip()) || VarBool.containsKey(IntList.get(0).strip())) {
                System.out.println("Error: Another Variable is assigned to that name");
            } else {
                if (!InputScanner.NotANumber(IntList.get(1).strip()) && !reassign) {
                    VarInt.put(IntList.get(0).strip(), getValue(IntList.get(1).strip()));
                } else if (!InputScanner.NotANumber(IntList.get(1).strip()) && reassign) {
                    VarInt.put(IntList.get(0).strip(), getValue(IntList.get(1).strip()));

                }
            }
        }
    }//Safety precautions, this code simply stores integer into variable.

    private static void String(String line) {
        List<String> StringList ;
        if (reassign) {
            StringList = List.of(line.split("="));
        } else {
            StringList = List.of(line.split(":="));
        }
        if (InputScanner.CheckString(StringList.get(1))) {
            if (VarInt.containsKey(StringList.get(0).strip())) {
                System.out.println("Error: Another Variable is assigned to name:" + StringList.getFirst().strip());
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
    //Safety precautions, this code simply stores String into variable.

    private static void Boolean(String line) {
        List<String> BoolList;
        if (reassign) {
            BoolList = List.of(line.split("="));
        } else {
            BoolList = List.of(line.split(":="));
        }
        if (InputScanner.CheckBool(BoolList.get(1))) {
            if (VarString.containsKey(BoolList.getFirst().strip()) || VarInt.containsKey(BoolList.getFirst().strip())) {
                System.out.println("Error: Another Variable is assigned to that name");
            } else {
                if (!reassign) {
                    if (VarBool.containsKey(BoolList.getFirst().strip())) {
                        System.out.println("Error: no new variables on left side of :=");
                    } else {
                        if (line.contains("true")) {
                            VarBool.put(BoolList.getFirst().strip(), true);
                        } else {
                            VarBool.put(BoolList.getFirst().strip(), false);
                        }
                    }
                }else{
                    if (line.contains("true")) {
                        VarBool.put(BoolList.getFirst().strip(), true);
                    } else {
                        VarBool.put(BoolList.getFirst().strip(), false);
                    }
                }
            }
        }
    }
    //Safety precautions, this code simply stores boolean into variable.
}
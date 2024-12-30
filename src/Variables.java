import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variables {
    public static Map<String, Integer> VarInt = new HashMap<>();    /// Integer "storage"
    public static Map<String, String> VarString = new HashMap<>();  /// String "storage"
    public static Map<String, Boolean> VarBool = new HashMap<>();   /// Boolean "storage"
    public static boolean reassign;

    public static void assign(String code) {
        ArrayList<String> Lines = new ArrayList<>(List.of(code.split("\n")));

        if (code.contains(":=")) {
            reassign = false;
        } else {
            reassign = true;
        }

        for (int i = 0; i < Lines.size(); i++) {
            Lines.set(i, (Lines.get(i).replaceAll(" ", "")));
        }

        for (String line : Lines) {
            if (InputScanner.CheckInt(code)) {
                Variables.Integer(line);
            } else if (InputScanner.CheckBool(code)) {
                Variables.Boolean(line);
            } else if (InputScanner.CheckString(code)) {
                Variables.String(line);
            }
        }
    }

    private static void Integer(String line) {
        List<String> IntList = new ArrayList<>();
        if (reassign) {
            IntList = List.of(line.split("="));
        } else {
            IntList = List.of(line.split(":="));
        }
        if (InputScanner.CheckInt(IntList.get(1))) {
            if (VarString.containsKey(IntList.get(0).strip()) || VarBool.containsKey(IntList.get(0).strip())) {
                System.out.println("Error: Another Variable is assigned to that name");
            } else {
                if (!InputScanner.NotANumber(IntList.get(1).strip()) && !reassign) {
                    if (VarInt.containsKey(IntList.get(0).strip())) {
                        VarInt.put(IntList.get(0).strip(), Integer.valueOf(IntList.get(1).strip()));

//                        System.out.println("Error: no new variables on left side of :=" + IntList.get(0).strip());
                    } else {
                        VarInt.put(IntList.get(0).strip(), Integer.valueOf(IntList.get(1).strip()));
                    }
                } else if (!InputScanner.NotANumber(IntList.get(1).strip()) && reassign) {
                    VarInt.put(IntList.get(0).strip(), Integer.valueOf(IntList.get(1).strip()));

                }
            }
        }
    }

    private static void String(String line) {
        List<String> StringList = new ArrayList<>();
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
        List<String> BoolList = new ArrayList<>();
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


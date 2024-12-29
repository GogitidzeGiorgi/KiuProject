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
            if (InputScanner.checkInt(code)) {
                Variables.Integer(line);
            } else if (InputScanner.checkInt(code)) {
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
        if (InputScanner.checkInt(IntList.get(1))) {
            if (VarString.containsKey(IntList.get(0).trim()) || VarBool.containsKey(IntList.get(0).trim())) {
                System.out.println("Error: Another Variable is assigned to that name");
            } else {
                if (!InputScanner.checkNotInt(IntList.get(1).trim()) && !reassign) {
                    if (VarInt.containsKey(IntList.get(0).trim())) {
                        System.out.println("Error: no new variables on left side of :=");
                    } else {
                        VarInt.put(IntList.get(0).trim(), Integer.valueOf(IntList.get(1).trim()));
                    }
                } else if (!InputScanner.checkNotInt(IntList.get(1).trim()) && reassign) {
                    VarInt.put(IntList.get(0).trim(), Integer.valueOf(IntList.get(1).trim()));

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
            if (VarInt.containsKey(StringList.get(0).trim())) {
                System.out.println("Error: Another Variable is assigned to name:" + StringList.get(0).trim());
            } else if (!reassign) {
                if (VarString.containsKey(StringList.get(0).trim())) {
                    System.out.println("Error: no new variables on left side of :=");
                } else {
                    VarString.put(StringList.get(0).trim(), StringList.get(1).trim());
                }
            }else{
                VarString.put(StringList.get(0).trim(), StringList.get(1).trim());
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
        if (InputScanner.checkInt(BoolList.get(1))) {
            if (VarString.containsKey(BoolList.get(0).trim()) || VarInt.containsKey(BoolList.get(0).trim())) {
                System.out.println("Error: Another Variable is assigned to that name");
            } else {
                if (!reassign) {
                    if (VarBool.containsKey(BoolList.get(0).trim())) {
                        System.out.println("Error: no new variables on left side of :=");
                    } else {
                        if (line.contains("true")) {
                            VarBool.put(BoolList.get(0).trim(), true);
                        } else {
                            VarBool.put(BoolList.get(0).trim(), false);
                        }
                    }
                }else{
                    if (line.contains("true")) {
                        VarBool.put(BoolList.get(0).trim(), true);
                    } else {
                        VarBool.put(BoolList.get(0).trim(), false);
                    }
                }
            }
        }
    }
}


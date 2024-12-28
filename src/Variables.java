import java.util.*;

public class Variables {
    public static Map<String, Integer> VarInt = new HashMap<>(); /// Integer "storage"
    public static Map<String, String> VarString = new HashMap<>(); /// String "storage"
    public static Map<String, Boolean> VarBool = new HashMap<>(); /// Integer "storage"

    public static void assign(String code) {
        ArrayList<String> Lines = new ArrayList<>(List.of(code.split("\n")));

        for (int i = 0; i < Lines.size(); i++) {
            Lines.set(i, (Lines.get(i).replaceAll(" ", "")));
        }
        ///  Variable Integer

        if (InputScanner.checkInt(code)) {

            for (String line : Lines) {
                String[] parts = line.split(":=");
                if (InputScanner.checkInt(parts[1])) {
                    if (VarString.containsKey(parts[0].trim())) {
                        System.out.println("Error: Another Variable is assigned to that name");
                    } else {
                        if (!InputScanner.NotANumber(parts[1].trim())) {
                            VarInt.put(parts[0].trim(), Integer.valueOf(parts[1].trim()));
                        }
                    }
                }
            }

            ///  Variable String
            if (InputScanner.CheckString(code)) {
                for (String line : Lines) {
                    String[] parts = line.split(":=");
                    if (InputScanner.CheckString(parts[1])) {
                        if (VarInt.containsKey(parts[0].trim())) {
                            System.out.println("Error: Another Variable is assigned to that name");
                        } else {
                            VarString.put(parts[0].trim(), parts[1].trim());
                        }
                    }
                }
            }
            ///  Variable Boolean
//
//            if (InputScanner.checkInt(code)) {
//
//                for (String line : Lines) {
//                    String[] parts = line.split(":=");
//                    if (InputScanner.checkInt(parts[1])) {
//                        if (VarString.containsKey(parts[0].trim())) {
//                            System.out.println("Error: Another Variable is assigned to that name");
//                        } else {
//                            if (!InputScanner.checkNotInt(parts[1].trim())) {
//                                VarInt.put(parts[0].trim(), Integer.valueOf(parts[1].trim()));
//                            }
//                        }
//                    }
//                }  this is just a blueprint
                System.out.println("Integer Map: " + VarInt.entrySet());
                System.out.println("String Map: " + VarString.entrySet());
            }
        }
    }


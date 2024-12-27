import java.util.*;

 public class Variables {
    public static void main(String[] args) {
        ///  Test:
        String code = """
            n := 0
            m := "one"
            Println(n)
        """;
        Map<String, Integer> VarInt = new HashMap<>(); /// Integer "storage"
        Map<String, String> VarString = new HashMap<>(); /// String "storage"
        ArrayList <String> Lines = new ArrayList<>(List.of(code.split("\n")));
        for(int i =0; i<Lines.size(); i++){
            Lines.set(i, (Lines.get(i).replaceAll(" ", "")));
        }
        int intCount = InputCounter.IntCounter(code);
        int stringCount = InputCounter.StringCounter(code);

        ///  Variable Integer
        if (InputCounter.checkInt) {
            for (int i = 0; i < intCount; i++) {
                VarInt.put(Lines.get(i).substring(0, Lines.get(i).indexOf(":=")), Integer.valueOf(Lines.get(i).substring(Lines.get(i).length() - 1, Lines.get(i).length())));
            }System.out.println("Integer Map: " + VarInt);
        }

        ///  Variable String
        if (InputCounter.checkString ) {
            for (int i = intCount; i < stringCount+intCount ; i++) {
                VarString.put(Lines.get(i).substring(0, Lines.get(i).indexOf(":=")), Lines.get(i).substring(Lines.get(i).indexOf("\"")+1 , Lines.get(i).lastIndexOf("\"")));
            }
            System.out.println("String Map: " +VarString);
        }
    }
}



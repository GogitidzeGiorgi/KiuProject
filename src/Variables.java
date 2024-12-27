import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variables {
    public static Map<String, Integer> VarInt = new HashMap<>(); /// Integer "storage"
    public static Map<String, String> VarString = new HashMap<>(); /// String "storage"

    public static void vars(String code) {
        ArrayList <String> Lines = new ArrayList<>(List.of(code.split("\n")));

        for(int i =0; i<Lines.size(); i++){
            Lines.set(i, (Lines.get(i).replaceAll(" ", "")));
        }
        Map<Integer, Integer> digitCount = new HashMap<>();


        for (int i =0; i<Lines.size(); i++){
            if (InputCounter.checkInt) {digitCount.put(i, InputCounter.DigitCounter(Lines.get(i)));}
            digitCount.put(i, InputCounter.DigitCounter(Lines.get(i)));
            if (InputCounter.checkString ) {VarString.put(Lines.get(i).substring(0, Lines.get(i).indexOf(":=")), Lines.get(i).substring(Lines.get(i).indexOf("\"")+1 , Lines.get(i).lastIndexOf("\"")));}
        }

        ///  Variable Integer
        if (InputCounter.checkInt) {
            for (String line : Lines) {
                if (InputCounter.checkInt2(line.substring(line.indexOf(":=")))) {
                    VarInt.put(line.substring(0, line.indexOf(":=")), Integer.valueOf(line.substring(line.indexOf(":=") + 2)));
                }
            }
        }
        ///  Variable String

        for (String line : Lines) {InputCounter.StringCounter(line);
            if (InputCounter.checkString ) {
                VarString.put(line.substring(0, line.indexOf(":=")), line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")));
            }
        }
        System.out.println("Integer Map: " + VarInt.entrySet());
        System.out.println("String Map: " +VarString.entrySet());

    }
}



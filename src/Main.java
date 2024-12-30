public class Main {
    public static void main(String[] args) {
        String code = """
                b := true
                x := 12
                c := "one"
                
                if x < 0 {
                        b = false
                    }
                
                    reversed := 0
                    for x != 0 {
                        reversed = reversed*10 + x%10
                        x /= 10
                    }
                
                    b = (x == reversed)
                    print(b)
                """;
        MinimalInterpreter.eval(code);
        System.out.println();
        System.out.println("*****");
        System.out.println("Integer Map: " + Variables.VarInt.entrySet());
        System.out.println("String Map: " + Variables.VarString.entrySet());
        System.out.println("Boolean Map: " + Variables.VarBool.entrySet());
        System.out.println("CheckedInt:" + InputScanner.CheckedInt);
        System.out.println("*****");
/// Try to make it work in calculator

    }
}

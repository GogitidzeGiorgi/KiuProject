public class Main {
    public static void main(String[] args) {
        String code = """
                a := 28
                b := 21
                for b != 0 {
                  a = b
                  b = a % b
                }
                fmt.Println(a)
                """;
        Interpreter.eval(code);
        System.out.println();
        System.out.println("*****");
        System.out.println("Integer Map: " + Variables.VarInt.entrySet());
        System.out.println("String Map: " + Variables.VarString.entrySet());
        System.out.println("Boolean Map: " + Variables.VarBool.entrySet());
        System.out.println("CheckedInt:" + InputScanner.CheckedInt);
        System.out.println("*****");

    }
}
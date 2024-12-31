public class Main {
    public static void main(String[] args) {
        String code = """
                a := 28
                b := 2
                if a != 2 {
                   fmt.Println(a)
                }else{
                   fmt.Println(b)
                }
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
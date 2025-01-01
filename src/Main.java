public class Main {
    public static void main(String[] args) {
        String code = """
                a := 28
                b := 21
                c := 0
                for b != 0 {
                    c = b
                    b = a%b
                    a = c
                }
                fmt.Println(a)
                """;
        Interpreter.eval(code);
//        System.out.println("*****");
//        System.out.println("Integer Map: " + Variables.VarInt.entrySet());
//        System.out.println("String Map: " + Variables.VarString.entrySet());
//        System.out.println("Boolean Map: " + Variables.VarBool.entrySet());
//        System.out.println("CheckedInt:" + InputScanner.CheckedInt);
//        System.out.println("*****");


        //Main is used to run commands and check every String.
    }
}
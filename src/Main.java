public class Main {
    public static void main(String[] args) {
        String code = """
                x := 0
                N := 5
                sum :=0
                b := 2
                for i := 0; i <= N; i++{
                          sum += i
                          x++
                          b *= 2
                     }
                fmt.Println(x)
                fmt.Println(N)
                fmt.Println(sum)
                fmt.Println(b)
                """;
        Interpreter.eval(code);
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

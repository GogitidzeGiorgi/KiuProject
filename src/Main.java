public class Main {
    public static void main(String[] args) {
        String code = """
                num := 29
                isPrime := true
                if num <= 1 {
                   isPrime = false
                } else {
                   for i := 2; i * i <= num; i++ {
                      if num%i == 0 {
                         isPrime = false
                      }
                   }
                }
                
                if isPrime {
                   fmt.Println("is prime")
                } else {
                   fmt.Println("is not prime")
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
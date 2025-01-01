public class Calculator {
    static char operator;
    static int value;
    public static boolean retBool;
    static String[] parts;
    static String varName;
    static String expression;
    static String varNameTemp;
    static int valueForVar;

    public static void handleCalculation(String line) throws ArithmeticException {



        // Check for augmented assignment operators
        // need to be checked

        if (line.contains("==")) {
            parts = line.split("==");
            varName = parts[0].strip();  //num%i
            System.out.println(varName);
            varNameTemp = varName; //num%i
            System.out.println(varNameTemp);
            expression = parts[1].strip();
            valueForVar = arithmetic(varNameTemp); // num%i
            System.out.println(valueForVar);
            Variables.VarInt.put(varNameTemp, valueForVar);
            System.out.println(varNameTemp + " = " + valueForVar);
            value = arithmetic(expression); // Assuming this returns an int or a boolean value
            // Assuming you have a method to get the value of the variable
            if (Variables.VarInt.containsKey(varNameTemp)) {
                int currentValue = getValue(varNameTemp);
                boolean isEqual = (currentValue == value);
            }
        } else if(line.contains("++")){
            varName = line.replaceAll("\\+\\+", "");
            if(Variables.VarInt.containsKey(varName)){
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue + 1);
            }
        } else if(line.contains("--")){
            varName = line.replaceAll("--", "");
            if(Variables.VarInt.containsKey(varName)){
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue - 1);
            }

        } else if (line.contains("+=")) {
            parts = line.split("\\+=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue + value);
            }
        } else if (line.contains("-=")) {
            parts = line.split("-=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue - value);
            }
        } else if (line.contains("*=")) {
            parts = line.split("\\*=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue * value);
            }
        } else if (line.contains("/=")) {
            parts = line.split("/=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if(value == 0){
                throw new ArithmeticException("Division by zero");
            }
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue / value);
            }
        } else if (line.contains("%=")) {
            parts = line.split("%=");
            varName = parts[0].strip();
            expression = parts[1].strip();
            value = arithmetic(expression);
            if(value == 0){
                throw new ArithmeticException("Modulo by zero");
            }
            if (Variables.VarInt.containsKey(varName)) {
                int currentValue = getValue(varName);
                Variables.VarInt.put(varName, currentValue % value);
            }

        }
//         Update the variable in the map
    }

    /// Arithmetic




    static int arithmetic(String input) {




        int secValue = 0;
        boolean isvar = false;
        String calcVar = ""; // b := 5 + 2
        if(input.contains(":=") ){
            isvar = true;
            parts = input.split(":=");
            if(!parts[0].strip().contains(" ")){
                expression = parts[1].strip();
                calcVar = parts[0].strip();
            }
        }else if(input.contains("=") ) {
            isvar = true;
            parts = input.split("=");
            if (!parts[0].strip().contains(" ")) {
                expression = parts[1].strip();
                calcVar = parts[0].strip();
            }
        }


        String stripped = expression.replaceAll(" ", "");
            //num%i/
        // Determine the operator present in the expression
        if (stripped.contains("+")) {
            operator = '+';
        } else if (stripped.contains("-")) {
            operator = '-';
        } else if (stripped.contains("*")) {
            operator = '*';
        } else if (stripped.contains("/")) {
            operator = '/';
        } else if (stripped.contains("%")) {
            operator = '%';
        }

        switch (operator) {
            case '+':
                String[] addNumbers = stripped.split("\\+");
                value = 1; // Reset value for multiplication
                if(!InputScanner.NotADigit(addNumbers[0].trim())){
                    value = Integer.parseInt(addNumbers[0].trim());
                }else if (!InputScanner.NotANumber(addNumbers[0].trim())){
                    value = Variables.VarInt.get(addNumbers[0].trim());
                }
                for(int i = 1; i < addNumbers.length; i++) {
                    if (!InputScanner.NotADigit(addNumbers[i].trim())) {
                        secValue = Integer.parseInt(addNumbers[i].trim());
                    } else if (!InputScanner.NotANumber(addNumbers[i].trim())) {
                        secValue = Variables.VarInt.get(addNumbers[i].trim());
                    }
                    value += secValue;
                }
                break;

            case '-':
                String[] subNumbers = stripped.split("-");
                value = 1; // Reset value for multiplication
                if(!InputScanner.NotADigit(subNumbers[0].trim())){
                    value = Integer.parseInt(subNumbers[0].trim());
                }else if (!InputScanner.NotANumber(subNumbers[0].trim())){
                    value = Variables.VarInt.get(subNumbers[0].trim());
                }
                for(int i = 1; i < subNumbers.length; i++) {
                    if (!InputScanner.NotADigit(subNumbers[i].trim())) {
                        secValue = Integer.parseInt(subNumbers[i].trim());
                    } else if (!InputScanner.NotANumber(subNumbers[i].trim())) {
                        secValue = Variables.VarInt.get(subNumbers[i].trim());
                    }
                    value -= secValue;
                }
                break;

            case '*':
                String[] mulNumbers = stripped.split("\\*");
                value = 1; // Reset value for multiplication
                if(!InputScanner.NotADigit(mulNumbers[0].trim())){
                    value = Integer.parseInt(mulNumbers[0].trim());
                }else if (!InputScanner.NotANumber(mulNumbers[0].trim())){
                    value = Variables.VarInt.get(mulNumbers[0].trim());
                }
                for(int i = 1; i < mulNumbers.length; i++) {
                    if (!InputScanner.NotADigit(mulNumbers[i].trim())) {
                        secValue = Integer.parseInt(mulNumbers[i].trim());
                    } else if (!InputScanner.NotANumber(mulNumbers[i].trim())) {
                        secValue = Variables.VarInt.get(mulNumbers[i].trim());
                    }
                    value *= secValue;
                }
                break;

            case '/':
                String[] divNumbers = stripped.split("/");
                if(!InputScanner.NotADigit(divNumbers[0].trim())){
                    value = Integer.parseInt(divNumbers[0].trim());
                }else if (!InputScanner.NotANumber(divNumbers[0].trim())){
                    value = Variables.VarInt.get(divNumbers[0].trim());
                }
                for(int i = 1; i < divNumbers.length; i++) {
                    if (!InputScanner.NotADigit(divNumbers[i].trim())) {
                        secValue = Integer.parseInt(divNumbers[i].trim());
                    } else if (!InputScanner.NotANumber(divNumbers[i].trim())) {
                        secValue = Variables.VarInt.get(divNumbers[i].trim());
                    }
                    if (secValue == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    value /= secValue;
                }
                break;

            case '%':
                String[] modNumbers = stripped.split("%");
                if(!InputScanner.NotADigit(modNumbers[0].trim())){
                    value = Integer.parseInt(modNumbers[0].trim());
                }else if (!InputScanner.NotANumber(modNumbers[0].trim())){
                    value = Variables.VarInt.get(modNumbers[0].trim());
                }
                for(int i = 1; i < modNumbers.length; i++) {
                    if (!InputScanner.NotADigit(modNumbers[i].trim())) {
                        secValue = Integer.parseInt(modNumbers[i].trim());
                    } else if (!InputScanner.NotANumber(modNumbers[i].trim())) {
                        secValue = Variables.VarInt.get(modNumbers[i].trim());
                    }
                    if (secValue == 0) {
                        throw new ArithmeticException("Modulo by zero");
                    }
                    value %= secValue;
                }
                break;

            default:
                value = getValue(stripped);
                break;

        }


        if(isvar){
            Variables.assign(calcVar +" := "+value);
        }
        //Depending on any operator chosen, String is split by operator into String[] and calls Variables.VarInt.ge}
        return value;

    }

    public static Integer getValue(String varOrNum) {
        if (varOrNum == null || varOrNum.isEmpty()) {
            return null; // or throw an IllegalArgumentException
        }
        if (Variables.VarInt.containsKey(varOrNum)) {
            return Variables.VarInt.get(varOrNum);
        }
        try {
            return Integer.parseInt(varOrNum);
        } catch (NumberFormatException e) {
            // Handle the case where varOrNum is not a valid integer
            return null; // or throw an IllegalArgumentException
        }
    }
}


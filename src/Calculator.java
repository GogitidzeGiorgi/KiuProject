public class Calculator {
    static char operator;
    static int value;
    static String[] parts;
    static String varName;
    static String expression;
    static String varNameTemp;
    static int valueForVar;
    static int secValue;

    public static void handleCalculation(String line) throws ArithmeticException {
        if (line.contains("==")) {
            parts = line.split("==");
            varName = parts[0].strip();
            varNameTemp = varName;
            expression = parts[1].strip();
            valueForVar = arithmetic(varNameTemp);
            Variables.VarInt.put(varNameTemp, valueForVar);
            value = arithmetic(expression);
            // Parts are split by any of operators, in case it is IF-conditional, it stores varName and it's new values
            // for every iteration(in case there is) and checks condition, for other operations it calls method - arithmetic.
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
    }

    static int arithmetic(String input) {
        boolean isVar = false;
        String calcVar = "";
        if (input.contains(":=")) {
            isVar = true;
            parts = input.split(":=");
            if (!parts[0].strip().contains(" ")) {
                expression = parts[1].strip();
                calcVar = parts[0].strip();
            }
        } else if (input.contains("=")) {
            isVar = true;
            parts = input.split("=");
            if (!parts[0].strip().contains(" ")) {
                expression = parts[1].strip();
                calcVar = parts[0].strip();
            }
        }
        //In case there are operations like a := 3 + 1,     a*a <= n, and so on
        //i.e. called from Interpreter, but are not pure expressions
        String stripped;
        if(null != expression){
            stripped = expression.replaceAll(" ", "");
        }  else {
            stripped = input.replaceAll(" ", "");
        }
        // Replaces spaces, whether it is pure expression or input called from Interpreter(arithmetic)

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
        //checks operator

        switch (operator) {
            case '+':
                String[] addNumbers = stripped.split("\\+");
                value = 1;
                if (!InputScanner.NotADigit(addNumbers[0].trim())) {
                    value = Integer.parseInt(addNumbers[0].trim());
                } else if (!InputScanner.NotANumber(addNumbers[0].trim())) {
                    value = Variables.VarInt.get(addNumbers[0].trim());
                }
                for (int i = 1; i < addNumbers.length; i++) {
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
                value = 1;
                if (!InputScanner.NotADigit(subNumbers[0].trim())) {
                    value = Integer.parseInt(subNumbers[0].trim());
                } else if (!InputScanner.NotANumber(subNumbers[0].trim())) {
                    value = Variables.VarInt.get(subNumbers[0].trim());
                }
                for (int i = 1; i < subNumbers.length; i++) {
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
                value = 1;
                if (!InputScanner.NotADigit(mulNumbers[0].trim())) {
                    value = Integer.parseInt(mulNumbers[0].trim());
                } else if (!InputScanner.NotANumber(mulNumbers[0].trim())) {
                    value = Variables.VarInt.get(mulNumbers[0].trim());
                }
                for (int i = 1; i < mulNumbers.length; i++) {
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
                if (!InputScanner.NotADigit(divNumbers[0].trim())) {
                    value = getValue(divNumbers[0].trim());
                } else if (!InputScanner.NotANumber(divNumbers[0].trim())) {
                    value = getValue(divNumbers[0].trim());
                }
                for (int i = 1; i < divNumbers.length; i++) {
                    if (!InputScanner.NotADigit(divNumbers[i].trim())) {
                        secValue = getValue(divNumbers[i].trim());
                    } else if (!InputScanner.NotANumber(divNumbers[i].trim())) {
                        secValue = getValue(divNumbers[i].trim());
                    }
                    if (secValue == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    value /= secValue;
                }
                break;

            case '%':
                String[] modNumbers = stripped.split("%");
                if (!InputScanner.NotADigit(modNumbers[0].trim())) {
                    value = getValue(modNumbers[0].trim());
                } else if (!InputScanner.NotANumber(modNumbers[0].trim())) {
                    value = getValue(modNumbers[0].trim());
                }
                for (int i = 1; i < modNumbers.length; i++) {
                    if (!InputScanner.NotADigit(modNumbers[i].trim())) {
                        secValue = getValue(modNumbers[i].trim());
                    } else if (!InputScanner.NotANumber(modNumbers[i])) {
                        secValue = getValue(modNumbers[i]);
                    }
                    if (secValue == 0) {
                        throw new ArithmeticException("Modulo by zero");
                    }
                    value %= secValue;
                }
                break;
            //Depending on operator chosen, case goes on. They are split by operators, first is stored as value to return,
            // other ones are stored as secValue and operated by value.
            default:
                value = getValue(stripped);
                break;
                // If there is no operator at all, there is one element and it somehow managed to get here,
                // though it should go to Variable.assign this is safe point, which is still stored
        }

        if (isVar) {
            Variables.assign(calcVar + " := " + value);
        }
        //If not pure expression was called from Interpreter, it is split by code above by ":=" or "="
        // and value assigns to calcVar.
        return value;
    }

    public static Integer getValue(String varOrNum) {
        if (varOrNum == null || varOrNum.isEmpty()) {
            return null;
        }
        if (Variables.VarInt.containsKey(varOrNum)) {
            return Variables.VarInt.get(varOrNum);
        }
        try {
            return Integer.parseInt(varOrNum);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    //This method returns value, whether it is variable store before, or digit as String.
    //This method returns value, whether it is variable store before, or digit as String.
}



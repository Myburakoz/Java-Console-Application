import java.util.Scanner;

public class Group06 {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        welcome();
        waitToClearScreen(input);
        makeWait();
        mainMenu(input);
    }

    /*
    * Terminal
    * */

    public static void welcome(){
        final String welcome = """
                ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗    ████████╗ ██████╗                                             \s
                ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝    ╚══██╔══╝██╔═══██╗                                            \s
                ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗         ██║   ██║   ██║                                            \s
                ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝         ██║   ██║   ██║                                            \s
                ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗       ██║   ╚██████╔╝                                            \s
                 ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝       ╚═╝    ╚═════╝                                             \s
                
                 ██████╗███╗   ███╗██████╗ ███████╗██████╗ ██╗  ██╗██████╗     ██████╗ ██████╗  ██████╗      ██╗███████╗ ██████╗████████╗     ██╗
                ██╔════╝████╗ ████║██╔══██╗██╔════╝╚════██╗██║  ██║╚════██╗    ██╔══██╗██╔══██╗██╔═══██╗     ██║██╔════╝██╔════╝╚══██╔══╝    ███║
                ██║     ██╔████╔██║██████╔╝█████╗   █████╔╝███████║ █████╔╝    ██████╔╝██████╔╝██║   ██║     ██║█████╗  ██║        ██║       ╚██║
                ██║     ██║╚██╔╝██║██╔═══╝ ██╔══╝   ╚═══██╗╚════██║ ╚═══██╗    ██╔═══╝ ██╔══██╗██║   ██║██   ██║██╔══╝  ██║        ██║        ██║
                ╚██████╗██║ ╚═╝ ██║██║     ███████╗██████╔╝     ██║██████╔╝    ██║     ██║  ██║╚██████╔╝╚█████╔╝███████╗╚██████╗   ██║        ██║
                 ╚═════╝╚═╝     ╚═╝╚═╝     ╚══════╝╚═════╝      ╚═╝╚═════╝     ╚═╝     ╚═╝  ╚═╝ ╚═════╝  ╚════╝ ╚══════╝ ╚═════╝   ╚═╝        ╚═╝ """;


        //Ascii art is temporary for now. It can be changed.


        final String members = """
                   ______                            __  ___               __                 \s
                  / ____/________  __  ______       /  |/  /__  ____ ___  / /_  ___  __________
                 / / __/ ___/ __ \\/ / / / __ \\     / /|_/ / _ \\/ __ `__ \\/ __ \\/ _ \\/ ___/ ___/
                / /_/ / /  / /_/ / /_/ / /_/ /    / /  / /  __/ / / / / / /_/ /  __/ /  (__  )\s
                \\____/_/   \\____/\\__,_/ .___/    /_/  /_/\\___/_/ /_/ /_/_.___/\\___/_/  /____/ \s
                                     /_/                                                      \s""";

        final String burak = """
                    ____                   __      ____                  _    \s
                   / __ )__  ___________ _/ /__   / __ \\____  ___ _   __(_)___\s
                  / __  / / / / ___/ __ `/ //_/  / / / /_  / / _ \\ | / / / __ \\
                 / /_/ / /_/ / /  / /_/ / ,<    / /_/ / / /_/  __/ |/ / / / / /
                /_____/\\__,_/_/   \\__,_/_/|_|   \\____/ /___/\\___/|___/_/_/ /_/\s""";
        final String suhan = """
                   _____       __                     ___             __         ____                \s
                  / ___/__  __/ /_  ____ _____       /   |  _________/ /___ _   / __ \\____  ___  _____
                  \\__ \\/ / / / __ \\/ __ `/ __ \\     / /| | / ___/ __  / __ `/  / / / / __ \\/ _ \\/ ___/
                 ___/ / /_/ / / / / /_/ / / / /    / ___ |/ /  / /_/ / /_/ /  / /_/ / / / /  __/ /   \s
                /____/\\__,_/_/ /_/\\__,_/_/ /_/    /_/  |_/_/   \\__,_/\\__,_/   \\____/_/ /_/\\___/_/    \s""";
        final String ramazan = """
                    ____                                               ____  _      __                   ____        __             __ \s
                   / __ \\____ _____ ___  ____ _____  ____ _____       / __ )(_)____/ /______ _____      / __ \\____  / /___  _______/ /__
                  / /_/ / __ `/ __ `__ \\/ __ `/_  / / __ `/ __ \\     / __  / / ___/ //_/ __ `/ __ \\    / / / /_  / / __/ / / / ___/ //_/
                 / _, _/ /_/ / / / / / / /_/ / / /_/ /_/ / / / /    / /_/ / / /  / ,< / /_/ / / / /   / /_/ / / /_/ /_/ /_/ / /  / ,<  \s
                /_/ |_|\\__,_/_/ /_/ /_/\\__,_/ /___/\\__,_/_/ /_/    /_____/_/_/  /_/|_|\\__,_/_/ /_/    \\____/ /___/\\__/\\__,_/_/  /_/|_| \s""";
        final String elif = """
                    _________ _________                                  ______      __          \s
                   / ____/ (_) __/__  /  ___  __  ______  ___  ____     /_  __/___ _/ /___ ___  __
                  / __/ / / / /_   / /  / _ \\/ / / / __ \\/ _ \\/ __ \\     / / / __ `/ / __ `/ / / /
                 / /___/ / / __/  / /__/  __/ /_/ / / / /  __/ /_/ /    / / / /_/ / / /_/ / /_/ /\s
                /_____/_/_/_/    /____/\\___/\\__, /_/ /_/\\___/ .___/    /_/  \\__,_/_/\\__,_/\\__, / \s
                                           /____/          /_/                           /____/  \s""";
        final String pressEnter = """
                 ___ _                                           ___     _             _            _                _   _                                   \s
                | _ \\ |___ __ _ ___ ___     _ __ _ _ ___ ______ | __|_ _| |_ ___ _ _  | |_ ___   __| |___ __ _ _ _  | |_| |_  ___   ___ __ _ _ ___ ___ _ _   \s
                |  _/ / -_) _` (_-</ -_)_  | '_ \\ '_/ -_|_-<_-< | _|| ' \\  _/ -_) '_| |  _/ _ \\ / _| / -_) _` | '_| |  _| ' \\/ -_) (_-</ _| '_/ -_) -_) ' \\ _\s
                |_| |_\\___\\__,_/__/\\___( ) | .__/_| \\___/__/__/ |___|_||_\\__\\___|_|    \\__\\___/ \\__|_\\___\\__,_|_|    \\__|_||_\\___| /__/\\__|_| \\___\\___|_||_(_)
                                       |/  |_|                                                                                                               \s""";

        System.out.println("\033[1;32m" + welcome + "\033[0m");
        System.out.printf("%n%n%n");

        System.out.println("\033[1;33m" + members + "\033[0m");
        System.out.println("\033[1;31m" + burak + "\033[0m");
        System.out.println("\033[1;31m" + suhan + "\033[0m");
        System.out.println("\033[1;31m" + ramazan + "\033[0m");
        System.out.println("\033[1;31m" + elif + "\033[0m");

        System.out.printf("%n%n%n");
    }

    public static void waitToClearScreen(Scanner input){
        final String pressEnter = """
                 ___ _                                           ___     _             _            _                _   _                                   \s
                | _ \\ |___ __ _ ___ ___     _ __ _ _ ___ ______ | __|_ _| |_ ___ _ _  | |_ ___   __| |___ __ _ _ _  | |_| |_  ___   ___ __ _ _ ___ ___ _ _   \s
                |  _/ / -_) _` (_-</ -_)_  | '_ \\ '_/ -_|_-<_-< | _|| ' \\  _/ -_) '_| |  _/ _ \\ / _| / -_) _` | '_| |  _| ' \\/ -_) (_-</ _| '_/ -_) -_) ' \\ _\s
                |_| |_\\___\\__,_/__/\\___( ) | .__/_| \\___/__/__/ |___|_||_\\__\\___|_|    \\__\\___/ \\__|_\\___\\__,_|_|    \\__|_||_\\___| /__/\\__|_| \\___\\___|_||_(_)
                                       |/  |_|                                                                                                               \s""";

        System.out.println("\033[1;39m" + pressEnter + "\033[0m");

        while (!input.hasNextLine())
        {
            input = new Scanner(System.in);
            clearScreen();
            System.out.println("\033[1;39m" + pressEnter + "\033[0m");
        }

        input.nextLine();
        clearScreen();
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void makeWait(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interruption cut: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public static void mainMenu(Scanner input) {
        input = new Scanner(System.in);

        boolean isNotTerminated = true;
        boolean isInputValid = true;

        do {
            System.out.printf("%n**********************************%n");
            System.out.println("Select an option:");
            System.out.println("[A] Primary School");
            System.out.println("[B] Secondary School");
            System.out.println("[C] High School");
            System.out.println("[D] University");
            System.out.println("[E] Terminate");
            System.out.println("**********************************");

            if(isInputValid)
                System.out.print("Your choice: ");
            else
                System.out.print("Please enter valid value [A-E]: ");

            String choice;

            try {
                if (!input.hasNextLine()) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                choice = input.nextLine().trim().toUpperCase();
            } catch (java.util.NoSuchElementException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            } catch (IllegalStateException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            }

            switch (choice){
                case "A":
                    clearScreen();
                    isInputValid = true;
                    primarySchoolMenu(input);
                    break;
                case "B":
                    clearScreen();
                    isInputValid = true;
                    secondrarySchoolMenu(input);
                    break;
                case "C":
                    clearScreen();
                    isInputValid = true;
                    highSchoolMenu(input);
                    break;
                case "D":
                    clearScreen();
                    isInputValid = true;
                    //universityMenu();
                    break;
                case "E":
                    clearScreen();
                    isInputValid = true;
                    //goodbye();
                    isNotTerminated = false;
                    break;
                default:
                    clearScreen();
                    isInputValid = false;
                    break;
            }
        }while(isNotTerminated);
    }

    public static void primarySchoolMenu(Scanner input){

        boolean isReturningMainMenu = false;
        boolean isInputValid = true;

        do {
            System.out.printf("%n**********************************%n");
            System.out.println("Select an option:");
            System.out.println("[A] Age and Zodiac Sign Detection");
            System.out.println("[B] Reverse the Words");
            System.out.println("[C] Return to Main Menu");
            System.out.println("**********************************");

            if(isInputValid)
                System.out.print("Your choice: ");
            else
                System.out.print("Please enter valid value [A-C]: ");

            String choice;

            try {
                if (!input.hasNextLine()) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                choice = input.nextLine().trim().toUpperCase();
            } catch (java.util.NoSuchElementException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            } catch (IllegalStateException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            }

            switch (choice){
                case "A":
                    clearScreen();
                    isInputValid = true;
                    ageZodiacDetectorMenu(input);
                    break;
                case "B":
                    clearScreen();
                    isInputValid = true;
                    wordReverserMenu(input);
                    break;
                case "C":
                    clearScreen();
                    isInputValid = true;
                    isReturningMainMenu = true;
                    break;
                default:
                    clearScreen();
                    isInputValid = false;
                    break;
            }

        }while(!isReturningMainMenu);
    }

    public static void secondrarySchoolMenu(Scanner input){

        boolean isReturningMainMenu = false;
        boolean isInputValid = true;

        do {
            System.out.printf("%n**********************************%n");
            System.out.println("Select an option:");
            System.out.println("[A] Prime Numbers");
            System.out.println("[B] Step-by-step Evaluation of Expression");
            System.out.println("[C] Return to Main Menu");
            System.out.println("**********************************");

            if(isInputValid)
                System.out.print("Your choice: ");
            else
                System.out.print("Please enter valid value [A-C]: ");

            String choice;

            try {
                if (!input.hasNextLine()) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                choice = input.nextLine().trim().toUpperCase();
            } catch (java.util.NoSuchElementException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            } catch (IllegalStateException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            }

            switch (choice){
                case "A":
                    clearScreen();
                    isInputValid = true;
                    primeNumbersMenu(input);
                    break;
                case "B":
                    clearScreen();
                    isInputValid = true;
                    evaluateExpressionMenu(input);
                    break;
                case "C":
                    clearScreen();
                    isInputValid = true;
                    isReturningMainMenu = true;
                    break;
                default:
                    clearScreen();
                    isInputValid = false;
                    break;
            }

        }while(!isReturningMainMenu);
    }

    public static void highSchoolMenu(Scanner input){

        boolean isReturningMainMenu = false;
        boolean isInputValid = true;

        do {
            System.out.printf("%n**********************************%n");
            System.out.println("Select an option:");
            System.out.println("[A] Statistical Information about an Array");
            System.out.println("[B] Distance between Two Arrays");
            System.out.println("[C] Return to Main Menu");
            System.out.println("**********************************");

            if(isInputValid)
                System.out.print("Your choice: ");
            else
                System.out.print("Please enter valid value [A-C]: ");

            String choice;

            try {
                if (!input.hasNextLine()) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                choice = input.nextLine().trim().toUpperCase();
            } catch (java.util.NoSuchElementException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            } catch (IllegalStateException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            }

            switch (choice){
                case "A":
                    clearScreen();
                    isInputValid = true;
                    statisticalInfoArrayMenu(input);
                    break;
                case "B":
                    clearScreen();
                    isInputValid = true;
                    distanceArrayMenu(input);
                    break;
                case "C":
                    clearScreen();
                    isInputValid = true;
                    isReturningMainMenu = true;
                    break;
                default:
                    clearScreen();
                    isInputValid = false;
                    break;
            }

        }while(!isReturningMainMenu);
    }

    /*
        Age Zodiac Sign Detection

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void ageZodiacDetectorMenu(Scanner input)
    {

    }

    /*
        Reverse the Words

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void wordReverserMenu(Scanner input)
    {

    }

    /*
        Prime Numbers

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void primeNumbersMenu(Scanner input)
    {

    }

    /*
        Step by step Evaluation of Expression

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void evaluateExpressionMenu(Scanner input)
    {
        boolean isInputValid = true;

        do {
            System.out.print("Enter a mathematical expression: ");
            String expression = input.nextLine().replace(" ", "");

            try {
                if (!isValidExpression(expression)) {
                    throw new IllegalArgumentException();
                }
                evaluateExpression(expression);
                isInputValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Re-enter a valid expression.");
                isInputValid = false;
            } catch (ArithmeticException e) {
                System.out.println("Error: Division by zero. " + e.getMessage());
                isInputValid = true;
            }

        }while(!isInputValid);
    }

    public static boolean isValidExpression(String expression){
        if (expression.isEmpty() || !areParenthesesBalanced(expression) || hasInvalidCharacters(expression)) {
            return false;
        }

        // Check for empty parentheses
        if (expression.contains("()")) {
            return false;
        }

        if (isOperator(expression.charAt(expression.length() - 1))) {
            return false;
        }

        if ((expression.startsWith("x") || expression.startsWith(":"))) {
            return false;
        }

        if (expression.startsWith("+")) {
            return false;
        }

        for (int i = 0; i < expression.length() - 1; i++) {
            char current = expression.charAt(i);
            char next = expression.charAt(i + 1);

            if (isOperator(current) && isOperator(next)) {
                // Allow x- or :- for unary minus (but NOT --)
                if ((current == 'x' || current == ':') && next == '-') {
                    continue;
                }
                return false;
            }
        }

        return true;
    }

    public static boolean areParenthesesBalanced(String expression){
        int balance = 0;

        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '(')
                balance++;
            if(expression.charAt(i) == ')')
                balance--;

            if(balance < 0)
                return false;
        }

        return balance == 0;
    }

    private static boolean hasInvalidCharacters(String expression) {
        for (char c : expression.toCharArray()) {
            if (!Character.isDigit(c) && !isOperator(c) && c != '(' && c != ')') {
                return true;
            }
        }
        return false;
    }

    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == 'x' || c == ':';
    }

    public static void evaluateExpression(String expression){
        if(expression.equals("-0")) {
            System.out.print(expression);
            System.out.println("= 0");
            return;
        }

        System.out.println(expression);

        String current = expression;
        String next = evaluateStep(current);

        while(!next.equals(current)){
            System.out.println("= " + next);
            current = next;
            next = evaluateStep(current);
        }
    }

    public static String evaluateStep(String step){
        int indexOfCloseParenthesis = step.indexOf(')');

        if(indexOfCloseParenthesis != -1){
            int indexOfOpenParenthesis = step.lastIndexOf('(', indexOfCloseParenthesis);
            String subStep = step.substring(indexOfOpenParenthesis + 1, indexOfCloseParenthesis);

            String evaluatedSubstep = evaluateStep(subStep);

            if(evaluatedSubstep.equals(subStep)){
                if(indexOfOpenParenthesis > 0 && step.charAt(indexOfOpenParenthesis - 1) == '-' && evaluatedSubstep.startsWith("-")) {
                    boolean isUnary = (indexOfOpenParenthesis - 1 == 0) || isOperator(step.charAt(indexOfOpenParenthesis - 2)) || step.charAt(indexOfOpenParenthesis - 2) == '(';

                    if(isUnary){
                        int value = Integer.parseInt(evaluatedSubstep);
                        int result = -value;
                        String resultStr = (result == 0) ? "0" : String.valueOf(result);
                        return step.substring(0, indexOfOpenParenthesis - 1) + resultStr + step.substring(indexOfCloseParenthesis + 1);
                    } else {
                        int operatorIndex = indexOfOpenParenthesis - 1;
                        int leftEnd = operatorIndex - 1;
                        int leftStart = findOperandStart(step, leftEnd);
                        String leftStr = step.substring(leftStart, leftEnd + 1);
                        int leftVal = Integer.parseInt(leftStr);
                        int rightVal = Integer.parseInt(evaluatedSubstep);

                        int result = leftVal - rightVal; // e.g. 2 - (-2) = 4

                        String resultStr = String.valueOf(result);
                        return step.substring(0, leftStart) + resultStr + step.substring(indexOfCloseParenthesis + 1);
                    }
                }

                String normalized = evaluatedSubstep.equals("-0") ? "0" : evaluatedSubstep;
                return step.substring(0, indexOfOpenParenthesis) + normalized + step.substring(indexOfCloseParenthesis + 1);
            }

            if (isSimpleNumber(evaluatedSubstep)) {
                // To get the desired "-(-3)" output, we keep the parentheses if it would create "--".
                if (indexOfOpenParenthesis > 0 && step.charAt(indexOfOpenParenthesis - 1) == '-' && evaluatedSubstep.startsWith("-")) {
                    return step.substring(0, indexOfOpenParenthesis + 1) + evaluatedSubstep + step.substring(indexOfCloseParenthesis);
                }
                // Otherwise, remove them.
                String normalized = evaluatedSubstep.equals("-0") ? "0" : evaluatedSubstep;
                return step.substring(0, indexOfOpenParenthesis) + normalized + step.substring(indexOfCloseParenthesis + 1);
            } else {
                // Case 3: The content evaluated to another expression (e.g. "(5*2-3)" -> "(10-3)").
                // Keep the parentheses and update the content.
                return step.substring(0, indexOfOpenParenthesis + 1) + evaluatedSubstep + step.substring(indexOfCloseParenthesis);
            }
        }

        if(step.contains("x") || step.contains(":"))
            return performMultiplicationOrDivision(step);

        if(step.contains("+") || step.contains("-"))
            return performPlusMinus(step);

        return step;
    }

    public static String performMultiplicationOrDivision(String expression){
        int multiplyIndex = expression.indexOf('x');
        int divIndex = expression.indexOf(':');

        int operatorIndex = -1;
        char operator = ' ';

        if (multiplyIndex != -1 && (divIndex == -1 || multiplyIndex < divIndex)) {
            operatorIndex = multiplyIndex;
            operator = 'x';
        } else if (divIndex != -1) {
            operatorIndex = divIndex;
            operator = ':';
        }

        return applyOperation(expression, operatorIndex, operator);
    }

    public static String performPlusMinus(String expression){
        int plusIndex = expression.indexOf('+', 1);
        int minusIndex = findNonUnaryMinus(expression);

        int operatorIndex = -1;
        char operator = ' ';

        // Special case: if expression contains -- (operator followed by negative number)
        int doubleMinus = expression.indexOf("--");
        if (doubleMinus >= 0) {
            return applyOperation(expression, doubleMinus, '-');
        }

        if (expression.startsWith("-"))
            if(plusIndex == -1 && minusIndex == -1)
                return expression;

        if (plusIndex != -1 && (minusIndex == -1 || plusIndex < minusIndex)) {
            operatorIndex = plusIndex;
            operator = '+';
        } else if (minusIndex != -1) {
            operatorIndex = minusIndex;
            operator = '-';
        } else if (expression.indexOf('-') == 0) {
            // Handle unary minus
            operatorIndex = 0;
            operator = '-';
        }

        return applyOperation(expression, operatorIndex, operator);
    }

    public static int findNonUnaryMinus(String expression){
        return expression.indexOf('-') == 0 ? expression.indexOf('-', 1):expression.indexOf('-');
    }

    public static int findCloseParenthesis(String expression, int openParenthesesIndex){
        int balance = 1;
        for (int i = openParenthesesIndex + 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                balance++;
            } else if (expression.charAt(i) == ')') {
                balance--;
                if (balance == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean isSimpleNumber(String expression){
        if(expression.isEmpty())
            return false;

        try{
            Integer.parseInt(expression);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static String applyOperation(String expression, int operatorIndex, char operator){
        if(operatorIndex == -1)
            return expression;

        //Unary Minus
        if(operatorIndex == 0 && operator == '-'){
            int rightOperandStartIndex = 1;
            int rightOperandEndIndex = findOperandEnd(expression, rightOperandStartIndex);
            String rightOperandStr = expression.substring(rightOperandStartIndex, rightOperandEndIndex);
            int rightOperand = Integer.parseInt(rightOperandStr);
            int result = -rightOperand;
            // Normalize -0 to 0
            String resultStr = (result == 0) ? "0" : String.valueOf(result);
            return resultStr + expression.substring(rightOperandEndIndex);
        }

        int leftOperandEnd = operatorIndex - 1;
        int leftOperandStart = findOperandStart(expression, leftOperandEnd);

        int rightOperandStart = operatorIndex + 1;
        int rightOperandEnd = findOperandEnd(expression, rightOperandStart);

        String leftOperandStr = expression.substring(leftOperandStart, leftOperandEnd + 1);
        String rightOperandStr = expression.substring(rightOperandStart, rightOperandEnd);

        int leftOperand = Integer.parseInt(leftOperandStr);
        int rightOperand = Integer.parseInt(rightOperandStr);

        int result = 0;
        switch (operator){
            case 'x':
                result = leftOperand * rightOperand;
                break;
            case ':':
                if (rightOperand == 0)
                    throw new ArithmeticException("Division by zero!");
                result = leftOperand / rightOperand;
                break;
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
        }

        String resultStr = (result == 0) ? "0" : String.valueOf(result);
        if (leftOperandStart > 0 && expression.charAt(leftOperandStart - 1) == '+' && result < 0)
            return expression.substring(0, leftOperandStart - 1) + resultStr + expression.substring(rightOperandEnd);

        return expression.substring(0, leftOperandStart) + resultStr + expression.substring(rightOperandEnd);
    }

    public static int findOperandStart(String expression, int index) {
        int start = index;
        if(start < 0)
            return 0;
        while (start >= 0 && Character.isDigit(expression.charAt(start)))
            start--;
        if(start >= 0 && (expression.charAt(start) == '-') && (start == 0 || isOperator(expression.charAt(start - 1)) || expression.charAt(start-1) == '('))
            start--;
        return start + 1;
    }

    public static int findOperandEnd(String expression, int index){
        int end = index;

        if(expression.charAt(end) == '-' || expression.charAt(end) == '+')
            end++;

        while (end < expression.length() && Character.isDigit(expression.charAt(end)))
            end++;
        return end;
    }

    /*
        Statistical Information about an Array

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void statisticalInfoArrayMenu(Scanner input)
    {

    }

    /*
        Distance between Two Arrays

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void distanceArrayMenu(Scanner input)
    {

    }
}
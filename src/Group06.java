import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Collections;

public class Group06 {
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        runMatrixRain(input);
        makeWait();
        mainMenu(input);
    }

    /*
    * Terminal
    * */
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&*()";
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[32m";
    private static final String BRIGHT_GREEN = "\033[1;32m";
    private static boolean running = true;

    private static void runMatrixRain(Scanner scanner) {
        int width = 120;
        int height = 40;
        Random random = new Random();

        System.out.print("\033[2J\033[?25l");

        int[] columnPositions = new int[width];
        float[] columnSpeeds = new float[width];
        float[] columnProgress = new float[width];

        for (int i = 0; i < width; i++) {
            columnPositions[i] = -(random.nextInt(height));
            columnSpeeds[i] = 0.3f + random.nextFloat() * 0.4f;
            columnProgress[i] = 0;
        }

        System.out.println("\033[1;1H\033[90mPress ENTER to exit Matrix screen...\033[0m");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\033[2J");

        // Enter kontrolü için thread başlat
        Thread inputThread = new Thread(new Runnable() {
            public void run() {
                scanner.nextLine();
                running = false;
            }
        });
        inputThread.setDaemon(true);
        inputThread.start();

        while (running) {
            printFrame(width, height, columnPositions, columnSpeeds, columnProgress, random);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                break;
            }
        }

        displayEndScreen(width, height, random);

        System.out.print("\033[2J\033[H\033[?25h");
    }

    private static void displayEndScreen(int width, int height, Random random) {
        String[] titleArt = {
                " ____ __  __ ____  _____ _____ _  _  _____   ____            _           _ ",
                " / ___|  \\/  |  _ \\| ____|___ /| || ||___ /  |  _ \\ _ __ ___ (_) ___  ___| |_",
                "| |   | |\\/| | |_) |  _|   |_ \\| || |_ |_ \\  | |_) | '__/ _ \\| |/ _ \\/ __| __|",
                "| |___| |  | |  __/| |___ ___) |__   _|__) | |  __/| | | (_) | |  __/ (__| |_",
                " \\____|_|  |_|_|   |_____|____/   |_||____/  |_|   |_|  \\___// |\\___|\\___|\\__|",
                "                                              |__/"
        };

        String[][] memberArts = {
                {
                        "    ____                         _                   _           ",
                        "  / ___|_ __ ___  _   _ _ __   | |    ___  __ _  __| | ___ _ __ ",
                        "  | |  _| '__/ _ \\| | | | '_ \\  | |   / _ \\/ _` |/ _` |/ _ \\ '__|",
                        " | |_| | | | (_) | |_| | |_) | | |__|  __/ (_| | (_| |  __/ |  ",
                        " \\____|_|  \\___/ \\__,_| .__/  |_____\\___|\\__,_|\\__,_|\\___|_|  ",
                        "                        |_|                                      "
                },
                {
                        "   ____                   __      ____                  _   ",
                        "   / __ )__  ___________ _/ /__   / __ \\____  ___ _   __(_)___",
                        "   / __  / / / / ___/ __ `/ //_/  / / / /_  / / _ \\ | / / / __ \\",
                        "  / /_/ / /_/ / /  / /_/ / ,<    / /_/ / / /_/  __/ |/ / / / / /",
                        "/_____/\\__,_/_/   \\__,_/_/|_|   \\____/ /___/\\___/|___/_/_/ /_/"
                },
                {
                        "  ____                         __  __                _                   ",
                        " / ___|_ __ ___  _   _ _ __   |  \\/  | ___ _ __ ___ | |__   ___ _ __ ___ ",
                        "| |  _| '__/ _ \\| | | | '_ \\  | |\\/| |/ _ \\ '_ ` _ \\| '_ \\ / _ \\ '__/ __|",
                        "| |_| | | | (_) | |_| | |_) | | |  | |  __/ | | | | | |_) |  __/ |  \\__ \\",
                        " \\____|_|  \\___/ \\__,_| .__/  |_|  |_|\\___|_| |_| |_|_.__/ \\___|_|  |___/",
                        "                       |_|                                                 "
                },
                {
                        "  _____       __                     ___             __         ____               ",
                        "  / ___/__  __/ /_  ____ _____       /   |  _________/ /___ _   / __ \\____  ___  _____",
                        "  \\__ \\/ / / / __ \\/ __ `/ __ \\     / /| | / ___/ __  / __ `/  / / / / __ \\/ _ \\/ ___/",
                        " ___/ / /_/ / / / / /_/ / / / /    / ___ |/ /  / /_/ / /_/ /  / /_/ / / / /  __/ /   ",
                        "/____/\\__,_/_/ /_/\\__,_/_/ /_/    /_/  |_/_/   \\__,_/\\__,_/   \\____/_/ /_/\\___/_/    "
                },
                {
                        "    ____                                               ____  _      __                   ____        __  _   _      __",
                        "   / __ \\____ _____ ___  ____ _____  ____ _____       / __ )(_)____/ /______ _____      / __ \\____  / /_(_) (_)____/ /__",
                        "  / /_/ / __ `/ __ `__ \\/ __ `/_  / / __ `/ __ \\     / __  / / ___/ //_/ __ `/ __ \\    / / / /_  / / __/ / / / ___/ //_/",
                        " / _, _/ /_/ / / / / / / /_/ / / /_/ /_/ / / / /    / /_/ / / /  / ,< / /_/ / / / /   / /_/ / / /_/ /_/ /_/ / /  / ,<  ",
                        "/_/ |_|\\__,_/_/ /_/ /_/\\__,_/ /___/\\__,_/_/ /_/    /_____/_/_/  /_/|_|\\__,_/_/ /_/    \\____/ /___/\\__/\\__,_/_/  /_/|_| "
                },
                {
                        "   _________ _________                                  ______      __         ",
                        "   / ____/ (_) __/__  /  ___  __  ______  ___  ____     /_  __/___ _/ /___ ___  __",
                        "  / __/ / / / /_   / /  / _ \\/ / / / __ \\/ _ \\/ __ \\     / / / __ `/ / __ `/ / / /",
                        "/ /___/ / / __/  / /__/  __/ /_/ / / / /  __/ /_/ /    / / / /_/ / / /_/ / /_/ /",
                        "/_____/_/_/_/    /____/\\___/\\__, /_/ /_/\\___/ .___/    /_/  \\__,_/_/\\__,_/\\__, / ",
                        "                           /____/          /_/                           /____/  "
                }
        };

        for (int clearStep = 0; clearStep < 30; clearStep++) {
            for (int col = 0; col < width; col++) {
                if (random.nextInt(30) < clearStep) {
                    for (int row = 0; row < height; row++) {
                        System.out.print(String.format("\033[%d;%dH", row + 1, col + 1));
                        System.out.print(" ");
                    }
                }
            }
            System.out.flush();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("\033[2J");

        int currentRow = 3;

        for (String line : titleArt) {
            int startCol = Math.max(1, (width - line.length()) / 2);
            for (int j = 0; j < line.length(); j++) {
                System.out.print(String.format("\033[%d;%dH", currentRow, startCol + j));
                System.out.print(BRIGHT_GREEN + line.charAt(j) + RESET);
                System.out.flush();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentRow++;
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        currentRow += 2;

        for (String[] memberArt : memberArts) {
            for (String line : memberArt) {
                int startCol = Math.max(1, (width - line.length()) / 2);
                for (int j = 0; j < line.length(); j++) {
                    System.out.print(String.format("\033[%d;%dH", currentRow, startCol + j));
                    System.out.print(BRIGHT_GREEN + line.charAt(j) + RESET);
                    System.out.flush();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                currentRow++;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentRow += 2;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\033[2J\033[H\033[?25h");
        System.out.println("\n");
    }

    private static void printFrame(int width, int height, int[] columnPositions,
                                   float[] columnSpeeds, float[] columnProgress, Random random) {
        for (int col = 0; col < width; col++) {
            columnProgress[col] += columnSpeeds[col];

            if (columnProgress[col] >= 1.0f) {
                columnProgress[col] -= 1.0f;
                int row = columnPositions[col];

                if (row >= 0 && row < height) {
                    System.out.print(String.format("\033[%d;%dH", row + 1, col + 1));

                    System.out.print(BRIGHT_GREEN + getRandomChar(random) + RESET);

                    for (int i = 1; i < 8; i++) {
                        int fadeRow = row - i;
                        if (fadeRow >= 0 && fadeRow < height) {
                            System.out.print(String.format("\033[%d;%dH", fadeRow + 1, col + 1));
                            System.out.print(GREEN + getRandomChar(random) + RESET);
                        }
                    }
                }

                int tailRow = row - 12;
                if (tailRow >= 0 && tailRow < height) {
                    System.out.print(String.format("\033[%d;%dH", tailRow + 1, col + 1));
                    System.out.print(" ");
                }

                columnPositions[col]++;

                if (columnPositions[col] >= height + 12) {
                    columnPositions[col] = -(random.nextInt(height / 2));
                    columnSpeeds[col] = 0.3f + random.nextFloat() * 0.4f;
                }
            }
        }

        System.out.flush();
    }

    private static char getRandomChar(Random random) {
        return CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
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
                    connect4(input);
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
        final String zodiacSigns = """
                          ████      ████      ██             ██        ███████████        ██████████               \s
                        ██    ██  ██    ██      ████    █████             ██  ██          ██    ██  ████           \s
                        ██      ██      ██          ████                  ██  ██          ██    ██      ██         \s
                         ██     ██    ██          ██    ██                ██  ██          ████████                 \s
                          █     ██     █          ██     ██               ██  ██          ████████                 \s
                                ██              ██        ███             ██  ██                    ████████       \s
                                ██              ██        ███             ██  ██            ██      ██     █       \s
                                ██                ██    ██                ██  ██              ████  ██    ██       \s
                                ██                  ████                ██████████                ██████████       \s
                
                
                
                
                
                
                                ██            ██████████                  █████           ██████████               \s
                               ████           ██████████                  ██████          ██████████               \s
                             ██    ██           ██  ██  ██             ███      ██          ██  ██  ██             \s
                             ██    ██           ██  ██  ██             ███      ██          ██  ██  ██             \s
                             ██    ██           ██  ██  █████          ███      ██          ██  ██  ██             \s
                           ████    ██           ██  ██  ██   ██           ██  ██            ██  ██  ██             \s
                         ██    ██  ██           ██  ██  ██   ██    ███████      ██████      ██  ██  ██             \s
                         ██    ██  ██  ██       ██  ██    ███                               ██  ██  ██     █       \s
                           ████    ████                  █   █      ██████████████████      ██  ██    █████        \s
                
                
                
                
                
                
                
                                 ████████   ██                     ███      ██      ██      ██          ███        \s
                                     ████     ██  ████                █   ██  ██  ██      ██  ██      ██  ██       \s
                                   ██  ██       ██    ██               ███      ██              ██  ██             \s
                                 ██    ██       ██    ██   ███                                ██  ██  ██           \s
                             ████               ██    ████     █                              ██  ██  ██           \s
                             ████               ██      ██     ██   █       █        █          ██  ███            \s
                             ████               ██      ██     █    ██      ██      ██          ██  ██             \s
                           ██                   ██    ██   ███        ██  ██  ██  ██      ██  ██      ██  ██       \s
                         ██                         ██                 ███      ██          ██          ███        \s
                """;

        final String menuAsciiArt = """
                  ___                                                                                                                                                                \s
                 -   -_,                                |\\         _-___        |\\                         -_-/                       -_____            ,               ,            \s
                (  ~/||    _                 _           \\\\            /         \\\\   '   _               (_ /    '   _                 ' | -,         ||              ||            \s
                (  / ||   / \\\\  _-_         < \\, \\\\/\\\\  / \\\\          /   /'\\\\  / \\\\ \\\\  < \\,  _-_       (_ --_  \\\\  / \\\\ \\\\/\\\\        /| |  |`  _-_  =||=  _-_   _-_ =||=  /'\\\\ ,._-_
                 \\/==||  || || || \\\\        /-|| || || || ||        =/=  || || || || ||  /-|| ||           --_ ) || || || || ||        || |==|| || \\\\  ||  || \\\\ ||    ||  || ||  || \s
                 /_ _||  || || ||/         (( || || || || ||        /    || || || || || (( || ||          _/  )) || || || || ||       ~|| |  |, ||/    ||  ||/   ||    ||  || ||  || \s
                (  - \\\\, \\\\_-| \\\\,/         \\/\\\\ \\\\ \\\\  \\\\/        /-__- \\\\,/   \\\\/  \\\\  \\/\\\\ \\\\,/       (_-_-   \\\\ \\\\_-| \\\\ \\\\        ~-____,  \\\\,/   \\\\, \\\\,/  \\\\,/  \\\\, \\\\,/   \\\\,\s
                          /  \\                                                                                       /  \\             (                                              \s
                         '----`                                                                                     '----`                                                           \s""";

        // Get the current date from the system clock in the specified timezone.
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Istanbul"));
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();

        int birthYear = 0, birthMonth = 0, birthDay = 0;

        boolean isInputInvalid = false;
        String message = "";

        // Loop until a valid date of birth is entered.
        do {
            // --- Try block---
            try {
                // --- Gent(input.nextLt User Input ---
                System.out.println(menuAsciiArt);
                System.out.println(zodiacSigns);
                System.out.printf("%n%n%n");
                System.out.printf("Note: The current date is set to %04d-%02d-%02d.%n", currentYear, currentMonth, currentDay);

                System.out.printf(message);
                System.out.print("Enter your birth year (1900-" + currentYear + "): ");

                birthYear = Integer.parseInt(input.nextLine().trim());

                if(!isValidYear(birthYear, currentYear)){
                    isInputInvalid = true;
                    message = "You entered invalid year. Try again!%n";
                    clearScreen();
                    continue;
                }

                System.out.print("Enter your birth month (1-12): ");
                birthMonth = Integer.parseInt(input.nextLine().trim());

                if(!isValidMonth(birthMonth, currentMonth, birthYear, currentYear)){
                    isInputInvalid = true;
                    message = "You entered invalid month. Try again!%n";
                    clearScreen();
                    continue;
                }


                System.out.print("Enter your birth day (1-31): ");
                birthDay = Integer.parseInt(input.nextLine().trim());

                if(!isValidDate(birthDay, birthMonth, birthYear, currentDay, currentMonth, currentYear)){
                    isInputInvalid = true;
                    message = "You entered invalid date. Try again!%n";
                    clearScreen();
                    continue;
                }

                isInputInvalid = false;
            }
            // --- Catch block ---
            catch (InputMismatchException e) {
                clearScreen();
                isInputInvalid = true;
            }
            catch (NumberFormatException e){
                clearScreen();
                message = "Error: Invalid input. Please enter numbers only. Try again.%n";
                isInputInvalid = true;
            }
            catch (NoSuchElementException e){
                clearScreen();
                System.out.println("EOF detected!");
                makeWait();
                return;
            }
        }while(isInputInvalid);

        clearScreen();

        // --- Perform Calculations ---
        int[] age = calculateAge(birthDay, birthMonth, birthYear, currentDay, currentMonth, currentYear);
        String zodiacSign = getZodiacSign(birthDay, birthMonth);

        // --- Display Results ---
        System.out.println("\n--------------------");
        System.out.println("      RESULTS       ");
        System.out.println("--------------------");
        System.out.printf("You are %d years, %d months, and %d days old.%n", age[0], age[1], age[2]);
        System.out.printf("Your Zodiac Sign is: %s%n%n", zodiacSign);
        System.out.println(findTheSymbolOfZodiacSign(zodiacSign));
        System.out.printf("%n--------------------%n");
    }

    /**
     * Calculates the age in years, months, and days with detailed, step-by-step logic.
     * @author Suhan Arda Öner
     * @param birthDay   The day of birth.
     * @param birthMonth The month of birth.
     * @param birthYear  The year of birth.
     * @param currentDay The current day.
     * @param currentMonth The current month.
     * @param currentYear The current year.
     * @return An integer array containing {years, months, days}.
     */
    public static int[] calculateAge(int birthDay, int birthMonth, int birthYear, int currentDay, int currentMonth, int currentYear) {
        // --- Step 1: Initialize calculation variables from the current date ---
        // We will modify these variables if we need to "borrow" time from a larger unit.
        int calculatedYear = currentYear;
        int calculatedMonth = currentMonth;
        int calculatedDay = currentDay;

        // --- Step 2: Adjust for days ---
        // If the birthday is later in the month than the current day, we can't subtract directly.
        // We need to "borrow" a month's worth of days.
        if (birthDay > calculatedDay) {
            // We go back one month.
            calculatedMonth--;

            // If going back one month puts us before January (i.e., month becomes 0),
            // it means we need to go back to December of the previous year.
            if (calculatedMonth < 1) {
                calculatedMonth = 12; // Month is now December
                calculatedYear--;   // And the year is one less
            }

            // Now, we add the number of days of the month we borrowed from to our current days.
            // We must determine the correct number of days for that specific month and year.
            int daysInBorrowedMonth;
            if (calculatedMonth == 2) { // The borrowed month was February
                // We must check if the year of THAT February was a leap year.
                daysInBorrowedMonth = isLeapYear(calculatedYear) ? 29 : 28;
            } else if (calculatedMonth == 4 || calculatedMonth == 6 || calculatedMonth == 9 || calculatedMonth == 11) {
                // April, June, September, November have 30 days.
                daysInBorrowedMonth = 30;
            } else {
                // All other months have 31 days.
                daysInBorrowedMonth = 31;
            }
            calculatedDay += daysInBorrowedMonth;
        }

        // --- Step 3: Adjust for months ---
        // Similarly, if the birth month is later in the year than the current month (after our day adjustment),
        // we need to "borrow" a year's worth of months.
        if (birthMonth > calculatedMonth) {
            // We go back one year.
            calculatedYear--;
            // We add 12 to our month count, representing the borrowed year.
            calculatedMonth += 12;
        }

        // --- Step 4: Perform the final subtraction ---
        // After all the adjustments and borrowing, we can now perform a simple subtraction
        // to find the final age components.
        int finalYears = calculatedYear - birthYear;
        int finalMonths = calculatedMonth - birthMonth;
        int finalDays = calculatedDay - birthDay;

        // Return the result as an array.
        return new int[]{finalYears, finalMonths, finalDays};
    }

    /**
     * Determines the zodiac sign based on the day and month.
     * @author Suhan Arda Öner
     * @param day   The day of birth.
     * @param month The month of birth.
     * @return A string representing the Zodiac sign.
     */
    public static String getZodiacSign(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "Aries";
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "Taurus";
        if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return "Gemini";
        if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return "Cancer";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "Leo";
        if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "Virgo";
        if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "Libra";
        if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return "Scorpius";
        if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return "Sagittarius";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return "Capricorn";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "Aquarius";
        if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) return "Pisces";
        return "Unknown";
    }

    /**
     * Checks if a given year is a leap year.
     * A year is a leap year if it is divisible by 4, except for end-of-century years,
     * which must be divisible by 400.
     * @author Suhan Arda Öner
     * @param year The year to check.
     * @return true if the year is a leap year, false otherwise.
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * A simple validation for the entered date.
     * @author Suhan Arda Öner and Burak Özevin
     * @param day   The day of the month.
     * @param month The month of the year.
     * @param year  The year.
     * @param currentDay The current day.
     * @param currentMonth The current month.
     * @param currentYear The current year.
     * @return true if the date is plausible, false otherwise.
     */
    public static boolean isValidDate(int day, int month, int year, int currentDay, int currentMonth, int currentYear) {
        if (day < 1 || day > 31)
            return false;

        if (month == 2) {
            if (isLeapYear(year)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return day <= 30;

        // Check if the birthdate is in the future relative to the current date
        if (year == currentYear) {
            if (month > currentMonth) {
                return false;
            }
            return month != currentMonth || day <= currentDay;
        }

        return true;
    }

    /**
     * A simple validation for the year.
     * @author Burak Özevin
     * @param year  The year.
     * @param currentYear The current year.
     * @return true if the year is plausible, false otherwise.
     */
    public static boolean isValidYear(int year, int currentYear){
        return !(year > currentYear || year < 1900);
    }

    /**
     * A simple validation for the month.
     * @author Burak Özevin
     * @param month The month.
     * @param currentMonth The current month.
     * @param year  The year.
     * @param currentYear The current year.
     * @return true if the month is plausible, false otherwise.
     */
    public static boolean isValidMonth(int month, int currentMonth, int year, int currentYear){
        if(month < 1 || month > 12)
            return false;

        if(year == currentYear){
            return month <= currentMonth;
        }

        return true;
    }

    /**
     * A function that shows ascii art according zodiac sign.
     * @author Burak Özevin
     * @param zodiac The zodiac sign
     * @return Ascii art of zodiac sign in string.
     */
    public static String findTheSymbolOfZodiacSign(String zodiac){
        switch (zodiac){
            case "Aries":
                return """
                           .-.   .-.
                          (_  \\ / _)
                               |
                               |
                        """;
            case "Taurus":
                return """
                            .     .
                            '.___.'
                            .'   `.
                           :       :
                           :       :
                            `.___.'
                        """;
            case "Gemini":
                return """
                            ._____.
                              | |
                              | |
                             _|_|_
                            '     '
                        """;
            case "Cancer":
                return """
                              .--.
                             /   _`.
                            (_) ( )
                           '.    /
                             `--'
                        """;
            case "Leo":
                return """
                              .--.
                             (    )
                            (_)  /
                                (_,
                        """;
            case "Virgo":
                return """
                          _
                           '`:--.--.
                             |  |  |_ 
                             |  |  | )
                             |  |  |/
                                  (J
                        """;
            case "Libra":
                return """
                                __
                           ___.'  '.___
                           ____________
                        
                        """;
            case "Scorpius":
                return """
                           _
                          ' `:--.--.
                             |  |  |
                             |  |  |
                             |  |  |  ..,
                                   `---':
                        """;
            case "Sagittarius":
                return """
                                  ...
                                  .':
                                .'
                            `..'
                            .'`.
                        """;
            case "Capricorn":
                return """
                                  _
                            \\      /_)
                             \\    /`.
                              \\  /   ;
                               \\/ __.'
                        """;
            case "Aquarius":
                return """
                        .-"-._.-"-._.-
                        .-"-._.-"-._.-
                        """;
            case "Pisces":
                return """
                        `-.    .-'
                           :  :
                         --:--:--
                           :  :
                        .-'    `-.
                        """;
            default:
                return "";
        }
    }


    /*
        Reverse the Words

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void wordReverserMenu(Scanner input)
    {
        final String wordReverser = """
                __        __            _   ____                                  \s
                \\ \\      / /__  _ __ __| | |  _ \\ _____   _____ _ __ ___  ___ _ __\s
                 \\ \\ /\\ / / _ \\| '__/ _` | | |_) / _ \\ \\ / / _ \\ '__/ __|/ _ \\ '__|
                  \\ V  V / (_) | | | (_| | |  _ <  __/\\ V /  __/ |  \\__ \\  __/ |  \s
                   \\_/\\_/ \\___/|_|  \\__,_| |_| \\_\\___| \\_/ \\___|_|  |___/\\___|_|  \s
                                          | |                                     \s
                                          | |                                     \s
                                          | |                                     \s
                                        __| |__                                   \s
                                        \\ \\_/ /                                   \s
                     _        __        _\\ V /                               ____ \s
                  __| |_ __ __\\ \\      / /\\_/ __ ___  ___ _ __ _____   _____|  _ \\\s
                 / _` | '__/ _ \\ \\ /\\ / /  | '__/ _ \\/ __| '__/ _ \\ \\ / / _ \\ |_) |
                | (_| | | | (_) \\ V  V /   | | |  __/\\__ \\ | |  __/\\ V /  __/  _ <\s
                 \\__,_|_|  \\___/ \\_/\\_/    |_|  \\___||___/_|  \\___| \\_/ \\___|_| \\_\\""";

        boolean isInputValid = false;
        String str = "";
        String message = "Enter a string: ";

        do {
            try {
                System.out.println(wordReverser);
                System.out.printf("%n%n");
                System.out.print(message);
                str = input.nextLine().trim();

                isInputValid = true;

                if(str.isEmpty()){
                    isInputValid = false;
                    message = "You entered an empty string or just spaces. Enter a valid string: ";
                }
            } catch (NoSuchElementException e) {
                clearScreen();
                System.out.println("EOF detected! Returning to the menu...");
                makeWait();
                clearScreen();
                return;
            }
        }while (!isInputValid);

        System.out.print("The result: ");
        System.out.println(process(str));
    }

    public static String process(String text) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int start = i;
                while (i < text.length() && Character.isLetter(text.charAt(i))) {
                    i++;
                }
                String word = text.substring(start, i);
                if (word.length() >= 2)
                    result.append(reverseRec(word));
                else
                    result.append(word);
            } else {
                result.append(c);
                i++;
            }

        }
        return result.toString();
    }
    private static String reverseRec(String s) {
        if(s.length() <= 1)
            return s;
        return reverseRec(s.substring(1)) + s.charAt(0);
    }

    /*
        Prime Numbers

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void primeNumbersMenu(Scanner input)
    {
        boolean isInputValid = true;
        int n = 0;

        do {
            System.out.println("PRIME NUMBERS");
            System.out.println("**************");

            try{
                System.out.print("Please enter a number: ");

                if(!input.hasNextLine()){
                    System.out.println("EOF detected!");
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                n = Integer.parseInt(input.nextLine().trim());

                if(n < 12)
                {
                    System.out.println("Please enter a number bigger than 12!");
                    isInputValid = false;
                    continue;
                }

            } catch (NumberFormatException e){
                System.out.println("Please enter a number!");
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;

            } catch (NoSuchElementException e){
                System.out.println("EOF detected!");
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
                continue;
            }

            isInputValid = true;

        }while (!isInputValid);

        try {
            long start = System.nanoTime();
            ArrayList<Integer> eratosthenes = null;
            eratosthenes = sieveOfEratosthenes(n);
            long end = System.nanoTime();
            System.out.println("First 3 primes -------> " + eratosthenes.getFirst() + ", " + eratosthenes.get(1) + ", " + eratosthenes.get(2));
            System.out.println("Last 2 primes -------> " + eratosthenes.get(eratosthenes.size() - 2) + ", " + eratosthenes.getLast());
            System.out.println("Time -------> " + (double)((end - start))/1000000000);
        } catch (OutOfMemoryError e) {
            System.out.println("The memory is not enough to process Eratosthenes Sieve. Returning to the menu...");
        }

        System.out.printf("%n%n");

        try {
            long start = System.nanoTime();
            ArrayList<Integer> sundaram = null;
            sundaram = sieveOfSundaram(n);
            long end = System.nanoTime();
            System.out.println("First 3 primes -------> " + sundaram.getFirst() + ", " + sundaram.get(1) + ", " + sundaram.get(2));
            System.out.println("Last 2 primes -------> " + sundaram.get(sundaram.size() - 2) + ", " + sundaram.getLast());
            System.out.println("Time -------> " + (double)((end - start))/1000000000);
        } catch (OutOfMemoryError e) {
            System.out.println("The memory is not enough to process Sundaram Sieve. Returning to the menu...");
        }

        System.out.printf("%n%n");

        try {
            long start = System.nanoTime();
            ArrayList<Integer> atkin = null;
            atkin = sieveOfAtkin(n);
            long end = System.nanoTime();
            System.out.println("First 3 primes -------> " + atkin.getFirst() + ", " + atkin.get(1) + ", " + atkin.get(2));
            System.out.println("Last 2 primes -------> " + atkin.get(atkin.size() - 2) + ", " + atkin.getLast());
            System.out.println("Time -------> " + (double)((end - start))/1000000000);
        } catch (OutOfMemoryError e) {
            System.out.println("The memory is not enough to process Atkin Sieve. Returning to the menu...");
        }
    }

    public static ArrayList<Integer> sieveOfEratosthenes(int n){
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i = 1; i <= n; i++)
            arr.add(i);

        arr.removeFirst();

        for(int i = 0; i < arr.size(); i++){
            for(int j = i + 1; j < arr.size(); j++){
                if(arr.get(j) % 2 == 0){
                    arr.remove(j);
                    j--;
                }
            }
        }

        return arr;
    }

    public static ArrayList<Integer> sieveOfSundaram(int n){
        ArrayList<Integer> arr = new ArrayList<>();

        int listSize = (n-2)/2;

        for(int i = 1; i <= listSize; i++)
            arr.add(i);

        boolean isRepeated = false;
        int repetition = 0;

        for(int i = 1; i < listSize; i++){
            repetition = 0;

            for(int j = 1; j < listSize; j++){
                int element = i + j + 2*i*j;

                if(element > listSize) {
                    if(j == repetition)
                        isRepeated = true;

                    break;
                }

                if(arr.contains(element))
                    repetition++;

                arr.remove(Integer.valueOf(element));
            }

            if (isRepeated)
                break;
        }

        for(int i = 0; i < arr.size(); i++)
            arr.set(i, 2*arr.get(i)+1);

        return arr;
    }

    public static ArrayList<Integer> sieveOfAtkin(int n){
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        ArrayList<Integer> arr3 = new ArrayList<>();
        ArrayList<Integer> primes = new ArrayList<>();

        primes.add(2);
        primes.add(3);
        primes.add(5);

        int limit = (int) Math.sqrt(n);

        for(int x = 1; x < limit; x++){
            for(int y = 1; y < limit; y++){
                int f1 = 4 * x * x + y * y;
                int f2 = 3 * x * x + y * y;
                int f3 = 3 * x * x - y * y;

                boolean condition1 = f1 < n;
                boolean condition2 = f2 < n;
                boolean condition3 = f3 < n && f3 > 0;

                if(condition1)
                    arr1.add(f1);

                if(condition2)
                    arr2.add(f2);

                if(condition3)
                    arr3.add(f3);
            }
        }

        for(int i = 0; i < arr1.size(); i++)
            if(arr1.get(i) % 4 != 1 || !isSquareFree(arr1.get(i)))
                arr1.remove(i--);

        for(int i = 0; i < arr2.size(); i++)
            if(arr2.get(i) % 6 != 1 || !isSquareFree(arr2.get(i)))
                arr2.remove(i--);

        for(int i = 0; i < arr3.size(); i++)
            if(arr3.get(i) % 12 != 11 || !isSquareFree(arr3.get(i)))
                arr3.remove(i--);

        ArrayList<Integer> merged = new ArrayList<>();
        merged.addAll(arr1);
        merged.addAll(arr2);
        merged.addAll(arr3);

        removeDuplicates(merged);

        Collections.sort(merged);

        for(int i = 0; i < merged.size(); i++)
            if(merged.get(i) != 2 && merged.get(i) != 3 && merged.get(i) != 5)
                primes.add(merged.get(i));

        return primes;
    }

    public static boolean isSquareFree(int element){
        for(int i = 2; i*i <= element; i++)
            if(element % (i*i) == 0)
                return false;

        return true;
    }

    public static void removeDuplicates(ArrayList<Integer> arr){
        for (int i = 0; i < arr.size(); i++)
            for (int j = i + 1; j < arr.size(); j++)
                if (arr.get(j).equals(arr.get(i)))
                    arr.remove(j--);
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

            if (!input.hasNextLine()) {
                System.out.printf("%nRe-enter a valid expression.%n");
                input = new Scanner(System.in);
                isInputValid = false;
                continue;
            }

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

    public static void statisticalInfoArrayMenu(Scanner input) {

        System.out.println("== Statistical Information about an Array ==");

        int n = getArraySize(input, "Enter the size of the array: ");

        if(n == -1)
            return;

        ArrayList<Double> arr = getTheElementsOfArrayDouble(input, n);

        if(arr == null)
            return;

        System.out.println("== Statistical Information about an Array ==");
        System.out.println();

        Collections.sort(arr);
        System.out.println("\nSorted Array: " + arr.toString());


        double median = findMedian(arr);
        System.out.println("Median: " + median);

        try {
            double arithmeticMean = arithmeticMean(arr);
            System.out.println("Arithmetic Mean: " + arithmeticMean);
        } catch (ArithmeticException e) {
            System.out.println("Overflow/Underflow happened when finding arithmetic mean.");
        }

        try {
            double geometricMean = geometricMean(arr);
            System.out.println("Geometric Mean: " + geometricMean);
        } catch (ArithmeticException e) {
            System.out.println("Overflow/Underflow happened when finding geometric mean.");
        }
        try {
            double harmonicMean = recursiveHarmonicSum(arr, 0);
            System.out.println("Harmonic Mean: " + harmonicMean);
        } catch (ArithmeticException e) {
            System.out.println("Division by zero or underflow risk");
        } catch (IllegalArgumentException e){
            System.out.println("Harmonic mean undefined for negative numbers.");
        }
    }

    public static int getArraySize(Scanner input, String message){
        int size = 0;
        boolean isInputValid = true;

        clearScreen();

        do {
            System.out.println("== Statistical Information about an Array ==");
            System.out.println();

            if (isInputValid)
                System.out.print(message);
            else
                System.out.print("Enter a valid value: ");


            try {
                size = Integer.parseInt(input.nextLine().trim());

                if(size < 1) {
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                isInputValid = true;

            } catch (java.util.NoSuchElementException e) {
                clearScreen();
                System.out.println("EOF Detected. Returning to the menu...");
                return -1;
            } catch (IllegalStateException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
            } catch (NumberFormatException e){
                isInputValid = false;
                clearScreen();
            }
        }while(!isInputValid);

        return size;
    }

    public static ArrayList<Double> getTheElementsOfArrayDouble(Scanner input, int size){
        ArrayList<Double> arr = new ArrayList<>();
        boolean isInputValid = true;

        for(int i = 0; i < size; i++)
        {
            do {
                clearScreen();
                System.out.println("== Statistical Information about an Array ==");
                System.out.println();

                if (isInputValid)
                    System.out.print("Enter a number " + (i+1) + ". element of the array: ");
                else
                    System.out.print("Enter a valid number: ");

                try{
                    double element = Double.parseDouble(input.nextLine().trim());
                    arr.add(element);
                    isInputValid = true;

                }catch (java.util.NoSuchElementException e) {
                    clearScreen();
                    System.out.println("EOF Detected. Returning to the menu...");
                    return null;
                } catch (IllegalStateException e) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                } catch (NumberFormatException e){
                    isInputValid = false;
                    clearScreen();
                }

            }while(!isInputValid);
        }

        return arr;
    }

    public static double findMedian(ArrayList<Double> arr){
        int n = arr.size();
        if(n % 2 == 0)
            return (arr.get(n/2 - 1) + arr.get(n/2)) / 2.0;

        return arr.get(n/2);
    }

    public static double arithmeticMean(ArrayList<Double> arr){
        double mean = 0;
        int n = arr.size();

        for(int i = 0; i < n; i++) {
            if(Double.isInfinite(mean))
                throw new ArithmeticException("Overflow/Underflow happened when finding arithmetic mean.");
            mean += arr.get(i) / n;
        }

        return mean;
    }

    public static double geometricMean(ArrayList<Double> arr){
        double mean = 1;
        int n = arr.size();

        for(int i = 0; i < n; i++) {
            if(Double.isInfinite(mean))
                throw new ArithmeticException("Overflow/Underflow happened when finding geometric mean.");

            mean *= Math.pow(arr.get(i), 1.0 / n);
        }

        return mean;
    }

    public static double recursiveHarmonicSum(ArrayList<Double> arr, int index) {
        if (index == arr.size())
            return 0;

        double value = arr.get(index);

        if (value == 0.0) {
            throw new ArithmeticException("Division by zero in harmonic mean.");
        }

        if (Math.abs(value) < 1e-308) {
            throw new ArithmeticException("Underflow risk: value too close to zero.");
        }

        if (value < 0) {
            throw new IllegalArgumentException("Harmonic mean undefined for negative numbers.");
        }
        return (1 / value) + recursiveHarmonicSum(arr, index + 1);
    }



    /*
        Distance between Two Arrays

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    public static void distanceArrayMenu(Scanner input)
    {
        System.out.println("=== Distance between Two Arrays ===");
        System.out.println();

        int dimension = checkDimension(input, "Enter the dimension of the arrays: ");

        System.out.println("=== Distance between Two Arrays ===");
        System.out.println();

        ArrayList<Integer> arr1 = getElementsOfTheArray(input, dimension, "first");
        ArrayList<Integer> arr2 = getElementsOfTheArray(input, dimension, "second");

        System.out.println("=== Distance between Two Arrays ===");
        System.out.println();

        int manhattan = getManhattanDistance(arr1, arr2);
        double euclidean = getEuclideanDistance(arr1, arr2);
        double cosine = getCosineSimilarity(arr1, arr2);

        System.out.println("=== Results ===");

        printArray(arr1);
        printArray(arr2);

        System.out.println("Manhattan Distance: " + manhattan);
        System.out.println("Euclidean Distance: " + euclidean);
        System.out.println("Cosine Similarity: " + cosine);
    }

    public static int checkDimension(Scanner input, String prompt){
        int dimension = 0;
        boolean isInputValid = true;

        clearScreen();

            do {
            System.out.println("=== Distance between Two Arrays ===");
            System.out.println();

            if (isInputValid)
                System.out.print(prompt);
            else
                System.out.print("Enter a valid value: ");


            try {
                if (!input.hasNextLine()) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                if (!input.hasNextInt()) {
                    input.nextLine();
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                dimension = input.nextInt();
                String remains = input.nextLine().trim();

                if(!remains.isEmpty())
                {
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                if(dimension < 1) {
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                isInputValid = true;

            } catch (java.util.NoSuchElementException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
            } catch (IllegalStateException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
            }

        }while(!isInputValid);

        return dimension;
    }

    public static ArrayList<Integer> getElementsOfTheArray(Scanner input, int dimension, String arrName)
    {
        ArrayList<Integer> arr = new ArrayList<>();
        boolean isInputValid = true;

        for(int i = 0; i < dimension; i++)
        {
            do {
                clearScreen();
                System.out.println("=== Distance between Two Arrays ===");
                System.out.println();

                if (isInputValid)
                    System.out.print("Enter a number [0-9] for " + (i+1) + ". element of the " + arrName + " array: ");
                else
                    System.out.print("Enter a valid number between 0 and 9: ");

                try{
                    if (!input.hasNextLine()) {
                        input = new Scanner(System.in);
                        isInputValid = false;
                        clearScreen();
                        continue;
                    }

                    if (!input.hasNextInt()) {
                        input = new Scanner(System.in);
                        isInputValid = false;
                        clearScreen();
                        continue;
                    }

                    int element = input.nextInt();
                    String remains = input.nextLine().trim();

                    if(!remains.isEmpty())
                    {
                        isInputValid = false;
                        clearScreen();
                        continue;
                    }

                    if(element < 0 || element > 9) {
                        isInputValid = false;
                        clearScreen();
                        continue;
                    }

                    arr.add(element);
                    isInputValid = true;

                }catch (java.util.NoSuchElementException e) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                } catch (IllegalStateException e) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                }

            }while(!isInputValid);
        }

        return arr;
    }

    public static int getManhattanDistance(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        int manhattan = 0;

        for(int i = 0; i < arr1.size(); i++)
            manhattan += Math.abs(arr1.get(i) - arr2.get(i));

        return manhattan;
    }

    public static double getEuclideanDistance(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        double euclidean = 0;

        for(int i = 0; i < arr1.size(); i++)
            euclidean += Math.abs(arr1.get(i) - arr2.get(i)) * Math.abs(arr1.get(i) - arr2.get(i));

        return Math.sqrt(euclidean);
    }

    public static double getCosineSimilarity(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        double dotProduct = 0;
        double lengthOfFirst = 0;
        double lengthOfSecond = 0;

        for(int i = 0; i < arr1.size(); i++) {
            dotProduct += arr1.get(i) * arr2.get(i);
            lengthOfFirst += arr1.get(i) * arr1.get(i);
            lengthOfSecond += arr2.get(i) * arr2.get(i);
        }

        lengthOfFirst = Math.sqrt(lengthOfFirst);
        lengthOfSecond = Math.sqrt(lengthOfSecond);

        return dotProduct/(lengthOfFirst * lengthOfSecond);
    }

    public static void printArray(ArrayList<Integer> arr){
        System.out.print("[");

        for(int i = 0; i < arr.size(); i++){
            if(i != arr.size() - 1)
                System.out.print(arr.get(i) + ", ");
            else
                System.out.print(arr.get(i));
        }

        System.out.println("]");
    }

    /*
        Connect4

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */
    public static final char PLAYER_ONE_DISC = 'X';
    public static final char PLAYER_TWO_DISC = 'O';
    public static final char EMPTY_CELL = ' ';

    /**
     * Main controller method for setting up the Connect Four game.
     * @author Suhan Arda Öner
     * @param input The scanner object for user input.
     */
    public static void connect4(Scanner input)
    {
        System.out.println("--- Welcome to Connect4 Game ---");

        // 1. Get Game Mode
        String gameMode = getGameMode(input);

        if(gameMode.isEmpty())
            return;

        // 2. Get Board Size
        int[] boardSize = getBoardSize(input);

        if(boardSize == null)
            return;

        int rows = boardSize[0];
        int cols = boardSize[1];

        // 3. Get Difficulty (only if playing against AI)
        String difficulty = "N/A"; // Default for two-player
        if (gameMode.equals("AI")) {
            difficulty = getDifficulty(input);
        }

        // --- Summary of Choices ---
        System.out.println("\n--- Game Settings Confirmed ---");
        System.out.println("Game Mode: " + (gameMode.equals("AI") ? "Single-Player (vs. AI)" : "Two-Player"));
        System.out.println("Board Size: " + rows + " rows x " + cols + " columns");
        System.out.println("Difficulty: " + difficulty);
        System.out.println("\nLet's start the game!");


        // --- Start the selected game loop ---
        if (gameMode.equals("PVP"))
            startGamePVP(rows, cols, input);

        else if (gameMode.equals("AI"))
            startGameAI(rows, cols, difficulty, input);
    }

    /**
     * Starts the game loop for a two-player (PVP) game.
     * @author Suhan Arda Öner
     * @param rows The number of rows in the game board.
     * @param cols The number of columns in the game board.
     * @param input The scanner object for user input.
     */
    public static void startGamePVP(int rows, int cols, Scanner input) {
        char[][] board = initializeBoard(rows, cols);
        boolean isPlayer1Turn = true;

        while (true) {
            printBoard(board);
            char currentPlayerDisc = (isPlayer1Turn) ? PLAYER_ONE_DISC : PLAYER_TWO_DISC;
            String playerName = (isPlayer1Turn) ? "Player 1" : "Player 2";

            // 1. Get move
            int col = getPlayerMove(input, board, playerName, currentPlayerDisc);

            if(col == -1)
                return;

            // 2. Place disc
            placeDisc(board, col, currentPlayerDisc);

            // 3. Check for win
            if (checkWin(board, currentPlayerDisc)) {
                printBoard(board);
                System.out.println("\n--- GAME OVER ---");
                System.out.println(playerName + " (" + currentPlayerDisc + ") wins!");
                break;
            }

            // 4. Check for draw
            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("\n--- GAME OVER ---");
                System.out.println("It's a draw!");
                break;
            }

            // 5. Switch turns
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    /**
     * Starts the game loop for a single-player (vs. Easy AI) game.
     * @author Suhan Arda Öner
     * @param rows The number of rows in the game board.
     * @param cols The number of columns in the game board.
     * @param input The scanner object for user input.
     */
    public static void startGameAI(int rows, int cols, String difficulty, Scanner input) {
        char[][] board = initializeBoard(rows, cols);
        boolean isPlayerTurn = true; // Player 1 is 'X', AI is 'O'

        while (true) {
            clearScreen();
            printBoard(board);

            if (isPlayerTurn) {
                // --- Player's Turn ---
                int col = getPlayerMove(input, board, "Player 1", PLAYER_ONE_DISC);
                placeDisc(board, col, PLAYER_ONE_DISC);

                if (checkWin(board, PLAYER_ONE_DISC)) {
                    clearScreen();
                    printBoard(board);
                    System.out.println("\n--- GAME OVER ---");
                    System.out.println("Player 1 (" + PLAYER_ONE_DISC + ") wins!");
                    break;
                }
            } else {
                // --- AI's Turn ---
                System.out.println("Computer's turn (" + PLAYER_TWO_DISC + ")...");

                int col = 0;

                switch (difficulty) {
                    case "Easy" -> col = getEasyAIMove(board);
                    case "Normal" -> {
                        System.out.println("Computer is thinking right now...");
                        int depth = 4;
                        col = bestMove(board, depth, PLAYER_TWO_DISC, PLAYER_ONE_DISC);
                    }
                    case "Hard" -> {
                        System.out.println("Computer is thinking right now...");
                        int depth = 6;
                        col = bestMove(board, depth, PLAYER_TWO_DISC, PLAYER_ONE_DISC);
                    }
                    default -> {
                        System.out.println("Computer is thinking right now...");
                        int depth = 8;
                        col = bestMove(board, depth, PLAYER_TWO_DISC, PLAYER_ONE_DISC);
                    }
                }


                placeDisc(board, col, PLAYER_TWO_DISC);

                if (checkWin(board, PLAYER_TWO_DISC)) {
                    makeWait();
                    clearScreen();
                    printBoard(board);
                    System.out.println("\n--- GAME OVER ---");
                    System.out.println("Computer (" + PLAYER_TWO_DISC + ") wins!");
                    break;
                }
            }

            // Check for draw (after either turn)
            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("\n--- GAME OVER ---");
                System.out.println("It's a draw!");
                break;
            }

            // Switch turns
            isPlayerTurn = !isPlayerTurn;
        }
    }

    /**
     * Creates a new empty board.
     * @author Suhan Arda Öner
     * @param rows The number of rows to create.
     * @param cols The number of columns to create.
     * @return A 2D char array filled with EMPTY_CELL.
     */
    public static char[][] initializeBoard(int rows, int cols) {
        char[][] board = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = EMPTY_CELL;
            }
        }
        return board;
    }

    /**
     * Prints the current state of the board to the console.
     * @author Suhan Arda Öner
     * @param board The 2D char array representing the game board.
     */
//    public static void printBoard(char[][] board) {
//        int rows = board.length;
//        int cols = board[0].length;
//
//        System.out.println();
//        // Print column headers
//        for (int c = 0; c < cols; c++) {
//            System.out.print(" " + (c + 1) + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < cols * 3; i++) {
//            System.out.print("-");
//        }
//        System.out.println();  // Divider
//
//        // Print board content
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < cols; c++) {
//                System.out.print("|" + board[r][c] + "|");
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < cols * 3; i++) {
//            System.out.print("-");
//        }
//        System.out.println();  // Divider
//    }

    public static void printBoard(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        final String reset = "\033[0m";
        final String red = "\033[91m";
        final String blue = "\033[94m";
        final String cyan = "\033[96m";
        final String yellow = "\033[93m";
        final String bold = "\033[1m";

        System.out.println();

        // Column numbers
        System.out.print("  ");
        for (int j = 1; j <= cols; j++) {
            System.out.print(bold + yellow + "   " + j + "    " + reset);
        }
        System.out.println();

        // Top border
        System.out.print(cyan + "╔═══════");
        for (int j = 1; j < cols; j++) {
            System.out.print("╦═══════");
        }
        System.out.println("╗" + reset);

        // Board rows
        for (int i = 0; i < rows; i++) {
            // Line 1 of cell
            System.out.print(cyan + "║" + reset);
            for (int j = 0; j < cols; j++) {
                char token = board[i][j];
                if (token == PLAYER_ONE_DISC) {
                    System.out.print(red + "  ▄▄▄  " + reset);
                } else if (token == PLAYER_TWO_DISC) {
                    System.out.print(blue + "  ▄▄▄  " + reset);
                } else {
                    System.out.print("       ");
                }
                System.out.print(cyan + "║" + reset);
            }
            System.out.println();

            // Line 2 of cell
            System.out.print(cyan + "║" + reset);
            for (int j = 0; j < cols; j++) {
                char token = board[i][j];
                if (token == PLAYER_ONE_DISC) {
                    System.out.print(red + " ▐   ▌ " + reset);
                } else if (token == PLAYER_TWO_DISC) {
                    System.out.print(blue + " ▐   ▌ " + reset);
                } else {
                    System.out.print("       ");
                }
                System.out.print(cyan + "║" + reset);
            }
            System.out.println();

            // Line 3 of cell
            System.out.print(cyan + "║" + reset);
            for (int j = 0; j < cols; j++) {
                char token = board[i][j];
                if (token == PLAYER_ONE_DISC) {
                    System.out.print(red + "  ▀▀▀  " + reset);
                } else if (token == PLAYER_TWO_DISC) {
                    System.out.print(blue + "  ▀▀▀  " + reset);
                } else {
                    System.out.print("       ");
                }
                System.out.print(cyan + "║" + reset);
            }
            System.out.println();

            // Middle divider (except after last row)
            if (i < rows - 1) {
                System.out.print(cyan + "╠═══════");
                for (int j = 1; j < cols; j++) {
                    System.out.print("╬═══════");
                }
                System.out.println("╣" + reset);
            }
        }

        // Bottom border
        System.out.print(cyan + "╚═══════");
        for (int j = 1; j < cols; j++) {
            System.out.print("╩═══════");
        }
        System.out.println("╝" + reset);
    }

    /**
     * Gets a valid column move from the human player.
     * Keeps asking until a valid, non-full column is chosen.
     * @author Suhan Arda Öner
     * @param input The scanner object for user input.
     * @param board The current game board (for checking full columns).
     * @param playerName The name of the current player (e.g., "Player 1").
     * @param playerDisc The disc character of the current player (e.g., 'X').
     * @return A 0-based integer for the chosen column.
     */
    public static int getPlayerMove(Scanner input, char[][] board, String playerName, char playerDisc) {
        int cols = board[0].length;
        int col = -1;
        boolean isValidMove = false;

        do {
            System.out.print("\n" + playerName + " (" + playerDisc + "), enter column (1-" + cols + "): ");
            try {
                String line = input.nextLine().trim();
                col = Integer.parseInt(line);

                // Check 1: Is it a valid column number?
                if (col >= 1 && col <= cols) {
                    // Check 2: Is the column full? (Check top-most cell)
                    if (board[0][col - 1] == EMPTY_CELL) {
                        isValidMove = true;
                    } else {
                        System.out.println("Error: Column " + col + " is full. Try again.");
                    }
                } else {
                    System.out.println("Error: Please enter a number between 1 and " + cols + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
            } catch (NoSuchElementException e){
                System.out.println("EOF Detected!");
                makeWait();
                return -1;
            }
        } while (!isValidMove);

        return col - 1; // Return 0-based index
    }

    /**
     * Generates a random, valid move for the "Easy" AI.
     * @author Suhan Arda Öner
     * @param board The current game board (for checking full columns).
     * @return A 0-based integer for the chosen column.
     */
    public static int getEasyAIMove(char[][] board) {
        int cols = board[0].length;
        SecureRandom rand = new SecureRandom();
        int col;

        do {
            col = rand.nextInt(cols); // Generates a random number from 0 to (cols-1)
        } while (board[0][col] != EMPTY_CELL); // Keep trying if the top cell is full

        System.out.println("Computer chose column: " + (col + 1));
        return col; // Already 0-based
    }

    /**
     * Places the player's disc in the lowest available spot in the chosen column.
     * @author Suhan Arda Öner
     * @param board The game board to modify.
     * @param col The 0-based column index to place the disc in.
     * @param playerDisc The disc character ('X' or 'O') to place.
     */
    public static void placeDisc(char[][] board, int col, char playerDisc) {
        int rows = board.length;
        for (int r = rows - 1; r >= 0; r--) { // Start from the bottom row and go up
            if (board[r][col] == EMPTY_CELL) {
                board[r][col] = playerDisc;
                return; // Disc placed, exit method
            }
        }
    }

    /**
     * Checks if the board is completely full (a draw).
     * @author Suhan Arda Öner
     * @param board The game board to check.
     * @return true if the board is full, false otherwise.
     */
    public static boolean isBoardFull(char[][] board) {
        int cols = board[0].length;
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == EMPTY_CELL) { // If *any* top cell is empty
                return false; // Board is not full
            }
        }
        return true; // All top cells are full
    }

    /**
     * Checks the entire board for a 4-in-a-row win for the given player.
     * @author Suhan Arda Öner
     * @param board The game board to check.
     * @param playerDisc The disc character ('X' or 'O') to check for a win.
     * @return true if a 4-in-a-row is found, false otherwise.
     */
    public static boolean checkWin(char[][] board, char playerDisc) {
        int rows = board.length;
        int cols = board[0].length;

        // Check for 4 in a row horizontally
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                if (board[r][c] == playerDisc &&
                        board[r][c+1] == playerDisc &&
                        board[r][c+2] == playerDisc &&
                        board[r][c+3] == playerDisc) {
                    return true;
                }
            }
        }

        // Check for 4 in a row vertically
        for (int r = 0; r <= rows - 4; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == playerDisc &&
                        board[r+1][c] == playerDisc &&
                        board[r+2][c] == playerDisc &&
                        board[r+3][c] == playerDisc) {
                    return true;
                }
            }
        }

        // Check for 4 in a row (diagonal /)
        for (int r = 3; r < rows; r++) { // Start from row 3 (0-indexed)
            for (int c = 0; c <= cols - 4; c++) {
                if (board[r][c] == playerDisc &&
                        board[r-1][c+1] == playerDisc &&
                        board[r-2][c+2] == playerDisc &&
                        board[r-3][c+3] == playerDisc) {
                    return true;
                }
            }
        }

        // Check for 4 in a row (diagonal \)
        for (int r = 0; r <= rows - 4; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                if (board[r][c] == playerDisc &&
                        board[r+1][c+1] == playerDisc &&
                        board[r+2][c+2] == playerDisc &&
                        board[r+3][c+3] == playerDisc) {
                    return true;
                }
            }
        }

        return false; // No win found
    }

    /**
     * Asks the user to select the game mode.
     * @author Suhan Arda Öner
     * @param input The scanner object for user input.
     * @return "PVP" for two-player or "AI" for single-player.
     */
    public static String getGameMode(Scanner input) {
        boolean isInputValid = false;
        int choice = 0;
        do {
            try {
                System.out.println("\nPlease select the game mode:");
                System.out.println("1. Two-Players");
                System.out.println("2. Single-Player (vs. Computer)");
                System.out.print("Your choice: ");

                choice = Integer.parseInt(input.nextLine().trim());

                if (choice == 1 || choice == 2) {
                    isInputValid = true;
                } else {
                    System.out.println("\nError: Invalid choice. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Invalid input. Please enter numbers only. Try again.");
            } catch (NoSuchElementException e){
                System.out.println("EOF Detected!");
                makeWait();
                return "";
            }
        } while (!isInputValid);

        return (choice == 1) ? "PVP" : "AI";
    }

    /**
     * Asks the user to select the board size.
     * @author Suhan Arda Öner
     * @param input The scanner object.
     * @return An integer array [rows, cols].
     */
    public static int[] getBoardSize(Scanner input) {
        boolean isInputValid = false;
        int choice = 0;
        int[] size = new int[2]; // [rows, cols]

        do {
            try {
                System.out.println("\nPlease select the board size:");
                System.out.println("1. Small (5x4)");  // 5 rows, 4 columns
                System.out.println("2. Medium (6x5)"); // 6 rows, 5 columns
                System.out.println("3. Large (7x6)");  // 7 rows, 6 columns
                System.out.print("Your choice: ");

                choice = Integer.parseInt(input.nextLine().trim());

                if (choice >= 1 && choice <= 3) {
                    isInputValid = true;
                    if (choice == 1) {
                        size = new int[]{5, 4};
                    } else if (choice == 2) {
                        size = new int[]{6, 5};
                    } else { // choice == 3
                        size = new int[]{7, 6};
                    }
                } else {
                    System.out.println("\nError: Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Invalid input. Please enter numbers only. Try again.");
            } catch (RuntimeException e) {
                System.out.println("EOF detected");
                makeWait();
                return null;
            }
        } while (!isInputValid);

        return size;
    }

    /**
     * Asks the user to select the AI difficulty.
     * @author Suhan Arda Öner
     * @param input The scanner object.
     * @return "Easy", "Medium", or "Hard".
     */
    public static String getDifficulty(Scanner input) {
        boolean isInputValid = false;
        int choice = 0;
        String difficulty = "";

        do {
            try {
                System.out.println("\nPlease select the difficulty of the game:");
                System.out.println("1. Easy (Random moves)");
                System.out.println("2. Medium (4 Move Depth)");
                System.out.println("3. Hard (6 Move Depth)");
                System.out.println("4. Extra Hard (8 Move Depth)");
                System.out.print("Your choice: ");

                choice = Integer.parseInt(input.nextLine().trim());

                if (choice >= 1 && choice <= 4) {
                    isInputValid = true;
                    if (choice == 1) {
                        difficulty = "Easy";
                    } else if (choice == 2) {
                        difficulty = "Medium";
                    } else if (choice == 3){ // choice == 3
                        difficulty = "Hard";
                    } else {
                        difficulty = "Extra Hard";
                    }
                } else {
                    System.out.println("\nError: Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: Invalid input. Please enter numbers only. Try again.");
            } catch (NoSuchElementException e) {
                System.out.println("EOF Detected!");
                makeWait();
                return "";
            }
        } while (!isInputValid);

        return difficulty;
    }

    public static ArrayList<Integer> getValidColumns(char[][] board){
        ArrayList<Integer> validCols = new ArrayList<>();

        for(int c = 0; c < board[0].length; c++)
            if(board[0][c] == EMPTY_CELL)
                validCols.add(c);

        return validCols;
    }

    public static int getOpenRowOfValidColumn(char[][] board, int c){
        for(int r = board.length - 1; r >= 0; r--)
            if(board[r][c] == EMPTY_CELL)
                return r;

        return -1;
    }

    public static void dropDisc(char[][] board, int col, char disc){
        int row = getOpenRowOfValidColumn(board, col);

        if(row != -1)
            board[row][col] = disc;
    }

    public static void undo(char[][] board, int col){
        for(int row = 0; row < board.length; row++){
            if(board[row][col] != EMPTY_CELL){
                board[row][col] = EMPTY_CELL;
                return;
            }
        }
    }

    public static int evaluateWindow(char[] window, char AIDisc, char playerDisc){
        int AIcount = 0, playerCount = 0, emptyCount = 0;

        for(int i = 0; i < window.length; i++){
            if(window[i] == AIDisc)
                AIcount++;
            else if(window[i] == playerDisc)
                playerCount++;
            else
                emptyCount++;
        }

        if(AIcount == 4)
            return 10000;
        if (AIcount == 3 && emptyCount == 1)
            return 500;
        if (AIcount == 2 && emptyCount == 2)
            return 10;

        if (playerCount == 4)
            return -10000;
        if (playerCount == 3 && emptyCount == 1)
            return -550;
        if (playerCount == 2 && emptyCount == 2)
            return -12;

        return 0;
    }

    public static int evaluateBoard(char[][] board, char AIDisc, char playerDisc){
        int score = 0;
        int rows = board.length;
        int cols = board[0].length;

        int centerCol = cols / 2;

        for(int r = 0; r < rows; r++){
            if(board[r][centerCol] == AIDisc)
                score += 10;
            else if(board[r][centerCol] == playerDisc)
                score -= 10;
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                char[] window = {board[r][c], board[r][c+1], board[r][c+2], board[r][c+3]};
                score += evaluateWindow(window, AIDisc, playerDisc);
            }
        }

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r <= rows - 4; r++) {
                char[] window = {board[r][c], board[r+1][c], board[r+2][c], board[r+3][c]};
                score += evaluateWindow(window, AIDisc, playerDisc);
            }
        }

        // Diagonal \
        for (int r = 0; r <= rows - 4; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                char[] window = {board[r][c], board[r+1][c+1], board[r+2][c+2], board[r+3][c+3]};
                score += evaluateWindow(window, AIDisc, playerDisc);
            }
        }

        // Diagonal /
        for (int r = 3; r < rows; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                char[] window = {board[r][c], board[r-1][c+1], board[r-2][c+2], board[r-3][c+3]};
                score += evaluateWindow(window, AIDisc, playerDisc);
            }
        }

        return score;
    }

    public static boolean isTerminalNode(char[][] board){
        return checkWin(board, PLAYER_ONE_DISC) || checkWin(board, PLAYER_TWO_DISC) || isBoardFull(board);
    }

    public static int minimax(char[][] board, int depth, boolean maximizing, int alpha, int beta, char AIDisc, char playerDisc){
        if(isTerminalNode(board) || depth == 0){
            if(checkWin(board, AIDisc))
                return 10000;
            if(checkWin(board, playerDisc))
                return -10000;
            if(isBoardFull(board))
                return 0;

            return evaluateBoard(board, AIDisc, playerDisc);
        }

        ArrayList<Integer> validCols = getValidColumns(board);
        int center = board[0].length / 2;
        quickSortByCenter(validCols, 0, validCols.size() - 1, center);

        if(maximizing){
            int value = Integer.MIN_VALUE;

            for(int i = 0; i < validCols.size(); i++){
                dropDisc(board, validCols.get(i), AIDisc);
                int score = minimax(board, depth - 1, false, alpha, beta, AIDisc, playerDisc);
                undo(board, validCols.get(i));

                if(score > value)
                    value = score;
                if(value > alpha)
                    alpha = value;
                if(alpha >= beta)
                    break;
            }

            return value;
        } else {
            int value = Integer.MAX_VALUE;

            for (int i = 0; i < validCols.size(); i++) {
                dropDisc(board, validCols.get(i), playerDisc);
                int score = minimax(board, depth - 1, true, alpha, beta, AIDisc, playerDisc);
                undo(board, validCols.get(i));

                if (score < value)
                    value = score;

                if (value < beta)
                    beta = value;

                if (alpha >= beta)
                    break;
            }
            return value;
        }
    }

    public static int bestMove(char[][] board, int depth, char AIDisc, char playerDisc){
        ArrayList<Integer> validCols = getValidColumns(board);

        if(validCols.isEmpty())
            return -1;

        int center = board[0].length;
        quickSortByCenter(validCols, 0, validCols.size() - 1, center);

        int bestCol = validCols.get(0);
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;

        for(int i = 0; i < validCols.size(); i++){
            dropDisc(board, validCols.get(i), AIDisc);
            int score = minimax(board, depth - 1, false, alpha, beta, AIDisc, playerDisc);
            undo(board, validCols.get(i));

            if(score > bestScore){
                bestScore = score;
                bestCol = validCols.get(i);
            }

            if(score > alpha)
                alpha = score;

            if(alpha >= beta)
                break;
        }
        return bestCol;
    }

    public static void quickSortByCenter(ArrayList<Integer> arr, int low, int high, int center){
        if(low < high){
            int pi = partition(arr, low, high, center);
            quickSortByCenter(arr, low, pi - 1, center);
            quickSortByCenter(arr, pi + 1, high, center);
        }
    }

    public static int partition(ArrayList<Integer> list, int low, int high, int center){
        int pivot = list.get(high);
        int pivotDist = Math.abs(pivot - center);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (Math.abs(list.get(j) - center) <= pivotDist) {
                i++;
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}
import java.util.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.security.SecureRandom;

/**
 * The class for the project 1
 * Group-6
 * This class contains age-and-zodiac-detection, reversing words in a sentence, three prime number algorithms,
 * mathematical expression calculator, statistical array information, distance calculator between two arrays, and
 * a connect four game
 * @author Burak Özevin, Suhan Arda Öner, Ramazan Birkan Öztürk, Elif Zeynep Talay
 */
public class Group06 {

    /**
     * Main function which initialize the scanner, includes a matrix rain animation and main menu of the program
     * @param args Command Line Arguments
     */
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

    /**
     * Runs a Matrix-style rain animation in the console using ANSI escape codes.
     * <p>
     * This method clears the screen, initializes columns of falling characters,
     * and continuously updates the terminal to simulate the Matrix digital rain effect.
     * The animation stops when the user presses ENTER.
     *
     * @param scanner the Scanner instance used to detect ENTER input for stopping the animation
     * @author Burak Özevin
     */
    public static void runMatrixRain(Scanner scanner) {
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

    /**
     * Displays the animated end screen after the Matrix rain effect finishes.
     * <p>
     * The end screen includes ASCII title art and member names displayed with
     * green color effects and timed animations.
     *
     * @param width  the width of the console in characters
     * @param height the height of the console in characters
     * @param random the Random instance used for generating random effects
     * @author Burak Özevin
     */
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
                        System.out.printf("\033[%d;%dH", row + 1, col + 1);
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
                System.out.printf("\033[%d;%dH", currentRow, startCol + j);
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
                    System.out.printf("\033[%d;%dH", currentRow, startCol + j);
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

    /**
     * Prints one frame of the Matrix rain animation to the console.
     * <p>
     * Each column of characters falls at its own random speed.
     * Bright green characters represent the head, and faded green characters
     * represent the trail. Columns are repositioned after reaching the bottom.
     *
     * @param width            number of columns in the console
     * @param height           number of rows in the console
     * @param columnPositions  current Y positions of falling columns
     * @param columnSpeeds     speed of each column’s fall
     * @param columnProgress   fractional progress for smooth frame timing
     * @param random           Random instance for character generation
     * @author Burak Özevin
     */
    private static void printFrame(int width, int height, int[] columnPositions,
                                   float[] columnSpeeds, float[] columnProgress, Random random) {
        for (int col = 0; col < width; col++) {
            columnProgress[col] += columnSpeeds[col];

            if (columnProgress[col] >= 1.0f) {
                columnProgress[col] -= 1.0f;
                int row = columnPositions[col];

                if (row >= 0 && row < height) {
                    System.out.printf("\033[%d;%dH", row + 1, col + 1);

                    System.out.print(BRIGHT_GREEN + getRandomChar(random) + RESET);

                    for (int i = 1; i < 8; i++) {
                        int fadeRow = row - i;
                        if (fadeRow >= 0 && fadeRow < height) {
                            System.out.printf("\033[%d;%dH", fadeRow + 1, col + 1);
                            System.out.print(GREEN + getRandomChar(random) + RESET);
                        }
                    }
                }

                int tailRow = row - 12;
                if (tailRow >= 0 && tailRow < height) {
                    System.out.printf("\033[%d;%dH", tailRow + 1, col + 1);
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

    /**
     * Returns a random character from the predefined character set used in the Matrix rain.
     *
     * @param random Random instance for selecting a character
     * @return a randomly chosen character for display
     * @author Burak Özevin
     */
    private static char getRandomChar(Random random) {
        return CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
    }

    /**
     * Clears the console using ANSI escape sequences.
     * <p>
     * Moves the cursor to the home position and clears the screen buffer.
     * @author Burak Özevin
     */
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pauses the current thread for a short, fixed duration (1 second).
     * <p>
     * If the sleep is interrupted, prints a message and re-asserts the thread's interrupted status.
     * @author Burak Özevin
     */
    public static void makeWait(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interruption cut: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Displays the top-level main menu and routes user choices to sub-menus.
     * <p>
     * The loop continues until the user selects the termination option.
     * Invalid or missing input re-prompts the user without terminating the program.
     *
     * <ul>
     *   <li>A –&gt; Primary School menu</li>
     *   <li>B –&gt; Secondary School menu</li>
     *   <li>C –&gt; High School menu</li>
     *   <li>D –&gt; University (Connect 4)</li>
     *   <li>E –&gt; Terminate program</li>
     * </ul>
     *
     * @param input a {@link java.util.Scanner} used to read user input from standard input
     * @author Burak Özevin
     */
    public static void mainMenu(Scanner input) {
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
                    secondarySchoolMenu(input);
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

    /**
     * Shows the Primary School menu and executes the selected feature.
     * <p>
     * Returns to the main menu when the user selects the corresponding option.
     * Invalid or missing input triggers a user-friendly re-prompt.
     *
     * <ul>
     *   <li>A –&gt; Age and Zodiac Sign Detection</li>
     *   <li>B –&gt; Reverse the Words</li>
     *   <li>C –&gt; Return to Main Menu</li>
     * </ul>
     *
     * @param input a {@link java.util.Scanner} used to read user input from standard input
     * @author Burak Özevin
     */
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

    /**
     * Shows the Secondary School menu and executes the selected feature.
     * <p>
     * Returns to the main menu when the user selects the corresponding option.
     * Invalid or missing input triggers a user-friendly re-prompt.
     *
     * <ul>
     *   <li>A –&gt; Prime Numbers</li>
     *   <li>B –&gt; Step-by-step Evaluation of Expression</li>
     *   <li>C –&gt; Return to Main Menu</li>
     * </ul>
     *
     * @param input a {@link java.util.Scanner} used to read user input from standard input
     * @author Burak Özevin
     */
    public static void secondarySchoolMenu(Scanner input){

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

    /**
     * Shows the High School menu and executes the selected feature.
     * <p>
     * Returns to the main menu when the user selects the corresponding option.
     * Invalid or missing input triggers a user-friendly re-prompt.
     *
     * <ul>
     *   <li>A –&gt; Statistical Information about an Array</li>
     *   <li>B –&gt; Distance between Two Arrays</li>
     *   <li>C –&gt; Return to Main Menu</li>
     * </ul>
     *
     * @param input a {@link java.util.Scanner} used to read user input from standard input
     * @author Burak Özevin
     */
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
     *  AGE AND ZODIAC DETECTOR MENU
     */

    /**
     * Interactively collects a user's date of birth and prints their age and zodiac sign.
     * <p>
     * This method renders ASCII art banners, shows the current date (Europe/Istanbul),
     * prompts for birth year/month/day with validation, and then prints:
     * <ul>
     *   <li>Age in years, months, and days</li>
     *   <li>Western zodiac sign and its symbol</li>
     * </ul>
     * The loop continues until a valid date is entered or an EOF condition is detected.
     * The console is cleared between attempts using ANSI escape sequences.
     *
     * @param input a non-null {@link java.util.Scanner} bound to standard input for interactive prompts
     * @author Burak Özevin
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
     */

    /**
     * Displays the "Word Reverser" feature menu and prompts the user for a string input.
     * <p>
     * The method prints an ASCII-art title banner, asks the user to enter a text line,
     * validates the input, and then prints the result of the {@link #process(String)} method.
     * Empty or whitespace-only inputs are rejected with a friendly message.
     * <p>
     * If an EOF signal (Ctrl+D/Ctrl+Z) is detected, the method prints a notice,
     * waits briefly, clears the screen, and returns to the previous menu.
     *
     * @author Burak Özevin and Ramazan Birkan Öztürk
     * @param input a {@link java.util.Scanner} instance used to capture user input from the console
     * @see #process(String)
     * @see #reverseRec(String)
     * @see #clearScreen()
     * @see #makeWait()
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

    /**
     * Processes a text string and reverses every word that contains at least two letters.
     * <p>
     * Non-letter characters (spaces, punctuation, symbols, etc.) remain in place.
     * This method iterates through each character, identifies contiguous letter sequences,
     * and reverses them recursively using {@link #reverseRec(String)}.
     *
     * <h3>Example</h3>
     * <pre>
     * Input:  "Hello, world!"
     * Output: "olleH, dlrow!"
     * </pre>
     *
     * @author Ramazan Birkan Öztürk
     * @param text the input string to process
     * @return a new string where all words (length ≥ 2) are reversed
     * @see #reverseRec(String)
     */
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

    /**
     * Recursively reverses a given string.
     * <p>
     * Base case: returns the string as-is if its length is ≤ 1.
     * Recursive case: returns the reversal of {@code s.substring(1)} followed by the first character.
     *
     * <h3>Example</h3>
     * <pre>
     * Input:  "abc"
     * Output: "cba"
     * </pre>
     *
     * @author Ramazan Birkan Öztürk
     * @param s the string to reverse
     * @return the reversed version of {@code s}
     */
    private static String reverseRec(String s) {
        if(s.length() <= 1)
            return s;
        return reverseRec(s.substring(1)) + s.charAt(0);
    }

    /*
        Prime Numbers

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    /**
     * Displays the "Prime Numbers" menu, validates the upper bound {@code n}, and
     * benchmarks three sieve algorithms (Eratosthenes, Sundaram, Atkin).
     *
     * @author Burak Özevin
     * @param input a {@link java.util.Scanner} bound to standard input
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
            //System.out.println(eratosthenes.toString());
            System.out.println("First 3 primes -------> " + eratosthenes.getFirst() + ", " + eratosthenes.get(1) + ", " + eratosthenes.get(2));
            System.out.println("Last 2 primes -------> " + eratosthenes.get(eratosthenes.size() - 2) + ", " + eratosthenes.getLast());
            System.out.println("Time -------> " + (double)((end - start))/1000000000);
        } catch (OutOfMemoryError e) {
            System.out.println("The memory is not enough to process Eratosthenes Sieve.");
        } catch (NegativeArraySizeException e){
            System.out.println("Overflow occured. Eratosthenes' Algorithm cannot proceed!");
        }

        System.out.printf("%n%n");

        try {
            long start = System.nanoTime();
            ArrayList<Integer> sundaram = null;
            sundaram = sieveOfSundaram(n);
            long end = System.nanoTime();
            //System.out.println(sundaram.toString());
            System.out.println("First 3 primes -------> " + sundaram.getFirst() + ", " + sundaram.get(1) + ", " + sundaram.get(2));
            System.out.println("Last 2 primes -------> " + sundaram.get(sundaram.size() - 2) + ", " + sundaram.getLast());
            System.out.println("Time -------> " + (double)((end - start))/1000000000);
        } catch (OutOfMemoryError e) {
            System.out.println("The memory is not enough to proceed Sundaram Sieve.");
        } catch (NegativeArraySizeException e){
            System.out.println("Overflow occured. Sundaram's Algorithm cannot be proceeded!");
        }

        System.out.printf("%n%n");

        try {
            long start = System.nanoTime();
            ArrayList<Integer> atkin = null;
            atkin = sieveOfAtkin(n);
            long end = System.nanoTime();
            //System.out.println(atkin.toString());
            System.out.println("First 3 primes -------> " + atkin.getFirst() + ", " + atkin.get(1) + ", " + atkin.get(2));
            System.out.println("Last 2 primes -------> " + atkin.get(atkin.size() - 2) + ", " + atkin.getLast());
            System.out.println("Time -------> " + (double)((end - start))/1000000000);
        } catch (OutOfMemoryError e) {
            System.out.println("The memory is not enough to process Atkin Sieve.");
        } catch (NegativeArraySizeException e){
            System.out.println("Overflow occured. Atkin's Algorithm cannot proceed!");
        }
    }

    /**
     * Generates all prime numbers up to and including {@code n} using the Sieve of Eratosthenes.
     * <p>
     * Complexity:
     * <ul>
     *   <li>Time: ~O(n log log n)</li>
     *   <li>Space: O(n) boolean array</li>
     * </ul>
     *
     * @author Burak Özevin
     * @param n the inclusive upper bound (non-negative)
     * @return a list of primes in ascending order within {@code [2, n]}
     */
    public static ArrayList<Integer> sieveOfEratosthenes(int n){
        boolean[] A = new boolean[n + 1];
        Arrays.fill(A, true);

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (A[i]) {
                int j = i * i;
                while (j <= n) {
                    A[j] = false;
                    j += i;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (A[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Generates all odd prime numbers up to and including {@code n} using the Sieve of Sundaram.
     * <p>
     * Description:
     * <ul>
     *   <li>Marks numbers of the form {@code i + j + 2ij} and maps the survivors to primes via {@code 2i + 1}.</li>
     *   <li>2 is not produced by the transformation; this implementation returns the odd primes only.</li>
     * </ul>
     *
     * Complexity:
     * <ul>
     *   <li>Time: ~O(n log n) in practice (depends on implementation details)</li>
     *   <li>Space: O(n) over the half-range array</li>
     * </ul>
     *
     * @author Burak Özevib
     * @param n the inclusive upper bound (non-negative)
     * @return a list of odd primes in ascending order within {@code [3, n]} (2 is omitted by construction)
     * @implNote If you need 2 included, add it manually when {@code n ≥ 2}.
     */
    public static ArrayList<Integer> sieveOfSundaram(int n){
        int k = (n - 1) / 2;
        boolean[] A = new boolean[k + 1];
        Arrays.fill(A, true);

        for (int i = 1; i <= Math.sqrt(k); i++) {
            int j = i;
            while (i + j + 2 * i * j <= k) {
                A[i + j + 2 * i * j] = false;
                j++;
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 1; i <= k; i++) {
            if (A[i]) {
                primes.add(2 * i + 1);
            }
        }

        return primes;
    }

    /**
     * Generates all prime numbers up to and including {@code n} using the Sieve of Atkin.
     * <p>
     * Description:
     * <ul>
     *   <li>Uses quadratic forms and modulo-12 rules to flip candidate flags, then eliminates squares.</li>
     *   <li>Explicitly seeds 2 and 3 as primes.</li>
     * </ul>
     *
     * Complexity:
     * <ul>
     *   <li>Time: O(n) depending on practical factors</li>
     *   <li>Space: O(n) boolean array</li>
     * </ul>
     *
     * @author Burak Özevin
     * @param n the inclusive upper bound (non-negative)
     * @return a list of primes in ascending order within {@code [2, n]}
     * @implNote For large {@code n}, memory usage is dominated by the boolean array of size {@code n + 1}.
     */
    public static ArrayList<Integer> sieveOfAtkin(int n){
        boolean[] arr = new boolean[n + 1];
        Arrays.fill(arr, false);

        if (n > 2) arr[2] = true;
        if (n > 3) arr[3] = true;

        for (int x = 1; x * x <= n; x++) {
            for (int y = 1; y * y <= n; y++) {

                int condition = (4 * x * x) + (y * y);
                if (condition <= n && (condition % 12 == 1 || condition % 12 == 5))
                    arr[condition] = !arr[condition];

                condition = (3 * x * x) + (y * y);
                if (condition <= n && condition % 12 == 7)
                    arr[condition] = !arr[condition];

                condition = (3 * x * x) - (y * y);
                if (x > y && condition <= n && condition % 12 == 11)
                    arr[condition] = !arr[condition];
            }
        }

        for (int i = 5; i * i <= n; i++) {
            if (!arr[i])
                continue;
            for (int j = i * i; j <= n; j += i * i)
                arr[j] = false;
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (arr[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    /*
        Step by step Evaluation of Expression

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    /**
     * Interactively reads a mathematical expression from the user and prints
     * its step-by-step evaluation to standard output.
     * <p>
     * The method removes spaces, validates the expression with
     * {@link #isValidExpression(String)}, then calls {@link #evaluateExpression(String)}.
     * On invalid input it re-prompts; on EOF it resets the scanner and continues.
     *
     * @author Burak Özevin
     * @param input a {@link java.util.Scanner} bound to standard input
     * @implNote Supported operators: '+' (plus), '-' (minus or unary minus),
     *           'x' (multiplication), ':' (division). Parentheses are supported.
     */
    public static void evaluateExpressionMenu(Scanner input)
    {
        boolean isInputValid = true;

        do {
            clearScreen();

            String asciiMathSymbols = """
                       _            __  __  _\s
                     _| |_   _____  \\ \\/ / (_)
                    |_   _| |_____|  >  <   _\s
                      |_|           /_/\\_\\ (_)
                    """;

            String evalauteExpression = """
                     _____            _             _                              \s
                    | ____|_   ____ _| |_   _  __ _| |_ ___                        \s
                    |  _| \\ \\ / / _` | | | | |/ _` | __/ _ \\                       \s
                    | |___ \\ V / (_| | | |_| | (_| | ||  __/                       \s
                    |_____| \\_/ \\__,_|_|\\__,_|\\__,_|\\__\\___|                       \s
                    | ____|_  ___ __  _ __ ___  ___ ___(_) ___  _ __               \s
                    |  _| \\ \\/ / '_ \\| '__/ _ \\/ __/ __| |/ _ \\| '_ \\              \s
                    | |___ >  <| |_) | | |  __/\\__ \\__ \\ | (_) | | | |             \s
                    |_____/_/\\_\\ .__/|_|  \\___||___/___/_|\\___/|_|_|_|_            \s
                    / ___|| |_ |_| _ __       | |__  _   _     / ___|| |_ ___ _ __ \s
                    \\___ \\| __/ _ \\ '_ \\ _____| '_ \\| | | |____\\___ \\| __/ _ \\ '_ \\\s
                     ___) | ||  __/ |_) |_____| |_) | |_| |_____|__) | ||  __/ |_) |
                    |____/ \\__\\___| .__/      |_.__/ \\__, |    |____/ \\__\\___| .__/\s
                                  |_|                |___/                   |_|   \s""";


            System.out.println("\033[91m" +asciiMathSymbols + "\033[0m");
            System.out.println("\033[93m" + evalauteExpression + "\033[0m");
            System.out.println("\033[91m" + asciiMathSymbols + "\033[0m");
            System.out.printf("%n%n");

            if(!isInputValid)
                System.out.println("Re-enter a valid expression.");

            System.out.print("Enter a mathematical expression: ");

            String expression = input.nextLine().replace(" ", "");

            try {
                if (!isValidExpression(expression)) {
                    throw new IllegalArgumentException();
                }
                evaluateExpression(expression);
                isInputValid = true;
            } catch (IllegalArgumentException e) {
                isInputValid = false;
            } catch (ArithmeticException e) {
                System.out.println("Error: Division by zero. " + e.getMessage());
                isInputValid = true;
            } catch (NoSuchElementException e){
                System.out.print("EOF Detected!");
                return;
            }

        }while(!isInputValid);
    }

    /**
     * Validates whether the given expression string is syntactically acceptable.
     * <p>
     * Rules enforced:
     * <ul>
     *   <li>Parentheses must be balanced (via {@link #areParenthesesBalanced(String)}).</li>
     *   <li>No invalid characters (via {@link #hasInvalidCharacters(String)}).</li>
     *   <li>No empty parentheses "()" and no trailing operator.</li>
     *   <li>Expression cannot start with '+' nor with a binary operator such as 'x' or ':'.</li>
     *   <li>Two consecutive operators are rejected, except allowing unary minus after 'x' or ':' or '(',
     *       and at the beginning of the expression.</li>
     * </ul>
     *
     * @author Burak Özevin
     * @param expression the raw expression (spaces may be removed by caller)
     * @return {@code true} if the expression passes the above checks; {@code false} otherwise
     */
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

            // Check for invalid implicit multiplication: digit followed by '(' or ')' followed by digit or '('
            if (Character.isDigit(current) && next == '(') {
                return false;
            }
            if (current == ')' && (Character.isDigit(next) || next == '(')) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if parentheses are balanced in the expression.
     *
     * @author Burak Özevin
     * @param expression the expression to check
     * @return {@code true} if balanced; {@code false} otherwise
     */
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

    /**
     * Returns whether the expression contains any character outside the allowed set:
     * digits, operators ({@code + - x :}), and parentheses.
     *
     * @author Burak Özevin
     * @param expression the expression to scan
     * @return {@code true} if an invalid character is found; {@code false} otherwise
     */
    private static boolean hasInvalidCharacters(String expression) {
        for (char c : expression.toCharArray()) {
            if (!Character.isDigit(c) && !isOperator(c) && c != '(' && c != ')') {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a character is one of the supported operators.
     *
     * @author Burak Özevin
     * @param c the character to test
     * @return {@code true} if {@code c} is '+', '-', 'x', or ':'; {@code false} otherwise
     */
    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == 'x' || c == ':';
    }

    /**
     * Prints a step-by-step evaluation trace of the given expression.
     * <p>
     * The method repeatedly calls {@link #evaluateStep(String)} until no further
     * reduction occurs, printing each intermediate result on its own line.
     * A special case for {@code "-0"} is normalized to {@code 0}.
     *
     * @author Burak Özevin
     * @param expression a syntactically valid expression string
     */
    public static void evaluateExpression(String expression){
        if(expression.equals("-0")) {
            System.out.print(expression);
            System.out.println();
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

    /**
     * Performs a single reduction step on the given expression:
     * <ol>
     *   <li>If a closing parenthesis exists, evaluates the innermost parenthesized sub-expression.</li>
     *   <li>Otherwise, performs one pending multiplication or division (leftmost-first).</li>
     *   <li>If none, performs one pending addition or subtraction (respecting unary minus rules).</li>
     * </ol>
     * If no change is possible, returns the input string unchanged.
     *
     * @author Burak Özevin
     * @param step the current expression state
     * @return the expression after applying exactly one reduction step (or the same string if none)
     * @implNote Uses custom operators: 'x' for multiplication and ':' for division.
     *           Unary minus is preserved where appropriate (e.g., "-(-3)" case).
     */
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

    /**
     * Locates and applies the leftmost multiplication ('x') or division (':') operation.
     * <p>
     * Delegates the actual splice-and-compute to an internal method {@code applyOperation}.
     * If no such operator exists, the input is returned as-is.
     *
     * @author Burak Özevin
     * @param expression the current expression
     * @return the expression with one 'x' or ':' operation evaluated
     */
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

    /**
     * Locates and applies the leftmost addition ('+') or (non-unary) subtraction ('-') operation.
     * <p>
     * Handles special cases:
     * <ul>
     *   <li>Preserves unary minus at the beginning or after an operator or '('.</li>
     *   <li>Simplifies sequences like {@code "--"} by applying one subtraction step.</li>
     * </ul>
     * Delegates the actual splice-and-compute to an internal method {@code applyOperation}.
     *
     * @author Burak Özevin
     * @param expression the current expression
     * @return the expression with one '+' or binary '-' operation evaluated
     */
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

    /**
     * Finds the index of the first minus sign that is treated as a binary operator
     * (i.e., not a unary minus).
     *
     * @author Burak Özevin
     * @param expression the expression to scan
     * @return the index of a binary '-' or {@code -1} if none found
     * @implNote A leading '-' at index 0 is considered unary and skipped.
     */
    public static int findNonUnaryMinus(String expression){
        return expression.indexOf('-') == 0 ? expression.indexOf('-', 1):expression.indexOf('-');
    }

    /**
     * Checks whether the given string is a simple (base-10) integer literal.
     * <p>
     * An empty string returns {@code false}. Otherwise, this method attempts
     * to parse with {@link Integer#parseInt(String)}.
     *
     * @author Burak Özevin
     * @param expression the string to test
     * @return {@code true} if {@code expression} is a valid integer literal; {@code false} otherwise
     */
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

    /**
     * Applies a single binary or unary operation at the specified operator index.
     * <p>
     * Supported operators:
     * <ul>
     *   <li>{@code 'x'} — multiplication (integer)</li>
     *   <li>{@code ':'} — division (integer, throws on divide-by-zero)</li>
     *   <li>{@code '+'} — addition (integer)</li>
     *   <li>{@code '-'} — subtraction (integer) or unary minus when at index 0</li>
     * </ul>
     *
     * <h3>Behavior</h3>
     * <ul>
     *   <li>If {@code operatorIndex == -1}, the expression is returned unchanged.</li>
     *   <li>If operator is unary minus at index 0, negates the right operand (normalizes {@code -0} to {@code 0}).</li>
     *   <li>Otherwise, extracts left/right integer operands surrounding {@code operatorIndex},
     *       computes the result using integer arithmetic, and splices it back.</li>
     *   <li>If a {@code '+'} immediately precedes a negative result, the redundant plus is removed.</li>
     * </ul>
     *
     * @author Burak Özevin
     * @param expression the full expression string
     * @param operatorIndex the index of the operator within {@code expression} (or {@code -1} to no-op)
     * @param operator one of {@code '+', '-', 'x', ':'}
     * @return the expression after applying exactly this one operation
     * @throws ArithmeticException if division by zero occurs for {@code ':'}
     * @implNote Uses integer arithmetic throughout (including integer division for {@code ':'}).
     */
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

        // Handle -- case: when result is negative and preceded by minus operator, convert to +
        if (leftOperandStart > 0 && expression.charAt(leftOperandStart - 1) == '-' && result < 0) {
            return expression.substring(0, leftOperandStart - 1) + "+" + (-result) + expression.substring(rightOperandEnd);
        }

        return expression.substring(0, leftOperandStart) + resultStr + expression.substring(rightOperandEnd);
    }

    /**
     * Finds the start index (inclusive) of the operand that ends at {@code index}.
     * <p>
     * Scans left over consecutive digits; also includes a unary {@code '-'} if it
     * appears at the start of the expression, or immediately after another operator or '('.
     *
     * @author Burak Özevin
     * @param expression the full expression
     * @param index the end position of the operand (typically {@code operatorIndex - 1})
     * @return the start index (inclusive) of the operand; {@code 0} if out of range
     * @implNote Only decimal digits and an optional unary minus are considered part of the operand.
     */
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

    /**
     * Finds the end index (exclusive) of the operand that starts at {@code index}.
     * <p>
     * Handles an optional leading sign ({@code '-'} or {@code '+'}) and then consumes
     * consecutive decimal digits.
     *
     * @author Burak Özevin
     * @param expression the full expression
     * @param index the start position of the operand (typically {@code operatorIndex + 1})
     * @return the end index (exclusive) immediately after the operand
     * @implNote Only decimal digits are consumed after an optional leading sign.
     */
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
     */

    /**
     * Interactive menu that collects an array of doubles from the user,
     * prints the sorted array, and displays median, arithmetic mean,
     * geometric mean, and harmonic mean.
     * <p>
     * Input is validated and EOF is handled gracefully (returning to the menu).
     * Each mean computation is wrapped with basic error handling to avoid crashes.
     *
     * @author Burak Özevin and Elif Zeynep Talay
     * @param input a {@link java.util.Scanner} bound to standard input
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
            double harmonicMean = harmonicMean(arr);
            System.out.println("Harmonic Mean: " + harmonicMean);
        } catch (ArithmeticException e) {
            System.out.println("Division by zero or underflow risk");
        } catch (IllegalArgumentException e){
            System.out.println("Harmonic mean undefined for negative numbers.");
        }
    }

    /**
     * Prompts the user for an array size (positive integer).
     * <p>
     * Re-prompts on invalid input. On EOF, prints a notice and returns {@code -1}.
     *
     * @author Elif Zeynep Talay
     * @param input a {@link java.util.Scanner} bound to standard input
     * @param message the prompt message to display
     * @return a positive array size, or {@code -1} if EOF was detected
     */
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

    /**
     * Reads {@code size} double values from the user and returns them as a list.
     * <p>
     * Each element is collected with validation; on EOF the method returns {@code null}.
     *
     * @author Elif Zeynep Talay
     * @param input a {@link java.util.Scanner} bound to standard input
     * @param size number of elements to read (must be ≥ 1)
     * @return an {@link ArrayList} of doubles, or {@code null} if EOF occurred
     */
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

    /**
     * Computes the median of a sorted list of doubles.
     * <p>
     * Assumes {@code arr} is already sorted in non-decreasing order.
     *
     * @author Elif Zeynep Talay
     * @param arr a non-empty, sorted list of doubles
     * @return the median value (middle element or average of the two middles)
     */
    public static double findMedian(ArrayList<Double> arr){
        int n = arr.size();
        if(n % 2 == 0)
            return (arr.get(n/2 - 1) + arr.get(n/2)) / 2.0;

        return arr.get(n/2);
    }

    /**
     * Computes the arithmetic mean of a list of doubles using a simple incremental sum.
     * <p>
     * Rounds the final result to 9 decimal places and (optionally) to 1 decimal place
     * if within a small tolerance.
     *
     * @author Elif Zeynep Talay
     * @param arr a non-empty list of doubles
     * @return the arithmetic mean
     * @throws ArithmeticException if overflow/underflow is detected during accumulation
     */
    public static double arithmeticMean(ArrayList<Double> arr){
        double mean = 0;
        int n = arr.size();

        for(int i = 0; i < n; i++) {
            if(Double.isInfinite(mean))
                throw new ArithmeticException("Overflow/Underflow happened when finding arithmetic mean.");
            mean += arr.get(i) / n;
        }

        double tolerance = 1e-9;
        mean = Math.round(mean * 1e9) / 1e9;
        if (Math.abs(mean - Math.round(mean * 10) / 10.0) < tolerance)
            mean = Math.round(mean * 10) / 10.0;

        return mean;
    }

    /**
     * Computes the geometric mean of a list of doubles via multiplicative accumulation:
     * {@code (Π x_i)^(1/n)} implemented as iterative {@code x_i^(1/n)} multiplications.
     * <p>
     * Rounds the final result to 9 decimal places and (optionally) to 1 decimal place
     * if within a small tolerance.
     *
     * @author Elif Zeynep Talay
     * @param arr a non-empty list of doubles
     * @return the geometric mean
     * @throws ArithmeticException if overflow/underflow is detected during accumulation
     * @implNote Values ≤ 0 will produce {@code NaN} from {@code Math.pow}; caller should ensure inputs are valid if needed.
     */
    public static double geometricMean(ArrayList<Double> arr){
        double mean = 1;
        int n = arr.size();

        for(int i = 0; i < n; i++) {
            if(Double.isInfinite(mean))
                throw new ArithmeticException("Overflow/Underflow happened when finding geometric mean.");

            mean *= Math.pow(arr.get(i), 1.0 / n);
        }

        double tolerance = 1e-9;
        mean = Math.round(mean * 1e9) / 1e9;
        if (Math.abs(mean - Math.round(mean * 10) / 10.0) < tolerance)
            mean = Math.round(mean * 10) / 10.0;

        return mean;
    }

    /**
     * Recursively computes the sum of reciprocals of the list elements:
     * {@code sum_{i=index}^{end} (1 / arr[i])}.
     * <p>
     * Validates inputs for harmonic mean requirements:
     * <ul>
     *   <li>Zero values are rejected (division by zero).</li>
     *   <li>Very small magnitudes trigger an underflow guard.</li>
     *   <li>Negative values are rejected (harmonic mean undefined for negatives).</li>
     * </ul>
     *
     * @author Elif Zeynep Talay
     * @param arr the list of values
     * @param index the current position (use 0 to sum the entire list)
     * @return the sum of reciprocals from {@code index} to the end
     * @throws ArithmeticException on division by zero or underflow risk
     * @throws IllegalArgumentException if a negative value is encountered
     */
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

    /**
     * Computes the harmonic mean: {@code H = n / (Σ (1 / x_i))}.
     * <p>
     * Uses {@link #recursiveHarmonicSum(ArrayList, int)} to obtain the reciprocal sum,
     * then divides the list size by that sum. Rounds the final result to 9 decimals.
     *
     * @author Elif Zeynep Talay
     * @param arr a non-empty list of strictly positive doubles
     * @return the harmonic mean
     * @throws IllegalArgumentException if {@code arr} is null/empty
     * @throws ArithmeticException if the reciprocal sum is zero or underflow occurs
     * @see #recursiveHarmonicSum(ArrayList, int)
     */
    public static double harmonicMean(ArrayList<Double> arr) {
        if (arr == null || arr.isEmpty())
            throw new IllegalArgumentException("Empty list: harmonic mean undefined.");

        double reciprocalSum = recursiveHarmonicSum(arr, 0);

        if (reciprocalSum == 0.0)
            throw new ArithmeticException("Sum of reciprocals is zero.");

        double h = arr.size() / reciprocalSum;

        h = Math.round(h * 1e9) / 1e9;

        return h;
    }



    /*
        Distance between Two Arrays

        NOTE: If you want add an extra function. You can of course add. These are just menus.
     */

    /**
     * Interactive menu for computing distances/similarity between two equal-length integer arrays.
     * <p>
     * Workflow:
     * <ol>
     *   <li>Reads the common dimension via {@link #checkDimension(Scanner, String)}.</li>
     *   <li>Collects two arrays using {@link #getElementsOfTheArray(Scanner, int, String)}.</li>
     *   <li>Computes Manhattan distance, Euclidean distance, and Cosine similarity.</li>
     * </ol>
     *
     * @author Burak Özevin
     * @param input a {@link java.util.Scanner} bound to standard input
     * @implNote Elements are constrained to integers in [0, 9] in the input routine.
     */
    public static void distanceArrayMenu(Scanner input)
    {
        System.out.println("=== Distance between Two Arrays ===");
        System.out.println();

        int dimension = checkDimension(input, "Enter the dimension of the arrays: ");

        if(dimension == -1)
            return;

        System.out.println("=== Distance between Two Arrays ===");
        System.out.println();

        ArrayList<Integer> arr1 = getElementsOfTheArray(input, dimension, "first");
        ArrayList<Integer> arr2 = getElementsOfTheArray(input, dimension, "second");

        if(arr1 == null || arr2 == null)
            return;

        System.out.println("=== Distance between Two Arrays ===");
        System.out.println();

        int manhattan = getManhattanDistance(arr1, arr2);
        double euclidean = getEuclideanDistance(arr1, arr2);
        double cosine = getCosineSimilarity(arr1, arr2);

        System.out.println("=== Results ===");

        System.out.println(arr1);
        System.out.println(arr2);

        System.out.println("Manhattan Distance: " + manhattan);
        System.out.println("Euclidean Distance: " + euclidean);
        System.out.println("Cosine Similarity: " + cosine);
    }

    /**
     * Prompts the user for a positive array dimension (integer ≥ 1), validating input.
     * <p>
     * Re-prompts on invalid or partial lines (e.g., trailing tokens). Resets the scanner on stream issues.
     *
     * @author Burak Özevin
     * @param input   a {@link java.util.Scanner} bound to standard input
     * @param prompt  the prompt message to display
     * @return a positive integer dimension
     */
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
                dimension = Integer.parseInt(input.nextLine());

                if(dimension < 1) {
                    isInputValid = false;
                    clearScreen();
                    continue;
                }

                isInputValid = true;

            } catch (NoSuchElementException e) {
                clearScreen();
                System.out.println("EOF Detected!");
                makeWait();
                return -1;
            } catch (IllegalStateException e) {
                input = new Scanner(System.in);
                isInputValid = false;
                clearScreen();
            }

        }while(!isInputValid);

        return dimension;
    }

    /**
     * Reads {@code dimension} integers for one array with validation and range limits.
     * <p>
     * Each element must be an integer in [0, 9]. Re-prompts on invalid input
     * or partially entered lines until a valid value is provided.
     *
     * @author Burak Özevin
     * @param input      a {@link java.util.Scanner} bound to standard input
     * @param dimension  the required number of elements (≥ 1)
     * @param arrName    a label used in prompts (e.g., "first", "second")
     * @return an {@link ArrayList} containing the collected integers in order
     */
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
                    int element = Integer.parseInt(input.nextLine());

                    if(element < 0 || element > 9) {
                        isInputValid = false;
                        clearScreen();
                        continue;
                    }

                    arr.add(element);
                    isInputValid = true;

                }catch (java.util.NoSuchElementException e) {
                    clearScreen();
                    System.out.println("EOF Detected!");
                    return null;
                } catch (IllegalStateException e) {
                    input = new Scanner(System.in);
                    isInputValid = false;
                    clearScreen();
                }

            }while(!isInputValid);
        }

        return arr;
    }

    /**
     * Computes the Manhattan (L1) distance between two equal-length integer arrays:
     * {@code sum_i |a_i - b_i|}.
     *
     * @author Burak Özevin
     * @param arr1 the first vector
     * @param arr2 the second vector (same length as {@code arr1})
     * @return the Manhattan distance as an integer
     */
    public static int getManhattanDistance(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        int manhattan = 0;

        for(int i = 0; i < arr1.size(); i++)
            manhattan += Math.abs(arr1.get(i) - arr2.get(i));

        return manhattan;
    }

    /**
     * Computes the Euclidean (L2) distance between two equal-length integer arrays:
     * {@code sqrt( sum_i (a_i - b_i)^2 )}.
     *
     * @author Burak Özevin
     * @param arr1 the first vector
     * @param arr2 the second vector (same length as {@code arr1})
     * @return the Euclidean distance as a double
     */
    public static double getEuclideanDistance(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        double euclidean = 0;

        for(int i = 0; i < arr1.size(); i++)
            euclidean += Math.abs(arr1.get(i) - arr2.get(i)) * Math.abs(arr1.get(i) - arr2.get(i));

        return Math.sqrt(euclidean);
    }

    /**
     * Computes the Cosine similarity between two equal-length integer arrays:
     * {@code (a · b) / (||a|| * ||b||)}.
     * <p>
     * Returns a value in [-1, 1] for non-negative vectors (with your input constraints, [0, 1]).
     * If either vector has zero magnitude, this method will evaluate to {@code NaN} (division by zero in doubles).
     *
     * @author Burak Özevin
     * @param arr1 the first vector
     * @param arr2 the second vector (same length as {@code arr1})
     * @return the cosine similarity in double precision
     * @implNote No explicit guard is made for zero vectors; caller may check for zero norms if needed.
     */
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
     * @author Suhan Arda Öner and Burak Özevin
     * @param rows The number of rows in the game board.
     * @param cols The number of columns in the game board.
     * @param difficulty The difficulty of AI
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
     * @author Suhan Arda Öner and Burak Özevin
     * @param board An ASCII Art Game Board
     */
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
     * Places the player's disc in the lowest available cell within the specified column.
     * If the column is not full, an animated falling effect is shown before the disc lands.
     *
     * <p>Behavior:
     * <ul>
     *   <li>Scans the column from bottom to top to find the lowest EMPTY cell.</li>
     *   <li>If found, calls {@link #animateFall(char[][], int, int, char)} to render the drop.</li>
     *   <li>Finally writes {@code playerDisc} to the computed final row.</li>
     * </ul>
     *
     * <p>Notes:
     * <ul>
     *   <li>No-op if the column is full (i.e., no EMPTY cell is found).</li>
     *   <li>Relies on {@code EMPTY_CELL}, {@link #clearScreen()}, and {@link #printBoard(char[][])}.</li>
     * </ul>
     *
     * @param board      the game board matrix [rows][cols]; modified in place
     * @param col        zero-based column index to drop into (must be within bounds)
     * @param playerDisc the disc character to place (e.g., 'X' or 'O')
     * @author Suhan Arda Öner and Burak Özevin
     */
    public static void placeDisc(char[][] board, int col, char playerDisc) {
        int finalRow = -1;
        for (int r = board.length - 1; r >= 0; r--) { // Start from the bottom row and go up
            if (board[r][col] == EMPTY_CELL) {
                finalRow = r;
                break;
            }
        }

        if (finalRow != -1) {
            animateFall(board, col, finalRow, playerDisc);
            board[finalRow][col] = playerDisc;
        }
    }

    /**
     * Animates a disc falling from the top of the column down to {@code finalRow}.
     * At each intermediate step, the board is redrawn to create a falling effect.
     *
     * <p>Behavior:
     * <ul>
     *   <li>Temporarily places the disc at each row from 0 to {@code finalRow}.</li>
     *   <li>Clears the screen and redraws the board at each step.</li>
     *   <li>Removes the temporary disc unless it is the final position.</li>
     *   <li>Waits ~100ms per frame to produce the animation.</li>
     * </ul>
     *
     * <p>Notes:
     * <ul>
     *   <li>Handles {@link InterruptedException} by re-asserting the interrupted status.</li>
     *   <li>Relies on {@code EMPTY_CELL}, {@link #clearScreen()}, and {@link #printBoard(char[][])}.</li>
     *   <li>Does not perform bounds checks—callers must ensure valid {@code col} and {@code finalRow}.</li>
     * </ul>
     *
     * @param board      the game board matrix [rows][cols]; temporarily modified for animation frames
     * @param col        zero-based column index being animated
     * @param finalRow   the landing row index (0 ≤ finalRow &lt; rows)
     * @param playerDisc the disc character to animate (e.g., 'X' or 'O')
     * @author Burak Özevin
     */
    public static void animateFall(char[][] board, int col, int finalRow, char playerDisc) {
        try {
            // Show the disc falling from top to final position
            for (int row = 0; row <= finalRow; row++) {
                // Temporarily place the token at current row
                board[row][col] = playerDisc;

                // Clear screen and redraw board
                clearScreen();
                printBoard(board);

                // Remove token from current position (unless it's the final position)
                if (row < finalRow) {
                    board[row][col] = EMPTY_CELL;
                }

                // Delay for animation effect
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
     * @author Suhan Arda Öner and Burak Özevin
     * @param input The scanner object.
     * @return "Easy", "Medium", "Hard", or "Extra Hard".
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

    /**
     * Returns the list of columns where a disc can legally be dropped.
     * A column is valid if its top cell is EMPTY (i.e., the column is not full).
     *
     * @author Burak Özevin
     * @param board the game board as a matrix [rows][cols]
     * @return an {@link ArrayList} of zero-based column indices that are playable
     */
    public static ArrayList<Integer> getValidColumns(char[][] board){
        ArrayList<Integer> validCols = new ArrayList<>();

        for(int c = 0; c < board[0].length; c++)
            if(board[0][c] == EMPTY_CELL)
                validCols.add(c);

        return validCols;
    }

    /**
     * Finds the lowest open row in a given valid column.
     *
     * @author Burak Özevin
     * @param board the game board as a matrix [rows][cols]
     * @param c     the column index to probe
     * @return the row index of the lowest empty cell; {@code -1} if the column is full
     */
    public static int getOpenRowOfValidColumn(char[][] board, int c){
        for(int r = board.length - 1; r >= 0; r--)
            if(board[r][c] == EMPTY_CELL)
                return r;

        return -1;
    }

    /**
     * Drops a disc into the specified column (if space exists).
     * The disc will occupy the lowest available row in that column.
     *
     * @author Burak Özevin
     * @param board the game board as a matrix [rows][cols]
     * @param col   the column index to drop into
     * @param disc  the disc character of AI
     * @implNote No-op if the column is full (no available row).
     */
    public static void dropDisc(char[][] board, int col, char disc){
        int row = getOpenRowOfValidColumn(board, col);

        if(row != -1)
            board[row][col] = disc;
    }

    /**
     * Undoes the last move in a given column by clearing the top-most non-empty cell.
     *
     * @author Burak Özevin
     * @param board the game board as a matrix [rows][cols]
     * @param col   the column index from which to undo
     */
    public static void undo(char[][] board, int col){
        for(int row = 0; row < board.length; row++){
            if(board[row][col] != EMPTY_CELL){
                board[row][col] = EMPTY_CELL;
                return;
            }
        }
    }

    /**
     * Heuristic evaluation of a 4-cell window along a line (row/column/diagonal).
     * Positive scores favor the AI; negative scores favor the opponent.
     *
     * @author Burak Özevin
     * @param window      a length-4 slice from the board
     * @param AIDisc      the AI's disc character
     * @param playerDisc  the opponent's disc character
     * @return an integer score for the window
     * @implNote Current weights: 4-in-row=±10000, 3+empty=+500/-550, 2+2empties=+100/-120.
     */
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
            return 100;

        if (playerCount == 4)
            return -10000;
        if (playerCount == 3 && emptyCount == 1)
            return -550;
        if (playerCount == 2 && emptyCount == 2)
            return -120;

        return 0;
    }

    /**
     * Evaluates the entire board position using a sum of window heuristics and a center-column bias.
     * Scans all horizontal, vertical, and both diagonal windows of length 4.
     *
     * @author Burak Özevin
     * @param board      the game board as a matrix [rows][cols]
     * @param AIDisc     the AI's disc character
     * @param playerDisc the opponent's disc character
     * @return a heuristic score where higher is better for the AI
     * @implNote Adds a small bonus/penalty for occupying the center column.
     */
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

    /**
     * Checks if the current board is in a terminal state:
     * AI wins, opponent wins, or the board is full (draw).
     *
     * @author Burak Özevin
     * @param board the game board as a matrix [rows][cols]
     * @return {@code true} if terminal; {@code false} otherwise
     * @implNote Relies on {@code checkWin(...)} and {@code isBoardFull(...)} helpers.
     */
    public static boolean isTerminalNode(char[][] board){
        return checkWin(board, PLAYER_ONE_DISC) || checkWin(board, PLAYER_TWO_DISC) || isBoardFull(board);
    }

    /**
     * Minimax with alpha-beta pruning for Connect4.
     * Performs a depth-limited search and returns a heuristic value for the node.
     *
     * @author Burak Özevin
     * @param board       the game board as a matrix [rows][cols]
     * @param depth       remaining search depth (0 = evaluate leaf)
     * @param maximizing  {@code true} if it's the AI's turn to move, else opponent's
     * @param alpha       alpha bound for pruning (max lower bound)
     * @param beta        beta bound for pruning (min upper bound)
     * @param AIDisc      the AI's disc character
     * @param playerDisc  the opponent's disc character
     * @return the best heuristic value from this node's perspective
     * @implNote Terminal checks: AI win=+10000, Opponent win=-10000, Full board=0.
     *         Node ordering is improved by sorting columns by proximity to center.
     */
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

    /**
     * Chooses the best column for the AI to play using minimax (alpha-beta).
     *
     * @author Burak Özevin
     * @param board      the game board as a matrix [rows][cols]
     * @param depth      search depth
     * @param AIDisc     the AI's disc character
     * @param playerDisc the opponent's disc character
     * @return the chosen column index, or {@code -1} if no valid move exists
     * @implNote Tries columns ordered by closeness to center for better pruning.
     */
    public static int bestMove(char[][] board, int depth, char AIDisc, char playerDisc){
        ArrayList<Integer> validCols = getValidColumns(board);

        if(validCols.isEmpty())
            return -1;

        int center = board[0].length / 2;
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

    /**
     * QuickSort helper that orders columns by their absolute distance to the center column.
     * Smaller distance (more central) comes first.
     *
     * @author Burak Özevin
     * @param arr    list of column indices to sort
     * @param low    low index (inclusive)
     * @param high   high index (inclusive)
     * @param center the center column index used as distance reference
     */
    public static void quickSortByCenter(ArrayList<Integer> arr, int low, int high, int center){
        if(low < high){
            int pi = partition(arr, low, high, center);
            quickSortByCenter(arr, low, pi - 1, center);
            quickSortByCenter(arr, pi + 1, high, center);
        }
    }

    /**
     * Partition function for {@link #quickSortByCenter(ArrayList, int, int, int)}.
     * Uses absolute distance to {@code center} as the key.
     *
     * @author Burak Özevin
     * @param list   list of column indices
     * @param low    low index (inclusive)
     * @param high   high index (inclusive)
     * @param center the center column index used as distance reference
     * @return the pivot's final index after partitioning
     */
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
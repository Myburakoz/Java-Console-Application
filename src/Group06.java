import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;

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

    }
}
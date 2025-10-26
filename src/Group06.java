import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

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
                    evalauteExpressionMenu(input);
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

    public static void evalauteExpressionMenu(Scanner input)
    {

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

        // 2. Get Board Size
        int[] boardSize = getBoardSize(input);
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
    public static void printBoard(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        System.out.println();
        // Print column headers
        for (int c = 0; c < cols; c++) {
            System.out.print(" " + (c + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < cols * 3; i++) {
            System.out.print("-");
        }
        System.out.println();  // Divider

        // Print board content
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print("|" + board[r][c] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < cols * 3; i++) {
            System.out.print("-");
        }
        System.out.println();  // Divider
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
        Random rand = new Random();
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